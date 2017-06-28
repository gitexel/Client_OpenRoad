package Client_OpenRoad;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.net.PasswordAuthentication;
import java.sql.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class login extends inputs_validation {

    private db_use_info db_token = new db_use_info();
    private PasswordAuthentication PassAuth;
    private Executor exec;
    private Task<Boolean> validation_login_task;
    private @NotNull String error_message = "Incorrect Username or Passowrd!";
    private sign_up show_sign; //controller for Sign up
    private dash show_dash;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private Label error1;  // error
    @FXML
    private Group group1;
    @FXML
    private JFXButton signup;
    @FXML
    private JFXButton login1, minmize;
    @FXML
    private JFXButton close;
    @FXML
    private ProgressIndicator prograssindicator;

    public login() {
    }

    public void initialize() {
        exec = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });
    }

    private Connection connection() throws SQLException {

        if (db_token.every_things_going_ok()) {

            try {

                Class.forName(db_token.getDriver());

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return DriverManager.getConnection(db_token.getUrl() + "user=" + db_token.getUsername() + "&password=" + db_token.getPassword());

        } else return null;
    }

    private void loading_on() {
        error_message = "Incorrect Username or Passowrd!"; //reset error message
        prograssindicator.setVisible(true);
        group1.setDisable(true);
        error1.setText("Please Wait...");
    }

    private void loading_off(String message) {

        prograssindicator.setVisible(false);
        group1.setDisable(false);
        password.setText("");
        error1.setText(message);

    }


    @FXML
    private void blogin() { // login button Action


        if (!username.getText().isEmpty() && !password.getText().isEmpty()) {

            PassAuth = new PasswordAuthentication(username.getText().toLowerCase(), password.getText().toCharArray());

            if (username_v(PassAuth.getUserName())) {

                loading_on(); //check boolean form database {  Return true or false }

                validation_login_task = new Task<Boolean>() {
                    @Override
                    protected Boolean call() throws Exception {
                        return validate_login();
                    }
                };

                validation_login_task.setOnSucceeded(event -> {

                    if (validation_login_task.getValue()) {

                        Stage stage = (Stage) signup.getScene().getWindow();
                        stage.close();
                        new dash(PassAuth.getUserName()).spark_dash();

                    } else {

                        loading_off(error_message);

                    }

                });

                exec.execute(validation_login_task);

            } else {
                error1.setText("Invalid inputs !");
            }
        } else error1.setText("Invalid inputs !");


    }

    private boolean validate_login() throws SQLException { //Check username and password


        PreparedStatement pst = null;
        try {
            pst = connection().prepareStatement("Select opr.customer.username from opr.customer where username=? and password=?");
        } catch (Exception e) {
            error_message = "Faild to connect to the Server";
            System.out.println(e);
            return false;
        }
        pst.setString(1, PassAuth.getUserName());
        pst.setString(2, String.valueOf(PassAuth.getPassword()));
        ResultSet rs = pst.executeQuery();
        connection().close();

        return rs.next();
    }

    @FXML
    private void bsign() {

        // signup button Action
        Task<Void> sign_up_task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                Thread.sleep(500);
                return null;
            }
        };
        sign_up_task.setOnSucceeded(event -> {

            Stage stage = (Stage) signup.getScene().getWindow();
            stage.close();
            new sign_up().open_sing_up();

        });

        exec.execute(sign_up_task);


    }

    @FXML
    private void minimize() {

        Stage stage = (Stage) minmize.getScene().getWindow();
        stage.setIconified(true);

    }

    @FXML
    private void exit() {

        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
        System.exit(1);

    }

}
