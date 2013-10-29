package com.msc.game.level;

import com.msc.game.level.tile.Tile;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class SpawnLevel extends Level {

    private Tile[] tiles;
    private int[] levelPixels;

    public SpawnLevel(String path) {
        super(path);
    }

    protected void loadLevel(String path) {
        try {
            BufferedImage img = ImageIO.read(getClass().getClassLoader().getResource(path));
            int w = img.getWidth();
            int h = img.getHeight();
            img.getRGB(0, 0, w, h, levelPixels, 0, w);
        } catch (IOException ex) {
            Logger.getLogger(SpawnLevel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    protected void generateLevel() {
        
    }
}
