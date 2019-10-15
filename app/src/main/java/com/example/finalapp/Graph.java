package com.example.finalapp;

public class Graph {

    String info ;
    int data;

    public Graph(String info, int data) {
        this.info = info;
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
