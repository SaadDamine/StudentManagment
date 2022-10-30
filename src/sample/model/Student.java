package sample.model;

import java.time.LocalDate;

public class Student {

    private int id;
    private String nom;
    private String prenom;
    private LocalDate dateNaiss;
    private String lieuNaiss;
    private LocalDate dateBac;
    private int numBac;
    private String cycle;
    private String mail;

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(LocalDate dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public String getLieuNaiss() {
        return lieuNaiss;
    }

    public void setLieuNaiss(String lieuNaiss) {
        this.lieuNaiss = lieuNaiss;
    }

    public LocalDate getDateBac() {
        return dateBac;
    }

    public void setDateBac(LocalDate dateBac) {
        this.dateBac = dateBac;
    }

    public int getNumBac() {
        return numBac;
    }

    public void setNumBac(int numBac) {
        this.numBac = numBac;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", nome='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaiss=" + dateNaiss +
                ", lieuNaiss='" + lieuNaiss + '\'' +
                ", dateBac=" + dateBac +
                ", numBac=" + numBac +
                ", cycle='" + cycle + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
