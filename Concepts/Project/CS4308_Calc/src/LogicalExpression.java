public class LogicalExpression implements Expression {
    private Expression left;
    private Expression right;
    TokenType operator;
    public LogicalExpression(Expression left, Expression right, TokenType operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }
    public int evaluate(){
        int leftValue = left.evaluate();
        int rightValue = right.evaluate();

        if(operator == TokenType.LT){
            return leftValue < rightValue ? 1 : 0;
        } else if (operator == TokenType.LE) {
            return leftValue <= rightValue ? 1 : 0;
        } else if (operator == TokenType.GT) {
            return leftValue > rightValue ? 1 : 0;
        }else if (operator == TokenType.GE) {
            return leftValue >= rightValue ? 1 : 0;
        } else if (operator==TokenType.EQ) {
            return leftValue == rightValue ? 1 : 0;
        }else if (operator==TokenType.NE) {
            return leftValue != rightValue ? 1 : 0;
        }else {
            throw new RuntimeException("Unrecognized operator: " + operator);
        }
    }
}
