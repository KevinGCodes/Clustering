package io;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

public class CSVLoader {

    public static String[][] load(String fileName){
        String fileBeginning = "src\\src\\io\\";
        try(Reader reader = new FileReader(fileBeginning + fileName);BufferedReader br = new BufferedReader(reader)){
            var result = br.lines().filter(line -> !line.isBlank())
                                                 .map(line -> line.strip().split(","))
                                                 .toArray(String[][]::new);
            return result;
        }catch(Exception exc){
            System.out.println(exc);
            JOptionPane.showMessageDialog(null, "CSV-File cannot be opened!");
        }return null;

    }

}
