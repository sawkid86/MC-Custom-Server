package net.swofty.type.hub.gui;

import net.minestom.server.item.Material;
import net.swofty.types.generic.enchantment.EnchantmentType;
import net.swofty.types.generic.enchantment.SkyBlockEnchantment;
import net.swofty.types.generic.gui.inventory.SkyBlockShopGUI;
import net.swofty.types.generic.item.ItemType;
import net.swofty.types.generic.item.SkyBlockItem;
import net.swofty.types.generic.shop.type.CoinShopPrice;

public class GUIShopLibrarian extends SkyBlockShopGUI {
    public GUIShopLibrarian() {
        super("Librarian", 1, DEFAULT);
    }

    @Override
    public void initializeShopItems() {
        attachItem(ShopItem.Stackable(new SkyBlockItem(Material.EXPERIENCE_BOTTLE), 1, new CoinShopPrice(30)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.BOOK), 1, new CoinShopPrice(20)));

        SkyBlockItem sharpness = new SkyBlockItem(ItemType.ENCHANTED_BOOK);
        sharpness.getAttributeHandler().addEnchantment(
                new SkyBlockEnchantment(EnchantmentType.SHARPNESS, 1)
        );

        SkyBlockItem scavenger = new SkyBlockItem(ItemType.ENCHANTED_BOOK);
        scavenger.getAttributeHandler().addEnchantment(
                new SkyBlockEnchantment(EnchantmentType.SCAVENGER, 1)
        );

        SkyBlockItem protection = new SkyBlockItem(ItemType.ENCHANTED_BOOK);
        protection.getAttributeHandler().addEnchantment(
                new SkyBlockEnchantment(EnchantmentType.PROTECTION, 1)
        );

        SkyBlockItem efficiency = new SkyBlockItem(ItemType.ENCHANTED_BOOK);
        efficiency.getAttributeHandler().addEnchantment(
                new SkyBlockEnchantment(EnchantmentType.EFFICIENCY, 1)
        );

        attachItem(ShopItem.Single(sharpness, 1, new CoinShopPrice(30)));
        attachItem(ShopItem.Single(scavenger, 1, new CoinShopPrice(40)));
        attachItem(ShopItem.Single(protection, 1, new CoinShopPrice(20)));
        attachItem(ShopItem.Single(efficiency, 1, new CoinShopPrice(20)));

    }
}
