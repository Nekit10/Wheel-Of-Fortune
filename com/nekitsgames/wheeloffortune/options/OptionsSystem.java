package com.nekitsgames.wheeloffortune.options;

import com.nekitsgames.wheeloffortune.system.ErrorSystem;
import com.nekitsgames.wheeloffortune.system.LogSystem;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class OptionsSystem {

    private static final String osType = "UNIX";
    private static JSONObject options = new JSONObject();
    private static JSONParser optionsParser = new JSONParser();
    private static OutputStream stream;


    public static String getFilesFolder(){
        LogSystem.LogInfo("Определение папки игры. Тип ОС: " + osType);
        String name = null;
        switch (osType){
            case "NT": {name = System.getenv("APPDATA") + "\\Nekits games\\wheel of fortune\\"; break;}
            case "OS X": {name = "/Library/Application Support/Nekits games/wheel of fortune/options/"; break;}
            case "UNIX": {name = System.getProperty("user.home") + "/.Nekits games/wheel of fortune/"; break;}
            }
        return name;
        }

    public static void setOption(String optionName, String optionValue){
        LogSystem.LogInfo("Сохраняем настройки. НАСТРОЙКА: " + optionName + "ЗНАЧЕНИЕ: " + optionValue);
        String tmp = "";
        File file = new File(getFilesFolder());
        file.mkdirs();
        try {
            file = new File(getFilesFolder() + "options.json");
            if (file.createNewFile()) {
                LogSystem.LogInfo("Создание нового файла.");
                stream = new FileOutputStream(getFilesFolder() + "options.json");
                byte data[] = "{\"points\":\"1000\",\"debt\": \"0\",\"lang\": \"EN\"}".getBytes();
                stream.write(data, 0, data.length);
            }
            Scanner in = new Scanner(new File(getFilesFolder() + "options.json"));
            while (in.hasNext())
                tmp += in.nextLine() + "\r\n";
            in.close();
            options = (JSONObject) optionsParser.parse(tmp);
            options.put(optionName, optionValue);
            byte data2[] = options.toString().getBytes();
            stream = new FileOutputStream(getFilesFolder() + "options.json");
            stream.write(data2, 0, data2.length);
        } catch (Exception e){
            ErrorSystem.SendExceptionDataToNG(e);
            JOptionPane.showMessageDialog(null, e.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
            LogSystem.LogERR(e.toString());
        }
    }

    public static String getOption(String optionName){
        LogSystem.LogInfo("Чтение настроек. НАСТРОЙКА: " + optionName);
        String optionsValue = "";
        try {
            Scanner in = new Scanner(new File(getFilesFolder() + "options.json"));
            while (in.hasNext())
                optionsValue += in.nextLine() + "\r\n";
            in.close();
            options = (JSONObject) optionsParser.parse(optionsValue);
            optionsValue = options.get(optionName).toString();
        } catch (Exception e){
            setOption("points", "1000");
            setOption("debt", "0");
            setOption("lang", "EN");
        }
        return optionsValue;

    }



    }
