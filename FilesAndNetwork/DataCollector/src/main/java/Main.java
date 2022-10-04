import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


public class Main {

    private static StationIndex stationIndex;
    private static String url = "https://skillbox-java.github.io/" ;


    public static void main(String[] args) {
        try {
            createStationIndex();
            dataSearch("data");
            createMapJsonFile("files/map.json");
            createStationsJsonFile("files/stations.json");
            readMapJsonFileAndPrintCountStations("files/map.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createStationIndex() throws Exception {
        stationIndex = new StationIndex();
        Document doc = Jsoup.connect(url).get();
        parseLines(doc);
        parseStations(doc);
    }

    private static void parseLines(Document doc) {
        Elements lineElements = doc.select("span.js-metro-line");
        lineElements.forEach(lineElement ->
            stationIndex.addLine(new Line(lineElement.text().trim(),
                    lineElement.attr("data-line"))));
    }

    private static void parseStations(Document doc) {
        Elements metroStationElements = doc.select("div.js-metro-stations");
        metroStationElements.forEach(metroStationElement -> {
            Elements stationElements = metroStationElement.select("p.single-station");
            stationElements.forEach(stationElement -> {
                String nameStation = stationElement.select(".name").text().trim();
                String lineNumber = metroStationElement.attr("data-line");
                Station station = new Station(nameStation, lineNumber);
                Line line = stationIndex.getLine(lineNumber);
                line.addStation(station);
                station.setLineName(line.getName());
                hasConnections(station, stationElement);
                stationIndex.addStation(station);
            });
        });
    }

    private static void hasConnections(Station station, Element stationElement) {
        Elements connection = stationElement.select(".t-icon-metroln");
        boolean hasConnection = true;
        if (connection.isEmpty()){
            hasConnection = false;
        }
        station.setHasConnection(hasConnection);
    }

    private static void dataSearch(String path) {
        File file = new File(path);
        if (file.isFile()) {
            if (file.getName().contains(".json")) {
                parseJsonFiles(file);
            }
            if (file.getName().contains(".csv")) {
                parseCsvFile(file);
            }
        }
        File[] files = file.listFiles();
        if (files == null) {
            return;
        }
        for (File folder : files) {
            dataSearch(folder.getAbsolutePath());
        }
    }

    private static void parseJsonFiles(File file) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(getJsonFile(file));
            Map<String, Station> stations = stationIndex.getStations();
            jsonArray.forEach(jsonData -> {
                JSONObject jsonObject = (JSONObject) jsonData;
                if (jsonObject.containsKey("name")) {
                   jsonKey1(jsonObject, stations);
                }
                else if (jsonObject.containsKey("station_name")) {
                   jsonKey2(jsonObject, stations);
                }
            });
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    private static void jsonKey1(JSONObject jsonObject, Map<String, Station> stations) {
        Station station = stations.get((String) jsonObject.get("name"));
        String depth = String.valueOf(jsonObject.get("depth"));
        String date = (String) jsonObject.get("date");
        if (station == null) {
            return;
        }
        if (depth.contains("?")) {
            return;
        }
        if (!depth.equals("null")) {
            station.setDepth(depth);
        }
        if (!(date == null) ) {
            station.setDate(date);
        }
    }

    private static void jsonKey2(JSONObject jsonObject, Map<String, Station> stations) {
        Station station = stations.get((String) jsonObject.get("station_name"));
        String depth = String.valueOf(jsonObject.get("depth_meters"));

        if (station == null) {
            return;
        }
        if (depth.contains("?")) {
            return;
        }
        if (station.getDepth() == null && !depth.equals("null")) {
            station.setDepth(depth);
        }
    }


    private static void parseCsvFile(File file) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
            Map<String, Station> stations = stationIndex.getStations();
            lines.forEach(line -> {
                String[] fragments = line.split("\\,");
                Station station = stations.get(fragments[0]);
                if (station == null) {
                    return;
                }
                if (lines.get(0).equals("Название станции,Дата открытия")) {
                    if (station.getDate() == null) {
                        station.setDate(fragments[1]);
                    }
                }
                if (lines.get(0).equals("Название,Глубина")) {
                    if (station.getDepth() == null && !fragments[1].contains("?")) {
                        station.setDepth(fragments[1]);
                    }
                }
            });
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void createMapJsonFile(String path) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.set("stations", getJsonNodeStation(mapper));
            objectNode.set("lines", getArrayNodeLine(mapper));
            DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
            prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        try {
            String json = mapper.writer(prettyPrinter).writeValueAsString(objectNode);
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JsonNode getJsonNodeStation(ObjectMapper mapper) {
        ObjectNode node = mapper.createObjectNode();
        Map<String, Line> lineMap = stationIndex.getLines();
        Collection<Line> lines = lineMap.values();
        lines.forEach(line -> {
            ArrayNode array = mapper.createArrayNode();
            List<Station> stationList = line.getStationList();
            stationList.forEach(station -> {
                array.add(station.getName());
            });
            node.set(line.getNumber(), array);
        });
        return node;
    }

    private static ArrayNode getArrayNodeLine(ObjectMapper mapper) {
        ArrayNode arrayNode = mapper.createArrayNode();
        Map<String, Line> lineMap = stationIndex.getLines();
        Collection<Line> lineList = lineMap.values();
        lineList.forEach(line -> {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("number", line.getNumber());
            objectNode.put("name", line.getName());
            arrayNode.add(objectNode);
        });
        return arrayNode;
    }

    private static void createStationsJsonFile(String path) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.set("stations", getArrayNodeStations(mapper));
        DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
        prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        try {
            String json = mapper.writer(prettyPrinter).writeValueAsString(objectNode);
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayNode getArrayNodeStations (ObjectMapper mapper) {
        ArrayNode arrayNode = mapper.createArrayNode();
        Map<String, Station> stationMap = stationIndex.getStations();
        Collection<Station> stations = stationMap.values();
        stations.forEach(station -> {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("name", station.getName());
            objectNode.put("line", station.getLineName());
            if (station.getDate() != null) {
                objectNode.put("date", station.getDate());
            }
            if (station.getDepth() != null) {
                String depth = station.getDepth();
                checkingAndAddingStationDepth(objectNode, depth);
            }
            objectNode.put("hasConnection", station.isHasConnection());
            arrayNode.add(objectNode);
        });
        return arrayNode;
    }

    private static void checkingAndAddingStationDepth(ObjectNode objectNode, String depth) {
        if(depth.charAt(0) == '\"') {
            String subString = depth.substring(0, depth.length());
            objectNode.put("depth", subString);
            return;
        }
        if (depth.charAt(0) == (char) 8722 && depth.contains(",")) {
            String depthWithDash = depth.replace((char) 8722, (char) 45);
            String depthWithDot = depthWithDash.replace(',', '.');
            objectNode.put("depth", Double.parseDouble(depthWithDot));
            return;
        }
        if (depth.charAt(0) == (char) 8722) {
            String depthWithDash = depth.replace((char) 8722, (char) 45);
            objectNode.put("depth", Double.parseDouble(depthWithDash));
            return;
        }
        if (depth.contains(",")) {
            String depthWithDot = depth.replace(',', '.');
            objectNode.put("depth", Double.parseDouble(depthWithDot));
        }
        else objectNode.put("depth", Double.parseDouble(depth));
    }

    private static void readMapJsonFileAndPrintCountStations(String path) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(getJsonFile(new File(path)));
            List<Line> lineList = new ArrayList<>();
            JSONArray jsonArrayLines = (JSONArray) jsonObject.get("lines");
            jsonArrayLines.forEach(lineObject -> {
                JSONObject lineJsonObject = (JSONObject) lineObject;
                lineList.add(new Line((String) lineJsonObject.get("name"), (String) lineJsonObject.get("number")));
            });
            JSONObject jsonObjectStations = (JSONObject) jsonObject.get("stations");
            lineList.forEach(line -> {
                JSONArray jsonArrayStations = (JSONArray) jsonObjectStations.get(line.getNumber());
                int countStations = jsonArrayStations.size();
                System.out.println(line.getNumber() +" " + line.getName() + "\nколичество станций: " + countStations);
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static String getJsonFile(File file) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
            lines.forEach(line -> builder.append(line));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return builder.toString();
    }
}
