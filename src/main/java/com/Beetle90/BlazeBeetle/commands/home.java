package com.Beetle90.BlazeBeetle.commands;


import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import com.Beetle90.BlazeBeetle.capabilities.Ihomeloc;
import com.Beetle90.BlazeBeetle.capabilities.homeProvider;
import jline.internal.Nullable;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class home extends CommandBase{

	@Override
	public String getCommandName() {
		return "home";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "Create a home location to teleport.";
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
			final EntityPlayer player = (EntityPlayer)sender;
			final Vec3d origPos = player.getPositionVector();
			int dim_id = player.dimension;
			final Ihomeloc homeloc = player.getCapability(homeProvider.HOMELOC_CAP,null);
			
			if (args.length == 0 && noHome(homeloc)){
				player.addChatMessage(new TextComponentString(TextFormatting.RED + "Error: Home has not been set! Please use /home set"));
			}
			else if (args.length == 0){
				
				if (player != null)
	            {
					player.addChatComponentMessage(new TextComponentString(TextFormatting.YELLOW + "Going home. Do not move for 5 seconds."));
					final Timer timer = new Timer();
					final TimerTask task = new TimerTask() {
						int countdown = 100;
						public void run() {
							countdown--;
							if (countdown <= 0){
								doTeleport(player, homeloc.getHome().xCoord, homeloc.getHome().yCoord, homeloc.getHome().zCoord, homeloc.getYaw(), homeloc.getRot());
								timer.cancel();
								timer.purge();
							}
							else if (((int)player.getPositionVector().xCoord != (int)origPos.xCoord) || ((int)player.getPositionVector().yCoord != (int)origPos.yCoord) || ((int)player.getPositionVector().zCoord != (int)origPos.zCoord)){
								player.addChatMessage(new TextComponentString(TextFormatting.RED + "Teleportation interrupted! You moved."));
								timer.cancel();
								timer.purge();
							}
						}
					};
					startCountdown(timer, task);
	            }
			}
			else if (args.length == 1 && args[0].equalsIgnoreCase("set")){
				homeloc.setHome(player.getPositionVector().xCoord, player.getPositionVector().yCoord, player.getPositionVector().zCoord, (double)player.rotationYaw, (double)player.rotationPitch, player.dimension);
				player.addChatComponentMessage(new TextComponentString(TextFormatting.YELLOW + "Home has been set."));
			}
		}
		
	}
	
	private void startCountdown(Timer timer, TimerTask task) {
		timer.scheduleAtFixedRate(task, 0, 50);
	}

	private void doTeleport(EntityPlayer player, double xCoord, double yCoord, double zCoord, double yaw, double rot) {
		
		Set<SPacketPlayerPosLook.EnumFlags> set = EnumSet.<SPacketPlayerPosLook.EnumFlags>noneOf(SPacketPlayerPosLook.EnumFlags.class);

		player.dismountRidingEntity();
        ((EntityPlayerMP)player).connection.setPlayerLocation(xCoord, yCoord, zCoord, (float)yaw, (float)rot, set);
        player.setRotationYawHead((float)yaw);
	}

	private boolean noHome(Ihomeloc homeloc){
		if (homeloc.getHome().xCoord == 0 && homeloc.getHome().yCoord == -1000 && homeloc.getHome().zCoord == 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	@Override
    public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos)
    {
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, new String[] {"set"}) : Collections.<String>emptyList();
    }
}
