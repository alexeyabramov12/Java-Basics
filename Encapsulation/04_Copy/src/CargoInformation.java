public class CargoInformation {

    private final Dimensions dimensions;
    private final double weight;
    private final String deliveryAddress;
    private final boolean canBeTunedOver;
    private final String registrationNumber;
    private final boolean fragile;

    public CargoInformation(Dimensions dimensions, double weight,
                            String deliveryAddress, boolean canBeTunedOver,
                            String registrationNumber, boolean fragile) {
        this.dimensions = dimensions;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.canBeTunedOver = canBeTunedOver;
        this.registrationNumber = registrationNumber;
        this.fragile = fragile;
    }

    public CargoInformation setDimensions(Dimensions dimensions) {
        return new CargoInformation(dimensions, weight, deliveryAddress, canBeTunedOver, registrationNumber, fragile);
    }

    public CargoInformation setWeight(double weight) {
        return new CargoInformation(dimensions, weight, deliveryAddress, canBeTunedOver, registrationNumber, fragile);

    }

    public CargoInformation setDeliveryAddress(String deliveryAddress) {
        return new CargoInformation(dimensions, weight, deliveryAddress, canBeTunedOver, registrationNumber, fragile);

    }

    public CargoInformation setCanBeTunedOver(boolean canBeTunedOver) {
        return new CargoInformation(dimensions, weight, deliveryAddress, canBeTunedOver, registrationNumber, fragile);
    }

    public CargoInformation setRegistrationNumber(String registrationNumber) {
        return new CargoInformation(dimensions, weight, deliveryAddress, canBeTunedOver, registrationNumber, fragile);
    }

    public CargoInformation setFragile(boolean fragile) {
        return new CargoInformation(dimensions, weight, deliveryAddress, canBeTunedOver, registrationNumber, fragile);
    }

    @Override
    public String toString() {
        return "dimensions =" + dimensions + "\n" +
                "weight = " + weight + "\n" +
                "deliveryAddress = '" + deliveryAddress + '\'' + "\n" +
                "canBeTunedOver = " + canBeTunedOver + "\n" +
                "registrationNumber = '" + registrationNumber + '\'' + "\n" +
                "fragile = " + fragile;
    }
}

