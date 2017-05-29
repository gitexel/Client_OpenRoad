package Client_OpenRoad;

import org.jetbrains.annotations.NotNull;

import java.net.PasswordAuthentication;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Salem on 3/27/17 at 9:39 PM.
 */
public class account_database extends account_issue {

    private PasswordAuthentication PassAuth;
    private db_use_info db_token = new db_use_info();


    account_database() {

    }

    private Connection connection() throws SQLException {
        try {
            Class.forName(db_token.getDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return DriverManager.getConnection(db_token.getUrl() + "user=" + db_token.getUsername() + "&password=" + db_token.getPassword());

    }

    account_database(String fname, String lname, String username, String mail, @NotNull char[] password, @NotNull LocalDate birthday, String country) {

        PassAuth = new PasswordAuthentication(username, password);
        this.fname = fname;
        this.lname = lname;
        this.mail = mail;
        this.birthday = Date.valueOf(birthday);
        this.country = country;


    }

    protected boolean new_customer_account() throws SQLException {


        PreparedStatement preparedStmt = connection().prepareStatement("insert into opr.customer (username,fname,lname,password,birthday,e_mail,country_name)" + " values (?,?, ?, ?, ?,?,?)");
        preparedStmt.setString(1, PassAuth.getUserName().toLowerCase());
        preparedStmt.setString(2, fname);
        preparedStmt.setString(3, lname);
        preparedStmt.setString(4, String.valueOf(PassAuth.getPassword())); // should be stored hashed (Hashing method)
        preparedStmt.setDate(5, birthday);
        preparedStmt.setString(6, mail);
        preparedStmt.setString(7, country);
        int action = preparedStmt.executeUpdate();
        connection().close();

        return action > 0;
    }

    @Override
    protected ArrayList<String> get_country_list() throws SQLException {

        ArrayList<String> country_list = new ArrayList<String>();
        PreparedStatement pst = connection().prepareStatement("SELECT country.name from opr.country");
        ResultSet rst = pst.executeQuery();

        while (rst.next()) {

            country_list.add(rst.getString("name"));

        }

        return country_list;
    }


}
