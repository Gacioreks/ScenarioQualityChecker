package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.base.Scenario;
import pl.put.poznan.transformer.base.Step;

import java.util.ArrayList;

public class CounterVisitor implements Visitor{

    private int quantity = 0;
    private int key_words = 0;
    ArrayList<String> actors;
    ArrayList<Step> steps = new ArrayList<Step>();

    @Override
    public void visit(Scenario sc) {

        quantity += 0;
        actors = sc.actors;
    }

    @Override
    public void visit(Step s) {
        ArrayList<String> words = new ArrayList<String>();
        quantity += 1;


        String [] temp = s.content.split(" ");
        for(String word : temp){
            words.add(word);
        }

        for(String word : temp){
            if(word.equals("IF:") || word.equals("FOR") || word.equals("ELSE:")){
                key_words += 1;
            }
        }

        for(String word : temp){
            if(word.equals("IF:") || word.equals("FOR") || word.equals("ELSE:") || word.equals("EACH:")){
                words.remove(word);
            }
        }

        if(!actors.get(0).equals(words.get(0))){
            steps.add(s);
            System.out.println("Krok nie zaczynajacy sie od aktora: " + s.content);
        }else{
            System.out.println("Krok zaczynajacy sie od aktora: " + s.content);

        }

    }

    public void getStepNumber(){
        System.out.println("Numbers of steps: " + quantity);
    }

    public void getKeyWordsNumber(){
        System.out.println("Number of key words: " + key_words);
    }
}