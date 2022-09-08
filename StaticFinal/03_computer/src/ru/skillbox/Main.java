package ru.skillbox;

public class Main {

    public static void main(String[] args) {
        Processor processor = new Processor(2.3,12,"intel", 0.75 );
        InformationAccumulator informationAccumulator = new InformationAccumulator(TypeInformationAccumulator.SSD, 256, 0.500);
        OperationalMemory operationalMemory = new OperationalMemory("DDR4", 8, 0.200);
        Screen screen = new Screen(19, TypeScreen.IPS, 3.200);
        Keyboard keyboard = new Keyboard("Ножничная", KeyboardBacklight.YES, 1.12);
        Computer acer = new Computer("acer", "nitro 5");
        acer.setProcessor(processor);
        acer.setOperationalMemory(operationalMemory);
        acer.setInformationAccumulator(informationAccumulator);
        acer.setKeyboard(keyboard);
        acer.setScreen(screen);
        System.out.println(acer);
        System.out.println(acer.getTotalWeight());

    }
}
