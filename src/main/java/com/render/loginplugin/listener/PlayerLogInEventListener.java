package com.render.loginplugin.listener;

import com.render.loginplugin.LogInPlugin;
import com.render.loginplugin.date.LoginDate;
import com.render.loginplugin.event.DengLuLogInEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.*;

import java.util.function.Supplier;
import java.util.logging.Level;

import static com.render.loginplugin.LogInPlugin.logInPlugin;

/**
 * @author Render
 * @ClassName PlayerLogInEventListener
 * @date 2022/11/2 13:22
 */
public class PlayerLogInEventListener implements Listener
{
    @EventHandler
    public void Player_login(DengLuLogInEvent e)
    {
        //当玩家成功登录后，将玩家添加到 PlayerLoginInfo
        LoginDate.addPlayerName(e.getPlayer().getName());
        logInPlugin.getLogger().log(Level.INFO, (Supplier<String>) LoginDate.getPlayerLoginInfo());
    }



    @EventHandler
    public void Player_quit(PlayerQuitEvent e)
    {
        LoginDate.removePlayerName(e.getPlayer().getName());
    }

    public static void CannotToDo(Cancellable e)
    {
        if(e instanceof PlayerEvent)
        {
            //如果playerLoginInfo 中没有 PlayerName 的小写
            if(!LoginDate.hasPlayerName(((PlayerEvent) e).getPlayer().getName()))
            {
                //限制成立
                e.setCancelled(true);
                ((PlayerEvent) e).getPlayer().sendMessage(ChatColor.GREEN + "您暂时还不能这么做\n请输入/login <password> 来登录或注册");
            }
        }
    }

    @EventHandler
    public void notToDo(PlayerMoveEvent e)
    {
        CannotToDo(e);
    }

    @EventHandler
    public void notToDo(PlayerInteractEvent e)
    {
        CannotToDo(e);
    }

    @EventHandler
    public void notToDo(PlayerInteractAtEntityEvent e)
    {
        CannotToDo(e);
    }

    @EventHandler
    public void notToDo(AsyncPlayerChatEvent e)
    {
        CannotToDo(e);
    }

    @EventHandler
    public void notToDo(InventoryOpenEvent e)
    {
        CannotToDo(e);
    }
}
