package com.company;
import java.util.ArrayList; // importere databasen for at kunne oprette en liste i klassen.
import java.util.Random; // importere databasen, da vi skal bruge en random funktion


public class Board {

    // kan kun tilgåes i klassen derfor private
    private int width; // Bredden på brættet - vi erklære en variable, som først skal have en værdi senere i programmet
    private int heigth; // Højden på brættet

    private Field[][] fields; // y, x - vi skal have et todimensionelt array - en liste som er vandret og en liste som er lodret y, x
    private Hero hero; // henter klassen Hero ind i denne klasse - opretter en variable hero, som er private da den kun skal tilgåes i denne klasse
    private ArrayList<Enemie> enemies = new ArrayList<Enemie>(); // Opretter en ny liste med enemie objekt
    private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>(); // Opretter en ny liste med obstacle objekt < > er en wrapper klasse, som kræver et objekt


    public Board(int width, int heigth) { // Konstruktør som angiver et objekt 'Board', som har en width og height variabel
        this.width = width;
        this.heigth = heigth;

        fields = new Field[width][heigth]; // Opretter brættet med felter inden i - et objekt
        for (int w = 0; w < heigth; w++) { //Et for-loop som definerer at 'Field' skal være indenfor brættets width og height
            for (int h = 0; h < width; h++) {
                fields[w][h] = new Field(); // Indsætter objektet 'Field', fra Field-klassen, i listerne
            }
        }
    }

    public void insertObstacle(int numberOfObstacle, int width, int heigth) { // opretter en metode som kræver tre parametre
        int x;
        int y;
        Random random = new Random(); // henter random fordi obstacle skal indsættes random.
        for (int countOfObstacle = 0; countOfObstacle < numberOfObstacle; countOfObstacle++) { // tællingen af obstacles vokser med en, indtil det rammer værdien for numberOfObstacles
            boolean fieldIsSet = false; // feltet er ikke optaget

            do {
                x = random.nextInt(width); // x-koordinaten får en random placering
                y = random.nextInt(heigth); // y-koordinaten får en random placering
                if (fields[y][x].isLedig()) { // hvis fields pladser i listen er ledig, skal nedenstående udføres:
                    fields[y][x].setTypeOfObjekt('X'); // hvis pladsen er ledig sættes et X
                    fields[y][x].setOptaget(); // sætter feltet til at være optaget
                    fieldIsSet = true; // feltet er optaget
                }
            }
            while (fieldIsSet == false);
            obstacles.add(new Obstacle(x, y)); // add anvendes for at tilføje et obstacle fra listen
        }
    }

    // her sker det samme som med obstacle (der er dog ikke et for-loop, da vi ikke skal have flere af den)
    public void insertHero(int width, int heigth) {
        int x;
        int y;
        Random random = new Random();
        boolean fieldIsSet = false; // Feltet er ledigt

        // en do-while løkke, som udføre do når feltet er ledigt.
        do {
            x = random.nextInt(width); // x-koordinaten får en random placering
            y = random.nextInt(heigth); // y-koordinaten får en random placering
            if (fields[y][x].isLedig()) { // hvis fields pladser i listen er ledig, skal nedenstående udføres:
                fields[y][x].setTypeOfObjekt('H'); // hvis pladsen er ledig sættes et H
                fields[y][x].setOptaget(); // sætter feltet til at være optaget
                fieldIsSet = true; // feltet er optaget
            }
        }
        while (fieldIsSet == false) ;
        hero = new Hero(x, y); // Når feltet er ledigt indsættes Hero

    }

    //det samme sker her bare med enemies
    public void insertEnemies(int numberOfEnemies, int width, int heigth) {
        int x;
        int y;
        Random random = new Random();
        for (int countOfEnemies = 0; countOfEnemies < numberOfEnemies; countOfEnemies++) { // tællingen af enemies vokser med en, indtil det rammer værdien for numberOfEnemies
            boolean fieldIsSet = false; // feltet er ikke optaget

            do {
                x = random.nextInt(width); // x-koordinaten får en random placering
                y = random.nextInt(heigth); // y-koordinaten får en random placering
                if (fields[y][x].isLedig()) { // hvis fields pladser i listen er ledig, skal nedenstående udføres:
                    fields[y][x].setTypeOfObjekt('E'); // hvis pladsen er ledig sættes et E
                    fields[y][x].setOptaget(); // sætter feltet til at være optaget
                    fieldIsSet = true; // feltet er optaget
                }
            }
            while (fieldIsSet == false);
            enemies.add(new Enemie(x, y)); // add anvendes for at tilføje en enemie fra listen
        }
    }

