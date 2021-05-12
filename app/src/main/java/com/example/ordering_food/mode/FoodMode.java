package com.example.ordering_food.mode;

import java.io.Serializable;

public class FoodMode implements Serializable {
    String id ;
    String title , name , image , price , count;
    int image2;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getImage2() {
        return image2;
    }

    public void setImage2(int image2) {
        this.image2 = image2;
    }

    public FoodMode(int image2, String title, String name, String price) {
        this.image2 = image2;
        this.title = title;
        this.name = name;
        this.price = price;
    }
    public FoodMode(String id, String title, String name, String image, String price, String count) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.image = image;
        this.price = price;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public FoodMode(){}

    public FoodMode(String id, String title, String name, String image, String price) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public FoodMode(String image, String name, String title, String price) {
        this.title = title;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
