package app;

import static jui.Jui.*;

public class App
{
    boolean shouldClose = false;
    int ch;
    int t = 0;

    public App() {}

    public void exec()
    {
        EnterAlternateMode();
        EnterRawMode();
        DisableEcho();

        HideCursor();

        while (!shouldClose) {
            switch (ch = GetCh()) {
                case 'q', 3:
                    shouldClose = true;
                    break;
            }
            ClearScreen();
            t++;

            SetFgColor(t % 240);

            WriteAt(5, 10, "Hello, World!");

            ResetAttr();

            WriteAt(20, 30, "Hello, World!");
            rendCirc();

            WriteAt(0, 0, "           ");

            WriteAt(0, 0, String.format("Key: %d", ch));
        }

        ExitAlternateMode();
        Restore();
        ShowCursor();
    }

    private void rendCirc()
    {
        int r = 15;
        int cy = 30;
        int cx = 70;

        for (double ang = 0.0; ang < 2 * Math.PI; ang += 0.001) {
            int x = (int)(cx + (r * Math.cos(ang) * 2));
            int y = (int)(cy + (r * Math.sin(ang)));
            SetFgColor((t + x) % 240);
            WriteAt(x, y, "#");
            ResetAttr();
        }
    }

    public static void main(String[] args)
    {
        App a = new App();
        a.exec();
    }
}
