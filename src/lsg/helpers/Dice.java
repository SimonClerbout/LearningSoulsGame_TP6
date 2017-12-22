package lsg.helpers;

import java.util.Random;

/**
 * Created by sclerbou on 12/10/17.
 */
public class Dice {

    private int faces;
    private Random random;

    public Dice(int faces){
        random = new Random(5340);
        this.faces = faces;

    }

    public int roll(){

          return random.nextInt(faces);

    }

    public static void main(String[] args) {

        Dice d = new Dice(50);
        int max = 0;
        int min = 50;
        for(int i = 0; i < 500; i++){
            int cours = d.roll();
            System.out.print(cours + " ");
            if(cours > max){

                max = cours;

            }
            if(cours < min){

                min = cours;

            }

        }

        System.out.println("\nmax : " + max);
        System.out.println("min : " + min);

    }

}
