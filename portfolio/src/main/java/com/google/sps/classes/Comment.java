package com.google.sps.classes;

import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.time.format.FormatStyle;

// Class representing a Comment
public class Comment {
  private String name;
  private String comment;
  private String date;


  public Comment(String commentIn, String nameIn){
    name = nameIn;
    comment = commentIn;
    date = getCurrentDate();
  }
  public Comment(String commentIn, String nameIn, String dateIn){
    name = nameIn;
    comment = commentIn;
    date = dateIn;
  }
  public void setDateToCurrentTime(){
    date = getCurrentDate();
  }
  public String getDate() {
    return date;
  }

  private String getCurrentDate() {
    DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);  
    LocalDateTime now = LocalDateTime.now();  
    String output = dtf.format(now);
    return output;
  }
}