import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {

    StationIndex stationIndex;
    Line line1;
    Line line2;
    Line line3;

    @Override
    protected void setUp() throws Exception {

        stationIndex = new StationIndex();

        line1 = new Line(1, "Первая");
        line2 = new Line(2, "Вторая");
        line3 = new Line(3, "Третья");

        line1.addStation(new Station("Финская", line1));
        line1.addStation(new Station("Карагандинская", line1));
        line2.addStation(new Station("Астраханская", line2));
        line2.addStation(new Station("Дамбовская", line2));
        line3.addStation(new Station("Лучезарная", line3));
        line3.addStation(new Station("Жвачная", line3));

        List<Station> connections1 = new ArrayList<>();
        List<Station> connections2 = new ArrayList<>();

        connections1.add(new Station("Карагандинская", line1));
        connections1.add(new Station("Астраханская", line2));
        connections2.add(new Station("Дамбовская", line2));
        connections2.add(new Station("Жвачная", line3));

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);

        stationIndex.addStation(new Station("Финская", line1));
        stationIndex.addStation(new Station("Карагандинская", line1));
        stationIndex.addStation(new Station("Астраханская", line2));
        stationIndex.addStation(new Station("Дамбовская", line2));
        stationIndex.addStation(new Station("Лучезарная", line3));
        stationIndex.addStation(new Station("Жвачная", line3));

        stationIndex.addConnection(connections1);
        stationIndex.addConnection(connections2);

    }

    public void testCalculateDuration() {
        List<Station> route = new ArrayList<>();
        route.add(new Station("Финская", line1));
        route.add(new Station("Карагандинская", line1));
        route.add(new Station("Астраханская", line2));
        route.add(new Station("Дамбовская", line2));
        double expected = 8.5;
        double actual = RouteCalculator.calculateDuration(route);
        assertEquals(expected, actual);
    }

    public void testGetRouteOnTheLine() {
        RouteCalculator routeCalculator = new RouteCalculator(stationIndex);
        List<Station> expected = new ArrayList<>();
        Station from = new Station("Финская", line1);
        Station to = new Station("Карагандинская", line1);
        expected.add(new Station("Финская", line1));
        expected.add(new Station("Карагандинская", line1));
        List<Station> actual = routeCalculator.getShortestRoute(from, to);
        assertEquals(expected, actual);
    }

    public void testGetRouteWithOneConnection() {
        RouteCalculator routeCalculator = new RouteCalculator(stationIndex);
        List<Station> expected = new ArrayList<>();
        Station from = new Station("Финская", line1);
        Station to = new Station("Дамбовская", line2);
        expected.add(new Station("Финская", line1));
        expected.add(new Station("Карагандинская", line1));
        expected.add(new Station("Астраханская", line2));
        expected.add(new Station("Дамбовская", line2));
        List<Station> actual = routeCalculator.getShortestRoute(from, to);
        assertEquals(expected, actual);
    }

    public void testGetRouteWithTwoConnection() {
        RouteCalculator routeCalculator = new RouteCalculator(stationIndex);
        List<Station> expected = new ArrayList<>();
        Station from = new Station("Финская", line1);
        Station to = new Station("Жвачная", line3);
        expected.add(new Station("Финская", line1));
        expected.add(new Station("Карагандинская", line1));
        expected.add(new Station("Астраханская", line2));
        expected.add(new Station("Дамбовская", line2));
        expected.add(new Station("Жвачная", line3));
        List<Station> actual = routeCalculator.getShortestRoute(from, to);
        assertEquals(expected, actual);
    }
}
