import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * KeyListener.
 * Klasa umożliwiająca interakcję gracza z grą poprzez klawiaturę.
 * @author Michał Grabski
 * */
public class KL extends KeyAdapter
{
    /**Obiekt reprezentujący klasę Panel*/
    Panel panel;

    /**
     * Konstruktor KL
     * Przypisywanie do panelu
     * @param panel
     */
    KL(Panel panel)
    {
        this.panel = panel;
    }
    /**
     * Interakcja gracza z programem - wciśnięcie klawisza
     * @param e obiekt klasy KeyEvent reprezentujący interakcję programu z klawiaturą
     */
    public void keyPressed(KeyEvent e)
    {
        panel.gracz1.keyPressed(e);
        panel.gracz2.keyPressed(e);
        if(e.getKeyCode() ==  KeyEvent.VK_SPACE)
        {
            this.panel.graj = true;
            if((panel.wynik.gracz1 == Wartosci.do_ilu || panel.wynik.gracz2 == Wartosci.do_ilu)&&e.getKeyCode() ==  KeyEvent.VK_SPACE)
            {
                panel.wynik.gracz1 = 0;
                panel.wynik.gracz2 = 0;
            }
        }
    }
    /**
     * Interakcja gracza z programem - puszczenie klawisza
     * @param e obiekt klasy KeyEvent reprezentujący interakcję programu z klawiaturą
     */
    public void keyReleased(KeyEvent e)
    {
        panel.gracz1.keyReleased(e);
        panel.gracz2.keyReleased(e);
    }
}