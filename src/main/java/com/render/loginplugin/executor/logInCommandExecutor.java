package com.render.loginplugin.executor;

import com.render.loginplugin.api.LogInPluginAPI;
import com.render.loginplugin.date.LoginDate;
import com.render.loginplugin.event.DengLuLogInEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Turtle;

/**
 * @author Render
 * @ClassName logInCommandExecutor
 * @date 2022/11/2 13:37
 */
public class logInCommandExecutor implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        //指令发送者是玩家
        if (sender instanceof Player)
        {
            Player player = (Player) sender;


            String pwdConcat = String.join("<space>", args);

            //如果玩家已经登陆
            if (hasLogin(player))
            {
                return false;
            }

            //如果玩家注册过了
            if (hasRegister(player))
            {
                //执行登录
                return playerLogin(player,pwdConcat);
            }
            //如果玩家没有注册
            else
            {
                playerRegister(player, pwdConcat);
            }
        }

        return false;
    }

    private boolean playerLogin(Player player, String pwdConcat)
    {
        player.sendMessage("--请输入您的密码--");
        //验证密码正确与否
        if (LogInPluginAPI.isPassword(player.getName(), pwdConcat))
        {
            player.sendMessage(ChatColor.GREEN + "---------密码正确---------");

            //解锁账号
            LoginDate.removePlayerName(player.getName());
            player.sendMessage(ChatColor.GREEN + "--------您的帐号已解锁----------");

            DengLuLogInEvent event = new DengLuLogInEvent(player);
            Bukkit.getPluginManager().callEvent(event);
            return true;
        }
        else
        {
            player.sendMessage(ChatColor.RED + "--------密码错误----------");
            player.sendMessage(ChatColor.RED + "-------请重新输入-------");
            return false;
        }
    }

    private void playerRegister(Player player, String pwdConcat)
    {
        player.sendMessage(ChatColor.GREEN + "您还没有注册,\n 请输入/login <password>注册");

        LogInPluginAPI.register(player.getName(), pwdConcat);

        LoginDate.removePlayerName(player.getName());
        //解锁玩家
        player.sendMessage(ChatColor.GREEN + "-----注册成功-----");
    }

    //玩家是否注册
    private boolean hasRegister(Player player)
    {
        return LogInPluginAPI.hasRegister(player.getName());
    }

    private boolean hasLogin(Player player)
    {
        //有名字则已经处于在线或者登陆中
        if (LogInPluginAPI.hasLogin(player.getName()))
        {
            player.sendMessage(ChatColor.GREEN + "---你已经登录了---");
            return true;//已经登陆
        }

        return false;
    }
}
