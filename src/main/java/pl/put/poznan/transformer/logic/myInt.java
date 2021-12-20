package pl.put.poznan.transformer.logic;

/**
 * Klasa MyInt wykorzystywana w celu iteracji kroków scenariusza.
 * MyInt wykorzystywana jest w celu przesyłania wartości liczbowej przez referencje.
 */
public class myInt {
    private int value;

    /**
     * Przypisanie wartości początkowej.
     * @param a Przypisywana wartośc.
     */
    public myInt(int a) {
        value = a;
    }

    /**
     * Inkrementacja value o 1.
     */
    public void increment() {
        this.value += 1;
    }

    /**
     * Resetowanie wartości value do 3. Przypisujemy w resecie taką wartość w celu ominięcia tytułu,aktorów oraz systemaktora.
     */
    public void reset() {
        this.value = 3;
    }

    /**
     * Zwracanie wartości value.
     */
    public int getValue() {
        return value;
    }
}
