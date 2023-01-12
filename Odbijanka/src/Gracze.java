import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Klasa opisująca działanie postaci graczy
 * @author Michał Grabski
 */
public class Gracze extends Rectangle
{
    /**zmienna potrzebna do przypisania nr gracza 1 lub 2*/
    int id;
    /**zmienna potrzebna do ruchu gracza po osi rzędnych*/
    int kierunek_y;
    /**Grafika z postacią gracza nr 1*/
    Image g1;
    /**Grafika z postacią gracza nr 2*/
    Image g2;

    /**
     * Konstruktor postaci gracza
     * @param x umieszczenie danej postaci na osi odciętych
     * @param y umieszczenie danej postaci na osi rzędnych
     * @param szerokosc_gracza określenie szerokości postaci gracza
     * @param wysokosc_gracza określenie wysokości postaci gracza
     * @param id określenie nr gracza (1 lub 2)
     */
    Gracze(int x, int y, int szerokosc_gracza, int wysokosc_gracza, int id)
    {
        super(x,y,szerokosc_gracza,wysokosc_gracza);
        this.id=id;
    }

    /**
     * Interakcja gracza z programem - wciśnięcie klawisza
     * @param e obiekt klasy KeyEvent reprezentujący interakcję programu z klawiaturą
     */
    public void keyPressed(KeyEvent e)
    {
        switch(id)
        {
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W)
                {
                    zwrot_y(-Wartosci.predkosc_gracza);
                    ruszaj();
                }
                if(e.getKeyCode()==KeyEvent.VK_S)
                {
                    zwrot_y(Wartosci.predkosc_gracza);
                    ruszaj();
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP)
                {
                    zwrot_y(-Wartosci.predkosc_gracza);
                    ruszaj();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN)
                {
                    zwrot_y(Wartosci.predkosc_gracza);
                    ruszaj();
                }
                break;
        }
    }

    /**
     * Interakcja gracza z programem - puszczenie klawisza
     * @param e obiekt klasy KeyEvent reprezentujący interakcję programu z klawiaturą
     */
    public void keyReleased(KeyEvent e)
    {
        switch(id)
        {
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W)
                {
                    zwrot_y(0);
                    ruszaj();
                }
                if(e.getKeyCode()==KeyEvent.VK_S)
                {
                    zwrot_y(0);
                    ruszaj();
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP)
                {
                    zwrot_y(0);
                    ruszaj();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN)
                {
                    zwrot_y(0);
                    ruszaj();
                }
                break;
        }
    }

    /**
     * Ustalenie położenia na osi y
     * @param ruch_po_y
     */
    public void zwrot_y(int ruch_po_y)
    {
        kierunek_y = ruch_po_y;
    }

    /**
     *Nadpisywanie obecnego położenia na osi y
     */
    public void ruszaj()
    {
        y = y + kierunek_y;
    }

    /**
     * Umieszczenie obrazów postaci graczy na planszy
     * @param g obiekt klasy Graphics reprezentujący ścieżkę do pliku png
     */
    public void rysuj(Graphics g)
    {
        g1 = new ImageIcon("zasoby/gracz1.png").getImage();
        g2 = new ImageIcon("zasoby/gracz2.png").getImage();
        if(id==1)
            g.drawImage(g1, x,y,null);
        if(id==2)
            g.drawImage(g2,x,y,null);
    }
}