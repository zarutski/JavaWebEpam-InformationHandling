package by.emaptc.zarutski.entity;

import java.util.Objects;

public class ParagraphNumber implements TextComponent {

    private String value = null;

    public ParagraphNumber(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParagraphNumber that = (ParagraphNumber) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "ParagraphNumber{" +
                "value='" + value + '\'' +
                '}';
    }
}
