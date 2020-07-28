package by.emaptc.zarutski.entity;

public class Whitespace implements TextComponent {

    private static final String VALUE = " ";
    private static final Whitespace INSTANCE = new Whitespace();

    private Whitespace() {
    }

    public static Whitespace getInstance() {
        return INSTANCE;
    }

    @Override
    public String getValue() {
        return VALUE;
    }

    @Override
    public String toString() {
        return "Whitespace{" + VALUE + "}";
    }
}
