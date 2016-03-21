package de.Ste3et_C0st.FurnitureLibExemple.main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import de.Ste3et_C0st.FurnitureLib.Events.FurnitureBreakEvent;
import de.Ste3et_C0st.FurnitureLib.Events.FurnitureClickEvent;
import de.Ste3et_C0st.FurnitureLib.main.Furniture;
import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
import de.Ste3et_C0st.FurnitureLib.main.Type.SQLAction;
import de.Ste3et_C0st.FurnitureLib.main.entity.Relative;
import de.Ste3et_C0st.FurnitureLib.main.entity.fArmorStand;

public class test extends Furniture implements Listener{

	/*
	 * you must be checkd if the Furniture load from the database or is a new Furniture and registred events
	 * it is a new Furniture you must be triggerd the spawn Function.
	 * 
	 * */
	public test(ObjectID id) {
		super(id);
		if(id.isFinish()){
			Bukkit.getPluginManager().registerEvents(this, main.instance);
			return;
		}
		spawn(id.getStartLocation());
	}

	
	public void spawn(Location loc) {
		fArmorStand stand = spawnArmorStand(loc);
		stand.setBasePlate(false).setMarker(true).setHeadPose(getLutil().degresstoRad(new EulerAngle(0, 45, 0)));
		stand.setName("TestArmorStand").setNameVasibility(true).setItemInMainHand(new ItemStack(Material.STICK));
		fArmorStand stand2 = stand.clone(new Relative(stand.getLocation(), 0, 1, 0, getBlockFace()));
		stand2.setBasePlate(true).setArms(true).setNameVasibility(false);
		send();
	}

	@EventHandler
	public void onFurnitureBreak(FurnitureBreakEvent e) {
		if(getObjID()==null){return;}
		if(getObjID().getSQLAction().equals(SQLAction.REMOVE)){return;}
		if(e.isCancelled()){return;}
		if(e.getID() == null || getObjID() == null) return;
		if(!e.getID().equals(getObjID())){return;}
		if(!canBuild(e.getPlayer())){return;}
		e.remove();
		delete();
	}

	@EventHandler
	public void onFurnitureClick(FurnitureClickEvent e) {
		
	}

}
