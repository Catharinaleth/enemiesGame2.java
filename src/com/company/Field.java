package com.company;

public class Field {

    private boolean ledig; // opretter en boolean, som får navnet ledig
    private char typeOfObjekt;


    public Field(){
        ledig = true; // sætter alle fields til at være ledige med mindre andet gør sig gældende
    }

    public boolean isLedig() { // benytter boolean for at tjekke om field er ledig
        return ledig;
    }

    public void setOptaget(){
        this.ledig = false; // noget nyt gør sig gældende og derfor er ledigt blevet til false, da feltet ikke er ledigt
    }

    public void setTypeOfObjekt(char typeOfObjekt){ // opretter metode som kan indeholde et tegn
        this.setOptaget(); //og dermed er feltet optaget
        this.typeOfObjekt = typeOfObjekt;
    }

    public void setClear(){
        this.ledig = true;
        this.typeOfObjekt = ' ';

    }
    public char getTypeOfObjekt () {
        return typeOfObjekt;
    }


}
