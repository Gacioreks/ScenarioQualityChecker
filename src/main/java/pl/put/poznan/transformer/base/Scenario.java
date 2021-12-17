package pl.put.poznan.transformer.base;

import pl.put.poznan.transformer.logic.FileReader;
import pl.put.poznan.transformer.logic.Visitor;
import pl.put.poznan.transformer.logic.myInt;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Scenario {
    public String title;
    public ArrayList<String> actors;
    public String systemActor;
    public SubScenario mySubScenario;
    public myInt startInt;
    public ArrayList<String> list;

    public Scenario(String file){
        title = "";
        actors = new ArrayList<>();
        systemActor = "";
        mySubScenario = new SubScenario();
        startInt = new myInt(3);
        list = FileReader.read(file);

        int n = list.size();
        //Scenario s = new Scenario();

        this.title = list.get(0);
        this.actors = new ArrayList<String>(Arrays.asList(list.get(1).split(",")));
        this.systemActor = list.get(2);
    }

    public void Scenarioshow() {
        this.mySubScenario.addContent(startInt, list, 0);
    }

    public void Scenarionumershow() {
        this.mySubScenario.numerized(startInt, list, 0, 0, "");
    }

    public void Scenariolvlshow(int stop){
        this.mySubScenario.lvlshow(startInt, list, 0,stop);
    }

    public void Stepscount() {
        this.mySubScenario.addContent(startInt, list, 0);
        this.mySubScenario.step_counter(this.mySubScenario);
        this.mySubScenario.get_steps_count();
    }

    public void Keywords(){
        this.mySubScenario.addContent(startInt, list, 0);
        this.mySubScenario.key_word_counter(this.mySubScenario);
        this.mySubScenario.get_key_words_count();
    }

    public void Stepscheck(){
        this.mySubScenario.addContent(startInt, list, 0);
        this.mySubScenario.step_check(this.mySubScenario, actors, systemActor);
        this.mySubScenario.get_invalid_steps();
    }

    public void Savetofile(PrintWriter output){
        output.println("Tytuł: "+this.title);
        output.println("Aktorzy: "+this.actors);
        output.println("Aktor systemowy: "+this.systemActor);

        this.mySubScenario.numerized(startInt, list, 0, 0, "");

        for (int y=0; y<this.mySubScenario.save.size(); y++){
            output.println(this.mySubScenario.save.get(y));
        }
    }
}
