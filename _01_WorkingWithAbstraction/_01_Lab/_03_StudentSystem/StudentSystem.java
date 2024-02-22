package _01_WorkingWithAbstraction._01_Lab._03_StudentSystem;


import static  _01_WorkingWithAbstraction._01_Lab._03_StudentSystem.ConsolePrinter.printLine;
public class StudentSystem {
    private CommandHandler handler;
    private boolean isWorking;

    public StudentSystem() {
        this.handler = new CommandHandler();
    }

    public void start() {
        this.isWorking = true;
        while (isWorking) {
            String result = handler.handleCommand(Reader.readStringArray("\\s+"));
            if (result != null && !result.equals("Exit")) {
                printLine(result);
            }
            isWorking = !"Exit".equals(result);
        }
    }
}
