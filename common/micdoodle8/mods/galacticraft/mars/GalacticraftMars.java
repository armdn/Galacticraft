package micdoodle8.mods.galacticraft.mars;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.core.GCCoreCreativeTab;
import micdoodle8.mods.galacticraft.core.GCLog;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.network.GCCoreConnectionHandler;
import micdoodle8.mods.galacticraft.core.network.GCCorePacketManager;
import micdoodle8.mods.galacticraft.core.util.PacketUtil;
import micdoodle8.mods.galacticraft.mars.blocks.GCMarsBlocks;
import micdoodle8.mods.galacticraft.mars.dimension.GCMarsWorldProvider;
import micdoodle8.mods.galacticraft.mars.entities.GCCoreEntityRocketT2;
import micdoodle8.mods.galacticraft.mars.items.GCMarsItems;
import micdoodle8.mods.galacticraft.moon.dimension.GCMoonTeleportType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.ServerStarted;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;

/**
 * Copyright 2012-2013, micdoodle8
 * 
 * All rights reserved.
 * 
 */
@Mod(name = GalacticraftMars.NAME, version = GalacticraftCore.LOCALMAJVERSION + "." + GalacticraftCore.LOCALMINVERSION + "." + GalacticraftCore.LOCALBUILDVERSION, useMetadata = true, modid = GalacticraftMars.MODID, dependencies = "required-after:Forge@[8.9.0.771,)")
@NetworkMod(channels = { GalacticraftMars.CHANNEL }, clientSideRequired = true, serverSideRequired = false, connectionHandler = GCCoreConnectionHandler.class, packetHandler = GCCorePacketManager.class)
public class GalacticraftMars
{
    public static final String NAME = "Galacticraft Mars";
    public static final String MODID = "GalacticraftMars";
    public static final String CHANNEL = "GalacticraftMars";
    public static final String CHANNELENTITIES = "GCMarsEntities";

    public static final String LANGUAGE_PATH = "/assets/galacticraftmars/lang/";
    private static final String[] LANGUAGES_SUPPORTED = new String[] { "en_US", "fi_FI", "ru_RU" };

    @SidedProxy(clientSide = "micdoodle8.mods.galacticraft.mars.client.ClientProxyMars", serverSide = "micdoodle8.mods.galacticraft.mars.CommonProxyMars")
    public static CommonProxyMars proxy;

    @Instance(GalacticraftMars.MODID)
    public static GalacticraftMars instance;

    public static GCCoreCreativeTab galacticraftMarsTab;

    public static final String TEXTURE_DOMAIN = "galacticraftmars";
    public static final String TEXTURE_PREFIX = GalacticraftMars.TEXTURE_DOMAIN + ":";

    public static Fluid SLUDGE;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        new GCMarsConfigManager(new File(event.getModConfigurationDirectory(), "Galacticraft/mars.conf"));

        GalacticraftMars.SLUDGE = new Fluid("bacterialsludge").setBlockID(GCMarsConfigManager.idBlockBacterialSludge).setViscosity(3000);
        if (!FluidRegistry.registerFluid(GalacticraftMars.SLUDGE))
        {
            GCLog.info("\"bacterialsludge\" has already been registered as a fluid, ignoring...");
        }
        
        GCMarsBlocks.initBlocks();
        GCMarsBlocks.registerBlocks();
        GCMarsBlocks.setHarvestLevels();

        GCMarsItems.initItems();

        GalacticraftMars.proxy.preInit(event);
    }

    @EventHandler
    public void load(FMLInitializationEvent event)
    {

        int languages = 0;

        for (String language : GalacticraftMars.LANGUAGES_SUPPORTED)
        {
            LanguageRegistry.instance().loadLocalization(GalacticraftMars.LANGUAGE_PATH + language + ".lang", language, false);

            if (LanguageRegistry.instance().getStringLocalization("children", language) != "")
            {
                try
                {
                    String[] children = LanguageRegistry.instance().getStringLocalization("children", language).split(",");

                    for (String child : children)
                    {
                        if (child != "" || child != null)
                        {
                            LanguageRegistry.instance().loadLocalization(GalacticraftMars.LANGUAGE_PATH + language + ".lang", child, false);
                            languages++;
                        }
                    }
                }
                catch (Exception e)
                {
                    FMLLog.severe("Failed to load a child language file.");
                    e.printStackTrace();
                }
            }

            languages++;
        }

        GCLog.info("Galacticraft Mars Loaded: " + languages + " Languages.");
        
        GalacticraftMars.galacticraftMarsTab = new GCCoreCreativeTab(CreativeTabs.getNextID(), GalacticraftMars.MODID, GCMarsItems.spaceship.itemID, 5);
        MinecraftForge.EVENT_BUS.register(new GCMarsEvents());
        GalacticraftRegistry.registerTeleportType(GCMarsWorldProvider.class, new GCMoonTeleportType());
        GCMarsUtil.addSmeltingRecipes();
        GalacticraftRegistry.registerCelestialBody(new GCMarsPlanet());
        this.registerTileEntities();
        this.registerCreatures();
        this.registerOtherEntities();
        GalacticraftMars.proxy.init(event);
        // SchematicRegistry.registerSchematicRecipe(new
        // GCMarsSchematicRocketT2());
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
    }

    public void registerTileEntities()
    {

    }

    public void registerCreatures()
    {
        // this.registerGalacticraftCreature(GCMarsEntityCreeperBoss.class,
        // "Creeper Boss", GCMarsConfigManager.idEntityCreeperBoss, 894731, 0);
    }

    public void registerOtherEntities()
    {
        // this.registerGalacticraftNonMobEntity(GCMarsEntityProjectileTNT.class,
        // "Projectile TNT", GCMarsConfigManager.idEntityProjectileTNT, 150, 5,
        // true);
        this.registerGalacticraftNonMobEntity(GCCoreEntityRocketT2.class, "SpaceshipT2", GCMarsConfigManager.idEntitySpaceshipTier2, 150, 1, true);
    }

    @PostInit
    public void postLoad(FMLPostInitializationEvent event)
    {
        GalacticraftMars.proxy.postInit(event);
        GalacticraftMars.proxy.registerRenderInformation();
        GCMarsUtil.addCraftingRecipes();
    }

    @ServerStarted
    public void serverInit(FMLServerStartedEvent event)
    {
        NetworkRegistry.instance().registerChannel(new ServerPacketHandler(), "GalacticraftMars", Side.SERVER);
    }

    public void registerGalacticraftCreature(Class var0, String var1, int id, int back, int fore)
    {
        EntityRegistry.registerGlobalEntityID(var0, var1, id, back, fore);
        EntityRegistry.registerModEntity(var0, var1, id, GalacticraftMars.instance, 80, 3, true);
        LanguageRegistry.instance().addStringLocalization("entity." + var1 + ".name", "en_US", var1);
    }

    public void registerGalacticraftNonMobEntity(Class var0, String var1, int id, int trackingDistance, int updateFreq, boolean sendVel)
    {
        EntityRegistry.registerModEntity(var0, var1, id, this, trackingDistance, updateFreq, sendVel);
    }

    public class ServerPacketHandler implements IPacketHandler
    {
        @Override
        public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player p)
        {
            final DataInputStream data = new DataInputStream(new ByteArrayInputStream(packet.data));
            final int packetType = PacketUtil.readPacketID(data);
            if (packetType == 0)
            {

            }
        }
    }
}