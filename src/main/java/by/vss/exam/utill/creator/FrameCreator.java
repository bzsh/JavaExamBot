package by.vss.exam.utill.creator;

import java.util.ArrayList;
import java.util.List;

public class FrameCreator {

    public static String createFrameStringMessage(String text, String filler) {
        StringBuilder textBuilder = new StringBuilder();
        String[] arr = text.split("\\s");
        List<String> rows = new ArrayList<>();
        int lengthCounter = 0;

        for (String str : arr) {
            if (lengthCounter + str.length() < 20) {
                textBuilder.append(str).append(" ");
            } else if (lengthCounter + str.length() > 20 && lengthCounter + str.length() < 23) {
                textBuilder.append(str);
                rows.add(" " + textBuilder.toString());
                textBuilder = new StringBuilder();
                lengthCounter = 0;
            } else if (lengthCounter + str.length() > 23) {
                rows.add(" " + textBuilder.toString());
                textBuilder = new StringBuilder();
                lengthCounter = 0;
                textBuilder.append(str).append(" ");
            } else {
                textBuilder.append(str).append(" ");
            }
            lengthCounter += str.length() + 1;
        }
        rows.add(" " + textBuilder.toString());

        StringBuilder fillerBuilder = new StringBuilder();

        int width = 26;
        int height = 16;
        int rowSize = rows.size();
        int up = (height - rowSize) / 2;

        for (int i = 0; i < up; i++) {
            fillerBuilder.append(filler);
            for (int j = 0; j < width; j++) {
                fillerBuilder.append(" ");
            }
            fillerBuilder.append("\n");
        }

        for (String row : rows) {
            int tempWidth = width - row.length();

            fillerBuilder.append(filler);
            for (int i = 0; i < tempWidth / 2; i++) {
                fillerBuilder.append(" ");
            }
            fillerBuilder.append(row);
            fillerBuilder.append("\n");
        }

        for (int i = 0; i < up; i++) {
            fillerBuilder.append(filler);

            fillerBuilder.append("\n");
        }
        fillerBuilder.append("╚");
        for (int j = 0; j < width; j++) {
            fillerBuilder.append("═");
        }
        return fillerBuilder.toString();
    }
}
