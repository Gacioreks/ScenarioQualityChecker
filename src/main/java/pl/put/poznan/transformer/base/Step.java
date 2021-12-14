package pl.put.poznan.transformer.base;

import pl.put.poznan.transformer.logic.Visitor;

import java.util.ArrayList;

public class Step {
    public String content;
    public ArrayList<Step> steps;

    Step() {
        steps = new ArrayList<Step>();
    }

    public void accept(Visitor v){
        v.visit(this);
        for (Step s : this.steps){
            s.accept(v);
        }

    }
}
