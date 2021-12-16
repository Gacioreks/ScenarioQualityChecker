package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;

public interface Visitor {
    public void visit(Step s);
    public void visit(SubScenario sc);
}
