package com.render.loginplugin.date;

import com.render.loginplugin.LogInPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Render
 * @ClassName LoginDate
 * @date 2022/11/2 13:23
 */
public class LoginDate
{
    private static final List<String> playerLoginInfo = new ArrayList<>();

    public LoginDate()
    {
        File file = new File(LogInPlugin.getInstance().getDataFolder(), "player-config.yml");
        if (!file.exists())
        {
            try
            {
                file.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        //读取文件
        FileConfiguration data = YamlConfiguration.loadConfiguration(file);
    }

    //将PlayerName 加入 LoginDate
    public static void addPlayerName(String playerName)
    {
        String LowerCasePlayerName = playerName.toLowerCase();

        //如果playerLoginInfo不包含 玩家名字小写
        if (!playerLoginInfo.contains(LowerCasePlayerName))
        {
            playerLoginInfo.add(LowerCasePlayerName);
        }
    }

    //将PlayerName 从 LoginDate 移除
    public static void removePlayerName(String playerName)
    {
        String LowerCasePlayerName = playerName.toLowerCase();
        playerLoginInfo.remove(LowerCasePlayerName);
    }


    //playerLoginInfo中是否存在名字
    public static boolean hasPlayerName(String playerName)
    {
        String LowerCasePlayerName = playerName.toLowerCase();
        //如果playerLoginInfo 存在 playerName 则 return true 则是已经登录
        return playerLoginInfo.contains(LowerCasePlayerName);
    }

    //打印出来看看
    public static List<String> getPlayerLoginInfo()
    {
        return playerLoginInfo;
    }
}
