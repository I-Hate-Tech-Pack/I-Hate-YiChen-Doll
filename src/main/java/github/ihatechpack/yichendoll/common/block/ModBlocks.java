package github.ihatechpack.yichendoll.common.block;

import github.ihatechpack.yichendoll.IHateYiChenDoll;
import github.ihatechpack.yichendoll.Res;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.*;
import java.util.function.Supplier;

/**
 * @description: TODO
 * @author: HowXu
 * @date: 2026/1/11 13:11
 */
public class ModBlocks {
    private static final DeferredRegister<Block> REGISTER = DeferredRegister.Blocks.createBlocks(IHateYiChenDoll.MOD_ID);
    public static final LinkedHashMap<String, DeferredHolder<Block,Block>> BLOCKS = new LinkedHashMap<>();

    public static void register(IEventBus bus) {
        Arrays.stream(Res.howxu).forEach(str -> {
            BLOCKS.put(str,REGISTER.register(str,() -> new Doll(str)));
        });
        Arrays.stream(Res.hualeibao).forEach(str -> {
            BLOCKS.put(str,REGISTER.register(str,() -> new Doll(str)));
        });
        Arrays.stream(Res.yichens).forEach(str -> {
            BLOCKS.put(str,REGISTER.register(str,() -> new Doll(str)));
        });
        Arrays.stream(Res.tech_guys).forEach(str -> {
            BLOCKS.put(str,REGISTER.register(str,() -> new Doll(str)));
        });
        REGISTER.register(bus);
    }
}
