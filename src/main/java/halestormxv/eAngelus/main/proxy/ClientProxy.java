package halestormxv.eAngelus.main.proxy;

import halestormxv.eAngelus.achievements.EA_Achievements;
import halestormxv.eAngelus.crafting.EARecipes;
import halestormxv.eAngelus.items.cards.eAngelusCardsDefense;
import halestormxv.eAngelus.items.cards.eAngelusCardsOffense;
import halestormxv.eAngelus.main.init.eAngelusArmor;
import halestormxv.eAngelus.main.init.eAngelusBlocks;
import halestormxv.eAngelus.main.init.eAngelusItems;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);

	}
	
	@Override
	public void init(FMLInitializationEvent event)
	{
		super.init(event);
		eAngelusItems.registerRenders();
		eAngelusBlocks.registerRenders();
		eAngelusArmor.registerRenders();
		registerModelBakeryStuff();
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
		super.postInit(event);	
	}
	
	@Override
	public void registerModelBakeryStuff() 
	{
		for (int i = 0; i < eAngelusCardsOffense.O_cardNames.length; ++i)
		{
		ModelBakery.registerItemVariants(eAngelusItems.eaCardO, new ResourceLocation("eangel:"+eAngelusCardsOffense.O_cardNames[i]));
		}
		
		for (int i = 0; i < eAngelusCardsDefense.D_cardNames.length; ++i)
		{
		ModelBakery.registerItemVariants(eAngelusItems.eaCardD, new ResourceLocation("eangel:"+eAngelusCardsDefense.D_cardNames[i]));
		}
	}
	
    public static String stripItemPrefix(String inString) 
    {
        return inString.replaceAll("item.", "");
    }
		
}
