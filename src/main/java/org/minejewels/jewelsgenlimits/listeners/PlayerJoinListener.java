package org.minejewels.jewelsgenlimits.listeners;

import net.abyssdev.abysslib.listener.AbyssListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.minejewels.jewelsgenlimits.JewelsGenLimits;
import org.minejewels.jewelsgenlimits.player.GenPlayer;

public class PlayerJoinListener extends AbyssListener<JewelsGenLimits> {

    public PlayerJoinListener(final JewelsGenLimits plugin) {
        super(plugin);
    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {

        final Player player = event.getPlayer();

        if (this.plugin.getPlayerStorage().contains(player.getUniqueId())) return;

        this.plugin.getPlayerStorage().save( new GenPlayer(player.getUniqueId()));
    }
}
