package Client_OpenRoad;

import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.fontawesome.AwesomeIcon;
import de.jensd.fx.fontawesome.Icon;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by salem on 13/10/16.
 */

public class sign_up extends inputs_validation {


    private ArrayList warning_list;
    private final @NotNull Icon warning = new Icon(AwesomeIcon.WARNING, "1em", ";", "error");
    private boolean complete;
    private final account_database account_database = new account_database();
    private final String icon_url ="file:"+System.getProperty("user.dir")+ File.separator+"src"+File.separator+"icon.png";


    @FXML
    private JFXPasswordField password1;
    @FXML
    private JFXPasswordField password2;
    @FXML
    private JFXTextField firstname, lastname, openRid, email1, phone1;
    @FXML
    private JFXComboBox<String> cb;
    @FXML
    private JFXButton confirm;
    @FXML
    private JFXDatePicker datePicker = new JFXDatePicker();
    @FXML
    private GridPane gridpanein;
    @FXML
    private ProgressIndicator prograssind;
    @FXML
    private StackPane stackPane;

    private Executor exec;

    public sign_up() {

    }

    private @NotNull RequiredFieldValidator required() {

        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setIcon(warning);
        validator.setMessage("Required Field");
        validator.setAccessibleHelp("fill it");
        return validator;
    }

    private void loadingon() {

        prograssind.setVisible(true);
        gridpanein.setDisable(true);
    }

    private void loadingoff() {
        prograssind.setVisible(false);
        gridpanein.setDisable(false);

    }

