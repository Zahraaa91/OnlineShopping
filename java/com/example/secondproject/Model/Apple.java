package com.example.secondproject.Model;

public class Apple
{
    private String  name,url,price;

    public Apple()
    {

    }

    public Apple(String name, String url, String price) {

        this.name = name;
        this.url = url;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    }

