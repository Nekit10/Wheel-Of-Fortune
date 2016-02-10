package com.nekitsgames.wheeloffortune.system;

import com.nekitsgames.wheeloffortune.options.OptionsSystem;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.*;

public class LogSystem {
    private static OutputStream stream;

    private static String LOG = "";

    public static void LogInfo(String msg){
        LOG += "\n" + "INFO " + msg;
    }

    public static void LogERR(String msg){
        LOG += "\n" + "ERROR " + msg;
    }

    public static String getLOG() {
        return LOG;
    }

    public static void CloseLog(){
        try {
            File file = new File(OptionsSystem.getFilesFolder());
            file.mkdirs();
            stream = new FileOutputStream(OptionsSystem.getFilesFolder() + "latest.log");
            byte data[] = LOG.getBytes();
            stream.write(data, 0, data.length);
        } catch (Exception e){
            ErrorSystem.SendExceptionDataToNG(e);
            JOptionPane.showMessageDialog(null, e.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
            java.lang.System.exit(1);
        }
    }
}
