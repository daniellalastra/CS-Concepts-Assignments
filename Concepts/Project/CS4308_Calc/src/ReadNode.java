import java.util.Scanner;

public class ReadNode {
    String name;
    Memory memory;

    public ReadNode(String name, Memory memory) {
        this.name = name;
        this.memory = memory;
    }
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        int value = scanner.nextInt();
        memory.set(name,value);
    }
}
