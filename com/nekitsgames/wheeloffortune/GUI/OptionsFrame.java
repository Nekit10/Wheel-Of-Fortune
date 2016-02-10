package com.nekitsgames.wheeloffortune.GUI;

import com.nekitsgames.wheeloffortune.lang.LangSystem;
import com.nekitsgames.wheeloffortune.lang.*;
import com.nekitsgames.wheeloffortune.options.OptionsSystem;
import com.nekitsgames.wheeloffortune.system.*;
import com.nekitsgames.wheeloffortune.system.Main;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class OptionsFrame extends JFrame  {

    //******FORM CONSTANTS******
    private final String label = EN.options;
    private final int height = 160;
    private final int width = 250;
    private int x, y;
    private final String[] langs = {
            "English",
            "Русский",
            "Український"
    };

    //******CLASSES******
    private Toolkit tools =Toolkit.getDefaultToolkit();
    private Container pane = getContentPane();
    private GroupLayout layout = new GroupLayout(pane);
    private Font sFont = new Font("Arial", Font.BOLD, 15);
    private Font lFont = new Font("Arial", Font.PLAIN, 13);
    private Font font = sFont;

    //******GUI ELEMENTS******
    public JLabel lSelectLang = new JLabel(EN.selectLang);
    public JComboBox cLangs = new JComboBox(langs);
    public JButton bStartAgain = new JButton(EN.startAgain);

    public OptionsFrame(){
        LogSystem.LogInfo("Создание формы OptionsFrame.");
        switch (OptionsSystem.getOption("lang")){
            case "RU": {
                lSelectLang.setText(RU.selectLang);
                bStartAgain.setText(RU.startAgain);

                LangSystem.setWaring3(RU.waring3);

                setTitle(RU.options);

                font = lFont;

                break;
            }
            case "UA": {
                lSelectLang.setText(UA.selectLang);
                bStartAgain.setText(UA.startAgain);

                LangSystem.setWaring3(UA.waring3);

                setTitle(UA.options);

                font = lFont;
            }
        }
        x = (tools.getScreenSize().width - width) /2;
        y = (tools.getScreenSize().height - height)/2;

        setTitle(label);
        setLocation(x, y);
        setSize(width, height);
        setResizable(false);
        setContentPane(pane);
        setLayout(layout);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {

            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                switch (cLangs.getSelectedIndex()){
                    case 0: {OptionsSystem.setOption("lang", "EN"); break;}
                    case 1: {OptionsSystem.setOption("lang", "RU"); break;}
                    case 2: {OptionsSystem.setOption("lang", "UA"); break;}
                }
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {

            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {

            }
        });

        //**lSelectLang**
        lSelectLang.setFont(font);
        lSelectLang.setVisible(true);
        lSelectLang.setBounds(45, 10, 200, 20);

        //**cLangs**
        cLangs.setFont(font);
        cLangs.setVisible(true);
        cLangs.setBounds(10, 40, 225, 25);
        switch (OptionsSystem.getOption("lang")){
            case "EN": {cLangs.setSelectedIndex(0); break;}
            case "RU": {cLangs.setSelectedIndex(1); break;}
            case "UA": {cLangs.setSelectedIndex(2); break;}
        }

        //**bStartAgain**
        bStartAgain.setFont(font);
        bStartAgain.setVisible(true);
        bStartAgain.setBounds(20,80,200,30);
        bStartAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (JOptionPane.showConfirmDialog (null, LangSystem.getWaring3(),"Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    OptionsSystem.setOption("points", "1000");
                    OptionsSystem.setOption("debt", "0");
                    Main.mainFrame.lPoints.setText(LangSystem.getPoints() + "1000");
                    Main.mainFrame.lDebt.setText(LangSystem.getDebt() + "0 ");
                }
            }
        });

        add(lSelectLang);
        add(cLangs);
        add(bStartAgain);

        setVisible(true);
    }

}
