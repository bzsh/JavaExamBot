package by.vss.exam.utill;

public class IdCreator {
    private static long id = 0;

    private IdCreator() {
    }

    public static Long createId() {
        return id++;
    }
}
