package com.Beetle90.BlazeBeetle.commands;

import java.util.Collections;
import java.util.List;

import com.Beetle90.BlazeBeetle.capabilities.Ideathloc;
import com.Beetle90.BlazeBeetle.capabilities.deathProvider;
import jline.internal.Nullable;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class dcoords extends CommandBase{

	@Override
	public String getCommandName() {
		return "dcoords";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "Provides the player's death coordinates to the server or another player.";
	}
	
	@Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }
	
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender){
		return true;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (sender instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)sender;
			int dim_id = player.dimension;
			String dim_string = getDimName(dim_id);
			Ideathloc deathloc = player.getCapability(deathProvider.DEATHLOC_CAP,null);
			String Cdim_string = getDimName(deathloc.getDim());
			
			if (args.length == 0){
				player.addChatMessage(new TextComponentString(TextFormatting.RED + "Error: Please use /dcoords <all/whisper> <player name>."));
			}
			
			if (args.length == 1){
				if (args[0].equalsIgnoreCase("all")){
					if (hasNeverDied(deathloc)){
						player.addChatMessage(new TextComponentString(TextFormatting.YELLOW + "I haven't died yet."));
					}
					else {
						TextComponentString deathText = new TextComponentString(TextFormatting.YELLOW + "<" + player.getDisplayNameString() + "> I died at x=" + (int)deathloc.getLoc().xCoord + " z=" + (int)deathloc.getLoc().zCoord + " y=" + (int)deathloc.getLoc().yCoord + " in " + Cdim_string);
						sendGlobalMsg(deathText, server);
					}
					
				}
			}
			else if (args.length == 2 && (args[0].equalsIgnoreCase("whisper") || args[0].equalsIgnoreCase("w"))){
				if (hasNeverDied(deathloc)){
					player.addChatMessage(new TextComponentString(TextFormatting.YELLOW + "I haven't died yet."));
				}
				else{
					String[] playerList = server.getAllUsernames();
					for (int i = 0; i < playerList.length; i++){
						if (args[1].equalsIgnoreCase(playerList[i])){
							EntityPlayer receiver = server.getEntityWorld().getPlayerEntityByName(playerList[i]);
							TextComponentString deathText = new TextComponentString(TextFormatting.GRAY + "<" + player.getDisplayNameString() + "> I died at x=" + (int)deathloc.getLoc().xCoord + " z=" + (int)deathloc.getLoc().zCoord + " y=" + (int)deathloc.getLoc().yCoord + " in " + Cdim_string);
							if (player != receiver){
								player.addChatMessage(deathText);
								receiver.addChatMessage(deathText);
							}
							else{
								player.addChatMessage(deathText);
							}
						}
					}
				}
			}
		}
	}
	
	@Override
    public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos)
    {
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, new String[] {"all", "whisper"}): (args.length == 2 ? getListOfStringsMatchingLastWord(args, server.getAllUsernames()) : Collections.<String>emptyList());
    }
	
	private String getDimName(int dim_id){
		String dim_string = null;
		switch (dim_id){
		case -1: dim_string = "The Nether";
		break;
		case 0: dim_string = "The Overworld";
		break;
		case 1: dim_string = "The End";
		break;
		default: dim_string = "Dimension " + dim_id;
		}
		return dim_string;
	}
	
	private void sendGlobalMsg(TextComponentString msg, MinecraftServer server){
		String[] onlinePlayers = server.getAllUsernames();
		for (int i = 0; i < onlinePlayers.length; i++){
			PlayerList list = server.getPlayerList();
			EntityPlayer plyr = list.getPlayerByUsername(onlinePlayers[i]);
			if (plyr != null){
				plyr.addChatMessage(msg);
			}
		}
	}
	
	private boolean hasNeverDied(Ideathloc deathloc){
		if (deathloc.getLoc().xCoord == 0 && deathloc.getLoc().yCoord == -1000 && deathloc.getLoc().zCoord == 0){
			return true;
		}
		return false;
	}

}
