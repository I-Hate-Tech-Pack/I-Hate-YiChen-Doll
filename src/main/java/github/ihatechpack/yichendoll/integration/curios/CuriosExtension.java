package github.ihatechpack.yichendoll.integration.curios;


import github.ihatechpack.yichendoll.Res;
import github.ihatechpack.yichendoll.api.ModEvent;
import github.ihatechpack.yichendoll.common.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.fml.ModList;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.common.NeoForgeMod;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.event.CurioChangeEvent;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.Objects;

public class CuriosExtension {
    private static final String ID = "curios";
    private static final boolean IS_LOADED;

    static {
        IS_LOADED = ModList.get().isLoaded(ID);
    }

    // 注册和调用提前使用IS_LOADED分离
    @OnlyIn(Dist.CLIENT)
    @ModEvent(side = ModEvent.Side.ClientSide)
    public static void addEntityLayers(EntityRenderersEvent.AddLayers event) {
        if (IS_LOADED) {
            CuriosCompatInner.registerRenderer(event);
        }
    }

    @ModEvent(side = ModEvent.Side.ServerSide)
    public static void onDollCurioTick(final RegisterCapabilitiesEvent event){
        if (!IS_LOADED) return;

        // 注册capability
        event.registerItem(CuriosCapability.ITEM,(stack,context)-> new ICurio() {
            @Override
            public ItemStack getStack() {
                return stack;
            }

            @Override
            public void curioTick(SlotContext slotContext) {
                if (slotContext.entity() instanceof Player player){
                    // 飞行能力 这里到时候和I Hate API接入一下
                    var fly = player.getAttribute(NeoForgeMod.CREATIVE_FLIGHT);
                    if (fly != null && !fly.hasModifier(Res.rl("flight"))) // 有这个modifier说明已经开启Doll的飞行了 就不需要额外加AttributeModifier
                        fly.addPermanentModifier(new AttributeModifier(Res.rl("flight"),1.0f, AttributeModifier.Operation.ADD_VALUE)); // >0的值意味可以飞行
                    // 夜视能力
                    keepEffect(player,MobEffects.NIGHT_VISION);
                    // 抗火能力
                    keepEffect(player,MobEffects.FIRE_RESISTANCE);
                }
            }
            @Override
            public void onUnequip(SlotContext slotContext, ItemStack newStack) {
                ICurio.super.onUnequip(slotContext, newStack);
                if (slotContext.entity() instanceof Player player){
                    var fly = player.getAttribute(NeoForgeMod.CREATIVE_FLIGHT);
                    if (fly != null) fly.removeModifier(Res.rl("flight"));
                }
            }
        },ModItems.ITEMS.get("howxu").get()); // howxu玩偶

        event.registerItem(CuriosCapability.ITEM,(stack,context)-> new ICurio() {
            @Override
            public ItemStack getStack() {
                return stack;
            }
            @Override
            public void curioTick(SlotContext slotContext) {
                if (slotContext.entity() instanceof Player player){
                    // 飞行能力 这里到时候和I Hate API接入一下
                    var fly = player.getAttribute(NeoForgeMod.CREATIVE_FLIGHT);
                    if (fly != null && !fly.hasModifier(Res.rl("flight")))
                        fly.addPermanentModifier(new AttributeModifier(Res.rl("flight"),1.0f, AttributeModifier.Operation.ADD_VALUE)); // >0的值意味可以飞行
                }
            }
            @Override
            public void onUnequip(SlotContext slotContext, ItemStack newStack) {
                ICurio.super.onUnequip(slotContext, newStack);
                if (slotContext.entity() instanceof Player player){
                    var fly = player.getAttribute(NeoForgeMod.CREATIVE_FLIGHT);
                    if (fly != null) fly.removeModifier(Res.rl("flight"));
                }
            }
        },ModItems.ITEMS.get("yichen_angel").get()); // 天使玩偶

        event.registerItem(CuriosCapability.ITEM,(stack,context)-> new ICurio() {
            @Override
            public ItemStack getStack() {
                return stack;
            }
            @Override
            public void curioTick(SlotContext slotContext) {
                if (slotContext.entity() instanceof Player player){
                    keepEffect(player,MobEffects.DAMAGE_BOOST);
                }
            }
        },ModItems.ITEMS.get("yichen_chachuqu").get()); // 叉出去玩偶
    }

    @ModEvent(side = ModEvent.Side.ServerSide)
    public static void commonSetup(FMLCommonSetupEvent event) {
        if (IS_LOADED) {
            CuriosCompatInner.registerDollItemPredicate();
        }
    }

    private static void keepEffect(Player player, Holder<MobEffect> effectHolder){
        if (!player.hasEffect(effectHolder.getDelegate()))
            player.addEffect(new MobEffectInstance(effectHolder,1200,255,true,false));
        if (Objects.requireNonNull(player.getEffect(effectHolder.getDelegate())).getDuration() < 40)
            player.addEffect(new MobEffectInstance(effectHolder,1200,255,true,false));
    }
}
