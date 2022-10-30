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

public class Stage3Controller implements Initializable {

    Controller controller;
    @FXML
    private Button min;
    @FXML
    private JFXDatePicker dateBac;
    @FXML
    private JFXTextField numBac;

    @FXML
    void add(ActionEvent event) {
        if (dateBac.getValue() == null) {
            return;
        }
        if (!Main.isInputValid(numBac, "[0-9]+")) {
            return;
        }
        LocalDate dateBac = this.dateBac.getValue();
        int numBac = Integer.parseInt(this.numBac.getText().trim());
        Student std;
        if (Controller.IsValid()) {
            std = Controller.getStudent();
        } else {
            std = new Student();
        }
        std.setDateBac(dateBac);
        std.setNumBac(numBac);
        controller.setIsValid(2);
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
        if (Controller.IsValid(2)) {
            Student student = Controller.getStudent();
            dateBac.setValue(student.getDateBac());
            numBac.setText(student.getNumBac() + "");
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

