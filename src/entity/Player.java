package entity;

import main.GamePanel;
import main.KeyHandler;
import utils.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
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
        getPlayerImage();
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
            isWalking = true;
            direction = Direction.UP;
        }
        else if (keyHandler.downPressed) {
            nextPosition = y + speedY;
            if (nextPosition < screenLimitHeight) {
                y = nextPosition;
            }
            isWalking = true;
            direction = Direction.DOWN;
        }
        else if (keyHandler.leftPressed) {
            nextPosition = x - speedX;
            if (nextPosition >= 0) {
                x = nextPosition;
            }
            isWalking = true;
            direction = Direction.LEFT;
        }
        else if (keyHandler.rightPressed) {
            nextPosition = x + speedX;
            if (nextPosition <= screenLimitWidth) {
                x = nextPosition;
            }
            isWalking = true;
            direction = Direction.RIGHT;
        } else {
            isWalking = false;
        }

        sprintCounter++;

        if (sprintCounter > 8 && isWalking) {
            changeSprite = !changeSprite;
        }
    }

    public void draw(Graphics2D graphic2D)
    {
        int spriteNumber = changeSprite ? 0 : 1;

        BufferedImage image = switch (direction) {
            case Direction.UP -> up[spriteNumber];
            case Direction.DOWN -> down[spriteNumber];
            case Direction.LEFT -> left[spriteNumber];
            case Direction.RIGHT -> right[spriteNumber];
            default -> null;
        };

        graphic2D.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);

    }

    public void getPlayerImage()
    {
        try {
            up[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/walking/boy_up_1.png")));
            up[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/walking/boy_up_2.png")));
            down[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/walking/boy_down_1.png")));
            down[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/walking/boy_down_2.png")));
            left[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/walking/boy_left_1.png")));
            left[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/walking/boy_left_2.png")));
            right[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/walking/boy_right_1.png")));
            right[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/walking/boy_right_2.png")));
        } catch (IOException event) {
            event.printStackTrace();
        }
    }
}