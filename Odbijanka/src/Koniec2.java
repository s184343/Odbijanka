import javax.swing.*;
import java.awt.*;

/**
 * Klasa tworząca napisy po zwycięstwie gracza nr 2
 * @author Michał Grabski
 */
public class Koniec2 extends Rectangle {
    Image i;
    /**
     * Konstruktor określający położenie i wymiary obrazu z napisami
     * @param x Położenie na osi x
     * @param y Położenie na osi y
     * @param width Szerokość obrazu
     * @param height Wysokość obrazu
     */
    Koniec2(int x, int y, int width, int height)
    {
        super(x, y, width, height);
    }
    /**
     * Rysowanie grafiki w programie
     * @param g obiekt klasy Graphics reprezentujący ścieżkę do pliku png
     */
    public void rysuj(Graphics g)
    {
        i = new ImageIcon("zasoby/koniec2.png").getImage();
        g.drawImage(i, x, y, null);
    }
    /**
     * Pokazanie grafiki na ekranie
     */
    public void pokaz()
    {
        x = 0;
        y = 0;
    }
    /**
     * Usunięcie grafiki z widoczności ekranu
     */
    public void won_z_ekranu()
    {
        x = 0;
        y = -99999;
    }

}