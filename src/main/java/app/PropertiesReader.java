/*
 * Copyright (c) 2020 Self-Order Kiosk
 */
package app;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Accessing Maven Properties in Java
 * https://www.baeldung.com/java-accessing-maven-properties
 */
public class PropertiesReader {

  private final Properties properties;

  public PropertiesReader(String propertyFileName) throws IOException {
    InputStream is = getClass().getClassLoader().getResourceAsStream(propertyFileName);
    this.properties = new Properties();
    this.properties.load(is);
  }

  public String getProperty(String propertyName) {
    return this.properties.getProperty(propertyName);
  }

}
