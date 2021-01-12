package com.codegym.model;

public class Product {
    private int id;
    private String name;
    private String typeOfProduct;
    private String image;
    

    public Product() {
    }

    public Product(int id, String name, String typeOfProduct, String image) {
        this.id = id;
        this.name = name;
        this.typeOfProduct = typeOfProduct;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeOfProduct() {
        return typeOfProduct;
    }

    public void setTypeOfProduct(String typeOfProduct) {
        this.typeOfProduct = typeOfProduct;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
