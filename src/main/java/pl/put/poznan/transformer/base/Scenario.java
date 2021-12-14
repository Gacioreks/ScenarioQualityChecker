package pl.put.poznan.transformer.base;

import pl.put.poznan.transformer.logic.Visitor;

import java.util.ArrayList;

public class Scenario {
    public String title;
    public ArrayList<String> actors;
    public String systemActor;
    public ArrayList<Step> steps;

    Scenario(){
        actors = new ArrayList<String>();
        steps = new ArrayList<Step>();
    }

    public void accept(Visitor v){
        v.visit(this);
        for (Step s : this.steps){
            s.accept(v);
        }
    }
}
