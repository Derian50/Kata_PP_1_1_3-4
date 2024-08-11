package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;


public class Main {
    public static void main(String[] args) {
        UserServiceImpl service = new UserServiceImpl();
        User[] users = {
                new User("Рик" , "Санчез", (byte) 69),
                new User("Морти" , "Смит", (byte) 15),
                new User("Бет" , "Смит", (byte) 35),
                new User("Саммер " , "Смит", (byte) 18),

        };
        service.createUsersTable();
        for (User user: users) {
            service.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.printf("User с именем — %s добавлен в базу данных \n", user.getName());
        }
        System.out.println(service.getAllUsers().toString());
        service.cleanUsersTable();
        service.dropUsersTable();
        //Does nothing if used Hibernate
        Util.closeConnection();
    }
}
