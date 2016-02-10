package com.nekitsgames.wheeloffortune.GUI;


import com.nekitsgames.wheeloffortune.lang.LangSystem;
import com.nekitsgames.wheeloffortune.lang.*;
import com.nekitsgames.wheeloffortune.options.OptionsSystem;
import com.nekitsgames.wheeloffortune.system.ErrorSystem;
import com.nekitsgames.wheeloffortune.system.LogSystem;
import com.nekitsgames.wheeloffortune.system.iSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Calendar;

public class MainFrame extends JFrame {


    //******FORM CONSTANTS & VARIABLES******
    private final int height = 500;
    private final int width = 450;
    private final String label = EN.mainCaption;
    private int x, y;
    int i;

    //******CLASSES******
    private Toolkit tools =Toolkit.getDefaultToolkit();
    private Container pane = getContentPane();
    private GroupLayout layout = new GroupLayout(pane);
    private Font sFont = new Font("Arial", Font.BOLD, 15);
    private Font lFont = new Font("Arial", Font.PLAIN, 13);
    private Font font = sFont;
    private Calendar calendar = Calendar.getInstance();

    //******GUI ELEMENTS******
    public JLabel lPoints = new JLabel();
    public JLabel lDebt = new JLabel();

    public JTextArea tGameLog = new JTextArea("");

    public JButton bBorrow = new JButton(EN.borrow);
    public JButton bReturn = new JButton(EN.iReturn);
    public JButton bSpin = new JButton(EN.spin);
    public JButton bOptions = new JButton(EN.options);


