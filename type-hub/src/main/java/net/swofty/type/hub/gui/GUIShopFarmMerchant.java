package net.swofty.type.hub.gui;

import net.minestom.server.item.Material;
import net.swofty.types.generic.gui.inventory.SkyBlockShopGUI;
import net.swofty.types.generic.item.ItemType;
import net.swofty.types.generic.item.SkyBlockItem;
import net.swofty.types.generic.shop.type.CoinShopPrice;

public class GUIShopFarmMerchant extends SkyBlockShopGUI {
    public GUIShopFarmMerchant() {
        super("Farm Merchant", 1, DEFAULT);
    }

    @Override
    public void initializeShopItems() {
        attachItem(ShopItem.Stackable(new SkyBlockItem(Material.WHEAT), 3, new CoinShopPrice(30)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(Material.CARROT), 3, new CoinShopPrice(30)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(Material.POTATO), 3, new CoinShopPrice(30)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(Material.MELON_SLICE), 10, new CoinShopPrice(40)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(Material.SUGAR_CANE), 3, new CoinShopPrice(30)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(Material.PUMPKIN), 1, new CoinShopPrice(25)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(Material.COCOA_BEANS), 1, new CoinShopPrice(5)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(Material.RED_MUSHROOM), 1, new CoinShopPrice(25)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(Material.BROWN_MUSHROOM), 1, new CoinShopPrice(25)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(Material.SAND), 2, new CoinShopPrice(8)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(Material.CACTUS), 1, new CoinShopPrice(15)));
        attachItem(ShopItem.Stackable(new SkyBlockItem(ItemType.ENCHANTED_BONE_MEAL), 3, new CoinShopPrice(6)));
        attachItem(ShopItem.Single(new SkyBlockItem(ItemType.ROOKIE_HOE), 1, new CoinShopPrice(10)));

    }
}
