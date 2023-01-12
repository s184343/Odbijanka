import javax.swing.*;
import java.awt.*;

/**
 * Klasa odpowiadająca za wyświetlenie okna programu.
 * @author Michał Grabski
 */
public class Okno extends JFrame implements Runnable
{
    /**Obiekt reprezentujący panel gry*/
    Panel panel;

    /**
     * Konstruktor klasy okna gry.
     * Określa jego parametry.
     */
    Okno()
    {
        panel = new Panel();
        this.add(panel);
        this.setSize(Wartosci.szerokosc,Wartosci.wysokosc);
        this.setBackground(Color.WHITE);
        this.setVisible(true);
        this.setTitle("Odbijanka");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
    }
    @Override
    public void run()
    {
        while(true)
        {

        }
    }
}
