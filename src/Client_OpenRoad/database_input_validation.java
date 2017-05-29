package Client_OpenRoad;

import java.sql.*;

/**
 * Created by Salem on 12/21/16 at 4:21 PM.
 */

public class database_input_validation {

    private db_use_info db_token = new db_use_info();

    public database_input_validation() {


    }

    private Connection connection() throws SQLException {

        try {
            Class.forName(db_token.getDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(db_token.getUrl() + "user=" + db_token.getUsername() + "&password=" + db_token.getPassword());

    }

    public boolean db_username_v(String username) throws SQLException {

        PreparedStatement pst = this.connection().prepareStatement("select username from opr.customer WHERE username=? ");
        pst.setString(1, username);
        ResultSet rs = pst.executeQuery();
        this.connection().close();
        return rs.next();

    }


    public boolean db_email_v(String e_mail) throws SQLException {

        PreparedStatement pst = this.connection().prepareStatement("Select e_mail from opr.customer where e_mail=? ");
        pst.setString(1, e_mail);
        ResultSet rs = pst.executeQuery();
        this.connection().close();
        return rs.next();

    }


    public boolean db_phone_v(String phone) throws SQLException {

        PreparedStatement pst = this.connection().prepareStatement("SELECT phone FROM  opr.customer WHERE phone=?");
        pst.setString(1, phone);
        ResultSet rs = pst.executeQuery();
        this.connection().close();
        return rs.next();

    }


    // check if cusotmer register with this id or not before
    public boolean db_national_id(String id) throws SQLException {


        PreparedStatement pst = this.connection().prepareStatement("Select opr.customer.n_id from opr.customer where n_id=? ");
        pst.setString(1, id);
        ResultSet rs = pst.executeQuery();
        this.connection().close();
        return rs.next();

    }

    // check for the national id if exist in the citizens database (country)
    public boolean db_citizen_national_id(String id) throws SQLException {

        PreparedStatement citizen = this.connection().prepareStatement("Select opr.citizen.n_id from opr.citizen where n_id=? ");
        citizen.setString(1, id);
        ResultSet rs_citizen = citizen.executeQuery();
        this.connection().close();
        return rs_citizen.next();
    }


}
