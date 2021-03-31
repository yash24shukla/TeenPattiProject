package com.teenpatti.teenpattipremium.api;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Webservice {

    private static final String TAG = Webservice.class.getSimpleName();

    public static String Send_data(HashMap<String, String> valueMap, String url) {
        HttpRequest httpRequest = new HttpRequest();
        String response = "";
        try {
            response = httpRequest.doPost(url, valueMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;

    }

    public static String convertStreamToString(InputStream is)
            throws IOException {

        if (is != null) {
            Writer writer = new StringWriter();
            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is,
                        StandardCharsets.UTF_8));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }
            return writer.toString();
        } else {
            return "";
        }
    }

    public static String Send_message(HashMap<String, String> valueMap,
                                      String url, String filekey, String filepath) {

        HttpRequest httpRequest = new HttpRequest();
        String response = "";

        try {
            response = httpRequest.doPostWithFile(url, filekey, filepath, valueMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;

    }

    public static String Send_messagemultiple(HashMap<String, String> valueMap,
                                              String url, String filekey, String filepath, String videokey, String videopath) {

        HttpRequest httpRequest = new HttpRequest();
        // HashMap<String, String> valueMap = new HashMap<String, String>();
        String response = "";

        try {


            response = httpRequest.doPostWithFilemultiple(url, filekey, filepath, valueMap, videokey, videopath);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;

    }


    // convert inputstream to String
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public static String GET(String url, List<NameValuePair> params) {
        InputStream inputStream = null;
        String result = "";

        URI url_main;
        String login1 = null;

        try {
            if (params != null) {
                String paramString = URLEncodedUtils
                        .format(params, "utf-8");
                url += "?" + paramString;
            }
            login1 = url;
            Log.e("url => ", login1);
            url_main = new URI(login1.replace(" ", "%20"));
            Log.e("my webservice", "" + url_main);
            // create HttpClient


            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
            // convert inputstream to string
            if (inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }


    public static Document getDomElement(String xml) {
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            DocumentBuilder db = dbf.newDocumentBuilder();

            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            doc = db.parse(is);

        } catch (ParserConfigurationException e) {
            Log.e(TAG, "Error: " + e.getMessage());
            return null;
        } catch (SAXException e) {
            Log.e(TAG, "Error: " + e.getMessage());
            return null;
        } catch (IOException e) {
            Log.e(TAG, "Error: " + e.getMessage());
            return null;
        }
        // return DOM
        return doc;
    }

    public static String getValue(Element item, String str) {
        NodeList n = item.getElementsByTagName(str);
        return getElementValue(n.item(0));
    }

    public static final String getElementValue(Node elem) {
        Node child;
        if (elem != null) {
            if (elem.hasChildNodes()) {
                for (child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
                    if (child.getNodeType() == Node.TEXT_NODE) {
                        return child.getNodeValue();
                    }
                }
            }
        }
        return "";
    }
}
