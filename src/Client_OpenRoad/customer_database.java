package Client_OpenRoad;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Salem on 11/16/16 at 11:17 PM.
 */
public class customer_database extends account_issue {

    private final String sql_get_customer = "SELECT * FROM opr.customer where username=?";
    private db_use_info db_token = new db_use_info();

    public customer_database(String username) {

        account_issue.username = username;

    }

    public customer_database() {

    }

    private Connection connection() throws SQLException {

        try {
            Class.forName(db_token.getDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(db_token.getUrl() + "user=" + db_token.getUsername() + "&password=" + db_token.getPassword());

    }


    public void update_main_db() {

        /**
         * First name ex. this.fname = input form database
         * Last name
         * username
         * e_mail
         * country
         * phone number
         **/

        PreparedStatement preparedStatement = null;


        try {

            preparedStatement = connection().prepareStatement(sql_get_customer);
            preparedStatement.setString(1, account_issue.username);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                phone = rs.getString("phone");
                fname = rs.getString("fname");
                lname = rs.getString("lname");
                account_issue.username = rs.getString("username");
                mail = rs.getString("e_mail");
                country = rs.getString("country_name");
            }

        } catch (SQLException e) {

            System.out.println("what is the f*ck");

        } finally {

            if (preparedStatement != null) {

                try {
                    preparedStatement.close();

                    if (this.connection() != null) {
                        this.connection().close();
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
       /* try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }

    public boolean update_profile_db() {

        /**
         * First Name
         * Middle Name
         * Last Name
         * Gender
         * Birth of date
         * username
         * email
         * country
         * phone number
         * password
         * */

        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = connection().prepareStatement(sql_get_customer);
            preparedStatement.setString(1, account_issue.username);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                account_issue.username = rs.getString("username");
                fname = rs.getString("fname");
                mname = rs.getString("mname");
                lname = rs.getString("lname");
                birthday = rs.getDate("birthday");
                gender = rs.getString("gender");
                mail = rs.getString("e_mail");
                country = rs.getString("country_name");
                phone = rs.getString("phone");
                nationalid = rs.getString("n_id");


            }

        } catch (SQLException e) {
            System.out.println("what is the f*ck");

        } finally {

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();

                    if (this.connection() != null) {
                        this.connection().close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
       /* try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return false;
    }

    public boolean update_application_db() {


        /**
         * id photo name ex. happy.jpg
         * national id
         * address
         * job
         * education
         * cv file name ex.  Bisho_cv.pdf
         * Linkedin
         * health certificate file name    el_kaser_el3eny.pdf
         * English certificate file name   iletes_2016_bisho.pdf
         * */


        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = this.connection().prepareStatement(this.sql_get_customer);
            preparedStatement.setString(1, account_issue.username);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                filename_appidphoto = rs.getString("filename_appidphoto");
                filename_appcvfile = rs.getString("filename_appcvfile");
                filename_apphealthfile = rs.getString("filename_apphealthfile");
                filename_appenglishfile = rs.getString("filename_appenglishfile");
                education = rs.getString("Education");
                job = rs.getString("job");
                address = rs.getString("address");
                checkboxenglish = rs.getString("Checkboxenglish");
            }

        } catch (SQLException e) {

            System.out.println("what is the f*ck");

        } finally {

            if (preparedStatement != null) {

                try {
                    preparedStatement.close();

                    if (this.connection() != null) {
                        this.connection().close();
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
       /* try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return false;
    }

    public void update_payment_db() {


        PreparedStatement preparedStatement = null;


        try {

            preparedStatement = this.connection().prepareStatement(this.sql_get_customer);
            preparedStatement.setString(1, account_issue.username);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                paypal_email = rs.getString("paypal_email");
                visa_or_mastercard = rs.getString("visa_or_mastercard");
                credit_card_number = rs.getString("credit_card_number");
                credit_card_cvv = rs.getString("credit_card_cvv");
                bank_account_number = rs.getString("bank_account_number");
                use_paypal = rs.getBoolean("paypal_email_b");
                use_credit_card = rs.getBoolean("use_credit_card_b");
                use_bank_account = rs.getBoolean("use_bank_account_b");
            }


        } catch (SQLException e) {

            System.out.println("what is the f*ck");

        } finally {

            if (preparedStatement != null) {

                try {
                    preparedStatement.close();

                    if (this.connection() != null) {
                        this.connection().close();
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }


    }

    private void send_string_to_db(String value, String column_name) { //dynameic method string, boolean or intger

        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = this.connection().prepareStatement("update opr.customer set customer." + column_name + "=? where customer.username=?");

            preparedStmt.setString(1, value);
            preparedStmt.setString(2, account_issue.username);
            preparedStmt.executeUpdate();

            this.connection().close();
        } catch (SQLException e) {
            System.out.println("Sql excption in " + column_name + "\n" + e);

        } finally {

            if (preparedStmt != null) {
                try {
                    preparedStmt.close();

                    if (this.connection() != null) {
                        this.connection().close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void send_boolean_to_db(Boolean value, String column_name) { //dynameic method string, boolean or intger

        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = this.connection().prepareStatement("update opr.customer set customer." + column_name + "=? where customer.username=?");

            preparedStmt.setBoolean(1, value);
            preparedStmt.setString(2, account_issue.username);
            preparedStmt.executeUpdate();

            this.connection().close();
        } catch (SQLException e) {
            System.out.println("Sql excption in " + column_name + "\n" + e);

        } finally {

            if (preparedStmt != null) {
                try {
                    preparedStmt.close();

                    if (this.connection() != null) {
                        this.connection().close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * setter for database
     */
    public String getFilename_appidphoto() {
        return this.filename_appidphoto;
    }

    public void setFilename_appidphoto(String filename_appidphoto) {
        this.send_string_to_db(filename_appidphoto, "filename_appidphoto");
        this.filename_appidphoto = filename_appidphoto;
    }

    public String getFilename_appcvfile() {
        return this.filename_appcvfile;
    }

    public void setFilename_appcvfile(String filename_appcvfile) {
        this.send_string_to_db(filename_appcvfile, "filename_appcvfile");
        this.filename_appcvfile = filename_appcvfile;
    }

    public String getFilename_apphealthfile() {
        return this.filename_apphealthfile;
    }

    public void setFilename_apphealthfile(String filename_apphealthfile) {
        this.send_string_to_db(filename_apphealthfile, "filename_apphealthfile");
        this.filename_apphealthfile = filename_apphealthfile;
    }

    public String getFilename_appenglishfile() {
        return this.filename_appenglishfile;
    }

    public void setFilename_appenglishfile(String filename_appenglishfile) {
        this.send_string_to_db(filename_appenglishfile, "filename_appenglishfile");
        this.filename_appenglishfile = filename_appenglishfile;
    }


    //main window and profile tab

    // we don't need to set username just once in the customer_database Constructor
    public String getUsername() {
        return account_issue.username;
    }


    public @Nullable char[] getPass() {


        PreparedStatement preparedStmt = null;

        try {
            preparedStmt = this.connection().prepareStatement("SELECT password FROM opr.customer where username=?");

            preparedStmt.setString(1, account_issue.username);
            ResultSet rst = preparedStmt.executeQuery();
            if (rst.next()) {
                return rst.getString("password").toCharArray();
            }
            this.connection().close();
        } catch (SQLException e) {
            System.out.println("passowrd " + e);

        } finally {

            if (preparedStmt != null) {
                try {
                    preparedStmt.close();

                    if (this.connection() != null) {
                        this.connection().close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    public void setPass(@NotNull char[] password) {


        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = this.connection().prepareStatement("update opr.customer set customer.password=? where customer.username=?");

            preparedStmt.setString(1, String.valueOf(password.clone()));
            preparedStmt.setString(2, account_issue.username);
            preparedStmt.executeUpdate();

            this.connection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public String getFname() {

        return this.fname;
    }

    public void setFname(String fname) {
        this.send_string_to_db(fname, "fname");
        this.fname = fname;
    }

    public String getMname() {
        return this.mname;
    }

    public void setMname(String mname) {
        this.send_string_to_db(mname, "mname");
        this.mname = mname;
    }

    public String getLname() {

        return this.lname;
    }

    public void setLname(String lname) {
        this.send_string_to_db(lname, "lname");
        this.lname = lname;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.send_string_to_db(mail, "e_mail");
        this.mail = mail;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.send_string_to_db(country, "country_name");
        this.country = country;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.send_string_to_db(phone, "phone");
        this.phone = phone;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(@NotNull String gender) {
        this.send_string_to_db(String.valueOf(gender.charAt(0)), "gender");
        this.gender = gender;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date brithdate) {

        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = this.connection().prepareStatement("update opr.customer set customer.birthday=? where customer.username=?");

            preparedStmt.setDate(1, brithdate);
            preparedStmt.setString(2, account_issue.username);
            preparedStmt.executeUpdate();
            this.connection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        birthday = brithdate;
    }


    // application tab

    public String getNational_id() {
        return this.nationalid;
    }

    /*public void setNational_id(String nationalid) {
        this.send_string_to_db(nationalid, "n_id");
        this.nationalid = nationalid;
    }*/


    public String getAdress() {
        return this.address;
    }

    public void setAdress(String address) {
        this.send_string_to_db(address, "address");
        this.address = address;
    }

    public String getJob() {
        return this.job;
    }

    public void setJob(String job) {
        this.send_string_to_db(job, "job");

        this.job = job;
    }

    public String getEducation() {
        return this.education;

    }

    public void setEducation(String education) {
        this.send_string_to_db(education, "Education");
        this.education = education;
    }


    public String getCheckbox_english() {
        return this.checkboxenglish;
    }

    public void setCheckbox_english(String checkboxenglish) {
        this.send_string_to_db(checkboxenglish, "Checkboxenglish");
        this.checkboxenglish = checkboxenglish;

    }


    // payment tab


    public String getPaypal_email() {
        return this.paypal_email;
    }

    public void setPaypal_email(String paypal_email) {
        this.send_string_to_db(paypal_email, "paypal_email");
        this.paypal_email = paypal_email;
    }

    public String getVisa_or_mastercard() {
        return this.visa_or_mastercard;
    }

    public void setVisa_or_mastercard(String visa_or_mastercard) {
        this.send_string_to_db(visa_or_mastercard, "visa_or_mastercard");

        this.visa_or_mastercard = visa_or_mastercard;
    }

    public String getCredit_card_number() {
        return this.credit_card_number;
    }

    public void setCredit_card_number(String credit_card_number) {
        this.send_string_to_db(credit_card_number, "credit_card_number");
        this.credit_card_number = credit_card_number;
    }

    public String getCredit_card_cvv() {
        return this.credit_card_cvv;
    }

    public void setCredit_card_cvv(String credit_card_cvv) {
        this.send_string_to_db(credit_card_cvv, "credit_card_cvv");
        this.credit_card_cvv = credit_card_cvv;
    }

    public String getBank_account_number() {
        return this.bank_account_number;
    }

    public void setBank_account_number(String bank_account_number) {
        this.send_string_to_db(bank_account_number, "bank_account_number");
        this.bank_account_number = bank_account_number;
    }

    public Boolean getUse_paypal() {
        return this.use_paypal;
    }

    public void setUse_paypal(Boolean use_paypal) {
        this.send_boolean_to_db(use_paypal, "paypal_email_b");
        this.use_paypal = use_paypal;
    }

    public Boolean getUse_credit_card() {
        return this.use_credit_card;
    }

    public void setUse_credit_card(Boolean use_credit_card) {
        this.send_boolean_to_db(use_credit_card, "use_credit_card_b");
        this.use_credit_card = use_credit_card;
    }

    public Boolean getUse_bank_account() {
        return this.use_bank_account;
    }

    public void setUse_bank_account(Boolean use_bank_account) {
        this.send_boolean_to_db(use_bank_account, "use_bank_account_b");
        this.use_bank_account = use_bank_account;

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
