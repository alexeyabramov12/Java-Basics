package ru.skillbox;

public class Main {

    public static void main(String[] args) {
        Computer acer = new Computer("acer", "nitro 5");
        acer.setProcessor(new Processor(2.3,12,"intel", 0.75 ));
        acer.setOperationalMemory(new OperationalMemory("DDR4", 8, 0.200));
        acer.setInformationAccumulator(new InformationAccumulator(TypeInformationAccumulator.SSD, 256, 0.500));
        acer.setKeyboard( new Keyboard("Ножничная", KeyboardBacklight.YES, 1.12));
        acer.setScreen(new Screen(19, TypeScreen.IPS, 3.200));
        System.out.println(acer);
        System.out.println(acer.getTotalWeight());

    }
}
