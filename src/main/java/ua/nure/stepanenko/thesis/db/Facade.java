package ua.nure.stepanenko.thesis.db;

import ua.nure.stepanenko.thesis.exception.db.DBException;
import ua.nure.stepanenko.thesis.model.been.Penalty;
import ua.nure.stepanenko.thesis.model.constant.Role;
import ua.nure.stepanenko.thesis.model.constant.State;
import ua.nure.stepanenko.thesis.model.entyty.Car;
import ua.nure.stepanenko.thesis.model.entyty.Order;
import ua.nure.stepanenko.thesis.model.entyty.StateCounter;
import ua.nure.stepanenko.thesis.model.entyty.User;

import java.util.List;

public interface Facade {

    // User
    boolean createUser(User user) throws DBException;

    User getUserByLogin(String login) throws DBException;

    User getUserByLoginAndPassword(String login, String password) throws DBException;

    boolean updateUserByLogin(User user, String oldLogin) throws DBException;

    boolean setUserFieldRoleByUserLogin(Role role, String userLogin) throws DBException;

    boolean setUserFieldPassportByUserLogin(String passport, String userLogin) throws DBException;

    boolean setUserFieldBlockedByUserLogin(boolean blocked, String userLogin) throws DBException;

    // Car
    boolean createCar(Car car) throws DBException;

    List<Car> getCars() throws DBException;

    List<Car> getCarsByClass(String clazz, String sort) throws DBException;

    List<Car> getCarsByMark(String mark, String sort) throws DBException;

    boolean updateCar(Car car) throws DBException;

    boolean setCarFieldThereIsByCarId(boolean thereIs, int carId) throws DBException;

    boolean deleteCarById(int id) throws DBException;

    // Order
    boolean createOrder(Order order) throws DBException;

    Order getOrderByNumber(int number) throws DBException;

    List<Order> getOrders() throws DBException;

    List<Order> getOrdersByUserLogin(String login) throws DBException;

    boolean setOrderFieldPenaltyIdByOrderNumber(int penaltyId, int orderNumber) throws DBException;

    boolean setNullToOrderFieldPenaltyIdByOrderNumber(int orderNumber) throws DBException;

    boolean setOrderFieldStateByOrderNumber(State state, int orderNumber) throws DBException;

    List<StateCounter> getStateCountOnOrders() throws DBException;

    // Penalty
    boolean createPenalty(Penalty penalty) throws DBException;

    Penalty getPenaltyById(int id) throws DBException;

    boolean deletePenaltyById(int id) throws DBException;

    // Car order
    boolean changeCarFieldThereIsAndOrderFieldStateByIDs(
            boolean thereIs, int carId, State state, int orderNumber) throws DBException;

    // Penalty order
    boolean createAndSetOrderFieldPenaltyIdByOrderNumber(
            Penalty penalty, int orderNumber) throws DBException;

    boolean createAndSetOrderFieldPenaltyIdByOrderNumberAndChangeState(
            Penalty penalty, int orderNumber, State state) throws DBException;
}
