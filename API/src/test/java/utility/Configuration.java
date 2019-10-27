package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Configuration {

	// the configuration file is stored in the root of the class path as a
	// .properties file
	private static final String CONFIGURATION_FILE = System.getProperty("user.dir")
			+ "/src/test/resources/properties/Config.properties";

	private static final Properties properties = new Properties();

	// use static initializer to read the configuration file when the class is
	// loaded

	static {
		InputStream is = null;
		try {
			is = new FileInputStream(CONFIGURATION_FILE);
			properties.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Map<String, String> getConfiguration() {

		Map<String, String> propertyMap = new HashMap();

		Enumeration keys = properties.propertyNames();
		
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			propertyMap.put(key, properties.getProperty(key));
		}
		return propertyMap;
	}

	

	public static String getConfigurationValue(String key) {
		return properties.getProperty(key);
	}

	// private constructor to prevent initialization
	public Configuration() {
	}

}