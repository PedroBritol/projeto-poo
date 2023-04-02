package main;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        GamePanel gamePanel = new GamePanel();
        JFrame window = new JFrame("Titulo");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}