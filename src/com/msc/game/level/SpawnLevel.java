package com.msc.game.level;

import com.msc.game.level.tile.Tile;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class SpawnLevel extends Level {

    public SpawnLevel(String path) {
        super(path);
    }

    protected void loadLevel(String path) {
        try {
            BufferedImage img = ImageIO.read(getClass().getClassLoader().getResource(path));
            int w = width = img.getWidth();
            int h = height = img.getHeight();
            tiles = new int[w * h];
            img.getRGB(0, 0, w, h, tiles, 0, w);
        } catch (IOException ex) {
            Logger.getLogger(SpawnLevel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    //Grass=0xFF00FFOO
    //Flower=0xFFFFFF00
    //Rock=0xFF7F700
    protected void generateLevel() {
        //   System.out.println("Tiles :" + tiles[0]);

    }
}
