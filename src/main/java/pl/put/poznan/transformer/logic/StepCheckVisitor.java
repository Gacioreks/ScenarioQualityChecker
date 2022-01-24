package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Klasa StepCheckVisitor jest implementacja interfejsu Visitor
 * Służy do sprawdzenia czy korki zaczynają się od aktorów
 */

public class StepCheckVisitor implements Visitor{

    private ArrayList<Step> invalid_steps = new ArrayList<Step>();
    private ArrayList<String> actors = new ArrayList<String>();
    private String sys_actor;

    /**
     * Klasyczny konstruktor
     * @param actrs aktorzy scenariusza.
     * @param sys_act aktor systemowy scenariusza.
     */

    public StepCheckVisitor(ArrayList<String> actrs, String sys_act){
        this.actors = actrs;
        this.sys_actor = sys_act;
    }

    /**
     * Funkcja visit urachamiana jest dla kazdego scenariusza, jest pusta poniewaz wymaga tego intefejs ktrory implementuje
     * @param sc Odwiedzany podscenariusz
     */
    @Override
    public void visit(SubScenario sc) {

    }

    /**
     * Funkcja visit uruchamian jest dla kazdego kroku scenariusza,
     * sprawdza czy dany krok rozpoczyna się od aktora danego scenariusza.
     * Jeśli krok nie zaczyna się od aktora jest on dodawany do listy niepoprawnych kroków
     * @param s Odwiedzany krok
     */
    @Override
    public void visit(Step s) {
        boolean starting_with_actor= false;

        String [] temp = s.value.split(" ");
        ArrayList<String> words = new ArrayList<String>();

        Collections.addAll(words, temp);

        if(words.get(0).equals("IF:") || words.get(0).equals("ELSE:")){
            words.remove(0);
        }

        for (String actor : actors){
            if(words.get(0).equals(actor)) starting_with_actor = true;
        }

        if(words.get(0).equals(sys_actor)) starting_with_actor = true;
        if(words.get(0).equals("FOR") || words.get(0).equals("")) starting_with_actor = true;

        if (!starting_with_actor) this.invalid_steps.add(s);
    }


    /**
     * Funkcja getInvalidSteps zwraca niepoprawne kroki scenariusza oraz je wyświetla
     * @return Lista niepoprawnych kroków.
     */
    public ArrayList<Step> getInvalidSteps(){
        System.out.println("Kroki nie zaczynające sie od aktora: ");
        ArrayList<Step> out = new ArrayList<Step>();
        for(Step s : invalid_steps){
            System.out.println(s.value);
            out.add(s);
        }
        //System.out.println("\nZerowanie invalid_steps...");

        this.invalid_steps.clear();
        return out;
    }
}
