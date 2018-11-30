public class Main {

    public static void main(String[] args) {

        v3();

        for (int i = 0; i < 30; i++) {
            diceRoll();
        }


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
}
