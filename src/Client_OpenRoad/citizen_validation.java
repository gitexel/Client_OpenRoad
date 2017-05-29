package Client_OpenRoad;

import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.time.LocalDate;

/**
 * Created by Salem on 12/28/16 at 3:41 AM.
 */
public class citizen_validation extends customer_validation_time {

    private db_use_info db_token = new db_use_info();

    public citizen_validation() {


    }

    private Connection connection() throws SQLException {
        try {
            Class.forName(db_token.getDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(db_token.getUrl() + "user=" + db_token.getUsername() + "&password=" + db_token.getPassword());

    }

    // check if the national id has true value
    public @NotNull Boolean valid_citizen(String username, String n_id, @NotNull String f_name, @NotNull String m_name, @NotNull String l_name, String country, @NotNull String gender, LocalDate birthday) {
        int action = 0;
        try {

            PreparedStatement pst = connection().prepareStatement("SELECT n_id from opr.citizen WHERE n_id=? and fname=? and mname=? and lname=? and country_name=? and gender=? and birthday=?"

            );

            pst.setString(1, n_id);
            pst.setString(2, f_name.toLowerCase());
            pst.setString(3, m_name.toLowerCase());
            pst.setString(4, l_name.toLowerCase());
            pst.setString(5, country);
            pst.setString(6, String.valueOf(gender.charAt(0)));
            pst.setDate(7, Date.valueOf(birthday));
            ResultSet rst = pst.executeQuery();


            if (rst.next()) {

                connection().close();
                PreparedStatement pst1 = connection().prepareStatement("update opr.customer set verified=?, n_id=? WHERE username=?");

                pst1.setBoolean(1, true);
                pst1.setString(2, n_id);
                pst1.setString(3, username);
                action = pst1.executeUpdate();


            }
            connection().close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (action > 0) { //user have validation for 1 year if no still customer withot id and not verfied

            new_request(username);
            return true;
        } else return false;


    }

    // check if the customer hava been validated in the database
    protected boolean check_customer_citizen(String username) {
        boolean done = false;


        check_validation_time(username); // check validation time expired or not

        try {
            PreparedStatement pst1 = connection().prepareStatement("SELECT customer.verified FROM opr.customer WHERE username=? and verified=?");

            pst1.setString(1, username);
            pst1.setInt(2, 1);
            ResultSet rst1 = pst1.executeQuery();
            connection().close();
            done = rst1.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return done;


    }


    private void check_validation_time(String username) {

        try {


            PreparedStatement pst2 = connection().prepareStatement("SELECT * from opr.valid_users WHERE username=? and valid=?");
            pst2.setString(1, username);
            pst2.setBoolean(2, true);
            ResultSet rst1 = pst2.executeQuery();
            connection().close();


            if (rst1.next()) {

                Statement pst1 = connection().createStatement();
                ResultSet rst4 = pst1.executeQuery("SELECT CURRENT_TIMESTAMP");//current server time


                if (rst4.next()) { // get current time status


                    if (rst1.getTimestamp(4).before(rst4.getTimestamp("CURRENT_TIMESTAMP"))) { //check if expired
                        connection().close();
                        PreparedStatement pst3 = connection().prepareStatement("update opr.valid_users set valid=? WHERE username=? and valid=true");
                        pst3.setBoolean(1, false);
                        pst3.setString(2, username);
                        pst3.executeUpdate();
                        connection().close();

                        PreparedStatement pst4 = connection().prepareStatement("update opr.customer set verified=? WHERE username=?");
                        pst4.setBoolean(1, false);
                        pst4.setString(2, username);
                        pst4.executeUpdate();
                        connection().close();
                    }
                } else {
                    connection().close();

                }
            } else { // if not have any valid time

                PreparedStatement pst5 = connection().prepareStatement("update opr.customer set verified=? WHERE username=?");
                pst5.setBoolean(1, false);
                pst5.setString(2, username);
                pst5.executeUpdate();
                connection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
