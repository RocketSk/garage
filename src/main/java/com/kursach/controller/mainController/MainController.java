package com.kursach.controller.mainController;

import com.kursach.interfaceForController.TabGetWindow;
import com.kursach.util.HibernateUtil;
import javafx.fxml.FXML;
import org.hibernate.SessionFactory;


public class MainController extends TabGetWindow {

    //Connection with JDBC for hibernate inserts sample data with init application
    @FXML
    void initialize() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.openSession();
    }

}
