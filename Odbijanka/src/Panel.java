import java.io.File;
import java.lang.*;
import java.awt.*;
import javax.swing.*;
import javax.sound.sampled.*;

/**
 * Klasa odpowiadająca za całą rozgrywkę i jej działanie
 * @author Michał Grabski
 */
public class Panel extends JPanel implements Runnable
{
    /**Wątek wykonujący program*/
    Thread thread;

    /**Zmienna potrzebna do ustalenia czy gra jest rozpoczęta */
    boolean graj;
    Image image;
    Graphics graphics;
    /**Obiekt reprezentujący pierwszego gracza*/
    Gracze gracz1;
    /**Obiekt reprezentujący drugiego gracza*/
    Gracze gracz2;
    /**Obiekt reprezentujący piłkę*/
    Pilka pilka;
    /**Obiekt reprezentujący wynik*/
    Wynik wynik;
    /**Obiekt reprezentujący wyświetlanie informacji na starcie*/
    Start start;
    /**Obiekt reprezentujący wyświetlanie informacji na końcu w przypadku zwycięstwa gracza nr 1*/
    Koniec1 koniec1;
    /**Obiekt reprezentujący wyświetlanie informacji na końcu w przypadku zwycięstwa gracza nr 2*/
    Koniec2 koniec2;


