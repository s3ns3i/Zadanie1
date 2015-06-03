package com.example.s3ns3i.zadanie1.Parser;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by s3ns3i on 2015-05-10.
 */
public class XMLParser {

    private static final String ns = null;

    /**
     * The only public method available in XMLParser class.
     * @param in InputStream object that holds downloaded XML document.
     * @return ArrayList object with entries. (still needs verification)
     * @throws XmlPullParserException
     * @throws IOException
     */
    public List<Object> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readExchangeTable(parser);
        } finally {
            in.close();
        }
    }

    /**
     * Private method used to read all XML feed in document.
     * @param parser Parser that holds XML document obtained from InputStream object.
     * @return ArrayList object with entries.
     * @throws XmlPullParserException
     * @throws IOException
     */
    private List<Object> readExchangeTable(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<Object> entries = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, ns, "tabela_kursow");
        while (parser.next() != XmlPullParser.START_TAG){
        }

        // TODO Make reading of the &lt;typ&gt; and &lt;uid&gt; attributes.

        String name = parser.getName();
        // Starts by looking for the tabela_kursow tag
        switch (name) {
            case "numer_tabeli":
                entries.add(readTableNumber(parser));
                break;
            case "data_publikacji":
                entries.add((readPublicationDate(parser)));
                break;
            case "pozycja":
                entries.add(readPosition(parser));
                break;
            default:
                skip(parser);
                break;
        }
        return entries;
    }

    /**
     * Private method used to read &lt;numer_tabeli&gt; tag.
     * @param parser Parser that holds XML document obtained from InputStream object.
     * @return String object with text from tag.
     * @throws IOException
     * @throws XmlPullParserException
     */
    private String readTableNumber(XmlPullParser parser) throws IOException, XmlPullParserException{
        parser.require(XmlPullParser.START_TAG, ns, "numer_tabeli");
        String tableName = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "numer_tabeli");
        return tableName;
    }

    /**
     * Private method used to read &lt;data_publikacji&gt; tag.
     * @param parser Parser that holds XML document obtained from InputStream object.
     * @return String object with text from tag.
     * @throws IOException
     * @throws XmlPullParserException
     */
    private String readPublicationDate(XmlPullParser parser) throws IOException, XmlPullParserException{
        parser.require(XmlPullParser.START_TAG, ns, "data_publikacji");
        String publicationDate = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "data_publikacji");
        return publicationDate;
    }

    /**
     * Inner class used to read &lt;pozycja&gt; tag, that has nested tags:
     * &lt;nazwa_waluty&gt;
     * &lt;przelicznik&gt;
     * &lt;kod_waluty&gt;
     * &lt;kurs_sredni&gt;
     */
    public static class Position {
        public final String currencyName;
        public final String conversion;
        public final String currencyCode;
        public final String averageExchangeRate;

        /**
         * Constructor that takes four arguments:
         * @param currencyName
         * @param conversion
         * @param currencyCode
         * @param averageExchangeRate
         */
        private Position(String currencyName, String conversion, String currencyCode, String averageExchangeRate) {
            this.currencyName = currencyName;
            this.conversion = conversion;
            this.currencyCode = currencyCode;
            this.averageExchangeRate = averageExchangeRate;
        }
    }

    /**
     * Private method used to read &lt;pozycja&gt; tag and get the four nested tags:
     * &lt;nazwa_waluty&gt;
     * &lt;przelicznik&gt;
     * &lt;kod_waluty&gt;
     * &lt;kurs_sredni&gt;
     * @param parser Parser that holds XML document obtained from InputStream object.
     * @return Position object with four tags: &lt;nazwa_waluty&gt;, &lt;przelicznik&gt;, &lt;kod_waluty&gt;, &lt;kurs_sredni&gt;
     * @throws XmlPullParserException
     * @throws IOException
     */
    private Position readPosition(XmlPullParser parser) throws XmlPullParserException, IOException{
        parser.require(XmlPullParser.START_TAG, ns, "pozycja");
        String currencyName = null;
        String conversion = null;
        String currencyCode = null;
        String averageExchangeRate = null;

        while(parser.next() != XmlPullParser.END_TAG){
            if(parser.getEventType() != XmlPullParser.START_TAG){
                continue;
            }
            String name = parser.getName();
            switch (name) {
                case "nazwa_waluty":
                    currencyName = readCurrencyName(parser);
                    break;
                case "przelicznik":
                    conversion = readConversion(parser);
                    break;
                case "kod_waluty":
                    currencyCode = readCurrencyCode(parser);
                    break;
                case "kurs_sredni":
                    averageExchangeRate = readAverageExchangeRate(parser);
                    break;
                default:
                    skip(parser);
                    break;
            }
        }
        return new Position(currencyName, conversion, currencyCode, averageExchangeRate);
    }

    /**
     * Private method used to read &lt;nazwa_waluty&gt; tag.
     * @param parser Parser that holds XML document obtained from InputStream object.
     * @return String object with text from tag.
     * @throws IOException
     * @throws XmlPullParserException
     */
    private String readCurrencyName(XmlPullParser parser) throws IOException, XmlPullParserException{
        parser.require(XmlPullParser.START_TAG, ns, "nazwa_waluty");
        String currencyName = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "nazwa_waluty");
        return currencyName;
    }

    /**
     * Private method used to read &lt;przelicznik&gt; tag.
     * @param parser Parser that holds XML document obtained from InputStream object.
     * @return String object with text from tag.
     * @throws IOException
     * @throws XmlPullParserException
     */
    private String readConversion(XmlPullParser parser) throws IOException, XmlPullParserException{
        parser.require(XmlPullParser.START_TAG, ns, "przelicznik");
        String conversion = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "przelicznik");
        return conversion;
    }

    /**
     * Private method used to read &lt;kod_waluty&gt; tag.
     * @param parser Parser that holds XML document obtained from InputStream object.
     * @return String object with text from tag.
     * @throws IOException
     * @throws XmlPullParserException
     */
    private String readCurrencyCode(XmlPullParser parser) throws IOException, XmlPullParserException{
        parser.require(XmlPullParser.START_TAG, ns, "kod_waluty");
        String currencyCode = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "kod_waluty");
        return currencyCode;
    }

    /**
     * Private method used to read &lt;kurs_sredni&gt; tag.
     * @param parser Parser that holds XML document obtained from InputStream object.
     * @return String object with text from tag.
     * @throws IOException
     * @throws XmlPullParserException
     */
    private String readAverageExchangeRate(XmlPullParser parser) throws IOException, XmlPullParserException{
        parser.require(XmlPullParser.START_TAG, ns, "kurs_sredni");
        String averageExchangeRate = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "kurs_sredni");
        return averageExchangeRate;
    }

    /**
     * Private method used to read text from the tag.
     * @param parser Parser that holds XML document obtained from InputStream object.
     * @return String object with text from tag.
     * @throws IOException
     * @throws XmlPullParserException
     */
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException{
        String result = "";
        if(parser.next() == XmlPullParser.TEXT){
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    /**
     * Private method use to skip tags we are not interested in.
     * @param parser Parser that holds XML document obtained from InputStream object.
     * @throws XmlPullParserException
     * @throws IOException
     */
    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
