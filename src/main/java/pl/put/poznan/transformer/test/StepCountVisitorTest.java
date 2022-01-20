package pl.put.poznan.transformer.test;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.base.Scenario;
import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;
import pl.put.poznan.transformer.logic.StepCountVisitor;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StepCountVisitorTest {
    @Test
    void brakKrokow() {
        Scenario s = new Scenario("file4.json");
        StepCountVisitor v = new StepCountVisitor();

        assertTrue(s.Stepscount(v) == 0);
    }

    @Test
    void mockTest(){

        Scenario s = new Scenario("fil4.json");
        SubScenario mock = mock(SubScenario.class);
        Object o = new Object();

        ArrayList<Object> objects = new ArrayList<Object>();

        objects.add(o);
        objects.add(o);
        objects.add(o);

        when(mock.addContent(s.startInt,s.list,0)).thenReturn(objects);

        for(int i = 0; i<s.mySubScenario.content.size();i++){
            s.mySubScenario.content.remove(0);
        }

        for (int i = 0; i<objects.size(); i++){
            s.mySubScenario.content.add(objects.get(i));
        }

        assertEquals(3, s.getContentLenght(), 0);
    }
}