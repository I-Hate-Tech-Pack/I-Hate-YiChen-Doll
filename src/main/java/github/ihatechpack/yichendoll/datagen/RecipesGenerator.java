package github.ihatechpack.yichendoll.datagen;

import github.ihatechpack.yichendoll.IHateYiChenDoll;
import github.ihatechpack.yichendoll.Res;
import github.ihatechpack.yichendoll.common.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

/**
 * @description: TODO
 * @author: HowXu
 * @date: 2026/1/13 17:17
 */
public class RecipesGenerator extends RecipeProvider {
    public RecipesGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    private Item[] recipeRaws = {
            Items.IRON_INGOT,
            Items.GOLD_INGOT,
            Items.DIAMOND,
            Items.POTATO,
            Items.BAKED_POTATO,
            Items.POISONOUS_POTATO,
            Items.EGG,
            Items.CAKE,
            Items.APPLE,
            Items.GLASS_BOTTLE,
            Items.PAPER,
            Items.IRON_SWORD,
            Items.FISHING_ROD,
            Items.DIAMOND_AXE,
            Items.NETHER_BRICK,
            Items.SAND,
            Items.EMERALD,
            Items.REDSTONE,
            Items.REDSTONE_BLOCK
    };
    private int i = 0;


    @Override
    protected void buildRecipes(RecipeOutput p_recipeOutput, HolderLookup.Provider holderLookup) {
        super.buildRecipes(p_recipeOutput, holderLookup);
        ModItems.ITEMS.forEach((name,holder)->{
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, holder.get())
                    .group(IHateYiChenDoll.MOD_ID)
                    .pattern("EAE")
                    .pattern("ABA")
                    .pattern("CAC")
                    .define('E', Items.EGG)
                    .define('A', Ingredient.of(ItemTags.create(ResourceLocation.parse("minecraft:wool"))))
                    .define('B',Ingredient.of(ItemTags.create(ResourceLocation.parse("minecraft:planks"))))
                    .define('C',recipeRaws[i])
                    .unlockedBy("has_egg", has(Items.EGG))
                    .save(p_recipeOutput, Res.rl(name));;
                    i++;
        });
    }
}
