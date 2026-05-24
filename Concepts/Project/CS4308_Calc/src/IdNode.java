public class IdNode implements Expression {
    private String name;
    private Memory memory;
    @Override
    public int evaluate() {
        return memory.get(name);
    }
    public IdNode(String name, Memory memory) {
        this.name = name;
        this.memory = memory;
    }
}
