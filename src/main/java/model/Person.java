/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import org.openimaj.feature.FeatureVector;

/**
 *
 * @author Admin
 */
public class Person {
    private String ID;
    private ArrayList<Features> list_of_features = new ArrayList();
    private String name;
    
    public Person(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public ArrayList<Features> getList_of_features() {
        return list_of_features;
    }

    public void setList_of_features(ArrayList<Features> list_of_features) {
        this.list_of_features = list_of_features;
    }
}
