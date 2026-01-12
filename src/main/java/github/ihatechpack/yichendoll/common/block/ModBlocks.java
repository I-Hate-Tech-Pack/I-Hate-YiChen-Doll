package github.ihatechpack.yichendoll.common.block;

import github.ihatechpack.yichendoll.IHateYiChenDoll;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @description: TODO
 * @author: HowXu
 * @date: 2026/1/11 13:11
 */
public class ModBlocks {
    private static final DeferredRegister<Block> REGISTER = DeferredRegister.Blocks.createBlocks(IHateYiChenDoll.MOD_ID);
    public static final HashMap<String, DeferredHolder<Block,Block>> BLOCKS = new HashMap<>();

    public static void register(IEventBus bus, String[] dolls) {
        Arrays.stream(dolls).forEach(str -> {
            BLOCKS.put(str,REGISTER.register(str,Doll::new));
        });
        REGISTER.register(bus);
    }
}
