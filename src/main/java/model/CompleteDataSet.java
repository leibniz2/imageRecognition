/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Jama.Matrix;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Admin
 */
public class CompleteDataSet {
    
    private ArrayList<Person> dataSet  = new ArrayList();
    private FileWriterReader file = new FileWriterReader();
    private Matrix input;
    private Matrix output;
    private ArrayList<String> dataSetString = new ArrayList();
    
    public Matrix getInput() {
        return input;
    }

    public void setInput(Matrix input) {
        this.input = input;
    }

    public Matrix getOutput() {
        return output;
    }

    public void setOutput(Matrix output) {
        this.output = output;
    }
    
    

    public ArrayList<Person> getDataSet() {
        return dataSet;
    }

    public void setDataSet(ArrayList<Person> dataSet) {
        this.dataSet = dataSet;
    }

    public FileWriterReader getFile() {
        return file;
    }

    public void setFile(FileWriterReader file) {
        this.file = file;
    }
    
    
     public void convertToString(){
        for(Person p:dataSet){
            for(Features features: p.getList_of_features()){
                
                dataSetString.add(features.getFeature_vector().get(0)+" "+features.getFeature_vector().get(1)
                        +" "+features.getFeature_vector().get(2)+" "+features.getFeature_vector().get(3)
                        +" "+features.getFeature_vector().get(4)+" "+features.getFeature_vector().get(5)
                        +" "+features.getFeature_vector().get(6)+" "+features.getFeature_vector().get(7)+" "+p.getID()) ;
            }
        }
    }
     
    public void convertToFile(String name){
        convertToString();
//        Collections.shuffle(dataSetString);
        for(String s:dataSetString){
                file.setData(file.getData() + s +"\r\n");
        }
        file.write(name);
    }
    
    public void convertFromFile(String name){
            file.read(name);
           
            input = file.getInput();
            output = file.getOutput();
    }
    
    public void printMatrix(Matrix a){
        for(int row=0;row<a.getRowDimension();row++){
            for(int col=0;col<a.getColumnDimension();col++){
                System.out.print(a.get(row, col)+" ");
            }
            System.out.println("");
        }
    } 

    /**
     * @return the size
     */
    public int getSize() {
        return input.getRowDimension();
    }

    
    
}
