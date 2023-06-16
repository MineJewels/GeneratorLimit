package org.minejewels.jewelsgenlimits.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.abyssdev.abysslib.placeholder.PlaceholderReplacer;
import net.abyssdev.abysslib.utils.Utils;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.minejewels.jewelsgenlimits.JewelsGenLimits;
import org.minejewels.jewelsgenlimits.player.GenPlayer;

import java.util.List;

public class GenLimitPlaceholder extends PlaceholderExpansion {

    private final JewelsGenLimits plugin;
    private final int baseLimit;
    private final String format;

    public GenLimitPlaceholder(final JewelsGenLimits plugin) {
        this.plugin = plugin;

        this.baseLimit = this.plugin.getSettingsConfig().getInt("limit");
        this.format = this.plugin.getSettingsConfig().getColoredString("placeholder-format");
    }

    @Override
    public @NotNull String getIdentifier() {
        return "genlimit";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Consciences";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {

        final GenPlayer genPlayer = this.plugin.getPlayerStorage().get(player.getUniqueId());
        final PlaceholderReplacer replacer = new PlaceholderReplacer()
                .addPlaceholder("%amount%", Utils.format(genPlayer.getGeneratorsPlaced().size()))
                .addPlaceholder("%max%", Utils.format(genPlayer.getAddedSlots() + this.baseLimit));

        return replacer.parse(this.format);
    }
}