    public void initialize() {

        exec = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });

        Task<ArrayList> get_country_db_task = new Task<ArrayList>() {
            @Override
            protected ArrayList call() throws Exception {
                loadingon();
                return account_database.get_country_list();
            }
        };

        get_country_db_task.setOnSucceeded(event -> {

            loadingoff();

            if (!get_country_db_task.getValue().isEmpty()) {

                cb.getItems().addAll(get_country_db_task.getValue());

            } else {
                warning_dialog("Warning", "The service not avilable now!, please try again later.", false);
            }

        });

        get_country_db_task.setOnFailed(event -> {

            warning_dialog("Warning", "Faild to connect to the server!, please try again later.", false);
            loadingoff();

        });
        exec.execute(get_country_db_task);


        firstname.getValidators().add(required());
        lastname.getValidators().add(required());
        email1.getValidators().add(required());
        openRid.getValidators().add(required());
        password1.getValidators().add(required());
        password2.getValidators().add(required());


        firstname.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) firstname.validate();
        });


        lastname.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) lastname.validate();
        });


        email1.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) email1.validate();
        });


        openRid.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) openRid.validate();
        });

        password1.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) password1.validate();
        });

        password2.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) password2.validate();
        });

    }

    protected void open_sing_up() {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("sign_up.fxml"));
            Stage stage = new Stage();
            stage.getIcons().add(new Image(icon_url));
            stage.setTitle("Sign up");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e2) {
        }
    }

    private Boolean isComplete() {

        warning_list = new ArrayList();


        if (!firstname.getText().isEmpty()) {

            if (name_v(firstname.getText())) {


            } else {

                warning_list.add("Invalid First Name, try with letters only");
            }

        } else {

            warning_list.add("First name required");
        }


        if (!lastname.getText().isEmpty()) {

            if (name_v(lastname.getText())) {

            } else {


                warning_list.add("Invalid Last Name, try with letters only");
            }

        } else {

            warning_list.add("Last name required");
        }

        if (!email1.getText().isEmpty()) {

            if (e_mail_v(email1.getText())) {

                try {
                    if (db_email_v(email1.getText())) {
                        warning_list.add("E-mail already exist, try with another one");
                    }
                } catch (SQLException e) {
                    warning_list.clear();
                    return false;

                }
            } else {

                warning_list.add("Invalid E-mail type, Hint: name@domain.com");
            }

        } else {

            warning_list.add("E-mail required");
        }


        if (!openRid.getText().isEmpty()) {

            if (username_v(openRid.getText())) {

                try {

                    if (db_username_v(openRid.getText())) {

                        warning_list.add("username already exist, try with another one");
                    }

                } catch (SQLException e) {
                    warning_list.clear();// connecton faild ----> to show connection error not the fileds errors
                    return false;
                }
            } else {
                warning_list.add("username not valid, try with another one");
            }

        } else {

            warning_list.add("Username required");
        }


        if (datePicker.getValue() == null) {
            warning_list.add("Birthday required ");
        }


        if (cb.getValue() == null) {
            warning_list.add("Country required");
        }


        if (!(password1.getText().isEmpty() && password2.getText().isEmpty())) {

            if (password_v(password1.getText().toCharArray()) && password_v(password2.getText().toCharArray())) {

                if (!Arrays.equals(password1.getText().toCharArray(), password2.getText().toCharArray())) {
                    warning_list.add("The two passwords not the same");
                }
            } else {

                warning_list.add("Password not valid, try like Openr6!");
            }

        } else {

            warning_list.add("Password required");
        }

        return check_warning();
    }


    @FXML
    private void next() { //send Sign up information to database"Forms"


        Task<Boolean> isComplete_task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                loadingon();
                return isComplete();
            }
        };

        isComplete_task.setOnSucceeded(e1 -> {

            if (isComplete_task.getValue()) {

                account_database create = new account_database(

                        firstname.getText(),
                        lastname.getText(),
                        openRid.getText().toLowerCase(),
                        email1.getText(),
                        password2.getText().toCharArray(),
                        datePicker.getValue(),
                        cb.getValue()

                );

                Task<Boolean> create_new_customer_account_task = new Task<Boolean>() {
                    @Override
                    protected Boolean call() throws Exception {
                        return create.new_customer_account();
                    }
                };
                create_new_customer_account_task.setOnSucceeded(e2 -> {
                    System.out.println("sucssed ---------------");
                    if (create_new_customer_account_task.getValue()) {

                        loadingoff();
                        Stage stage = (Stage) confirm.getScene().getWindow();
                        stage.close();
                        new dash(openRid.getText().toLowerCase()).spark_dash();

                    } else {
                        warning_dialog("Warning!", "Failed to create a new account, Check your connection", false);

                    }
                    loadingoff();
                });
                create_new_customer_account_task.setOnFailed(event -> {
                    this.warning_dialog("Warning", "Faild to connect to the server!, please try again later.", false);

                    this.loadingoff();
                });
                exec.execute(create_new_customer_account_task);


            } else {
                loadingoff();
                if (warning_list.isEmpty()) {
                    warning_dialog("Warning!", "Faild to connect to the server! else", false);
                }
            }
        });

        isComplete_task.setOnFailed(event -> warning_dialog("Warning!", "Faild to connect to the server!", false));
        exec.execute(isComplete_task);


    }

    private boolean check_warning() {


        if (!warning_list.isEmpty()) {

            Task<Void> check_warning_task = new Task<Void>() {

                @Override
                protected Void call() throws Exception {

                    Thread.sleep(500);
                    return null;
                }
            };
            check_warning_task.setOnSucceeded(event -> warning_dialog("Warning!", "Invalid Inputs", true));
            exec.execute(check_warning_task);

            return false;
        }
        return true;
    }

    private void warning_dialog(String title, String message, Boolean use_warrning_list) {

        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        jfxDialogLayout.setHeading(new Text(title));

        if (use_warrning_list) {

            JFXListView jfxListView = new JFXListView();
            jfxListView.getItems().addAll(warning_list);
            jfxDialogLayout.setBody(new Text(message), jfxListView);

        } else {

            jfxDialogLayout.setBody(new Text(message));

        }

        JFXDialog Dialog = new JFXDialog(stackPane, jfxDialogLayout, JFXDialog.DialogTransition.CENTER);
        JFXButton confirm_button = new JFXButton("OK");
        confirm_button.setOnAction(event1 -> Dialog.close());
        jfxDialogLayout.setActions(confirm_button);
        Dialog.show();
    }


}


