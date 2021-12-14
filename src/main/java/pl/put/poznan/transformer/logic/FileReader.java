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

    public static Scenario arr2Scenario(String file)
    {
        ArrayList<String> list;
        list = read(file);
        int n = list.size();
        Scenario s = new Scenario();

        s.title = list.get(0);
        s.actors.add(list.get(1)); //jeden element, pozniej rozszerzyc
        s.systemActor = list.get(2);

        ArrayList<Step> steps = new ArrayList<Step>(n-3);
        for(int i=3;i<n;i++)
        {
            steps.add(i-3,new Step(list.get(i)));
            //System.out.println(steps.get(i-3).value);
        }
        s.mySubScenario.content = steps;

        return s;
    }
}
