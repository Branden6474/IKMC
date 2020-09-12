package bronk.ikmc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.math.Vec3d;

public class InverseKinematicsTestModel extends EntityModel<InverseKinematicsTestEntity> {
	
	private final IKModelRenderer base;
	private final IKModelRenderer arm;
	private final IKModelRenderer forearm;
	private final IKModelRenderer elbow;

	public InverseKinematicsTestModel(){
        textureWidth = 16;
		textureHeight = 16;
		base = new IKModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.setTextureOffset(0, 0).addBox(-8.0F, -2.0F, -8.0F, 16.0F, 2.0F, 16.0F, 0.0F, false);

		arm = new IKModelRenderer(this);
		arm.setRotationPoint(0.0F, -2.0F, 0.0F);
		base.addChild(arm);
		arm.setTextureOffset(0, 0).addBox(-1.5F, -16.0F, -1.5F, 3.0F, 16.0F, 3.0F, 0.0F, false);
		
		elbow = new IKModelRenderer(this);
		elbow.setRotationPoint(0.0F, -16.0F, 0.0F);
		arm.addChild(elbow);
		elbow.setTextureOffset(0, 0).addBox(-1.5F, -16.0F, -1.5F, 3.0F, 16.0F, 3.0F, 0.0F, false);

		forearm = new IKModelRenderer(this,0,61-34,0);
		forearm.setRotationPoint(0.0F, -16.0F, 0.0F);
		elbow.addChild(forearm);
		forearm.setTextureOffset(0, 0).addBox(-1.5F, -23.0F, -1.5F, 3.0F, 23.0F, 3.0F, 0.0F, false);
		forearm.setTextureOffset(0, 0).addBox(-2.5F, -27.0F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, false);
	}
	
	@Override
	public void setRotationAngles(InverseKinematicsTestEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		Vec3d goal = Minecraft.getInstance().player.getPositionVec().add(0, 1, 0).subtract(entityIn.getPositionVec()).mul(16,16,16).add(0, 0, 0);
		arm.performKinematics(netHeadYaw, goal);
	}
	
	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		base.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}
}
