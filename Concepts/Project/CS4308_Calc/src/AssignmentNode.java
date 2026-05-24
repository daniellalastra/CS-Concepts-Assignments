public class AssignmentNode {
String name;
Expression expression;
Memory memory;

public AssignmentNode(String name, Expression expression, Memory memory) {
    this.name = name;
    this.expression = expression;
    this.memory = memory;
}
public void execute() {
    int value = expression.evaluate();
    memory.set(name, value);
    }
}