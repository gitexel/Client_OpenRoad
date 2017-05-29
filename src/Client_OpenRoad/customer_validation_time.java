package Client_OpenRoad;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * Created by Salem on 12/18/16 at 4:18 AM.
 */

public class customer_validation_time extends RecursiveTreeObject<customer_validation_time> {

    @NotNull ObservableList<customer_validation_time> customer_validation_times = FXCollections.observableArrayList();
    private final @Nullable Timestamp request_date;
    private final @Nullable String request_state;
    private final @Nullable Timestamp request_end;
    private final int ONE_YEAR = 1;
    private db_use_info db_token = new db_use_info();

    public customer_validation_time() {

        request_date = null;
        request_state = null;
        request_end = null;

    }


    public customer_validation_time(Timestamp request_date, String request_state, Timestamp request_end) {
        this.request_date = request_date;
        this.request_state = request_state;
        this.request_end = request_end;
    }

    private Connection connection() throws SQLException {
        try {
            Class.forName(db_token.getDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(db_token.getUrl() + "user=" + db_token.getUsername() + "&password=" + db_token.getPassword());

    }

    public @Nullable Timestamp getRequest_date() {
        return request_date;
    }

    public @Nullable String getRequest_state() {
        return request_state;
    }

    public @Nullable Timestamp getRequest_end() {
        return request_end;
    }

    public @NotNull ObservableList<customer_validation_time> getCustomer_validation_times() {
        return customer_validation_times;
    }

    public boolean new_request(String username) {

        /** Generate customer_validation_time info automatically*/
        boolean done = false;
        try {
            Statement smt = connection().createStatement();
            ResultSet resultSet = smt.executeQuery("SELECT CURRENT_TIMESTAMP ");//get form time from the server
            if (resultSet.next()) {
                Timestamp tsp = resultSet.getTimestamp("CURRENT_TIMESTAMP");
                LocalDateTime for1year = tsp.toLocalDateTime().plusYears(1);


                customer_validation_times.add(new customer_validation_time(tsp,
                        "  Valid  ", Timestamp.valueOf(for1year))
                );

                if (send_request_to_database(username, Timestamp.valueOf(for1year))) {
                    done = true;

                }//

            }
            connection().close();
        } catch (Exception hh) {
            done = false;
            System.out.println("This the error " + hh);
        }
        return done;
    }

    //search for customer_validation_times history if available return true
    // get form database
    public boolean request_history(String username) {
        customer_validation_times.clear();
        // example
        // customer_validation_times.add(new customer_validation_time(LocalDate.of(2015,12,6), "Completed & verified successfully", "2016.12.6"));
        Boolean done = false;
        try {
            PreparedStatement pst = connection().prepareStatement("SELECT * from opr.valid_users WHERE username=?");
            pst.setString(1, username);
            ResultSet rst = pst.executeQuery();
            String status;

            while (rst.next()) {

                if (rst.getBoolean(5)) {
                    status = "  Valid  ";
                } else {
                    status = " Not Valid ";
                }

                customer_validation_times.add(new customer_validation_time(rst.getTimestamp(3), status, rst.getTimestamp(4)));// fill history table
                done = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return done;
    }

    //send customer_validation_times and save it to database
    private boolean send_request_to_database(String username, Timestamp request_end) {
        boolean done = false;
        try {

            PreparedStatement pst = connection().prepareStatement("INSERT into opr.valid_users  (username,valid_end)" + "VALUES (?,?)");
            pst.setString(1, username);

            pst.setTimestamp(2, request_end);

            if (pst.execute()) {
                done = true;
            }
            connection().close();

        } catch (SQLException e) {
            e.printStackTrace();
            done = false;
        }
        return done;
    }

}
