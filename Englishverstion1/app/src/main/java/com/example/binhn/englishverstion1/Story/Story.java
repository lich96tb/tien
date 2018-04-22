package com.example.binhn.englishverstion1.Story;

/**
 * Created by user on 30/12/2017.
 */

public class Story {
    int Id;
    String Tentruyen, Noidungtruyen;

    public Story(){

    }

    public Story(int id, String tentruyen, String noidungtruyen) {
        this.Id = id;
        Tentruyen = tentruyen;
        Noidungtruyen = noidungtruyen;
    }

    public int getId() {return Id;}
    public void setId (int id) {this.Id = id;}

    public String getTentruyen() {return Tentruyen;}
    public void setTentruyen(String tentruyen) {Tentruyen = tentruyen;}

    public String getNoidungtruyen() {return Noidungtruyen;}
    public void setNoidungtruyen(String noidungtruyen) {Noidungtruyen = noidungtruyen;}
}
