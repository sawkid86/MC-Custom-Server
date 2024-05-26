package net.swofty.type.hub.gui.elizabeth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.swofty.types.generic.item.ItemType;

@AllArgsConstructor
@Getter
public class CommunityShopItem {
    private ItemType itemType;
    private int price;
    private int amount;
}
