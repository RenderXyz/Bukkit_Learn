package com.render.loginplugin.api;

import com.render.loginplugin.LogInPlugin;
import com.render.loginplugin.date.LoginDate;

import java.io.File;
import java.io.IOException;

import static com.render.loginplugin.util.ConfigUtil.yamlConfig;


/**
 * @author Render
 * @ClassName LogInPluginAPI
 * @date 2022/11/2 15:31
 */
public class LogInPluginAPI
{
    //是否注册
    public static boolean hasRegister(String playerName)
    {
        //如果含有子串便返回一个true 否则返回false
        return yamlConfig.contains(playerName.toLowerCase());
    }

    //是否登录
    public static boolean hasLogin(String playerName)
    {
        //如果LoginDate中有该名字 则已经登陆
        return LoginDate.hasPlayerName(playerName);
    }

    //验证密码
    public static boolean isPassword(String playerName, String password)
    {
        return password.equals(yamlConfig.get(playerName.toLowerCase()));
    }

    //注册
    public static void register(String playerName, String password)
    {
        yamlConfig.set(playerName.toLowerCase(), password);

        try
        {
            yamlConfig.save(new File(LogInPlugin.logInPlugin.getDataFolder(), "player-config.yml"));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
