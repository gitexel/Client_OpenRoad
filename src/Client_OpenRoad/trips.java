package Client_OpenRoad;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * Created by Salem on 11/13/16.
 */
public class trips extends RecursiveTreeObject<trips> {

    protected ObservableList<trips> flights_history = FXCollections.observableArrayList();
    @NotNull
    protected ObservableList<trips> flights = FXCollections.observableArrayList();  // get flights from search result
    private Timestamp bought_ticket_date;
    private String time;
    private String airport_name_from;
    private String airport_name_to;
    private String trip_type_way;
    private Double price;
    private LocalDate Debenture_date, Arrival_date;
    private String Country_From, Country_to;
    private int trip_id;

    public trips() {


    }


    //flight information
    public trips(int id, String time, String airport_name_from, String airport_name_to,
                 String trip_type_way, Double price) {

        this.trip_id = id;
        this.time = time;
        this.airport_name_from = airport_name_from;
        this.airport_name_to = airport_name_to;
        this.trip_type_way = trip_type_way;
        this.price = price;

    }

    //for trips bought history for home tap
    public trips(int id, Timestamp bought_ticket_date, String time, String airport_name_from,
                 String airport_name_to, String trip_type_way, Double price) {
        this.trip_id = id;
        this.bought_ticket_date = bought_ticket_date;
        this.time = time;
        this.airport_name_from = airport_name_from;
        this.airport_name_to = airport_name_to;
        this.trip_type_way = trip_type_way;
        this.price = price;

    }

    public int getTrip_id() {
        return trip_id;
    }

    public Timestamp getBought_ticket_date() {
        return bought_ticket_date;
    }

    public void setBought_ticket_date(Timestamp bought_ticket_date) {
        this.bought_ticket_date = bought_ticket_date;
    }

    public String getTime() {
        return time;
    }


    public String getAirport_name_from() {
        return airport_name_from;
    }

    public String getAirport_name_to() {
        return airport_name_to;
    }

    public String getTrip_type_way() {
        return trip_type_way;
    }

    public Double getPrice() {
        return price;
    }

    // inputs for search specification
    public LocalDate getDebenture_date() {
        return Debenture_date;
    }

    public LocalDate getArrival_date() {
        return Arrival_date;
    }

    public String getCountry_From() {
        return Country_From;
    }

    public String getCountry_to() {
        return Country_to;
    }

    @NotNull
    public ObservableList<trips> getFlights() {
        return flights;
    }

    public ObservableList<trips> getFlights_history() {
        return flights_history;
    }

    public void setFlights_history(ObservableList<trips> flights_history) {
        this.flights_history = flights_history;
    }


}