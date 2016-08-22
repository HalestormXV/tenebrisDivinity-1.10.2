package halestormxv.eAngelus.crafting;

import halestormxv.eAngelus.main.init.eAngelusArmor;
import halestormxv.eAngelus.main.init.eAngelusBlocks;
import halestormxv.eAngelus.main.init.eAngelusItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class EARecipes 
{
	public static void initRecipes()
	{
		//Shaped Crafting
		GameRegistry.addRecipe(new ItemStack(eAngelusBlocks.angelic_block),
				new Object []
						{
								"###",
								"###",
								"###",
								'#', eAngelusItems.angelic_ingot				
						});
		
		//Serpentine Axe
		GameRegistry.addRecipe(new ItemStack(eAngelusItems.serpentineAxe),
				"*SS", 
				"*sS", 
				"*s*", 
				
				'S', eAngelusItems.serpentineStone, 's', Items.STICK);
		
		//Serpentine Spade
		GameRegistry.addRecipe(new ItemStack(eAngelusItems.serpentineSpade),
				"*S*", 
				"*s*", 
				"*s*", 
				
				'S', eAngelusItems.serpentineStone, 's', Items.STICK);
		
		//Serpentine Pick
		GameRegistry.addRecipe(new ItemStack(eAngelusItems.serpentinePick),
				"SSS", 
				"*s*", 
				"*s*", 
				
				'S', eAngelusItems.serpentineStone, 's', Items.STICK);
		
		//Serpentine Hoe
		GameRegistry.addRecipe(new ItemStack(eAngelusItems.serpentineHoe),
				"*SS", 
				"*s*", 
				"*s*", 
				
				'S', eAngelusItems.serpentineStone, 's', Items.STICK);
		
		//Multi Tool
		GameRegistry.addRecipe(new ItemStack(eAngelusItems.serpentineMulti),
				"PAS", 
				"*sH", 
				"*s*", 
				
				'S', eAngelusItems.serpentineSpade, 's', Items.STICK, 'P', eAngelusItems.serpentinePick, 
				'A', eAngelusItems.serpentineAxe, 'H', eAngelusItems.serpentineHoe);
		
		
		//Azurite Helm
		GameRegistry.addRecipe(new ItemStack(eAngelusArmor.AZURITE_HELM),
				"AAA", 
				"A*A", 
				"***", 
				
				'A', eAngelusItems.azuriteStone);
		//Azurite Chest
		GameRegistry.addRecipe(new ItemStack(eAngelusArmor.AZURITE_CHEST),
				"A*A", 
				"AAA", 
				"AAA", 
				
				'A', eAngelusItems.azuriteStone);
		//Azurite Leggings
		GameRegistry.addRecipe(new ItemStack(eAngelusArmor.AZURITE_LEGGINGS),
				"AAA", 
				"A*A", 
				"A*A", 
				
				'A', eAngelusItems.azuriteStone);
		//Azurite Boots
		GameRegistry.addRecipe(new ItemStack(eAngelusArmor.AZURITE_BOOTS),
				"***", 
				"A*A", 
				"A*A", 
				
				'A', eAngelusItems.azuriteStone);
		
		//===================Shapeless Crafting===================\\
		GameRegistry.addShapelessRecipe(new ItemStack(eAngelusItems.angelic_ingot, 9), 
				new Object [] {
				eAngelusBlocks.angelic_block
				});
		
		GameRegistry.addShapelessRecipe(new ItemStack(eAngelusItems.demonic_ingot, 9), 
				new Object [] {
				eAngelusBlocks.demonic_block
				});
		
		
		//Smelting Recipes
		GameRegistry.addSmelting(eAngelusBlocks.angelicOre, new ItemStack(eAngelusItems.angelic_ingot), 13.0F);
		GameRegistry.addSmelting(eAngelusBlocks.demonicOre, new ItemStack(eAngelusItems.demonic_ingot), 13.0F);
	}

}
