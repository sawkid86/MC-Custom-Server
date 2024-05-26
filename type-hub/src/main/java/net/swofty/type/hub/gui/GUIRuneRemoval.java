package net.swofty.type.hub.gui;

import net.minestom.server.event.inventory.InventoryCloseEvent;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.swofty.types.generic.gui.inventory.ItemStackCreator;
import net.swofty.types.generic.gui.inventory.SkyBlockInventoryGUI;
import net.swofty.types.generic.gui.inventory.item.GUIClickableItem;
import net.swofty.types.generic.gui.inventory.item.GUIItem;
import net.swofty.types.generic.item.SkyBlockItem;
import net.swofty.types.generic.item.attribute.attributes.ItemAttributeRuneInfusedWith;
import net.swofty.types.generic.item.impl.Runeable;
import net.swofty.types.generic.item.updater.PlayerItemUpdater;
import net.swofty.types.generic.user.SkyBlockPlayer;

public class GUIRuneRemoval extends SkyBlockInventoryGUI {
    private final int[] borderSlots = {
            0, 8, 9, 17, 18, 26, 27, 35, 36, 44
    };
    private final int[] bottomSlots = {
            45, 46, 47, 48, 49, 50, 51, 52, 53
    };

    public GUIRuneRemoval() {
        super("Rune Removal", InventoryType.CHEST_6_ROW);
    }

    @Override
    public void onOpen(InventoryGUIOpenEvent e) {
        fill(Material.BLACK_STAINED_GLASS_PANE, "");
        for (int i : bottomSlots) {
            set(i, ItemStackCreator.createNamedItemStack(Material.WHITE_STAINED_GLASS_PANE));
        }
        set(GUIClickableItem.getCloseItem(49));
        set(GUIClickableItem.getGoBackItem(48, new GUIRunicPedestal()));

        updateFromItem(null);
    }

    public void updateFromItem(SkyBlockItem item) {
        border(ItemStackCreator.createNamedItemStack(Material.RED_STAINED_GLASS_PANE));

        if (item == null) {
            set(new GUIClickableItem(13) {
                @Override
                public void run(InventoryPreClickEvent e, SkyBlockPlayer player) {
                    ItemStack stack = e.getCursorItem();

                    if (stack.getDisplayName() == null) {
                        updateFromItem(null);
                        return;
                    }

                    SkyBlockItem item = new SkyBlockItem(stack);
                    updateFromItem(item);
                }

                @Override
                public boolean canPickup() {
                    return true;
                }

                @Override
                public ItemStack.Builder getItem(SkyBlockPlayer player) {
                    return ItemStack.builder(Material.AIR);
                }
            });
            set(new GUIClickableItem(22) {
                @Override
                public void run(InventoryPreClickEvent e, SkyBlockPlayer player) {
                    player.sendMessage("§cYou must place an item in the above slot!");
                }

                @Override
                public ItemStack.Builder getItem(SkyBlockPlayer player) {
                    return ItemStackCreator.getStack(
                            "§aRune Removal", Material.BARRIER, (short) 0, 1,
                            "§7Place an item with a rune attached to",
                            "§7it in the above slot."
                    );
                }
            });
            updateItemStacks(getInventory(), getPlayer());
            return;
        }

        set(new GUIClickableItem(13) {
            @Override
            public ItemStack.Builder getItem(SkyBlockPlayer player) {
                return PlayerItemUpdater.playerUpdate(player , item.getItemStack());
            }

            @Override
            public void run(InventoryPreClickEvent e, SkyBlockPlayer player) {
                ItemStack stack = e.getClickedItem();
                if (stack.isAir()) return;
                updateFromItem(null);

                player.addAndUpdateItem(stack);
            }
        });

        ItemAttributeRuneInfusedWith.RuneData runeData = item.getAttributeHandler().getRuneData();
        if (item.getAmount() > 1 ||
                item.getGenericInstance() == null ||
                !(item.getGenericInstance() instanceof Runeable) ||
                runeData == null ||
                !runeData.hasRune()) {
            set(new GUIItem(22) {
                @Override
                public ItemStack.Builder getItem(SkyBlockPlayer player) {
                    return ItemStackCreator.getStack(
                            "§cError!", Material.BARRIER, (short) 0, 1,
                            "§7Place an item with a rune attached to",
                            "§7it in the above slot."
                    );
                }
            });
            updateItemStacks(getInventory(), getPlayer());
            return;
        }

        border(ItemStackCreator.createNamedItemStack(Material.GREEN_STAINED_GLASS_PANE));
        set(new GUIClickableItem(22) {
            @Override
            public void run(InventoryPreClickEvent e, SkyBlockPlayer player) {
                runeData.setRuneType(null);
                runeData.setLevel(null);

                player.sendMessage("§aSuccessfully removed rune!");

                updateFromItem(item);
            }

            @Override
            public ItemStack.Builder getItem(SkyBlockPlayer player) {
                return ItemStackCreator.getStack(
                        "§aClick to Remove Rune!", Material.CAULDRON, (short) 0, 1,
                        "§cWARNING: The rune will be lost",
                        "§cforever!"
                );
            }
        });
        updateItemStacks(getInventory(), getPlayer());
    }

    @Override
    public boolean allowHotkeying() {
        return false;
    }

    @Override
    public void onClose(InventoryCloseEvent e, CloseReason reason) {
        ((SkyBlockPlayer) e.getPlayer()).addAndUpdateItem(new SkyBlockItem(e.getInventory().getItemStack(13)));
    }

    @Override
    public void suddenlyQuit(Inventory inventory, SkyBlockPlayer player) {
        player.addAndUpdateItem(new SkyBlockItem(inventory.getItemStack(13)));
    }

    @Override
    public void border(ItemStack.Builder stack) {
        for (int i : borderSlots) {
            set(i, stack);
        }
        updateItemStacks(getInventory(), getPlayer());
    }

    @Override
    public void onBottomClick(InventoryPreClickEvent e) {

    }
}
