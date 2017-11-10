package lsg.helpers;
import java.util.Random;

public class Dice {
    private int faces;
    private Random random;

    public Dice(int face){
        random = new Random(5340);
        faces = face;
    }

    public int roll(){
        return random.nextInt(faces);
    }

    public static void main(String[] args) {
        Dice d = new Dice(50);
        for(int i = 0; i < 500; i ++)
            System.out.println(d.roll()+",");
    }
}
