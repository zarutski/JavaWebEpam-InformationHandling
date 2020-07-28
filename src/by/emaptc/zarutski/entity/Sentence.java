package by.emaptc.zarutski.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sentence implements TextComponent {

    private final List<TextComponent> sentenceContents = new ArrayList<>();

    @Override
    public String getValue() {
        StringBuilder builder = new StringBuilder();
        for (TextComponent component : sentenceContents) {
            builder.append(component.getValue());
        }

        return builder.toString();
    }

    public void addComponent(TextComponent component) {
        sentenceContents.add(component);
    }

    public void removeComponent(TextComponent component) {
        sentenceContents.remove(component);
    }

    public List<TextComponent> getSentenceContents() {
        return this.sentenceContents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return Objects.equals(sentenceContents, sentence.sentenceContents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sentenceContents);
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "sentenceContents=" + sentenceContents +
                '}';
    }
}
