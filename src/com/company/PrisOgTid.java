package com.company;

public class PrisOgTid {

    //Person
    private String tid;
    private int pris;

    public PrisOgTid(String tid, int pris){
        this.tid = tid;
        this.pris = pris;
    }
    public PrisOgTid() {
    }


    public String toString(){
        return this.tid + "<- Tid... Pris-> " + this.pris;
    }

    public String getTid(){
        return this.tid;
    }

    public int getPris(){
        return this.pris;
    }

    int setPris(int pris) {this.pris = pris;
        return pris;
    }

    void setTid(String tid) {this.tid = tid;} //bliver ikke brugt

}


