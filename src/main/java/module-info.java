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
    requires jakarta.validation;
    requires spring.tx;
    requires javafx.base;
    requires java.desktop;
    requires org.apache.poi.poi;

    opens com.example.alpha.JavaFx.model;
    opens com.example.alpha.JavaFx.controller;
    opens com.example.alpha.JavaFx.Load;
    opens com.example.alpha.JavaFx.view;
    opens com.example.alpha;
    opens com.example.alpha.JavaFx.model.MonHoc;
    opens com.example.alpha.JavaFx.model.Diem;
    opens com.example.alpha.JavaFx.model.GiaoVien;
    opens com.example.alpha.JavaFx.model.SinhVien;
    opens com.example.alpha.JavaFx.controller.DanhGia;

    opens com.example.alpha.JavaFx.Teacher;
    opens com.example.alpha.Spring_boot.result;
    opens com.example.alpha.Spring_boot.result.student;
    opens com.example.alpha.Spring_boot.student;
    opens com.example.alpha.repository;
    opens com.example.alpha.Spring_boot.class_grade;
    opens com.example.alpha.Spring_boot.user;
    opens com.example.alpha.Spring_boot.subject;
    opens com.example.alpha.Spring_boot.assignment;
    opens com.example.alpha.JavaFx.Student;
//    opens com.example.alpha.Spring_boot.regulations;
    exports com.example.alpha;
    exports com.example.alpha.Spring_boot.student;
    exports com.example.alpha.JavaFx.view;
    exports com.example.alpha.repository;
    exports com.example.alpha.Spring_boot.class_grade;
    exports com.example.alpha.JavaFx.model;
    opens com.example.alpha.JavaFx.controller.DiemSinhVien;
    opens com.example.alpha.JavaFx.controller.SinhVien;
    opens com.example.alpha.JavaFx.controller.DiemQuaTrinh;
    opens com.example.alpha.JavaFx.controller.TaiKhoan;
    opens com.example.alpha.JavaFx.controller.Menu;
    opens com.example.alpha.JavaFx.controller.ThongKe;
    exports com.example.alpha.JavaFx.model.Diem;
    exports com.example.alpha.JavaFx.model.GiaoVien;
    exports com.example.alpha.JavaFx.model.SinhVien;
    exports com.example.alpha.JavaFx.model.MonHoc;
    exports com.example.alpha.Spring_boot.result.student;
    exports com.example.alpha.JavaFx.controller.ThongKe;
    exports com.example.alpha.JavaFx.controller.DanhGia;

    exports com.example.alpha.JavaFx.Teacher;
    exports com.example.alpha.JavaFx.Student;
    opens com.example.alpha.JavaFx.controller.DiemThi;
    opens com.example.alpha.JavaFx.controller.DiemQuaTrinh.info;
    exports com.example.alpha.JavaFx.controller.DiemSinhVien;
}