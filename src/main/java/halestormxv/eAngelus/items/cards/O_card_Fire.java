package halestormxv.eAngelus.items.cards;

import java.util.List;

import halestormxv.eAngelus.items.eAngelusCards;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class O_card_Fire extends eAngelusCards 
{
	public O_card_Fire(String unlocalizedName) 
	{
		super(unlocalizedName);
		this.setMaxStackSize(1);
		this.setMaxDamage(-1);
	}

	public boolean isDamageable()
	{
		return false;
	}

}


/*
@Override
public void addInformation(ItemStack istack, EntityPlayer player, List info, boolean par4) {

	NBTTagCompound tag = istack.stackTagCompound;

    if (tag == null)
    {
        tag = new NBTTagCompound();
        tag.setTag("display", new NBTTagCompound());
        istack.stackTagCompound = tag;
    }

    tag.getTag("display");
    tag.setTag("display", new NBTTagCompound());
	NBTTagCompound taglore = tag.getCompoundTag("display");
	NBTTagList lore = new NBTTagList();

	lore.appendTag(new NBTTagString("�r"+"This is a special item, for adding"));
	lore.appendTag(new NBTTagString("�r"+"guests"));
	lore.appendTag(new NBTTagString("�r"+"into your home."));

	taglore.setTag("Lore", lore);
}*/
