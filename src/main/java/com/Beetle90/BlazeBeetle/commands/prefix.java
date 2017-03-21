package com.Beetle90.BlazeBeetle.commands;

import java.util.Collections;
import java.util.List;

import com.Beetle90.BlazeBeetle.capabilities.Ideathloc;
import com.Beetle90.BlazeBeetle.capabilities.Inamestuff;
import com.Beetle90.BlazeBeetle.capabilities.deathProvider;
import com.Beetle90.BlazeBeetle.capabilities.namestuffProvider;
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

public class prefix extends CommandBase {

	@Override
	public String getCommandName() {
		return "prefix";
	}
	
	@Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "Applies a prefix to the targeted player with the option to use color. /prefix player_name prefix_text color";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (sender instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)sender;

			if (args.length == 0 || args.length == 1){
				player.addChatMessage(new TextComponentString(TextFormatting.RED + "Error: Please use /prefix <player name> <prefix_text> optional:<color>"));
			}
			else if (args.length == 2){ //aka no color specified.
				PlayerList pList = server.getPlayerList();
				EntityPlayer affectedP = pList.getPlayerByUsername(args[0]);
				if (affectedP != null){
					Inamestuff namestuff = affectedP.getCapability(namestuffProvider.NAMESTUFF_CAP,null);
					System.out.println(args[1].toString());
					if (args[1] != null && !args[1].equalsIgnoreCase("clear")){
						namestuff.setPrefix(args[1].toString());
					}
					else if (args[1].equalsIgnoreCase("clear")){
						namestuff.setPrefix("empty");
					}
				}
			}
			else if (args.length == 3){ //aka color specified.
				PlayerList pList = server.getPlayerList();
				EntityPlayer affectedP = pList.getPlayerByUsername(args[0]);
				if (affectedP != null){
					Inamestuff namestuff = affectedP.getCapability(namestuffProvider.NAMESTUFF_CAP,null);
					namestuff.setPrefix(args[1].toString(),args[2].toString()); //MAY NOT WORK WITH ARGS[2] NO CHECK ON 3rd ARGUMENT.
				}
			}
		}
	}
	
	@Override
    public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos)
    {
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, server.getAllUsernames()) : args.length == 2 ? getListOfStringsMatchingLastWord(args, "clear") : (args.length == 3 ? getListOfStringsMatchingLastWord(args, "Aqua","Black","Blue","Dark_Aqua","Dark_Blue","Dark_Gray","Dark_Green","Dark_Purple","Dark_Red","Gold","Gray","Green","Light_Purple","Red","White","Yellow") : Collections.<String>emptyList());
    }

}
