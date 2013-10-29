package com.msc.game;

import com.msc.game.Graphics.Screen;
import com.msc.game.input.Keyboard;
import com.msc.game.level.Level;
import com.msc.game.level.RandomLevel;
import com.msc.game.entitiy.mob.Player;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    public static String title = "MAVAJ SUN CO ";
    public static int width = 300;
    public static int height = width / 16 * 9;
    public static int scale = 3;
    private Thread thread;
    private JFrame frame;
    private Keyboard key;
    private Level level;
    private Player player;
    private boolean running = false;

    private Screen screen;

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        screen = new Screen(width, height);
        frame = new JFrame();
        key = new Keyboard();
        level = new RandomLevel(64, 64);
        player = new Player(key);
        addKeyListener(key);
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        long LastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        requestFocus();
        while (running) {
            long now = System.nanoTime();
            delta += (now - LastTime) / ns;
            LastTime = now;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frame.setTitle(title + " | " + updates + " ups , " + frames + " fps");
                frames = 0;
                updates = 0;
            }
        }
        stop();
    }
    int x = 0, y = 0;

    public void update() {
        key.update();
        player.update();
//        if (key.up) {
//            y = y - 5;
//        }
//        if (key.down) {
//            y = y + 5;
//        }
//        if (key.left) {
//            x = x - 5;
//        }
//        if (key.right) {
//            x = x + 5;
//        }

    }

    public void render() {

        BufferStrategy bs = getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        screen.clear();
        //    screen.render(x, y);

        int xScroll = player.x - screen.width / 2;
        int yScroll = player.y - screen.height / 2;

        level.render(xScroll, yScroll, screen);
        player.render(screen);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();
        {
//            g.setColor(new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)));
//            g.fillRect(0, 0, getWidth(), getHeight());
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Verdana", 0, 50));
            g.drawString("X: " + player.x + " ,Y: " + player.y, 400, 400);
        }
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle(game.title);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);
        game.start();

    }
}
