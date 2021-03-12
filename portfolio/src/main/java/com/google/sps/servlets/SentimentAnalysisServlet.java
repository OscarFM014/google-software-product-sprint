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

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sentiment")
public class SentimentAnalysisServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String message = request.getParameter("message");
    System.out.println(message);

    Document doc =
        Document.newBuilder().setContent(message).setType(Document.Type.PLAIN_TEXT).build();
    LanguageServiceClient languageService = LanguageServiceClient.create();
    Sentiment sentiment = languageService.analyzeSentiment(doc).getDocumentSentiment();
    float score = sentiment.getScore();
    languageService.close();
    

    // Output the sentiment score as HTML.
    // A real project would probably store the score alongside the content.
   // Output the sentiment score as HTML.
    // A real project would probably store the score alongside the content.
    response.setContentType("text/html;");
    response.getWriter().println("<style>");
    response.getWriter().println("@import url('https://fonts.googleapis.com/css2?family=Mulish&display=swap');");
    response.getWriter().println("html, body{font-family: 'Montserrat', sans-serif; margin: 10px;scroll-behavior: smooth;}");
    response.getWriter().println("h1{color: black;font-weight: normalfont-size: 4em;}");
    response.getWriter().println("p{color: black;font-weight: normal;margin-left: 10%;margin-right: 10%;font-size: 1em;font-weight: normal;line-height: 40px;}");
    response.getWriter().println("p{color: black;font-weight: normal;margin-left: 10%;margin-right: 10%;font-size: 1em;font-weight: normal;line-height: 40px;}");
    response.getWriter().println("button{background-color: rgba(0, 0, 0, 0.1);border: none;color: black;cursor: pointer;font-size: 16px;margin-bottom: 10px;font-family: 'Muli', sans-serif;font-weight: bold;height: 50px;letter-spacing: 1px;}");
    response.getWriter().println("button:hover{background: #ffd900;color: black;cursor: pointer;}");
    response.getWriter().println("a{outline-style: none;text-decoration: none; color:black;}");
   
    response.getWriter().println("</style>");
    response.getWriter().println("<h1>Sentiment Analysis</h1>");
    
    response.getWriter().println("<p>The message is: " + message + "</p>");
    
    response.getWriter().println("<p>Sentiment analysis score is " + score + ", that means that I was " + (score*100) + "% happy when I wrote that text</p>");
    
    response.getWriter().println("<button><a href=\"/\">Back</a></button>");
  }
}
