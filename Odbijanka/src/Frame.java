import javax.swing.*;

public class Frame extends JFrame
{
    static int w = 1280;
    static int h = 720;
    public Frame()
    {
        this.setSize(w,h);
        this.setTitle("Odbijanka");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
