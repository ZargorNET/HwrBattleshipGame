package hwr.oop;

import java.util.Scanner;

public class Field {
    Scanner scanner = new Scanner(System.in);

    int RequestFieldSize(){
        System.out.print("Wie gross soll das Feld werden: ");
        int size = scanner.nextInt();
        return size;
    }

    String[][] setGameField(int size){
        String[][] Field = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Field[i][j] = "[ ]";
            }

        }
        return Field;
    }

    void printGameField(int size, String[][] Field){

        System.out.println(" ");
        for (int i = 0; i < size; i++) {
            if(i<10)
                System.out.print(" "+i+" ");
            else
                System.out.print(i+" ");
        }
        System.out.println(" ");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(Field[i][j]);
            }
            System.out.println(" "+ i);
        }
    }
}
