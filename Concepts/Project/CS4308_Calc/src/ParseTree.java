import java.util.ArrayList;

public class ParseTree {
    private ArrayList<Object> program;
    public ParseTree(ArrayList<Object> program) {
        this.program = program;
    }
    public void execute() {
        for (Object stmt : program){
            if (stmt instanceof AssignmentNode){
                ((AssignmentNode) stmt).execute();
            }else if (stmt instanceof PrintNode){
                ((PrintNode) stmt).execute();
            }else if (stmt instanceof ReadNode){
                ((ReadNode) stmt).execute();
            } else if (stmt instanceof IfNode) {
                ((IfNode) stmt).execute();
            }else if (stmt instanceof DoWhileNode) {
                ((DoWhileNode) stmt).execute();
            }else if (stmt instanceof DoNode) {
                ((DoNode) stmt).execute();
            }
        }
    }
}
