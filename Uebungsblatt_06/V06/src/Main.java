public class Main {

    public static void main(String[] args) {

        v3();
    }

    private static void v3() {
        V3 compare = (x, c) -> x*x > c;

        boolean comparison = compare.compare(2, 3);

        System.out.println(comparison);
    }
}
