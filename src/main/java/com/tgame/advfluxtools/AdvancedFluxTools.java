package com.tgame.advfluxtools;

import com.tgame.advfluxtools.blocks.BlockChargePlatform;
import com.tgame.advfluxtools.blocks.TileChargePlatform;
import com.tgame.advfluxtools.blocks.itemblocks.ItemBlockMetadata;
import com.tgame.advfluxtools.entities.EntityLaserProjectile;
import com.tgame.advfluxtools.items.ItemLaserDrill;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;

/**
 * @author tgame14
 * @since 29/04/14
 */
@Mod(modid = Settings.ID, name = Settings.NAME, version = Settings.VERSION)
public class AdvancedFluxTools
{
	@Mod.Instance(Settings.ID)
	public static AdvancedFluxTools instance;

	@SidedProxy(clientSide = "com.tgame.advfluxtools.ClientProxy", serverSide = "com.tgame.advflux.tools.CommonProxy")
	public static CommonProxy proxy;

	@Mod.Metadata
	public static ModMetadata metadata;

	public static Item itemLaserDrill;

	public static Block blockChargePlatform;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Settings.CONFIGURATION = new Configuration(event.getSuggestedConfigurationFile());

		itemLaserDrill = new ItemLaserDrill(Settings.CONFIGURATION.getItem(ItemLaserDrill.class.getSimpleName(), 22040).getInt());
		GameRegistry.registerItem(itemLaserDrill, itemLaserDrill.getClass().getSimpleName());

		blockChargePlatform = new BlockChargePlatform(Settings.CONFIGURATION.getBlock(BlockChargePlatform.class.getSimpleName(), 2040).getInt());
		GameRegistry.registerBlock(blockChargePlatform, ItemBlockMetadata.class, blockChargePlatform.getClass().getSimpleName());

		GameRegistry.registerTileEntity(TileChargePlatform.class, TileChargePlatform.class.getSimpleName());

		EntityRegistry.registerGlobalEntityID(EntityLaserProjectile.class, EntityLaserProjectile.class.getSimpleName(), 0);

	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		metadata.authorList.add("@AUTHOR@");
		metadata.credits = "@AUTHOR@, and wanting better content from mods";
		metadata.description = "A Mod that adds Advanced tools and machinery";
		metadata.modId = Settings.ID;
		metadata.version = Settings.VERSION;
		metadata.name = Settings.NAME;

		metadata.autogenerated = false;

		proxy.registerRenderers();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		// RECIPES

		ItemStack leadConduit = GameRegistry.findItemStack("ThermalExpansion", "conduitEnergyBasic", 1);
		ItemStack leadEnergy = GameRegistry.findItemStack("ThermalExpansion", "cellBasic", 1);

		ItemStack hardenedConduit = GameRegistry.findItemStack("ThermalExpansion", "conduitEnergyHardened", 1);
		ItemStack hardenedEnergy = GameRegistry.findItemStack("ThermalExpansion", "cellHardened", 1);

		ItemStack redsConduit = GameRegistry.findItemStack("ThermalExpansion", "conduitEnergyReinforced", 1);
		ItemStack redsEnergy = GameRegistry.findItemStack("ThermalExpansion", "cellReinforced", 1);

		ItemStack ppIron = new ItemStack(Block.pressurePlateIron);

		GameRegistry.addShapedRecipe(new ItemStack(blockChargePlatform, 1, 0), "CPC", "PEP", "CPC", 'C', leadConduit, 'P', ppIron, 'E', leadEnergy);
		GameRegistry.addShapedRecipe(new ItemStack(blockChargePlatform, 1, 1), "CPC", "PEP", "CPC", 'C', hardenedConduit, 'P', ppIron, 'E', hardenedEnergy);
		GameRegistry.addShapedRecipe(new ItemStack(blockChargePlatform, 1, 2), "CPC", "PEP", "CPC", 'C', redsConduit, 'P', ppIron, 'E', redsEnergy);


	}

}
