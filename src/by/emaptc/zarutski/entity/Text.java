package by.emaptc.zarutski.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Text implements TextComponent {

    private final List<TextComponent> textContents = new ArrayList<>();

    @Override
    public String getValue() {
        StringBuilder builder = new StringBuilder();
        for (TextComponent component : textContents) {
            builder.append(component.getValue());
        }

        return builder.toString();
    }

    public List<TextComponent> getTextContents() {
        return textContents;
    }

    public void addComponent(TextComponent textComponent) {
        textContents.add(textComponent);
    }

    public void removeComponent(TextComponent textComponent) {
        textContents.remove(textComponent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Text text = (Text) o;
        return Objects.equals(textContents, text.textContents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textContents);
    }

    @Override
    public String toString() {
        return "Text{" +
                "textContents=" + textContents +
                '}';
    }
}