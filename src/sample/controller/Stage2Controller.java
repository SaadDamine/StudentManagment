package sample.controller;

import com.jfoenix.controls.JFXDatePicker;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Stage2Controller implements Initializable {

    Controller controller;
    @FXML
    private Button min;
    @FXML
    private JFXDatePicker dateNaiss;
    @FXML
    private JFXTextField lieuNaiss;

    @FXML
    void add(ActionEvent event) {
        if (dateNaiss.getValue() == null) {
            return;
        }
        if (!Main.isInputValid(lieuNaiss, "[A-Za-z\\s]+")) {
            return;
        }
        LocalDate dateNaiss = this.dateNaiss.getValue();
        String lieuNaiss = this.lieuNaiss.getText().toLowerCase().trim();
        Student std;
        if (Controller.IsValid()) {
            std = Controller.getStudent();
        } else {
            std = new Student();
        }
        std.setDateNaiss(dateNaiss);
        std.setLieuNaiss(lieuNaiss);
        controller.setIsValid(1);
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
        if (Controller.IsValid(1)) {
            Student student = Controller.getStudent();
            dateNaiss.setValue(student.getDateNaiss());
            lieuNaiss.setText(student.getLieuNaiss());
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
