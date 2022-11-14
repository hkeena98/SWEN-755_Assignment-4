package com.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DemoServlet")
public class DemoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        if(user.equals("username1") && pass.equals("password1")) {
            setSessionRedirect(request, response, "admin.jsp");
        } else if(user.equals("username2") && pass.equals("password2")) {
            setSessionRedirect(request, response, "student.jsp");
        } else if(user.equals("username3") && pass.equals("password3")) {
            setSessionRedirect(request, response, "faculty.jsp");
        } else {
//            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
//            rd.include(request, response);
        }

    }

    protected void setSessionRedirect(HttpServletRequest request, HttpServletResponse response, String page) throws IOException {
        HttpSession session = request.getSession();
        request.setAttribute("isSession", true);
        session.setAttribute("username", request.getParameter("username"));
        response.setContentType("text/html");
        response.sendRedirect(page);
    }
}