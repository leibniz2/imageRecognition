package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Admin on 21/12/2015.
 */
public class FileParser {


    private Double[] h = new Double[3000];
    private Double[] y = new Double[3000];
    private int index_h = 0;
    private int index_y = 0;

    public FileParser(){
    }

    /**
     * @param filename name of the text file
     */
    public void loadFromFile(String filename){

        File file = new File("src/main/resources/"+filename);
        System.out.println("File exists: " +file.exists());
        Scanner reader = new Scanner(System.in);

        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String str = "";
        String str_h = "";
        String str_y = "";

        while(reader.hasNext()){
            str = reader.nextLine();
            if(!str.startsWith("T")){
                str_h = str.substring( str.indexOf("O")+7 , str.indexOf("D"));
                String[] temp = str_h.split(";");
                for (String s : temp){
                    if (!(s == null || s.trim().equals("")))
                         this.h[index_h++] = Double.parseDouble(s);
                }

                str_y = str.substring( str.indexOf("D")+15, str.indexOf("E")-1);
                temp = str_y.split(";");
                for (String s: temp) {
                    if (!(s == null || s.trim().equals("")))
                        this.y[index_y++] = Double.parseDouble(s);
                }
            }
        }

        System.out.println("Test: " + getIndex_h() + " == " + getIndex_y());
    }

    public void TestOutputs(){
        for (int i = 0; i < index_h ; i++) {
            System.out.println(h[i]);
        }
    }

    public void TestDesiredOutputs(){
        for (int i = 0; i < index_y ; i++) {
            System.out.println(y[i]);
        }
    }


    public static void main(String[] args) {
        FileParser parser = new FileParser();
        parser.loadFromFile("parse.txt");
        System.out.println(parser.getH().length);
        Diagnosis dia = new Diagnosis();
        Double[] h = parser.getH();
        Double[] y = parser.getY();

//        for(Double d : h){
//            System.out.println(d);
//        }
        h = dia.setThreshold(h,parser.getIndex_h(), 0.3);



//        for(int i=0; i<h.length; i++){
//            System.out.println(h[i]+ "->" +y[i]);
//        }

        dia.diagnose(h,y);
//        System.out.println("True Positive:" + dia.getTrue_positive());
//        System.out.println("True Negative:" + dia.getTrue_negative());
//        System.out.println("False Positive:" + dia.getFalse_positive());
//        System.out.println("False Negative:" + dia.getFalse_negative());
        System.out.println("Precision:" + dia.getPrecision());
        System.out.println("Recall:" + dia.getRecall());
        System.out.println("F-Score:" + dia.getfScore());
//        parser.TestOutputs();
//        parser.TestDesiredOutputs();
    }

    /**
     * @return the index of the output (h)
     */
    public int getIndex_h() {
        return index_h;
    }

    /**
     * @return the index of the desired output(y)
     */
    public int getIndex_y() {
        return index_y;
    }

    /**
     * @return an array of outputs form the training set
     */
    public Double[] getH() {
        return this.h;
    }

    public void setH(Double[] h) {
        this.h = h;
    }

    /**
     * @return an array of desired outputs from the training set
     */
    public Double[] getY() {
        return this.y;
    }

    public void setY(Double[] y) {
        this.y = y;
    }
}
