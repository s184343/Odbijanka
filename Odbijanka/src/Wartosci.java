import java.awt.*;
/**Klasa zawierająca użyteczne stałe wartości.
 * Ułatwia szybką edycję parametrów w jednym miejscu.
 * @author Michał Grabski
 * */
public class Wartosci
{
    /**Szerokość okna gry*/
    static int szerokosc = 1280;
    /**Wysokość okna gry*/
    static int wysokosc = 720;
    /**Wymiary okna gry*/
    static Dimension wymiary = new Dimension(szerokosc,wysokosc);
    /**Pozycja górnej ściany planszy na osi rzędnych*/
    static int sciana_gorna = 50;
    /**Średnica grafiki piłki*/
    static int srednica_pilki = 20;
    /**Szerokość grafiki gracza*/
    static int szerokosc_gracza = 20;
    /**Wysokość grafiki gracza*/
    static int wysokosc_gracza =100;
    /**Prędkość początkowa poruszania się piłki po planszy*/
    static int predkosc_pilki = 10;
    /**Prędkość poruszania się gracza po planszy*/
    static int predkosc_gracza = 10;
    /**Liczba punktów kończąca rozgrywkę*/
    static int do_ilu = 5;
}
