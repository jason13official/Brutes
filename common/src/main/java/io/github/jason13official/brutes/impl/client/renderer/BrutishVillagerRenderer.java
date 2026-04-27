package io.github.jason13official.brutes.impl.client.renderer;

import io.github.jason13official.brutes.Brutes;
import io.github.jason13official.brutes.impl.client.model.BrutishVillagerModel;
import io.github.jason13official.brutes.impl.common.registry.entity.BrutishVillager;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BrutishVillagerRenderer extends MobRenderer<BrutishVillager, BrutishVillagerModel> {

  private static final ResourceLocation TEXTURE_LOCATION = Brutes.identifier("textures/entity/brute/villager.png");

  public BrutishVillagerRenderer(Context context) {
    super(context, new BrutishVillagerModel(context.bakeLayer(BrutishVillagerModel.LAYER_LOCATION)), 0.7f);
  }

  @Override
  public ResourceLocation getTextureLocation(BrutishVillager brutishVillager) {
    return TEXTURE_LOCATION;
  }
}
