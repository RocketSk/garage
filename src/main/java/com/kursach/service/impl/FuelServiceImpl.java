package com.kursach.service.impl;

import com.kursach.dao.FuelDAO;
import com.kursach.dao.impl.FuelDAOImpl;
import com.kursach.model.Fuel;
import com.kursach.service.FuelService;

import java.sql.SQLException;
import java.util.List;

public class FuelServiceImpl implements FuelService {

    private final FuelDAO fuelDAO = new FuelDAOImpl();

    @Override
    public void addFuel(Fuel fuel) {
        fuelDAO.addFuel(fuel);
    }

    @Override
    public void deleteFuel(Fuel fuel) {
        fuelDAO.deleteFuel(fuel);
    }

    @Override
    public List<Fuel> getAll() throws SQLException {
        return fuelDAO.getAll();
    }

    @Override
    public Fuel getById(Integer id) {
        return fuelDAO.getById(id);
    }

    @Override
    public void updateFuel(Fuel fuel) {
        fuelDAO.updateFuel(fuel);
    }
}
