package github.ihatechpack.yichendoll.datagen;

import github.ihatechpack.yichendoll.IHateYiChenDoll;
import github.ihatechpack.yichendoll.common.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BlockStateGenerator extends BlockStateProvider {
    public BlockStateGenerator(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, IHateYiChenDoll.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ModBlocks.BLOCKS.forEach((str,block)->{
            registerSpecialBlock(block.get(),"block/doll/");
        });

    }

    public void registerSpecialBlock(Block block,String specialLoc){
        var key = BuiltInRegistries.BLOCK.getKey(block);
        var location = modLoc(specialLoc + key.getPath());
        horizontalBlock(block, models().getExistingFile(location));
    }
}
