package com.naukma.springproject.logging.appender;

import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

import java.time.Instant;

@Plugin(
        name = "CustomConsoleAppender",
        category = Core.CATEGORY_NAME,
        elementType = Appender.ELEMENT_TYPE)
public class CustomConsoleAppender extends AbstractAppender {

    public CustomConsoleAppender(String name, Filter filter) {
        super(name, filter, null);
    }

    @PluginFactory
    public static CustomConsoleAppender createAppender(
            @PluginAttribute("name") String name,
            @PluginElement("Filter") Filter filter) {
        return new CustomConsoleAppender(name, filter);
    }

    @Override
    public void append(LogEvent event) {
        System.out.println("Hello from here - " + event.getMessage());
    }
}
