package bronk.ikmc;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod("ikmc")
@Mod.EventBusSubscriber(modid = "ikmc", bus = Mod.EventBusSubscriber.Bus.MOD)
public class IKMC {
	
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES,"ikmc");
	
	public static final String IKTEST = "ikmc:ik_test";
	public static final RegistryObject<EntityType<InverseKinematicsTestEntity>> IKTestEntity = ENTITY_TYPES.register("ik_test", () -> EntityType.Builder
			.<InverseKinematicsTestEntity>create(InverseKinematicsTestEntity::new, EntityClassification.MISC)
			.size(1f, 1f).build(IKTEST));
	
	public static final RegistryObject<EntityType<IKQuadrupedEntity>> QuadrupedEntity = ENTITY_TYPES.register("ik_quadruped", () -> EntityType.Builder
			.<IKQuadrupedEntity>create(IKQuadrupedEntity::new, EntityClassification.MISC)
			.size(1f, 1f).build("ikmc:ik_quadruped"));
	
	
	public IKMC() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ENTITY_TYPES.register(modEventBus);
	}

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(IKTestEntity.get(), InverseKinematicsTestEntity.RenderFactory.INSTANCE);
		RenderingRegistry.registerEntityRenderingHandler(QuadrupedEntity.get(), IKQuadrupedEntity.RenderFactory.INSTANCE);
	}

}