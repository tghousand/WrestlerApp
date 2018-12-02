package tghousand.finalproject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tyler on 5/1/2018.
 */

public class WrestlerInformation{

    public HashMap wrestler;

    WrestlerInformation(String name, String gender, String heightFeet, String heightInches, String weight, String homeTown, String state, String country, int picture){
        wrestler = new HashMap();
        wrestler.put("name", name);
        wrestler.put("gender", gender);
        wrestler.put("feet", heightFeet);
        wrestler.put("inches", heightInches);
        wrestler.put("weight", weight);
        wrestler.put("hometown", homeTown);
        wrestler.put("state", state);
        wrestler.put("country", country);
        wrestler.put("picture", picture);
    }

    WrestlerInformation(){

    }

    public HashMap getMap(){
        return wrestler;
    }


}
