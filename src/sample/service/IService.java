package sample.service;

import javafx.collections.ObservableList;

public interface IService<T> {

    ObservableList<T> getAll();

    T addStudent(T entity);

    T updateStudent(T entity);

    void deleteStudent(int id);
}
