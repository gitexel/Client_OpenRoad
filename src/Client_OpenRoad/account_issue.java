package Client_OpenRoad;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created by Salem on 12/24/16 at 6:24 PM.
 */
public abstract class account_issue {

    static String username;
    String fname;
    String mname;
    String lname;
    String mail;
    String country;
    String phone;
    String gender;
    Date birthday;

    // private String paymethod;

    String nationalid;
    String address;
    String job;
    String education;
    String checkboxenglish;

    // payment

    String paypal_email, visa_or_mastercard,
            credit_card_number, credit_card_cvv,
            bank_account_number;
    Boolean use_paypal, use_credit_card, use_bank_account;


    //for file file names
    String filename_appidphoto;
    String filename_appcvfile;
    String filename_apphealthfile;
    String filename_appenglishfile;

    abstract ArrayList<String> get_country_list() throws SQLException;


}