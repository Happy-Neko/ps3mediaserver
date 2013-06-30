package net.pms.util.platform;

import net.pms.newgui.LooksFrame;

import java.io.File;

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

	public void addSystemTray(final LooksFrame frame);

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
