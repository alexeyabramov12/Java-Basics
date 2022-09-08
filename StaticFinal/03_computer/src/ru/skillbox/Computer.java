package ru.skillbox;

public class Computer {

    private final String vendor;
    private final String name;
    private Processor processor;
    private OperationalMemory operationalMemory;
    private InformationAccumulator informationAccumulator;
    private Screen screen;
    private Keyboard keyboard;

    public Computer(String vendor, String name) {
        this.vendor = vendor;
        this.name = name;
    }

    public double getTotalWeight() {
        return    processor.getWeight()
                + informationAccumulator.getWeight()
                + operationalMemory.getWeight()
                + screen.getWeight()
                + keyboard.getWeight();
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public OperationalMemory getOperationalMemory() {
        return operationalMemory;
    }

    public void setOperationalMemory(OperationalMemory operationalMemory) {
        this.operationalMemory = operationalMemory;
    }

    public InformationAccumulator getInformationAccumulator() {
        return informationAccumulator;
    }

    public void setInformationAccumulator(InformationAccumulator informationAccumulator) {
        this.informationAccumulator = informationAccumulator;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    @Override
    public String toString() {
        return  "Характеристики компьютера: " + "\n" +
                "Производитель: " + vendor + "\n" +
                "Название: " + name + "\n" +
                 processor + "\n" +
                 operationalMemory + "\n" +
                 informationAccumulator + "\n" +
                 screen + "\n" +
                 keyboard;
    }

}
