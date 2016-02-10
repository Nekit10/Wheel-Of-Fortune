package com.nekitsgames.wheeloffortune.system;

import com.devcolibri.tls.Sender;
import java.lang.*;
import java.lang.System;

public class ErrorSystem {


    public static void SendExceptionDataToNG(Exception e){
        LogSystem.LogInfo("Отправка данных в Nekit's games.");
        String msg;
        msg = "Отчет об ошибке в игре 'Колесо Фортуны' \n Exception: " + e.toString() + "\n LOG: \n" + LogSystem.getLOG() + "\n \n OS name: " + System.getProperty("os.name") + "\n OS version: " + System.getProperty("os.version") + "\n OS arch: " + System.getProperty("os.arch") + "\n Java version: " + System.getProperty("java.version") + "\n ---------------------------------------------- \n Это сообщение было отправленно автамотически! Пожалуйста, не отвечайте на это письмо!";
        try{
            Sender tlsSender = new com.devcolibri.tls.Sender("ng.weeloffortune@gmail.com", "CBV-Nffs76");
            tlsSender.send("ОШИБКА В КОЛЕСО ФОРТУНЫ", msg, "ng.weeloffortune@gmail.com", "nikitaserba@gmail.com");
        } catch (Exception ec){
            System.err.println(ec);
            LogSystem.LogERR(ec.toString());
            LogSystem.LogERR("Не удалось отправить данные об ошибке в ng.");
            System.exit(2);
    }
    }

}
