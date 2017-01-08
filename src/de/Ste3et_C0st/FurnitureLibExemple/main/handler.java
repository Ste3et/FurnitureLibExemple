package de.Ste3et_C0st.FurnitureLibExemple.main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import de.Ste3et_C0st.FurnitureLib.Events.PostFurnitureBreakEvent;
import de.Ste3et_C0st.FurnitureLib.Events.PostFurnitureClickEvent;
import de.Ste3et_C0st.FurnitureLib.Utilitis.LocationUtil;
import de.Ste3et_C0st.FurnitureLib.main.FurnitureHelper;
import de.Ste3et_C0st.FurnitureLib.main.FurnitureLib;
import de.Ste3et_C0st.FurnitureLib.main.FurnitureManager;
import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
import de.Ste3et_C0st.FurnitureLib.main.Type.SQLAction;
import de.Ste3et_C0st.FurnitureLib.main.entity.Relative;
import de.Ste3et_C0st.FurnitureLib.main.entity.fArmorStand;
import de.Ste3et_C0st.FurnitureLib.main.entity.fEntity;

public class handler extends FurnitureHelper implements Listener{

	//if you want to manipulate some ArmorStands you can use
	//id.getPacketList().get(<int>) or
	/*
		you can give your armorstands custom names and check it here in this plugin
	
	  	for(fEntity entity : id.getPacketList()){
			if(entity.getName().equalsIgnoreCase("")){
				doSomthing();
			}
		}
	
	 	You can spawn new ArmorStands with the function:
	 	FurnitureLib.getInstance().getFurnitureManager().createArmorStand(ObjectID, Location);
	 */
	
	public handler(ObjectID id) {
		super(id);

		//Register events to make this object useful
		
		Bukkit.getPluginManager().registerEvents(this, main.instance);
	}
	
	//The post events will be called befor the main event is called in this function i mean
	//PostFurnitureClickEvent will be called before FurnitureClickEvent
	@EventHandler
	public void onClick(PostFurnitureClickEvent e){
		if(getObjID()==null) return;
		if(e.getID()==null) return;
		if(!e.getID().equals(getObjID())) return;
		if(e.getID().getSQLAction().equals(SQLAction.REMOVE)) return;
		fEntity newEntity = null;
		for(fEntity entity : e.getID().getPacketList()){
			if(entity.getName().equalsIgnoreCase("test")){
				newEntity = entity;
				break;
			}
		}
		
		if(newEntity == null){
			FurnitureManager manager = FurnitureLib.getInstance().getFurnitureManager();
			Location middle = getCenter();
			Location newLocation = getRelative(middle, getBlockFace(), 0, 0).add(0, .2, 0);
			fArmorStand stand = manager.createArmorStand(e.getID(), newLocation);
			stand.setName("test");
			//You must be send the ArmorStand to all players who are in the radius
			update();
		}else{
			e.getPlayer().sendMessage("Hallo :)");
		}
		
	}
	
	@EventHandler
	public void onBreak(PostFurnitureBreakEvent e){
		if(getObjID()==null) return;
		if(e.getID()==null) return;
		if(!e.getID().equals(getObjID())) return;
		if(e.getID().getSQLAction().equals(SQLAction.REMOVE)) return;
		boolean b = true;
		for(fEntity entity : e.getID().getPacketList()){
			if(entity.getName().equalsIgnoreCase("test")){
				b = false;
				break;
			}
		}
		//You can set it cancelled
		e.setCancelled(b);
		if(b){
			e.getPlayer().sendMessage("You cannot destroy this ;)");
		}
		
	}
}
