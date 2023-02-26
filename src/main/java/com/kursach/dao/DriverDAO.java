package com.kursach.dao;

import com.kursach.model.Auto;
import com.kursach.model.Driver;

import java.sql.SQLException;
import java.util.List;

public interface DriverDAO {

    void addDriver(Driver driver);

    List<Driver> getAll() throws SQLException;

    List<Driver> getAllByAuto(Auto auto) throws SQLException;

    Driver getById(Integer id);

    void updateDriver(Driver driver);

    void deleteDriver(Integer id);
}
