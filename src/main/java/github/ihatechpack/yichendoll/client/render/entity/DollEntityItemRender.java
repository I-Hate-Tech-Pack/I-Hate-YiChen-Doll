package github.ihatechpack.yichendoll.client.render.entity;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import github.ihatechpack.yichendoll.Res;
import github.ihatechpack.yichendoll.common.entity.DollEntity;
import github.ihatechpack.yichendoll.common.item.DollEntityItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.joml.Matrix4f;

import java.util.concurrent.TimeUnit;

public class DollEntityItemRender extends BlockEntityWithoutLevelRenderer {
    private final Cache<ItemStack, ItemStack> dollCache = CacheBuilder.newBuilder().expireAfterAccess(30, TimeUnit.SECONDS).build();
    private final ResourceLocation BG =  Res.rl("textures/item/doll_entity_item_bg.png");

    public DollEntityItemRender(BlockEntityRenderDispatcher dispatcher, EntityModelSet modelSet) {
        super(dispatcher, modelSet);
    }

    @Override
    public void renderByItem(ItemStack itemStackIn, ItemDisplayContext transformType, PoseStack poseStack,
                             MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        Level world = Minecraft.getInstance().level;
        if (world == null) {
            return;
        }

        // 从物品获取玩偶实体
        ItemStack dollShowItem = dollCache.getIfPresent(itemStackIn);
        if (dollShowItem == null) {
            DollEntity entity = DollEntityItem.getDollEntity(world, itemStackIn);
            Block displayBlock = entity.getDisplayBlockState().getBlock();
            dollShowItem = new ItemStack(displayBlock);
            dollCache.put(itemStackIn, dollShowItem);
        }

        // GUI 内渲染背景，从而方便区分实体版玩偶和方块版玩偶
        if (transformType == ItemDisplayContext.GUI) {
            poseStack.pushPose();
            poseStack.translate(0.5, 0.5, 0);

            // 绑定背景纹理并渲染
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, BG);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();

            RenderBuffers renderBuffers = Minecraft.getInstance().renderBuffers();
            RenderType renderType = RenderType.gui(); // 或 RenderType.text(textureLocation)

            VertexConsumer bufferBuilder = renderBuffers.bufferSource().getBuffer(renderType);

            // 创建矩阵并渲染背景四边形
            Matrix4f matrix = poseStack.last().pose();
            // bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);

            float size = 0.5f;
            bufferBuilder.addVertex(matrix, -size, -size, -0.001f).setUv(0, 1);//.endVertex();
            bufferBuilder.addVertex(matrix, size, -size, -0.001f).setUv(1, 1);//.endVertex();
            bufferBuilder.addVertex(matrix, size, size, -0.001f).setUv(1, 0);//.endVertex();
            bufferBuilder.addVertex(matrix, -size, size, -0.001f).setUv(0, 0);//.endVertex();

            renderBuffers.bufferSource().endBatch(renderType);
            //BufferUploader.drawWithShader(bufferBuilder.end());
            RenderSystem.disableBlend();

            poseStack.popPose();
        }

        // 渲染物品
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        poseStack.pushPose();
        if (transformType == ItemDisplayContext.GUI) {
            poseStack.scale(0.75f, 0.75f, 0.75f);
        }
        poseStack.translate(0.5, 0.5, 0.5);
        itemRenderer.renderStatic(dollShowItem, transformType, combinedLight, combinedOverlay, poseStack, bufferSource, world, 0);
        poseStack.popPose();
    }
}
