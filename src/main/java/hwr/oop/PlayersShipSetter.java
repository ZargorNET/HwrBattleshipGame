package hwr.oop;

import java.util.Scanner;

public class PlayersShipSetter {
    int endX;
    int endY;
    int dir;

    Scanner scanner = new Scanner(System.in);
    String[][] Player1SetShips(int FieldSize){
        String[][] Player1Field = new String[FieldSize][FieldSize];
        for (int i = 0; i < FieldSize; i++) {
            for (int j = 0; j < FieldSize; j++) {
                Player1Field[i][j] = "[ ]";
            }
        }

        SetAircraftCarrier(Player1Field, FieldSize);
        SetCorvette(Player1Field, FieldSize);
        SetFrigate(Player1Field, FieldSize);
        SetSubmarine(Player1Field, FieldSize);
        System.out.println("Spieler 1 hat seine Schiffe gesetzt. Loesche bitte die Konsole\n -------------------------------");
        return Player1Field;
    }

    String[][] Player2SetShips(int FieldSize){
        String[][] Player2Field = new String[FieldSize][FieldSize];
        for (int i = 0; i < FieldSize; i++) {
            for (int j = 0; j < FieldSize; j++) {
                Player2Field[i][j] = "[ ]";
            }
        }

        SetAircraftCarrier(Player2Field, FieldSize);
        SetCorvette(Player2Field, FieldSize);
        SetFrigate(Player2Field, FieldSize);
        SetSubmarine(Player2Field, FieldSize);
        System.out.println("Spieler 2 hat seine Schiffe gesetzt. Loesche bitte die Konsole\n -------------------------------");
        return Player2Field;
    }

    void SetAircraftCarrier(String[][] PlayerField, int FieldSize){
        System.out.println("Setze den Flugzeugtraeger (6 Felder)");
        System.out.print("Wert X: ");
        int valueX = scanner.nextInt();
        System.out.print("Wert Y: ");
        int valueY = scanner.nextInt();
        System.out.print("Richtung int Input (U, D, L, R) :");
        int direction = scanner.nextInt();
        if (this.CheckIfValidInput(valueX, valueY, direction, 6, PlayerField, FieldSize)){
            this.setShip(direction, PlayerField, valueX, valueY, 6);

            Field PlayersField = new Field();
            PlayersField.printGameField(FieldSize, PlayerField);
        }
        else{
            System.out.println("!!! KONFLIKT !!!");
            SetAircraftCarrier(PlayerField, FieldSize);
        }

    }

    void SetFrigate(String[][] PlayerField, int FieldSize){
        System.out.println("Setze die Fregatte (4 Felder)");
        System.out.print("Wert X: ");
        int valueX = scanner.nextInt();
        System.out.print("Wert Y: ");
        int valueY = scanner.nextInt();
        System.out.print("Richtung int Input (U, D, L, R) :");
        int direction = scanner.nextInt();
        if (this.CheckIfValidInput(valueX, valueY, direction, 4, PlayerField, FieldSize)){
            this.setShip(direction, PlayerField, valueX, valueY, 4);

            Field PlayersField = new Field();
            PlayersField.printGameField(FieldSize, PlayerField);
        }
        else{
            System.out.println("!!! KONFLIKT !!!");
            SetFrigate(PlayerField, FieldSize);
        }
    }

    void SetCorvette(String[][] PlayerField, int FieldSize){
        System.out.println("Setze die Corvette (3 Felder)");
        System.out.print("Wert X: ");
        int valueX = scanner.nextInt();
        System.out.print("Wert Y: ");
        int valueY = scanner.nextInt();
        System.out.print("Richtung (U, D, L, R) :");
        int direction = scanner.nextInt();
        if (this.CheckIfValidInput(valueX, valueY, direction, 3, PlayerField, FieldSize)){
            this.setShip(direction, PlayerField, valueX, valueY, 3);

            Field PlayersField = new Field();
            PlayersField.printGameField(FieldSize, PlayerField);
        }
        else{
            System.out.println("!!! KONFLIKT !!!");
            SetCorvette(PlayerField, FieldSize);
        }
    }

    void SetSubmarine(String[][] PlayerField, int FieldSize){
        System.out.println("Setze das U-Boot (2 Felder)");
        System.out.print("Wert X: ");
        int valueX = scanner.nextInt();
        System.out.print("Wert Y: ");
        int valueY = scanner.nextInt();
        System.out.print("Richtung (U, D, L, R) :");
        int direction = scanner.nextInt();
        if (this.CheckIfValidInput(valueX, valueY, direction, 2, PlayerField, FieldSize)){
            this.setShip(direction, PlayerField, valueX, valueY, 2);

            Field PlayersField = new Field();
            PlayersField.printGameField(FieldSize, PlayerField);
        }
        else{
            System.out.println("!!! KONFLIKT !!!");
            SetSubmarine(PlayerField, FieldSize);
        }
    }

    boolean CheckIfValidInput(int valueX,int valueY,int direction, int ShipSize, String[][] PlayerField, int FieldSize){

        switch (direction){
            case 1:
                endY = valueY - ShipSize-1;
                dir = -1;
                break;
            case 2:
                endY = valueY + ShipSize-1;
                dir = 1;
                break;
            case 3:
                endX = valueX - ShipSize-1;
                dir = -1;
                break;
            case 4:
                endX = valueX + ShipSize-1;
                dir = 1;
                break;
        }
        for (int i = 0; i < ShipSize; i++) {
            if (direction == 1 || direction == 2 && PlayerField[valueY+(i*dir)][valueX] == "[ ]"){
                continue;
            }
            else if(direction == 3 || direction == 4 && PlayerField[valueY][valueX+(i*dir)] == "[ ]"){
                continue;
            }
            else{
                return false;
            }
        }
        return valueX >=0 && valueX <= FieldSize && valueY >=0 && valueY <= FieldSize && endX >=0 &&
                endX <= FieldSize && endY >=0 && endY <= FieldSize;
    }

    void setShip(int direction, String[][] PlayerField, int valueX, int valueY, int Size){
        switch (direction){     //Hier dann noch die Schiffspositionen eintragen
            case 1:
                for (int i = 0; i < Size; i++) {
                    PlayerField[valueY-i][valueX] = "[*]";
                }
                break;
            case 2:
                for (int i = 0; i < Size; i++) {
                    PlayerField[valueY+i][valueX] = "[*]";
                }
                break;
            case 3:
                for (int i = 0; i < Size; i++) {
                    PlayerField[valueY][valueX-i] = "[*]";
                }
                break;
            case 4:
                for (int i = 0; i < Size; i++) {
                    PlayerField[valueY][valueX+i] = "[*]";
                }
                break;
        }
    }
}
