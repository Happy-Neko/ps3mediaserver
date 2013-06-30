/*
 * PS3 Media Server, for streaming any medias to your PS3.
 * Copyright (C) 2008  A.Brochard
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; version 2
 * of the License only.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 */
package net.pms.util;

import com.sun.jna.Native;
import com.sun.jna.Platform;
import net.pms.configuration.PmsConfiguration;
import net.pms.dlna.DLNAMediaAudio;
import net.pms.util.platform.Kernel32;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class CodecUtil {
	private static final Logger logger = LoggerFactory.getLogger(CodecUtil.class);
	private static final ArrayList<String> codecs = new ArrayList<String>();
	private static String defaultFontPath = null;

	static {
		// Make sure the list of codecs is initialized before other threads start retrieving it
		initCodecs();
	}

	/**
	 * Initialize the list of codec formats that are recognized by ffmpeg by
	 * parsing the "ffmpeg_formats.txt" resource. 
	 */
	private static void initCodecs() {
		InputStream is = CodecUtil.class.getClassLoader().getResourceAsStream("resources/ffmpeg_formats.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;

		try {
			while ((line = br.readLine()) != null) {
				if (line.contains(" ")) {
					codecs.add(line.substring(0, line.indexOf(" ")));
				} else {
					codecs.add(line);
				}
			}

			br.close();
			codecs.add("iso");
		} catch (IOException e) {
			logger.error("Error while retrieving codec list", e);
		}
	}


	/**
	 * Return the list of codec formats that will be recognized.
	 * @return The list of codecs.
	 */
	public static ArrayList<String> getPossibleCodecs() {
		return codecs;
	}

	public static int getAC3Bitrate(PmsConfiguration configuration, DLNAMediaAudio media) {
		int defaultBitrate = configuration.getAudioBitrate();
		if (media != null && defaultBitrate >= 384) {
			if (media.getAudioProperties().getNumberOfChannels() == 2 || configuration.getAudioChannelCount() == 2) {
				defaultBitrate = 448;
			} else if (media.getAudioProperties().getNumberOfChannels() == 1) {
				defaultBitrate = 192;
			}
		}
		return defaultBitrate;
	}

	public static synchronized String getDefaultFontPath() {
		if (defaultFontPath == null) {
			defaultFontPath = "";
			if (Platform.isWindows()) {
				// get Windows Arial
				final String winDir = getWindowsDirectory();
				if (winDir != null) {
					File winDirFile = new File(winDir);
					if (winDirFile.exists()) {
						File fontsDir = new File(winDirFile, "Fonts");
						if (fontsDir.exists()) {
							File arialDir = new File(fontsDir, "Arial.ttf");
							if (arialDir.exists()) {
								defaultFontPath = arialDir.getAbsolutePath();
							} else {
								arialDir = new File(fontsDir, "arial.ttf");
								if (arialDir.exists()) {
									defaultFontPath = arialDir.getAbsolutePath();
								}
							}
						}
					}
				}
				if (isBlank(defaultFontPath)) {
					defaultFontPath = getFont("C:\\Windows\\Fonts", "Arial.ttf");
				}
				if (isBlank(defaultFontPath)) {
					defaultFontPath = getFont("C:\\WINNT\\Fonts", "Arial.ttf");
				}
				if (isBlank(defaultFontPath)) {
					defaultFontPath = getFont("D:\\Windows\\Fonts", "Arial.ttf");
				}
				if (isBlank(defaultFontPath)) {
					defaultFontPath = getFont(".\\win32\\mplayer\\", "subfont.ttf");
				}
				return defaultFontPath;
			} else if (Platform.isLinux()) {
				// get Linux default font
				defaultFontPath = getFont("/usr/share/fonts/truetype/msttcorefonts/", "Arial.ttf");
				if (isBlank(defaultFontPath)) {
					defaultFontPath = getFont("/usr/share/fonts/truetype/ttf-bitstream-veras/", "Vera.ttf");
				}
				if (isBlank(defaultFontPath)) {
					defaultFontPath = getFont("/usr/share/fonts/truetype/ttf-dejavu/", "DejaVuSans.ttf");
				}
				return defaultFontPath;
			} else if (Platform.isMac()) {
				// get default osx font
				defaultFontPath = getFont("/System/Library/Frameworks/JavaVM.framework/Versions/1.5.0/Home/lib/fonts/", "LucidaSansRegular.ttf");
				return defaultFontPath;
			}
			return defaultFontPath;
		} else {
			return defaultFontPath;
		}
	}

	private static String getWindowsDirectory() {
		char test[] = new char[2 + 256 * 2];
		int r = Kernel32.INSTANCE.GetWindowsDirectoryW(test, 256);
		if (r > 0) {
			return Native.toString(test);
		}
		return null;
	}

	private static String getFont(String path, String name) {
		File f = new File(path, name);
		if (f.exists()) {
			return f.getAbsolutePath();
		}
		return null;
	}
}
