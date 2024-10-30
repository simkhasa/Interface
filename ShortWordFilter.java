public class ShortWordFilter implements Filter {
    @Override
    public boolean accept(Object x) {
        return x.toString().length() < 5;
    }
}