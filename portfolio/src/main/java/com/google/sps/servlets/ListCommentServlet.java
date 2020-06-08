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
import com.google.sps.classes.Comment;
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

@WebServlet("/display-comments")
public class ListCommentServlet extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

    Query query = new Query("Comment").addSort("date", SortDirection.DESCENDING);
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastore.prepare(query);

    ArrayList<Comment> listOfComments = new ArrayList<>();  
    for (Entity entity : results.asIterable()) {
        String com = (String) entity.getProperty("comment");
        String person = (String) entity.getProperty("name");
        String date = (String) entity.getProperty("date");
        
        Comment commentAt = new Comment(com, person, date);
        listOfComments.add(commentAt);
    }

    if(!listOfComments.isEmpty()){
        response.setContentType("application/json;");
        response.getWriter().println(convertToJsonUsingGson(listOfComments));
    }else{
        response.getWriter().println("There are no comments to show.");
    }

  }
   private String convertToJsonUsingGson(ArrayList listOfComments) {
    Gson gson = new Gson();
    String json = gson.toJson(listOfComments);
    return json;
  }

  private String getChoice(HttpServletRequest request) {
    // Get the input from the form.
    String answerString = request.getParameter("text-input");
    return answerString;
  }
}