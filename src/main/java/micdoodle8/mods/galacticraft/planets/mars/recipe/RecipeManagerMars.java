package micdoodle8.mods.galacticraft.planets.mars.recipe;

import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import micdoodle8.mods.galacticraft.core.util.RecipeUtil;
import micdoodle8.mods.galacticraft.planets.mars.blocks.BlockMachineMars;
import micdoodle8.mods.galacticraft.planets.mars.blocks.MarsBlocks;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import micdoodle8.mods.galacticraft.planets.mars.util.MarsUtil;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashMap;

public class RecipeManagerMars
{
	public static void loadRecipes()
	{
		RecipeManagerMars.addUniversalRecipes();
	}

	private static void addUniversalRecipes()
	{
		OreDictionary.registerOre("ingotDesh", new ItemStack(MarsItems.marsItemBasic, 1, 2));
		OreDictionary.registerOre("plateDesh", new ItemStack(MarsItems.marsItemBasic, 1, 5));

		RecipeUtil.addRecipe(new ItemStack(MarsItems.deshBoots), new Object[] { "X X", "X X", 'X', new ItemStack(MarsItems.marsItemBasic, 1, 2) });

		RecipeUtil.addRecipe(new ItemStack(MarsBlocks.marsBlock, 1, 8), new Object[] { "XXX", "XXX", "XXX", 'X', "ingotDesh" });

		RecipeUtil.addRecipe(new ItemStack(MarsBlocks.machine, 1, 0), new Object[] { "XWX", "XZX", "WVW", 'V', GCItems.oxygenConcentrator, 'W', "plateDesh", 'X', "ingotDesh", 'Z', new ItemStack(GCItems.canister) });

		RecipeUtil.addRecipe(new ItemStack(MarsBlocks.machine, 1, 4), new Object[] { "XYX", "XZX", "XYX", 'X', "plateDesh", 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 3), 'Z', Items.bed });
		//Gas liquefier
		RecipeUtil.addRecipe(new ItemStack(MarsBlocks.machineT2, 1, 0), new Object[] { "TCT", "XWX", "OCP", 'T', GCItems.oxTankHeavy, 'C', GCItems.oxygenConcentrator, 'X', "plateDesh", 'W', "advancedWafer", 'O', new ItemStack(GCBlocks.oxygenCompressor, 1, 0), 'O', new ItemStack(GCBlocks.oxygenCompressor, 1, 4) });

		RecipeUtil.addRecipe(new ItemStack(MarsItems.marsItemBasic, 1, 1), new Object[] { "X", "X", 'X', "ingotDesh" });

		RecipeUtil.addRecipe(new ItemStack(MarsItems.marsItemBasic, 1, 4), new Object[] { "XWX", "XYX", " Z ", 'W', Items.diamond, 'X', Items.leather, 'Y', Items.slime_ball, 'Z', Blocks.chest });

		RecipeUtil.addRecipe(new ItemStack(MarsItems.deshSword), new Object[] { "X", "X", "Y", 'X', new ItemStack(MarsItems.marsItemBasic, 1, 2), 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });

		RecipeUtil.addRecipe(new ItemStack(MarsItems.deshPickaxe), new Object[] { "XXX", " Y ", " Y ", 'X', new ItemStack(MarsItems.marsItemBasic, 1, 2), 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });

		RecipeUtil.addRecipe(new ItemStack(MarsItems.deshSpade), new Object[] { "X", "Y", "Y", 'X', new ItemStack(MarsItems.marsItemBasic, 1, 2), 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });

		RecipeUtil.addRecipe(new ItemStack(MarsItems.deshHoe), new Object[] { "XX", "Y ", "Y ", 'X', new ItemStack(MarsItems.marsItemBasic, 1, 2), 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });

		RecipeUtil.addRecipe(new ItemStack(MarsItems.deshHoe), new Object[] { "XX", " Y", " Y", 'X', new ItemStack(MarsItems.marsItemBasic, 1, 2), 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });

		RecipeUtil.addRecipe(new ItemStack(MarsItems.deshAxe), new Object[] { "XX", "XY", " Y", 'X', new ItemStack(MarsItems.marsItemBasic, 1, 2), 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });

		RecipeUtil.addRecipe(new ItemStack(MarsItems.deshAxe), new Object[] { "XX", "YX", "Y ", 'X', new ItemStack(MarsItems.marsItemBasic, 1, 2), 'Y', new ItemStack(MarsItems.marsItemBasic, 1, 1) });

