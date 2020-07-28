package by.emaptc.zarutski.entity;

public class NewLine implements TextComponent {

    private static final String VALUE = "\n";
    private static final NewLine INSTANCE = new NewLine();

    private NewLine() {
    }

    public static NewLine getInstance() {
        return INSTANCE;
    }

    @Override
    public String getValue() {
        return VALUE;
    }

    @Override
    public String toString() {
        return "NewLine{" + VALUE + "}";
    }
}
