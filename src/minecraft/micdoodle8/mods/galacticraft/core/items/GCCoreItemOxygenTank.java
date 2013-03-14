package micdoodle8.mods.galacticraft.core.items;

import java.util.ArrayList;
import java.util.List;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.ClientProxyCore;
import micdoodle8.mods.galacticraft.core.client.GCCorePlayerBaseClient;
import micdoodle8.mods.galacticraft.core.util.PlayerUtil;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GCCoreItemOxygenTank extends Item
{
	protected List<Icon> icons = new ArrayList<Icon>();
	
	public GCCoreItemOxygenTank(int par1)
	{
		super(par1);
		this.setMaxStackSize(1);
	}

	@Override
    public CreativeTabs getCreativeTab()
    {
        return GalacticraftCore.galacticraftTab;
    }

    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
		return EnumRarity.uncommon;
    }

	@Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer player, List par2List, boolean b)
	{
		par2List.add("- Air Remaining: " + (par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage()));

    	if (player != null && player.worldObj.isRemote)
    	{
    		final GCCorePlayerBaseClient playerBaseCl = PlayerUtil.getPlayerBaseClientFromPlayer(player);

    		if (playerBaseCl != null && playerBaseCl.getUseTutorialText())
    		{
    			par2List.add("- Press " + Keyboard.getKeyName(ClientProxyCore.GCKeyHandler.tankRefill.keyCode) + " to access");
            	par2List.add("     Galacticraft Inventory");
    		}
    	}
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void func_94581_a(IconRegister iconRegister)
	{
		List<ItemStack> list = new ArrayList<ItemStack>();
		this.getSubItems(this.itemID, this.getCreativeTab(), list);

		if (list.size() > 0)
		{
			for (ItemStack itemStack : list)
			{
				this.icons.add(iconRegister.func_94245_a("galacticraftcore:oxygen_tank_light"));
				this.icons.add(iconRegister.func_94245_a("galacticraftcore:oxygen_tank_medium"));
				this.icons.add(iconRegister.func_94245_a("galacticraftcore:oxygen_tank_heavy"));
			}
		}
		else
		{
			this.iconIndex = iconRegister.func_94245_a("galacticraftcore:extractor_1");
		}
	}

	@Override
    public Icon getIconFromDamage(int par1)
    {
		if (this.itemID == GCCoreItems.heavyOxygenTank.itemID)
		{
			return this.icons.get(2);
		}

		if (this.itemID == GCCoreItems.medOxygenTank.itemID)
		{
			return this.icons.get(1);
		}

		if (this.itemID == GCCoreItems.lightOxygenTank.itemID)
		{
			return this.icons.get(0);
		}
		
		return this.icons.get(0);
    }
}