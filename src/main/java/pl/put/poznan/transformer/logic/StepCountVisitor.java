package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;

import java.util.Objects;

public class StepCountVisitor implements Visitor{

    private int quantity = 0;

    @Override
    public void visit(Step s) {
        if(!Objects.equals(s.value, "")) {
            this.quantity += 1;
        }
    }

    @Override
    public void visit(SubScenario sc) {

    }

    public int getStepCount(){
        System.out.println("Liczba krokow: " + (this.quantity));
        int out = this.quantity;
        this.quantity = 0;
        return out;
    }
}
