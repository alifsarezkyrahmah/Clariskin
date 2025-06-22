package com.example.clariskin.Model;

public class UserRoutine {
    private int id;
    private int productId;
    private String timeOfDay;
    private String note;

    public UserRoutine(int id, int productId, String timeOfDay, String note) {
        this.id = id;
        this.productId = productId;
        this.timeOfDay = timeOfDay;
        this.note = note;
    }

    public UserRoutine() {}

    // Getter dan Setter ...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
