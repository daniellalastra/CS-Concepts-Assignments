import java.util.ArrayList;

public class DoWhileNode {
    LogicalExpression condition;
    ArrayList<Object> body;
    public DoWhileNode(LogicalExpression condition, ArrayList<Object> body) {
        this.condition = condition;
        this.body = body;
    }

    public void execute() {
        while (condition.evaluate()==1) {
            for (Object o : body) {
                if (o instanceof AssignmentNode) {
                    ((AssignmentNode) o).execute();
                } else if (o instanceof PrintNode) {
                    ((PrintNode) o).execute();
                } else if (o instanceof ReadNode) {
                    ((ReadNode) o).execute();
                }
            }
        }
    }
}
