package com.render.loginplugin.util;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static com.render.loginplugin.LogInPlugin.logInPlugin;

/**
 * @author Render
 * @ClassName ReaderUtil
 * @date 2022/11/2 15:55
 */
public class ConfigUtil
{
    public static FileConfiguration yamlConfig = new YamlConfiguration();

    public static void loadConfig()
    {
//        File dataFolder = new File(getDataFolder(), "data");
//        dataFolder.mkdirs();

        //判断资源文件是否存在 //不能存在则创建
        for (String fileName : new String[]{"player-config.yml"})
        {
            File f = new File(logInPlugin.getDataFolder(), fileName);
            if (!f.exists())
            {
                logInPlugin.saveResource(fileName, true);
            }
        }

        try
        {
            yamlConfig.load(new File(logInPlugin.getDataFolder(), "player-config.yml"));
        }
        catch (IOException | InvalidConfigurationException e)
        {
            e.printStackTrace();
        }
    }

}
