package org.minejewels.jewelsgenlimits.player;

import lombok.Data;
import net.abyssdev.abysslib.storage.id.Id;
import org.minejewels.jewelsgens.gen.data.GeneratorData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class GenPlayer {

    @Id
    private final UUID uuid;
    private final List<UUID> generatorsPlaced;
    private int addedSlots;

    public GenPlayer(final UUID uuid) {
        this.uuid = uuid;
        this.generatorsPlaced = new ArrayList<>();
        this.addedSlots = 0;
    }

    public void addGenerator(final GeneratorData generatorData) {
        this.generatorsPlaced.add(generatorData.getUuid());
    }

    public void removeGenerator(final GeneratorData generatorData) {
        this.generatorsPlaced.remove(generatorData.getUuid());
    }

    public boolean canPlaceGenerator(final int baseLimit) {
        return this.generatorsPlaced.size() + 1 <= baseLimit + this.addedSlots;
    }

    public void addSlots(final int amount) {
        this.addedSlots += amount;
    }

    public void removeSlots(final int amount) {

        if (this.addedSlots-amount < 0) {
            this.addedSlots = 0;
            return;
        }

        this.addedSlots -= amount;
    }
}
