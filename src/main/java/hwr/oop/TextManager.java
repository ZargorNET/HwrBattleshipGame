package hwr.oop;

public class TextManager {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    void WelcomeMessage(){
        System.out.println("\n Herzlich Willkommen im Spiel Fische ertraenke...ehhm...Schiffe versenken. \n" +
                "Ihr tretet gegeneinander an und wer zuerst die Schiffe seines Gegeners versenkt hat, gewinnt das Spiel. \n" +
                "----------------Legende--------------- \n" +
                "[*] Hier wurde ein Schiff von dir gesetzt\n"+
                "[ ] Feld noch nicht entdeckt/kein Schiff gesetzt  \n" +
                "[/] Feld ohne Treffer \n" +
                "[#] Feld mit Treffer \n" +
                "--------------------------------------");
    }
}
