import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class StationIndex {

    private Map<String, Station> stations;
    private Map<String, Line> lines;


    public StationIndex(){
        stations = new LinkedHashMap<>();
        lines = new LinkedHashMap<>();
    }

    public void addStation(Station station){
        stations.put(station.getName(), station);
    }

    public void addLine(Line line) {
        lines.put(line.getNumber(),line);
    }


    public Map<String, Line> getLines() {
        return lines;
    }

    public Line getLine(String lineNumber) {
        return lines.get(lineNumber);
    }

    public Map<String, Station> getStations() {
        return stations;
    }

}
