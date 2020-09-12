package bronk.ikmc;

import java.util.ArrayList;

import au.edu.federation.caliko.FabrikBone3D;
import au.edu.federation.caliko.FabrikChain3D;
import au.edu.federation.caliko.FabrikStructure3D;
import au.edu.federation.utils.Vec3f;
import net.minecraft.client.renderer.culling.ClippingHelperImpl;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class IKQuadrupedEntity extends LivingEntity {
	
	FabrikStructure3D structure = new FabrikStructure3D();
	FabrikChain3D chain = new FabrikChain3D();
	FabrikBone3D bone1 = new FabrikBone3D(new Vec3f(0,2,0), new Vec3f(0,18,0));
	FabrikBone3D bone2 = new FabrikBone3D(new Vec3f(0,18,0), new Vec3f(0,34,0));
	FabrikBone3D bone3 = new FabrikBone3D(new Vec3f(0,34,0), new Vec3f(0,61,0));
	
	public IKQuadrupedEntity(EntityType<? extends LivingEntity> type, World worldIn) {
		super(type, worldIn);
		
		chain.addBone(bone1);
		chain.addConsecutiveBone(bone2);
		chain.addConsecutiveBone(bone3);
		
		structure.addChain(chain);
	}

	@Override
	public Iterable<ItemStack> getArmorInventoryList() {
		return new ArrayList<ItemStack>();
	}

	@Override
	public ItemStack getItemStackFromSlot(EquipmentSlotType slotIn) {
		return ItemStack.EMPTY;
	}

	@Override
	public void setItemStackToSlot(EquipmentSlotType slotIn, ItemStack stack) {}

	@Override
	public HandSide getPrimaryHand() {
		return HandSide.RIGHT;
	}
	
	public static class RenderFactory implements IRenderFactory<IKQuadrupedEntity> {
		
		private static final ResourceLocation SKIN = new ResourceLocation("ikmc", "textures/entity/white.png");
		public static final RenderFactory INSTANCE = new RenderFactory();
		
		@Override
		public EntityRenderer<? super IKQuadrupedEntity> createRenderFor(EntityRendererManager manager) {
			return new InverseKinematicsTestRenderer(manager);
		}
		
		public class InverseKinematicsTestRenderer extends LivingRenderer<IKQuadrupedEntity, IKQuadrupedModel> {
			public InverseKinematicsTestRenderer(EntityRendererManager renderManagerIn) {
				super(renderManagerIn, new IKQuadrupedModel(), 1f);
			}
			
			@Override
			public boolean shouldRender(IKQuadrupedEntity livingEntityIn, ClippingHelperImpl camera, double camX, double camY, double camZ) {
				return true;
			}
			@Override
			public ResourceLocation getEntityTexture(IKQuadrupedEntity entity) {
				return SKIN;
			}
			
			@Override
			protected boolean canRenderName(IKQuadrupedEntity entity) {
				return false;
			}
		}

	}
}
