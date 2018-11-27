public class V9 {

    public static void main(String[] args) {
        System.out.println(karelAreYouThere("aKarelgfd"));
        System.out.println(karelAreYouThere("34hfK7release"));
        System.out.println(karelAreYouThere("Hey :)"));
        System.out.println(karelAreYouThere("Kaarel"));
    }

    static boolean karelAreYouThere(String input) {
        return input.matches(".*K.rel.*");
    }
}
