/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Jama.Matrix;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class FileWriterReader {

    private  String data = "";
    private File File_Name = new File("Data_Set_Kurt.junshean");
    private Matrix input;
    private Matrix output;
    private int ROW;
    private int COL;

    private boolean fromCV = true;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public File getFile_Name() {
        return File_Name;
    }

    public void setFile_Name(File File_Name) {
        this.File_Name = File_Name;
    }

    public boolean isFromCV() {
        return fromCV;
    }

    public void setFromCV(boolean fromCV) {
        this.fromCV = fromCV;
    }
    
    
    
    public int getROW() {
        return ROW;
    }

    public void setROW(int ROW) {
        this.ROW = ROW;
    }

    public int getCOL() {
        return COL;
    }

    public void setCOL(int COL) {
        this.COL = COL;
    }

    
    
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

    

    public void write(String name) {
        File_Name = new File(name+".junshean");
        FileWriter writer;
        try {
            writer = new FileWriter(File_Name.getName(), false);
            BufferedWriter bufferWritter = new BufferedWriter(writer);
            bufferWritter.write(data);
            bufferWritter.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getRow(String name) {
         File_Name = new File(name+".junshean");
        int row = 0;
        try {
            FileReader reader;
            reader = new FileReader(File_Name.getName());
            BufferedReader br = new BufferedReader(reader);
            while (br.readLine() != null) {
                row ++;
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileWriterReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileWriterReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
    
    public void read(String name){
        ROW = getRow(name);
        File_Name = new File(name+".junshean");
        int c = 0;
        if(fromCV){
            c = 2;
        }
        else if(!fromCV){
            c = 6;
        }
        else
            c = 1;
        input =  new Matrix(ROW,8);
        output = new Matrix(ROW,ROW/c);
         try {
            FileReader reader;
            reader = new FileReader(File_Name.getName());
            BufferedReader br = new BufferedReader(reader);
            String s;
            int row = 0;
            while ((s = br.readLine()) != null) {
                String[] features = s.split("\\s");
                int col = 0;
                for(;col<8;col++){
                    input.set(row, col, Double.parseDouble(features[col]));
                }
               
                for(;col<(ROW/c)+8;col++){
                    output.set(row, col-8, Double.parseDouble(features[col]));
                    
                }
                
                row++;
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileWriterReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileWriterReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean filePresent() {
        return File_Name.exists();
    }
    

}
