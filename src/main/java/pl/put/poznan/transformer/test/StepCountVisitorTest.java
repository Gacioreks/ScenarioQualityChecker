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
        Scenario s = new Scenario("file3.json");
        StepCountVisitor v = new StepCountVisitor();

        assertTrue(s.Stepscount(v) == 0);
    }


    @Test
    void mockTest() {
        Scenario s = new Scenario("file5.json");
        SubScenario mock = mock(SubScenario.class);
        Object o = new Object();

        s.Scenarioshow();
        int test = 0;
        int size = s.mySubScenario.content.size();

        ArrayList<Object> objects = new ArrayList<Object>();

        objects.add(o);
        objects.add(o);
        objects.add(o);

        when(mock.getContent()).thenReturn(objects.size());

        for (int i = 0; i < size; i++) {
            s.mySubScenario.content.remove(0);
        }
        test += s.Count(mock);

        assertEquals(3, test, 0);
    }
}