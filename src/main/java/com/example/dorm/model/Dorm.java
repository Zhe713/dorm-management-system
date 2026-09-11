package com.example.dorm.model;

// 宿舍
public class Dorm {
    private int id;
    private String building;
    private String roomNo;
    private int capacity;   // 床位
    private int occupied;   // 已住

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getBuilding() { return building; }
    public void setBuilding(String building) { this.building = building; }

    public String getRoomNo() { return roomNo; }
    public void setRoomNo(String roomNo) { this.roomNo = roomNo; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public int getOccupied() { return occupied; }
    public void setOccupied(int occupied) { this.occupied = occupied; }
}