    public void drawLine(int width) { // Laver metode som tegner brættets kant i bredden
        String symbol = new String(); // opretter objekt af klassen string for at vi kan anvende symbol senere
        for (int w = 0; w < width; w++) { // Tegner symbol, så længe w er mindre end boards bredde
            symbol += "_";
        }
        System.out.println(symbol);
    }

    public void showBoard() {
        drawLine(width); //øverste streg

        //Det her sker mellem de to streger i bredden
        for (int w = 0; w < this.heigth; w++) {
            String symbol = "|";
            for (int h = 0; h < this.width; h++) {
                if (fields[w][h].isLedig() == true) {
                    symbol += " ";
                } else {
                    symbol += fields[w][h].getTypeOfObjekt();
                }
            }
            symbol += "|";
            System.out.println(symbol);
        }
        drawLine(width); // nederste streg
    }

    // det som for Hero til at bevæge sig + 'forsvinde'
    public void moveHeroUp() {
        int[] coordinate = hero.getCoordinate(); //henter metoden getCoordinate Heros gamle koordinater
        hero.goUp(); //ændrer i heros y koordinat

        fields[coordinate[0]][coordinate[1]].setTypeOfObjekt('H'); //sætter et nyt H på Heros nye koordinat

        int[] newCoordinate = hero.getCoordinate(); //henter Heros nye koordinater

        if (fields[newCoordinate[0]][newCoordinate[1]].isLedig()) { //hvis pladsen er ledig -->
            fields[coordinate[0]][coordinate[1]].setClear(); // slettes H på Heros gamle koordinater
            fields[newCoordinate[0]][newCoordinate[1]].setTypeOfObjekt('H'); // og sætter et H på Heros nye koordinater

        }
        else { //hvis ikke feltet er tomt skal følgende udføres (Kollision mellem to objekter)
            hero.setEliminated();
            fields[coordinate[0]][coordinate[1]].setTypeOfObjekt(' '); //fjerne H på gamle koordinater
            fields[newCoordinate[0]][newCoordinate[1]].setTypeOfObjekt('*'); //sætter stjerne på nye, for at indikerer at der er sket et sammenstød
        }
    }

    public void moveHeroDown() {
       // int Hero = new Hero()
        int[] coordinate = hero.getCoordinate(); //henter Heros gamle koordinater
        hero.goDown(); // ændre i Hero's y koordinat

        fields[coordinate[0]][coordinate[1]].setTypeOfObjekt('H'); //sætter et nyt H på heors nye koordinat

        int[] newCoordinate = hero.getCoordinate(); //henter Heros nye koordinater

        if (fields[newCoordinate[0]][newCoordinate[1]].isLedig()) { //hvis pladsen er ledig -->
            fields[coordinate[0]][coordinate[1]].setClear(); // slettes H på Heros gamle koordinater
            fields[newCoordinate[0]][newCoordinate[1]].setTypeOfObjekt('H'); // og sætter et H på heors nye koordinater

        } else { //hvis ikke feltet er tomt skal følgende udføres (Kollision mellem to objekter)
            hero.setEliminated();
            fields[coordinate[0]][coordinate[1]].setTypeOfObjekt(' '); //fjerne H på gamle koordinater
            fields[newCoordinate[0]][newCoordinate[1]].setTypeOfObjekt('*'); //sætter stjerne på nye, for at indikerer at der er sket et sammenstød

        }

        /* kunne tilføje noget med at hvis der sker en kollison mellem H og kant skal der ske noget */
    }

    public void moveHeroRight() {
        int[] coordinate = hero.getCoordinate(); //henter heros gamle koordinater
        hero.goRight(); // ændre i Hero's x koordinater

        fields[coordinate[0]][coordinate[1]].setTypeOfObjekt('H'); //sætter et nyt H på Heros nye koordinat

        int[] newCoordinate = hero.getCoordinate(); //henter Heros nye koordinater

        if (fields[newCoordinate[0]][newCoordinate[1]].isLedig()) { //hvis pladsen er ledig -->
            fields[coordinate[0]][coordinate[1]].setClear(); // slettes H på Heros gamle koordinater
            fields[newCoordinate[0]][newCoordinate[1]].setTypeOfObjekt('H'); // og sætter et H på Heros nye koordinater

        } else { //hvis ikke feltet er tomt skal følgende udføres (Kollision mellem to objekter)
            hero.setEliminated();
            fields[coordinate[0]][coordinate[1]].setTypeOfObjekt(' '); //fjerne H på gamle koordinater
            fields[newCoordinate[0]][newCoordinate[1]].setTypeOfObjekt('*'); //sætter stjerne på nye, for at indikerer at der er sket et sammenstød
        }
    }

