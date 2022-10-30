package sample.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import sample.Main;
import sample.model.Student;
import sample.service.StudentService;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    ObservableList<Student> ListStudent;

    private static boolean[] isValid = new boolean[5];
    // open stage just once
    private static boolean isClosed = false;
    private static Student student;
    @FXML
    private Button add;
    @FXML
    private Button sup;
    @FXML
    private Circle c1;
    @FXML
    private Circle c2;
    @FXML
    private Circle c3;
    @FXML
    private Circle c4;
    @FXML
    private Circle c5;
    @FXML
    private Button min;
    @FXML
    private AnchorPane main1;
    @FXML
    private AnchorPane main2;
    @FXML
    private TextField rechR;

    @FXML
    private TableView<Student> tableStudent;
    @FXML
    private TableColumn<Student, Integer> idC;

    @FXML
    private TableColumn<Student, String> nomC;

    @FXML
    private TableColumn<Student, String> prenomC;

    @FXML
    private TableColumn<Student, LocalDate> dateNaissC;

    @FXML
    private TableColumn<Student, String> lieuNaissC;

    @FXML
    private TableColumn<Student, LocalDate> dateBacC;

    @FXML
    private TableColumn<Student, Integer> numBacC;

    @FXML
    private TableColumn<Student, String> cycleC;

    @FXML
    private TableColumn<Student, String> mailC;


    private final StudentService studentService;

    public Controller() {
        studentService = new StudentService();
    }

    public static Boolean IsValid(int index) {
        return isValid[index];
    }

    public static Boolean IsValid() {
        for (boolean b : isValid) if (b) return true;
        return false;
    }

    public static boolean areAllTrue() {
        for (boolean b : isValid) if (!b) return false;
        return true;
    }

    public static Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        Controller.student = student;
        setFill();
    }

    private void setFill() {
        if (!isValid[0]) {
            c1.setFill(Color.WHITE);
        } else {
            c1.setFill(Color.web("96c8a2"));
        }
        if (!isValid[1]) {
            c2.setFill(Color.WHITE);
        } else {
            c2.setFill(Color.web("96c8a2"));
        }
        if (!isValid[2]) {
            c3.setFill(Color.WHITE);
        } else {
            c3.setFill(Color.web("96c8a2"));
        }
        if (!isValid[3]) {
            c4.setFill(Color.WHITE);
        } else {
            c4.setFill(Color.web("96c8a2"));
        }
        if (!isValid[4]) {
            c5.setFill(Color.WHITE);
        } else {
            c5.setFill(Color.web("96c8a2"));
        }
    }

    private void setFill(Circle c, int index) {
        if (!isValid[0]) {
            c1.setFill(Color.WHITE);
        } else {
            c1.setFill(Color.web("96c8a2"));
        }
        if (!isValid[1]) {
            c2.setFill(Color.WHITE);
        } else {
            c2.setFill(Color.web("96c8a2"));
        }
        if (!isValid[2]) {
            c3.setFill(Color.WHITE);
        } else {
            c3.setFill(Color.web("96c8a2"));
        }
        if (!isValid[3]) {
            c4.setFill(Color.WHITE);
        } else {
            c4.setFill(Color.web("96c8a2"));
        }
        if (!isValid[4]) {
            c5.setFill(Color.WHITE);
        } else {
            c5.setFill(Color.web("96c8a2"));
        }
        if (!isValid[index]) {
            c.setFill(Color.web("a8a8a8"));
        } else {
            c.setFill(Color.web("96c8a2"));
        }
    }

    public void setStage(String filePath) {
        // if stage is open it will not open second time
        if (!isClosed) {
            Stage stage = Main.setStage(
                    new Stage(),
                    getClass().getResource(filePath),
                    "stage2"
            );
            stage.setAlwaysOnTop(true);
        }
    }

    @FXML
    void addName() {
        if (!isClosed) {
            setStage("/view/fxml/stage1.fxml");
            setFill(c1, 0);
            isClosed = true;
        }
    }

    @FXML
    void addNaiss() {
        if (!isClosed) {
            setStage("/view/fxml/stage2.fxml");
            setFill(c2, 1);
            isClosed = true;
        }
    }

    @FXML
    void addBac() {
        if (!isClosed) {
            setStage("/view/fxml/stage3.fxml");
            setFill(c3, 2);
            isClosed = true;
        }
    }

    @FXML
    void addCycle() {
        if (!isClosed) {
            setStage("/view/fxml/stage4.fxml");
            setFill(c4, 3);
            isClosed = true;
        }
    }

    @FXML
    void addMail() {
        if (!isClosed) {
            setStage("/view/fxml/stage5.fxml");
            setFill(c5, 4);
            isClosed = true;
        }
    }

    @FXML
    void closeStage() {
        System.exit(0);
    }

    @FXML
    void minStage() {
        Stage stage = (Stage) min.getScene().getWindow();
        stage.setIconified(true);
    }

    int index = -1;

    public void getSelectedItems2() {
        index = tableStudent.getSelectionModel().getSelectedIndex();
    }

    private boolean isDone = false;

    @FXML
    void addStudent() {
        if (add.getText().equals("Ajouter")) {
            if (areAllTrue()) {
                System.out.println(studentService.addStudent(student).toString());
                clearFields();
            } else {
                System.out.println("you can't add student");
            }
        } else {
            if (!isDone) {
                if (index < 0) {
                    return;
                }
                main1.setVisible(true);
                main2.setVisible(false);
                sup.setVisible(false);
                Student std = new Student();
                std.setId(idC.getCellData(index));
                std.setNom(nomC.getCellData(index));
                std.setPrenom(prenomC.getCellData(index));
                std.setDateNaiss(dateNaissC.getCellData(index));
                std.setLieuNaiss(lieuNaissC.getCellData(index));
                std.setDateBac(dateBacC.getCellData(index));
                std.setNumBac(numBacC.getCellData(index));
                std.setCycle(cycleC.getCellData(index));
                std.setMail(mailC.getCellData(index));
                student = std;
                Arrays.fill(isValid, true);
                isDone = true;
                setFill();
            } else {
                if (index < 0) {
                    return;
                }
                Student student = studentService.updateStudent(Controller.student);
                System.out.println(student.toString());
                getAllStudent();
                main1.setVisible(false);
                main2.setVisible(true);
                sup.setVisible(true);
                clearFields();
                isDone = false;
                index = -1;
            }
        }
    }

    public void clearFields() {
        isValid = new boolean[5];
        setFill(c1, 0);
    }

    public void setClosed() {
        isClosed = false;
    }

    public void setIsValid(int index) {
        Controller.isValid[index] = true;
    }

    @FXML
    void visualiser() {
        if (areAllTrue()) {
            clearFields();
            index = -1;
            isDone = false;
        }
        if (main1.isVisible()) {
            main1.setVisible(false);
            main2.setVisible(true);
            add.setText("Modifier");
            sup.setVisible(true);
            getAllStudent();
        } else {
            main1.setVisible(true);
            main2.setVisible(false);
            sup.setVisible(false);
            add.setText("Ajouter");
        }
    }

    public void getAllStudent() {
        ListStudent = studentService.getAll();
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomC.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomC.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        dateNaissC.setCellValueFactory(new PropertyValueFactory<>("dateNaiss"));
        lieuNaissC.setCellValueFactory(new PropertyValueFactory<>("lieuNaiss"));
        dateBacC.setCellValueFactory(new PropertyValueFactory<>("dateBac"));
        numBacC.setCellValueFactory(new PropertyValueFactory<>("numBac"));
        cycleC.setCellValueFactory(new PropertyValueFactory<>("cycle"));
        mailC.setCellValueFactory(new PropertyValueFactory<>("mail"));
        tableStudent.setItems(ListStudent);
    }

    @FXML
    void suprimer() {
        if (index < 0) {
            return;
        }
        studentService.deleteStudent(idC.getCellData(index));
        getAllStudent();
        clearFields();
        isDone = false;
        index = -1;
    }

    @FXML
    public void rechercherR() {
        rechR.textProperty().addListener((Observable observable) -> {
            if (rechR.textProperty().get().isEmpty()) {
                tableStudent.getItems().setAll(ListStudent);
                return;
            }
            ObservableList<Student> tableE1 = FXCollections.observableArrayList();
            ObservableList<TableColumn<Student, ?>> cols = tableStudent.getColumns();
            for (Student value : ListStudent) {
                for (TableColumn col : cols) {
                    String cellValue = col.getCellData(value).toString();
                    cellValue = cellValue.toLowerCase();
                    if (cellValue.contains(rechR.getText().toLowerCase()) && cellValue.startsWith(rechR.getText().toLowerCase())) {
                        tableE1.add(value);
                        break;
                    }
                }
            }
            tableStudent.setItems(tableE1);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
