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


  public Comment(String comment, String name){
    this.name = name;
    this.comment = comment;
    date = getCurrentDate();
  }
  public Comment(String comment, String name, String date){
    this.name = name;
    this.comment = comment;
    this.date = date;
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