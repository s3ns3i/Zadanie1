package com.example.s3ns3i.zadanie1;

import android.os.AsyncTask;
import android.util.Xml;

import com.example.s3ns3i.zadanie1.Parser.XMLParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by s3ns3i on 2015-04-23.
 */
public class ExchangeRateDownloader extends AsyncTask<String, Void, String> {

    public ExchangeRateDownloader(){}

    @Override
    protected String doInBackground(String... urls) {
        try{
            return loadXmlFromNetwork(urls[0]);
        } catch(IOException e){
            return getResources().getString(R.string.connection_error);
        } catch (XmlPullParserException e) {
            return getResources().getString(R.string.xml_error);
        }
    }

//    @Override
//    protected void onPostExecute(String s) {
//        setContentView(R.layout.main);
//        //
//    }

    public void downloadXMLFromNetwork(String urlString) throws  XmlPullParserException, IOException {
        InputStream stream = null;
        XMLParser xmlParser = new XMLParser();
        ArrayList<Object> entries = null;
        String

    }
}
