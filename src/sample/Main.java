package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) {
        setStage(primaryStage, getClass().getResource("/view/fxml/main.fxml"), "main");
    }

    public static Stage setStage(Stage primaryStage, URL url, String name) {
        FXMLLoader fxmlLoader;
        try {
            fxmlLoader = getLoader(url);
            Parent root = fxmlLoader.load();
            primaryStage.setTitle(name);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            moveStage(primaryStage, scene);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return primaryStage;
    }

    public static void close(ActionEvent event) {
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.close();
    }

    public static boolean isInputValid(JFXTextField textField, String regex) {
        if (!(textField.getText() == null) || (textField.getText().length() == 0)) {
            if (textField.getText().length() >= 4) {
                if (textField.getText().matches(regex)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static FXMLLoader getLoader(URL url) {
        return new FXMLLoader(url);
    }

    public static void moveStage(Stage primaryStage, Scene scene) {
        final double[] xOffset = {0};
        final double[] yOffset = {0};
        scene.setOnMousePressed(event -> {
            xOffset[0] = event.getSceneX();
            yOffset[0] = event.getSceneY();
            primaryStage.setOpacity(0.2f);
        });
        scene.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset[0]);
            primaryStage.setY(event.getScreenY() - yOffset[0]);
            primaryStage.setOpacity(0.2f);
        });
        scene.setOnDragDone(event -> {
            primaryStage.setOpacity(1.0f);
        });
        scene.setOnMouseReleased(event -> {
            primaryStage.setOpacity(1.0f);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
