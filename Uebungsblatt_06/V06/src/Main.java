import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        v3();

        for (int i = 0; i < 30; i++) {
            diceRoll();
        }

        v5();

        System.out.println(evenlySpaced(-5, 0, 5));
        System.out.println(evenlySpaced(100, 200, 300));
        System.out.println(evenlySpaced(1, 3, 4));

        int[] sumArray = {3, 4, 1, 9, -5, 4};
        sumUp(sumArray);

        System.out.println(Arrays.toString(sumArray));

        int[] mirrorArray = {7, 6, 5, 1, 9, 8, 9, 1, 5, 6, 7, 5, 6, 7};
        System.out.println(maxMirror(mirrorArray));
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

    private static boolean evenlySpaced(int a, int b, int c) {

        int[] numbers = {a, b, c};

        Arrays.sort(numbers);

        int distanceSmallestMedium = numbers[1] - numbers[0];
        int distanceLargestMedium = numbers[2] - numbers[1];

        return distanceSmallestMedium == distanceLargestMedium;
    }

    private static void sumUp(int[] a) {

        int sum = 0;

        for (int i = 0; i < a.length; i++) {
            sum += a[i];

            a[i] = sum;
        }
    }

    private static int maxMirror(int[] arr) {
        int maxMirror = 0;


        for (int i = 0; i < arr.length; i++) {
            int leftMarker = i;

            for (int j = 1; j <= arr.length; j++) {
                int rightMarker = arr.length - j;

                while (rightMarker > leftMarker) {

                    int newMirror = 0;

                    int oldLeftMarker = leftMarker;

                    while (rightMarker >= 0 && arr[rightMarker] == arr[leftMarker]) {
                        newMirror++;

                        leftMarker++;
                        rightMarker--;
                    }

                    maxMirror = Math.max(maxMirror, newMirror);

                    leftMarker = oldLeftMarker;
                    rightMarker--;
                }
            }
        }

        return maxMirror;
    }

}
