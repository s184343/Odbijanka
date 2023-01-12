import javax.swing.*;
import java.awt.*;

/**
 * Klasa tworząca napisy na starcie
 * @author Michał Grabski
 */
public class Start extends Rectangle
{
    Image i;

    /**
     * Konstruktor określający położenie i wymiary obrazu z napisami
     * @param x Położenie na osi x
     * @param y Położenie na osi y
     * @param width Szerokość obrazu
     * @param height Wysokość obrazu
     */
    Start(int x, int y, int width, int height)
    {
        super(x,y,width,height);
    }

    /**
     * Usunięcie napisu z widoczności ekranu
     */
    public void won_z_ekranu()
    {
        x = 99999;
        y = 0;
    }

    /**
     * Rysowanie grafiki w programie
     * @param g obiekt klasy Graphics reprezentujący ścieżkę do pliku png
     */
    public void rysuj(Graphics g)
    {
        i = new ImageIcon("zasoby/start.png").getImage();

        g.drawImage(i, x,y,null);
    }
}