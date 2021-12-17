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

        int n = x.mySubScenario.content.size();

        output.println("Tytuł: "+x.title);
        output.println("Aktorzy: "+x.actors);
        output.println("Aktor systemowy: "+x.systemActor);

        System.out.println("Tytuł: "+x.title);
        System.out.print("Aktorzy: ");
        x.actors.forEach(System.out::println);
        System.out.println("Aktor systemowy: "+x.systemActor);


        for (int y=0; y<x.mySubScenario.save.size(); y++){
            output.println(x.mySubScenario.save.get(y));
        }

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
