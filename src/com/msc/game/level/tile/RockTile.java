/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msc.game.level.tile;

import com.msc.game.Graphics.Screen;
import com.msc.game.Graphics.Sprite;

/**
 *
 * @author M.S.C
 */
class RockTile extends Tile {

    public RockTile(Sprite rock) {
        super(rock);
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }

    public boolean solid() {
        return true;
    }
}
