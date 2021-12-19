package pl.put.poznan.transformer.app;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.transformer.base.Scenario;
import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;
import pl.put.poznan.transformer.logic.FileReader;
import pl.put.poznan.transformer.logic.Visitor;



@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class Application {

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, FileNotFoundException, JSONException {
        //SpringApplication.run(Application.class, args);

        String ofile = "file12.txt";
        String fileJson="file20.json";

        PrintWriter output = new PrintWriter("with_steps-" + ofile);

        //Obiekty scenariusze
        Scenario x1 = new Scenario(ofile);

        FileWriter file = null;
        //private static FileWriter file;
        try {
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("./files/"+fileJson);
            file.write(new Gson().toJson(x1));

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File myFile = new File("files/"+fileJson);
        Scanner myReader = new Scanner(myFile);

        Gson g = new Gson();
        Scenario x = g.fromJson(myReader.nextLine(), Scenario.class);


        System.out.println("Dane scenariusza:");
        System.out.println("Tytuł: "+x.title);
        System.out.print("Aktorzy: ");
        x.actors.forEach(System.out::print);
        System.out.print("\n");
        System.out.println("Aktor systemowy: "+x.systemActor);

        ////Wyświetlenie scenariusza
        System.out.println("\n"+"Wyświetlenie scenariusza");
        x.Scenarioshow();

        ////Wyświetlenie scenariusza do określonego poziomu zagłębienia (poziom główny to 1)
        System.out.println("\n"+"Scenariusz do poziomu zagłebienia");
        //x = new Scenario(ofile);
        x.Scenariolvlshow(2);

        ////Wyświetlenie scenariusza z numeracją korków
        System.out.println("\n"+"Scenariusz z numeracją kroków");
        //x = new Scenario(ofile);
        x.Scenarionumershow();

        ////Wyświetlenie liczby kroków scenariusza
        System.out.println("\n" + "Liczba kroków");
        x.Stepscount();

        ////Wyświetlanie liczby słów kluczowych
        System.out.println("\n" + "Słowa kluczowe");
        x.Keywords();

        ////Wyświetlanie niepoprawnych kroków scenariusza
        System.out.println("\n" + "Niepoprawne kroki");
        x.Stepscheck();

        ////Zapis scenariusza do pliku
        System.out.println("\n" + "Zapis");
        x.Savetofile(output);

        output.close();


    }
}
