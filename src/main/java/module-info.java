module com.example.alpha {
    requires java.sql;
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;
    requires spring.beans;
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.core;
    requires spring.data.jpa;
    requires jakarta.persistence;
    requires static lombok;
    //very important
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires spring.web;
    requires com.fasterxml.jackson.databind;
    requires jakarta.validation;


    opens com.example.alpha;
    opens com.example.alpha.JavaFx.model;
    opens com.example.alpha.JavaFx.controller;
    opens com.example.alpha.JavaFx.Load;
    opens com.example.alpha.JavaFx.view;


    opens com.example.alpha.Spring_boot.result;
    opens com.example.alpha.Spring_boot.result.student;
    opens com.example.alpha.Spring_boot.result.grade;
    opens com.example.alpha.Spring_boot.student;
    opens com.example.alpha.Spring_boot.student.student_profile;
    opens com.example.alpha.repository;
    opens com.example.alpha.Spring_boot.class_grade;
    opens com.example.alpha.Spring_boot.user;
    opens com.example.alpha.Spring_boot.subject;
    opens com.example.alpha.Spring_boot.assignment;

//    opens com.example.alpha.Spring_boot.regulations;
    exports com.example.alpha;
    exports com.example.alpha.Spring_boot.student;
    exports com.example.alpha.JavaFx.view;
    exports com.example.alpha.repository;
    exports com.example.alpha.Spring_boot.class_grade;
    exports com.example.alpha.JavaFx.model;
}