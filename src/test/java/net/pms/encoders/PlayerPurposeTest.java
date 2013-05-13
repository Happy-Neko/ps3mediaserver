/*
 * PS3 Media Server, for streaming any medias to your PS3.
 * Copyright (C) 2013  I. Sokolov
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
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package net.pms.encoders;

import org.junit.Test;

import static net.pms.encoders.PlayerPurpose.*;
import static org.fest.assertions.Assertions.assertThat;

public class PlayerPurposeTest {
	@Test
	public void testIsVideoPlayer() throws Exception {
		assertThat(VIDEO_FILE_PLAYER.isVideoPlayer()).isTrue();
		assertThat(AUDIO_FILE_PLAYER.isVideoPlayer()).isFalse();
		assertThat(VIDEO_WEB_STREAM_PLAYER.isVideoPlayer()).isTrue();
		assertThat(AUDIO_WEB_STREAM_PLAYER.isVideoPlayer()).isFalse();
		assertThat(MISC_PLAYER.isVideoPlayer()).isFalse();
	}

	@Test
	public void testIsAudioPlayer() throws Exception {
		assertThat(VIDEO_FILE_PLAYER.isAudioPlayer()).isFalse();
		assertThat(AUDIO_FILE_PLAYER.isAudioPlayer()).isTrue();
		assertThat(VIDEO_WEB_STREAM_PLAYER.isAudioPlayer()).isFalse();
		assertThat(AUDIO_WEB_STREAM_PLAYER.isAudioPlayer()).isTrue();
		assertThat(MISC_PLAYER.isAudioPlayer()).isFalse();
	}

	@Test
	public void testIsImagePlayer() throws Exception {
		assertThat(VIDEO_FILE_PLAYER.isImagePlayer()).isFalse();
		assertThat(AUDIO_FILE_PLAYER.isImagePlayer()).isFalse();
		assertThat(VIDEO_WEB_STREAM_PLAYER.isImagePlayer()).isFalse();
		assertThat(AUDIO_WEB_STREAM_PLAYER.isImagePlayer()).isFalse();
		assertThat(MISC_PLAYER.isImagePlayer()).isTrue();
	}
}
