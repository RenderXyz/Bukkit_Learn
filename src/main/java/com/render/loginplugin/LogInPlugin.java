package com.render.loginplugin;

import com.render.loginplugin.executor.logInCommandExecutor;
import com.render.loginplugin.listener.PlayerLogInEventListener;
import com.render.loginplugin.util.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * @author Render
 * @ClassName LogInPlugin
 * @date 2022/9/26 13:40
 */
public class LogInPlugin extends JavaPlugin
{
    public static LogInPlugin logInPlugin;

    @Override
    public void onEnable()
    {
        logInPlugin = this;
        ConfigUtil.loadConfig();
        Bukkit.getPluginManager().registerEvents(new PlayerLogInEventListener(), this);
        Objects.requireNonNull(Bukkit.getPluginCommand("Login")).setExecutor(new logInCommandExecutor());
    }

    @Override
    public void onDisable()
    {

    }

    public static LogInPlugin getInstance()
    {
        return logInPlugin;
    }
}




