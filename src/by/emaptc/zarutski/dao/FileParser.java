package by.emaptc.zarutski.dao;

import by.emaptc.zarutski.entity.*;

import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser {

    public static final String REGEX_DELIMITER_SENTENCES = "\\. ";
    public static final String REGEX_DELIMITER_SENTENCE_PARTS = " ";
    public static final String REGEX_WORD = "([A-Za-z]+)";
    public static final String REGEX_PARAGRAPH_NUMBER = "[\\d+.]+";

    public static final String DOT = ".";
    public static final String OPENED_CURLY_BRACE = "{";
    public static final String CLOSED_CURLY_BRACE = "}";
    public static final String EMPTY_STRING = "";

    public static Pattern wordPattern = Pattern.compile(REGEX_WORD);
    public static Pattern paragraphPattern = Pattern.compile(REGEX_PARAGRAPH_NUMBER);


    public static Text buildText(List<String> textLines) {
        Text text = new Text();

        boolean readingCodeBlock = false;
        Stack<String> bracesStack = new Stack<>();
        StringBuilder codeBlockContents = new StringBuilder();

        for (String line : textLines) {

            // нумерованный параграф
            if (isParagraph(line)) {
                ParagraphNumber paragraphNumber = new ParagraphNumber(getParagraphNumber(line));
                text.addComponent(paragraphNumber);
                text.addComponent(Whitespace.getInstance());

                String paragraphSentence = line.replace(paragraphNumber.getValue(), EMPTY_STRING);
                String[] sentenceParts = getSentenceParts(paragraphSentence.trim());

                Sentence paragraphText = buildSentence(sentenceParts, true);
                text.addComponent(paragraphText);
                text.addComponent(NewLine.getInstance());
                continue;
            }


            // CodeBlock
            if (line.contains(OPENED_CURLY_BRACE)) {
                bracesStack.push(OPENED_CURLY_BRACE);
                readingCodeBlock = true;
            }
            if (line.contains(CLOSED_CURLY_BRACE)) {
                bracesStack.pop();
            }
            if (readingCodeBlock) {
                codeBlockContents.append(line);
                codeBlockContents.append(NewLine.getInstance().getValue());
            }
            if (readingCodeBlock && bracesStack.isEmpty()) {
                CodeBlock block = new CodeBlock(codeBlockContents.toString());
                text.addComponent(block);
                readingCodeBlock = false;
                codeBlockContents = new StringBuilder();
                continue;
            }
            if (readingCodeBlock) {
                continue;
            }


            if (line.isEmpty()) {
                text.addComponent(NewLine.getInstance());
                continue;
            }


            // предложения
            String[] sentences = parseIntoSentences(line);
            int sentencesCount = sentences.length;
            boolean isLastSentence = false;
            String sentenceText;

            for (int ind = 0; ind < sentencesCount; ind++) {

                sentenceText = sentences[ind];
                if (ind == (sentencesCount - 1)) {
                    isLastSentence = true;
                    sentenceText = sentenceText.replace(DOT, EMPTY_STRING);
                }

                String[] sentenceParts = getSentenceParts(sentenceText);
                Sentence sentence = buildSentence(sentenceParts, isLastSentence);

                if (isLastSentence && endsWithDot(line)) {
                    sentence.addComponent(DotMark.getInstance());
                }

                text.addComponent(sentence);
                if (isLastSentence) {
                    text.addComponent(NewLine.getInstance());
                }
            }
        }
        return text;
    }


    private static boolean endsWithDot(String s) {
        return s.endsWith(DOT);
    }


    private static Sentence buildSentence(String[] sentenceParts, boolean isLastSentence) {

        Sentence sentence = new Sentence();
        for (int i = 0; i < sentenceParts.length; i++) {

            String sentencePart = sentenceParts[i];
            if (containsWord(sentencePart)) {

                Matcher matcher = wordPattern.matcher(sentencePart);
                if (matcher.find()) {
                    String word = matcher.group(1);
                    String unparsable = sentencePart.replaceAll(word, EMPTY_STRING);
                    sentence.addComponent(new Word(word));
                    sentence.addComponent(new UnparsableElement(unparsable));
                }

            } else {
                sentence.addComponent(new UnparsableElement(sentencePart));
            }


            boolean endOfSentence = (i == sentenceParts.length - 1);
            if (!isLastSentence && endOfSentence) {
                sentence.addComponent(DotMark.getInstance());
                sentence.addComponent(Whitespace.getInstance());
                return sentence;
            }
            if (endOfSentence) {
                return sentence;
            }
            sentence.addComponent(Whitespace.getInstance());
        }
        return sentence;
    }


    // SENTENCES PARSING
    private static String[] parseIntoSentences(String s) {
        return s.split(REGEX_DELIMITER_SENTENCES);
    }

    private static String[] getSentenceParts(String s) {
        return s.split(REGEX_DELIMITER_SENTENCE_PARTS);
    }

    // WORDS PARSING
    private static boolean containsWord(String textComponent) {
        Matcher matcher = wordPattern.matcher(textComponent);

        if (matcher.find()) {
            String word = matcher.group(1);
            return textComponent.startsWith(word);
        }
        return false;
    }

    // PARAGRAPH PARSING
    private static boolean isParagraph(String s) {
        Matcher paragraphMatcher = paragraphPattern.matcher(s);

        String paragraph;
        if (paragraphMatcher.find()) {
            paragraph = paragraphMatcher.group();
        } else {
            return false;
        }
        return s.startsWith(paragraph);
    }

    private static String getParagraphNumber(String s) {
        Matcher paragraphMatcher = paragraphPattern.matcher(s);

        if (paragraphMatcher.find()) {
            return paragraphMatcher.group();
        }
        return s;
    }
}
