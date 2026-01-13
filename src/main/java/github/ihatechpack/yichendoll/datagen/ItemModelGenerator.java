package github.ihatechpack.yichendoll.datagen;

import github.ihatechpack.yichendoll.IHateYiChenDoll;
import github.ihatechpack.yichendoll.common.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;


public class ItemModelGenerator extends ItemModelProvider {
    public ItemModelGenerator(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, IHateYiChenDoll.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ModItems.ITEMS.forEach((str,item)->{
            registerSpecialBlockItem(item.get(),"block/");
        });
    }

    public void registerSpecialBlockItem(Item item,String specialLoc){
        var key = BuiltInRegistries.ITEM.getKey(item);
        withExistingParent(key.getPath(), modLoc(specialLoc + key.getPath()));
    }
}
