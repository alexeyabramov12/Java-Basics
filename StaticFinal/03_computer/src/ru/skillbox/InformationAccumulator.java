package ru.skillbox;

public class InformationAccumulator {

    private final TypeInformationAccumulator type;
    private final int volume;
    private final double weight;


    public InformationAccumulator(TypeInformationAccumulator type, int volume, double weight) {
        this.type = type;
        this.volume = volume;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return  "\n" +
                "Накопитель информации: " + "\n" +
                "Тип: " + type + "\n" +
                "Объём: " + volume + "\n" +
                "Вес: " + weight;
    }

}
