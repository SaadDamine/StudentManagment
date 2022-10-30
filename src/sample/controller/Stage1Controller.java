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

public class Stage1Controller implements Initializable {
    Controller controller;
    @FXML
    private Button min;
    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField prenom;

    @FXML
    void add(ActionEvent event) {
        if (!Main.isInputValid(nom, "[A-Za-z\\s]+")) {
            return;
        }
        if (!Main.isInputValid(prenom, "[A-Za-z\\s]+")) {
            return;
        }
        String nom = this.nom.getText().toLowerCase().trim();
        String prenom = this.prenom.getText().toLowerCase().trim();
        Student std;
        if (Controller.IsValid()) {
            std = Controller.getStudent();
        } else {
            std = new Student();
        }
        std.setNom(nom);
        std.setPrenom(prenom);
        controller.setIsValid(0);
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
        if (Controller.IsValid(0)) {
            Student student = Controller.getStudent();
            nom.setText(student.getNom());
            prenom.setText(student.getPrenom());
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
