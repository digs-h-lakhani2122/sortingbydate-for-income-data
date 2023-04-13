package com.example.staticincome;

public class PojoOfJsonArray
{
    String date;
    String account;
    String category;
    String note;
    String amount;

    public PojoOfJsonArray(String date, String account, String category, String note, String amount) {
        this.date = date;
        this.account = account;
        this.category = category;
        this.note = note;
        this.amount = amount;
    }


    public String getDate() {
        return date;
    }

    public String getAccount() {
        return account;
    }


    public String getCategory() {
        return category;
    }


    public String getNote() {
        return note;
    }


    public String getAmount() {
        return amount;
    }

}
