package micdoodle8.mods.galacticraft.core.items;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GCCoreItemPickaxe extends ItemPickaxe
{
	private final EnumToolMaterial material;

	public GCCoreItemPickaxe(int par1, EnumToolMaterial par2EnumToolMaterial)
	{
		super(par1, par2EnumToolMaterial);
		this.material = par2EnumToolMaterial;
	}

	@Override
    public CreativeTabs getCreativeTab()
    {
        return GalacticraftCore.galacticraftTab;
    }

	@Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.rare;
    }

	@Override
    @SideOnly(Side.CLIENT)
    public void func_94581_a(IconRegister par1IconRegister)
    {
        this.iconIndex = par1IconRegister.func_94245_a(this.getUnlocalizedName().replace("item.", "galacticraftcore:"));
    }
}