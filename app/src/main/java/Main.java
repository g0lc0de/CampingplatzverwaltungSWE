import de.dhbwka.swe.utils.app.ComponentTest;
import de.dhbwka.swe.utils.util.CSVReader;
import de.dhbwka.swe.utils.util.IOUtilities;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
//        try {
//            CSVReader readerGruppe = new CSVReader("de/dhbwka/swe/csvFiles/Gruppe.csv");
//            List<String[]> a = readerGruppe.readData();
//            System.out.println(a);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        ComponentTest.main(args);
    }
}
