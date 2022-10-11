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
                BufferedWriter bufferedWriter1 = Files.newBufferedWriter(Path.of("ValidStrings.txt"));
                bufferedWriter1.write(s);

            } else {
                BufferedWriter bufferedWriter2 = Files.newBufferedWriter(Path.of("InvalidStrings.txt"));
                bufferedWriter2.write(s);
            }
        }
    }

}