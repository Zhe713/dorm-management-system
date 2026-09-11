package com.example.dorm.dao;

import com.example.dorm.model.Student;
import com.example.dorm.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // 查所有学生
    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement("SELECT * FROM student");
            rs = ps.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setSno(rs.getString("sno"));
                s.setName(rs.getString("name"));
                s.setGender(rs.getString("gender"));
                s.setDormId(rs.getInt("dorm_id"));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return list;
    }

    // 添加学生
    public void add(Student s) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(
                "INSERT INTO student(sno, name, gender) VALUES (?, ?, ?)");
            ps.setString(1, s.getSno());
            ps.setString(2, s.getName());
            ps.setString(3, s.getGender());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }
    }

    // 删除学生
    public void delete(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement("DELETE FROM student WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }
    }

    // 登录校验（简单用 admin 表，这里放个学生查询也无妨）
    public Student findBySno(String sno) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement("SELECT * FROM student WHERE sno = ?");
            ps.setString(1, sno);
            rs = ps.executeQuery();
            if (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setSno(rs.getString("sno"));
                s.setName(rs.getString("name"));
                s.setGender(rs.getString("gender"));
                s.setDormId(rs.getInt("dorm_id"));
                return s;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return null;
    }
}
