package cn.edu.nju.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by njucjc at 2018/9/29
 */
public class FileHelper {

    public static List<String> readFile (String filePath) {

        List<String> strLines = new ArrayList<>();

        BufferedReader bufferedReader;
        try {
            FileReader fr = new FileReader(filePath);
            bufferedReader = new BufferedReader(fr);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                strLines.add(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return strLines;
    }
}
