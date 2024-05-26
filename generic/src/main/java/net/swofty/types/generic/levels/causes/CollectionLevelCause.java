package net.swofty.types.generic.levels.causes;

import lombok.Getter;
import net.swofty.types.generic.collection.CollectionCategories;
import net.swofty.types.generic.collection.CollectionCategory;
import net.swofty.types.generic.item.ItemType;
import net.swofty.types.generic.levels.abstr.SkyBlockLevelCauseAbstr;
import net.swofty.types.generic.user.SkyBlockPlayer;

@Getter
public class CollectionLevelCause extends SkyBlockLevelCauseAbstr {
    private final ItemType type;
    private final int level;
    private final CollectionCategory.ItemCollection collection;

    public CollectionLevelCause(ItemType type, int level) {
        this.type = type;
        this.level = level;

        CollectionCategory category = CollectionCategories.getCategory(type);
        if (category == null) {
            this.collection = null;
            return;
        }
        this.collection = category.getCollection(type);
    }

    @Override
    public double xpReward() {
        for (CollectionCategory.ItemCollectionReward reward : collection.rewards()) {
            if (collection.getPlacementOf(reward) == level) {
                int xp = 0;
                for (CollectionCategory.Unlock unlock : reward.unlocks()) {
                    if (unlock.type() == CollectionCategory.Unlock.UnlockType.XP) {
                        xp += ((CollectionCategory.UnlockXP) unlock).xp();
                    }
                }
                return xp;
            }
        }
        return 5;
    }

    @Override
    public boolean shouldDisplayMessage(SkyBlockPlayer player) {
        return false; // This is handled by the Collection Level up message
    }
}
