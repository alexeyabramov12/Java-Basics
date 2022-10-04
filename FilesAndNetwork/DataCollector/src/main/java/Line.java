import java.util.ArrayList;
import java.util.List;

public class Line {

    private String name;
    private String number;
    private List<Station> stationList;


    public Line(String name, String number) {
        this.name = name;
        this.number = number;
        stationList = new ArrayList<>();
    }

    public void addStation(Station station) {
        stationList.add(station);
    }

    public List<Station> getStationList() {
        return stationList;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return name + " " + number;
    }

}
