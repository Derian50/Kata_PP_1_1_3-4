package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection = Util.getConnection();

    public void createUsersTable() {

        String createTableSql = "CREATE TABLE IF NOT EXISTS users(" +
                "id INT NOT NULL AUTO_INCREMENT," +
                "name TEXT," +
                "lastName TEXT," +
                "age INT," +
                "PRIMARY KEY (id)" +
                ");";
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(createTableSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String createTableSql = "DROP TABLE IF EXISTS users";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "INSERT INTO users (name, lastName, age) " +
                "VALUES (?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(saveUser)){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String removeUserSql = "DELETE from users where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(removeUserSql)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String getAllSql = "SELECT * FROM users;";
        List<User> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(getAllSql);
            int i = 0;
            while(resultSet.next()){
                list.add(new User(
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age")));
                list.get(i++).setId(resultSet.getLong("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        String cleanUsersSql = "TRUNCATE users";
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(cleanUsersSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
