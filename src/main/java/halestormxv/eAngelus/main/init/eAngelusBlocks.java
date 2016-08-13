package halestormxv.eAngelus.main.init;

import halestormxv.eAngelus.blocks.AngelicBlock;
import halestormxv.eAngelus.blocks.AngelicOre;
import halestormxv.eAngelus.blocks.AzureiteOre;
import halestormxv.eAngelus.blocks.DemonicBlock;
import halestormxv.eAngelus.blocks.DemonicOre;
import halestormxv.eAngelus.blocks.MystalCite;
import halestormxv.eAngelus.blocks.ObsidianIronTable;
import halestormxv.eAngelus.blocks.SerpentineOre;
import halestormxv.eAngelus.blocks.TopazOre;
import halestormxv.eAngelus.items.EAItem;
import halestormxv.eAngelus.main.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class eAngelusBlocks 
{
	public static AngelicBlock angelic_block;
	public static DemonicBlock demonic_block;
	public static AngelicOre angelicOre;
	public static DemonicOre demonicOre;
	public static MystalCite mystalCite;
	public static AzureiteOre azureite_Ore;
	public static SerpentineOre serpentine_Ore;
	public static ObsidianIronTable oTable;

	//Gems
	public static TopazOre topazOre;
	
	
	
	public static void initBlocks()
	{
		//Blocks
		angelic_block = new AngelicBlock();
		angelic_block.setUnlocalizedName("angelic_block");
		angelic_block.setCreativeTab(Reference.eaCreativeTab);
		demonic_block = new DemonicBlock();
		oTable = new ObsidianIronTable("oTable", Material.IRON);

		//Ores
		angelicOre = new AngelicOre();
		demonicOre = new DemonicOre();
		mystalCite = new MystalCite();
		azureite_Ore = new AzureiteOre();
		serpentine_Ore = new SerpentineOre();
		
		//Gems
		topazOre = new TopazOre();
	}	
	
	public static void registerRenders()
	{
		//Blocks
		registerRender(angelic_block);
		registerRender(demonic_block);
		
		//Ores
		registerRender(angelicOre);
		registerRender(demonicOre);
		registerRender(mystalCite);
		registerRender(azureite_Ore);
		registerRender(serpentine_Ore);
		registerRender(oTable);
		
		//Gems
		registerRender(topazOre);
	}
	
	public static void registerRender(Block block)
	{		
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block),  0, 
		new ModelResourceLocation(Reference.MODID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
	}
	
	
	
	
	
	
	
	
	//registerBlock Start\\
	public static Block registerBlock(Block block, String name)
	{
		return registerBlock(block, name, null);
	}
	
	public static Block registerBlock(Block block, String name, CreativeTabs tab)
	{
		GameRegistry.register(block, new ResourceLocation(Reference.MODID, name));
		return block;
	}
	//registerBlock End\\

}
