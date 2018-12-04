public class YZ extends XY implements B {


    public YZ(long r) {
        super(r);
    }
    @Override
    public double m1(int n, char c) {
        return n + c + p;
    }

    @Override
    public String m2() {
        return "Hallo";
    }

    @Override
    public void m3(XY xy) {
        p = xy.p;
    }
}
