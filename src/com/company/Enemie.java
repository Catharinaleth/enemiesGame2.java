package com.company;

public class Enemie {
    private int x; // definerer x og y, som private, så de kun kan tilgåes i denne klasse
    private int y;
    boolean collision;

    public Enemie (int x, int y) { // opretter konstruktør som returnerer objektet Enemie
        this.x = x; // henter x og y fra denne klasse
        this.y = y;
    }

    public int[] getCoordinate () {
        int[] coordinate = new int [2]; // opretter et array for hhv. x og y aksen
        coordinate[0] = this.y; // plads nr. 0 i listen er y
        coordinate[1] = this.x; // plads nr. 1 i listen er x
        return coordinate; // skal gives os kordinater med x og ys værdier
    }

    public void moveEnemiesToward(Hero hero){
        int[] coordinate = hero.getCoordinate(); //laver en ny liste der består af heros koordinater

        if(this.y < coordinate[0]){ //flytter enemies x og y henholdvis til heros x og y ændringer
            this.y += 1;
        }
        if(this.y > coordinate[0]){
            this.y -= 1;
        }
        if(this.x < coordinate[1]){
            this.x += 1;
        }
        if(this.x > coordinate[1]){
            this.x -= 1;
        }
    }

    public void setEliminated(){
        this.collision = true;
    }
}
