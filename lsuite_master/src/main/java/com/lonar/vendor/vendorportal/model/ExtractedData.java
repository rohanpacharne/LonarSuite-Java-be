package com.lonar.vendor.vendorportal.model;

public class ExtractedData {
    private String billNumber;
    private String gstNumber;
    private String date;
    private String totalAmount;

    // Getters and Setters
    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "ExtractedData{" +
                "billNumber='" + billNumber + '\'' +
                ", gstNumber='" + gstNumber + '\'' +
                ", date='" + date + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                '}';
    }
}

