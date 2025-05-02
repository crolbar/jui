package jui;

import java.io.BufferedInputStream;
import java.io.IOException;

public class Jui
{
    public String n;
    BufferedInputStream in;
    private final static char ANSI_ESC = '\033';

    public Jui(String n)
    {
        this.in = new BufferedInputStream(System.in, 1024);
        this.n = n;
    }

    // return 0 on err
    public int GetCh()
    {
        try {
            return in.read();
        } catch (IOException e) {
            System.out.println(e.toString());
            return 0;
        }
    }

    public void WriteAt(int x, int y, String txt)
    {
        Write(String.format("%s[%d;%dH%s", ANSI_ESC, y, x, txt));
    }

    public void ClearScreen()
    {
        Write(ANSI_ESC + "[2J");
        System.out.flush();
    }

    public void EnterAlternateMode()
    {
        Write(ANSI_ESC + "[?1049h");
        System.out.flush();
    }

    public void ExitAlternateMode()
    {
        Write(ANSI_ESC + "[?1049l");
        System.out.flush();
    }

    public void HideCursor()
    {
        Write(ANSI_ESC + "[?25l");
        System.out.flush();
    }

    public void ShowCursor()
    {
        Write(ANSI_ESC + "[?25h");
        System.out.flush();
    }

    public void EnterRawMode()
    {
        try {
            new ProcessBuilder("sh", "-c", "stty raw </dev/tty").start();
        } catch (IOException e) {
            System.out.println(e.toString());
            return;
        }
    }

    public void DisableEcho()
    {
        try {
            new ProcessBuilder("sh", "-c", "stty -echo </dev/tty").start();
        } catch (IOException e) {
            System.out.println(e.toString());
            return;
        }
    }

    // exits raw mode, enables echo
    public void Restore()
    {
        try {
            new ProcessBuilder("sh", "-c", "stty sane </dev/tty").start();
        } catch (IOException e) {
            System.out.println(e.toString());
            return;
        }
    }

    private void Write(String s)
    {
        try {
            System.out.write(s.getBytes());
        } catch (IOException e) {
            System.out.println(e.toString());
            return;
        }
    }
    private void Write(char c) { System.out.write(c); }
    private void Write(byte c) { System.out.write(c); }
}
