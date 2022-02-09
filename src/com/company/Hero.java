package com.company;
import java.util.Random;

public class Hero { // Hero har sine egne koordinater,
    private int x;
    private int y;
    boolean collision;

    public Hero(int x, int y) { // konstruktør som retunere objektet Hero
        this.x = x;
        this.y = y;
    }

    public void goUp() {
        this.y -= 1;

    }

    public void goDown() {
        this.y += 1;

    }
    public void goRight() {
        this.x += 1;

    }
    public void goLeft() {
        this.x -= 1;

    }
    public void jumpHero(int x, int y) {
        Random r = new Random();
        this.x = r.nextInt(x); // x-koordinaten får en random placering
        this.y = r.nextInt(y);  //y-koordinaten får en random placering
    }
    public int[] getCoordinate() {
        int[] coordinate = new int[2]; // opretter en array for hhv. x og y aksen
        coordinate[0] = this.y;
        coordinate[1] = this.x;
        return coordinate;
    }

    public void setEliminated(){
        this.collision = true;
        System.out.println("GAME OVER");
        System.exit(0); // pga. denne bliver boardet ikke printet igen, derfor kommer stjernen ikke frem ved kullision
    }
}


