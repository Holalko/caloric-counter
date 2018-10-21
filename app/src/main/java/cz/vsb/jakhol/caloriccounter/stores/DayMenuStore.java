package cz.vsb.jakhol.caloriccounter.stores;

import cz.vsb.jakhol.caloriccounter.models.DayMenu;

public class DayMenuStore {

    private static DayMenu dayMenu;

    public static DayMenu getDayMenu(){
        if(dayMenu == null){
            dayMenu = new DayMenu();
        }
        return dayMenu;
    }


}
