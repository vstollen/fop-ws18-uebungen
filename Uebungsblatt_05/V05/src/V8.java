public class V8 {

    int strEqual(String a, String b) {
        if (a == b) {
            return 2;
        } else if(a.equals(b)) {
            return 1;
        } else {
            return 0;
        }
    }
}
