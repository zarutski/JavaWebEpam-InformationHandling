package by.emaptc.zarutski.main.print;

import by.emaptc.zarutski.entity.Text;
import by.emaptc.zarutski.entity.TextComponent;

import java.util.List;

public class PrintText {

    public static void printText(Text text) {
        List<TextComponent> textContents = text.getTextContents();

        for (TextComponent t : textContents) {
            System.out.print(t.getValue());
        }
    }
}
