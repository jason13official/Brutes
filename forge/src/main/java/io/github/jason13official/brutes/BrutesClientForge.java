package io.github.jason13official.brutes;

import io.github.jason13official.brutes.impl.client.model.BrutishVillagerModel;
import io.github.jason13official.brutes.impl.client.renderer.BrutishVillagerRenderer;
import io.github.jason13official.brutes.impl.common.registry.ModEntities;
import java.util.function.Consumer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class BrutesClientForge {

  public BrutesClientForge(final IEventBus modEventBus) {

    modEventBus.addListener((Consumer<FMLClientSetupEvent>) event -> BrutesClient.init());

    modEventBus.addListener((Consumer<EntityRenderersEvent.RegisterLayerDefinitions>) event -> {
      event.registerLayerDefinition(BrutishVillagerModel.LAYER_LOCATION, BrutishVillagerModel::createBodyLayer);
    });

    modEventBus.addListener((Consumer<EntityRenderersEvent.RegisterRenderers>) event -> {
      event.registerEntityRenderer(ModEntities.BRUTISH_VILLAGER, BrutishVillagerRenderer::new);
    });
  }
}
