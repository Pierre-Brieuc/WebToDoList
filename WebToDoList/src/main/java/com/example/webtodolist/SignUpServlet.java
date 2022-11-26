package com.example.webtodolist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "signUpServlet", value = "/sign-up-servlet")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newName = req.getParameter("name");
        String newPassword = req.getParameter("password");
        String newRole = req.getParameter("role");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1> You are now registered </h1>");
        out.println("</body></html>");

        if (newRole == "student") {
            Student newStudent = new Student(newName,newPassword);
        } else if (newRole == "instructor") {
            Instructor newInstructor = new Instructor(newName, newPassword);
        }
    }

    public void destroy() {
    }
}
