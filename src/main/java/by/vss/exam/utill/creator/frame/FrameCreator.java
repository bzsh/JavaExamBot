package by.vss.exam.utill.creator.frame;

import java.util.ArrayList;
import java.util.List;

public class FrameCreator {

    public static String createUnCentredFrameStringMessage(String text) {
        String[] arr = text.split("\\s");
        String filler = "║";
        List<String> rows = unCentredTextBuilder(arr);

        StringBuilder fillerBuilder = new StringBuilder();

        int width = 26;
        int height = 16;
        int rowSize = rows.size();
        int up = (height - rowSize) / 2;


        for (int i = 0; i < up; i++) {
            fillerBuilder.append(filler);

//            for (int j = 0; j < width; j++) {
//                fillerBuilder.append(" ");
//            }

            fillerBuilder.append("\n");
        }

        for (String row : rows) {

            fillerBuilder.append(filler);
            fillerBuilder.append(row);
            fillerBuilder.append("\n");
        }

        for (int i = 0; i < up; i++) {
            fillerBuilder.append(filler);

            fillerBuilder.append("\n");
        }
        fillerBuilder.append("╚");
        fillerBuilder.append("═".repeat(width));
        return fillerBuilder.toString();
    }

    public static String createCentredFrameStringMessage(String text) {
        String[] arr = text.split("\\s");
        List<String> rows = centredTextBuilder(arr);
        String filler = "║";

        StringBuilder fillerBuilder = new StringBuilder();

        int width = 26;
        int height = 16;
        int rowSize = rows.size();
        int up = (height - rowSize) / 2;

        for (int i = 0; i < up; i++) {
            fillerBuilder.append(filler);
            fillerBuilder.append(" ".repeat(width));
            fillerBuilder.append("\n");
        }

        for (String row : rows) {
            int tempWidth = width - row.length();

            fillerBuilder.append(filler);
            fillerBuilder.append(" ".repeat(Math.max(0, tempWidth / 2)));
            fillerBuilder.append(row);
            fillerBuilder.append("\n");
        }
        for (int i = 0; i < up; i++) {
            fillerBuilder.append(filler);

            fillerBuilder.append("\n");
        }
        fillerBuilder.append("╚");
        fillerBuilder.append("═".repeat(width));
        return fillerBuilder.toString();
    }

    private static List<String> centredTextBuilder(String[] arr) {
        StringBuilder textBuilder = new StringBuilder();
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
        return rows;
    }

    private static List<String> unCentredTextBuilder(String[] arr) {
        StringBuilder textBuilder = new StringBuilder();
        List<String> rows = new ArrayList<>();
        int lengthCounter = 0;
        for (String str : arr) {
            if (lengthCounter + str.length() < 34) {
                textBuilder.append(str).append(" ");
            } else if (lengthCounter + str.length() > 34 && lengthCounter + str.length() < 26) {
                textBuilder.append(str);
                rows.add(" " + textBuilder.toString());
                textBuilder = new StringBuilder();
                lengthCounter = 0;
            } else if (lengthCounter + str.length() > 26) {
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
        return rows;
    }
}