    public void moveHeroLeft() {
        int[] coordinate = hero.getCoordinate(); //henter heros gamle koordinater
        hero.goLeft(); //ændre i Heros x koordinater

        fields[coordinate[0]][coordinate[1]].setTypeOfObjekt('H'); //sætter et nyt H på heors nye koordinat

        int[] newCoordinate = hero.getCoordinate(); //henter Heros nye koordinater

        if (fields[newCoordinate[0]][newCoordinate[1]].isLedig()) { //hvis pladsen er ledig -->
            fields[coordinate[0]][coordinate[1]].setClear(); // slettes H på Heros gamle koordinater
            fields[newCoordinate[0]][newCoordinate[1]].setTypeOfObjekt('H'); // og sætter et H på Heros nye koordinater

        } else { //hvis ikke feltet er tomt skal følgende udføres (Kollision mellem to objekter)
            hero.setEliminated();
            fields[coordinate[0]][coordinate[1]].setTypeOfObjekt(' '); //fjerne H på gamle koordinater
            fields[newCoordinate[0]][newCoordinate[1]].setTypeOfObjekt('*'); //sætter stjerne på nye, for at indikerer at der er sket et sammenstød
        }
    }
    // vi når ikke at se *, da programmet exiter før, da vi har defineret system.exit inde i Hero klassen

    public void jumpHero() {
        int[] coordinate = hero.getCoordinate(); //henter Heros gamle koordinater
        hero.jumpHero(width, heigth); //ændrer Heros x og y koordinat

        fields[coordinate[0]][coordinate[1]].setTypeOfObjekt('H'); //sætter et nyt H på Heros nye koordinater

        int[] newCoordinate = hero.getCoordinate(); //henter Heros nye koordinater

        if (fields[newCoordinate[0]][newCoordinate[1]].isLedig()) { //hvis pladsen er ledig -->
            fields[coordinate[0]][coordinate[1]].setClear(); // slettes H på Heros gamle koordinater
            fields[newCoordinate[0]][newCoordinate[1]].setTypeOfObjekt('H'); // og sætter et H på Heros nye koordinater
        }
    }

    public void moveEnemies() {
        for (int e = 0; e < this.enemies.size(); e++) { //løber liste med enemies igennem, så man får fat i alle - med this fortæller vi at vi tager fat i den første liste
                int[] coordinate = this.enemies.get(e).getCoordinate(); //får fat i enemies koordinater
                this.enemies.get(e).moveEnemiesToward(hero); //ændrer i enemies koordinater i forhold til Hero
                int[] newCoordinate = this.enemies.get(e).getCoordinate(); //henter enemies nye koordinater

               if (fields[newCoordinate[0]][newCoordinate[1]].isLedig()) { //hvis pladsen er ledig -->
                   fields[coordinate[0]][coordinate[1]].setClear(); // slettes E på enemies gamle koordinater
                   fields[newCoordinate[0]][newCoordinate[1]].setTypeOfObjekt('E'); // og sætter et E på enemies nye koordinater
               } else if (fields[newCoordinate[0]][newCoordinate[1]].getTypeOfObjekt() == 'H') { //hvis koordinatet er det samme som H...
                   hero.setEliminated(); // skal Hero tabe
                   fields[coordinate[0]][coordinate[1]].setClear(); //fjerner det gamle E
                   fields[newCoordinate[0]][newCoordinate[1]].setTypeOfObjekt('E'); //sætter det nye E hvor sammenstødet fandt sted
               } else { //hvis ingen af ovenstående er gældende, er det fordi E har ramt en obstacle
                   this.enemies.get(e).setEliminated(); //så skal E tabe
                   this.enemies.remove(e); // og den enkelte E der har ramt obstacle skal fjernes fra enemies listen
                   fields[coordinate[0]][coordinate[1]].setTypeOfObjekt(' '); // og feltet skal stå tomt
               }
               if (this.enemies.isEmpty()){ //når enemies listen er tom, har spilleren vundet
                   System.out.println("You won !!");
                   showBoard();
                   showBoard();
                   System.exit(0);
               }
           }
       }
    }









