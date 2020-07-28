package by.emaptc.zarutski.dao;

import by.emaptc.zarutski.dao.exception.DaoException;
import by.emaptc.zarutski.entity.Text;

public interface TextDAO {

    Text loadText() throws DaoException;
}