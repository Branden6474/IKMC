package bronk.ikmc;

import java.util.ArrayList;

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

public class InverseKinematicsTestEntity extends LivingEntity {
	
	public InverseKinematicsTestEntity(EntityType<? extends LivingEntity> type, World worldIn) {
		super(type, worldIn);
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
	
	public static class RenderFactory implements IRenderFactory<InverseKinematicsTestEntity> {
		
		private static final ResourceLocation SKIN = new ResourceLocation("ikmc", "textures/entity/white.png");
		
		public static final RenderFactory INSTANCE = new RenderFactory();
		
		@Override
		public EntityRenderer<? super InverseKinematicsTestEntity> createRenderFor(EntityRendererManager manager) {
			return new InverseKinematicsTestRenderer(manager);
		}
		
		public class InverseKinematicsTestRenderer extends LivingRenderer<InverseKinematicsTestEntity, InverseKinematicsTestModel> {

			public InverseKinematicsTestRenderer(EntityRendererManager renderManagerIn) {
				super(renderManagerIn, new InverseKinematicsTestModel(), 1f);
			}
			
			@Override
			public boolean shouldRender(InverseKinematicsTestEntity livingEntityIn, ClippingHelperImpl camera, double camX,
					double camY, double camZ) {
				return true;
			}
			@Override
			public ResourceLocation getEntityTexture(InverseKinematicsTestEntity entity) {
				return SKIN;
			}
			
			@Override
			protected boolean canRenderName(InverseKinematicsTestEntity entity) {
				return false;
			}
			
		}

	}

}
