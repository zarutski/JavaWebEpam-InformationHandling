package by.emaptc.zarutski.entity;

import java.util.Objects;

public class UnparsableElement implements TextComponent {

    private String value = null;

    public UnparsableElement(String value) {
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
        UnparsableElement that = (UnparsableElement) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "UnparsableElement{" +
                "value='" + value + '\'' +
                '}';
    }
}
