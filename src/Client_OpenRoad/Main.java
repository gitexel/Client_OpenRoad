package Client_OpenRoad;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jetbrains.annotations.NotNull;

import java.io.File;


public class Main extends Application {
    private final String icon_url ="file:"+System.getProperty("user.dir")+File.separator+"src"+File.separator+"icon.png";
    private double xOffset, yOffset;

    public static void main(String[] args) {

        Application.launch(args);

    }


    @Override
    public void start(@NotNull Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(this.getClass().getResource("login.fxml"));
        primaryStage.getIcons().add(new Image(icon_url));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Open Road");
        primaryStage.setScene(new Scene(root, 558, 380));
        primaryStage.show();

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(@NotNull MouseEvent event) {
                Main.this.xOffset = event.getSceneX();
                Main.this.yOffset = event.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(@NotNull MouseEvent event) {
                primaryStage.setX(event.getScreenX() - Main.this.xOffset);
                primaryStage.setY(event.getScreenY() - Main.this.yOffset);

            }
        });


    }

}


