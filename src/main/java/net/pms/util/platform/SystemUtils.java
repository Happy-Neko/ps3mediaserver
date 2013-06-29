package net.pms.util.platform;

import net.pms.newgui.LooksFrame;

import java.io.File;
import java.net.NetworkInterface;
import java.net.SocketException;

public interface SystemUtils {
	/**
	 * Prevent PC from going into sleep mode.
	 */
	public abstract void disableOSSleepMode();

	/**
	 * Allow PC to go into sleep mode.
	 */
	public abstract void enableOSSleepMode();

	/**
	 * Returns short file path (8.3 DOS format on Windows platform) if relevant.
	 * @param longPathName path to file.
	 * @return short path if relevant of unchanged {@code longPathName}.
	 */
	public abstract String getShortPathName(String longPathName);

	public abstract String getWindowsDirectory();

	public abstract String getDiskLabel(File f);

	/**
	 * Tests if Kerio Firewall is installed.
	 * @return {@code true} if Kerio Firewall is installed, {@code false} otherwise.
	 */
	public abstract boolean isKerioFirewallInstalled();

	public abstract String getVlcPath();

	public abstract String getVlcVersion();

	/**
	 * Open HTTP URLs in the default browser.
	 * @param uri URI string to open externally.
	 */
	public void browseURI(String uri);

	public boolean isNetworkInterfaceLoopback(NetworkInterface ni) throws SocketException;

	public void addSystemTray(final LooksFrame frame);

	/**
	 * Fetch the hardware address for a network interface.
	 * 
	 * @param ni Interface to fetch the mac address for
	 * @return the mac address as bytes, or null if it couldn't be fetched.
	 * @throws SocketException
	 *             This won't happen on Mac OS, since the NetworkInterface is
	 *             only used to get a name.
	 */
	public byte[] getHardwareAddress(NetworkInterface ni) throws SocketException;

	/**
	 * Return the platform specific ping command for the given host address,
	 * ping count and packet size.
	 *
	 * @param hostAddress The host address.
	 * @param count The ping count.
	 * @param packetSize The packet size.
	 * @return The ping command.
	 */
	String[] getPingCommand(String hostAddress, int count, int packetSize);
}
