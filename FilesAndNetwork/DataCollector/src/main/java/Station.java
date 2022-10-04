public class Station {

    private String name;
    private String lineNumber;
    private String lineName;
    private String depth;
    private String date;
    private boolean hasConnection;

    public Station(String name, String number) {
        this.name = name;
        this.lineNumber = number;
    }


    public String getName() {
        return name;
    }

    public String geNumber() {
        return lineNumber;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }


    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isHasConnection() {
        return hasConnection;
    }

    public void setHasConnection(boolean hasConnection) {
        this.hasConnection = hasConnection;
    }


    @Override
    public String toString() {
        return "name - " + name + "\n"
                + "lineNumber - " + lineNumber + "\n"
                + "depth - " + depth + "\n"
                + "date - " + date + "\n"
                + "hasConnection - " + hasConnection;
    }


}
