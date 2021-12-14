package pl.put.poznan.transformer.base;

import pl.put.poznan.transformer.logic.Visitor;

import java.util.ArrayList;

public class Scenario {
    public String title;
    public ArrayList<String> actors;
    public String systemActor;
    public SubScenario mySubScenario;

    public Scenario(){
        title = new String();
        actors = new ArrayList<String>();
        systemActor = new String();
        mySubScenario = new SubScenario();
    }
/*
    public void accept(Visitor v){
        v.visit(this);
        for (Step s : this.steps){
            s.accept(v);
        }
    }

 */
}
