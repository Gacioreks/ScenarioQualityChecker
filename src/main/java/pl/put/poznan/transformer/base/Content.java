package pl.put.poznan.transformer.base;

public class Content {
    public String value;

    public int steps(Scenario s){
        int result = s.mySubScenario.content.size();


        return result;
    }
}
