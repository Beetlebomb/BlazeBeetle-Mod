package com.Beetle90.BlazeBeetle.commands;

import net.minecraft.command.ServerCommandManager;

public class RegisterBlazeCommands {
	public RegisterBlazeCommands(ServerCommandManager manager) {
		manager.registerCommand(new bb());
		manager.registerCommand(new coords());
    	manager.registerCommand(new dcoords());
    	manager.registerCommand(new home());
    	manager.registerCommand(new prefix());
    	manager.registerCommand(new suffix());
    	manager.registerCommand(new nickname());
	}
}
