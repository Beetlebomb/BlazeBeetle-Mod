package com.Beetle90.BlazeBeetle.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class bb extends CommandBase {

	@Override
	public String getCommandName() {
		return "bb";
	}
	
	@Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }
	
	@Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
    }

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "Displays a list of commands for the BlazeBeetle mod";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (sender instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)sender;
			
			if (args.length == 0){
				player.addChatMessage(new TextComponentString(TextFormatting.RED + "BlazeBeetle commands: /coords, /dcoords, /home, /nickname, /prefix, and /suffix"));
			}
			else if (args.length == 1 && args[0].equalsIgnoreCase("help")){
				player.addChatMessage(new TextComponentString(TextFormatting.RED + "BlazeBeetle commands: /coords, /dcoords, /home, /nickname, /prefix, and /suffix"));
			}
		}
	}

}
