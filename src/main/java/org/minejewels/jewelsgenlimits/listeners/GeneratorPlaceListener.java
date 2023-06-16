package org.minejewels.jewelsgenlimits.listeners;

import net.abyssdev.abysslib.listener.AbyssListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.minejewels.jewelsgenlimits.JewelsGenLimits;
import org.minejewels.jewelsgenlimits.player.GenPlayer;
import org.minejewels.jewelsgens.events.GeneratorPlaceEvent;

public class GeneratorPlaceListener extends AbyssListener<JewelsGenLimits> {

    private final String permission;
    private final int baseLimit;

    public GeneratorPlaceListener(final JewelsGenLimits plugin) {
        super(plugin);

        this.permission = plugin.getSettingsConfig().getString("bypass-permission");
        this.baseLimit = this.plugin.getSettingsConfig().getInt("limit");
    }

    @EventHandler
    public void onPlace(final GeneratorPlaceEvent event) {

        final Player player = event.getPlayer();
        final GenPlayer genPlayer = this.plugin.getPlayerStorage().get(player.getUniqueId());

        if (player.hasPermission(this.permission)) {
            genPlayer.addGenerator(event.getGeneratorData());
            return;
        }

        if (!genPlayer.canPlaceGenerator(this.baseLimit)) {
            this.plugin.getMessageCache().sendMessage(player, "messages.too-many-generators");
            event.setCancelled(true);
            return;
        }

        genPlayer.addGenerator(event.getGeneratorData());
    }
}
