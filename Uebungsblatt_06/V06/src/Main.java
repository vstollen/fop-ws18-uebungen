import java.util.HashMap;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        v3();

        for (int i = 0; i < 30; i++) {
            diceRoll();
        }

        v5();

    }

    private static void v3() {
        V3 compare = (x, c) -> x*x > c;

        boolean comparison = compare.compare(2, 3);

        System.out.println(comparison);
    }

    private static void diceRoll() {

        double random = Math.random() * 6;

        if (random == 0) {
            random = 1;
        }

        System.out.println((int) Math.ceil(random));
    }

    private static void v5() {
        int x = 0;

        while (Math.pow(x, 2) <= 1000) {
            x++;
        }

        System.out.println(x);


        for (x = 0; Math.pow(x, 2) <= 1000; x++);

        System.out.println(x);

        x = -1;

        do {
            x++;
        } while (Math.pow(x, 2) <= 1000);

        System.out.println(x);
    }
}
