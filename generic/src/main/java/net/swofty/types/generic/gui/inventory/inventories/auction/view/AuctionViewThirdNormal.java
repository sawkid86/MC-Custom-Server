package net.swofty.types.generic.gui.inventory.inventories.auction.view;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.swofty.commons.ServiceType;
import net.swofty.commons.auctions.AuctionCategories;
import net.swofty.proxyapi.ProxyPlayer;
import net.swofty.proxyapi.ProxyPlayerSet;
import net.swofty.proxyapi.ProxyService;
import net.swofty.types.generic.auction.AuctionItem;
import net.swofty.types.generic.data.DataHandler;
import net.swofty.types.generic.data.datapoints.DatapointDouble;
import net.swofty.types.generic.data.datapoints.DatapointUUIDList;
import net.swofty.types.generic.data.mongodb.CoopDatabase;
import net.swofty.types.generic.gui.inventory.ItemStackCreator;
import net.swofty.types.generic.gui.inventory.SkyBlockInventoryGUI;
import net.swofty.types.generic.gui.inventory.inventories.auction.GUIAuctionViewItem;
import net.swofty.types.generic.gui.inventory.item.GUIClickableItem;
import net.swofty.types.generic.gui.inventory.item.GUIItem;
import net.swofty.types.generic.gui.inventory.item.GUIQueryItem;
import net.swofty.types.generic.item.impl.SpecificAuctionCategory;
import net.swofty.types.generic.protocol.auctions.ProtocolAddItem;
import net.swofty.types.generic.protocol.auctions.ProtocolFetchItem;
import net.swofty.types.generic.user.SkyBlockPlayer;
import net.swofty.types.generic.utility.StringUtility;
import org.json.JSONObject;

import java.util.*;

