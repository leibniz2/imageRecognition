package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by RRosall on 12/20/2015.
 */
public class Person_Identifier {
    private String[] names = {"Andrew Garfield","Candice Swanepoel","Cara de Levigne","Chrissy Costanza"
                                ,"Joseph Gordon Levitt","Junshean Espinosa","Keira Knightley","Kendall Jenner"
                                ,"Lee Jung Suk","Legolas","Lenka","Mario Maurer","Michelle Monaghan","Robert Downey Jr.","Roger Dupe"};
    private HashMap<String, Double> map = new HashMap<String, Double>();



    public ArrayList<String> getNames(){
        ArrayList<Double> values = new ArrayList<Double>();
        for(String b:map.keySet()){
            values.add(map.get(b));
        }
        Collections.sort(values);
        ArrayList<String> matched = new ArrayList<String>();

        for (int i =14; i >= 12; i--) {
            for(String b:map.keySet()){
                if(map.get(b).equals(values.get(i))){
                    matched.add(b);
                }
            }
        }
        return matched;
    }

    public void convert(double[] predictThis){
        
        for (int i = 0; i < predictThis.length; i++) {
            map.put(names[i],predictThis[i]);
        }
    }

}
