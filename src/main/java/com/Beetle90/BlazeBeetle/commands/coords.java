package com.Beetle90.BlazeBeetle.commands;

import java.util.Collections;
import java.util.List;

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

public class coords extends CommandBase{

	@Override
	public String getCommandName() {
		return "coords";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "Provides the player's coordinates to the server or another player.";
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
			
			if (args.length == 0){
				player.addChatMessage(new TextComponentString(TextFormatting.RED + "Error: Please use /coords <all/whisper> <player name>."));
			}
			
			if (args.length == 1){
				if (args[0].equalsIgnoreCase("all")){
					TextComponentString text = new TextComponentString(TextFormatting.YELLOW + "<" + player.getDisplayNameString() + "> I'm at x=" + (int)(player.getPositionVector().xCoord) + " z=" + (int)(player.getPositionVector().zCoord) + " y=" + (int)(player.getPositionVector().yCoord) + " in " + dim_string);
					sendGlobalMsg(text, server);
				}
			}
			else if (args.length == 2 && (args[0].equalsIgnoreCase("whisper") || args[0].equalsIgnoreCase("w"))){
				String[] playerList = server.getAllUsernames();
				for (int i = 0; i < playerList.length; i++){
					if (args[1].equalsIgnoreCase(playerList[i])){
						EntityPlayer receiver = server.getEntityWorld().getPlayerEntityByName(playerList[i]);
						TextComponentString text = new TextComponentString(TextFormatting.GRAY + "<" + player.getDisplayNameString() + "> I'm at x=" + (int)(player.getPositionVector().xCoord) + " z=" + (int)(player.getPositionVector().zCoord) + " y=" + (int)(player.getPositionVector().yCoord) + " in " + dim_string);
						if (player != receiver){
							player.addChatMessage(text);
							receiver.addChatMessage(text);
						}
						else{
							player.addChatMessage(text);
						}
					}
				}
			}
		}
	}
	
	@Override
    public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos)
    {
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, new String[] {"all", "whisper"}): (args.length == 2 && args[0].equalsIgnoreCase("whisper") ? getListOfStringsMatchingLastWord(args, server.getAllUsernames()) : Collections.<String>emptyList());
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
		PlayerList list = server.getPlayerList();
		list.sendChatMsg(msg);
		/*String[] onlinePlayers = server.getAllUsernames();
		for (int i = 0; i < onlinePlayers.length; i++){
			EntityPlayer plyr = list.getPlayerByUsername(onlinePlayers[i]);
			if (plyr != null){
				plyr.addChatMessage(msg);
			}
		}*/
	}

}
