package com.Beetle90.BlazeBeetle.capabilities;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.Vec3d;

public class homeloc implements Ihomeloc{

	private NBTTagCompound nbt = new NBTTagCompound();
	private int dim_id = -1000;
	private double x = 0;
	private double y = -1000;
	private double z = 0;
	private double yaw = 0;
	private double rot = 0;
	private Vec3d location = new Vec3d(x, y, z);

	@Override
	public void writeHome() {
		nbt.setDouble("x", x);
		nbt.setDouble("y", y);
		nbt.setDouble("z", z);
		nbt.setDouble("yaw", yaw);
		nbt.setDouble("rot", rot);
		nbt.setInteger("dim", dim_id);
	}

	@Override
	public void setHome(double x, double y, double z, double yaw, double rot, int dim_id) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.yaw = yaw;
		this.rot = rot;
		this.dim_id = dim_id;
		location = new Vec3d(x, y, z);
	}

	@Override
	public Vec3d getHome() {
		return location;
	}

	@Override
	public NBTTagCompound getNBTComp() {
		return nbt;
	}

	@Override
	public double getYaw() {
		return yaw;
	}

	@Override
	public double getRot() {
		return rot;
	}

	@Override
	public int getDim() {
		return dim_id;
	}
	
}
