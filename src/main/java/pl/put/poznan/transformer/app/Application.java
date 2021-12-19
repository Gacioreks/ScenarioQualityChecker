package pl.put.poznan.transformer.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.transformer.base.Scenario;

@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class Application {

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException{
        //SpringApplication.run(Application.class, args);



        //Obiekty scenariusze
        Scenario x = new Scenario();
        Scenario xx = new Scenario();
        Scenario xxx = new Scenario();

        ////Wyświetlenie scenariusza
        System.out.println("\n"+"Wyświetlenie scenariusza");
        x.Scenarioshow();

        ////Wyświetlenie scenariusza do określonego poziomu zagłębienia (poziom główny to 1)
        System.out.println("\n"+"Scenariusz do poziomu zagłebienia");
        xx.Scenariolvlshow(2);

        ////Wyświetlenie scenariusza z numeracją korków
        System.out.println("\n"+"Scenariusz z numeracją kroków");
        xxx.Scenarionumershow();

        ////Wyświetlenie liczby kroków scenariusza
        System.out.println("\n" + "Liczba kroków");
        xxx.Stepscount();

        ////Wyświetlanie liczby słów kluczowych
        System.out.println("\n" + "Słowa kluczowe");
        xxx.Keywords();

        ////Wyświetlanie niepoprawnych kroków scenariusza
        System.out.println("\n" + "Niepoprawne kroki");
        xxx.Stepscheck();

    }
}