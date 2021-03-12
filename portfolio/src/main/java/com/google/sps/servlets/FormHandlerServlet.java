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
import java.util.Arrays;
import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
/** Servlet that processes text. */
@WebServlet("/form-handler")
public final class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the input from the form.
    ArrayList<String> arrayInformation =new ArrayList<String>();
    String textName = getParameter(request, "mensaje-input", "Hello there!");
    String textEmail= getParameter(request, "email-input", "example@gmail.com");
    String textSubject= getParameter(request, "subject-input", "Hi!");
    String textMensaje = getParameter(request, "mensaje-input", "Hello there!");
    System.out.println(textName + " " + textEmail + " " + textSubject + " " + textMensaje);
    arrayInformation.add("Name:"  + textName);
    arrayInformation.add("Email:"  + textEmail);
    arrayInformation.add("Subject:"  + textSubject);
    arrayInformation.add("Massage:"  + textMensaje);
    String jsonInformation = new Gson().toJson(arrayInformation);
    /* System.out.println(arrayInformation); */
    System.out.println(jsonInformation);
    
    // Respond with the result.
    /* response.getWriter().println(textName + " " + textEmail + " " + textSubject + " " + textMensaje);
    response.getWriter().println(arrayInformation); */
    response.getWriter().println(jsonInformation);
    
    response.sendRedirect("https://ofernandez-sps-spring21.uc.r.appspot.com/"); 
  }


  static void authImplicit() {
  // If you don't specify credentials when constructing the client, the client library will
  // look for credentials via the environment variable GOOGLE_APPLICATION_CREDENTIALS.
  Storage storage = StorageOptions.getDefaultInstance().getService();

  System.out.println("Buckets:");
  Page<Bucket> buckets = storage.list();
  for (Bucket bucket : buckets.iterateAll()) {
    System.out.println(bucket.toString());
  }
}

  private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }
 
}
