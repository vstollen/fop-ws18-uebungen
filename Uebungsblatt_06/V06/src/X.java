public class X {

    public int[] foo(int[] a) {

        int[] b = new int[a.length - a.length / 3];

        for (int i = 1; i < a.length; i++) {
            if (i % 3 != 0) {
                b[i - 1 - i / 3] = a[i];
            }
        }

        return b;
    }
}
