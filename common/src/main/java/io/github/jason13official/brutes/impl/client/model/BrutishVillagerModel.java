package io.github.jason13official.brutes.impl.client.model;

import io.github.jason13official.brutes.Brutes;
import io.github.jason13official.brutes.impl.client.animation.BrutishVillagerAnimations;
import io.github.jason13official.brutes.impl.common.registry.entity.BrutishVillager;
import net.minecraft.client.animation.definitions.SnifferAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class BrutishVillagerModel extends HierarchicalModel<BrutishVillager> {

  public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Brutes.identifier("brutish_villager"), "main");

  private final ModelPart root;
  private final ModelPart Brute;
  private final ModelPart Body;
  private final ModelPart RightArm;
  private final ModelPart LeftArm;
  private final ModelPart Head;
  private final ModelPart LeftLeg;
  private final ModelPart RightLeg;

  public BrutishVillagerModel(ModelPart root) {
    this.root = root;

    this.Brute = root.getChild("Brute");
    this.Body = this.Brute.getChild("Body");
    this.RightArm = this.Body.getChild("RightArm");
    this.LeftArm = this.Body.getChild("LeftArm");
    this.Head = this.Body.getChild("Head");
    this.LeftLeg = this.Brute.getChild("LeftLeg");
    this.RightLeg = this.Brute.getChild("RightLeg");
  }

  public static LayerDefinition createBodyLayer() {
    MeshDefinition meshdefinition = new MeshDefinition();
    PartDefinition partdefinition = meshdefinition.getRoot();

    PartDefinition Brute = partdefinition.addOrReplaceChild("Brute", CubeListBuilder.create(), PartPose.offset(1.0F, 10.0F, -3.0F));

    PartDefinition Body = Brute.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, -21.0F, -6.0F, 20.0F, 17.0F, 13.0F, new CubeDeformation(0.0F))
        .texOffs(26, 30).addBox(-7.0F, -4.0F, -4.0F, 14.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -5.0F, 3.0F));

    PartDefinition RightArm = Body.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(0, 30).mirror().addBox(-6.0F, -4.0F, -3.0F, 6.0F, 32.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-10.0F, -16.0F, 0.0F));

    PartDefinition LeftArm = Body.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(0, 30).addBox(0.0F, -4.0F, -3.0F, 6.0F, 32.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(10.0F, -16.0F, 0.0F));

    PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(26, 44).addBox(-4.4F, -10.0F, -5.0F, 9.0F, 12.0F, 9.0F, new CubeDeformation(0.0F))
        .texOffs(26, 65).addBox(-4.4F, -5.0F, -5.0F, 9.0F, 2.0F, 4.0F, new CubeDeformation(0.1F))
        .texOffs(66, 0).addBox(-2.0F, -3.0F, -8.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -21.0F, -4.0F));

    PartDefinition LeftLeg = Brute.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(62, 44).addBox(-3.5F, 0.0F, -2.9F, 6.0F, 19.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -5.0F, 3.0F));

    PartDefinition RightLeg = Brute.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(62, 44).mirror().addBox(-2.6F, 0.0F, -3.9F, 6.0F, 19.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-6.0F, -5.0F, 4.0F));

    return LayerDefinition.create(meshdefinition, 128, 128);
  }

  @Override
  public ModelPart root() {
    return this.root;
  }

  @Override
  public void setupAnim(BrutishVillager brutishVillager, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    this.root().getAllParts().forEach(ModelPart::resetPose);
    this.Head.xRot = headPitch * ((float)Math.PI / 180F);
    this.Head.yRot = netHeadYaw * ((float)Math.PI / 180F);

    this.animate(brutishVillager.idleAnimationState, BrutishVillagerAnimations.idle, ageInTicks);
  }
}
