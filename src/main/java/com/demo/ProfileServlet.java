package com.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        request.getRequestDispatcher("index.jsp").include(request, response);

        HttpSession session=request.getSession(false);
        if(session!=null){
            session.setMaxInactiveInterval(session.getMaxInactiveInterval() + 30);
            out.print("Session inactive timeout is now " + session.getMaxInactiveInterval() + " seconds" );
        }
        else{
            out.print("Please login first");
            request.getRequestDispatcher("index.jsp").include(request, response);
        }
        out.close();
    }
}
