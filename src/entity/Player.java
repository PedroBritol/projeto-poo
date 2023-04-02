package entity;

import main.GamePanel;
import main.KeyHandler;
import utils.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity
{
    GamePanel gamePanel;
    KeyHandler keyHandler;

    final float gravity = 0.1f; // 9.8 m/s²
    final float maxSpeed = 100f; // 100 m/s

    public Player (GamePanel gamePanel, KeyHandler keyHandler)
    {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValues();
    }

    public void setDefaultValues()
    {
        x = 10;
        // y = gamePanel.screenHeight - gamePanel.tileSize;
        y = 0;
        speedY = 2;
        speedX = 4;
        jumpSpeed = 4;
        direction = Direction.DOWN;
    }

    public void update()
    {
        int nextPosition;
        int screenLimitHeight = gamePanel.screenHeight - gamePanel.tileSize;
        int screenLimitWidth = gamePanel.screenWidth - gamePanel.tileSize;

        if (keyHandler.upPressed) {
            nextPosition = y - jumpSpeed;
            if (nextPosition >= 0) {
                y = nextPosition;
            }
            direction = Direction.UP;
        }
        else if (keyHandler.downPressed) {
            nextPosition = y + speedY;
            if (nextPosition < screenLimitHeight) {
                y = nextPosition;
            }
            direction = Direction.DOWN;
        }
        else if (keyHandler.leftPressed) {
            nextPosition = x - speedX;
            if (nextPosition >= 0) {
                x = nextPosition;
            }
            direction = Direction.LEFT;
        }
        else if (keyHandler.rightPressed) {
            nextPosition = x + speedX;
            if (nextPosition <= screenLimitWidth) {
                x = nextPosition;
            }
            direction = Direction.RIGHT;
        }
    }

    public void draw(Graphics2D graphic2D)
    {
        graphic2D.setColor(Color.white);
        graphic2D.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
    }
}