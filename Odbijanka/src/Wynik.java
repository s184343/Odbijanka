import java.awt.*;

/**
 * Klasa wyświetlająca na ekranie wynik oraz statyczne elementy planszy
 * @author Michał Grabski
 */
public class Wynik extends Rectangle
{
    /**stała określająca szerokość gry*/
    static int szerokosc;
    /**stała określająca wysokość gry*/
    static int wysokosc;
    /**zmienna przypisana do gracza nr 1*/
    public int gracz1;
    /**zmienna przypisana do gracza nr 2*/
    public int gracz2;

    /**
     * Konstruktor tworzący pole na określonych wymiarach
     * @param szerokosc szerokość pola gry
     * @param wysokosc wysokosc pola gry
     */
    Wynik(int szerokosc, int wysokosc)
    {
        Wynik.szerokosc = szerokosc;
        Wynik.wysokosc = wysokosc;
    }

    /**
     * Rysowanie wyniku oraz statycznych elementów na planszy
     * @param g obiekt klasy Graphics reprezentujący ścieżkę do pliku png
     */
    public void rysuj(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Ink Free", Font.BOLD, 50));
        g.drawLine(szerokosc/2, Wartosci.sciana_gorna, szerokosc/2, wysokosc);
        g.drawLine(0,Wartosci.sciana_gorna,szerokosc,Wartosci.sciana_gorna);
        g.drawLine(0,wysokosc-720,szerokosc,wysokosc-720);
        g.drawString(String.valueOf(gracz1),(szerokosc/2)-65,40);
        g.drawString(String.valueOf(gracz2),(szerokosc/2)+40,40);
        g.drawString(":",(szerokosc/2)-5,40);
        g.drawString("Gracz 1",10,40);
        g.drawString("Gracz 2",(szerokosc)-200,40);
    }
}