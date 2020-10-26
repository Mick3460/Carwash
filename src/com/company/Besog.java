package com.company;
import java.util.ArrayList;
public class Besog {

        //Person
        //private String customerID;
        private int balance;


        public Besog(int balance){
            //this.customerID = costumerID;
            this.balance = balance;
        }
        public Besog() {
        }

       // public String getCostumerID(){
       //     return this.customerID;
       // }

        public int getBalance(){
            return this.balance;
        }

        public String toString(){
            return this.balance + " balancen er";
        }

        int setBalance(int balance) {this.balance = balance;
            return balance;
        }

       // void setCustomerID(String customerID) {this.customerID = customerID;} //bliver ikke brugt

    }
