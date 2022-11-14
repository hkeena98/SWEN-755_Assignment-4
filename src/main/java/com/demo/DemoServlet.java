package com.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
            setSessionRedirect(request, response, "Admin");
        } else if(user.equals("username2") && pass.equals("password2")) {
            setSessionRedirect(request, response, "Student");
        } else if(user.equals("username3") && pass.equals("password3")) {
            setSessionRedirect(request, response, "Faculty");
        } else {
            PrintWriter out= response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
        }

    }

    protected void setSessionRedirect(HttpServletRequest request, HttpServletResponse response,
                                      String role) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute("profileRole", role);
        session.setMaxInactiveInterval(50);
        request.setAttribute("isSession", true);
        session.setAttribute("username", request.getParameter("username"));
        response.setContentType("text/html");
        response.sendRedirect("profile.jsp");
    }
}