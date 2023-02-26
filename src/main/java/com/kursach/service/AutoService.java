package com.kursach.service;

import com.kursach.model.Auto;
import com.kursach.model.Fuel;

import java.sql.SQLException;
import java.util.List;

public interface AutoService {
    void addAuto(Auto auto);

    List<Auto> getAll() throws SQLException;

    Auto getById(Integer id);

    Auto getByNum(String autoNum);

    void updateAuto(Auto auto);

    void deleteAuto(Auto auto);

    List<Fuel> getAllByFuel(Fuel fuel) throws SQLException;
}
