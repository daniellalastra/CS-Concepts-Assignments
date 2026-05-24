public class UnaryExpression implements Expression {
    private int value;

    public UnaryExpression(int value) {
        this.value = value;
    }

    @Override
    public int evaluate() {
        return value;
    }
}
