package by.emaptc.zarutski.dao;

import by.emaptc.zarutski.dao.impl.TextDAOImpl;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private final TextDAO textDAO = new TextDAOImpl();

    private DAOFactory() {
    }

    public TextDAO getTextDAO() {
        return textDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }
}
