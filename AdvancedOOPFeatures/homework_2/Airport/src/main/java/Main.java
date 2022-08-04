import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Date now = new Date();
        System.out.println(now);

    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        //TODO Метод должден вернуть список рейсов вылетающих в ближайшие два часа.
        List<Terminal> terminalList = airport.getTerminals();
        List<Flight> flightList = new ArrayList<>();
        List<Flight> listOfFlightDepartingInTheNextTwoHours = new ArrayList<>();
        Date now = new Date();
        Date nextTwoHours = Date.from(now.toInstant().plus(Duration.ofHours(2)));

        for (Terminal terminal : terminalList) {
            flightList.addAll(terminal.getFlights());
        }

        for (Flight flight : flightList) {
            if (flight.getType() == Flight.Type.DEPARTURE && flight.getDate().after(now) && flight.getDate().before(nextTwoHours)) {
                listOfFlightDepartingInTheNextTwoHours.add(flight);
            }
        }

        return listOfFlightDepartingInTheNextTwoHours;
    }

}