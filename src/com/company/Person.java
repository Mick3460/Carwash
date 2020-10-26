package com.company;

public class Person {

    //Person
    private String customerID;
    private int balance;

    Person(String costumerID, int balance){
        this.customerID = costumerID;
        this.balance = balance;
    }
    public Person() {
    }

    public String getCostumerID(){
        return this.customerID;
    }

    public int getBalance(){
        return this.balance;
    }

    int setBalance(int balance) {this.balance = balance;
        return balance;
    }

    void setCustomerID(String customerID) {this.customerID = customerID;} //bliver ikke brugt

}
