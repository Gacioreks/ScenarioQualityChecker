package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;

public class ShowScenarioVisitor implements Visitor{

    @Override
    public void visit(SubScenario sc) {

    }

    @Override
    public void visit(Step s) {
        StringBuilder tmp = new StringBuilder();
        for(int i=0;i<s.level;i++) tmp.append("\t");

        System.out.println(tmp+s.value);

    }
}
