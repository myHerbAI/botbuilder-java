/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.bot.connector;

import java.io.InputStream;
import java.util.Properties;
import java.util.function.Consumer;

/**
 * Loads configuration properties for bot-connector.
 *
 * Properties are located in an optional connector.properties file
 * in the classpath.
 */
public class ConnectorConfiguration {
    public void process(Consumer<Properties> func) {
        final Properties properties = new Properties();
        try ( InputStream propStream = UserAgent.class.getClassLoader()
            .getResourceAsStream("connector.properties")) {

            properties.load(propStream);
            func.accept(properties);
        } catch (Throwable t) {
            Properties p = new Properties();
            p.setProperty("version", "4.0.0");
            func.accept(p);
        }
    }
}
