package com.space_td.game;

public class data {
    //релизы
    public static int v_release=0;
    //патчи
    public static int v_patch=4;
    //патчи патчей
    public static int v_subpatch=0;
    //тип билда. Сейчас у нас DEV - (ещё делаем каркасс)
    public static String build_type="DEV";
    //доп имя верссии. Сейчас у нас какой-то кринж
    public static String build_add="cringe";
    //имя приложухи
    public static String App_name="Space TD";
    public static int FPS=1;
    //собираем в 1...
    public static String window_name=App_name+" v"+v_release+"."+v_patch+"."+v_subpatch+"-"+build_type+": "+build_add+"";
}
