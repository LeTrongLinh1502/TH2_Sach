package com.example.thuchanh2.model;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String ten,tacGia,ngayXB,nhaXB,gia;

    public Book() {
    }

    public Book(String ten, String tacGia,String ngayXB, String nhaXB,  String gia) {
        this.ten = ten;
        this.tacGia = tacGia;
        this.nhaXB = nhaXB;
        this.ngayXB = ngayXB;
        this.gia = gia;
    }

    public Book(int id, String ten, String tacGia,String ngayXB, String nhaXB,  String gia) {
        this.id = id;
        this.ten = ten;
        this.tacGia = tacGia;
        this.nhaXB = nhaXB;
        this.ngayXB = ngayXB;
        this.gia = gia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNhaXB() {
        return nhaXB;
    }

    public void setNhaXB(String nhaXB) {
        this.nhaXB = nhaXB;
    }

    public String getNgayXB() {
        return ngayXB;
    }

    public void setNgayXB(String ngayXB) {
        this.ngayXB = ngayXB;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
}
