package halestormxv.eAngelus.main.init;

import halestormxv.eAngelus.items.AngelicIngot;
import halestormxv.eAngelus.items.EAItem;
import halestormxv.eAngelus.items.ModItemAxe;
import halestormxv.eAngelus.items.ModItemHoe;
import halestormxv.eAngelus.items.ModItemMulti;
import halestormxv.eAngelus.items.ModItemPick;
import halestormxv.eAngelus.items.ModItemSpade;
import halestormxv.eAngelus.items.ModItemSword;
import halestormxv.eAngelus.items.cards.eAngelusCardsDefense;
import halestormxv.eAngelus.items.cards.eAngelusCardsOffense;
import halestormxv.eAngelus.main.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class eAngelusItems 
{
	static int[] AzuriteReductionAmounts = new int[]{2,7,4,1}; //Helmet, Chest, Legs, Boots
	
	public static Item angelic_ingot;
	public static Item demonic_ingot;
	public static Item topazStone;
	public static Item angelicDust;
	public static Item mystalDust;
	public static Item azuriteStone;
	public static Item serpentineStone;
	public static Item displacementDust;

	//Tools
	public static Item serpentinePick;
	public static Item serpentineSpade;
	public static Item serpentineAxe;
	public static Item serpentineHoe;
	public static Item serpentineMulti;

	//Weapons
	public static Item serpentineSword;
	public static Item serpentineShield;

	//Cards
	public static Item eaCardO;
	public static Item eaCardD;


	//Materials
	public static ToolMaterial Serpentine = EnumHelper.addToolMaterial("Serpentine", 4, 1800, 10.0F, 9.0F, 25);
	public static ArmorMaterial Azurite = EnumHelper.addArmorMaterial("AZURITE", Reference.MODID + ":azurite", 23, AzuriteReductionAmounts, 19, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0);
	
	public static void initItems() //illustrates both ways to register an item.
	{	
		//Cards
		eaCardO = registerItem(new eAngelusCardsOffense("eaCardO"), "eaCardO");
		eaCardD = registerItem(new eAngelusCardsDefense("eaCardD"), "eaCardD");

		//Ingots
		angelic_ingot = registerItem(new AngelicIngot(), "angelic_ingot");
		demonic_ingot = registerItem(new Item().setUnlocalizedName("demonic_ingot").setCreativeTab(Reference.eaCreativeTab), "demonic_ingot");
		//Gems
		topazStone = registerItem(new EAItem("topazStone"), "topazStone");
		azuriteStone = registerItem(new EAItem("azuriteStone"), "azuriteStone");
		serpentineStone = registerItem(new EAItem("serpentineStone"), "serpentineStone");

		//Dusts
		angelicDust = registerItem(new EAItem("angelicDust"), "angelicDust");
		mystalDust = registerItem(new EAItem("mystalDust"), "mystalDust");
		displacementDust = registerItem(new EAItem("displacementDust"), "displacementDust");

		//Tools
		serpentineHoe = registerItem(new ModItemHoe("serpentineHoe", Serpentine), "serpentineHoe");
		serpentineSpade = registerItem(new ModItemSpade("serpentineSpade", Serpentine), "serpentineSpade");
		serpentineAxe = registerItem(new ModItemAxe("serpentineAxe", Serpentine), "serpentineAxe");
		serpentinePick = registerItem(new ModItemPick("serpentinePick", Serpentine), "serpentinePick");
		serpentineMulti = registerItem(new ModItemMulti("serpentineMulti", Serpentine), "serpentineMulti");

		//Weapons
		serpentineSword = registerItem(new ModItemSword("serpentineSword", Serpentine), "serpentineSword");
		//serpentineShield = registerItem(new SerpentSword("serpentinePick", Serpentine), "serpentinePick");
	}


	public static void registerRenders()
	{
		//Cards
		for (int i = 0; i < eAngelusCardsOffense.O_cardNames.length; ++i)
		{
			registerRender(eaCardO, i, eAngelusCardsOffense.O_cardNames[i]);
		}
		
		for (int i = 0; i < eAngelusCardsDefense.D_cardNames.length; ++i)
		{
			registerRender(eaCardD, i, eAngelusCardsDefense.D_cardNames[i]);
		}
		//

		registerRender(angelic_ingot);
		registerRender(demonic_ingot);
		registerRender(topazStone);
		registerRender(angelicDust);
		registerRender(mystalDust);
		registerRender(azuriteStone);
		registerRender(serpentineStone);
		registerRender(displacementDust);

		//Tools
		registerRender(serpentinePick);
		registerRender(serpentineSpade);
		registerRender(serpentineAxe);
		registerRender(serpentineHoe);
		registerRender(serpentineMulti);	

		//Weapons
		registerRender(serpentineSword);
		//registerRender(serpentineShield);
	}

	public static void registerRender(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item,  0, 
				new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}

	public static void registerRender(Item item, int meta, String fileName)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item,  meta, 
				new ModelResourceLocation(Reference.MODID + ":" + fileName, "inventory"));
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
