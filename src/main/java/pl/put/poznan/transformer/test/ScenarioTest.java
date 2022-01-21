package pl.put.poznan.transformer.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.base.Scenario;
import pl.put.poznan.transformer.logic.StepCountVisitor;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ScenarioTest {
    private Scenario s;
    @BeforeEach
    void setUP(){
        s=new Scenario("file4.json");
    }
    
    @Test
    void testStepscount(){
        StepCountVisitor v = new StepCountVisitor();
        assertEquals(13, s.Stepscount(v));
    }

    @Test
    void testKeywords(){
        assertEquals(2, s.Keywords());
    }

    @Test
    void testMyIntReset(){
        s.numInt.reset();
        assertEquals(3, s.numInt.getValue());
    }

    @Test
    void testMyIntIncr(){
        s.numInt.reset();
        s.numInt.increment();
        s.numInt.increment();
        assertEquals(5, s.numInt.getValue());
    }

    @Test
    void testSysActor(){
        assertEquals("System", s.SysActor());
        //assertEquals("System1", s.SysActor());
    }

    @Test
    void testTitle(){
        assertEquals("Dodanie książki", s.Title());
        //assertEquals("Dodanie książki123", s.Title());
    }
    @Test
    void testActors(){
        ArrayList<String> actors = new ArrayList<>() ;
        actors.add("Bibliotekarz");
        assertEquals(actors,s.actors);

    }
}