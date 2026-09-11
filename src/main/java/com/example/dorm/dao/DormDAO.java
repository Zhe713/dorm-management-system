package com.example.dorm.dao;

import com.example.dorm.model.Dorm;
import com.example.dorm.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DormDAO {

    public List<Dorm> findAll() {
        List<Dorm> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement("SELECT * FROM dorm");
            rs = ps.executeQuery();
            while (rs.next()) {
                Dorm d = new Dorm();
                d.setId(rs.getInt("id"));
                d.setBuilding(rs.getString("building"));
                d.setRoomNo(rs.getString("room_no"));
                d.setCapacity(rs.getInt("capacity"));
                d.setOccupied(rs.getInt("occupied"));
                list.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return list;
    }

    public void add(Dorm d) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(
                "INSERT INTO dorm(building, room_no, capacity) VALUES (?, ?, ?)");
            ps.setString(1, d.getBuilding());
            ps.setString(2, d.getRoomNo());
            ps.setInt(3, d.getCapacity());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }
    }

    // 分配宿舍：把学生 dorm_id 改掉，同时宿舍 occupied + 1
    // 用事务保证两步要么都成要么都不成
    public void assignStudent(int studentId, int dormId) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement ps1 = conn.prepareStatement(
                "UPDATE student SET dorm_id = ? WHERE id = ?");
            ps1.setInt(1, dormId);
            ps1.setInt(2, studentId);
            ps1.executeUpdate();

            PreparedStatement ps2 = conn.prepareStatement(
                "UPDATE dorm SET occupied = occupied + 1 WHERE id = ?");
            ps2.setInt(1, dormId);
            ps2.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            try { if (conn != null) conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, null, null);
        }
    }

    // 退宿：学生 dorm_id 置空，宿舍 occupied - 1
    public void checkoutStudent(int studentId, int dormId) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement ps1 = conn.prepareStatement(
                "UPDATE student SET dorm_id = NULL WHERE id = ?");
            ps1.setInt(1, studentId);
            ps1.executeUpdate();

            PreparedStatement ps2 = conn.prepareStatement(
                "UPDATE dorm SET occupied = occupied - 1 WHERE id = ?");
            ps2.setInt(1, dormId);
            ps2.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            try { if (conn != null) conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, null, null);
        }
    }
}
