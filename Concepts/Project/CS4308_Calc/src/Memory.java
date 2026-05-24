import java.util.HashMap;

public class Memory {
    private HashMap<String, Integer> memory;
    public Memory() {
        memory = new HashMap<>();
    }
    public int get(String name) {
        return memory.getOrDefault(name, 0);
    }
    public void set(String name, int value) {
        memory.put(name, value);
    }

}
