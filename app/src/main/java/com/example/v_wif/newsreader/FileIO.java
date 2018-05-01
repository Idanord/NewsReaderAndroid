package com.example.v_wif.newsreader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class FileIO {

    //constants for the url and file name
    private final String URL_STRING = "http://rss.cnn.com/rss/cnn_tech.rss";
    private final String FILENAME = "news_feed.xml";
    private Context context = null;

    public FileIO(Context context){
        this.context = context;
    }//end FileIO()

    public void downloadFile(){

        //get NetworkInfo object
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        //if network is connected, download file
        if(networkInfo != null && networkInfo.isConnected()) {
            try {
                //get the URL
                URL url = new URL(URL_STRING);

                //open the input stream
                InputStream in = url.openStream();

                //get the output stream
                FileOutputStream out = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);

                //read input and write output
                byte[] buffer = new byte[1024];
                int bytesRead = in.read(buffer);
                while (bytesRead != -1) {
                    out.write(buffer, 0, bytesRead);
                    bytesRead = in.read(buffer);
                }
                //close resources
                out.close();
                in.close();
            } catch (IOException e) {
                Log.e("News Reader", e.toString());
            }//end try & catch

        }//end if(networkInfo != null && network.isConnected)

    }//end downloadFile()

    public RSSFeed readFile(){

        try {
            //get the XML reader
            SAXParserFactory factory = SAXParserFactory.newInstance().newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader xmlReader = parser.getXMLReader();

            //set the content handler
            RSSFeedHandler theRSSHandler = new RSSFeedHandler();
            xmlReader.setContentHandler((ContentHandler) theRSSHandler);

            //read the file form internal storage
            FileInputStream in = context.openFileInput(FILENAME);

            //parse the data
            InputSource is = new InputSource(in);
            xmlReader.parse(is);

            RSSFeed feed = RSSFeedHandler.getFeed();
            return feed;
        }catch (Exception e){
            Log.e("News Reader", e.toString());
            return null;
        }
    }//end RSSFeed readFile()

}//End FileIO.java
