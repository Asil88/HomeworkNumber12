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

        for (String s : strings) {
            if (s.length() == 15 && (s.startsWith("docnum") || s.startsWith("contract"))) {
                BufferedWriter validWriter = Files.newBufferedWriter(Path.of("ValidStrings.txt"));
                validWriter.write(s);

            } else {
                BufferedWriter invalidWriter = Files.newBufferedWriter(Path.of("InvalidStrings.txt"));
                invalidWriter.write(s);
            }
        }
    }

}