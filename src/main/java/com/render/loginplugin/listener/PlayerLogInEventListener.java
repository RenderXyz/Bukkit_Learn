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
    public void playerJoin(PlayerJoinEvent e)
    {
        //重复进入服务器就踢出
        if(LoginDate.hasPlayerName(e.getPlayer().getName()))
        {
            e.getPlayer().kickPlayer("你已经登录了");
        }
    }

    @EventHandler
    public void playerLogin(DengLuLogInEvent e)
    {
        //当玩家成功登录后，将玩家添加到 PlayerLoginInfo
        LoginDate.addPlayerName(e.getPlayer().getName());
    }

    @EventHandler
    public void playerQuit(PlayerQuitEvent e)
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
                ((PlayerEvent) e).getPlayer().sendMessage(ChatColor.RED + "您暂时还不能这么做\n请输入/login <password> 来登录或注册");
            }
        }
    }

    @EventHandler//玩家移动
    public void notToDo(PlayerMoveEvent e)
    {
        CannotToDo(e);
    }

    @EventHandler//玩家对一个对象或空气进行交互时
    public void notToDo(PlayerInteractEvent e)
    {
        CannotToDo(e);
    }

    @EventHandler//玩家在实体上点击某实体上的某位置时
    public void notToDo(PlayerInteractAtEntityEvent e)
    {
        CannotToDo(e);
    }

    @EventHandler//玩家聊天
    public void notToDo(AsyncPlayerChatEvent e)
    {
        CannotToDo(e);
    }

    @EventHandler//玩家打开物品栏
    public void notToDo(InventoryOpenEvent e)
    {
        CannotToDo(e);
    }

    @EventHandler//玩家切换潜行
    public void notToDo(PlayerToggleSneakEvent e)
    {
        CannotToDo(e);
    }

    @EventHandler//玩家传送
    public void notToDo(PlayerTeleportEvent e)
    {
        CannotToDo(e);
    }

    @EventHandler//玩家丢出物品
    public void notToDo(PlayerDropItemEvent e) { CannotToDo(e);}

    @EventHandler//玩家切换主副手道具
    public void notToDo(PlayerSwapHandItemsEvent e) { CannotToDo(e);}
}
