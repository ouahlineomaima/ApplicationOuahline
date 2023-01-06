package com.example.applicationouahline.business;

public class Item {
    private int id;
    private String itemName;
    private String itemColor;
    private int itemQuantity;
    private int itemSize;
    private String dateItemAdded;

    public Item() {
    }

    public Item(int id, String itemName, String itemColor, int itemQuantity, int itemSize, String dateItemAdded) {
        this.id = id;
        this.itemName = itemName;
        this.itemColor = itemColor;
        this.itemQuantity = itemQuantity;
        this.itemSize = itemSize;
        this.dateItemAdded = dateItemAdded;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemColor(String itemColor) {
        this.itemColor = itemColor;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public void setItemSize(int itemSize) {
        this.itemSize = itemSize;
    }

    public void setDateItemAdded(String dateItemAdded) {
        this.dateItemAdded = dateItemAdded;
    }

    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemColor() {
        return itemColor;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public int getItemSize() {
        return itemSize;
    }

    public String getDateItemAdded() {
        return dateItemAdded;
    }


}