    /**
     * Konstruktor panelu.
     * Wyświetla obiekty w oknie gry.
     * Uruchamia wątek gry.
     * Dodaje obsługę zdarzeń.
     */
    Panel()
    {
        gracze();
        pilka();
        start();
        koniec1();
        koniec2();
        this.thread = new Thread(this);
        thread.start();
        wynik = new Wynik(Wartosci.szerokosc,Wartosci.wysokosc);
        this.setFocusable(true);
        this.addKeyListener(new KL(this));
        this.setPreferredSize(Wartosci.wymiary);
    }
    /**Pętla gry.
     * Działanie rysowania i poruszania się obiektów w czasie rzeczywistym.
     * Warunki zakończenia rozgrywki.
     * */
    @Override
    public void run()
    {
        long t = System.nanoTime();
        double ns = 1E9/60;
        double roznica = 0;
        while(true)
        {
            long now = System.nanoTime();
            roznica += (now - t)/ns;
            t = now;
            if (roznica >= 1)
            {
                ruszaj();
                odbicia();
                repaint();
                roznica--;
                if(wynik.gracz1 == Wartosci.do_ilu)
                {
                    this.graj = false;
                    koniec1.pokaz();
                }
                else if(wynik.gracz2 == Wartosci.do_ilu)
                {
                    this.graj = false;
                    koniec2.pokaz();
                }
            }
        }
    }
    /**Utworzenie grafiki z tytułem pojawiającej się po uruchomieniu gry, przed rozpoczęciem rozgrywki*/
    public void start()
    {
        start = new Start(0,0,Wartosci.szerokosc,Wartosci.wysokosc);
    }
    /**Funkcja tworząca grafikę wyświetlaną po zakończeniu rozgrywki ze wskazaniem na zwycięstwo gracza nr 1*/
    public void koniec1()
    {
        koniec1 = new Koniec1(9999,0,Wartosci.szerokosc,Wartosci.wysokosc);
    }
    /**Funkcja tworząca grafikę wyświetlaną po zakończeniu rozgrywki ze wskazaniem na zwycięstwo gracza nr 1*/
    public void koniec2()
    {
        koniec2 = new Koniec2(-9999,0,Wartosci.szerokosc,Wartosci.wysokosc);
    }
    /**Funkcja tworząca każdego z graczy*/
    public void gracze()
    {
        gracz1 = new Gracze(0,(Wartosci.wysokosc/2+Wartosci.sciana_gorna/2)-(Wartosci.wysokosc_gracza/2),Wartosci.szerokosc_gracza,Wartosci.wysokosc_gracza,1);
        gracz2 = new Gracze(Wartosci.szerokosc-Wartosci.szerokosc_gracza,(Wartosci.wysokosc/2+Wartosci.sciana_gorna/2)-(Wartosci.wysokosc_gracza/2),Wartosci.szerokosc_gracza,Wartosci.wysokosc_gracza,2);
    }
    /**Funkcja tworząca piłkę*/
    public void pilka()
    {
        pilka = new Pilka ((Wartosci.szerokosc/2)-(Wartosci.srednica_pilki/2), (Wartosci.wysokosc/2)-(Wartosci.srednica_pilki/2)+25, Wartosci.srednica_pilki, Wartosci.srednica_pilki);
    }
    /**Funkcja umożliwiająca rysowanie przez program obiektów na ekranie*/
    public void paint(Graphics g)
    {
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        rysuj(graphics);
        g.drawImage(image,0,0,this);
    }
    /**Funkcja, która wyświetla obiekty na planszy
     * @param g obiekt klasy Graphics reprezentujący ścieżkę do pliku png
     * */
    public void rysuj(Graphics g)
    {
        gracz1.rysuj(g);
        gracz2.rysuj(g);
        pilka.rysuj(g);
        wynik.rysuj(g);
        start.rysuj(g);
        koniec1.rysuj(g);
        koniec2.rysuj(g);
    }
    /**Funkcja, która umożliwia ruch po planszy narysowanymi obiektami*/
    public void ruszaj()
    {
        if(this.graj)
        {
            gracz1.ruszaj();
            gracz2.ruszaj();
            pilka.ruszaj();
            start.won_z_ekranu();
            koniec1.won_z_ekranu();
            koniec2.won_z_ekranu();
        }
    }
    /**
     * Funkcja odtwarzania dźwięku z pliku
     * @param f - obiekt klasy File reprezentujący ścieżkę do pliku dźwiękowego
     */
    public static synchronized void dzwiek(final File f) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(f);
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }
    /**
     * Ruch piłki po ekranie.
     * Matematyka zastosowana do odbijania się piłki od ścian i graczy.
     * Dodawanie punktów w przypadku ominięcia gracza przez piłkę.
     * Tworzenie nowych obiektów po zdobyciu punktu przez gracza.
     */
    public void odbicia()
    {
        if(gracz1.y<=50)
            gracz1.y=50;
        if(gracz1.y>=(Wartosci.wysokosc-Wartosci.wysokosc_gracza))
            gracz1.y = Wartosci.wysokosc-Wartosci.wysokosc_gracza;

        if(gracz2.y<=50)
            gracz2.y=50;
        if(gracz2.y>=(Wartosci.wysokosc-Wartosci.wysokosc_gracza))
            gracz2.y = Wartosci.wysokosc-Wartosci.wysokosc_gracza;

        if(pilka.y <= 50)
        {
            pilka.zwrot_y(-pilka.kierunek_y);
            dzwiek(new File("zasoby/pilka_sciana.wav"));
        }
        if(pilka.y >= Wartosci.wysokosc-Wartosci.srednica_pilki)
        {
            pilka.zwrot_y(-pilka.kierunek_y);
            dzwiek(new File("zasoby/pilka_sciana.wav"));
        }
        if(pilka.intersects(gracz1))
        {
            pilka.kierunek_x = Math.abs(pilka.kierunek_x);
            pilka.kierunek_x++;
            dzwiek(new File("zasoby/pilka_gracz.wav"));


            if(pilka.kierunek_y > 0)
                pilka.kierunek_y++;
            else
                pilka.kierunek_y--;

            pilka.zwrot_x(pilka.kierunek_x);
            pilka.zwrot_y(pilka.kierunek_y);
        }
        if(pilka.intersects(gracz2))
        {
            pilka.kierunek_x = Math.abs(pilka.kierunek_x);
            pilka.kierunek_x++;
            dzwiek(new File("zasoby/pilka_gracz.wav"));
            if(pilka.kierunek_y > 0)
                pilka.kierunek_y++;
            else
                pilka.kierunek_y--;

            pilka.zwrot_x(-pilka.kierunek_x);
            pilka.zwrot_y(pilka.kierunek_y);
        }
        if(pilka.x < -50)
        {
            wynik.gracz2++;
            dzwiek(new File("zasoby/gwizdek.wav"));
            gracze();
            pilka();
        }
        if(pilka.x > Wartosci.szerokosc)
        {
            wynik.gracz1++;
            dzwiek(new File("zasoby/gwizdek.wav"));
            gracze();
            pilka();
        }
    }

}