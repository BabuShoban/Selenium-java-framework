package com.automation.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

	public String propertyFile;

	public PropertyLoader(String propertyFile)
	{
		this.propertyFile = propertyFile;
	}

	public Properties load()
	{
		InputStream propertyStream = Thread.class.getResourceAsStream(propertyFile);
		Properties properties = new Properties();
		try {
			properties.load(propertyStream);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}

		return properties;
	}
}

