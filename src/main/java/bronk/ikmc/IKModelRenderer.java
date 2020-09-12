package bronk.ikmc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import au.edu.federation.caliko.FabrikBone3D;
import au.edu.federation.caliko.FabrikChain3D;
import au.edu.federation.utils.Vec3f;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.Vec3d;

public class IKModelRenderer extends ModelRenderer {

	public Vec3f endAffector = new Vec3f(0,0,0);
	public Vec3f IKAbsoluteOffset = new Vec3f(0,0,0);
	public Vec3f targetOffset = new Vec3f(0,0,0);
	public Vec3f originalUV = new Vec3f(0,0,0);
	
	private FabrikChain3D chain = null;
	
	public IKModelRenderer(Model model) {
		super(model);
	}
	
	public IKModelRenderer(Model model, float affectorX, float affectorY, float affectorZ) {
		super(model);
		endAffector = new Vec3f(affectorX,affectorY,affectorZ);
	}
	
	public FabrikChain3D getChain() {
		if(chain == null) {
			setupChain();
		}
		return chain;
	}
	
	public void setupChain() {
		chain = new FabrikChain3D();
		ObjectList<ModelRenderer> children = this.childModels;
		if(children.size() > 0) {
			chain.addBone(new FabrikBone3D(new Vec3f(0,0,0), new Vec3f(children.get(0).rotationPointX,children.get(0).rotationPointY,children.get(0).rotationPointZ)));
			for(ModelRenderer x:this.childModels) {
				IKModelRenderer child = (IKModelRenderer) x;
				child.setupChain(chain);
			}
		}else {
			chain.addBone(new FabrikBone3D(new Vec3f(0,0,0), endAffector));
		}
		targetOffset = new Vec3f(rotationPointX, rotationPointY, rotationPointZ);
		originalUV = chain.getBone(0).getDirectionUV();
	}
	
	public void setupChain(FabrikChain3D chain) {
		ObjectList<ModelRenderer> children = this.childModels;
		if(children.size() > 0) {
			FabrikBone3D previousBone = chain.getBone(chain.getNumBones()-1);
			chain.addConsecutiveBone(new FabrikBone3D(previousBone.getEndLocation(),previousBone.getEndLocation().plus(new Vec3f(children.get(0).rotationPointX,children.get(0).rotationPointY,children.get(0).rotationPointZ))));
			for(ModelRenderer x:this.childModels) {
				IKModelRenderer child = (IKModelRenderer) x;
				child.setupChain(chain);
			}
		}else {
			FabrikBone3D previousBone = chain.getBone(chain.getNumBones()-1);
			FabrikBone3D endBone = new FabrikBone3D(previousBone.getEndLocation(), previousBone.getEndLocation().plus(endAffector));
			chain.addConsecutiveBone(endBone);
		}
		targetOffset = new Vec3f(rotationPointX, rotationPointY, rotationPointZ);
		originalUV = chain.getBone(0).getDirectionUV();
	}

	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		if (this.showModel) {
			if (!this.cubeList.isEmpty() || !this.childModels.isEmpty()) {
				this.translateRotate(matrixStackIn);
				this.doRender(matrixStackIn.getLast(), bufferIn, packedLightIn, packedOverlayIn, red, green, blue,
						alpha);
				matrixStackIn.pop();
				for (ModelRenderer modelrenderer : this.childModels) {
					modelrenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue,
							alpha); 
				}
			}
		}
	}
	
	@Override
	public void translateRotate(MatrixStack matrixStackIn) {
		matrixStackIn.translate(((rotationPointX) / 16F), ((rotationPointY) / 16F), ((rotationPointZ) / 16F));
		
		matrixStackIn.push();
		matrixStackIn.translate(this.IKAbsoluteOffset.x/16, -this.IKAbsoluteOffset.y/16, -this.IKAbsoluteOffset.z/16);
		if (this.rotateAngleY != 0.0F) {
			matrixStackIn.rotate(Vector3f.YP.rotation(this.rotateAngleY));
		}

		if (this.rotateAngleX != 0.0F) {
			matrixStackIn.rotate(Vector3f.XP.rotation(this.rotateAngleX));
		}
		
		if (this.rotateAngleZ != 0.0F) {
			matrixStackIn.rotate(Vector3f.ZP.rotation(this.rotateAngleZ));
		}	
	}
	
	//input goal is in relative entity coordinates
	public void performKinematics(float netHeadYaw, Vec3d goal) {
		float yawOffset = (float) (netHeadYaw*(Math.PI/180));
		Vec3f target = new Vec3f((float) (goal.x*Math.cos(yawOffset)-goal.z*Math.sin(yawOffset)), (float) goal.y, (float) (goal.x*Math.sin(yawOffset)+goal.z*Math.cos(yawOffset)));
		//Vec3f target = targetAdjusted.minus(new Vec3f(this.targetOffset.x,this.targetOffset.y,-this.targetOffset.z));
		
		this.getChain().solveForTarget(target);
		
		applyKinematics();
	}
	
	private void applyKinematics() {
		IKModelRenderer currentModel = this;
		Vec3f UV = currentModel.originalUV;
		float angularOffsetX = (float) ((UV.getGlobalYawDegs()*Math.PI/180)-Math.PI/2);
		Vec3f offset = new Vec3f(0,0,0);
		int count = 0;

		while(true) {
			Vec3f Startlocation = Vec3f.clone(this.getChain().getBone(count).getStartLocation());
			UV = this.getChain().getBone(count).getDirectionUV();
			
	 		currentModel.rotateAngleX = (float) Math.atan2(Math.sqrt(UV.x*UV.x+UV.z*UV.z), UV.y)+angularOffsetX;
	 		currentModel.rotateAngleY = (float) (Math.atan2(UV.z,UV.x))-(float) Math.PI/2;
	 		currentModel.rotateAngleZ = 0;
	 		currentModel.IKAbsoluteOffset = new Vec3f(Startlocation).plus(offset);
			
			
			if(currentModel.childModels.size() == 0) {
				break;
			}
			currentModel = (IKModelRenderer) currentModel.childModels.get(0);
			offset = offset.plus(currentModel.targetOffset);
			count++;
		}
	}
}
