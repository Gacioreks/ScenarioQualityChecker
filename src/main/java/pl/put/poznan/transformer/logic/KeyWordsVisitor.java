package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;

public class KeyWordsVisitor implements Visitor{

    private int key_words = 0;

    @Override
    public void visit(SubScenario sc) {

    }

    @Override
    public void visit(Step s) {
        String [] temp = s.value.split(" ");

        for (String word : temp){
            if (word.equals("IF:")||word.equals("ELSE:")||word.equals("FOR")){
                key_words += 1;
            }
        }
    }

    public int getKeyWords(){
        System.out.println("Liczba słów kluczowych: " + this.key_words);
        //System.out.println("Zerowanie key_words wizytatora...");
        int out = this.key_words;
        this.key_words = 0;
        return out;
    }
}
