package halestormxv.eAngelus.main.handlers;

import halestormxv.eAngelus.main.init.eAngelusArmor;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class EAngelus_ElytraReplicate 
{
	@SubscribeEvent 
	public void onPlayerTick(PlayerTickEvent event)
	{
		if (event.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == eAngelusArmor.AZURITE_CHEST)
		{
			boolean ElyFly = event.player.isElytraFlying();
			ElyFly = true; 
		}		
	}
}
