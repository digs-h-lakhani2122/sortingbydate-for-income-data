package com.example.staticincome;

public class DateItem extends ListItem {
    private String date;
//    private int total_income;
//
//    public int getTotal_income() {
//        return total_income;
//    }
//
//    public void setTotal_income(int total_income) {
//        this.total_income = total_income;
//    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int getType() {
        return TYPE_DATE;
    }
}
