package pl.put.poznan.transformer.app;

        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import pl.put.poznan.transformer.base.Scenario;
        import pl.put.poznan.transformer.logic.FileReader;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        Scenario x = FileReader.arr2Scenario("file1.txt");
        System.out.println(x.title);
        System.out.println(x.actors);
        System.out.println(x.systemActor);
        for(int i=0;i<x.mySubScenario.content.size();i++)
        {
            System.out.println(x.mySubScenario.content.get(i).value);
        }
    }
}
