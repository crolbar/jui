package main;

import jui.Jui;

public class App
{
    boolean shouldClose = false;
    int ch;
    Jui t;

    public App() { this.t = new Jui("hi"); }

    public void exec()
    {
        t.EnterAlternateMode();
        t.EnterRawMode();
        t.DisableEcho();

        t.HideCursor();
        t.ClearScreen();

        t.WriteAt(5, 10, "Hello, World!");

        t.WriteAt(20, 30, "Hello, World!");
        rendCirc();
        while (!shouldClose) {
            switch (ch = t.GetCh()) {
                case 'q', 3:
                    shouldClose = true;
                    break;
            }

            t.WriteAt(0, 0, "           ");

            t.WriteAt(0, 0, String.format("Key: %d", ch));
        }

        t.ExitAlternateMode();
        t.Restore();
        t.ShowCursor();
    }

    private void rendCirc()
    {
        int r = 15;
        int cy = 30;
        int cx = 70;

        for (double ang = 0.0; ang < 2 * Math.PI; ang += 0.001) {
            int x = (int)(cx + (r * Math.cos(ang) * 2));
            int y = (int)(cy + (r * Math.sin(ang)));
            t.WriteAt(x, y, "#");
        }
    }
}
