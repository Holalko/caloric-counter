package cz.vsb.jakhol.caloriccounter.stores;

import cz.vsb.jakhol.caloriccounter.models.User;
import cz.vsb.jakhol.caloriccounter.models.enums.GoalState;

public class UserStore {

    private static User user;

    public static User getUser() {
        if (user == null) {
            user = new User("Nick", 75.5, 80, 180, 20, GoalState.GAIN);
        }
        return user;

    }

}
