package sample.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Main;
import sample.model.Student;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Stage4Controller implements Initializable {

    Controller controller;
    @FXML
    private Button min;
    @FXML
    private JFXTextField cycle;

    @FXML
    void add(ActionEvent event) {
        if (!Main.isInputValid(cycle, "[A-Za-z\\s]+")) {
            return;
        }
        String cycle = this.cycle.getText().toLowerCase().trim();
        Student std;
        if (Controller.IsValid()) {
            std = Controller.getStudent();
        } else {
            std = new Student();
        }
        std.setCycle(cycle);
        controller.setIsValid(3);
        controller.setStudent(std);
        // set isClosed to false to let windows to open again
        controller.setClosed();
        Main.close(event);
    }

    @FXML
    void cancel(ActionEvent event) {
        // set isClosed to false to let windows to open again
        controller.setClosed();
        Main.close(event);
    }

    @FXML
    void closeStage(ActionEvent event) {
        // set isClosed to false to let windows to open again
        controller.setClosed();
        Main.close(event);
    }

    @FXML
    void minStage() {
        Stage stage = (Stage) min.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Controller.IsValid(3)) {
            Student student = Controller.getStudent();
            cycle.setText(student.getCycle() + "");
        }
        try {
            FXMLLoader loader = Main.getLoader(getClass().getResource("/view/fxml/main.fxml"));
            loader.load();
            controller = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
