package by.emaptc.zarutski.entity;

import java.util.Objects;

public class CodeBlock implements TextComponent{

    private String value = null;

    public CodeBlock(String value) {
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
        CodeBlock block = (CodeBlock) o;
        return Objects.equals(value, block.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "CodeBlock{" +
                "value='" + value + '\'' +
                '}';
    }
}
