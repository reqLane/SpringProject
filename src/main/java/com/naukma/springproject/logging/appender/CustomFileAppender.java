package com.naukma.springproject.logging.appender;

import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Plugin(name = "CustomFileAppender",
        category = Core.CATEGORY_NAME,
        elementType = Appender.ELEMENT_TYPE)
public class CustomFileAppender extends AbstractAppender {
    private final File file;

    public CustomFileAppender(String name, Filter filter, AbstractStringLayout layout, String fileName) {
        super(name, filter, layout);
        this.file = new File(fileName);
    }

    @PluginFactory
    public static CustomFileAppender createAppender(
            @PluginAttribute("name") String name,
            @PluginElement("Filter") Filter filter,
            @PluginElement("Layout") AbstractStringLayout layout,
            @PluginAttribute("fileName") String fileName) {
        return new CustomFileAppender(name, filter, layout, fileName);
    }

    @Override
    public void append(LogEvent event) {
        String serialized = ((AbstractStringLayout)getLayout()).toSerializable(event);
        try {
            file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(serialized);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
