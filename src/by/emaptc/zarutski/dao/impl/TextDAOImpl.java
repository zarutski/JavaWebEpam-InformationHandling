package by.emaptc.zarutski.dao.impl;

import by.emaptc.zarutski.dao.FileParser;
import by.emaptc.zarutski.dao.TextDAO;
import by.emaptc.zarutski.dao.TextFileReader;
import by.emaptc.zarutski.dao.exception.DaoException;
import by.emaptc.zarutski.entity.Text;

import java.util.List;

public class TextDAOImpl implements TextDAO {

    @Override
    public Text loadText() throws DaoException {
        TextFileReader reader = new TextFileReader();
        List<String> fileContents = reader.readSource();

        return FileParser.buildText(fileContents);
    }
}