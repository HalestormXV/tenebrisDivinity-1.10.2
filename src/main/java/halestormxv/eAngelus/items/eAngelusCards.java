package halestormxv.eAngelus.items;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;

import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.init.eAngelusItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class eAngelusCards extends Item
{
	static public final int CHARGE_UP_INITIAL_PAUSE_TICKS = 10;
	static public final int CHARGE_UP_DURATION_TICKS = 20;

	//Offense Card Names
	public static final String[] O_cardNames = new String[] {"cIgnis", "cFortitudo", "cVentus", "cArescet", "cLightning"};

	public eAngelusCards(String unlocalizedName)
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
		if (playerIn.inventory.hasItemStack(new ItemStack(getItemUsedByORDER())))
		{
			if (playerIn.isSneaking())
			{
				playerIn.setActiveHand(hand); // start the charge up sequence
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
			}
			else
			{  
				playerIn.addChatComponentMessage(new TextComponentString("\u00A74You need to be sneaking to activate an ORDER."));
				return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
			}
		}
		else
		{
			playerIn.addChatComponentMessage(new TextComponentString("You need \u00A76Mystal Dust \u00A7fto power an ORDER."));
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
		}
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) 
	{
		return CHARGE_UP_DURATION_TICKS + CHARGE_UP_INITIAL_PAUSE_TICKS;
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
				for(int i = 0; i <=3; i++)
				{
					Vec3d look = entityLiving.getLookVec();
					EntityLargeFireball fireball2 = new EntityLargeFireball(worldIn, entityLiving, 1, 1, 1);
					fireball2.setPosition(entityLiving.posX + look.xCoord * 5 + i, entityLiving.posY + look.yCoord * 5, entityLiving.posZ + look.zCoord * 5 + i + 1);
					fireball2.accelerationX = look.xCoord * 0.3;
					fireball2.accelerationY = look.yCoord * 0.3;
					fireball2.accelerationZ = look.zCoord * 0.3;
					worldIn.spawnEntityInWorld(fireball2);
				}
			}
			break;

		case 1:
			if (entityLiving instanceof EntityPlayer)
			{
				EntityPlayer entityPlayer = (EntityPlayer) entityLiving;
				entityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 500, 2));
			}
			break;

		case 2:
			if (entityLiving instanceof EntityPlayer)
			{
				EntityPlayer entityPlayer = (EntityPlayer) entityLiving;
				entityPlayer.addPotionEffect(new PotionEffect(MobEffects.SPEED, 500, 2));
				entityPlayer.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 500, 2));
			}
			break;

		case 3:
			if (entityLiving instanceof EntityPlayer)
			{
				int j = getMaxItemUseDuration(stack) / Math.round(5);
				EntityPlayer entityPlayer = (EntityPlayer) entityLiving;

				List<EntityLivingBase> targetList = entityPlayer.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, entityPlayer.getEntityBoundingBox().expand(8.0F + j, 8.0F + j, 8.0F + j));
				for (EntityLivingBase targets : targetList)
				{
					if ( targets != null)
					{
						if ( targets != entityPlayer )
						{
							targets.clearActivePotions();
							targets.addPotionEffect(new PotionEffect(MobEffects.WITHER, 500, 2 + j));
							targets.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 500, 2 + j));
							//world.playSoundAtEntity(targets, RefStrings.MODID + ":leo_gift_execute", 1.4F, 1.0F);
							System.out.println(j);
						}
					}
				}
			}
			break;

		case 4:
			if (entityLiving instanceof EntityPlayer)
			{
				int j = getMaxItemUseDuration(stack) / Math.round(5);
				EntityPlayer entityPlayer = (EntityPlayer) entityLiving;

				List<EntityLivingBase> targetList = entityPlayer.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, entityPlayer.getEntityBoundingBox().expand(8.0F + j, 8.0F + j, 8.0F + j));
				for (EntityLivingBase targets : targetList)
				{
					if ( targets != null)
					{
						if ( targets != entityPlayer )
						{
							entityPlayer.worldObj.spawnEntityInWorld(new EntityLightningBolt(worldIn, targets.posX, targets.posY, targets.posZ, false) );
							targets.clearActivePotions();
							worldIn.createExplosion(targets, targets.posX, targets.posY, targets.posZ, 3.2F + (j / 2), true);
							targets.setHealth(targets.getHealth() - j);
							//world.playSoundAtEntity(targets, RefStrings.MODID + ":leo_gift_execute", 1.4F, 1.0F);
							System.out.println(j);
						}
					}
				}
			}
			break;
		}
		this.consumeReagent(stack, worldIn, (EntityPlayer) entityLiving);
		return stack;
		//	    for items with multiple count, decrease stack size and return the itemstack, eg
		//	    stack.stackSize--;
		//	    return stack;
	}

	protected void consumeReagent(ItemStack stack, World worldIn, EntityPlayer entityLiving) {
		entityLiving.inventory.clearMatchingItems(getItemUsedByORDER(), -1, 1, null);
	}

	protected Item getItemUsedByORDER() 
	{
		return eAngelusItems.mystalDust;
	}

	//===============================AUTO HANDLE UNLOCALIZED NAMES===============================\\
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> items) 
	{
		for (int i = 0; i < O_cardNames.length; ++i)
		{
			items.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) 
	{
		for (int i = 0; i < O_cardNames .length; ++i)
			if(stack.getItemDamage() == i)
			{
				return this.getUnlocalizedName() + "." + O_cardNames[i];
			}
		return "Invalid";
	}
	//===============================CLIENT SIDE SNAZZY FLAVOR TEXT AND FUN STUFF===============================\\
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		if (stack.getItemDamage() == 0)
		{
			tooltip.add("");
			tooltip.add("\u00A76" + "You fall into my arms.");
			tooltip.add("\u00A76" + "You are the good gift of destruction's path.");
			tooltip.add("\u00A76" + "When life sickens more than disease.");
			tooltip.add("\u00A76" + "And boldness is the root of beauty...");
			tooltip.add("");
			tooltip.add("\u00A7n" + "Calls forth fire supreme.");
		}
		if (stack.getItemDamage() == 1)
		{
			tooltip.add("");
			tooltip.add("\u00A76" + "You have power over your mind.");
			tooltip.add("\u00A76" + "Not over outside events.");
			tooltip.add("\u00A76" + "Realize this and you");
			tooltip.add("\u00A76" + "will find strength..");
			tooltip.add("");
			tooltip.add("\u00A7n" + "Enhances physical strength.");
		}
		if (stack.getItemDamage() == 2)
		{
			tooltip.add("");
			tooltip.add("\u00A76" + "He who does not know how");
			tooltip.add("\u00A76" + "to look back at where he came");
			tooltip.add("\u00A76" + "from will never get to his");
			tooltip.add("\u00A76" + "destination...");
			tooltip.add("");
			tooltip.add("\u00A7n" + "Enhances Movement and Jump.");
		}
		if (stack.getItemDamage() == 3)
		{
			tooltip.add("");
			tooltip.add("\u00A76" + "I like it that order exists somwhere,");
			tooltip.add("\u00A76" + "even if it shatters near me.");
			tooltip.add("\u00A76" + "Order will wither and born");
			tooltip.add("\u00A76" + "will be chaos...");
			tooltip.add("");
			tooltip.add("\u00A7n" + "Inflicts Wither and Weakness around you.");
		}
		if (stack.getItemDamage() == 4)
		{
			tooltip.add("");
			tooltip.add("\u00A76" + "Suddenly there was a great burst of");
			tooltip.add("\u00A76" + "light through the Darkness.");
			tooltip.add("\u00A76" + "The light spread out and where");
			tooltip.add("\u00A76" + "it touched, Darkness disappeared..");
			tooltip.add("");
			tooltip.add("\u00A7n" + "Calls down a storm of lightning.");
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
