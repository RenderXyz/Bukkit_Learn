package com.render.loginplugin.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

/**
 * @author Render
 * @ClassName DengLuLogInEvent
 * @date 2022/11/2 14:31
 */
public class DengLuLogInEvent extends PlayerEvent
{
    private static final HandlerList handlers = new HandlerList();

    public DengLuLogInEvent(Player who)
    {
        super(who);
    }

    @Override
    public HandlerList getHandlers()
    {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
