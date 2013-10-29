package com.msc.game.entitiy;

import com.msc.game.Graphics.Screen;
import com.msc.game.level.Level;
import java.util.Random;

public class Entity {

    public int x, y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();

    public void update() {
    }

    public void render(Screen screen) {
    }

    public void removed() {
        //Remove from level
        this.removed = true;
    }

    public boolean isRemoved() {
        return this.removed;
    }
}
