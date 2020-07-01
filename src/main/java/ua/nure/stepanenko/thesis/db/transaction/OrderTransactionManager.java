package ua.nure.stepanenko.thesis.db.transaction;

import ua.nure.stepanenko.thesis.exception.db.DBException;
import ua.nure.stepanenko.thesis.model.constant.State;
import ua.nure.stepanenko.thesis.model.entyty.Order;
import ua.nure.stepanenko.thesis.model.entyty.StateCounter;

import java.util.List;

public interface OrderTransactionManager {

    boolean createOrder(Order order) throws DBException;

    Order getOrderByNumber(int number) throws DBException;

    List<Order> getOrders() throws DBException;

    List<Order> getOrdersByLogin(String login) throws DBException;

    boolean setOrderFieldPenaltyIdByOrderNumber(int penaltyId, int orderNumber) throws DBException;

    boolean setNullToOrderFieldPenaltyIdByOrderNumber(int orderNumber) throws DBException;

    boolean setOrderFieldStateByOrderNumber(State state, int orderNumber) throws DBException;

    List<StateCounter> getStateCountOnOrders() throws DBException;
}
