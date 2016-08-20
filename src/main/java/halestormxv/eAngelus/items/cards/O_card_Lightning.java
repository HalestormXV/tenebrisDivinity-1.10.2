package halestormxv.eAngelus.items.cards;

import java.util.List;

import halestormxv.eAngelus.items.ModItemBowBase;
import halestormxv.eAngelus.items.eAngelusCards;
import halestormxv.eAngelus.main.Reference;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class O_card_Lightning extends eAngelusCards 
{	
	public O_card_Lightning(String unlocalizedName) 
	{
		super(unlocalizedName);
		this.setMaxStackSize(1);
		this.setMaxDamage(-1);
	}

	public boolean isDamageable()
	{
		return false;
	}
	
	//=====================DO ALL THE STUFF HERE=====================\\
	@Override
	public int getMaxItemUseDuration(ItemStack stack) 
	{
		return CHARGE_UP_DURATION_TICKS + CHARGE_UP_INITIAL_PAUSE_TICKS;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
	{
		if (playerIn.isSneaking())
		{
			playerIn.setActiveHand(hand); // start the charge up sequence
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
		}else{  
			playerIn.addChatComponentMessage(new TextComponentString("You need to be sneaking to activate an ORDER."));
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
		}
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
	{
		if (!worldIn.isRemote) 
		{
			if (entityLiving instanceof EntityPlayer)
			{
				int j = getMaxItemUseDuration(stack);
				EntityPlayer entityPlayer = (EntityPlayer) entityLiving;
				
				List<EntityLivingBase> targetList = entityPlayer.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, entityPlayer.getEntityBoundingBox().expand(8.0F + j, 8.0F + j, 8.0F + j));
				for (EntityLivingBase targets : targetList)
				{
					if ( targets != null)
					{
						if ( targets != entityPlayer )
						{
							entityPlayer.worldObj.spawnEntityInWorld(new EntityLightningBolt(worldIn, targets.posX, targets.posY, targets.posZ, true) );
							targets.clearActivePotions();
							worldIn.createExplosion(targets, targets.posX, targets.posY, targets.posZ, 3.2F + (j / 2), true);
							targets.setHealth(targets.getHealth() - j);
							//world.playSoundAtEntity(targets, RefStrings.MODID + ":leo_gift_execute", 1.4F, 1.0F);
						}
					}
				}
			}
		}
		return stack;
		//	    for items with multiple count, decrease stack size and return the itemstack, eg
		//	    stack.stackSize--;
		//	    return stack;
	}
	//=====================END ALL THE STUFF HERE=====================\\
}
