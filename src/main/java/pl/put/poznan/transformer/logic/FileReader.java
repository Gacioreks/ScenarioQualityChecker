package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.base.Content;
import pl.put.poznan.transformer.base.Scenario;
import pl.put.poznan.transformer.base.Step;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class FileReader {

    public static ArrayList read(String file)
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

    public static Scenario Arr2Scen(String file)
    {
        Scenario s = new Scenario();
        ArrayList<String> list = new ArrayList<String>();
        list = read(file);

        s.title = (String) list.get(0);
        s.actors.add(list.get(1)); //jeden element, pozniej rozszerzyc
        s.systemActor = (String) list.get(2);

        Step step = new Step();
        for(int i=3;i<list.size();i++)
        {
            step.value=list.get(i);
            s.content.content.add(step);
        }
        return s;
    }
}
