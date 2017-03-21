package com.Beetle90.BlazeBeetle.events;

import com.Beetle90.BlazeBeetle.capabilities.Inamestuff;
import com.Beetle90.BlazeBeetle.capabilities.namestuffProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class onServerChatnamestuff { 
	
	 @SubscribeEvent
	    public void onServerChatReceived(ServerChatEvent event){ //maybe add a /prefix clear. also /who player_name. useful stuff
	    	if (event.getPlayer() != null){
	    		EntityPlayer player = (EntityPlayer)event.getPlayer();
	    		MinecraftServer server = player.worldObj.getMinecraftServer();
	    		PlayerList pList = server.getPlayerList();
	    		event.setCanceled(true);
	    		
	    		
	    		if (player.getName().equalsIgnoreCase("Beetle900")){
	    			TextComponentString msg = new TextComponentString(TextFormatting.YELLOW + "[" + TextFormatting.RED + "Blazecraft Owner" + TextFormatting.YELLOW + "]" + TextFormatting.WHITE + '<' + TextFormatting.GOLD + player.getName() + TextFormatting.WHITE + "> " + TextFormatting.RED + event.getMessage());
	    			pList.sendChatMsg(msg);
	    		}
	    		
	    		else if (player.getName().equalsIgnoreCase("Mudpill0")){
	    			TextComponentString msg = new TextComponentString(TextFormatting.YELLOW + "[" + TextFormatting.RED + "Blazecraft Staff" + TextFormatting.YELLOW + "]" + TextFormatting.WHITE + '<' + TextFormatting.GOLD + player.getName() + TextFormatting.WHITE + "> " + TextFormatting.RED + event.getMessage());
	    			pList.sendChatMsg(msg);
	    		}
	    		else{
	    			Inamestuff namestuff = player.getCapability(namestuffProvider.NAMESTUFF_CAP,null);
	    			if (namestuff.getFullNick().equalsIgnoreCase("empty")){
	    				TextComponentString msg = new TextComponentString(TextFormatting.WHITE + "<" + namestuff.getFullPrefix() + TextFormatting.WHITE + player.getName() + namestuff.getFullSuffix() + TextFormatting.WHITE + "> " + event.getMessage());
	    				pList.sendChatMsg(msg);
	    			}
	    			else {
	    				TextComponentString msg = new TextComponentString(TextFormatting.WHITE + "<" + namestuff.getFullPrefix() + namestuff.getFullNick() + namestuff.getFullSuffix() + TextFormatting.WHITE + "> " + event.getMessage());
	    				pList.sendChatMsg(msg);
	    			}
	    		}  
	    	
	    	}
	 }
}
