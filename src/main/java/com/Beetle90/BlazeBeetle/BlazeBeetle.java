package com.Beetle90.BlazeBeetle;

import com.Beetle90.BlazeBeetle.capabilities.Ideathloc;
import com.Beetle90.BlazeBeetle.capabilities.Ihomeloc;
import com.Beetle90.BlazeBeetle.capabilities.Inamestuff;
import com.Beetle90.BlazeBeetle.capabilities.deathStorage;
import com.Beetle90.BlazeBeetle.capabilities.deathloc;
import com.Beetle90.BlazeBeetle.capabilities.homeStorage;
import com.Beetle90.BlazeBeetle.capabilities.homeloc;
import com.Beetle90.BlazeBeetle.capabilities.namestuff;
import com.Beetle90.BlazeBeetle.capabilities.namestuffStorage;
import com.Beetle90.BlazeBeetle.commands.RegisterBlazeCommands;
import com.Beetle90.BlazeBeetle.events.onServerChatnamestuff;
import com.Beetle90.BlazeBeetle.handlers.bbEventHandler;
import net.minecraft.command.ServerCommandManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = BlazeBeetle.MODID, version = BlazeBeetle.VERSION)
public class BlazeBeetle
{
    public static final String MODID = "blazebeetle";
    public static final String VERSION = "0.0.6";
    
    // /prefix/suffix/nick/nickname player text color
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
    	CapabilityManager.INSTANCE.register(Ideathloc.class,new deathStorage(),deathloc.class);
    	CapabilityManager.INSTANCE.register(Ihomeloc.class,new homeStorage(),homeloc.class);
    	CapabilityManager.INSTANCE.register(Inamestuff.class,new namestuffStorage(),namestuff.class);
    	MinecraftForge.EVENT_BUS.register(new bbEventHandler());
    	MinecraftForge.EVENT_BUS.register(new onServerChatnamestuff());
    }
    
    @EventHandler
    public void serverStart(FMLServerStartingEvent event){
    	ServerCommandManager manager = (ServerCommandManager) event.getServer().getCommandManager();
    	new RegisterBlazeCommands(manager);
    }
}
