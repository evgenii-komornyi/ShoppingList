package com.javaguru.shoppinglist.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {

        String param1 = req.getParameter("param1");

        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.println("<h1>" + "Param 1 = " + param1 + "</h1>");
    }
}
