package pl.put.poznan.transformer.base;

import pl.put.poznan.transformer.logic.Visitor;
import pl.put.poznan.transformer.logic.myInt;

import java.util.ArrayList;
import java.util.Objects;

public class SubScenario {
    public ArrayList<Object> content;

    SubScenario() {
        content = new ArrayList<>();
    }

    public ArrayList<Object> addContent(myInt start, ArrayList<String> list,int lvl)
    {
        String line;

        for(;start.getValue()<list.size();start.increment()) {
            line = list.get(start.getValue());
            line=line.replaceAll("\\t+","");
            if (line.equals("<start>")) {
                start.increment();

                SubScenario sub = new SubScenario();
                sub.addContent(start,list,lvl+1);
                content.add(sub);
            } else if (Objects.equals(line, "<end>")) {
                return content;
            }
            else {
                Step s = new Step(lvl,line);
                content.add(s);
            }
        }
        return content;
    }

    public void accept(Visitor v){
        v.visit(this);
        for (Object s : this.content){
            if(s.getClass()==Step.class) ((Step) s).accept(v);
            else ((SubScenario) s).accept(v);
        }
    }
}
