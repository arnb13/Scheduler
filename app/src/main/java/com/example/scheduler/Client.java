package com.example.scheduler;

public class Client {
    public int id;
    public String name;
    public String address;
    public String company;
    public String designation;

    public Client () {

    }

    public Client (int i, String n, String a, String c, String d) {
        id = i;
        name = n;
        address = a;
        company = c;
        designation = d;
    }

    public void setId (int i) {
        id = i;
    }

    public void setName (String n) {
        name = n;
    }

    public void setAddress (String a) {
        address = a;
    }

    public void setCompany (String c) {
        company = c;
    }

    public void setDesignation (String d) {
        designation = d;
    }


    public int getId () {
        return id;
    }

    public String getName () {
        return name;
    }

    public String getAddress () {
        return address;
    }

    public String getCompany () {
        return company;
    }

    public String getDesignation () {
        return designation;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", company='" + company + '\'' +
                ", designation='" + designation + '\'' +
                '}';
    }
}