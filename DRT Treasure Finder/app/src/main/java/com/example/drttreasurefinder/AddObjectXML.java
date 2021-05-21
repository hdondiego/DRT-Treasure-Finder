package com.example.drttreasurefinder;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;

public class AddObjectXML {
    //private StringReader stringReader;
    private File file;
    private FileInputStream fileInputStream;

    public void AddObject() throws XmlPullParserException, IOException {
        XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
        //xmlPullParserFactory.setNamespaceAware(true);
        XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();

        fileInputStream = new FileInputStream(file);

        xmlPullParser.setInput(fileInputStream, null);

        int eventType = xmlPullParser.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {

        }


    }

    public AddObjectXML(File file) {
        this.file = file;
    }
}
