package pl.put.poznan.transformer.logic;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class FileReader {

    public static ArrayList<String> read(String file)
    {
        ArrayList<String> result = new ArrayList<>();
        try
        {
            File myFile = new File("files\\"+file);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine())
            {
                String data = myReader.nextLine();
                result.add(data);
            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("file error");
            e.printStackTrace();
        }
        return result;
    }
}
