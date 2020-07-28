package by.emaptc.zarutski.main;

import by.emaptc.zarutski.entity.Text;
import by.emaptc.zarutski.main.print.PrintText;
import by.emaptc.zarutski.service.ServiceFactory;
import by.emaptc.zarutski.service.TextService;
import by.emaptc.zarutski.service.exception.ServiceException;

public class Main {

    public static void main(String[] args) throws ServiceException{

        ServiceFactory factory = ServiceFactory.getInstance();
        TextService textService = factory.getTextService();
        Text text = textService.getText();

        //////////////////////////////////////////////////////////////////
        textService.printSentencesByWordsCount(text);

        //////////////////////////////////////////////////////////////////
        textService.swapSentenceFirstLastWords(text);
        PrintText.printText(text);
        System.out.println();

        //////////////////////////////////////////////////////////////////
        text = textService.getText();
        textService.printWordsAlphabetOrder(text);

        //////////////////////////////////////////////////////////////////
        PrintText.printText(text);
    }
}