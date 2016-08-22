package halestormxv.eAngelus.main.init;

import halestormxv.eAngelus.main.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class eAngelusSoundEvents 
{
	public static SoundEvent FINISH_CARD_USE;
	public static SoundEvent FIRE_CARD_USE;
	
	public static void registerSounds()
	{
		FINISH_CARD_USE = registerSound("cardUse.ambient");
		FIRE_CARD_USE = registerSound("fireCardUse.ambient");
	}
	
	private static SoundEvent registerSound(String soundName)
	{
		final ResourceLocation soundID = new ResourceLocation(Reference.MODID, soundName);
		return GameRegistry.register(new SoundEvent(soundID).setRegistryName(soundID));
	}

}
