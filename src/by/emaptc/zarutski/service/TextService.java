package by.emaptc.zarutski.service;

import by.emaptc.zarutski.entity.Text;
import by.emaptc.zarutski.service.exception.ServiceException;

public interface TextService {

    Text getText() throws ServiceException;

    void printSentencesByWordsCount(Text text);

    void swapSentenceFirstLastWords(Text text);

    void printWordsAlphabetOrder(Text text);

}
