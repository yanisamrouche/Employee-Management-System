package com.example.project;

import java.util.Date;

public class Employee {
    private Integer id;
    private String nom;
    private String prenom;
    private String adresse;
    private Date an;
    private String ln;
    private Integer age;
    private String fonction;
    private double Salaire;

    public Employee(Integer id, String nom, String prenom, String adresse,
                    Date an, String ln, Integer age, String fonction, double salaire) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.an = an;
        this.ln = ln;
        this.age = age;
        this.fonction = fonction;
        Salaire = salaire;
    }

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public Date getAn() {
        return an;
    }

    public String getLn() {
        return ln;
    }

    public Integer getAge() {
        return age;
    }

    public String getFonction() {
        return fonction;
    }

    public double getSalaire() {
        return Salaire;
    }
}
