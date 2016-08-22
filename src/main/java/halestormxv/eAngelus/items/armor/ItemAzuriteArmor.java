package halestormxv.eAngelus.items.armor;

import java.util.UUID;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.init.eAngelusArmor;
import halestormxv.eAngelus.main.init.eAngelusItems;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemAzuriteArmor extends ItemArmor
{

	public ItemAzuriteArmor(int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) 
	{
		super(eAngelusItems.Azurite, renderIndexIn, equipmentSlotIn);
		this.setCreativeTab(Reference.eaCreativeTab);
	}
	
	/*
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) 
	{
		Multimap mods = HashMultimap.create();
		if(this == eAngelusArmor.AZURITE_HELM)
			mods.put(SharedMonsterAttributes.MAX_HEALTH.getAttributeUnlocalizedName(), 
					new AttributeModifier(UUID.fromString("1bca943c-3cf5-42cc-a3df-2ed994ae0000"), "hp", 3D, 0));
		
		return mods;
	}*/
	

}
