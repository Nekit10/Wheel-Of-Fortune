package com.nekitsgames.wheeloffortune.system;

import com.nekitsgames.wheeloffortune.system.exceptions.RandomNumberNotFoundException;

import java.util.Calendar;

public class iSystem {

    public static long getRate(long points){
        LogSystem.LogInfo("Вычесление ставки.");
        long rate;

        if (points > 0 && points <= 1000){rate = 100;} else
        if (points > 1000 && points <= 5000){rate = 500;} else
        if (points > 5000 && points <= 10000){rate = 1000;} else
        if (points > 10000 && points <= 50000){rate = 5000;} else
        if (points > 50000 && points <= 100000){rate = 10000;} else
        if (points > 100000 && points <= 500000){rate = 50000;} else {rate = 100000;}

        return rate;
    }

    public static int getWinPoints() throws RandomNumberNotFoundException {
        LogSystem.LogInfo("Вычесление приза.");
        int points;

        points = (int) Math.floor(Math.random() * 2390 + 1);

        if (points > 0 && points <= 800) {points = 0;} else
        if (points > 800 && points <= 1500) {points = 100;} else
        if (points > 1500 && points <= 1900) {points = 500;} else
        if (points > 1900 && points <= 2200) {points = 1000;} else
        if (points > 2200 && points <= 2300) {points = 3000;} else
        if (points > 2300 && points <= 2350) {points = 5000;} else
        if (points > 2350 && points <= 2380) {points = 10000;} else
        if (points > 2380 && points <= 2390) {points = 100000;} else {throw new RandomNumberNotFoundException("Error #101! Random number hasn't been processed by the System!");}

        return points;
    }

    public static int calcDebt(int debt, int day, int month, int year){
        Calendar calendar = Calendar.getInstance();
        int nowday, nowmonth, nowyear, newdebt, debtMonth;
        nowday = calendar.get(Calendar.DAY_OF_MONTH);
        nowmonth = calendar.get(Calendar.MONTH) + 1;
        nowyear = calendar.get(Calendar.YEAR);
        debtMonth = (nowmonth - month) + (nowyear - year) * 12;
        if (day > nowday) {debtMonth -= 1;}
        newdebt = debt;
        newdebt += Math.floor((newdebt / 2) * debtMonth);

        return newdebt;
    }

}
