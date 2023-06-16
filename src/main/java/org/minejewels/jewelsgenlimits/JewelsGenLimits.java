package org.minejewels.jewelsgenlimits;

import lombok.Getter;
import net.abyssdev.abysslib.config.AbyssConfig;
import net.abyssdev.abysslib.plugin.AbyssPlugin;
import net.abyssdev.abysslib.storage.Storage;
import net.abyssdev.abysslib.text.MessageCache;
import org.minejewels.jewelsgenlimits.commands.GenLimitCommand;
import org.minejewels.jewelsgenlimits.listeners.GeneratorBreakListener;
import org.minejewels.jewelsgenlimits.listeners.GeneratorPlaceListener;
import org.minejewels.jewelsgenlimits.listeners.PlayerJoinListener;
import org.minejewels.jewelsgenlimits.placeholder.GenLimitPlaceholder;
import org.minejewels.jewelsgenlimits.player.GenPlayer;
import org.minejewels.jewelsgenlimits.player.storage.GenPlayerStorage;

import java.util.UUID;

@Getter
public final class JewelsGenLimits extends AbyssPlugin {

    private static JewelsGenLimits api;

    private final AbyssConfig settingsConfig = this.getAbyssConfig("settings");
    private final AbyssConfig langConfig = this.getAbyssConfig("lang");

    private final MessageCache messageCache = new MessageCache(this.langConfig);

    private final Storage<UUID, GenPlayer> playerStorage = new GenPlayerStorage(this);

    @Override
    public void onEnable() {
        JewelsGenLimits.api = this;

        this.loadMessages(this.messageCache, this.langConfig);

        new PlayerJoinListener(this);
        new GeneratorPlaceListener(this);
        new GeneratorBreakListener(this);

        new GenLimitCommand(this);

        new GenLimitPlaceholder(this).register();
    }

    @Override
    public void onDisable() {
        this.playerStorage.write();
    }

    public static JewelsGenLimits get() {
        return JewelsGenLimits.api;
    }
}
