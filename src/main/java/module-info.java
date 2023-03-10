module com.kursach {

    // Fx req
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires java.compiler;
    requires java.instrument;
    requires jdk.unsupported;

    // Database
    requires java.sql;
    requires mysql.connector.j;
    requires org.hibernate.orm.core;
    requires org.hibernate.commons.annotations;
    requires javax.el.api;
    requires java.persistence;
    requires java.naming;

    // Fx core
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    //lombok
    requires lombok;

    opens com.kursach to javafx.fxml;
    exports com.kursach;
    exports com.kursach.controller.driverController;
    exports com.kursach.controller.autoController;
    exports com.kursach.controller.mainController;
    exports com.kursach.controller.orderController;
    exports com.kursach.controller.fuelController;
    exports com.kursach.controller.userController;

    exports com.kursach.model;
    exports com.kursach.util;
    opens com.kursach.util to javafx.fxml;
    opens com.kursach.model to org.hibernate.orm.core;
    opens com.kursach.controller.driverController to javafx.fxml;
    opens com.kursach.controller.autoController to javafx.fxml;
    opens com.kursach.controller.mainController to javafx.fxml;
    opens com.kursach.controller.orderController to javafx.fxml;
    opens com.kursach.controller.fuelController to javafx.fxml;
    opens com.kursach.controller.userController to javafx.fxml;
}

