package ru.skillbox;

public class Screen {

    private final int diagonal;
    private final TypeScreen type;
    private final double weight;

    public Screen(int diagonal, TypeScreen type, double weight) {
        this.diagonal = diagonal;
        this.type = type;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return  "\n" +
                "Экран: " + "\n" +
                "Диагональ: " + diagonal + "\n" +
                "Тип: " + type + "\n" +
                "Вес: " + weight;
    }

}