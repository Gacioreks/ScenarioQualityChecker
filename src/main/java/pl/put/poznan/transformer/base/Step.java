package pl.put.poznan.transformer.base;

import pl.put.poznan.transformer.logic.Visitor;

import java.util.ArrayList;

public class Step extends Content {

    public Step() {
        value = "";
    }

    public Step(String tmp) {
        value = tmp;
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
