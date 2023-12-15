package com.example.projecto08.models;

public class copper {
    private final String SERIAL;
    private int quantity,price,total;
    private String month, idUser;

    public copper(String SERIAL, int quantity, int price,  String month, String idUser) {
        this.SERIAL = SERIAL;
        this.quantity = quantity;
        this.price = price;
        this.month = month;
        this.idUser = idUser;
    }

    public String getSERIAL() {
        return SERIAL;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
