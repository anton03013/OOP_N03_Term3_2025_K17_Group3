package Classes;

import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;


public class PlatformManager {
    private List<Platforms> platforms;

    public PlatformManager() {
        platforms = new ArrayList<>();
    }

    public void addPlatform(int x, int y, int width, int height) {
        platforms.add(new Platforms(x, y, width, height));
    }

    public Platforms getPlatform(int index) {
        if (index >= 0 && index < platforms.size()) {
            return platforms.get(index);
        }
        return null;
    }

    public List<Platforms> getAllPlatforms() {
        return platforms;
    }

    public boolean updatePlatform(int index, int newX, int newY, int newWidth, int newHeight) {
        Platforms p = getPlatform(index);
        if (p != null) {
            platforms.set(index, new Platforms(newX, newY, newWidth, newHeight));
            return true;
        }
        return false;
    }

    public boolean deletePlatform(int index) {
        if (index >= 0 && index < platforms.size()) {
            platforms.remove(index);
            return true;
        }
        return false;
    }

    public void drawAllPlatforms(Graphics g) {
        for (Platforms platform : platforms) {
            platform.draw(g);
        }
    }
}
