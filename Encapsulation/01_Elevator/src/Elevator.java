public class Elevator {

    private int currentFloor = 1;
    private int minFloor;
    private int maxFloor;

    public Elevator(int minFloor, int maxFloor) {
        this.minFloor = this.minFloor + minFloor;
        this.maxFloor = this.maxFloor + maxFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void moveDown() {
        currentFloor -= 1;
    }

    public void moveUp() {
        currentFloor += 1;
    }

    public void move(int floor) {
        if (floor > maxFloor) {
            System.out.println("Введёный этаж больше максимальго");
            return;
        }
        if (floor < minFloor) {
            System.out.println("Введённый этаж меньше минимального");
            return;
        }
        while (currentFloor != floor) {
            if (floor > currentFloor) {
                moveUp();
                System.out.println(getCurrentFloor());
            }
            if (floor < currentFloor) {
                moveDown();
                System.out.println(getCurrentFloor());
            }
        }
    }
}
