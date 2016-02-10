package com.nekitsgames.wheeloffortune.system;

import com.nekitsgames.wheeloffortune.GUI.MainFrame;



public class Main {

    public static MainFrame mainFrame;

    public static void main(String... args) {
        LogSystem.LogInfo("Запуск игры.");
        mainFrame = new MainFrame();
    }
}
