import java.awt.*;

public class BigRectangleFilter implements Filter {
    //Provide a class BigRectangleFilter which implements the Filter interface
    // whose accept method accepts all java Rectangle objects that have a perimeter > 10.
    @Override
    public boolean accept(Object x) {
        Rectangle r = (Rectangle) x;
        return (r.getHeight() + r.getWidth()) * 2 > 10;
    }

}