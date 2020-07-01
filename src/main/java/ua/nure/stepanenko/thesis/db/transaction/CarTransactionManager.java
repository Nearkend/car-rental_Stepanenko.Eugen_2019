package ua.nure.stepanenko.thesis.db.transaction;

import ua.nure.stepanenko.thesis.exception.db.DBException;
import ua.nure.stepanenko.thesis.model.entyty.Car;

import java.util.List;

public interface CarTransactionManager {

    boolean createCar(Car car) throws DBException;

    List<Car> getCars() throws DBException;

    List<Car> getCarsByClass(String clazz, String sort) throws DBException;

    List<Car> getCarsByMark(String mark, String sort) throws DBException;

    boolean updateCar(Car car) throws DBException;

    boolean setCarFieldThereIsByCarId(boolean thereIs, int carId) throws DBException;

    boolean deleteCarById(int id) throws DBException;
}
