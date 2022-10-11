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
        } catch (IOException | ValidException e) {
            System.out.println(e.getMessage());
        }
    }
}
class Reader {

    public static void readDoc(Path path) throws ValidException, IOException {
        List<String> strings = Files.readAllLines(path);
        BufferedWriter validWriter = Files.newBufferedWriter(Path.of("ValidStrings.txt"));
        BufferedWriter invalidWriter = Files.newBufferedWriter(Path.of("InvalidStrings.txt"));
        try {
            for (String s : strings) {
                if (s.length() == 15 && (s.startsWith("docnum") || s.startsWith("contract"))) {
                    validWriter.write(s + "\n");
                }
            }
        } catch (ValidException | IOException e) {
            throw new ValidException();

        } finally {
            validWriter.close();
        }
        try {
            for (String s : strings) {
                if (s.length() != 15 && (!s.startsWith("docnum") || !s.startsWith("contract"))) {
                    invalidWriter.write(s + "\n");
                }
            }
        } catch (ValidException | IOException e) {
            System.out.println(e.getMessage());
            throw new ValidException(e);
        } finally {
            invalidWriter.close();
        }
    }
}
    class Condition {
        public static void validationTrue(String s) throws ValidException {
            if (s.length() != 15 && (!s.startsWith("docnum") || !s.startsWith("contract"))) {
                throw new ValidException("Номер " + s + " дожен начинаться c docnum или contract и состоять из 15 символов");
            }
        }
    }

