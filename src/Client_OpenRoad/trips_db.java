package Client_OpenRoad;

import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Salem on 4/8/17 at 11:02 PM.
 */
public class trips_db extends trips {
    private db_use_info db_token = new db_use_info();

    public trips_db() {

    }

    private Connection connection() throws SQLException {
        try {

            Class.forName(db_token.getDriver());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(db_token.getUrl() + "user=" + db_token.getUsername() + "&password=" + db_token.getPassword());

    }

    @NotNull
    public Boolean found_result(String Country_From, String Country_to, @NotNull LocalDate Debenture_date, @NotNull LocalDate Arrival_date) { //for trips tap
        Boolean done = false;
        flights.clear(); // clear trips table
        try {


            PreparedStatement pst = connection().prepareStatement("SELECT * FROM opr.trip WHERE origin=? and destination=? and trip.departure=? and  trip.arrival=?");
            pst.setString(1, Country_From);
            pst.setString(2, Country_to);
            pst.setDate(3, Date.valueOf(Debenture_date));
            pst.setDate(4, Date.valueOf(Arrival_date));

            ResultSet rst = pst.executeQuery();


            while (rst.next()) {

                int trip_id = rst.getInt("id");
                String origin = rst.getString("origin");
                String dstination = rst.getString("destination");
                String class1 = rst.getString("class");
                String departure = String.valueOf(rst.getDate("departure"));
                String dclock = String.valueOf(rst.getTime("dclock"));
                String arrival = String.valueOf(rst.getDate("arrival"));
                String aclock = String.valueOf(rst.getTime("aclock"));
                double price = rst.getDouble("price");

                flights.add(new trips(trip_id, (departure + " " + dclock + " To " + arrival + " " + dclock),
                        origin, dstination, class1, price)
                );
                System.out.println(trip_id + (departure + " " + dclock + " To " + arrival + " " + dclock) +
                        origin + " " + dstination + " " + class1 + " " + price);

            }


            connection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Done: " + done);
        return !flights.isEmpty();

    }


    @NotNull
    public Boolean find_flights_history(String username) {  // for home tap // find history in the first app loading


        flights_history.clear();
        ArrayList<Integer> flight_id_counter = new ArrayList<>();
        ArrayList<Timestamp> flight_date_counter = new ArrayList<>();

        try {

            PreparedStatement pst = connection().prepareStatement("SELECT * from opr.history where username=? ");
            pst.setString(1, username);
            ResultSet rst = pst.executeQuery();

            while (rst.next()) {

                flight_id_counter.add(rst.getInt("trip_id"));
                flight_date_counter.add(rst.getTimestamp("p_date"));

            }
            connection().close();

            if (!(flight_id_counter.size() == 0)) {

                for (int i = 0; i < flight_id_counter.size(); i++) {

                    PreparedStatement pst1 = connection().prepareStatement("SELECT * from opr.trip where id=? ");
                    pst1.setInt(1, flight_id_counter.get(i));
                    ResultSet rst1 = pst1.executeQuery();

                    if (rst1.next()) {

                        String time =

                                (String.valueOf(rst1.getDate("departure")) + " " + String.valueOf(rst1.getTime("dclock"))
                                        + " To " + String.valueOf(rst1.getDate("arrival"))
                                        + " " + String.valueOf(rst1.getTime("aclock")));

                        flights_history.add(new trips(rst1.getInt("id")
                                , flight_date_counter.get(i)
                                , time
                                , rst1.getString("origin")
                                , rst1.getString("destination")
                                , rst1.getString("class")
                                , rst1.getDouble("price")));


                    }

                }

                connection().close();
                return true;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // save to database
    public boolean send_bought_trips_to_database(String username, int flight_id) {


        try {

            PreparedStatement pst = connection().prepareStatement("SELECT username from opr.history WHERE username=? and trip_id=?");
            pst.setString(1, username);
            pst.setInt(2, flight_id);
            ResultSet rst = pst.executeQuery();
            connection().close();

            if (!rst.next()) {  // if customer didn't buy the same flight


                try {

                    PreparedStatement pst1 = connection().prepareStatement("INSERT into opr.history (username,trip_id)" + "VALUEs (?,?)");

                    pst1.setString(1, username);
                    pst1.setInt(2, flight_id);


                    pst1.executeUpdate();
                    connection().close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return true;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
