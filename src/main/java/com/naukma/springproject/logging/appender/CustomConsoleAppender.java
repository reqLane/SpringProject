package com.naukma.springproject.logging.appender;

import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;

@Plugin(name = "CustomConsoleAppender",
        category = Core.CATEGORY_NAME,
        elementType = Appender.ELEMENT_TYPE)
public class CustomConsoleAppender extends AbstractAppender {

    public CustomConsoleAppender(String name, Filter filter, AbstractStringLayout layout) {
        super(name, filter, layout);
    }

    @PluginFactory
    public static CustomConsoleAppender createAppender(
            @PluginAttribute("name") String name,
            @PluginElement("Filter") Filter filter,
            @PluginElement("Layout") AbstractStringLayout layout) {
        return new CustomConsoleAppender(name, filter, layout);
    }

    @Override
    public void append(LogEvent event) {
        String serialized = ((AbstractStringLayout)getLayout()).toSerializable(event);
        System.out.println(serialized);
    }
}
