public class PrintNode {
    String name;
    Memory memory;

    public PrintNode(String name, Memory memory) {
        this.name = name;
        this.memory = memory;
    }
    public void execute(){
        System.out.println(memory.get(name));
    }
}
