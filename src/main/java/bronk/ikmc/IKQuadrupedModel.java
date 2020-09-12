package bronk.ikmc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.Vec3d;

public class IKQuadrupedModel extends EntityModel<IKQuadrupedEntity> {
	/*
	private final IKModelRenderer base;
	private final IKModelRenderer arm;
	private final IKModelRenderer forearm;
	private final IKModelRenderer elbow;
	*/

	private final ModelRenderer Torso;
	private final ModelRenderer Head;
	private final ModelRenderer Jaw;
	private final IKModelRenderer FRLeg;
	private final IKModelRenderer FRShin;
	private final IKModelRenderer FRFoot;
	private final IKModelRenderer FRLeg3;
	private final IKModelRenderer FRShin3;
	private final IKModelRenderer FRFoot3;
	private final IKModelRenderer FRLeg6;
	private final IKModelRenderer FRShin6;
	private final IKModelRenderer FRFoot6;
	private final IKModelRenderer FRLeg4;
	private final IKModelRenderer FRShin4;
	private final IKModelRenderer FRFoot4;
	private final IKModelRenderer FRLeg5;
	private final IKModelRenderer FRShin5;
	private final IKModelRenderer FRFoot5;
	private final IKModelRenderer FRLeg2;
	private final IKModelRenderer FRShin2;
	private final IKModelRenderer FRFoot2;
	/*
	private final ModelRenderer base;
	private final IKModelRenderer north;
	private final IKModelRenderer north2;
	private final IKModelRenderer north3;
	private final IKModelRenderer south;
	private final IKModelRenderer south2;
	private final IKModelRenderer south3;
	private final IKModelRenderer west;
	private final IKModelRenderer west2;
	private final IKModelRenderer west3;
	private final IKModelRenderer east;
	private final IKModelRenderer east2;
	private final IKModelRenderer east3;
	*/
	public IKQuadrupedModel() {
		/*
		textureWidth = 16;
		textureHeight = 16;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 4.6F, 0.0F);
		base.setTextureOffset(0, 0).addBox(-4.5F, -4.6F, -4.5F, 9.0F, 24.0F, 9.0F, 0.0F, false);

		north = new IKModelRenderer(this);
		north.setRotationPoint(0.0F, 0.4F, -4.5F);
		base.addChild(north);
		north.setTextureOffset(0, 0).addBox(-1.5F, -2.0F, -13.0F, 3.0F, 4.0F, 13.0F, 0.0F, false);

		north2 = new IKModelRenderer(this);
		north2.setRotationPoint(0.0F, 0.0F, -13.0F);
		north.addChild(north2);
		north2.setTextureOffset(0, 0).addBox(-1.5F, -2.0F, -13.0F, 3.0F, 4.0F, 13.0F, 0.0F, false);

		north3 = new IKModelRenderer(this,0,0,-13);
		north3.setRotationPoint(0.0F, 0.0F, -13.0F);
		north2.addChild(north3);
		north3.setTextureOffset(0, 0).addBox(-1.5F, -2.0F, -13.0F, 3.0F, 4.0F, 13.0F, 0.0F, false);

		south = new IKModelRenderer(this);
		south.setRotationPoint(0.0F, 0.4F, 4.5F);
		base.addChild(south);
		south.setTextureOffset(0, 0).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 4.0F, 13.0F, 0.0F, false);

		south2 = new IKModelRenderer(this);
		south2.setRotationPoint(0.0F, 0.0F, 12.5F);
		south.addChild(south2);
		south2.setTextureOffset(0, 0).addBox(-1.5F, -2.0F, 0.5F, 3.0F, 4.0F, 13.0F, 0.0F, false);

		south3 = new IKModelRenderer(this,0,0,13);
		south3.setRotationPoint(0.0F, 0.0F, 13.5F);
		south2.addChild(south3);
		south3.setTextureOffset(0, 0).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 4.0F, 13.0F, 0.0F, false);

		west = new IKModelRenderer(this);
		west.setRotationPoint(4.5F, 0.4F, 0.0F);
		base.addChild(west);
		//setRotationAngle(west, 0.0F, -1.5708F, 0.0F);
		west.setTextureOffset(0, 0).addBox(-1.5F, -2.0F, -13.0F, 3.0F, 4.0F, 13.0F, 0.0F, false);

		west2 = new IKModelRenderer(this);
		west2.setRotationPoint(0.0F, 0.0F, -13.0F);
		west.addChild(west2);
		west2.setTextureOffset(0, 0).addBox(-1.5F, -2.0F, -13.0F, 3.0F, 4.0F, 13.0F, 0.0F, false);

		west3 = new IKModelRenderer(this,0,0,-13);
		west3.setRotationPoint(0.0F, 0.0F, -13.0F);
		west2.addChild(west3);
		west3.setTextureOffset(0, 0).addBox(-1.5F, -2.0F, -13.0F, 3.0F, 4.0F, 13.0F, 0.0F, false);

		east = new IKModelRenderer(this);
		east.setRotationPoint(-4.5F, 0.4F, 0.0F);
		base.addChild(east);
		//setRotationAngle(east, 0.0F, -1.5708F, 0.0F);
		east.setTextureOffset(0, 0).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 4.0F, 13.0F, 0.0F, false);

		east2 = new IKModelRenderer(this);
		east2.setRotationPoint(0.0F, 0.0F, 13.0F);
		east.addChild(east2);
		east2.setTextureOffset(0, 0).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 4.0F, 13.0F, 0.0F, false);

		east3 = new IKModelRenderer(this,0,0,13);
		east3.setRotationPoint(0.0F, 0.0F, 13.0F);
		east2.addChild(east3);
		east3.setTextureOffset(0, 0).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 4.0F, 13.0F, 0.0F, false);
		*/

		textureWidth = 128;
		textureHeight = 128;

		Torso = new ModelRenderer(this);
		Torso.setRotationPoint(0.0F, 24.0F, 0.0F);
		Torso.setTextureOffset(0, 0).addBox(-6.0F, -28.0F, -10.0F, 12.0F, 9.0F, 23.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, -23.0F, -10.0F);
		Torso.addChild(Head);
		Head.setTextureOffset(25, 32).addBox(-4.0F, -3.0F, -7.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		Head.setTextureOffset(12, 18).addBox(-1.0F, -3.0F, -7.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		Head.setTextureOffset(10, 14).addBox(2.0F, -3.0F, -7.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		Head.setTextureOffset(0, 32).addBox(-5.0F, -4.0F, -5.0F, 10.0F, 5.0F, 5.0F, 0.0F, false);

		Jaw = new ModelRenderer(this);
		Jaw.setRotationPoint(0.0F, 2.4F, 0.0F);
		Head.addChild(Jaw);
		Jaw.setTextureOffset(30, 32).addBox(-5.0F, -0.4F, -5.0F, 10.0F, 1.0F, 5.0F, 0.0F, false);

		FRLeg = new IKModelRenderer(this);
		FRLeg.setRotationPoint(-6.0F, -20.0F, -7.5F);
		Torso.addChild(FRLeg);
		setRotationAngle(FRLeg, 0.0F, 0.0F, 0.7418F);
		FRLeg.setTextureOffset(18, 55).addBox(-1.75F, -2.0F, -1.0F, 3.0F, 11.0F, 2.0F, 0.1F, false);

		FRShin = new IKModelRenderer(this);
		FRShin.setRotationPoint(-0.3604F, 9.0265F, 0.0F);
		FRLeg.addChild(FRShin);
		setRotationAngle(FRShin, 0.0F, 0.0F, -0.4363F);
		FRShin.setTextureOffset(10, 42).addBox(-1.3816F, -0.5961F, -1.0F, 3.0F, 12.0F, 2.0F, 0.0F, false);

		FRFoot = new IKModelRenderer(this,0,2,0);
		FRFoot.setRotationPoint(0.2F, 12.4F, -0.5F);
		FRShin.addChild(FRFoot);
		FRFoot.setTextureOffset(57, 0).addBox(-1.6F, -1.0F, -1.5F, 3.0F, 2.0F, 4.0F, 0.0F, false);

		FRLeg3 = new IKModelRenderer(this);
		FRLeg3.setRotationPoint(-6.0F, -20.0F, 1.5F);
		Torso.addChild(FRLeg3);
		setRotationAngle(FRLeg3, 0.0F, 0.0F, 0.7418F);
		FRLeg3.setTextureOffset(30, 52).addBox(-1.75F, -2.0F, -1.0F, 3.0F, 11.0F, 2.0F, 0.1F, false);

		FRShin3 = new IKModelRenderer(this);
		FRShin3.setRotationPoint(-0.3604F, 9.0265F, 0.0F);
		FRLeg3.addChild(FRShin3);
		setRotationAngle(FRShin3, 0.0F, 0.0F, -0.4363F);
		FRShin3.setTextureOffset(40, 40).addBox(-1.3816F, -0.5961F, -1.0F, 3.0F, 12.0F, 2.0F, 0.0F, false);

		FRFoot3 = new IKModelRenderer(this,0,2,0);
		FRFoot3.setRotationPoint(0.2F, 12.4F, -0.5F);
		FRShin3.addChild(FRFoot3);
		FRFoot3.setTextureOffset(50, 44).addBox(-1.6F, -1.0F, -1.5F, 3.0F, 2.0F, 4.0F, 0.0F, false);

		FRLeg6 = new IKModelRenderer(this);
		FRLeg6.setRotationPoint(6.0F, -20.0F, 1.5F);
		Torso.addChild(FRLeg6);
		setRotationAngle(FRLeg6, 0.0F, 0.0F, -0.7418F);
		FRLeg6.setTextureOffset(20, 42).addBox(-1.25F, -2.0F, -1.0F, 3.0F, 11.0F, 2.0F, 0.1F, false);

		FRShin6 = new IKModelRenderer(this);
		FRShin6.setRotationPoint(0.3604F, 9.0265F, 0.0F);
		FRLeg6.addChild(FRShin6);
		setRotationAngle(FRShin6, 0.0F, 0.0F, 0.4363F);
		FRShin6.setTextureOffset(0, 0).addBox(-1.6184F, -0.5961F, -1.0F, 3.0F, 12.0F, 2.0F, 0.0F, false);

		FRFoot6 = new IKModelRenderer(this,0,2,0);
		FRFoot6.setRotationPoint(-0.2F, 12.4F, -0.5F);
		FRShin6.addChild(FRFoot6);
		FRFoot6.setTextureOffset(0, 14).addBox(-1.4F, -1.0F, -1.5F, 3.0F, 2.0F, 4.0F, 0.0F, false);

		FRLeg4 = new IKModelRenderer(this);
		FRLeg4.setRotationPoint(-6.0F, -20.0F, 10.5F);
		Torso.addChild(FRLeg4);
		setRotationAngle(FRLeg4, 0.0F, 0.0F, 0.7418F);
		FRLeg4.setTextureOffset(50, 50).addBox(-1.75F, -2.0F, -1.0F, 3.0F, 11.0F, 2.0F, 0.1F, false);

		FRShin4 = new IKModelRenderer(this);
		FRShin4.setRotationPoint(-0.3604F, 9.0265F, 0.0F);
		FRLeg4.addChild(FRShin4);
		setRotationAngle(FRShin4, 0.0F, 0.0F, -0.4363F);
		FRShin4.setTextureOffset(30, 38).addBox(-1.3816F, -0.5961F, -1.0F, 3.0F, 12.0F, 2.0F, 0.0F, false);

		FRFoot4 = new IKModelRenderer(this,0,2,0);
		FRFoot4.setRotationPoint(0.2F, 12.4F, -0.5F);
		FRShin4.addChild(FRFoot4);
		FRFoot4.setTextureOffset(50, 38).addBox(-1.6F, -1.0F, -1.5F, 3.0F, 2.0F, 4.0F, 0.0F, false);

		FRLeg5 = new IKModelRenderer(this);
		FRLeg5.setRotationPoint(6.0F, -20.0F, 10.5F);
		Torso.addChild(FRLeg5);
		setRotationAngle(FRLeg5, 0.0F, 0.0F, -0.7418F);
		FRLeg5.setTextureOffset(47, 0).addBox(-1.25F, -2.0F, -1.0F, 3.0F, 11.0F, 2.0F, 0.1F, false);

		FRShin5 = new IKModelRenderer(this);
		FRShin5.setRotationPoint(0.3604F, 9.0265F, 0.0F);
		FRLeg5.addChild(FRShin5);
		setRotationAngle(FRShin5, 0.0F, 0.0F, 0.4363F);
		FRShin5.setTextureOffset(10, 0).addBox(-1.6184F, -0.5961F, -1.0F, 3.0F, 12.0F, 2.0F, 0.0F, false);

		FRFoot5 = new IKModelRenderer(this,0,2,0);
		FRFoot5.setRotationPoint(-0.2F, 12.4F, -0.5F);
		FRShin5.addChild(FRFoot5);
		FRFoot5.setTextureOffset(47, 13).addBox(-1.4F, -1.0F, -1.5F, 3.0F, 2.0F, 4.0F, 0.0F, false);

		FRLeg2 = new IKModelRenderer(this);
		FRLeg2.setRotationPoint(6.0F, -20.0F, -7.5F);
		Torso.addChild(FRLeg2);
		setRotationAngle(FRLeg2, 0.0F, 0.0F, -0.7418F);
		FRLeg2.setTextureOffset(40, 54).addBox(-1.25F, -2.0F, -1.0F, 3.0F, 11.0F, 2.0F, 0.1F, false);

		FRShin2 = new IKModelRenderer(this);
		FRShin2.setRotationPoint(0.3604F, 9.0265F, 0.0F);
		FRLeg2.addChild(FRShin2);
		setRotationAngle(FRShin2, 0.0F, 0.0F, 0.4363F);
		FRShin2.setTextureOffset(0, 42).addBox(-1.6184F, -0.5961F, -1.0F, 3.0F, 12.0F, 2.0F, 0.0F, false);

		FRFoot2 = new IKModelRenderer(this,0,2,0);
		FRFoot2.setRotationPoint(-0.2F, 12.4F, -0.5F);
		FRShin2.addChild(FRFoot2);
		FRFoot2.setTextureOffset(0, 56).addBox(-1.4F, -1.0F, -1.5F, 3.0F, 2.0F, 4.0F, 0.0F, false);

		/*
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

		forearm = new IKModelRenderer(this,0,27,0);
		forearm.setRotationPoint(0.0F, -16.0F, 0.0F);
		elbow.addChild(forearm);
		forearm.setTextureOffset(0, 0).addBox(-1.5F, -23.0F, -1.5F, 3.0F, 23.0F, 3.0F, 0.0F, false);
		forearm.setTextureOffset(0, 0).addBox(-2.5F, -27.0F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, false);
		*/
	}

	@Override
	public void setRotationAngles(IKQuadrupedEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,float netHeadYaw, float headPitch) {
		
		Vec3d goal = Minecraft.getInstance().player.getPositionVec().add(0, 1, 0).subtract(entityIn.getPositionVec()).mul(16,16,16).subtract(0, 40, 0);
		FRLeg.performKinematics(netHeadYaw, new Vec3d(-5,-20,10));
		FRLeg2.performKinematics(netHeadYaw, new Vec3d(-5,-20,10));
		FRLeg3.performKinematics(netHeadYaw, new Vec3d(-5,-20,10));
		FRLeg4.performKinematics(netHeadYaw, new Vec3d(-5,-20,10));
		FRLeg5.performKinematics(netHeadYaw, new Vec3d(-5,-20,10));
		FRLeg6.performKinematics(netHeadYaw, new Vec3d(-5,-20,10));
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,float red, float green, float blue, float alpha) {
		Torso.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}
	
	public void setRotationAngle(IKModelRenderer IKModelRenderer, float x, float y, float z) {
		IKModelRenderer.rotateAngleX = x;
		IKModelRenderer.rotateAngleY = y;
		IKModelRenderer.rotateAngleZ = z;
	}
	
	public void setRotationAngle(ModelRenderer IKModelRenderer, float x, float y, float z) {
		IKModelRenderer.rotateAngleX = x;
		IKModelRenderer.rotateAngleY = y;
		IKModelRenderer.rotateAngleZ = z;
	}
}