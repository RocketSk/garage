package com.kursach.dao;

import com.kursach.model.Fuel;

import java.sql.SQLException;
import java.util.List;

public interface FuelDAO{

    void addFuel(Fuel fuel);

    void deleteFuel(Fuel fuel);

    List<Fuel> getAll() throws SQLException;

    Fuel getById(Integer id);

    void updateFuel(Fuel fuel);
}
