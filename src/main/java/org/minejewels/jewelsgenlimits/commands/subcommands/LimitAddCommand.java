package org.minejewels.jewelsgenlimits.commands.subcommands;

import net.abyssdev.abysslib.command.AbyssSubCommand;
import net.abyssdev.abysslib.command.context.CommandContext;
import net.abyssdev.abysslib.placeholder.PlaceholderReplacer;
import net.abyssdev.abysslib.utils.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.eclipse.collections.api.factory.Sets;
import org.minejewels.jewelsgenlimits.JewelsGenLimits;
import org.minejewels.jewelsgenlimits.player.GenPlayer;

import java.util.Optional;

public class LimitAddCommand extends AbyssSubCommand<JewelsGenLimits> {

    public LimitAddCommand(final JewelsGenLimits plugin) {
        super(plugin, 2, Sets.immutable.of("add", "put"));
    }

    @Override
    public void execute(final CommandContext<?> context) {
        final CommandSender sender = context.getSender();

        if (!sender.hasPermission("genlimit.admin")) {
            this.plugin.getMessageCache().sendMessage(sender, "messages.no-permission");
            return;
        }

        final Optional<Player> optionalPlayer = Optional.ofNullable(context.asPlayer(0));

        if (!optionalPlayer.isPresent()) {
            this.plugin.getMessageCache().sendMessage(sender, "messages.player-doesnt-exist");
            return;
        }

        final Player player = optionalPlayer.get();

        if (!Utils.isInteger(String.valueOf(context.asInt(1)))) {
            this.plugin.getMessageCache().sendMessage(sender, "messages.invalid-number");
            return;
        }

        final int amount = context.asInt(1);

        if (amount <= 0) {
            this.plugin.getMessageCache().sendMessage(sender, "messages.invalid-number");
            return;
        }

        final GenPlayer targetProfile = this.plugin.getPlayerStorage().get(player.getUniqueId());

        final PlaceholderReplacer replacer = new PlaceholderReplacer()
                .addPlaceholder("%amount%", Utils.format(amount))
                .addPlaceholder("%new%", Utils.format(amount + targetProfile.getAddedSlots()));

        targetProfile.addSlots(amount);

        this.plugin.getMessageCache().sendMessage(player, "messages.slots-added", replacer);
    }
}
