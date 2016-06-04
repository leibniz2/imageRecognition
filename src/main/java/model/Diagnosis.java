package model;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class Diagnosis {
    private Double true_positive;
    private Double true_negative;
    private Double false_positive;
    private Double false_negative;
    private Double precision;
    private Double accuracy;
    private Double recall;
    private Double fScore;
    
    Diagnosis(){
        true_positive = 0.0;
        true_negative = 0.0;
        false_positive = 0.0;
        false_negative = 0.0;
        precision = 0.0;
        accuracy = 0.0;
        recall = 0.0;
        fScore = 0.0;
    }

    public void diagnose(Double[] h, Double[] y){
        Double tp = 0.0;
        Double tn = 0.0;
        Double fp = 0.0;
        Double fn = 0.0;
        for(int i=0; i<h.length; i++){
            if((h[i].equals(y[i]) && (h[i] == 1.0))) {
               // System.out.println("tp");
                tp++;
            }
            else if(h[i].equals(y[i]) && h[i] == 0.0) {
                //System.out.println("tn");
                tn++;
            }
            else if(!h[i].equals(y[i]) && h[i] == 1.0) {
                //System.out.println("fp");
                fp++;
            }
            else if(!h[i].equals(y[i]) && h[i] == 0.0) {
                //System.out.println("fn");
                fn++;
            }
        }
        setTrue_positive(tp);
        setTrue_negative(tn);
        setFalse_positive(fp);
        setFalse_negative(fn);

        System.out.println("True Positive: "+ tp);
        System.out.println("True Negative: "+ tn);
        System.out.println("False Positive: "+ fp);
        System.out.println("False Negative: "+ fn);
        calculatePrecision();
        calculateRecall();
        calculateFScore();
    }

    public Double[] setThreshold(Double[] h, int size, Double threshold){
        System.out.println(h.length);
        Double[] b = new Double[size];
        for(int i=0; i<size; i++){
            if(h[i] >= threshold)
                b[i] = 1.0;
            else
                b[i] = 0.0;
        }
        return b;
    }
    
    public void calculateFScore(){
        Double fScore = 2.0*((getPrecision()*getRecall())/(getPrecision()+getRecall()));
        this.setfScore(fScore);
    }
    
    public void calculatePrecision(){
        setPrecision(getTrue_positive()/(getTrue_positive() + getFalse_positive()));
    }
    
    public void calculateRecall(){
        //System.out.println(getTrue_positive() + " " + getTrue_positive() + " " + getFalse_negative());
        setRecall(getTrue_positive()/(getTrue_positive() + getFalse_negative()));
    }
    
   

    /**
     * @return the true_positive
     */
    public Double getTrue_positive() {
        return true_positive;
    }

    /**
     * @param true_positive the true_positive to set
     */
    public void setTrue_positive(Double true_positive) {
        this.true_positive = true_positive;
    }

    /**
     * @return the true_negative
     */
    public Double getTrue_negative() {
        return true_negative;
    }

    /**
     * @param true_negative the true_negative to set
     */
    public void setTrue_negative(Double true_negative) {
        this.true_negative = true_negative;
    }

    /**
     * @return the false_positive
     */
    public Double getFalse_positive() {
        return false_positive;
    }

    /**
     * @param false_positive the false_positive to set
     */
    public void setFalse_positive(Double false_positive) {
        this.false_positive = false_positive;
    }

    /**
     * @return the false_negative
     */
    public Double getFalse_negative() {
        return false_negative;
    }

    /**
     * @param false_negative the false_negative to set
     */
    public void setFalse_negative(Double false_negative) {
        this.false_negative = false_negative;
    }

    /**
     * @return the precision
     */
    public Double getPrecision() {
        return precision;
    }

    /**
     * @param precision the precision to set
     */
    public void setPrecision(Double precision) {
        this.precision = precision;
    }

    /**
     * @return the accuracy
     */
    public double getAccuracy() {
        return accuracy;
    }

    /**
     * @param accuracy the accuracy to set
     */
    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    /**
     * @return the fScore
     */
    public double getfScore() {
        return fScore;
    }

    /**
     * @param fScore the fScore to set
     */
    public void setfScore(Double fScore) {
        this.fScore = fScore;
    }

    /**
     * @return the recall
     */
    public double getRecall() {
        return recall;
    }

    /**
     * @param recall the recall to set
     */
    public void setRecall(Double recall) {
        this.recall = recall;
    }
}
