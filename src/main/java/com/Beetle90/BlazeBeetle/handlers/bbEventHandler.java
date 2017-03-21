package com.Beetle90.BlazeBeetle.handlers;

import com.Beetle90.BlazeBeetle.BlazeBeetle;
import com.Beetle90.BlazeBeetle.capabilities.Ideathloc;
import com.Beetle90.BlazeBeetle.capabilities.Ihomeloc;
import com.Beetle90.BlazeBeetle.capabilities.Inamestuff;
import com.Beetle90.BlazeBeetle.capabilities.deathProvider;
import com.Beetle90.BlazeBeetle.capabilities.homeProvider;
import com.Beetle90.BlazeBeetle.capabilities.namestuffProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class bbEventHandler {
	
public static final ResourceLocation DEATHLOC_CAP = new ResourceLocation(BlazeBeetle.MODID, "deathloc");
public static final ResourceLocation HOMELOC_CAP = new ResourceLocation(BlazeBeetle.MODID, "homeloc");
public static final ResourceLocation NAMESTUFF_CAP = new ResourceLocation(BlazeBeetle.MODID, "namestuff");
	
	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent.Entity event){
		if (!(event.getEntity() instanceof EntityPlayer)){
			return;
		}
		event.addCapability(DEATHLOC_CAP, new deathProvider());
		event.addCapability(HOMELOC_CAP, new homeProvider());
		event.addCapability(NAMESTUFF_CAP, new namestuffProvider());
	}
	
	@SubscribeEvent
    public void onPlayerLogsIn(PlayerLoggedInEvent event){
    	EntityPlayer player = event.player;
    	Ideathloc deathloc = player.getCapability(deathProvider.DEATHLOC_CAP, null);
    	Ihomeloc homeloc = player.getCapability(homeProvider.HOMELOC_CAP, null);
    	Inamestuff namestuff = player.getCapability(namestuffProvider.NAMESTUFF_CAP, null);
    }
	
	@SubscribeEvent
	public void copyDeathLoc(PlayerEvent.Clone event){
		if (event.getEntity() instanceof EntityPlayer && event.isWasDeath()){
			EntityPlayer player = event.getEntityPlayer();
			EntityPlayer oldPlayer = event.getOriginal();
			Ideathloc oldIdl = oldPlayer.getCapability(deathProvider.DEATHLOC_CAP, null);
			Ideathloc deathloc = player.getCapability(deathProvider.DEATHLOC_CAP, null);
			Ihomeloc oldIhl = oldPlayer.getCapability(homeProvider.HOMELOC_CAP,null);
			Ihomeloc homeloc = player.getCapability(homeProvider.HOMELOC_CAP, null);
			Inamestuff oldIns = oldPlayer.getCapability(namestuffProvider.NAMESTUFF_CAP, null);
			Inamestuff namestuff = player.getCapability(namestuffProvider.NAMESTUFF_CAP, null);
			
			deathloc.setLoc(oldIdl.getLoc().xCoord,oldIdl.getLoc().yCoord,oldIdl.getLoc().zCoord, oldIdl.getDim());
			homeloc.setHome(oldIhl.getHome().xCoord, oldIhl.getHome().yCoord, oldIhl.getHome().zCoord, oldIhl.getYaw(), oldIhl.getRot(), oldIhl.getDim());
			namestuff.initNameStuff(oldIns.getPrefix(), oldIns.getpCol(), oldIns.getNick(), oldIns.getnickCol(), oldIns.getSuffix(), oldIns.getsCol());
		}
	}
    
    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event){
    	if (event.getEntity() instanceof EntityPlayer){
    		EntityPlayer player = (EntityPlayer)event.getEntityLiving();
    		Ideathloc deathloc = player.getCapability(deathProvider.DEATHLOC_CAP, null);
    		deathloc.setLoc(player.getPositionVector().xCoord, player.getPositionVector().yCoord, player.getPositionVector().zCoord, player.dimension);
    	}
    }
}
