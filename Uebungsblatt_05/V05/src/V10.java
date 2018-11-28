public class V10 {

    public static void main(String[] args) {
        System.out.println(sumAllDigits("asd38asd2139oioawh")); //26
    }

    static int sumAllDigits(String input) {

        int sum = 0;

        char[] inputCharArray = input.toCharArray();

        for (char inputChar : inputCharArray) {

            if (Character.isDigit(inputChar)) {
                sum = sum + Integer.parseInt(Character.toString(inputChar));
            }
        }

        return sum;
    }
}
