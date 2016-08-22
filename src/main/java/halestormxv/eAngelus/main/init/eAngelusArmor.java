package halestormxv.eAngelus.main.init;

import halestormxv.eAngelus.items.armor.ItemAzuriteArmor;
import halestormxv.eAngelus.main.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class eAngelusArmor 
{
	public static Item AZURITE_HELM, AZURITE_CHEST, AZURITE_LEGGINGS, AZURITE_BOOTS;
	
	public static void initArmor()
	{
		AZURITE_HELM = registerItem(new ItemAzuriteArmor(1, EntityEquipmentSlot.HEAD), "azurite_helm").setUnlocalizedName("azurite_helm");
		AZURITE_CHEST = registerItem(new ItemAzuriteArmor(1, EntityEquipmentSlot.CHEST), "azurite_chest").setUnlocalizedName("azurite_chest");
		AZURITE_LEGGINGS = registerItem(new ItemAzuriteArmor(2, EntityEquipmentSlot.LEGS), "azurite_leggings").setUnlocalizedName("azurite_leggings");
		AZURITE_BOOTS = registerItem(new ItemAzuriteArmor(1, EntityEquipmentSlot.FEET), "azurite_boots").setUnlocalizedName("azurite_boots");

	}
	
	public static void registerRenders()
	{
		registerRender(AZURITE_HELM);
		registerRender(AZURITE_CHEST);
		registerRender(AZURITE_LEGGINGS);
		registerRender(AZURITE_BOOTS);
	}
	
	public static void registerRender(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item,  0, 
				new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
	
	//registerItem Start\\
	public static Item registerItem(Item item, String name)
	{
		return registerItem(item, name, null);
	}

	public static Item registerItem(Item item, String name, CreativeTabs tab)
	{
		GameRegistry.register(item, new ResourceLocation(Reference.MODID, name));
		return item;
	}
	//registerItem End\\

}
