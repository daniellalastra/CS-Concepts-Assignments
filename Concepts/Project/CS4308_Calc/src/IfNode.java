import java.util.ArrayList;

public class IfNode {
    LogicalExpression condition;
    ArrayList<Object> thenBranch;
    ArrayList<Object> elseBranch;

    public IfNode(LogicalExpression condition, ArrayList<Object> thenBranch, ArrayList<Object> elseBranch) {
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }
    public void execute() {
        if (condition.evaluate() == 1 ){
            for (Object o : thenBranch) {
                if (o instanceof AssignmentNode) {
                    ((AssignmentNode) o).execute();
                } else if (o instanceof PrintNode) {
                    ((PrintNode) o).execute();
                } else if (o instanceof ReadNode) {
                    ((ReadNode) o).execute();
                }
            }
        }else{
            for (Object o : elseBranch) {
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
