package pl.put.poznan.transformer.app;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.transformer.base.Scenario;
import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;
import pl.put.poznan.transformer.logic.Visitor;



@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class Application {

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, FileNotFoundException, JSONException {
        //SpringApplication.run(Application.class, args);

        String ofile = "file11.txt";

        PrintWriter output = new PrintWriter("with_steps-" + ofile);

        //Obiekty scenariusze
        //Scenario x = new Scenario(ofile);

        JSONObject scen = new JSONObject();
        scen.put("Tytul", "Dodanie książki");
        JSONArray aktorzy = new JSONArray();
        aktorzy.put("Bibliotekarz");
        aktorzy.put("Sekretarka");
        aktorzy.put("Nauczyciel");
        scen.put("Aktorzy", aktorzy);
        scen.put("Aktor systemowy", "System");

        JSONArray substep1 = new JSONArray();
        substep1.put(new JSONObject().put("Step","Bibliotekarz wybiera opcje dodania nowej pozycji książkowej"));
        substep1.put(new JSONObject().put("Step","Wyświetla się formularz."));
        substep1.put(new JSONObject().put("Step","Bibliotekarz podaje dane książki."));

        JSONArray substep2 = new JSONArray();
        substep2.put(new JSONObject().put("Step","IF: Bibliotekarz pragnie dodać egzemplarze książki"));
        substep2.put(new JSONObject().put("Step","Bibliotekarz wybiera opcję definiowania egzemplarzy"));
        substep2.put(new JSONObject().put("Step","System prezentuje zdefiniowane egzemplarze"));

        JSONArray substep3 = new JSONArray();
        substep3.put(new JSONObject().put("Step","FOR EACH egzemplarz:"));
        substep3.put(new JSONObject().put("Step","Bibliotekarz wybiera opcję dodania egzemplarza"));
        substep3.put(new JSONObject().put("Step","System prosi o podanie danych egzemplarza"));
        substep3.put(new JSONObject().put("Step","Bibliotekarz podaje dane egzemplarza i zatwierdza."));
        substep3.put(new JSONObject().put("Step","System informuje o poprawnym dodaniu egzemplarza i prezentuje zaktualizowaną listę egzemplarzy."));

        substep2.put(substep3);

        substep1.put(substep2);

        substep1.put(new JSONObject().put("Step","Bibliotekarz zatwierdza dodanie książki."));
        substep1.put(new JSONObject().put("Step","System informuje o poprawnym dodaniu książki."));
        substep1.put(new JSONObject().put("Step","Bibliotekarz zatwierdza dodanie książki."));
        substep1.put(new JSONObject().put("Step","System informuje o poprawnym dodaniu książki."));

        scen.put("Content", substep1);

        FileWriter file = null;
        //private static FileWriter file;
        try {
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("./files/file20.txt");
            file.write(scen.toString());

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
/*
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

 */
    }
}
