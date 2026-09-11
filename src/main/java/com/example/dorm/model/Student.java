package com.example.dorm.model;

// 学生
public class Student {
    private int id;
    private String sno;      // 学号
    private String name;
    private String gender;
    private int dormId;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getSno() { return sno; }
    public void setSno(String sno) { this.sno = sno; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public int getDormId() { return dormId; }
    public void setDormId(int dormId) { this.dormId = dormId; }
}
