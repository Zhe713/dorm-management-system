package com.example.dorm.servlet;

import com.example.dorm.dao.DormDAO;
import com.example.dorm.model.Dorm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DormServlet extends HttpServlet {

    private DormDAO dormDAO = new DormDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        if ("list".equals(action)) {
            List<Dorm> list = dormDAO.findAll();
            req.setAttribute("list", list);
            req.getRequestDispatcher("dorm_list.jsp").forward(req, resp);
        } else if ("assign".equals(action)) {
            int studentId = Integer.parseInt(req.getParameter("studentId"));
            int dormId = Integer.parseInt(req.getParameter("dormId"));
            dormDAO.assignStudent(studentId, dormId);
            resp.sendRedirect("student?action=list");
        } else if ("checkout".equals(action)) {
            int studentId = Integer.parseInt(req.getParameter("studentId"));
            int dormId = Integer.parseInt(req.getParameter("dormId"));
            dormDAO.checkoutStudent(studentId, dormId);
            resp.sendRedirect("student?action=list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Dorm d = new Dorm();
        d.setBuilding(req.getParameter("building"));
        d.setRoomNo(req.getParameter("roomNo"));
        d.setCapacity(Integer.parseInt(req.getParameter("capacity")));
        dormDAO.add(d);
        resp.sendRedirect("dorm?action=list");
    }
}