public class AuctionViewThirdNormal implements AuctionView {
    @Override
    public void open(GUIAuctionViewItem gui, AuctionItem item, SkyBlockPlayer player) {
        AuctionItem.Bid highestBid = item.getBids().stream().max(Comparator.comparingLong(AuctionItem.Bid::value)).orElse(null);
        if (highestBid == null) {
            if (gui.bidAmount == 0)
                gui.bidAmount = item.getStartingPrice();
            gui.minimumBidAmount = item.getStartingPrice();
        } else {
            if (gui.bidAmount == 0)
                gui.bidAmount = highestBid.value() + 1;
            gui.minimumBidAmount = highestBid.value() + 1;
        }

        gui.set(new GUIItem(33) {
            @Override
            public ItemStack.Builder getItem(SkyBlockPlayer player) {
                List<String> lore = new ArrayList<>();
                lore.add("§7Total bids: §a" + item.getBids().size() + " bids");

                List<AuctionItem.Bid> bids = new ArrayList<>(item.getBids());
                bids.sort(Comparator.comparingLong(AuctionItem.Bid::value).reversed());

                for (int i = 0; i < 10; i++) {
                    if (i >= bids.size())
                        break;
                    AuctionItem.Bid bid = bids.get(i);

                    lore.add("§8§m---------------");
                    lore.add("§7Bid: §6" + bid.value() + " coins");
                    lore.add("§7By: " + SkyBlockPlayer.getDisplayName(bid.uuid()));
                    lore.add("§b" + StringUtility.formatTimeAsAgo(bid.timestamp()));
                }

                return ItemStackCreator.getStack("Bid History", Material.FILLED_MAP, 1, lore);
            }
        });

        if (System.currentTimeMillis() > item.getEndTime()) {
            DatapointUUIDList activeBids = player.getDataHandler().get(DataHandler.Data.AUCTION_ACTIVE_BIDS, DatapointUUIDList.class);
            DatapointUUIDList inactiveBids = player.getDataHandler().get(DataHandler.Data.AUCTION_INACTIVE_BIDS, DatapointUUIDList.class);

            // If UUID is in here, it hasn't been claimed yet, if its not, it has been claimed,
            // goes for both coins and items
            // Check that they won the auction in the first place

            AuctionItem.Bid winningBid = item.getBids().stream().max(Comparator.comparingLong(AuctionItem.Bid::value)).orElse(null);
            AuctionItem.Bid highestBidMadeByPlayer = item.getBids().stream().filter(bid -> bid.uuid().equals(player.getUuid())).max(Comparator.comparingLong(AuctionItem.Bid::value)).orElse(null);
            if (winningBid == null || !winningBid.uuid().equals(player.getUuid())) {
                if (activeBids.getValue().contains(item.getUuid())) {
                    gui.set(new GUIClickableItem(29) {
                        @Override
                        public void run(InventoryPreClickEvent e, SkyBlockPlayer player) {
                            player.sendMessage("§8Claiming your bid coins back...");
                            DatapointDouble coins = player.getDataHandler().get(DataHandler.Data.COINS, DatapointDouble.class);
                            coins.setValue(coins.getValue() + highestBidMadeByPlayer.value());
                            activeBids.setValue(new ArrayList<>(activeBids.getValue()) {{
                                remove(item.getUuid());
                            }});
                            inactiveBids.setValue(new ArrayList<>(inactiveBids.getValue()) {{
                                add(item.getUuid());
                            }});

                            player.sendMessage("§aYou have had §6" + highestBidMadeByPlayer.value() + " coins §areturned to you.");
                            player.closeInventory();
                        }

                        @Override
                        public ItemStack.Builder getItem(SkyBlockPlayer player) {
                            return ItemStackCreator.getStack("§cAuction Ended", Material.BARRIER, 1,
                                    "§7This auction has ended.",
                                    "§7You did not win this auction.",
                                    " ",
                                    "§7You can claim your §6" + highestBidMadeByPlayer.value() + " coins §7back.",
                                    " ",
                                    "§eClick to claim coins!");
                        }
                    });
                } else {
                    gui.set(new GUIItem(29) {
                        @Override
                        public ItemStack.Builder getItem(SkyBlockPlayer player) {
                            return ItemStackCreator.getStack("§cAuction Ended", Material.BARRIER, 1,
                                    "§7This auction has ended.",
                                    "§7You did not win this auction.",
                                    " ",
                                    "§cYou either did not bid or you",
                                    "§chave claimed your coins back.");
                        }
                    });
                }
            } else {
                // Player won auction, do similar checks as above to see
                // if they have claimed their item
                if (activeBids.getValue().contains(item.getUuid())) {
                    gui.set(new GUIClickableItem(29) {
                        @Override
                        public void run(InventoryPreClickEvent e, SkyBlockPlayer player) {
                            player.sendMessage("§8Claiming your item...");
                            activeBids.setValue(new ArrayList<>(activeBids.getValue()) {{
                                remove(item.getUuid());
                            }});
                            inactiveBids.setValue(new ArrayList<>(inactiveBids.getValue()) {{
                                add(item.getUuid());
                            }});

                            player.addAndUpdateItem(item.getItem());

                            player.sendMessage("§aYou have claimed your item.");
                            player.closeInventory();
                        }

                        @Override
                        public ItemStack.Builder getItem(SkyBlockPlayer player) {
                            return ItemStackCreator.getStack("§aAuction Ended", Material.EMERALD, 1,
                                    "§7This auction has ended.",
                                    "§7You won this auction.",
                                    " ",
                                    "§7You can claim your item.",
                                    " ",
                                    "§eClick to claim item!");
                        }
                    });
                } else {
                    gui.set(new GUIItem(29) {
                        @Override
                        public ItemStack.Builder getItem(SkyBlockPlayer player) {
                            return ItemStackCreator.getStack("§aAuction Ended", Material.EMERALD, 1,
                                    "§7This auction has ended.",
                                    "§7You won this auction.",
                                    " ",
                                    "§cYou have already claimed your item.");
                        }
                    });
                }
            }
            return;
        }

        gui.set(new GUIQueryItem(31) {
            @Override
            public SkyBlockInventoryGUI onQueryFinish(String query, SkyBlockPlayer player) {
                long l;
                try {
                    l = Long.parseLong(query);
                } catch (NumberFormatException ex) {
                    player.sendMessage("§cCould not read this number!");
                    return gui;
                }
                if (l < gui.minimumBidAmount) {
                    player.sendMessage("§cYou need to bid at least §6" + gui.minimumBidAmount + " coins §cto hold the top bid on this auction.");
                    return gui;
                }

                gui.bidAmount = l;

                return gui;
            }

            @Override
            public ItemStack.Builder getItem(SkyBlockPlayer player) {
                return ItemStackCreator.getStack("Bid Amount: §6" + gui.bidAmount, Material.GOLD_INGOT, 1,
                        "§7You need to bid at least §6" + gui.minimumBidAmount + " coins §7to",
                        "§7hold the top bid on this auction.",
                        " ",
                        "§7The §etop bid §7on auction end wins the",
                        "§7item.",
                        " ",
                        "§7If you do not win, you can claim your",
                        "§7bid coins back.",
                        " ",
                        "§eClick to edit amount!");
            }
        });
        gui.set(new GUIClickableItem( 29) {
            @Override
            public void run(InventoryPreClickEvent e, SkyBlockPlayer player) {
                if (gui.bidAmount < gui.minimumBidAmount) {
                    player.sendMessage("§cYou need to bid at least §6" + gui.minimumBidAmount + " coins §cto hold the top bid on this auction.");
                    return;
                }

                DatapointDouble coins = player.getDataHandler().get(DataHandler.Data.COINS, DatapointDouble.class);
                if (coins.getValue() < gui.bidAmount) {
                    player.sendMessage("§cYou do not have enough coins to bid this amount!");
                    return;
                }

                UUID topBidder = item.getBids().stream().max(Comparator.comparingLong(AuctionItem.Bid::value)).map(AuctionItem.Bid::uuid).orElse(null);
                if (topBidder != null && topBidder.equals(player.getUuid())) {
                    player.sendMessage("§cYou already have the top bid on this auction!");
                    return;
                }

                player.sendMessage("§7Putting coins in escrow...");
                coins.setValue(coins.getValue() - gui.bidAmount);
                player.closeInventory();

                AuctionCategories category;
                if (item.getItem().getGenericInstance() != null && item.getItem().getGenericInstance() instanceof SpecificAuctionCategory instanceCategory)
                    category = instanceCategory.getAuctionCategory();
                else {
                    category = AuctionCategories.TOOLS;
                }

                CoopDatabase.Coop originatorCoop = CoopDatabase.getFromMember(item.getOriginator());
                CoopDatabase.Coop purchaserCoop = CoopDatabase.getFromMember(player.getUuid());
                if (originatorCoop != null && purchaserCoop != null && originatorCoop.isSameAs(purchaserCoop)) {
                    player.sendMessage("§cCannot purchase an item from someone in the same coop!");
                    player.sendMessage("§8Returning escrowed coins...");
                    coins.setValue(coins.getValue() + gui.bidAmount);
                    return;
                }

                player.sendMessage("§7Processing bid...");
                Thread.startVirtualThread(() -> {
                    Map<String, Object> itemResponse = new ProxyService(ServiceType.AUCTION_HOUSE).callEndpoint(
                            new ProtocolFetchItem(), new JSONObject().put("uuid", item.getUuid()).toMap()).join();
                    AuctionItem item = (AuctionItem) itemResponse.get("item");
                    AuctionItem.Bid highestBid = item.getBids().stream().max(Comparator.comparingLong(AuctionItem.Bid::value)).orElse(null);

                    if (highestBid != null && highestBid.value() >= gui.bidAmount) {
                        player.sendMessage("§cCouldn't place your bid, the highest bid has changed!");
                        player.sendMessage("§8Returning escrowed coins...");
                        coins.setValue(coins.getValue() + gui.bidAmount);
                        return;
                    }

                    if (item.getEndTime() + 5000 < System.currentTimeMillis()) {
                        player.sendMessage("§cCouldn't place your bid, the auction has ended!");
                        player.sendMessage("§8Returning escrowed coins...");
                        coins.setValue(coins.getValue() + gui.bidAmount);
                        return;
                    }

                    item.setBids(new ArrayList<>(item.getBids()) {{
                        add(new AuctionItem.Bid(System.currentTimeMillis(), player.getUuid(), gui.bidAmount));
                    }});
                    // Add two minutes on
                    item.setEndTime(item.getEndTime() + 120000);

                    Map<String, Object> request = new HashMap<>();
                    request.put("item", item);
                    request.put("category", category);
                    new ProxyService(ServiceType.AUCTION_HOUSE).callEndpoint(new ProtocolAddItem(),
                            request).join();

                    player.sendMessage("§eBid of §6" + gui.bidAmount + " coins §eplaced!");
                    new GUIAuctionViewItem(gui.auctionID, gui.previousGUI).open(player);

                    // Add auction uuid to activebids
                    DatapointUUIDList activeBids = player.getDataHandler().get(DataHandler.Data.AUCTION_ACTIVE_BIDS, DatapointUUIDList.class);
                    activeBids.setValue(new ArrayList<>(activeBids.getValue()) {{
                        add(item.getUuid());
                    }});

                    List<UUID> alertsSentOutTo = new ArrayList<>();
                    new ProxyPlayerSet(item.getBids().stream().map(AuctionItem.Bid::uuid).toList()).asProxyPlayers().forEach(proxyPlayer -> {
                        if (proxyPlayer.isOnline().join()) {
                            long playersBid = item.getBids().stream().filter(bid -> bid.uuid().equals(proxyPlayer.getUuid())).mapToLong(AuctionItem.Bid::value).max().orElse(0);

                            if (playersBid < gui.bidAmount && !alertsSentOutTo.contains(proxyPlayer.getUuid())) {
                                alertsSentOutTo.add(proxyPlayer.getUuid());
                                proxyPlayer.sendMessage(Component.text("§6[Auction] " + player.getFullDisplayName() + " §eoutbid you by §6" + (gui.bidAmount - playersBid) + " coins §efor the item §6" + item.getItem().getDisplayName() + "§e!").clickEvent(
                                        ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/ahview " + gui.auctionID)
                                ));
                            }
                        }
                    });

                    ProxyPlayer auctionOwner = new ProxyPlayer(item.getOriginator());
                    if (auctionOwner.isOnline().join()) {
                        auctionOwner.sendMessage(Component.text("§6[Auction] " + player.getFullDisplayName() + " §eplaced a bid of §6" + gui.bidAmount + " coins §eon your item §6" + item.getItem().getDisplayName() + "§e!").clickEvent(
                                ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/ahview " + gui.auctionID)
                        ));
                    }
                });
            }

            @Override
            public ItemStack.Builder getItem(SkyBlockPlayer player) {
                return ItemStackCreator.getStack("§6Submit Bid", Material.GOLD_NUGGET, 1,
                        " ",
                        "§7New Bid: §6" + gui.bidAmount + " coins",
                        " ",
                        "§eClick to bid!");
            }
        });
    }
}
