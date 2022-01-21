package pl.put.poznan.transformer.test;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.base.Scenario;
import pl.put.poznan.transformer.logic.StepCountVisitor;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.base.Scenario;
import pl.put.poznan.transformer.logic.StepCountVisitor;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class SimplyScenarioTest {
    private Scenario s;
    @BeforeEach
    void setUP(){
        s=new Scenario("file6.json");
    }


    @Test
    void brakKeyWords() {
        StepCountVisitor v = new StepCountVisitor();
        assertEquals(0, s.Keywords());

    }
    @Test
    void testMoreThenOneActor(){
        ArrayList<String> actors = new ArrayList<>() ;
        actors.add("User");
        actors.add("notUser");
        assertEquals(actors,s.actors);

    }
}
