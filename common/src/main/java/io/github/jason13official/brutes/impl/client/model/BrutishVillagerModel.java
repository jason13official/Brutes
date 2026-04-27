package io.github.jason13official.brutes.impl.client.model;

import io.github.jason13official.brutes.impl.common.registry.entity.BrutishVillager;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class BrutishVillagerModel extends HierarchicalModel<BrutishVillager> {

  private final ModelPart root;

  public BrutishVillagerModel(ModelPart root) {
    this.root = root;
  }

  @Override
  public ModelPart root() {
    return this.root;
  }

  @Override
  public void setupAnim(BrutishVillager brutishVillager, float v, float v1, float v2, float v3, float v4) {
    
  }
}
