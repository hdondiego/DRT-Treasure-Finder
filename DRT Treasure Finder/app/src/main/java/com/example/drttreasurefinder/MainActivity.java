package com.example.drttreasurefinder;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button startButton, recordsButton, instrButton;
    private TextView txtTitle, txtXValue, txtYValue, txtFileExists, txtCreated;
    private float screenX, screenY;
    private int buttonWidth;
    private String strXValue, strYValue;

    private Context context;
    private File dataFile;
    private FileOutputStream fileOutputStream;

    private FileInputStream fileInputStream;
    private StringReader stringReader;
    private ObjectOutputStream objectOutputStream;
    private XmlPullParserFactory xmlPullParserFactory;
    private XmlPullParser xmlPullParser;

    private ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        XmlSerializer xmlSerializer = Xml.newSerializer();
        StringWriter stringWriter = new StringWriter();

        users = new ArrayList<>();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenX = displayMetrics.widthPixels;
        screenY = displayMetrics.heightPixels;

        startButton = findViewById(R.id.startButton);
        recordsButton = findViewById(R.id.recordsButton);
        instrButton = findViewById(R.id.instrButton);
        txtTitle = findViewById(R.id.txtTitle);
        txtXValue = findViewById(R.id.txtXValue);
        txtYValue = findViewById(R.id.txtYValue);
        txtFileExists = findViewById(R.id.txtFileExists);
        txtCreated = findViewById(R.id.txtCreated);

        String yesExist = "File Existed";
        String noExist = "File Didn't Exist";
        String nowItDoes = "The File is Now Created";
        String xmlContent;

        boolean existence = false;

        dataFile = new File(context.getFilesDir(), "appdata.xml");

        while (!existence) {
            existence = dataFile.exists();

            if (existence) {
                txtFileExists.setText(yesExist);
            } else {
                txtFileExists.setText(noExist);

                try {
                    dataFile.createNewFile();
                    xmlSerializer.setOutput(stringWriter);
                    xmlSerializer.startDocument("UTF-8",true);
                    xmlSerializer.startTag("", "data");
                    objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    xmlSerializer.endTag("","data");
                    xmlSerializer.endDocument();
                    xmlContent = stringWriter.toString();
                    XMLCreator(dataFile, xmlContent);
                } catch (IOException io) {
                    io.printStackTrace();
                }

                txtCreated.setText(nowItDoes);
            }
        }

        stringReader = new StringReader("<data></data>");

        AddObjectXML addObjectXML = new AddObjectXML(stringReader);

        /*try {
            AddUsersXML(dataFile);
        } catch () {
            fnf.printStackTrace();
        }*/

        strXValue = Float.toString(screenX);
        strYValue = Float.toString(screenY);
        txtXValue.setText(strXValue);
        txtYValue.setText(strYValue);

        buttonWidth = ((int)screenX * 3) / 4;

        startButton.setWidth(buttonWidth);
        startButton.setTextSize((float)(buttonWidth/27));

        recordsButton.setWidth(buttonWidth);
        recordsButton.setTextSize((float)(buttonWidth/27));

        instrButton.setWidth(buttonWidth);
        instrButton.setTextSize((float)(buttonWidth/27));

        txtTitle.setTextSize((float)(buttonWidth/21));
        txtTitle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        startButton.setOnClickListener(new StartClickListener());
        recordsButton.setOnClickListener(new RecordsClickListener());
        instrButton.setOnClickListener(new InstrClickListener());

    }



    public class StartClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent startIntent = new Intent(MainActivity.this, StartActivity.class);
            startIntent.putExtra("screenX", screenX);
            startIntent.putExtra("screenY", screenY);
            startActivity(startIntent);
        }
    }

    public class RecordsClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent recordsIntent = new Intent(MainActivity.this, RecordsActivity.class);
            recordsIntent.putExtra("screenX", screenX);
            recordsIntent.putExtra("screenY", screenY);
            startActivity(recordsIntent);
        }
    }

    public class InstrClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent instrIntent = new Intent(MainActivity.this, InstructionsActivity.class);
            instrIntent.putExtra("screenX", screenX);
            instrIntent.putExtra("screenY", screenY);
            startActivity(instrIntent);
        }
    }

    public void XMLCreator(File file, String xmlContent) throws IOException{
        fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(xmlContent.getBytes(), 0, xmlContent.length());
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    public void AddUsersXML(File file) throws XmlPullParserException, IOException {
        /*fileInputStream = new FileInputStream(file);
        xmlPullParser.setInput(stringReader);*/

    }
}
