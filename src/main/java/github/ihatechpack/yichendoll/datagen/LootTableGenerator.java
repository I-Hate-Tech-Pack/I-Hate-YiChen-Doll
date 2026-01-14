package github.ihatechpack.yichendoll.datagen;

import github.ihatechpack.yichendoll.common.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * @description: TODO
 * @author: HowXu
 * @date: 2026/1/14 20:11
 */
public class LootTableGenerator extends LootTableProvider {

    public LootTableGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Set.of(), List.of(
                new SubProviderEntry(BlockLootGenerator::new, LootContextParamSets.BLOCK)
        ), registries);
    }
}

class BlockLootGenerator extends BlockLootSubProvider {

    private final Set<Block> known = new HashSet<>();

    protected BlockLootGenerator(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        ModBlocks.BLOCKS.forEach((name, holder) -> {
            this.dropSelf(holder.get());
            known.add(holder.get());
        });
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return known.stream().toList();
    }
}
