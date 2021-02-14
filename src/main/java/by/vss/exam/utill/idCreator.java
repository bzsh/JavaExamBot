package by.vss.exam.utill;

public class idCreator {
    private static long id = 0;

    public static Long createId(){
        return id++;
    }
}
