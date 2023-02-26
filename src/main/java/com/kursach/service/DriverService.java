package com.kursach.service;

import com.kursach.model.Auto;
import com.kursach.model.Driver;

import java.sql.SQLException;
import java.util.List;

public interface DriverService {
    void addDriver(Driver driver);

    List<Driver> getAll() throws SQLException;

    List<Driver> getAllByAuto(Auto auto) throws SQLException;

    Driver  getByID(Integer id) throws SQLException;

    void updateDriver(Driver driver);

    void deleteDriver(Integer id);
}
