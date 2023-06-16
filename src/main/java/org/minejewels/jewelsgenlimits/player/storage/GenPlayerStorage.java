package org.minejewels.jewelsgenlimits.player.storage;

import net.abyssdev.abysslib.patterns.registry.Registry;
import net.abyssdev.abysslib.storage.json.JsonStorage;
import net.abyssdev.abysslib.utils.file.Files;
import org.minejewels.jewelsgenlimits.JewelsGenLimits;
import org.minejewels.jewelsgenlimits.player.GenPlayer;

import java.util.UUID;

public class GenPlayerStorage extends JsonStorage<UUID, GenPlayer> {

    public GenPlayerStorage(final JewelsGenLimits plugin) {
        super(Files.file("data.json", plugin), GenPlayer.class);
    }
}
