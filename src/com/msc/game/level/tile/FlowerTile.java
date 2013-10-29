package com.msc.game.level.tile;

import com.msc.game.Graphics.Screen;
import com.msc.game.Graphics.Sprite;

class FlowerTile extends Tile {

    public FlowerTile(Sprite flower) {
        super(flower);
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }
}
