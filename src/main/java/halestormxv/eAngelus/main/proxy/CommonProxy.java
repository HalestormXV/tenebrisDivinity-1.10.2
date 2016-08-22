package halestormxv.eAngelus.main.proxy;

import halestormxv.eAngelus.achievements.EA_Achievements;
import halestormxv.eAngelus.crafting.EARecipes;
import halestormxv.eAngelus.main.handlers.EA_FuelHandler;
import halestormxv.eAngelus.main.init.eAngelusArmor;
import halestormxv.eAngelus.main.init.eAngelusBlocks;
import halestormxv.eAngelus.main.init.eAngelusItems;
import halestormxv.eAngelus.main.world.E_AngWorldGen;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy 
{
	public void preInit(FMLPreInitializationEvent event)
	{
		eAngelusItems.initItems();
		eAngelusBlocks.initBlocks();
		eAngelusArmor.initArmor();
		EARecipes.initRecipes();
		EA_Achievements.AchievementRegistry();
		
	}
	
	public void init(FMLInitializationEvent event)
	{

	}
	
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
	
	public void registerItemSided(Item item)
	{
		
	}
	
	public void registerModelBakeryStuff()
	{
		
	}

}
