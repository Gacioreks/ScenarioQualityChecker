package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;

/**
 * Klasa KeyWordsVisitor jest implementacja interfejsu Visitor.
 * Służy do obliczenia ilości słów kluczowych w scenariuszu
 */

public class KeyWordsVisitor implements Visitor{

    private int key_words = 0;

    /**
     * Funkcja visit uruchamiana jest dla kazdego scenariusza, jest pusta poniewaz wymaga tego interfejs ktory implementuje.
     * @param sc Odwiedzany podscenariusz.
     */
    @Override
    public void visit(SubScenario sc) {

    }

    /**
     * Funkcja visit uruchamiana jest dla każdego kroku scenariusza, zwiększa wartość pola prywatnego key_words jeśli w kroku znajduje się słowo kluczowe
     * @param s Odwiedzany krok.
     */
    @Override
    public void visit(Step s) {
        String [] temp = s.value.split(" ");

        for (String word : temp){
            if (word.equals("IF:")||word.equals("ELSE:")||word.equals("FOR")){
                key_words += 1;
            }
        }
    }

    /**
     * Funkcja getKeyWords zwraca ilość słów kluczowych w scenariuszu
     * Powinna być uruchamiana bezpośrednio po wywołaniu funkcji visit na wszystkich krokach scenariusza.
     * @return Ilość słów kluczowych w Scenariuszu.
     */
    public int getKeyWords(){
        System.out.println("Liczba słów kluczowych: " + this.key_words);
        //System.out.println("Zerowanie key_words wizytatora...");
        int out = this.key_words;
        this.key_words = 0;
        return out;
    }
}
