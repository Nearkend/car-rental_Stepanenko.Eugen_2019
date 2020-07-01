package ua.nure.stepanenko.thesis.db.connector.postgres.dao;

import ua.nure.stepanenko.thesis.db.connector.postgres.utli.DAOUtils;
import ua.nure.stepanenko.thesis.db.dao.CarDAO;
import ua.nure.stepanenko.thesis.model.entyty.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresCarDAO extends CarDAO {

    private static final char ANY_CHARACTER = '%';

    private static final String INSERT_INTO_CARS_SQL =
            "INSERT INTO cars (mark_id, class_id, name, cost) VALUES (?, ?, ?, ?)";

    private static final String SELECT_ALL_FROM_CARS_SQL = "SELECT * FROM cars";

    private static final String DELETE_FROM_CARS_SQL = "DELETE FROM cars WHERE id = ?";

    private static final String UPDATE_CARS_SQL =
            "UPDATE cars SET mark_id = ?, class_id = ?, name = ?, cost = ? WHERE id = ?";

    private static final String UPDATE_THERE_IS_SQL = "UPDATE cars SET there_is = ? WHERE id = ?";

    private static final String SELECT_FROM_CARS_BY_CLASS_SQL =
            "SELECT * FROM cars ca INNER JOIN classes cl ON ca.class_id = cl.id WHERE cl.name LIKE ? ORDER BY ca.%s";

    private static final String SELECT_FROM_CARS_BY_MARK_SQL =
            "SELECT * FROM cars c INNER JOIN marks m ON c.mark_id = m.id WHERE m.name LIKE ? ORDER BY c.%s";

    private static PostgresCarDAO postgresCarDAO;

    private PostgresCarDAO() {
    }

    public static PostgresCarDAO getInstance() {
        if (postgresCarDAO == null) {
            synchronized (PostgresUserDAO.class) {
                if (postgresCarDAO == null) {
                    postgresCarDAO = new PostgresCarDAO();
                }
            }
        }
        return postgresCarDAO;
    }

    @Override
    public boolean createCar(Connection connection, Car car) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_CARS_SQL)) {
            DAOUtils.fillPreparedStatement(
                    preparedStatement,
                    car.getMark().getId(),
                    car.getClazz().getId(),
                    car.getName(),
                    car.getCost());

            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public List<Car> getCars(Connection connection) throws SQLException {
        List<Car> cars = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_CARS_SQL)) {

            while (resultSet.next()) {
                cars.add(getCar(resultSet));
            }
        }
        return cars;
    }

    @Override
    public List<Car> getCarsByClass(Connection connection, String clazz, String sort) throws SQLException {
        List<Car> cars = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                String.format(SELECT_FROM_CARS_BY_CLASS_SQL, sort))) {

            DAOUtils.fillPreparedStatement(preparedStatement, ANY_CHARACTER + clazz + ANY_CHARACTER);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    cars.add(getCar(resultSet));
                }
            }
        }
        return cars;
    }

    @Override
    public List<Car> getCarsByMark(Connection connection, String mark, String sort) throws SQLException {
        List<Car> cars = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                String.format(SELECT_FROM_CARS_BY_MARK_SQL, sort))) {

            DAOUtils.fillPreparedStatement(
                    preparedStatement, String.format("%c%s%c", ANY_CHARACTER, mark, ANY_CHARACTER));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    cars.add(getCar(resultSet));
                }
            }
        }
        return cars;
    }

    @Override
    public boolean updateCar(Connection connection, Car car) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CARS_SQL)) {
            DAOUtils.fillPreparedStatement(
                    preparedStatement,
                    car.getMark().getId(),
                    car.getClazz().getId(),
                    car.getName(),
                    car.getCost(),
                    car.getId());

            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean setCarFieldThereIsByCarId(
            Connection connection, boolean thereIs, int carId) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_THERE_IS_SQL)) {
            DAOUtils.fillPreparedStatement(
                    preparedStatement,
                    thereIs,
                    carId);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteCarById(Connection connection, int id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_CARS_SQL)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }
}
