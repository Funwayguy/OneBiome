package onebiome.core;

import net.minecraftforge.common.config.Configuration;
import onebiome.core.proxies.CommonProxy;
import onebiome.handlers.ConfigHandler;
import org.apache.logging.log4j.Logger;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(modid = OneBiome.MODID, version = OneBiome.VERSION, name = OneBiome.NAME, guiFactory = "onebiome.handlers.ConfigGuiFactory")
public class OneBiome
{
    public static final String MODID = "onebiome";
    public static final String VERSION = "OB_VER_KEY";
    public static final String NAME = "OneBiome";
    public static final String PROXY = "onebiome.core.proxies";
    public static final String CHANNEL = "OB_NET_CHAN";
	
	@Instance(MODID)
	public static OneBiome instance;
	
	@SidedProxy(clientSide = PROXY + ".ClientProxy", serverSide = PROXY + ".CommonProxy")
	public static CommonProxy proxy;
	public SimpleNetworkWrapper network;
	public static Logger logger;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	logger = event.getModLog();
    	network = NetworkRegistry.INSTANCE.newSimpleChannel(CHANNEL);
    	ConfigHandler.config = new Configuration(event.getSuggestedConfigurationFile(), true);
    	ConfigHandler.initConfigs();
    	
    	proxy.registerHandlers();
    	
    	
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    }
}
