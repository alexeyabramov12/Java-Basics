public class Dimensions {

    private final double width;
    private final double height;
    private final double lenght;
    private double volume;

    public Dimensions(double width, double height, double lenght) {
        this.width = width;
        this.height = height;
        this.lenght = lenght;
    }

    public double getVolume() {
        volume = width * height * lenght;
        System.out.print("volume = ");
        return volume;
    }

    @Override
    public String toString() {
        return " width = " + width +
                ", height = " + height +
                ", lenght = " + lenght;
    }
}
