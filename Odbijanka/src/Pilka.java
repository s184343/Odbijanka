import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * Klasa opisująca Piłkę
 * @author Michał Grabski
 */
public class Pilka extends Rectangle
{
    /**Zmienna nadająca losowy kierunek startu piłki*/
    Random random;
    /**Zmienna odpowiadająca za ruch piłki po osi odciętych*/
    int kierunek_x;
    /**Zmienna odpowiadająca za ruch piłki po osi rzędnych*/
    int kierunek_y;
    /**Grafika przedstawiająca piłkę*/
    Image pilka;

    /**
     * Konstruktor tworzący poruszającą się po ekranie piłkę
     * @param x Położenie piłki na osi x
     * @param y Położenie piłki na osi y
     * @param szerokosc Szerokość piłki
     * @param wysokosc Wysokość piłki
     */
    Pilka(int x, int y, int szerokosc, int wysokosc)
    {
        super(x,y,szerokosc,wysokosc);
        random = new Random();
        int rusz_x = random.nextInt(2);
        if (rusz_x == 0)
            rusz_x--;
        zwrot_x(rusz_x*Wartosci.predkosc_pilki);

        int rusz_y = random.nextInt(2);
        if (rusz_y == 0)
            rusz_y--;
        zwrot_y(rusz_y*Wartosci.predkosc_pilki);
    }

    /**
     * Ustalenie kierunku poruszania się po osi x
     * @param rusz_x
     */
    public void zwrot_x(int rusz_x)
    {
        kierunek_x = rusz_x;
    }
    /**
     * Ustalenie kierunku poruszania się po osi y
     * @param rusz_y
     */
    public void zwrot_y(int rusz_y)
    {
        kierunek_y = rusz_y;
    }

    /**
     * Poruszanie się piłki
     */
    public void ruszaj()
    {
        x += kierunek_x;
        y += kierunek_y;
    }

    /**
     * Umieszczenie grafiki piłki na ekranie
     * @param g obiekt klasy Graphics reprezentujący ścieżkę do pliku png
     */
    public void rysuj(Graphics g)
    {
        pilka = new ImageIcon("zasoby/pilka.png").getImage();
        g.drawImage(pilka, x,y,null);
    }
}