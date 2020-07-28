package by.emaptc.zarutski.entity;

public class DotMark implements TextComponent {

    private static final String VALUE = ".";
    private static final DotMark INSTANCE = new DotMark();

    private DotMark() {
    }

    public static DotMark getInstance() {
        return INSTANCE;
    }

    @Override
    public String getValue() {
        return VALUE;
    }

    @Override
    public String toString() {
        return "DotMark{" + VALUE + "}";
    }
}