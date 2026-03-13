package com.revplay.daoimpl;

import com.revplay.dao.UserDao;
import com.revplay.daoimpl.UserDaoImpl;
import com.revplay.model.User;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {

    private static UserDao userDao;

    @BeforeAll
    static void setup() {
        userDao = new UserDaoImpl();
    }
//
//    @Test
//    void testUserRegisterAndLogin() {
//
//        User user = new User(
//                "JUnitUser",
//                "junituser@gmail.com",
//                "123"
//        );
//
//        userDao.register(user);
//
//        int userId = userDao.login(
//                "junituser@gmail.com",
//                "123"
//        );
//
//        assertTrue(userId > 0, "User login failed");
//    }

    @Test
    void testUserRegisterAndLogin() {

        String email = "junit_" + System.currentTimeMillis() + "@gmail.com";

        User user = new User(
                "JUnitUser",
                email,
                "123"
        );

        userDao.register(user);

        int userId = userDao.login(email, "123");

        assertTrue(userId > 0, "User login failed");
    }

}
