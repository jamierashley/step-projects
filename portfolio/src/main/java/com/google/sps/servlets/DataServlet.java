// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime; 
import java.util.Date;
import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {

  private ArrayList<String> statements;

  public void init(){
      statements = new ArrayList<>();
  }
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
    String json = convertToJsonUsingGson(statements);

    response.setContentType("application/json;");
    response.getWriter().println(json);

    Query query = new Query("Task").addSort("timestamp", SortDirection.DESCENDING);
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastore.prepare(query);

    ArrayList<String> stringOfComments = new ArrayList<>();  
    for (Entity entity : results.asIterable()) {
         String title = (String) entity.getProperty("title");
       //  String time = (String) entity.getProperty("timestamp");
         stringOfComments.add(title);
    }
  }

  @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String name = getChoice(request);
    String comment = getChoice2(request);

    Date dateNow = new Date();
    long timeNow = dateNow.getTime();
    int timeNowToUse = (int) timeNow;
    String time = "6:00";


    statements.add(comment + ", " + name + ", " + time);

    Entity taskEntity = new Entity("Task");
    taskEntity.setProperty("title", comment + ", " + name);
    taskEntity.setProperty("timestamp", timeNowToUse);

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(taskEntity);

    response.sendRedirect("/index.html");

  }


  private String convertToJsonUsingGson(ArrayList state) {
    Gson gson = new Gson();
    String json = gson.toJson(state);
    return json;
  }

  private String getChoice(HttpServletRequest request) {
    String answerString = request.getParameter("name");
    return answerString;
  }
  private String getChoice2(HttpServletRequest request) {
    String answerString = request.getParameter("comment");
    return answerString;
  }
}

