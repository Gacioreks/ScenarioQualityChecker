package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.base.Scenario;
import pl.put.poznan.transformer.base.Step;

public interface Visitor {
    public void visit(Step s);
    public void visit(Scenario sc);
}
