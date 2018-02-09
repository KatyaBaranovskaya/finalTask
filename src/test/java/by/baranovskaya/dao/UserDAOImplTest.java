package by.baranovskaya.dao;

import by.baranovskaya.dao.UserDAO;
import by.baranovskaya.dao.factory.DAOFactory;
import by.baranovskaya.exception.DAOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserDAOImplTest {
    private static UserDAO userDAO;

    @BeforeClass
    public static void init() {
        userDAO = DAOFactory.getInstance().getUserDAO();
    }

    @Test
    public void testCheckUser() throws DAOException {
        Assert.assertTrue(userDAO.findUserByLogin("admin1"));
    }

    @Test
    public void testDeleteUser() throws DAOException {
        Assert.assertTrue(userDAO.deleteUser(20));
    }

    @Test
    public void testUpdatePass() throws DAOException {
        Assert.assertTrue(userDAO.updatePasswordById(20, "katyaS14"));
    }
}
