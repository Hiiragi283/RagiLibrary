package hiiragi283.ragi_lib.main

import hiiragi283.ragi_lib.main.proxy.CommonProxy
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

//Modの定義
@Mod(
    modid = Reference.MOD_ID,
    name = Reference.MOD_NAME,
    version = Reference.VERSION,
    dependencies = Reference.DEPENDENCIES,
    acceptedMinecraftVersions = Reference.MC_VERSIONS
)
class RagiLibrary {

    companion object {
        //Proxyの定義
        @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
        var proxy: CommonProxy? = null
    }

    //Pre-Initializationの段階で呼ばれるevent
    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent?) {
        RagiLibraryInit.registerBlocks()
        RagiLibraryInit.registerEvents()
        RagiLibraryInit.registerItems()
        proxy!!.loadPreInit()
    }

    //Initializationの段階で呼ばれるevent
    @Mod.EventHandler
    fun init(event: FMLInitializationEvent?) {
        RagiLibraryInit.registerOreDict()
        proxy!!.loadInit()
    }

    //Post-Initializationの段階で呼ばれるevent
    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent?) {
        proxy!!.loadPostInit()
    }
}