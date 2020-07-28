package by.emaptc.zarutski.service.impl;

import by.emaptc.zarutski.dao.DAOFactory;
import by.emaptc.zarutski.dao.TextDAO;
import by.emaptc.zarutski.dao.exception.DaoException;
import by.emaptc.zarutski.entity.Sentence;
import by.emaptc.zarutski.entity.Text;
import by.emaptc.zarutski.entity.TextComponent;
import by.emaptc.zarutski.entity.Word;
import by.emaptc.zarutski.service.TextService;
import by.emaptc.zarutski.service.exception.ServiceException;

import java.util.*;

public class TextServiceImpl implements TextService {

    @Override
    public Text getText() throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        TextDAO dao = factory.getTextDAO();

        Text text = null;
        try {
            text = dao.loadText();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return text;
    }

    @Override
    public void printSentencesByWordsCount(Text text) {
        List<Sentence> sentences = getSentences(text);
        sentences.sort(Comparator.comparingInt(o -> getWords(o).size()));

        for (Sentence sentence : sentences) {
            System.out.println(sentence.getValue());
        }
    }

    @Override
    public void swapSentenceFirstLastWords(Text text) {

        List<Sentence> sentences = getSentences(text);

        for (Sentence s : sentences) {
            List<TextComponent> sentenceContents = s.getSentenceContents();
            List<Word> words = getWords(s);

            Word first = words.get(0);
            Word last = words.get(words.size() - 1);
            TextComponent current;

            for (int i = 0; i < sentenceContents.size(); i++) {
                current = sentenceContents.get(i);

                if (current == first) {
                    sentenceContents.set(i, last);
                    continue;
                }

                if (current == last) {
                    sentenceContents.set(i, first);
                }
            }
        }

    }

    @Override
    public void printWordsAlphabetOrder(Text text) {

        Set<String> wordsValues = new TreeSet<>();

        List<Sentence> sentences = getSentences(text);
        for (Sentence sentence : sentences) {
            List<Word> words = getWords(sentence);
            for (Word w : words) {
                wordsValues.add(w.getValue().toLowerCase());
            }
        }

        String previousLetter = null;
        String startsWithLetter;

        for (String s : wordsValues) {
            startsWithLetter = s.substring(0, 1);

            if (previousLetter == null) {
                previousLetter = startsWithLetter;
            }

            if (!startsWithLetter.equals(previousLetter)) {
                System.out.println();
                previousLetter = startsWithLetter;
            }
            System.out.print(s + " ");
        }
    }

    private List<Sentence> getSentences(Text text) {
        List<Sentence> sentences = new ArrayList<>();
        List<TextComponent> textComponents = text.getTextContents();

        for (TextComponent component : textComponents) {
            if (component.getClass().equals(Sentence.class)) {
                Sentence sentence = (Sentence) component;
                sentences.add(sentence);
            }
        }

        return sentences;
    }

    private List<Word> getWords(Sentence sentence) {
        List<Word> words = new ArrayList<>();
        List<TextComponent> sentenceContents = sentence.getSentenceContents();

        for (TextComponent component : sentenceContents) {
            if (component.getClass().equals(Word.class)) {
                Word word = (Word) component;
                words.add(word);
            }
        }

        return words;
    }
}