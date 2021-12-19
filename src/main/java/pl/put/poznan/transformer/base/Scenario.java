package pl.put.poznan.transformer.base;

import pl.put.poznan.transformer.logic.FileReader;
import pl.put.poznan.transformer.logic.JsonReader;
import pl.put.poznan.transformer.logic.Visitor;
import pl.put.poznan.transformer.logic.myInt;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Scenario {
    public String title;
    public ArrayList<String> actors;
    public String systemActor;
    public SubScenario mySubScenario;
    public myInt startInt;
    public myInt numInt;
    public ArrayList<String> list;

    public JsonReader json_scenario;
    public Scenario(){
        title = "";
        actors = new ArrayList<>();
        systemActor = "";
        mySubScenario = new SubScenario();
        startInt = new myInt(0);
        numInt = new myInt(3);
        json_scenario = new JsonReader();

        json_scenario.main();

        this.title = json_scenario.return_title();
        this.actors = json_scenario.return_actors();
        this.systemActor = json_scenario.systemActor;

        list = FileReader.read("ReadJson.txt");

    }


    public void Scenarioshow() {
        this.mySubScenario.addContent(startInt, list, 0);
        this.mySubScenario.accept(new Visitor() {
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

    public void Scenarionumershow() {
        this.mySubScenario.numerized(numInt, list, 0, 0, "");
        this.mySubScenario.accept(new Visitor() {
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

    public void Scenariolvlshow(int stop){
        this.mySubScenario.lvlshow(startInt, list, 0,stop);
        this.mySubScenario.accept(new Visitor() {
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

    public int Stepscount() {
        this.mySubScenario.step_counter(this.mySubScenario);
        int value = this.mySubScenario.get_steps_count();
        return value;
    }

    public void Keywords(){
        this.mySubScenario.key_word_counter(this.mySubScenario);
        this.mySubScenario.get_key_words_count();
    }

    public void Stepscheck(){
        this.mySubScenario.step_check(this.mySubScenario, actors, systemActor);
        this.mySubScenario.get_invalid_steps();
    }

    /*
    public void Savetofile(PrintWriter output){
        output.println("Tytuł: "+this.title);
        output.println("Aktorzy: "+this.actors);
        output.println("Aktor systemowy: "+this.systemActor);

        this.mySubScenario.numerized(startInt, list, 0, 0, "");

        for (int y=0; y<this.mySubScenario.save.size(); y++){
            output.println(this.mySubScenario.save.get(y));
        }
    }
    */
}
