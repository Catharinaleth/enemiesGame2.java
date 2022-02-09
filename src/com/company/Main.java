package com.company;
import java.util.Scanner; // importere scanner - en database, som gør det muligt at kunne anvende brugerinput

public class Main {

    // her sætter vi antal og størrelse på board
    // public - kan tilgås i hele projektet
    /*static - værdien skal være det samme hele vejen igennem -
    der defineres en variable pr. klasse. - static fordi de er udenfor main metoden
    static gør variablen global - skal ikke være individuel for hver instans
     */
    // final - den tildelte værdi kan ikke ændres i løbet af programmet - er en konstant variable
    // int = det output vi ønsker - int er et tal.
    // ved at fjerne public og final, kan programmet stadig køre, men det er bare relevant at have med, da det ikke skal kunne lade sig gøre at ændre på værdien løbende.
    public static final int enemieAntal = 4;
    public static final int obstacleAntal = 8;
    public static final int boardWidth = 20;
    public static final int boardHeight = 20;

    public static void main(String[] args) { // metoden er en string array, som hedder args + void fordi metoden ikke retunere noget

        System.out.println("Welcome to enemies game !! Use w, a, s, d to control the Hero(H) and j to jump.\nAvoid enemies(E) and obstacles(X).\nHint: try to lure enemies into obstacles");

        Board board = new Board(boardWidth, boardHeight); /*opretter et objekt af klassen Board som hedder board -
        skal indeholde værdierne boardWidth og boardHeight - henter automatisk konstruktøren Board fra klassen
         vi kan nu kalde de forskellige metoder fra klassen Board, som ses her: */
        board.insertObstacle(obstacleAntal, boardWidth, boardHeight);
        board.insertEnemies(enemieAntal, boardWidth, boardHeight);
        board.insertHero(boardWidth, boardHeight);
        board.showBoard();

        // Henter scanner til Brugerinput
        Scanner input = new Scanner(System.in); // der skabes en variable - et objekt af klassen scanner
        String currentInput = input.nextLine(); /* opretter en String fordi brugerinputtet skal være en String
        skal have et input før programmet kan gå videre til det næste */


        // While-loop til styring af Hero
        while (true) { // er sat for at løkken hele tiden køre. og løkken skal ikke sammenlignes med noget, den er det konstant sandt
            if (currentInput.equals("w")) { // equals er en boolean, som er under String + hvis currentInput er lig med det i () skal de udføre nedenstående kommandoer
                board.moveHeroUp();
                board.moveEnemies();
                board.showBoard();
                currentInput = input.nextLine();
                continue; // er tilføjet for ellers er det ikke muligt at taste samme funktionelle tast to gange uden at den skriver vælg en ny
            }

            if (currentInput.equals("s")) {
                board.moveHeroDown();
                board.moveEnemies();
                board.showBoard();
                currentInput = input.nextLine();
                continue;
            }

            if (currentInput.equals("a")) {
                board.moveHeroLeft();
                board.moveEnemies();
                board.showBoard();
                currentInput = input.nextLine();
                continue;
            }
            if (currentInput.equals("d")) {
                board.moveHeroRight();
                board.moveEnemies();
                board.showBoard();
                currentInput = input.nextLine();
                continue;
            }

            if (currentInput.equals("j")) {
                board.jumpHero();
                board.moveEnemies();
                board.showBoard();
                currentInput = input.nextLine();
                continue;
            }
            else {
                board.showBoard();
                System.out.println("forkert tast, tryk på en ny");
                currentInput = input.nextLine();
                continue;

                //kan også skrives som:
            /*else if (!currentInput.equals("w" + "s" + "a" + "d" + "j")) {  - et logisk udtryk !
                board.showBoard();
                System.out.println("forkert tast, tryk på en ny");
                currentInput = input.nextLine(); */
            }
        }
    }
}
    // vi har ikke taget højde for, hvis brugeren kommer til at trykke på en anden tast end de nævnte. Så stopper spillet og det skal dermed startes forfra, hvis der skal spilles igen... her kunne vi have indsat en
