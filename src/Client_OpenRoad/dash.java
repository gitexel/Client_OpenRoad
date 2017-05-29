package Client_OpenRoad;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.fontawesome.AwesomeIcon;
import de.jensd.fx.fontawesome.Icon;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by salem on 10/26/16.
 */

public class dash extends inputs_validation {




    //objects
    private @NotNull customer_database db = new customer_database();
    private final trips_db trip_data = new trips_db();
    private final customer_validation_time customer_validation_time_object = new customer_validation_time();
    private final citizen_validation citizen_v = new citizen_validation();
    private final files2db messenger = new files2db();

    private final char separator = File.separatorChar;

    @FXML
    private StackPane stackPane;

    //main gui
    @FXML
    private JFXButton open_profile_picture;
    @FXML
    private ImageView main_profile_image;
    @FXML
    private ImageView profile_image;
    @FXML
    private Label main_id;
    @FXML
    private Label main_email;
    @FXML
    private Label main_country;
    @FXML
    private Label main_phone;

    //Home GUI

    @FXML
    private JFXCheckBox status_complete;
    @FXML
    private JFXCheckBox status_verified;
    @FXML
    private JFXCheckBox procom;
    @FXML
    private JFXCheckBox prover;
    @FXML
    private JFXCheckBox appcom;
    @FXML
    private JFXCheckBox appver;

    // Complete and validation  corner
    @FXML
    private JFXCheckBox paycom;
    @FXML
    private JFXCheckBox payver;
    @FXML
    private JFXTreeTableView<customer_validation_time> requests_table;
    @FXML
    private TreeTableColumn<customer_validation_time, Timestamp> requests_column_date;
    @FXML
    private TreeTableColumn<customer_validation_time, String> requests_column_state;
    @FXML
    private TreeTableColumn<customer_validation_time, Timestamp> requests_column_valid_until;
    @FXML
    private JFXTreeTableView<trips> stuffTableView1;
    @FXML
    private TreeTableColumn<trips, Integer> trip_id_1;
    @FXML
    private TreeTableColumn<trips, Timestamp> trips_date_column1;
    @FXML
    private TreeTableColumn<trips, String> flight_duration;
    @FXML
    private TreeTableColumn<trips, String> airport_name_from_Column1;
    @FXML
    private TreeTableColumn<trips, String> airport_name_to_Column1;
    @FXML
    private TreeTableColumn<trips, String> trip_type_Column1;
    @FXML
    private TreeTableColumn<trips, Double> price_column1;


    @FXML
    private Label password_label;
    // ComboBox items
    @FXML
    private JFXButton check_id;
    //Profile Country
    @FXML
    private Label id_state;

    //Application English certificate kind
    @FXML
    private JFXTextField national_id;
    //payment visa or mastercard
    @FXML
    private JFXTextField fname, mname, lname, email, phone, jfx_username;
    @FXML
    private JFXDatePicker datePicker;
    @FXML
    private JFXComboBox<String> gender;
    @FXML
    private JFXComboBox<String> country;
    @FXML
    private JFXButton change_profile_image;
    @FXML
    private JFXButton psave;
    @FXML
    private JFXButton new_password_save;

    @FXML
    private JFXToggleButton toggleButton_password_enable;
    @FXML
    private JFXPasswordField old_password;
    @FXML
    private JFXPasswordField new_password;
    @FXML
    private JFXPasswordField new_password_retype;
    @FXML
    private JFXButton idphoto;
    @FXML
    private JFXButton cv;
    @FXML
    private JFXButton healhcert;
    @FXML
    private JFXButton entest;
    @FXML
    private JFXButton appsave;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField job;
    @FXML
    private JFXTextField edu;

    @FXML
    private JFXComboBox<String> venglish;
    @FXML
    private Label idphotfilename;
    @FXML
    private Label cvfilename;
    @FXML
    private Label healthfilename;
    @FXML
    private Label englishprovefilename;
    // gui trips  tap
    @FXML
    private JFXComboBox<String> trips_country_from;
    @FXML
    private JFXComboBox<String> trips_country_to;
    @FXML
    private JFXDatePicker trips_departure;
    @FXML
    private JFXDatePicker trips_arrival;
    @FXML
    private JFXButton search_trips;
    @FXML
    private JFXButton buy_trip;
    @FXML
    private JFXTextField trip_details;
    @FXML
    private JFXTreeTableView<trips> stuffTableView;

    @FXML
    private TreeTableColumn<trips, Integer> trip_id;
    @FXML
    private TreeTableColumn<trips, String> trip_duration_column;
    @FXML
    private TreeTableColumn<trips, String> airport_name_from_Column;
    @FXML
    private TreeTableColumn<trips, String> airport_name_to_Column;
    @FXML
    private TreeTableColumn<trips, String> trip_type_Column;
    @FXML
    private TreeTableColumn<trips, Double> price_column;
    // Payment tab
    @FXML
    private JFXTextField paypal_account_email;
    @FXML
    private JFXTextField credit_card_number;
    @FXML
    private JFXTextField credit_card_cvv;
    @FXML
    private JFXTextField Bank_account_number;
    @FXML
    private JFXToggleButton use_paypal;
    @FXML
    private JFXToggleButton use_credit_card;
    @FXML
    private JFXToggleButton use_bank_account;

    //Profile GUI
    @FXML
    private JFXComboBox<String> visa_or_mastercard;
    @FXML
    private JFXButton save_payment;

    //main gui

    @FXML
    private GridPane gridPaneleft;
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private Tab tabhome;
    @FXML
    private Tab tabprofile;
    @FXML
    private Tab tabapplication;
    @FXML
    private Tab tabtrips;
    @FXML
    private Tab tabpayment;
    @FXML
    private Tab tababoutus;

    // for ProgressIndicator
    @FXML
    private ProgressIndicator home_progress;
    @FXML
    private ProgressIndicator profile_progress;
    @FXML
    private ProgressIndicator trip_progress;
    @FXML
    private ProgressIndicator appliction_progress;
    @FXML
    private ProgressIndicator payment_progress;

    //gridpane
    @FXML
    private GridPane main_window_gridpane;
    @FXML
    private GridPane home_gridpane;
    @FXML
    private GridPane trip_gridpane;
    @FXML
    private GridPane profile_gridpane;
    @FXML
    private GridPane all_profile_gridpane;
    @FXML
    private GridPane application_gridpane;
    @FXML
    private GridPane payment_gridpane;


    private @NotNull FileChooser fileC = new FileChooser();
    private final Stage stage = new Stage();
    private RequiredFieldValidator validator;
    private final Icon warning = new Icon(AwesomeIcon.WARNING, "1em", ";", "error");
    private String filename;
    private boolean series; // prevent set null name_file
    private ArrayList warning_list, country_list;

    //check if the customer'separator national jfx_username and his info correct or not
    private boolean verified;

