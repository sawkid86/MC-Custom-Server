package net.swofty.types.generic.gui.inventory.inventories.banker;

import net.kyori.adventure.text.Component;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.swofty.types.generic.calendar.SkyBlockCalendar;
import net.swofty.types.generic.data.DataHandler;
import net.swofty.types.generic.data.datapoints.DatapointBankData;
import net.swofty.types.generic.gui.inventory.ItemStackCreator;
import net.swofty.types.generic.gui.inventory.RefreshingGUI;
import net.swofty.types.generic.gui.inventory.SkyBlockInventoryGUI;
import net.swofty.types.generic.gui.inventory.item.GUIClickableItem;
import net.swofty.types.generic.gui.inventory.item.GUIItem;
import net.swofty.types.generic.user.SkyBlockPlayer;
import net.swofty.types.generic.utility.StringUtility;

import java.util.ArrayList;
import java.util.List;

public class GUIBanker extends SkyBlockInventoryGUI implements RefreshingGUI {
    public GUIBanker() {
        super("Bank Account", InventoryType.CHEST_4_ROW);
    }

    @Override
    public void setItems(InventoryGUIOpenEvent e) {
        if (e.player().isBankDelayed) {
            e.player().sendMessage("§cYou currently have processing transactions!");
            e.player().sendMessage("§cPlease wait a moment before accessing your bank account.");
            e.player().closeInventory();
            return;
        }

        e.inventory().setTitle(Component.text(
                (e.player().isCoop() ? "Co-op" : "Personal") + " Bank Account"
        ));

        refreshItems(e.player());
    }

    @Override
    public void refreshItems(SkyBlockPlayer player) {
        fill(Material.BLACK_STAINED_GLASS_PANE, "");
        set(GUIClickableItem.getCloseItem(31));

        DatapointBankData.BankData bankData = player.getDataHandler()
                .get(DataHandler.Data.BANK_DATA, DatapointBankData.class)
                .getValue();

        set(new GUIItem(32) {
            @Override
            public ItemStack.Builder getItem(SkyBlockPlayer player) {
                return ItemStackCreator.getStack("§aInformation", Material.REDSTONE_TORCH, 1,
                        "§7Keep your coins safe in the bank!",
                        "§7You lose half the coins in your purse when dying in combat.",
                        " ",
                        "§7Balance limit: §6" + StringUtility.commaify(bankData.getBalanceLimit()) + " Coins",
                        " ",
                        "§7The banker rewards you every 31",
                        "§7hours with §binterest §7for the coins in your bank balance.",
                        "§7 ",
                        "§7Interest is in: §b" + SkyBlockCalendar.getHoursUntilNextInterest() + "h"
                );
            }
        });

        set(new GUIClickableItem(11) {
            @Override
            public void run(InventoryPreClickEvent e, SkyBlockPlayer player) {
                new GUIBankerDeposit(bankData.getSessionHash()).open(player);
            }

            @Override
            public ItemStack.Builder getItem(SkyBlockPlayer player) {
                return ItemStackCreator.getStack("§aDeposit Coins", Material.CHEST, (short) 0, 1,
                        "§7Current balance: §6" + StringUtility.decimalify(bankData.getAmount(), 1),
                        " ",
                        "§7Store coins in the bank to keep",
                        "§7them safe while you go on",
                        "§7on adventures!",
                        " ",
                        "§7You will earn §b2%§7 interest every",
                        "§7season for your first §6" + "10 million",
                        "§7banked coins.",
                        " ",
                        "§7Until interest: §b" + SkyBlockCalendar.getHoursUntilNextInterest() + "h",
                        " ",
                        "§eClick to make a deposit!"
                );
            }
        });

        set(new GUIClickableItem(13) {
            @Override
            public void run(InventoryPreClickEvent e, SkyBlockPlayer player) {
                new GUIBankerWithdraw(bankData.getSessionHash()).open(player);
            }

            @Override
            public ItemStack.Builder getItem(SkyBlockPlayer player) {
                return ItemStackCreator.getStack("§aWithdraw Coins", Material.DISPENSER, (short) 0, 1,
                        "§7Current balance: §6" + StringUtility.decimalify(bankData.getAmount(), 1),
                        " ",
                        "§7Withdraw coins from the bank",
                        "§7to use them for trading or",
                        "§7other purposes!",
                        " ",
                        "§eClick to make a withdrawal!"
                );
            }
        });

        set(new GUIItem(15) {
            @Override
            public ItemStack.Builder getItem(SkyBlockPlayer player) {
                List<String> lore = new ArrayList<>();
                List<DatapointBankData.Transaction> transactions = bankData.getTransactions();

                if (transactions.isEmpty()) lore.add("§cNo transactions yet!");
                else {
                    for (int i = Math.min(transactions.size() - 1, 10); i >= 0; i--) {
                        DatapointBankData.Transaction transaction = transactions.get(i);

                        boolean isNegative = transaction.amount < 0;
                        String amount = StringUtility.decimalify(Math.abs(transaction.amount), 1);

                        lore.add("§7" + (isNegative ? "§c-" : "§a+")
                                + " §6" + amount + "§7, §e" + StringUtility.formatTimeAsAgo(transaction.timestamp)
                                + "§7 by §b" + transaction.originator);
                    }
                }

                return ItemStackCreator.getStack("§aRecent Transactions",
                        Material.FILLED_MAP, 1, lore
                );
            }
        });
    }

    @Override
    public int refreshRate() {
        return 20;
    }

    @Override
    public boolean allowHotkeying() {
        return false;
    }

    @Override
    public void onBottomClick(InventoryPreClickEvent e) {
        e.setCancelled(true);
    }
}
