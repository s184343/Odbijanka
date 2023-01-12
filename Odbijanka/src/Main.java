import java.lang.*;

/**
 * Klasa główna programu
 * @author Michał Grabski
 */
public class Main
{
    /**
     * Metoda uruchamiająca grę, tworząc nowe okno.
     * @param args
     */
    public static void main(String[] args)
    {
        Okno o = new Okno();
        o.run();
    }
}