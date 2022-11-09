package lpnu.entity.enumeration;

public enum PizzaSize {
    SMALL(250),
    MEDIUM(500),
    LARGE(750);
    public final int weight;

    PizzaSize(final int weight) {
        this.weight = weight;
    }
}