    public MainFrame(){
        LogSystem.LogInfo("Создание формы MainFrame.");
        setTitle(label);
        switch (OptionsSystem.getOption("lang")){

            case "RU": {
                LangSystem.setDebt(RU.debt);
                LangSystem.setEnter(RU.enter);
                LangSystem.setHaveDebt(RU.haveDebt);
                LangSystem.setJackpot(RU.jackpot);
                LangSystem.setNotHaveDebt(RU.notHaveDebt);
                LangSystem.setNotHavePoints(RU.notHavePoints);
                LangSystem.setPoints(RU.points);
                LangSystem.setWin(RU.got);
                LangSystem.setValidNum(RU.validNum);
                LangSystem.setWaring(RU.waring);
                LangSystem.setWaring2(RU.waring2);

                bBorrow.setText(RU.borrow);
                bReturn.setText(RU.iReturn);
                bOptions.setText(RU.options);
                bSpin.setText(RU.spin);
                setTitle(RU.mainCaption);

                font = lFont;
                break;
            }
            case "UA": {
                LangSystem.setDebt(UA.debt);
                LangSystem.setEnter(UA.enter);
                LangSystem.setHaveDebt(UA.haveDebt);
                LangSystem.setJackpot(UA.jackpot);
                LangSystem.setNotHaveDebt(UA.notHaveDebt);
                LangSystem.setNotHavePoints(UA.notHavePoints);
                LangSystem.setPoints(UA.points);
                LangSystem.setWin(UA.got);
                LangSystem.setValidNum(UA.validNum);
                LangSystem.setWaring(UA.waring);
                LangSystem.setWaring2(UA.waring2);

                bBorrow.setText(UA.borrow);
                bReturn.setText(UA.iReturn);
                bOptions.setText(UA.options);
                bSpin.setText(UA.spin);
                setTitle(UA.mainCaption);

                font = lFont;
            }

        }
        x = (tools.getScreenSize().width - width) /2;
        y = (tools.getScreenSize().height - height)/2;

        setLocation(x, y);
        setSize(width, height);
        setResizable(false);
        setContentPane(pane);
        setLayout(layout);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {

            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                LogSystem.CloseLog();
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

        //**lPoints**
        lPoints.setFont(font);
        lPoints.setVisible(true);
        lPoints.setBounds(5, 10, 100, 20);
        lPoints.setText(LangSystem.getPoints() + OptionsSystem.getOption("points"));

        //**lDebt**
        lDebt.setFont(font);
        lDebt.setVisible(true);
        lDebt.setBounds(125, 10, 100, 20);
        lDebt.setText(LangSystem.getDebt() + OptionsSystem.getOption("debt"));

        //**tGameLog**
        tGameLog.setFont(font);
        tGameLog.setVisible(true);
        tGameLog.setBounds(5, 30, 430, 380);
        tGameLog.setEditable(false);

        //**bBorrow**
        bBorrow.setFont(font);
        bBorrow.setVisible(true);
        bBorrow.setBounds(210, 5, 110, 20);
        bBorrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (JOptionPane.showConfirmDialog (null, LangSystem.getWaring(),"Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    try {
                        int debt = Integer.parseInt(JOptionPane.showInputDialog(LangSystem.getEnter()));
                        if (debt < 0 | debt > 9000) {
                            JOptionPane.showMessageDialog(null, LangSystem.getValidNum(), "Valid number", JOptionPane.ERROR_MESSAGE);
                        } else if (Integer.parseInt(OptionsSystem.getOption("debt")) != 0) {
                            JOptionPane.showMessageDialog(null, LangSystem.getHaveDebt(), "ERROR", JOptionPane.ERROR_MESSAGE);
                        } else {
                            int points = Integer.parseInt(OptionsSystem.getOption("points")) + debt;
                            OptionsSystem.setOption("debt", String.valueOf(debt));
                            OptionsSystem.setOption("points", String.valueOf(points));
                            lPoints.setText(LangSystem.getPoints() + points);
                            lDebt.setText(LangSystem.getDebt() + debt);
                            int day, month, year;
                            day = calendar.get(Calendar.DAY_OF_MONTH);
                            month = calendar.get(Calendar.MONTH);
                            year = calendar.get(Calendar.YEAR);
                            OptionsSystem.setOption("debt.day", String.valueOf(day));
                            OptionsSystem.setOption("debt.month", String.valueOf(month + 1));
                            OptionsSystem.setOption("debt.year", String.valueOf(year));
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, LangSystem.getValidNum(), "Valid number", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        //**bReturn**
        bReturn.setFont(font);
        bReturn.setVisible(true);
        bReturn.setBounds(325, 5, 110, 20);
        bReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int points, debt, day, year, month;
                day = Integer.parseInt(OptionsSystem.getOption("debt.day"));
                month = Integer.parseInt(OptionsSystem.getOption("debt.month"));
                year = Integer.parseInt(OptionsSystem.getOption("debt.year"));
                points = Integer.parseInt(OptionsSystem.getOption("points"));
                debt = Integer.parseInt(OptionsSystem.getOption("debt"));
                if (debt == 0){JOptionPane.showMessageDialog(null, LangSystem.getNotHaveDebt(), "NO DEBT", JOptionPane.INFORMATION_MESSAGE);}else {
                    debt = iSystem.calcDebt(debt, day, month, year);
                    if (debt > points) {
                        JOptionPane.showMessageDialog(null, LangSystem.getNotHavePoints(), "NOT ENOUGH POINTS", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if (JOptionPane.showConfirmDialog (null, LangSystem.getWaring2().replaceAll("x", String.valueOf(debt)),"Warning",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                        points -= debt;
                        debt = 0;
                        OptionsSystem.setOption("points", String.valueOf(points));
                        OptionsSystem.setOption("debt", String.valueOf(debt));
                        lPoints.setText(LangSystem.getPoints() + points);
                        lDebt.setText(LangSystem.getDebt() + debt);
                    }
                }
            }
        });
        //**bSpin**
        bSpin.setFont(font);
        bSpin.setVisible(true);
        bSpin.setBounds(5, 420, 215, 40);
        bSpin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                i++;
                if (i == 22){tGameLog.setText(""); i = 0;}
                LogSystem.LogInfo("Крутим колесо.");
                try{
                    long rate, points, win;
                    points = Integer.parseInt(OptionsSystem.getOption("points"));
                    rate = iSystem.getRate(points);
                    if (points - rate >0) {
                        win = iSystem.getWinPoints();
                        points = points - rate + win;
                        OptionsSystem.setOption("points", String.valueOf(points));
                        lPoints.setText(LangSystem.getPoints() + points);
                        if (win != 100000){tGameLog.append(LangSystem.getWin().replaceAll("x", String.valueOf(win)) + "\n");} else {tGameLog.append(LangSystem.getJackpot() + LangSystem.getWin().replaceAll("x", String.valueOf(win)) + "\n");}
                    } else{JOptionPane.showMessageDialog(null, LangSystem.getNotHavePoints(), "NOT ENOUGH POINTS", JOptionPane.INFORMATION_MESSAGE);}

                } catch (Exception e){
                    ErrorSystem.SendExceptionDataToNG(e);
                    JOptionPane.showMessageDialog(null, e.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    LogSystem.LogERR(e.toString());
                }
            }
        });
        //**bOptions**
        bOptions.setFont(font);
        bOptions.setVisible(true);
        bOptions.setBounds(225, 420, 215, 40);
        bOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                OptionsFrame optionsFrame = new OptionsFrame();
            }
        });

        add(lPoints);
        add(lDebt);
        add(tGameLog);
        add(bBorrow);
        add(bReturn);
        add(bSpin);
        add(bOptions);

        setVisible(true);
    }

}
