package org.minejewels.jewelsgenlimits.commands;

import net.abyssdev.abysslib.command.AbyssCommand;
import net.abyssdev.abysslib.command.context.CommandContext;
import org.bukkit.command.CommandSender;
import org.eclipse.collections.api.factory.Lists;
import org.minejewels.jewelsgenlimits.JewelsGenLimits;
import org.minejewels.jewelsgenlimits.commands.subcommands.LimitAddCommand;

public class GenLimitCommand extends AbyssCommand<JewelsGenLimits, CommandSender> {

    public GenLimitCommand(final JewelsGenLimits plugin) {
        super(
                plugin,
                "genlimit",
                "The main generator limit command!",
                Lists.mutable.of("genlimit", "genlimits", "generatorlimit"),
                CommandSender.class
        );

        this.register(new LimitAddCommand(plugin));

        this.register();
    }

    @Override
    public void execute(final CommandContext<CommandSender> context) {
        final CommandSender sender = context.getSender();

        if (!sender.hasPermission("genlimit.admin")) {
            this.plugin.getMessageCache().sendMessage(sender, "messages.no-permission");
            return;
        }

        this.plugin.getMessageCache().sendMessage(sender, "messages.help");
    }
}
