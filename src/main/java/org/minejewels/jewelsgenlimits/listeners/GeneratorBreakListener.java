package org.minejewels.jewelsgenlimits.listeners;

import net.abyssdev.abysslib.listener.AbyssListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.minejewels.jewelsgenlimits.JewelsGenLimits;
import org.minejewels.jewelsgenlimits.player.GenPlayer;
import org.minejewels.jewelsgens.events.GeneratorBreakEvent;
import org.minejewels.jewelsgens.events.GeneratorPlaceEvent;
import org.minejewels.jewelsgens.gen.data.GeneratorData;

import java.util.UUID;

public class GeneratorBreakListener extends AbyssListener<JewelsGenLimits> {

    public GeneratorBreakListener(final JewelsGenLimits plugin) {
        super(plugin);
    }

    @EventHandler
    public void onBreak(final GeneratorBreakEvent event) {

        final Player player = event.getPlayer();
        final GenPlayer genPlayer = this.plugin.getPlayerStorage().get(player.getUniqueId());

        for (final UUID uuid : genPlayer.getGeneratorsPlaced()) {
            if (!uuid.toString().equalsIgnoreCase(event.getGeneratorData().getUuid().toString())) continue;

            genPlayer.removeGenerator(event.getGeneratorData());
            return;
        }

        this.plugin.getMessageCache().sendMessage(player, "messages.not-your-generator");
        event.setCancelled(true);
    }
}
