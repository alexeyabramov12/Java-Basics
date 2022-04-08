public class Main {

    public static void main(String[] args) {
        Dimensions dimensions = new Dimensions(12.2, 23.3, 11.1);
        CargoInformation cargoInformation = new CargoInformation(dimensions, 23.3, "2da",true,"212n",true);
        dimensions.volume();
        System.out.println(cargoInformation);
        System.out.println(dimensions.getVolume());
    }
}
