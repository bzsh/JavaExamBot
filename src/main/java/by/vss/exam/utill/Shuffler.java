package by.vss.exam.utill;

import java.util.ArrayList;
import java.util.Random;

public class Shuffler {

    private Shuffler() {
    }

    public static ArrayList<Long> getShuffledList(int numberOfTasks, int size) {
        ArrayList<Long> numbersGenerated = new ArrayList<>(numberOfTasks);

        for (int i = 0; i < numberOfTasks; i++) {
            Random randNumber = new Random();
            long iNumber = randNumber.nextInt(size) + 1;

            if (!numbersGenerated.contains(iNumber)) {
                numbersGenerated.add(iNumber);
            } else {
                i--;
            }
        }
        return numbersGenerated;
    }
}
