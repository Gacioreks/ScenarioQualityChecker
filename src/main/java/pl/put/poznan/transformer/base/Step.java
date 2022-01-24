package pl.put.poznan.transformer.base;

import pl.put.poznan.transformer.logic.Visitor;

/**
 * Klasa Step to podstawowa klasa wykorzystywana w projekcie do pracy nad scenariuszem.
 * Wykorzystywana przez wizytatora. Obiekt klasy Step jest odzwierciedleniem kroku scenariusza 
 * zawierającym jego treść oraz poziom zagłębienia. 
 */
public class Step {
    /**
     * Treść kroku.
     */
    public String value;
    /**
     * Poziom zagłębienia.
     */
    public int level;

    /**
     * Klasyczyny konstrukotr przypisujący wartości.
     * @param a poziom zaglebienia
     * @param tmp tresc kroku
     */
    public Step(int a, String tmp) {
        value = tmp;
        level = a;
    }

    /**
     * Funkcja accept służy do "wpuszczenia" do obiektu instancji konkretnego wizytatora,
     * pozwalając mu na wykonanie swojej implementacji funkcji visit.
     * @param v konkretny obiekt wizytatora.
     */
    public void accept(Visitor v){

        v.visit(this);

    }
}