    private @NotNull String profile_path = "", id_path = "", cv_path = "", health_path = "", english_path = "";
    //profile //textfield change boolean
    private boolean ch_fname, ch_mname, ch_lname, ch_email, ch_phone, ch_gender, ch_birthday,
            ch_country;
    private Executor exec; // java.util.concurrent.Executor typically provides a pool of threads...
    private Task<Boolean> valid_citizen_task = null;
    private Task<Void> update_profile_task, update_application_task, update_payment_task;
    private final String icon_url ="file:"+System.getProperty("user.dir")+separator+"src"+separator+"icon.png";

    public dash() {

    }

    public dash(String username) {

        db = new customer_database(username);

    }

    public void initialize() {
        fname.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, @NotNull String oldValue, String newValue) {
                if (!oldValue.isEmpty()) {
                    ch_fname = true;
                    psave.setDisable(false);
                }
            }
        });
        mname.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, @NotNull String oldValue, String newValue) {
                if (!oldValue.isEmpty()) {
                    ch_mname = true;
                    psave.setDisable(false);
                }
            }
        });
        lname.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, @NotNull String oldValue, String newValue) {

                if (!oldValue.isEmpty()) {
                    ch_lname = true;
                    psave.setDisable(false);
                }
            }
        });
        email.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, @NotNull String oldValue, String newValue) {

                if (!oldValue.isEmpty()) {
                    ch_email = true;
                    psave.setDisable(false);
                }
            }
        });
        gender.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (oldValue != null) {
                    ch_gender = true;
                    psave.setDisable(false);
                    System.out.println(gender.getValue());

                }

            }
        });

        phone.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(@NotNull ObservableValue<? extends String> observable, @NotNull String oldValue, String newValue) {
                if (!oldValue.isEmpty()) {
                    ch_phone = true;
                    psave.setDisable(false);
                }

            }
        });
        country.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, @Nullable String oldValue, String newValue) {
                if (oldValue != null) {
                    ch_country = true;
                    psave.setDisable(false);
                }

            }
        });
        datePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, @Nullable LocalDate oldValue, LocalDate newValue) {
                if (oldValue != null) {
                    ch_birthday = true;
                    psave.setDisable(false);
                }
            }
        });
        // create executor that uses daemon threads:
        exec = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });

        warning_dialog("Welcome " + db.getUsername(), "\nPlease read these notes carefully!\n" +
                "\n  1.Validation for National ID required (first, middle and last name),\n" +
                "    country, gender and birthday will marked as valid from the database only.]\n" +
                "2. to book trips you should fill application and payment(only one method) tab.\n" +
                "3. The payment tab is just a prototype!, but you need to fill just one method.\n" +
                "4. you will fill application only once until your validation status expired.\n" +
                "5. application and payments inputs should be marked as valid from employer side!.\n" +
                "    and we don't have yet employer side app so validation for inputs will \n be automatically marked as valid!.\n" +
                "6. you can change your profile photo after you got verified.", false);


        Task<ArrayList> get_country_db_task = new Task<ArrayList>() {
            @Override
            protected ArrayList call() throws Exception {
                // start_profile_task_action();
                return db.get_country_list();
            }
        };
        get_country_db_task.setOnSucceeded(event -> {

            //    finish_profile_task_action();

            if (!get_country_db_task.getValue().isEmpty()) {

                this.country_list = get_country_db_task.getValue();
                //   Combobox Items
                Combo_country(country_list);
                //trips
                Combo_from_country(country_list);
                Combo_to_country(country_list);
            } else {
                warning_dialog("Warning", "The service not avilable now!, please try again later.", false);
            }

        });

        get_country_db_task.setOnFailed(event -> {
            //    finish_profile_task_action();
            warning_dialog("Warning", "Faild to connect to the server!, please try again later.", false);


        });
        exec.execute(get_country_db_task);
        try {

            // add Combobox Items
            Combo_gender();
            Combo_english();
            Combo_visa();


        } catch (Exception e) {
            System.out.println(e + "problem private");
        }


        try {
            update_main_window();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            update_profile_tab();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            update_application_tab();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            update_payment_tab();
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {

            /**
             * it will be update automatically
             * if customer book new trips with method action
             * buy button in trip tab
             */

            Task<Boolean> find_flights_history_task = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    return trip_data.find_flights_history(db.getUsername());
                }
            };
            find_flights_history_task.setOnSucceeded(event -> {
                if (find_flights_history_task.getValue()) {

                    trips_initialization_history(trip_data.getFlights_history());
                }
            });

            exec.execute(find_flights_history_task);
        } catch (Exception lol) {
            System.out.println(" trips history not working " + lol);
        }


        national_id.focusedProperty().addListener((o, old, newVal) -> {

            if (!newVal) {

                national_id.getValidators().add(required("Required for authentication", warning));

                if (national_id.validate()) {

                    national_id.setFocusColor(Color.GREEN);
                    national_id.setUnFocusColor(Color.GREEN);


                } else {

                    national_id.setFocusColor(Color.RED);
                    national_id.setUnFocusColor(Color.RED);

                }
            }
        });

        //get all details from database


        //Detect input_changed elemnts

    }

    private RequiredFieldValidator required(String message, @NotNull Icon icon) {

        validator = new RequiredFieldValidator();
        validator.setMessage(message);
        validator.setIcon(icon);

        return validator;
    }

    /**
     * spark dash
     * First method running on the class
     * it'separator load dash.fxml file
     * opening dashboard window
     */
    protected void spark_dash() {

        try {

            Stage stage1 = new Stage();
            Parent node = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            stage1.getIcons().add(new Image(icon_url));
            stage1.setTitle("Dashboard");
            //stage1.initStyle(StageStyle.DECORATED);
            stage1.setScene(new Scene(node));
            stage1.show();

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    /**
     * Custom methods
     * file_chooser
     * Image_buffer_view
     */

    //main file_chooser
    private @NotNull String file_chooser(String title, FileChooser.ExtensionFilter extensions) {

        String file_path = new String();
        fileC = new FileChooser();
        fileC.setTitle(title);
        fileC.getExtensionFilters().addAll(extensions);
        File selectedFile = fileC.showOpenDialog(stage);
        series = false; // if a file selected will be true
        if (selectedFile != null) {

            file_path = selectedFile.getPath(); // file path
            filename = selectedFile.getName(); // file name
            series = true;

        } else {

            file_path = "";

        }

        return file_path;
    }

    /**
     * 5 methods for File chooser
     */

    // profile photo -- profile tab
    @FXML
    private void profile_photo() {


        //save Image to database
        try {

            profile_path = file_chooser("Choose Picture",
                    new FileChooser.ExtensionFilter("Image file", "*.png"));
            if (series) {
                profile_image.setImage(new Image("file://" + profile_path, true));
                psave.setDisable(false);
            }
        } catch (Exception photoE) {
            System.out.println(photoE);
        }
        //   main_profile_image.setImage(image_buffer_view());


    }

    //1-for ID photo button, only Images files
    @FXML
    private void open_photo_id() {

        id_path = file_chooser("Choose Id-Image", new FileChooser.ExtensionFilter("Image file", "*.png", "*.jpg", "*.gif"));

        if (series) {
            idphotfilename.setText(filename);
        }
    }

    //2 for cv button, files
    @FXML
    private void open_cv() {

       /* inputcv = */
        cv_path = file_chooser("Choose file",
                new FileChooser.ExtensionFilter("pdf, docx", "*.pdf", "*.docx"));
        if (series) {
            cvfilename.setText(filename);
        }
    }

    //3-for health prove button, files
    @FXML
    private void open_health() {

      /*  inputhealth = */
        health_path = file_chooser("Choose file",
                new FileChooser.ExtensionFilter("images and pdf", "*.png", "*.jpg", "*.gif", "*.pdf"));
        if (series) {
            healthfilename.setText(filename);
        }
    }

    //4-for english prove button, files
    @FXML
    private void open_english() {

      /*  inputenglish = */
        english_path = file_chooser("Choose file",
                new FileChooser.ExtensionFilter("png,jpg,gif,pdf", "*.png", "*.jpg", "*.gif", "*.pdf"));
        if (series) {
            englishprovefilename.setText(filename);
        }
    }


    /**
     * These methods for getting customer information from database.
     */

    //get customer'separator information from DataBase
    private void update_main_window() {


        Task<Void> update_main_window_task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                start_home_task_action();
                db.update_main_db();
                return null;
            }
        };

        update_main_window_task.setOnSucceeded(event -> {

                    update_main_window_UI();

                }

        );

        update_main_window_task.setOnFailed(event ->
                finish_home_task_action());


        exec.execute(update_main_window_task);


    }

    private void update_main_window_UI() {

        try {

            try {


                Task<Boolean> get_profile_photo_boolean_task = new Task<Boolean>() {

                    @Override
                    protected Boolean call() throws Exception {
                        start_home_task_action();
                        return messenger.get_profile_photo();

                    }
                };

                get_profile_photo_boolean_task.setOnSucceeded(event -> {

                    if (get_profile_photo_boolean_task.getValue()) {

                        try {

                            main_profile_image.setImage(new Image("file:"+ System.getProperty("user.home") + separator +".openroad_files"+ separator + db.getUsername() + "profile.png", true));

                        } catch (Exception e) {
                        }

                        try {
                            profile_image.setImage(new Image("file:"+ System.getProperty("user.home") + separator +".openroad_files" + separator + db.getUsername() + "profile.png", true));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else {
                       /* try {

                            main_profile_image.setImage(new Image("file:"+separator+separator+ System.getProperty("user.dir") +separator+"src"+separator+"Client_OpenRoad"+separator+"resources"+separator+"default-profile.png", true));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {

                            profile_image.setImage(new Image("file:"+separator+separator+ System.getProperty("user.dir") +separator+"src"+separator+"Client_OpenRoad"+separator+"resources"+separator+"default-profile.png", true));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
*/
                    }

                    finish_home_task_action();

                });

                get_profile_photo_boolean_task.setOnFailed(event -> {

                    finish_home_task_action();
                    warning_dialog("Connecton error", "Failed to connect to the server!, please try again later", false);

                });

                exec.execute(get_profile_photo_boolean_task);

            } catch (Exception e) {
                e.printStackTrace();
            }


            try {
                open_profile_picture.setText(db.getFname() + " " + db.getLname());
            } catch (Exception e) {
                System.out.println(db.getFname() + " " + db.getLname());
            }
            try {
                main_id.setText(db.getUsername());
            } catch (Exception e) {
                System.out.println(db.getUsername());
            }
            try {
                main_country.setText(db.getCountry());
            } catch (Exception e) {
                System.out.println("error 3");
            }
            try {
                main_email.setText(db.getMail());
            } catch (Exception e) {
                System.out.println("error 4");
            }
            if (!(db.getPhone() == null)) {
                main_phone.setText(db.getPhone());
            }
            //main_profile_image.setImage(new Image(image_url, true));


        } catch (Exception u_mainE) {
            System.out.println(u_mainE + " ---> update_main_window ");
        }
    }

    //get profile info form database
    private void update_profile_tab() {

        update_profile_task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                start_profile_task_action();
                db.update_profile_db();
                return null;
            }
        };

        update_profile_task.setOnSucceeded(event -> {
                    Task<Void> citizen_customer_status_task = new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {

                            citizen_customer_status();


                            return null;
                        }
                    };

                    citizen_customer_status_task.setOnSucceeded(event2 -> {

                        update_profile_UI();
                        complete_validation_UI();


                    });

                    citizen_customer_status_task.setOnFailed(event2 -> {
                        warning_dialog("Connection Error", "Faild to connect to the server", false);
                        finish_profile_task_action();
                    });
                    exec.execute(citizen_customer_status_task);

                }

        );
        update_profile_task.setOnFailed(event -> {
            finish_profile_task_action();
            warning_dialog("Connecton error - update profile tab", "Faild to connect to the server!, please try again later", false);

        });
        exec.execute(update_profile_task);
    }

    private void update_profile_UI() {

        try {

            try {
                if (!db.getFname().isEmpty()) {
                    fname.setText(db.getFname());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if ((db.getMname() != null)) {
                    mname.setText(db.getMname());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (!db.getLname().isEmpty()) {
                    lname.setText(db.getLname());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if ((db.getGender() != null)) {

                    if (db.getGender().equals("M")) {

                        gender.setValue("Male");

                    } else if (db.getGender().equals("F")) {
                        gender.setValue("Female");
                    } else {
                        gender.setValue("Other");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if ((db.getBirthday() != null)) {
                    datePicker.setValue(LocalDate.parse(String.valueOf(db.getBirthday())));
                }

            } catch (Exception e) {
            }
            try {
                jfx_username.setText(db.getUsername());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                email.setText(db.getMail());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {

                if (db.getCountry() != null) {
                    country.setValue(db.getCountry());
                    country.setPromptText(db.getCountry());
                }
            } catch (NullPointerException e) {

            }
            try {
                if ((db.getPhone() != null)) {
                    phone.setText(db.getPhone());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception U_profileE) {
            System.out.println(U_profileE + " ----> Update_profile_tab");
        }
    }

    //get application info
    private void update_application_tab() {


        update_application_task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                start_application_task_action();
                db.update_application_db();
                return null;
            }
        };

        update_application_task.setOnSucceeded(event -> {

                    update_application_UI();
                    complete_validation_UI();
                    finish_application_task_action();
                }

        );
        update_application_task.setOnFailed(event -> {

            finish_application_task_action();
            warning_dialog("Connecton error - update application", "Faild to connect to the server!, please try again later", false);

        });

        exec.execute(update_application_task);

    }

    private void update_application_UI() {
        try {

            try {
                if ((db.getAdress() != null)) {
                    address.setText(db.getAdress());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if ((db.getJob() != null)) {
                    job.setText(db.getJob());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if ((db.getEducation() != null)) {
                    edu.setText(db.getEducation());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if ((db.getFilename_appidphoto() != null)) {
                    idphotfilename.setText(db.getFilename_appidphoto());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if ((db.getFilename_appcvfile() != null)) {
                    cvfilename.setText(db.getFilename_appcvfile());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if ((db.getFilename_apphealthfile() != null)) {
                    healthfilename.setText(db.getFilename_apphealthfile());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if ((db.getFilename_appenglishfile() != null)) {
                    entest.setDisable(false);  // for method english_check_to_enable_upload();
                    englishprovefilename.setText(db.getFilename_appenglishfile());
                    venglish.setValue(db.getCheckbox_english());
                    venglish.setPromptText(db.getCheckbox_english());
                }
            } catch (Exception e) {
            }

        } catch (Exception U_applicationE) {
            System.out.println(U_applicationE + " ----> Update_application_tab");
        }
    }

    //get payment info
    private void update_payment_tab() {


        update_payment_task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                start_payment_task_action();
                db.update_payment_db();
                return null;
            }
        };

        update_payment_task.setOnSucceeded(event -> {

                    update_payment_UI();
                    complete_validation_UI();
                    finish_payment_task_action();
                }

        );

        update_payment_task.setOnFailed(event -> {
            finish_payment_task_action();
            warning_dialog("Connecton error = update payment tab", "Faild to connect to the server!, please try again later", false);

        });

        exec.execute(update_payment_task);

    }

    private void update_payment_UI() {
        try {

            try {
                if (db.getUse_credit_card()) {
                    use_credit_card.setSelected(true);
                    credit_card_number.setDisable(false);
                    credit_card_cvv.setDisable(false);
                    visa_or_mastercard.setDisable(false);
                    credit_card_number.setText(db.getCredit_card_number());
                    visa_or_mastercard.setValue(db.getVisa_or_mastercard());
                    visa_or_mastercard.setPromptText(db.getVisa_or_mastercard());
                    credit_card_cvv.setText(db.getCredit_card_cvv());
                } else {
                    use_credit_card.setSelected(false);
                    credit_card_number.setDisable(true);
                    credit_card_cvv.setDisable(true);
                    visa_or_mastercard.setDisable(true);
                    try {
                        credit_card_number.setText(db.getCredit_card_number());
                        visa_or_mastercard.setValue(db.getVisa_or_mastercard());
                        credit_card_cvv.setText(db.getCredit_card_cvv());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (db.getUse_paypal()) {
                    use_paypal.setSelected(true);
                    paypal_account_email.setDisable(false);
                    paypal_account_email.setText(db.getPaypal_email());
                } else {
                    use_paypal.setSelected(false);
                    paypal_account_email.setDisable(true);
                    try {
                        paypal_account_email.setText(db.getPaypal_email());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (db.getUse_bank_account()) {
                    use_bank_account.setSelected(true);
                    Bank_account_number.setDisable(false);
                    Bank_account_number.setText(db.getBank_account_number());
                } else {
                    use_bank_account.setSelected(false);
                    Bank_account_number.setDisable(true);
                    try {
                        Bank_account_number.setText(db.getBank_account_number());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception U_paymentE) {
            System.out.println(U_paymentE + "------> payment update");
        }
    }

    /**
     * Methods for sending information to database
     */

    private void send_profile_info() {

        warning_list = new ArrayList();

        try {

            if ((profile_path != "")) {
                if (!messenger.file2db(profile_path, 1)) {
                    warning_list.add("Picture not saved!");
                }

            }

            if ((!fname.getText().isEmpty())) {
                if (ch_fname) {
                    if (name_v(fname.getText())) {

                        db.setFname(fname.getText());

                    } else {
                        warning_list.add("Invalid First Name, try with letters only");

                    }
                }
            } else {
                warning_list.add("First Name, Required to verify your account");
            }

            if (!mname.getText().isEmpty()) {
                if (ch_mname) {
                    if (name_v(mname.getText())) {

                        db.setMname(mname.getText());

                    } else {
                        warning_list.add("Invalid Middle Name, try with letters only");

                    }
                }
            } else {
                warning_list.add("Middle Name, Required to verify your account");
            }
            if (!lname.getText().isEmpty()) {
                if (ch_lname) {
                    if (name_v(lname.getText())) {

                        db.setLname(lname.getText());

                    } else {
                        warning_list.add("Invalid Last Name, try with letters only");
                    }
                }
            } else {
                warning_list.add("Last Name Name, Required to verify your account");
            }

            try {
                if (gender.getValue() != null) {
                    if (ch_gender) {
                        db.setGender(gender.getValue());
                    }
                } else {
                    warning_list.add("your gender, Required to verify your account");
                }
            } catch (NullPointerException e) {
                warning_list.add("your gender, Required to verify your account");
            }

            try {
                if (datePicker.getValue() != null) {
                    if (ch_birthday) {
                        db.setBirthday(Date.valueOf(datePicker.getValue()));
                    }
                }
            } catch (NullPointerException e) {
                warning_list.add("birth date, Required to verify your account");
            }

            try {
                if (!email.getText().isEmpty()) {
                    if (ch_email) {

                        if (e_mail_v(email.getText())) {

                            if (!db_email_v(email.getText())) {

                                db.setMail(email.getText());

                            } else {

                                warning_list.add("E-mail already exist, try with another one");
                            }
                        }
                    }
                } else {
                    warning_list.add("Invalid E-mail tray, try with another one, name@domain.com");


                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (country.getValue() != null) {
                    if (ch_country) {
                        db.setCountry(country.getValue());
                    }
                } else {
                    warning_list.add("Country ,Required to verify try with another one");
                }
            } catch (NullPointerException e) {
                warning_list.add("Country ,Required to verify try with another one");
            }

            try {
                if (!phone.getText().isEmpty()) {
                    if (ch_phone) {

                        if (phone_v(phone.getText())) {

                            db.setPhone(phone.getText());

                        } else {
                            warning_list.add("Invalid Phone number");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch (Exception profileE) {
            System.out.println(profileE);
        }

        if (warning_list.isEmpty() && !verified && !national_id.getText().isEmpty()) {

            valid_citizen_task = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    return citizen_v.valid_citizen(jfx_username.getText(), national_id.getText(),

                            fname.getText(), mname.getText(),
                            lname.getText(), country.getValue(),
                            gender.getValue(), datePicker.getValue());

                }
            };

            valid_citizen_task.setOnSucceeded(event -> {

                if (valid_citizen_task.getValue()) {


                    warning_dialog("Congratulation " + db.getUsername(), "You are now Verified!", false);


                }

            });
            //exec.execute(valid_citizen_task);
            Thread thread = new Thread(valid_citizen_task);
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // verifying with username

        }


        check_warning();
        reset_change_profile();
    }

    private void send_application_info() {

        warning_list = new ArrayList();

        if (id_path != "") {

            if (!idphotfilename.getText().isEmpty()) {
                if (messenger.file2db(id_path, 2)) {

                    if (file_name_v(idphotfilename.getText())) {
                        db.setFilename_appidphoto(idphotfilename.getText());
                    }
                } else {
                    idphotfilename.setText("");
                    warning_list.add("id_file not saved!");
                }

            } else warning_list.add("ID image-copy required");

        }

        if (cv_path != "") {


            if (!cvfilename.getText().isEmpty()) {
                if (messenger.file2db(cv_path, 3)) {
                    if (file_name_v(cvfilename.getText())) {

                        db.setFilename_appcvfile(cvfilename.getText());
                    }
                } else {
                    cvfilename.setText("");
                    warning_list.add("C.V file not saved!");
                }
            } else warning_list.add("C.V. required");


        }

        if (health_path != "") {


            if (!healthfilename.getText().isEmpty()) {
                if (messenger.file2db(health_path, 4)) {
                    if (file_name_v(healthfilename.getText())) {
                        db.setFilename_apphealthfile(healthfilename.getText());
                    }
                } else {
                    healthfilename.setText("");
                    warning_list.add("health certificate file!");
                }
            } else warning_list.add("Health insurance image-copy required ");

        }

        if (english_path != "") {


            if (venglish.getValue() != null) {


                if (!englishprovefilename.getText().isEmpty()) {
                    if (messenger.file2db(english_path, 5)) {
                        if (file_name_v(englishprovefilename.getText())) {
                            db.setCheckbox_english(venglish.getValue());
                            db.setFilename_appenglishfile(englishprovefilename.getText());
                        }
                    } else {
                        englishprovefilename.setText("");
                        warning_list.add("English Certificate not saved!");
                    }
                }
            } else warning_list.add("English Certificate required ");


        }


        if (!address.getText().isEmpty()) {

            if (regular_v(address.getText())) {
                db.setAdress(address.getText());
            } else warning_list.add("Adress input not valid");

        } else warning_list.add("Adress required for verification");

        if (!job.getText().isEmpty()) {

            if (regular_v(job.getText())) {
                db.setJob(job.getText());
            } else warning_list.add("job input not valid");

        } else warning_list.add("job required for verification");

        if (!edu.getText().isEmpty()) {

            if (regular_v(edu.getText())) {
                db.setEducation(edu.getText());
            } else warning_list.add("eductation input not valid");

        } else warning_list.add("Education required for verification");


        if (!idphotfilename.getText().isEmpty()) {

            if (file_name_v(idphotfilename.getText())) {
                db.setFilename_appidphoto(idphotfilename.getText());
            }

        } else warning_list.add("ID image-copy required for verification");

        check_warning();
    }

    private void send_payment_info() {

        warning_list = new ArrayList();


        try {
            if (use_paypal.isSelected()) {


                if (!paypal_account_email.getText().isEmpty()) {

                    if (e_mail_v(paypal_account_email.getText())) {

                        db.setUse_paypal(true);
                        db.setPaypal_email(paypal_account_email.getText());

                    } else {
                        warning_list.add("Not valid E-mail");
                    }
                } else {
                    warning_list.add("Empty E-mail filed");
                    use_paypal.setSelected(false);
                    check_paypal_option();
                }
            } else {

                db.setUse_paypal(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (use_credit_card.isSelected()) {


            try {

                if (visa_or_mastercard.getValue() != null && !credit_card_number.getText().isEmpty() && !credit_card_number.getText().isEmpty()) {
                    if (card_number_v(credit_card_number.getText()) && card_number_cvv_v(credit_card_cvv.getText())) {
                        db.setUse_credit_card(true);

                        db.setVisa_or_mastercard(visa_or_mastercard.getValue());

                        db.setCredit_card_number(credit_card_number.getText());

                        db.setCredit_card_cvv(credit_card_cvv.getText());
                    } else {
                        warning_list.add("Invalid Card number!");
                    }

                } else {
                    warning_list.add("Empty credit card fields!");
                    use_credit_card.setSelected(false);
                    check_credit_option();
                }
            } catch (NullPointerException e) {
            }
        } else {
            db.setUse_credit_card(false);
        }

        try {
            if (use_bank_account.isSelected()) {

                if (!Bank_account_number.getText().isEmpty()) {

                    if (bank_account_number_v(Bank_account_number.getText())) {

                        db.setUse_bank_account(true);
                        db.setBank_account_number(Bank_account_number.getText());
                    } else {
                        warning_list.add("Invalid Bank account number!");
                    }
                } else {
                    warning_list.add("Empty Bank number filed");
                    use_bank_account.setSelected(false);
                    check_bank_option();
                }
            } else {
                db.setUse_bank_account(false);
            }
        } catch (Exception e) {
            warning_list.add("Empty Bank number filed");
            e.printStackTrace();
        }


        check_warning();


    }

    // know the status of profile and application and payment
    private void complete_validation_UI() {

        try {
            if (comval_profile() && comval_application() && comval_payment()) {


                status_complete.setSelected(true);
                procom.setSelected(true);
                appcom.setSelected(true);
                paycom.setSelected(true);

            } else {
                status_complete.setSelected(false);
            }
        } catch (Exception check) {
            System.out.println("This is the bug all the time" +
                    check);
        }

        try {
            if (comval_profile()) {
                procom.setSelected(true);

                // method to check customer name, gender, birth date
            } else {
                procom.setSelected(false);
                // prover.setSelected(false);// from employer side
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            if (comval_application()) {
                appcom.setSelected(true);
                appver.setSelected(true);// from employer side
                appsave.setText("Applied");
                appsave.setDisable(true);
            } else {
                appcom.setSelected(false);
                appver.setSelected(false);// from employer side
                appsave.setDisable(false);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        try {

            if (comval_payment()) {
                paycom.setSelected(true);
                payver.setSelected(true); // from employer side
            } else {
                paycom.setSelected(false);
                payver.setSelected(false); // from employer side
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        if (payver.isSelected() && appver.isSelected() && prover.isSelected()) {
            status_verified.setSelected(true);
        } else {
            status_verified.setSelected(false);
        }
        update_trip_availability();
    }

    //update status from all tabs
    private void update_trip_availability() {

        // if the customer complete and verified all information the we can enable request_visa button
        if (status_complete.isSelected() & status_verified.isSelected()) {
            tabtrips.setDisable(false);
            // warning_dialog("Trips booking", "Trips booking is now available", false);
        } else {

            tabtrips.setDisable(true);

        }

    }

    /**
     * 3 methods for checking  Complete and validation for profile and application and payment
     */

    private boolean comval_profile() {


        return (!fname.getText().isEmpty()
                && !mname.getText().isEmpty()
                && !lname.getText().isEmpty()
                && !gender.getPromptText().isEmpty()
                && datePicker.getValue() != null
                && !email.getText().isEmpty()
                && country.getValue() != null
                && !phone.getText().isEmpty()
        );
    }

    private boolean comval_application() {

        return (!address.getText().isEmpty()
                && !job.getText().isEmpty()
                && !edu.getText().isEmpty()
                && !idphotfilename.getText().isEmpty()
                && !cvfilename.getText().isEmpty()
                && !healthfilename.getText().isEmpty()
                && !englishprovefilename.getText().isEmpty()
        );
    }

    private boolean comval_payment() {


        return ((use_paypal.isSelected() && !paypal_account_email.getText().isEmpty())
                || (use_bank_account.isSelected() && !Bank_account_number.getText().isEmpty())
                || (use_credit_card.isSelected() && !credit_card_number.getText().isEmpty() &&
                !credit_card_cvv.getText().isEmpty())
        );
    }

    //Toggle button actions
    @FXML
    private void check_paypal_option() {

        if (use_paypal.isSelected()) {

            paypal_account_email.setDisable(false);


        } else paypal_account_email.setDisable(true);

    }

    @FXML
    private void check_credit_option() {

        if (use_credit_card.isSelected()) {

            credit_card_number.setDisable(false);
            credit_card_cvv.setDisable(false);
            visa_or_mastercard.setDisable(false);

        } else {

            credit_card_number.setDisable(true);
            credit_card_cvv.setDisable(true);
            visa_or_mastercard.setDisable(true);
        }


    }

    @FXML
    private void check_bank_option() {
        if (use_bank_account.isSelected())

            Bank_account_number.setDisable(false);

        else Bank_account_number.setDisable(true);
    }

    @FXML
    private void enable_password_change() {


        if (toggleButton_password_enable.isSelected()) {
            password_label.setText("");
            old_password.setDisable(false);
            old_password.setPromptText("Enter Your Password");
            new_password.setDisable(false);
            new_password.setPromptText("Enter New Password");
            new_password_retype.setDisable(false);
            new_password_retype.setPromptText("Confirm New Password");
            new_password_save.setDisable(false);

        } else {

            old_password.setText("");
            new_password.setText("");
            new_password_retype.setText("");
            old_password.setDisable(true);
            new_password.setDisable(true);
            new_password_retype.setDisable(true);
            old_password.setPromptText("Enter Your Password");
            new_password.setPromptText("Enter New Password");
            new_password_retype.setPromptText("Confirm New Password");
            new_password_save.setDisable(true);
        }

    }

    // button Actions

    @FXML
    private void id_check() {

        if (!national_id.getText().isEmpty()) {

            if (id_v(national_id.getText())) {


                Task<Boolean> id_check_task = new Task<Boolean>() {
                    @Override
                    protected Boolean call() throws Exception {
                        start_profile_task_action();
                        return db_national_id(national_id.getText());
                    }
                };

                id_check_task.setOnSucceeded(event -> {

                    if (id_check_task.getValue()) {

                        national_id.setFocusColor(Color.RED);
                        national_id.setUnFocusColor(Color.RED);
                        id_state.setText("The ID already has been taken");
                        finish_profile_task_action();

                    } else {
                        Task<Boolean> citizen_national_id_task = new Task<Boolean>() {
                            @Override
                            protected Boolean call() throws Exception {
                                return db_citizen_national_id(national_id.getText());
                            }
                        };
                        citizen_national_id_task.setOnSucceeded(event1 -> {

                            if (citizen_national_id_task.getValue()) {


                                national_id.setFocusColor(Color.GREEN);
                                national_id.setUnFocusColor(Color.GREEN);
                                id_state.setText("Valid National ID");
                                profile_gridpane.setDisable(false);
                                idphoto.setDisable(false);

                            } else {

                                national_id.setFocusColor(Color.RED);
                                national_id.setUnFocusColor(Color.RED);
                                id_state.setText("Citizen ID not found!");
                            }
                            finish_profile_task_action();
                        });

                        citizen_national_id_task.setOnFailed(event1 -> {
                            finish_profile_task_action();
                            warning_dialog("Connecton error", "Failed to save!, please try again later", false);


                        });

                        exec.execute(citizen_national_id_task);
                    }

                });

                id_check_task.setOnFailed(event -> {
                    finish_profile_task_action();
                    warning_dialog("Connecton error", "Failed to save!, please try again later", false);


                });

                exec.execute(id_check_task);

            } else {
                national_id.setFocusColor(Color.RED);
                national_id.setUnFocusColor(Color.RED);
                id_state.setText("Not valid, ID should be 14 numbers only");
            }
        } else {
            national_id.setFocusColor(Color.RED);
            national_id.setUnFocusColor(Color.RED);
            id_state.setText("Should not be Empty");
        }
    }

    @FXML
    private void save_profile_button() {


        Task<Void> send_profile_task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                start_profile_task_action();
                send_profile_info();
                return null;
            }
        };
        send_profile_task.setOnSucceeded(event -> {

            update_profile_tab();
            update_main_window();
            psave.setDisable(true);

        });
        send_profile_task.setOnFailed(event -> {
            finish_profile_task_action();
            warning_dialog("Connecton error", "Failed to save!, please try again later", false);
        });
        exec.execute(send_profile_task);


    }

    @FXML
    private void save_new_password() {

        Task<Void> new_password_task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                start_profile_task_action();
                new_password_validation_send();
                return null;
            }
        };
        new_password_task.setOnSucceeded(event -> {
            finish_profile_task_action();
        });
        new_password_task.setOnFailed(event -> {
            finish_profile_task_action();
            warning_dialog("Connecton error", "Failed to save!, please try again later", false);
        });

        exec.execute(new_password_task);


    }

    private void new_password_validation_send() {

        if (toggleButton_password_enable.isSelected()) {

            try {
                if (!old_password.getText().isEmpty() && !new_password.getText().isEmpty() && !new_password_retype.getText().isEmpty()) {
                    Task<char[]> get_password_task = new Task<char[]>() {
                        @Override
                        protected char[] call() throws Exception {
                            return db.getPass();
                        }
                    };
                    get_password_task.setOnSucceeded(event -> {

                        if (get_password_task.getValue() != null) {

                            if (Arrays.equals(old_password.getText().toCharArray(), get_password_task.getValue())) {

                                if (password_v(new_password.getText().toCharArray()) && Arrays.equals(new_password.getText().toCharArray(), new_password_retype.getText().toCharArray())) {
                                    Task<Void> send_password_task = new Task<Void>() {
                                        @Override
                                        protected Void call() throws Exception {

                                            db.setPass(new_password.getText().toCharArray());
                                            return null;
                                        }
                                    };

                                    send_password_task.setOnSucceeded(event1 -> {

                                        new_password.setFocusColor(Color.GREEN);
                                        new_password.setUnFocusColor(Color.GREEN);
                                        new_password_retype.setFocusColor(Color.GREEN);
                                        new_password_retype.setUnFocusColor(Color.GREEN);
                                        old_password.setFocusColor(Color.GREEN);
                                        old_password.setUnFocusColor(Color.GREEN);
                                        toggleButton_password_enable.setSelected(false);
                                        old_password.setText("");
                                        new_password.setText("");
                                        new_password_retype.setText("");
                                        old_password.setDisable(true);
                                        new_password.setDisable(true);
                                        new_password_retype.setDisable(true);
                                        old_password.setPromptText("Enter Your Password");
                                        new_password.setPromptText("Enter New Password");
                                        new_password_retype.setPromptText("Confirm New Password");
                                        password_label.setText("Password Changed Successfully");
                                        new_password_save.setDisable(true);
                                        warning_dialog("Password Changing", "Your password changed sucessfully", false);

                                    });

                                    exec.execute(send_password_task);

                                } else {

                                    new_password_retype.setText("");
                                    password_label.setText("The Passwords not the same!");
                                    new_password_retype.setFocusColor(Color.RED);
                                    new_password_retype.setUnFocusColor(Color.RED);
                                }
                            } else {

                                old_password.setText("");
                                password_label.setText("Old password not correct!");
                                old_password.setFocusColor(Color.RED);
                                old_password.setUnFocusColor(Color.RED);

                            }


                        }


                    });
                    exec.execute(get_password_task);
                } else password_label.setText("Empty fields!");

            } catch (Exception password_error) {
                System.out.println(password_error);
            }
        }
    } // error message

    @FXML
    private void save_application_button() {

        Task<Void> send_applicatoin_task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                start_application_task_action();
                send_application_info();
                return null;
            }
        };
        send_applicatoin_task.setOnSucceeded(event -> update_application_tab());
        send_applicatoin_task.setOnFailed(event -> {
            finish_application_task_action();
            warning_dialog("Connecton error", "Failed to save!, please try again later", false);
        });
        exec.execute(send_applicatoin_task);

        //   complete_validation();

    }

    @FXML
    private void save_payment_button() {

        Task<Void> send_payment_task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                start_payment_task_action();
                send_payment_info();

                return null;
            }
        };
        send_payment_task.setOnSucceeded(event -> {
            update_payment_tab();
        });

        send_payment_task.setOnFailed(event -> {

            finish_payment_task_action();
            warning_dialog("Connecton error", "Failed to save!, please try again later", false);

        });
        exec.execute(send_payment_task);
        //  complete_validation();

    }

    @FXML
    private void search_trips_button() {

        //System.out.println(warning_list);


        if ((trips_country_from.getValue() != null) && (trips_country_to.getValue() != null) && (trips_departure.getValue() != null)
                && (trips_arrival.getValue() != null)) {

            Task<Boolean> found_result_task = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    start_trip_task_action();
                    return trip_data.found_result(trips_country_from.getValue(), trips_country_to.getValue(), trips_departure.getValue(),
                            trips_arrival.getValue());
                }
            };
            found_result_task.setOnSucceeded(event -> {

                if (found_result_task.getValue()) {

                    trips_initialization(trip_data.getFlights());
                    buy_trip.setDisable(false);
                } else {
                    buy_trip.setDisable(true);
                    warning_dialog("Warning!", "No result found!", false);

                }
                finish_trip_task_action();
            });
            exec.execute(found_result_task);


        } else {
            warning_dialog("Invalid inputs!", "Please fill all required fileds", false);
        }


    }

    @FXML
    private void buy_trip_button() {

        /* get selected flight and use send_trips to save new flight to data and return it to as history in home tab
          base  */

        if (stuffTableView.getSelectionModel().getSelectedIndex() != -1) {
            try {
                Task<Boolean> send_trips_2_db_task = new Task<Boolean>() {
                    @Override
                    protected Boolean call() throws Exception {
                        start_trip_task_action();
                        return send_trips_to_database(stuffTableView.getSelectionModel().getSelectedIndex());

                    }
                };

                send_trips_2_db_task.setOnSucceeded(event1 -> {

                    if (send_trips_2_db_task.getValue()) {

                        Task<Boolean> find_flights_history_task = new Task<Boolean>() {
                            @Override
                            protected Boolean call() throws Exception {
                                start_home_task_action(); // show trips history on home tap so we need to loadnig it
                                return trip_data.find_flights_history(db.getUsername());
                            }
                        };
                        find_flights_history_task.setOnSucceeded(event2 -> {
                            if (find_flights_history_task.getValue()) {

                                trips_initialization_history(trip_data.getFlights_history());
                            }
                            finish_home_task_action();
                            finish_trip_task_action();

                        });
                        find_flights_history_task.setOnFailed(event -> {

                            finish_home_task_action();
                            finish_trip_task_action();
                            warning_dialog("Connecton error", "Failed to save!, please try again later", false);


                        });

                        exec.execute(find_flights_history_task);

                    } else {
                        finish_trip_task_action();

                    }

                });
                send_trips_2_db_task.setOnFailed(event -> {

                    finish_trip_task_action();
                    warning_dialog("Connecton error", "Failed to save!, please try again later", false);
                });

                exec.execute(send_trips_2_db_task);

            } catch (Exception no_rows) {
                System.out.println(no_rows);
            }
        } else {//alert box here // "please select something to buy"
        }

    }

    @FXML
    private void go_to_profile() {

        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tabprofile);
    }

    //Profile gender
    private void Combo_gender() {
        String items[] = {"Male", "Female", "Other"};
        gender.getItems().addAll(items);
    }

    private void Combo_country(ArrayList<String> items) {

        country.getItems().addAll(items);

    }

    private void Combo_english() {

        String items[] = {"TOEFL", "ILETS", "CAE"};

        venglish.getItems().addAll(items);
    }

    private void Combo_visa() {

        String items[] = {"MasterCard", "Visa"};

        visa_or_mastercard.getItems().addAll(items);
    }

    // send bought flight to database
    private Boolean send_trips_to_database(int row_num) {
        warning_list = new ArrayList();

        trips row_value = stuffTableView.getTreeItem(row_num).getValue();


        if (trip_data.send_bought_trips_to_database(db.getUsername(), row_value.getTrip_id())) {


            return true;
        } else

            warning_list.add("You can't buy the same trip both");
        check_warning();
        return false;

    }

    // trips initialization
    private void trips_initialization(ObservableList<trips> tripsObservableList) {


        trip_id.setCellValueFactory(new TreeItemPropertyValueFactory("trip_id"));
        trip_duration_column.setCellValueFactory(new TreeItemPropertyValueFactory("time"));
        airport_name_from_Column.setCellValueFactory(new TreeItemPropertyValueFactory("airport_name_from"));
        airport_name_to_Column.setCellValueFactory(new TreeItemPropertyValueFactory("airport_name_to"));
        trip_type_Column.setCellValueFactory(new TreeItemPropertyValueFactory("trip_type_way"));
        price_column.setCellValueFactory(new TreeItemPropertyValueFactory("price"));


        TreeItem<trips> root = new RecursiveTreeItem<trips>(tripsObservableList, RecursiveTreeObject::getChildren);


        stuffTableView.getColumns().setAll(

                trip_id, trip_duration_column, airport_name_from_Column,
                airport_name_to_Column, trip_type_Column, price_column

        );

        stuffTableView.setRoot(root);
        // stuffTableView.setShowRoot(false);
        stuffTableView1.setShowRoot(false);


    }

    // text filed for trip info
    @FXML
    private void detail_bar() {

        if (!stuffTableView.getSelectionModel().isEmpty()) {
            int selected_row_num = stuffTableView.getSelectionModel().getSelectedIndex();
            trips row_value = stuffTableView.getTreeItem(selected_row_num).getValue();
            trip_details.setText(row_value.getTrip_type_way() + " Class " + " | From " + row_value.getAirport_name_from() + " To " + row_value.getAirport_name_to() +
                    " | It Costs " + row_value.getPrice() + " $"
            );

        }
    }

    private void trips_initialization_history(ObservableList<trips> tripsObservableList) {

        trip_id_1.setCellValueFactory(new TreeItemPropertyValueFactory("trip_id"));
        trips_date_column1.setCellValueFactory(new TreeItemPropertyValueFactory("bought_ticket_date"));
        flight_duration.setCellValueFactory(new TreeItemPropertyValueFactory("time"));
        airport_name_from_Column1.setCellValueFactory(new TreeItemPropertyValueFactory("airport_name_from"));
        airport_name_to_Column1.setCellValueFactory(new TreeItemPropertyValueFactory("airport_name_to"));
        trip_type_Column1.setCellValueFactory(new TreeItemPropertyValueFactory("trip_type_way"));
        price_column1.setCellValueFactory(new TreeItemPropertyValueFactory("price"));


        TreeItem<trips> root = new RecursiveTreeItem<trips>(tripsObservableList, RecursiveTreeObject::getChildren);

        // stuffTableView1.getColumns().clear();
        stuffTableView1.getColumns().setAll(

                trip_id_1, trips_date_column1, flight_duration, airport_name_from_Column1,
                airport_name_to_Column1, trip_type_Column1, price_column1

        );

        stuffTableView1.setRoot(root);

        stuffTableView1.setShowRoot(false);

    }

    private void requests_state_table(ObservableList<customer_validation_time> customervalidationtime_ObservableList) {

        requests_column_date.setCellValueFactory(new TreeItemPropertyValueFactory("request_date"));
        requests_column_state.setCellValueFactory(new TreeItemPropertyValueFactory("request_state"));
        requests_column_valid_until.setCellValueFactory(new TreeItemPropertyValueFactory("request_end"));

        TreeItem<customer_validation_time> root1 = new RecursiveTreeItem<customer_validation_time>(customervalidationtime_ObservableList, RecursiveTreeObject::getChildren);

        requests_table.getColumns().setAll(

                requests_column_date, requests_column_state, requests_column_valid_until

        );
        requests_table.setRoot(root1);

        requests_table.setShowRoot(false);

    }

    private void Combo_from_country(ArrayList<String> items) {


        trips_country_from.getItems().addAll(items);

    }

    private void Combo_to_country(ArrayList<String> items) {

        trips_country_to.getItems().addAll(items);

    }

    @FXML
    private void english_check_to_enable_upload() {
        try {

            if (!venglish.equals(null)) {

                entest.setDisable(false);

            }
        } catch (NullPointerException e) {

            entest.setDisable(true);
        }

    }

    @FXML
    private void check_tab_update() {

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
            check_warning_task.setOnSucceeded(event -> {

                warning_dialog("Warning!", "Invalid inputs!", true);


            });
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


    private void reset_change_profile() {
        ch_fname = ch_mname = ch_lname = ch_email = ch_phone = ch_gender = ch_birthday = ch_country = false;

    }


    private void citizen_customer_status() {


        Task<Boolean> booleanTask = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                /*if(valid_citizen_task != null){
                    valid_citizen_task.wait();}*/
                start_home_task_action();
                return citizen_v.check_customer_citizen(db.getUsername());
            }
        };

        booleanTask.setOnSucceeded(e -> {

            if (booleanTask.getValue()) {

                citizen_customer_status_true();
                national_id.setText(db.getNational_id());

            } else {

                citizen_customer_status_false();
                warning_dialog("Opps! " + db.getUsername(), "Your National ID not Verified yet!", false);


            }

        });
        booleanTask.setOnFailed(event -> {
            finish_profile_task_action();
            finish_home_task_action();
        });

        exec.execute(booleanTask);

    }

    private void citizen_customer_status_true() {


        Task<ObservableList<customer_validation_time>> observableListTask = new Task<ObservableList<customer_validation_time>>() {
            @Override
            protected ObservableList<customer_validation_time> call() throws Exception {
                start_home_task_action();
                customer_validation_time_object.request_history(db.getUsername());

                return customer_validation_time_object.getCustomer_validation_times();
            }
        };

        observableListTask.setOnSucceeded(e -> {

            requests_state_table(observableListTask.getValue());
            // national_id.setText(db.getNational_id());
            // status_verified.setSelected(true);
            prover.setSelected(true);
            update_trip_availability();
            tabapplication.setDisable(false);
            tabpayment.setDisable(false);
            verified = true;
            id_state.setText("Verified");
            national_id.setEditable(false);
            check_id.setDisable(true);
            profile_gridpane.setDisable(false);
            fname.setEditable(false);
            mname.setEditable(false);
            lname.setEditable(false);
            gender.setDisable(true);
            country.setDisable(true);
            datePicker.setDisable(true);
            change_profile_image.setDisable(false);
            finish_profile_task_action();
            finish_home_task_action();
            complete_validation_UI();
        });

        observableListTask.setOnFailed(e -> {
            finish_profile_task_action();
            finish_home_task_action();
        });


        exec.execute(observableListTask);
        //   return customer_validation_time_object.getCustomer_validation_times();


        // service_observableList.getOnSucceeded();
        // requests_state_table(customer_validation_time_object.getCustomer_validation_times());
    }

    private void citizen_customer_status_false() {
        change_profile_image.setDisable(true);
        tabapplication.setDisable(true);
        // status_verified.setSelected(false);
        prover.setSelected(false);
        verified = false;
        tabpayment.setDisable(true);
        id_state.setText("Not Verified!");
        national_id.setEditable(true);
        check_id.setDisable(false);

        finish_profile_task_action();
        finish_home_task_action();
        complete_validation_UI();
    }

    // tasks action begin and finish

    private void start_home_task_action() {
        home_gridpane.setDisable(true);
        home_progress.setVisible(true);
    }

    private void start_profile_task_action() {
        all_profile_gridpane.setDisable(true);
        profile_progress.setVisible(true);
    }

    private void start_trip_task_action() {
        trip_gridpane.setDisable(true);
        trip_progress.setVisible(true);
    }

    private void start_application_task_action() {
        application_gridpane.setDisable(true);
        appliction_progress.setVisible(true);
    }

    private void start_payment_task_action() {
        payment_gridpane.setDisable(true);
        payment_progress.setVisible(true);
    }

    private void finish_home_task_action() {
        home_gridpane.setDisable(false);
        home_progress.setVisible(false);
    }

    private void finish_profile_task_action() {
        all_profile_gridpane.setDisable(false);
        profile_progress.setVisible(false);
    }

    private void finish_trip_task_action() {
        trip_gridpane.setDisable(false);
        trip_progress.setVisible(false);
    }

    private void finish_application_task_action() {
        application_gridpane.setDisable(false);
        appliction_progress.setVisible(false);
    }

    private void finish_payment_task_action() {
        payment_gridpane.setDisable(false);
        payment_progress.setVisible(false);
    }

    @FXML
    private void log_out() {

        try { // it will not work if the jar'separator name changed.
            Runtime.getRuntime().exec("java -jar OpenRoad.jar");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.exit(3);

    }

}


