package pl.put.poznan.transformer.base;

import pl.put.poznan.transformer.logic.FileReader;
import pl.put.poznan.transformer.logic.Visitor;
import pl.put.poznan.transformer.logic.myInt;

import java.util.ArrayList;

public class Scenario {
    public String title;
    public ArrayList<String> actors;
    public String systemActor;
    public SubScenario mySubScenario;
    public myInt startInt;

    public Scenario(String file){
        title = "";
        actors = new ArrayList<>();
        systemActor = "";
        mySubScenario = new SubScenario();
        startInt = new myInt(3);

        ArrayList<String> list;
        list = FileReader.read(file);
        int n = list.size();
        //Scenario s = new Scenario();

        this.title = list.get(0);
        this.actors.add(list.get(1)); //jeden element, pozniej rozszerzyc
        this.systemActor = list.get(2);
        this.mySubScenario.addContent(startInt,list,0);
    }
}
