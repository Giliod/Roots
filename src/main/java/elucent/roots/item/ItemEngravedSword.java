package elucent.roots.item;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import elucent.roots.RegistryManager;
import elucent.roots.Roots;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEngravedSword extends ItemTool{
	private ToolMaterial material;
	private String[] numerals = {"0", "I", "II", "III", "IIII"};
	
	public ItemEngravedSword(ToolMaterial material){
		super(material,Sets.newHashSet(new Block[]{Blocks.WEB}));
		this.material = material;
		this.setUnlocalizedName("engravedSword");
		this.setCreativeTab(Roots.tab);
	}
	
	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot){
	    Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

	    if (equipmentSlot == EntityEquipmentSlot.MAINHAND){	
	    	multimap.removeAll(SharedMonsterAttributes.ATTACK_SPEED.getAttributeUnlocalizedName());      
	    	multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getAttributeUnlocalizedName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.7D, 0));
	    }
	    return multimap;
	}

	@SideOnly(Side.CLIENT)
	public void initModel(){
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(),"inventory"));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced)
	{
		if(stack.hasTagCompound()){
			NBTTagCompound tag = stack.getTagCompound();
			if(tag.hasKey("spikes")){
				tooltip.add("Spikes " + numerals[tag.getInteger("spikes")]);
			}
			if(tag.hasKey("forceful")){
				tooltip.add("Forceful " + numerals[tag.getInteger("forceful")]);
			}
			if(tag.hasKey("holy")){
				tooltip.add("Holy " + numerals[tag.getInteger("holy")]);
			}
			if(tag.hasKey("aquatic")){
				tooltip.add("Aqautic " + numerals[tag.getInteger("aquatic")]);
			}
			if(tag.hasKey("shadowstep")){
				tooltip.add("Shadow Step " + numerals[tag.getInteger("shadowstep")]);
			}
		}
	}

}
