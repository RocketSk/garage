package com.kursach.service.impl;

import com.kursach.dao.AutoDAO;
import com.kursach.dao.impl.AutoDAOImpl;
import com.kursach.model.Auto;
import com.kursach.model.Fuel;
import com.kursach.service.AutoService;

import java.sql.SQLException;
import java.util.List;

public class AutoServiceImpl implements AutoService {

    AutoDAO autoDAO;

    public AutoServiceImpl(){
        this.autoDAO = new AutoDAOImpl();
    }

    @Override
    public void addAuto(Auto auto) {
        autoDAO.addAuto(auto);
    }

    @Override
    public List<Auto> getAll() throws SQLException {
        return autoDAO.getAll();
    }

    @Override
    public Auto getById(Integer id) {
        return autoDAO.getById(id);
    }

    @Override
    public Auto getByNum(String autoNum) {
        return autoDAO.getByNum(autoNum);
    }

    @Override
    public void updateAuto(Auto auto) {
        autoDAO.updateAuto(auto);
    }

    @Override
    public void deleteAuto(Auto auto) {
        autoDAO.deleteAuto(auto);
    }

    @Override
    public List<Fuel> getAllByFuel(Fuel fuel) throws SQLException {
        return autoDAO.getAllByFuel(fuel);
    }
}
