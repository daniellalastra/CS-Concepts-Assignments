import java.util.ArrayList;

public class DoNode {
    String id;
    Expression from;
    Expression to;
    ArrayList<Object> body;
    Memory memory;

    DoNode(String id, Expression from, Expression to, ArrayList<Object> body, Memory memory) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.body = body;
        this.memory = memory;
    }

    public void execute() {
        memory.set(id, from.evaluate());
        while (memory.get(id) <= to.evaluate()) {
            for (Object o : body) {
                if (o instanceof AssignmentNode) {
                    ((AssignmentNode) o).execute();
                } else if (o instanceof PrintNode) {
                    ((PrintNode) o).execute();
                } else if (o instanceof ReadNode) {
                    ((ReadNode) o).execute();
                }
            }
            memory.set(id, memory.get(id) + 1);
        }
    }
}
