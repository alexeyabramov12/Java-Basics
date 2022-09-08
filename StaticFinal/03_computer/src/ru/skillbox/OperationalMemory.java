package ru.skillbox;

public class OperationalMemory {

    private final String type;
    private final int volume;
    private final double weight;

    public OperationalMemory(String type, int volume, double weight) {
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
                "Оперативная память: " + "\n" +
                "Тип: " + type + "\n" +
                "Объем: " + volume + "\n" +
                "Вес: " + weight;
    }

}
