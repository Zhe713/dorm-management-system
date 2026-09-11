package com.example.dorm.servlet;

import com.example.dorm.model.Student;
import com.example.dorm.dao.StudentDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StudentServlet extends HttpServlet {

    private StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
                List<Student> list = studentDAO.findAll();
                req.setAttribute("list", list);
                req.getRequestDispatcher("student_list.jsp").forward(req, resp);
                break;
            case "delete":
                int id = Integer.parseInt(req.getParameter("id"));
                studentDAO.delete(id);
                resp.sendRedirect("student?action=list");
                break;
            default:
                resp.sendRedirect("student?action=list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Student s = new Student();
        s.setSno(req.getParameter("sno"));
        s.setName(req.getParameter("name"));
        s.setGender(req.getParameter("gender"));
        studentDAO.add(s);
        resp.sendRedirect("student?action=list");
    }
}
