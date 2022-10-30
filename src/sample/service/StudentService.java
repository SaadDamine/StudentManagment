package sample.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Student;
import sample.util.JdbcAccessUtil;

import java.sql.*;

public class StudentService implements IService<Student> {

    private Connection instance;

    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        studentService.getAll();
    }

    @Override
    public ObservableList<Student> getAll() {
        try {
            // connect to databse
            instance = JdbcAccessUtil.getInstance();
            // select query
            String QUERY = "Select * from Student";
            Statement stmt = instance.createStatement();
            // execute the query
            ResultSet rs = stmt.executeQuery(QUERY);
            // a student object
            Student student;
            // list of all students
            ObservableList<Student> std = FXCollections.observableArrayList();
            while (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setNom(rs.getString("nom"));
                student.setPrenom(rs.getString("prenom"));
                student.setDateNaiss(rs.getDate("dateNaiss").toLocalDate());
                student.setLieuNaiss(rs.getString("lieuNaiss"));
                student.setDateBac(rs.getDate("dateBac").toLocalDate());
                student.setNumBac(rs.getInt("numBac"));
                student.setCycle(rs.getString("cycle"));
                student.setMail(rs.getString("mail"));
                std.add(student);
            }
            return std;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Student addStudent(Student entity) {
        try {
            // connect to databse
            instance = JdbcAccessUtil.getInstance();
            // select query
            String QUERY = "insert into student(nom,prenom,dateNaiss,lieuNaiss,dateBac,numBac,cycle,mail) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = instance.prepareStatement(QUERY);
            preparedStatement.setString(1, entity.getNom());
            preparedStatement.setString(2, entity.getPrenom());
            preparedStatement.setDate(3, Date.valueOf(entity.getDateNaiss()));
            preparedStatement.setString(4, entity.getLieuNaiss());
            preparedStatement.setDate(5, Date.valueOf(entity.getDateBac()));
            preparedStatement.setInt(6, entity.getNumBac());
            preparedStatement.setString(7, entity.getCycle());
            preparedStatement.setString(8, entity.getMail());
            preparedStatement.executeUpdate();
            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                if (rs.next()) {
                    entity.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public Student updateStudent(Student entity) {
        try {
            // connect to databse
            instance = JdbcAccessUtil.getInstance();
            // select query
            String QUERY = "UPDATE student SET nom = ?,prenom = ?,dateNaiss = ?,lieuNaiss = ?,dateBac = ?,numBac = ?,cycle = ?,mail = ? WHERE id = ?";
            PreparedStatement preparedStatement = instance.prepareStatement(QUERY);
            preparedStatement.setString(1, entity.getNom());
            preparedStatement.setString(2, entity.getPrenom());
            preparedStatement.setDate(3, Date.valueOf(entity.getDateNaiss()));
            preparedStatement.setString(4, entity.getLieuNaiss());
            preparedStatement.setDate(5, Date.valueOf(entity.getDateBac()));
            preparedStatement.setInt(6, entity.getNumBac());
            preparedStatement.setString(7, entity.getCycle());
            preparedStatement.setString(8, entity.getMail());
            preparedStatement.setInt(9, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public void deleteStudent(int id) {
        try {
            // connect to databse
            instance = JdbcAccessUtil.getInstance();
            // select query
            String QUERY = "delete from student WHERE id = ?";
            PreparedStatement preparedStatement = instance.prepareStatement(QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
