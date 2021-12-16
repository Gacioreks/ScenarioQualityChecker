package pl.put.poznan.transformer.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.transformer.base.Scenario;
import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;
import pl.put.poznan.transformer.logic.FileReader;
import pl.put.poznan.transformer.logic.Visitor;

import java.lang.reflect.Field;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class Application {

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        ///SpringApplication.run(Application.class, args);

        //Scenario x = FileReader.arr2Scenario("file11.txt");
        Scenario x = new Scenario("file11.txt");
        int n = x.mySubScenario.content.size();

        System.out.println("Tytuł: "+x.title);
        System.out.println("Aktorzy: "+x.actors);
        System.out.println("Aktor systemowy: "+x.systemActor);

        //for(int i=0;i<n;i++) System.out.println(x.mySubScenario.content.get(i).getClass());
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

    }
}
