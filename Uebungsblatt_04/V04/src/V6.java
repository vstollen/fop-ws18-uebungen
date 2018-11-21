
public class V6 {

    public static void main(String[] args) {
        int number = (int) ((Math.random() * 10) - 5);

        System.out.println(number);

        if (number < 0) {
            number = -number;
            number += 1;
        } else {
            number = number * 2;
        }

        System.out.println(number);
    }
}