		RecipeUtil.addRecipe(new ItemStack(MarsItems.deshHelmet), new Object[] { "XXX", "X X", 'X', new ItemStack(MarsItems.marsItemBasic, 1, 2) });

		RecipeUtil.addRecipe(new ItemStack(MarsItems.deshChestplate), new Object[] { "X X", "XXX", "XXX", 'X', new ItemStack(MarsItems.marsItemBasic, 1, 2) });

		RecipeUtil.addRecipe(new ItemStack(MarsItems.deshLeggings), new Object[] { "XXX", "X X", "X X", 'X', new ItemStack(MarsItems.marsItemBasic, 1, 2) });

		RecipeUtil.addRecipe(new ItemStack(MarsItems.marsItemBasic, 9, 2), new Object[] { "X", 'X', new ItemStack(MarsBlocks.marsBlock, 1, 8) });

		for (int var2 = 0; var2 < 16; ++var2)
		{
			CraftingManager.getInstance().addShapelessRecipe(new ItemStack(MarsBlocks.tintedGlassPane, 1, 15 - var2), new Object[] { new ItemStack(Items.dye, 1, var2), Blocks.glass_pane, OreDictionary.getOres("plateDesh").get(0) });
		}

		// Smelting
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(MarsItems.marsItemBasic, 1, 0), new ItemStack(MarsItems.marsItemBasic, 1, 2), 0.2F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(MarsBlocks.marsBlock, 1, 4), new ItemStack(MarsBlocks.marsBlock, 1, 9), 0.0F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(MarsBlocks.marsBlock, 1, 0), new ItemStack(GCItems.basicItem, 1, 3), 1.0F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(MarsBlocks.marsBlock, 1, 1), new ItemStack(GCItems.basicItem, 1, 4), 1.0F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(MarsBlocks.marsBlock, 1, 2), new ItemStack(MarsItems.marsItemBasic, 1, 2), 0.2F);
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(MarsBlocks.marsBlock, 1, 3), new ItemStack(Items.iron_ingot), 0.2F);

		// Schematic
		HashMap<Integer, ItemStack> input = new HashMap<Integer, ItemStack>();
		input.put(1, new ItemStack(GCItems.partNoseCone));
		input.put(2, new ItemStack(MarsItems.marsItemBasic, 1, 3));
		input.put(3, new ItemStack(MarsItems.marsItemBasic, 1, 3));
		input.put(4, new ItemStack(MarsItems.marsItemBasic, 1, 3));
		input.put(5, new ItemStack(MarsItems.marsItemBasic, 1, 3));
		input.put(6, new ItemStack(MarsItems.marsItemBasic, 1, 3));
		input.put(7, new ItemStack(MarsItems.marsItemBasic, 1, 3));
		input.put(8, new ItemStack(MarsItems.marsItemBasic, 1, 3));
		input.put(9, new ItemStack(MarsItems.marsItemBasic, 1, 3));
		input.put(10, new ItemStack(MarsItems.marsItemBasic, 1, 3));
		input.put(11, new ItemStack(MarsItems.marsItemBasic, 1, 3));
		input.put(12, new ItemStack(GCItems.rocketEngine, 1, 1));
		input.put(13, new ItemStack(GCItems.partFins));
		input.put(14, new ItemStack(GCItems.partFins));
		input.put(15, new ItemStack(GCItems.rocketEngine));
		input.put(16, new ItemStack(GCItems.rocketEngine, 1, 1));
		input.put(17, new ItemStack(GCItems.partFins));
		input.put(18, new ItemStack(GCItems.partFins));
		input.put(19, null);
		input.put(20, null);
		input.put(21, null);
		MarsUtil.addRocketBenchT2Recipe(new ItemStack(MarsItems.spaceship, 1, 0), input);

		HashMap<Integer, ItemStack> input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Blocks.chest));
		input2.put(20, null);
		input2.put(21, null);
		MarsUtil.addRocketBenchT2Recipe(new ItemStack(MarsItems.spaceship, 1, 1), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, null);
		input2.put(20, new ItemStack(Blocks.chest));
		input2.put(21, null);
		MarsUtil.addRocketBenchT2Recipe(new ItemStack(MarsItems.spaceship, 1, 1), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, null);
		input2.put(20, null);
		input2.put(21, new ItemStack(Blocks.chest));
		MarsUtil.addRocketBenchT2Recipe(new ItemStack(MarsItems.spaceship, 1, 1), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Blocks.chest));
		input2.put(20, new ItemStack(Blocks.chest));
		input2.put(21, null);
		MarsUtil.addRocketBenchT2Recipe(new ItemStack(MarsItems.spaceship, 1, 2), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Blocks.chest));
		input2.put(20, null);
		input2.put(21, new ItemStack(Blocks.chest));
		MarsUtil.addRocketBenchT2Recipe(new ItemStack(MarsItems.spaceship, 1, 2), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, null);
		input2.put(20, new ItemStack(Blocks.chest));
		input2.put(21, new ItemStack(Blocks.chest));
		MarsUtil.addRocketBenchT2Recipe(new ItemStack(MarsItems.spaceship, 1, 2), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(19, new ItemStack(Blocks.chest));
		input2.put(20, new ItemStack(Blocks.chest));
		input2.put(21, new ItemStack(Blocks.chest));
		MarsUtil.addRocketBenchT2Recipe(new ItemStack(MarsItems.spaceship, 1, 3), input2);

		//

		input = new HashMap<Integer, ItemStack>();
		input.put(1, new ItemStack(GCItems.partNoseCone));
		input.put(2, new ItemStack(GCItems.basicItem, 1, 14));
		input.put(3, new ItemStack(MarsItems.marsItemBasic, 1, 3));
		input.put(4, new ItemStack(MarsItems.marsItemBasic, 1, 3));
		input.put(5, new ItemStack(MarsItems.marsItemBasic, 1, 3));
		input.put(6, new ItemStack(MarsItems.marsItemBasic, 1, 3));
		input.put(7, new ItemStack(MarsItems.marsItemBasic, 1, 3));
		input.put(8, new ItemStack(MarsItems.marsItemBasic, 1, 3));
		input.put(9, new ItemStack(GCItems.partFins));
		input.put(10, new ItemStack(GCItems.partFins));
		input.put(11, new ItemStack(GCItems.rocketEngine));
		input.put(12, new ItemStack(GCItems.partFins));
		input.put(13, new ItemStack(GCItems.partFins));

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(14, new ItemStack(Blocks.chest));
		input2.put(15, null);
		input2.put(16, null);
		MarsUtil.adCargoRocketRecipe(new ItemStack(MarsItems.spaceship, 1, 11), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(14, null);
		input2.put(15, new ItemStack(Blocks.chest));
		input2.put(16, null);
		MarsUtil.adCargoRocketRecipe(new ItemStack(MarsItems.spaceship, 1, 11), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(14, null);
		input2.put(15, null);
		input2.put(16, new ItemStack(Blocks.chest));
		MarsUtil.adCargoRocketRecipe(new ItemStack(MarsItems.spaceship, 1, 11), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(14, new ItemStack(Blocks.chest));
		input2.put(15, new ItemStack(Blocks.chest));
		input2.put(16, null);
		MarsUtil.adCargoRocketRecipe(new ItemStack(MarsItems.spaceship, 1, 12), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(14, new ItemStack(Blocks.chest));
		input2.put(15, null);
		input2.put(16, new ItemStack(Blocks.chest));
		MarsUtil.adCargoRocketRecipe(new ItemStack(MarsItems.spaceship, 1, 12), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(14, null);
		input2.put(15, new ItemStack(Blocks.chest));
		input2.put(16, new ItemStack(Blocks.chest));
		MarsUtil.adCargoRocketRecipe(new ItemStack(MarsItems.spaceship, 1, 12), input2);

		input2 = new HashMap<Integer, ItemStack>(input);
		input2.put(14, new ItemStack(Blocks.chest));
		input2.put(15, new ItemStack(Blocks.chest));
		input2.put(16, new ItemStack(Blocks.chest));
		MarsUtil.adCargoRocketRecipe(new ItemStack(MarsItems.spaceship, 1, 13), input2);

		RecipeUtil.addRecipe(new ItemStack(MarsBlocks.machine, 1, BlockMachineMars.LAUNCH_CONTROLLER_METADATA), new Object[] { "ZVZ", "YXY", "ZWZ", 'V', new ItemStack(GCItems.basicItem, 1, 19), 'W', new ItemStack(GCBlocks.aluminumWire, 1, 0), 'X', new ItemStack(GCItems.basicItem, 1, 14), 'Y', "plateDesh", 'Z', "ingotDesh" });
	}
}
