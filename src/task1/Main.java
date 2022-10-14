package task1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Path path = Path.of(scanner.nextLine());
        try {
            Reader.readDoc(path);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

class Reader {

    public static void readDoc(Path path) throws IOException {
        List<String> strings = Files.readAllLines(path);

        BufferedWriter validWriter = Files.newBufferedWriter(Path.of("ValidStrings.txt"));
        BufferedWriter invalidWriter = Files.newBufferedWriter(Path.of("InvalidStrings.txt"));
        for (String s : strings) {
            try {
                Condition.validationTrue(s);
            } catch (ValidException e) {
                System.out.println(e.getMessage());
            }

            if (s.length() == 15 && (s.startsWith("docnum") || s.startsWith("contract"))) {
                validWriter.write(s + "\n");
            } else {
                invalidWriter.write(s + "\n");
            }
        }


        validWriter.close();
        invalidWriter.close();
    }
}

class Condition {
    public static void validationTrue(String s) throws ValidException {
        if (s.length() != 15 && (!s.startsWith("docnum") || !s.startsWith("contract"))) {
            throw new ValidException("Номер " + s + " дожен начинаться c docnum или contract и состоять из 15 символов");
        }
    }
}

