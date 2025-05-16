package jui;

import java.io.BufferedInputStream;
import java.io.IOException;

public class Jui
{
    private static BufferedInputStream in =
      new BufferedInputStream(System.in, 1024);
    private static StringBuilder sb = new StringBuilder();

    private final static char ANSI_ESC = '\033';

    // return 0 on err
    public static int GetCh()
    {
        try {
            return in.read();
        } catch (IOException e) {
            System.out.println(e.toString());
            return 0;
        }
    }

    public static void WriteAt(int x, int y, String txt)
    {
        Write(sb.append(ANSI_ESC)
                .append('[')
                .append(y)
                .append(';')
                .append(x)
                .append('H')
                .append(txt)
                .toString());
        sb.setLength(0);
    }

    public static void MoveCursorAt(int x, int y)
    {
        Write(sb.append(ANSI_ESC)
                .append('[')
                .append(y)
                .append(';')
                .append(x)
                .append('H')
                .toString());
        sb.setLength(0);
    }

    /// write ansi foreground 256 color code
    public static void SetFgColor(int n)
    {
        Write(sb.append(ANSI_ESC)
                .append('[')
                .append(38)
                .append(';')
                .append(5)
                .append(';')
                .append(n)
                .append('m')
                .toString());
        sb.setLength(0);
    }

    /// write ansi background 256 color code
    public static void SetBgColor(int n)
    {
        Write(sb.append(ANSI_ESC)
                .append('[')
                .append(48)
                .append(';')
                .append(5)
                .append(';')
                .append(n)
                .append('m')
                .toString());
        sb.setLength(0);
    }

    /// write ansi foreground rgb color code
    public static void SetFgColorRGB(int r, int g, int b)
    {
        Write(sb.append(ANSI_ESC)
                .append('[')
                .append(38)
                .append(';')
                .append(2)
                .append(';')
                .append(r)
                .append(';')
                .append(g)
                .append(';')
                .append(b)
                .append('m')
                .toString());
        sb.setLength(0);
    }

    /// write ansi background rgb color code
    public static void SetBgColorRGB(int r, int g, int b)
    {
        Write(sb.append(ANSI_ESC)
                .append('[')
                .append(48)
                .append(';')
                .append(2)
                .append(';')
                .append(r)
                .append(';')
                .append(g)
                .append(';')
                .append(b)
                .append('m')
                .toString());
        sb.setLength(0);
    }

    /// write ansi reset
    public static void ResetAttr()
    {
        Write(sb.append(ANSI_ESC).append('[').append(0).append('m').toString());
        sb.setLength(0);
    }

    public static void ClearScreen()
    {
        Write(ANSI_ESC + "[2J");
        System.out.flush();
    }

    public static void EnterAlternateMode()
    {
        Write(ANSI_ESC + "[?1049h");
        System.out.flush();
    }

    public static void ExitAlternateMode()
    {
        Write(ANSI_ESC + "[?1049l");
        System.out.flush();
    }

    public static void HideCursor()
    {
        Write(ANSI_ESC + "[?25l");
        System.out.flush();
    }

    public static void ShowCursor()
    {
        Write(ANSI_ESC + "[?25h");
        System.out.flush();
    }

    public static void EnterRawMode()
    {
        try {
            new ProcessBuilder("sh", "-c", "stty raw </dev/tty").start();
        } catch (IOException e) {
            System.out.println(e.toString());
            return;
        }
    }

    public static void DisableEcho()
    {
        try {
            new ProcessBuilder("sh", "-c", "stty -echo </dev/tty").start();
        } catch (IOException e) {
            System.out.println(e.toString());
            return;
        }
    }

    /// exits raw mode, enables echo
    public static void Restore()
    {
        try {
            new ProcessBuilder("sh", "-c", "stty sane </dev/tty").start();
        } catch (IOException e) {
            System.out.println(e.toString());
            return;
        }
    }

    private static void Write(String s)
    {
        try {
            System.out.write(s.getBytes());
        } catch (IOException e) {
        }
    }
    private static void Write(char c) { System.out.write(c); }
    private static void Write(byte c) { System.out.write(c); }
}
