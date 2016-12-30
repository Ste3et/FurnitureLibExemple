package de.Ste3et_C0st.FurnitureLibExemple.main;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import de.Ste3et_C0st.FurnitureLib.Events.PostFurnitureBreakEvent;
import de.Ste3et_C0st.FurnitureLib.Events.PostFurnitureClickEvent;
import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
import de.Ste3et_C0st.FurnitureLib.main.Type.SQLAction;

public class handler implements Listener{

	private ObjectID id;
	
	public handler(ObjectID id) {
		this.id = id;
		Bukkit.getPluginManager().registerEvents(this, main.instance);
	}
	
	@EventHandler
	public void onClick(PostFurnitureClickEvent e){
		if(id==null) return;
		if(e.getID()==null) return;
		if(!e.getID().equals(id)) return;
		if(e.getID().getSQLAction().equals(SQLAction.REMOVE)) return;
		e.getPlayer().sendMessage("Hallo :)");
	}
	
	@EventHandler
	public void onBreak(PostFurnitureBreakEvent e){
		if(id==null) return;
		if(e.getID()==null) return;
		if(!e.getID().equals(id)) return;
		if(e.getID().getSQLAction().equals(SQLAction.REMOVE)) return;
		e.setCancelled(true);
	}
}
