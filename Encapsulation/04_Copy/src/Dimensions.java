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

    public void volume() {
        volume = width * height * lenght;
    }

    public double getVolume() {
        System.out.print("volume: ");
        return volume;
    }

    @Override
    public String toString() {
        return " width = " + width +
                ", height = " + height +
                ", lenght = " + lenght;
    }
}
