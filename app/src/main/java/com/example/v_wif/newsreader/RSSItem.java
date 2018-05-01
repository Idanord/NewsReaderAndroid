package com.example.v_wif.newsreader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RSSItem {

    private String title = null;
    private String description = null;
    private String link = null;
    private String pubDate = null;

    private SimpleDateFormat dateOutFormat = new SimpleDateFormat("EEE (MMM d)");
    private SimpleDateFormat dateInFormat = new SimpleDateFormat("yyyy-MM-dd");

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public SimpleDateFormat getDateOutFormat() {
        return dateOutFormat;
    }

    public void setDateOutFormat(SimpleDateFormat dateOutFormat) {
        this.dateOutFormat = dateOutFormat;
    }

    public SimpleDateFormat getDateInFormat() {
        return dateInFormat;
    }

    public void setDateInFormat(SimpleDateFormat dateInFormat) {
        this.dateInFormat = dateInFormat;
    }

    public String getPubDateFormatted(){
        try{
            if(pubDate != null){
                Date date = dateInFormat.parse(pubDate);
                String pubDateFormatted = dateOutFormat.format(date);
                return pubDateFormatted;
            }else{
                return "No date in the feed";
            }
        }catch (ParseException e){
            return "No date in the feed";
        }
    }
}
