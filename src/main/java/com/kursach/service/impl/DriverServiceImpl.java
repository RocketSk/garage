package com.kursach.service.impl;

import com.kursach.dao.DriverDAO;
import com.kursach.dao.impl.DriverDAOImpl;
import com.kursach.model.Auto;
import com.kursach.model.Driver;
import com.kursach.service.DriverService;

import java.sql.SQLException;
import java.util.List;

public class DriverServiceImpl implements DriverService {

    DriverDAO driverDAO;

    public DriverServiceImpl() {
        this.driverDAO = new DriverDAOImpl();
    }

    @Override
    public void addDriver(Driver driver) {
        driverDAO.addDriver(driver);
    }

    @Override
    public List<Driver> getAll() throws SQLException {
        return driverDAO.getAll();
    }

    @Override
    public List<Driver> getAllByAuto(Auto auto) throws SQLException {
        return driverDAO.getAllByAuto(auto);
    }

    @Override
    public Driver getByID(Integer id) throws SQLException{
        return driverDAO.getById(id);
    }

    @Override
    public void updateDriver(Driver driver) {
        driverDAO.updateDriver(driver);
    }

    @Override
    public void deleteDriver(Integer id) {
        driverDAO.deleteDriver(id);
    }
}
