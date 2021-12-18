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

        this.title = list.get(0);
        this.actors = new ArrayList<String>(Arrays.asList(list.get(1).split(",")));
        this.systemActor = list.get(2);
    }

    public Scenario(Scenario tmp) {
        this.title=tmp.title;
        this.actors=tmp.actors;
        this.systemActor=tmp.systemActor;
        this.mySubScenario=tmp.mySubScenario;
        this.startInt=tmp.startInt;
        this.list=tmp.list;
    }

    public void Scenarioshow() {
        Scenario tmp=new Scenario(this);
        tmp.mySubScenario.addContent(startInt, list, 0);
        tmp.mySubScenario.accept(new Visitor() {
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
        this.mySubScenario.numerized(startInt, list, 0, 0, "");
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
        Scenario tmp=new Scenario(this);
        tmp.mySubScenario.lvlshow(startInt, list, 0,stop);
        tmp.mySubScenario.accept(new Visitor() {
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

    public void Stepscount() {
        this.mySubScenario.step_counter(this.mySubScenario);
        this.mySubScenario.get_steps_count();
    }

    public void Keywords(){
        this.mySubScenario.key_word_counter(this.mySubScenario);
        this.mySubScenario.get_key_words_count();
    }

    public void Stepscheck(){
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
