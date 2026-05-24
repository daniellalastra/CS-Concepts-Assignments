public class BinaryExpression implements Expression {
    private Expression left;
    private Expression right;
    private char operator;
    public BinaryExpression(Expression left, Expression right, char operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public int evaluate() {
        int leftValue = left.evaluate();
        int rightValue = right.evaluate();

        if (operator == '+') {
            return leftValue + rightValue;
        }else if (operator == '-') {
            return leftValue - rightValue;
        }else if (operator == '*') {
            return leftValue * rightValue;
        }else if (operator == '/'){
            return leftValue / rightValue;
        }
        return 0;
    }
}
