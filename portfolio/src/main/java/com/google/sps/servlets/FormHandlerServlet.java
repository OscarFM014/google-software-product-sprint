package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the value entered in the form.
    String nameValue = request.getParameter("name-value");

    // Write the value to the response so the user can see it.
    response.getWriter().write(nameValue);
    /* response.sendRedirect("https://ofernandez-sps-spring21.uc.r.appspot.com/#home"); */
  }
}