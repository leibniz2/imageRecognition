package model;

import java.util.Arrays;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.Perceptron;
import org.neuroph.util.TransferFunctionType;
import org.neuroph.contrib.eval.EvaluationResult;
import org.neuroph.contrib.model.errorestimation.CrossValidationResult;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class FacialRecognition {
    public static void main(String[] args) {
        FacialRecognition fr = new FacialRecognition();
        //fr.trainer_perceptron();
        //fr.tester_perceptron();
        //fr.diagnose(outputfromtest, actualoutputfrommelgo)
        fr.trainer_mlp();
        
    }
    
    public void trainer_mlp(){
            // create training set (logical XOR function)
        DataSet trainingSet = new DataSet(2, 1);
        EvaluationResult r = new EvaluationResult();
        CrossValidationResult cv = new CrossValidationResult();
       
        trainingSet.addRow(new DataSetRow(new double[]{0, 0}, new double[]{0}));
        trainingSet.addRow(new DataSetRow(new double[]{0, 1}, new double[]{1}));
        trainingSet.addRow(new DataSetRow(new double[]{1, 0}, new double[]{1}));
        trainingSet.addRow(new DataSetRow(new double[]{1, 1}, new double[]{0}));


        // create multi layer perceptron
        MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 2, 3, 1);

        // learn the training set
        myMlPerceptron.learn(trainingSet);

        // test perceptron
        System.out.println("Testing trained neural network");
        testNeuralNetwork(myMlPerceptron, trainingSet);

        // save trained neural network
        myMlPerceptron.save("myMlPerceptron.nnet");

        // load saved neural network
        NeuralNetwork loadedMlPerceptron = NeuralNetwork.createFromFile("myMlPerceptron.nnet");

        // test loaded neural network
        System.out.println("Testing loaded neural network");
        testNeuralNetwork(loadedMlPerceptron, trainingSet);
       // loadedMlPerceptron..getOutputNeurons()[0].
        
    }
    
    public void trainer_perceptron(){
        NeuralNetwork nn =  new Perceptron(2,1);
        DataSet training  = new DataSet(2,1);
        
        //logical AND
        training.addRow(new double[]{0,0},new double[]{0});
        training.addRow(new double[]{0,1},new double[]{0});
        training.addRow(new double[]{1,0},new double[]{0});
        training.addRow(new double[]{1,1},new double[]{1});
        
        
        nn.learn(training);
        
        nn.save("or_perceptron.nnet");
    }
    
    public void tester_perceptron(){
        NeuralNetwork nn = NeuralNetwork.load("or_perceptron.nnet");
        nn.setInput(1,1);
        
        nn.calculate();
        
        double[] n_output = nn.getOutput();
        
        for(int i=0; i<n_output.length; i++){
            System.out.println("output:" + n_output[i]);
        }


    }
    
    
    public void testNeuralNetwork(NeuralNetwork nnet, DataSet testSet) {

        for(DataSetRow dataRow : testSet.getRows()) {
            nnet.setInput(dataRow.getInput());
            nnet.calculate();
            double[ ] networkOutput = nnet.getOutput();
            System.out.print("Input: " + Arrays.toString(dataRow.getInput()) );
            System.out.println(" Output: " + Arrays.toString(networkOutput) );
        }

    }
    
    public Diagnosis diagnose(double[] h, double[] y){
        Diagnosis d = new Diagnosis();
        
        for(int i=0; i<h.length; i++){
            if(h[i] == y[i] && h[i] == 1)
                d.setTrue_positive(d.getTrue_positive()+1);
            else if(h[i] == y[i] && h[i] == 0)
                d.setTrue_negative(d.getTrue_negative()+1);
            else if(h[i] != y[i] && h[i] == 1)
                d.setFalse_positive(d.getFalse_positive()+1);
            else if(h[i] != y[i] && h[i] == 0)
                d.setFalse_negative(d.getFalse_negative()+1);
        }
        d.calculatePrecision();
        d.calculateRecall();
        d.calculateFScore();
        
        return d;
    }
    
    public double[] setThreshold(double[] h, double threshold){
        double[] b = new double[h.length];
        for(int i=0; i<h.length; i++){
            if(h[i] >= threshold)
                b[i] = 1;
            else
                b[i] = 0;
        }
        return b;
    }
    
    
}
// http://neuroph.sourceforge.net/tutorials/MultiLayerPerceptron.html
// ArrayList<double[]> a = new ArrayList();