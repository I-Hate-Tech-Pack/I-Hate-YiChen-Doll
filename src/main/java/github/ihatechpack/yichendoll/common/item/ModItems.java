package github.ihatechpack.yichendoll.common.item;


import github.ihatechpack.yichendoll.IHateYiChenDoll;
import github.ihatechpack.yichendoll.common.block.ModBlocks;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @description: TODO
 * @author: HowXu
 * @date: 2026/1/11 13:26
 */
public class ModItems {

    private static final DeferredRegister<Item> REGISTER = DeferredRegister.Items.createItems(IHateYiChenDoll.MOD_ID);
    public static final HashMap<String, DeferredHolder<Item, Item>> ITEMS = new HashMap<>();

    public static void register(IEventBus bus, String[] dolls) {
        Arrays.stream(dolls).forEach(str -> {
            ITEMS.put(str, REGISTER.register(str, () -> new DollItem(ModBlocks.BLOCKS.get(str).get())));
        });
        REGISTER.register(bus);
    }
}
