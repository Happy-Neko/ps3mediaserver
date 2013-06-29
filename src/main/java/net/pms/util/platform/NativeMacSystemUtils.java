package net.pms.util.platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class NativeMacSystemUtils extends GenericSystemUtils {
	private final static Logger logger = LoggerFactory.getLogger(NativeMacSystemUtils.class);

	@Override
	public void browseURI(String uri) {
		try {
			// On OS X, open the given URI with the "open" command.
			// This will open HTTP URLs in the default browser.
			Runtime.getRuntime().exec(new String[] { "open", uri });
		} catch (IOException e) {
			logger.trace("Unable to open the given URI: {}", uri);
		}
	}
}
