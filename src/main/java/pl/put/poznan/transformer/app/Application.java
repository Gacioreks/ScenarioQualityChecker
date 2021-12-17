package pl.put.poznan.transformer.app;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.transformer.base.Scenario;
import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;
import pl.put.poznan.transformer.logic.Visitor;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class Application {

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, FileNotFoundException {
        //SpringApplication.run(Application.class, args);

        String ofile = "file11.txt";

        PrintWriter output = new PrintWriter("with_steps-" + ofile);

        Scenario x = new Scenario(ofile);

        System.out.println("Tytuł: "+x.title);
        System.out.print("Aktorzy: ");
        x.actors.forEach(System.out::print);
        System.out.print("\n");
        System.out.println("Aktor systemowy: "+x.systemActor+"\n");

        ////Wyświetlenie scenariusza
        //System.out.println("Wyświetlenie scenariusza");
        //x.Scenarioshow();

        ////Wyświetlenie scenariusza z numeracją korków
        //System.out.println("Scenariusz z numeracją kroków");
        //x.Scenarionumershow();

        ////Wyświetlenie scenariusza do określonego poziomu zagłębienia (poziom główny to 1)
        System.out.println("Scenariusz do poziomu zagłebienia");
        x.Scenariolvlshow(2);

        ////Wyświetlenie liczby kroków scenariusza
        //x.Stepscount();

        ////Wyświetlanie liczby słów kluczowych
        //x.Keywords();

        ////Wyświetlanie niepoprawnych kroków scenariusza
        //x.Stepscheck();

        ////Zapis scenariusza do pliku
        //x.Savetofile(output);

        x.mySubScenario.accept(new Visitor() {
            @Override
            public void visit(Step s) {
                ;
            }

            @Override
            public void visit(SubScenario sc) {
                ;
            }
        });

        output.close();
    }
}
