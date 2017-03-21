package com.Beetle90.BlazeBeetle.capabilities;

import net.minecraft.nbt.NBTTagCompound;

public interface Inamestuff { //prefix and/or color, nickname and/or color, suffix and/or color, and set all colors.
	public void setPrefix(String prefix);
	public void setPrefix(String prefix, String color);
	public String getPrefix();
	public String getpCol();
	public String getFullPrefix();
	public void setNickname(String nick);
	public void setNickname(String nick, String color);
	public String getNick();
	public String getnickCol();
	public String getFullNick();
	public void setSuffix(String suffix);
	public void setSuffix(String suffix, String color);
	public String getSuffix();
	public String getsCol();
	public String getFullSuffix();
	public void setAllColors(String color);
	public void initNameStuff(String prefix, String pCol, String nickname, String nickCol, String suffix, String sCol);
	public NBTTagCompound getNBTComp();
}
