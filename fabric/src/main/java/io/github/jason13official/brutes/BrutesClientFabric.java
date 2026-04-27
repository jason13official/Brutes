package io.github.jason13official.brutes;

import io.github.jason13official.brutes.impl.client.model.BrutishVillagerModel;
import io.github.jason13official.brutes.impl.client.renderer.BrutishVillagerRenderer;
import io.github.jason13official.brutes.impl.common.registry.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class BrutesClientFabric implements ClientModInitializer {

  @Override
  public void onInitializeClient() {

    BrutesClient.init();

    EntityModelLayerRegistry.registerModelLayer(BrutishVillagerModel.LAYER_LOCATION, BrutishVillagerModel::createBodyLayer);
    EntityRendererRegistry.register(ModEntities.BRUTISH_VILLAGER, BrutishVillagerRenderer::new);
  }
}
