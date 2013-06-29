package net.pms.util.platform;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.WString;
import com.sun.jna.ptr.LongByReference;

import java.nio.CharBuffer;

public interface Kernel32 extends Library {
	Kernel32 INSTANCE = (Kernel32) Native.loadLibrary("kernel32", Kernel32.class);

	int GetShortPathNameW(WString lpszLongPath, char[] lpdzShortPath, int cchBuffer);

	int GetWindowsDirectoryW(char[] lpdzShortPath, int uSize);

	boolean GetVolumeInformationW(
			char[] lpRootPathName,
			CharBuffer lpVolumeNameBuffer,
			int nVolumeNameSize,
			LongByReference lpVolumeSerialNumber,
			LongByReference lpMaximumComponentLength,
			LongByReference lpFileSystemFlags,
			CharBuffer lpFileSystemNameBuffer,
			int nFileSystemNameSize
	);

	int SetThreadExecutionState(int EXECUTION_STATE);
	int ES_SYSTEM_REQUIRED = 0x00000001;
	int ES_CONTINUOUS = 0x80000000;
}
