package by.emaptc.zarutski.dao;

import by.emaptc.zarutski.dao.exception.DaoException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TextFileReader {

    private static final String ERROR_MESSAGE = "Error reading file";

    public List<String> readSource() throws DaoException {

        List<String> contents = new ArrayList<>();
        Path filePath = FileSystems.getDefault().getPath("resources" + File.separator + "text_sample.txt");

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {

            String line = reader.readLine();
            while (line != null) {
                contents.add(line);
                line = reader.readLine();
            }

        } catch (IOException e) {
            throw new DaoException(ERROR_MESSAGE, e);
        }
        return contents;
    }
}