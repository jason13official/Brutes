// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class villagerBrute<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "villagerbrute"), "main");
	private final ModelPart Brute;
	private final ModelPart Body;
	private final ModelPart Right Arm;
	private final ModelPart Left Arm;
	private final ModelPart Head;
	private final ModelPart Left Leg;
	private final ModelPart Right Leg;

	public villagerBrute(ModelPart root) {
		this.Brute = root.getChild("Brute");
		this.Body = this.Brute.getChild("Body");
		this.Right Arm = this.Body.getChild("Right Arm");
		this.Left Arm = this.Body.getChild("Left Arm");
		this.Head = this.Body.getChild("Head");
		this.Left Leg = this.Brute.getChild("Left Leg");
		this.Right Leg = this.Brute.getChild("Right Leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Brute = partdefinition.addOrReplaceChild("Brute", CubeListBuilder.create(), PartPose.offset(1.0F, 10.0F, -3.0F));

		PartDefinition Body = Brute.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, -21.0F, -6.0F, 20.0F, 17.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(26, 30).addBox(-7.0F, -4.0F, -4.0F, 14.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -5.0F, 3.0F));

		PartDefinition Right Arm = Body.addOrReplaceChild("Right Arm", CubeListBuilder.create().texOffs(0, 30).mirror().addBox(-6.0F, -4.0F, -3.0F, 6.0F, 32.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-10.0F, -16.0F, 0.0F));

		PartDefinition Left Arm = Body.addOrReplaceChild("Left Arm", CubeListBuilder.create().texOffs(0, 30).addBox(0.0F, -4.0F, -3.0F, 6.0F, 32.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(10.0F, -16.0F, 0.0F));

		PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(26, 44).addBox(-4.4F, -10.0F, -5.0F, 9.0F, 12.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(26, 65).addBox(-4.4F, -5.0F, -5.0F, 9.0F, 2.0F, 4.0F, new CubeDeformation(0.1F))
		.texOffs(66, 0).addBox(-2.0F, -3.0F, -8.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -21.0F, -4.0F));

		PartDefinition Left Leg = Brute.addOrReplaceChild("Left Leg", CubeListBuilder.create().texOffs(62, 44).addBox(-3.5F, 0.0F, -2.9F, 6.0F, 19.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -5.0F, 3.0F));

		PartDefinition Right Leg = Brute.addOrReplaceChild("Right Leg", CubeListBuilder.create().texOffs(62, 44).mirror().addBox(-2.6F, 0.0F, -3.9F, 6.0F, 19.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-6.0F, -5.0F, 4.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Brute.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}