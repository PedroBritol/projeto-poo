package entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int x, y, speedY, speedX, jumpSpeed, sprintCounter;

    public BufferedImage[]
            up = new BufferedImage[2],
            down = new BufferedImage[2],
            left = new BufferedImage[2],
            right = new BufferedImage[2];

    public String direction;

    public boolean
            changeSprite = false,
            isWalking = false;
}