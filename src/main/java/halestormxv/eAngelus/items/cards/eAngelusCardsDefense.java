package halestormxv.eAngelus.items.cards;

import java.util.List;

import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.init.eAngelusItems;
import halestormxv.eAngelus.main.init.eAngelusSoundEvents;
import halestormxv.eAngelus.main.network.packets.ChatUtil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class eAngelusCardsDefense extends Item
{
	static public final int D_CHARGE_UP_INITIAL_PAUSE_TICKS = 15;
	static public final int D_CHARGE_UP_DURATION_TICKS = 25;
	private int ReagentCost;

	//Defense Card Names
	public static final String[] D_cardNames = new String[] {"c_D_Resist", "c_D_WaterBreath", "c_D_NightVision", "c_D_Feather", "c_D_ExplodeProtect"};

	public eAngelusCardsDefense(String unlocalizedName)
	{
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(Reference.eaCreativeTab);
		this.setMaxStackSize(1);
		this.setMaxDamage(-1);
		this.setHasSubtypes(true);
	}

	public boolean isDamageable()
	{
		return false;
	}

	public EnumAction getItemUseAction(ItemStack itemstack)
	{
		return EnumAction.BLOCK;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
	{
		NBTTagCompound nbtTagCompound = itemStackIn.getTagCompound();
		if (nbtTagCompound == null) 
		{
			nbtTagCompound = new NBTTagCompound();
			itemStackIn.setTagCompound(nbtTagCompound);
			ItemStack reagentName = new ItemStack(getItemUsedByORDER(itemStackIn));
			nbtTagCompound.setString("Reagent", reagentName.getDisplayName());
		}

		if (playerIn.inventory.hasItemStack(new ItemStack(getItemUsedByORDER(itemStackIn))))
		{
			if (playerIn.isSneaking())
			{
				playerIn.setActiveHand(hand); // start the charge up sequence
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
			}
			else
			{  
				ChatUtil.sendNoSpam(playerIn, "\u00A74You need to be sneaking to activate an ORDER.");
				return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
			}
		}
		else
		{
			ChatUtil.sendNoSpam(playerIn, "You need the correct \u00A76Reagent \u00A7fto power an ORDER.");
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
		}
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) 
	{
		return D_CHARGE_UP_DURATION_TICKS + D_CHARGE_UP_INITIAL_PAUSE_TICKS;
	}


	//===============================HANDLE ALL CARD EFFECTS===============================\\
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
	{
		switch(stack.getItemDamage())
		{
		case 0:
			if (entityLiving instanceof EntityPlayer)
			{
				EntityPlayer entityPlayer = (EntityPlayer) entityLiving;
				entityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 1200, 4));
				entityPlayer.playSound(eAngelusSoundEvents.FINISH_CARD_USE, 4.0f, 1.0f);
			}
			break;

		case 1:
			if (entityLiving instanceof EntityPlayer)
			{
				EntityPlayer entityPlayer = (EntityPlayer) entityLiving;
				entityPlayer.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 1200, 2));
				entityPlayer.playSound(eAngelusSoundEvents.FINISH_CARD_USE, 4.0f, 1.0f);
			}
			break;

		case 2:
			if (entityLiving instanceof EntityPlayer)
			{
				EntityPlayer entityPlayer = (EntityPlayer) entityLiving;
				entityPlayer.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 1500, 2));
				entityPlayer.playSound(eAngelusSoundEvents.FINISH_CARD_USE, 4.0f, 1.0f);
			}
			break;

		case 3:
			if (entityLiving instanceof EntityPlayer)
			{
				EntityPlayer entityPlayer = (EntityPlayer) entityLiving;
				entityPlayer.addPotionEffect(new PotionEffect(MobEffects.HASTE, 900, 2));
				entityPlayer.playSound(eAngelusSoundEvents.FINISH_CARD_USE, 4.0f, 1.0f);
			}
			break;

		case 4:
			if (entityLiving instanceof EntityPlayer)
			{
				EntityPlayer entityPlayer = (EntityPlayer) entityLiving;
				entityPlayer.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 800, 3));
				entityPlayer.playSound(eAngelusSoundEvents.FINISH_CARD_USE, 4.0f, 1.0f);
			}
			break;
		}
		this.consumeReagent(stack, worldIn, (EntityPlayer) entityLiving);
		return stack;
	}

	protected void consumeReagent(ItemStack stack, World worldIn, EntityPlayer entityLiving) 
	{
		if (stack.getItemDamage() == 4)
		{
			entityLiving.inventory.clearMatchingItems(getItemUsedByORDER(stack), -1, 2, null);
		}else{
			entityLiving.inventory.clearMatchingItems(getItemUsedByORDER(stack), -1, 1, null);
		}
	}

	protected Item getItemUsedByORDER(ItemStack itemStackIn) 
	{
		if (itemStackIn.getItemDamage() == 4)
		{
			return eAngelusItems.displacementDust;
		}else{
			return eAngelusItems.mystalDust;
		}
	}

	/*protected int getOrderItemCost(ItemStack card) 
	{
		if (card.getItemDamage() == 4)
		{
			ReagentCost = 2;
		}else{
			ReagentCost = 1;
		}
		return ReagentCost;
	}*/

	//===============================AUTO HANDLE UNLOCALIZED NAMES===============================\\
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> items) 
	{
		for (int i = 0; i < D_cardNames.length; ++i)
		{
			items.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) 
	{
		for (int i = 0; i < D_cardNames .length; ++i)
			if(stack.getItemDamage() == i)
			{
				return this.getUnlocalizedName() + "." + D_cardNames[i];
			}
		return "Invalid";
	}
	//===============================CLIENT SIDE SNAZZY FLAVOR TEXT AND FUN STUFF===============================\\
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		NBTTagCompound nbtTagCompound = stack.getTagCompound();

		if (stack.getItemDamage() == 0)
		{
			tooltip.add("\u00A76" + "Humans can lie with words. And they can deceive with actions.");
			tooltip.add("\u00A76" + "You can't trust their eyes, voices, or expressions.");
			tooltip.add("\u00A76" + "But when they're about to die,");
			tooltip.add("\u00A76" + "their faces show the truth, no matter what.");
			tooltip.add("");
			tooltip.add("\u00A7n" + "Enhances your Resistance.");
			tooltip.add("");
			if (nbtTagCompound != null && nbtTagCompound.hasKey("Reagent"))
			{
				tooltip.add("\u00A7aReagent Used: "+nbtTagCompound.getString("Reagent"));
			}
		}
		if (stack.getItemDamage() == 1)
		{
			tooltip.add("\u00A76" + "Ever since that fiery night, my time has been stopped.");
			tooltip.add("\u00A76" + "But it was you who melted my frozen heart and kindly made");
			tooltip.add("\u00A76" + "my stopped time begin to move forward again");
			tooltip.add("");
			tooltip.add("\u00A7n" + "Allows Underwater Breathing.");
			tooltip.add("");
			if (nbtTagCompound != null && nbtTagCompound.hasKey("Reagent"))
			{
				tooltip.add("\u00A7aReagent Used: "+nbtTagCompound.getString("Reagent"));
			}
		}
		if (stack.getItemDamage() == 2)
		{
			tooltip.add("\u00A76" + "The night is in its darkest just before dawn.");
			tooltip.add("\u00A76" + "But keep your eyes open. If you avert your eyes from the dark,");
			tooltip.add("\u00A76" + "you'll be blinded by the rays of a new day. So keep your eyes open,");
			tooltip.add("\u00A76" + "no matter how dark the night ahead may be");
			tooltip.add("");
			tooltip.add("\u00A7n" + "Grants Night Vision.");
			tooltip.add("");
			if (nbtTagCompound != null && nbtTagCompound.hasKey("Reagent"))
			{
				tooltip.add("\u00A7aReagent Used: "+nbtTagCompound.getString("Reagent"));
			}
		}
		if (stack.getItemDamage() == 3)
		{
			tooltip.add("\u00A76" + "A maiden grieves on a dark night.");
			tooltip.add("\u00A76" + "She sinks alone into the midnight curtain.");
			tooltip.add("\u00A76" + "The silver moon nestles close,");
			tooltip.add("\u00A76" + "as the sacred night passes quietly.");
			tooltip.add("");
			tooltip.add("\u00A7n" + "Adds Haste.");
			tooltip.add("");	
			if (nbtTagCompound != null && nbtTagCompound.hasKey("Reagent"))
			{
				tooltip.add("\u00A7aReagent Used: "+nbtTagCompound.getString("Reagent"));
			}
		}
		if (stack.getItemDamage() == 4)
		{
			tooltip.add("\u00A76" + "The world isn't perfect. But it's there for us,");
			tooltip.add("\u00A76" + "doing the best it can....that's what makes it");
			tooltip.add("\u00A76" + "so damn beautiful.");
			tooltip.add("");
			tooltip.add("\u00A7n" + "Adds a temporary Absorbtion Shield.");
			tooltip.add("");	
			if (nbtTagCompound != null && nbtTagCompound.hasKey("Reagent"))
			{
				tooltip.add("\u00A7aReagent Used: "+nbtTagCompound.getString("Reagent"));
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack)
	{
		return EnumRarity.UNCOMMON;
		/*if (stack.getItemDamage() == 0)
			{
				return EnumRarity.UNCOMMON;
			}else{
				return EnumRarity.COMMON;
			}*/
	}
}
