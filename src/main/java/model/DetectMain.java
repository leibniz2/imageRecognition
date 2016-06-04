package model;

import model.Person_Identifier;
import model.Test;
import org.apache.poi.hssf.record.formula.functions.Rows;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.TrainingSetImport;
import org.neuroph.util.TransferFunctionType;
import org.neuroph.util.data.norm.MaxMinNormalizer;
import org.neuroph.util.data.norm.Normalizer;
import org.neuroph.util.data.norm.RangeNormalizer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Created by RRosall on 12/20/2015.
 */
public class DetectMain {

    JLabel one, two, three;
    JLabel tag_1, tag_2, tag_3;

    public DetectMain(String filename, JLabel one, JLabel two, JLabel three, JLabel tag_1, JLabel tag_2, JLabel tag_3){
        this.one = one;
        this.two = two;
        this.three = three;
        this.tag_1 = tag_1;
        this.tag_2 = tag_2;
        this.tag_3 = tag_3;
        System.out.println("Running Face Recognition Sample");
        // System.out.println("Using training set Training.junshean file!");

        NeuralNetwork neural = NeuralNetwork.createFromFile("ThisIsIt.nnet");
        neural.getWeights();
        Test test = new Test();
//
//        //KUHA ON NI SA UI
        String s = filename;

        test.extractFeatures(s);

        test.writeToFile();
        test.loadFromFile();
        double[] input = test.getInput().getRowPackedCopy();
        ArrayList<Double> newInput = new ArrayList();
        for(int i=0; i<input.length; i++){
            newInput.add(input[i]);
        }
        double max = Collections.max(newInput);
        double min = Collections.min(newInput);

        for(int i=0; i<newInput.size();i++) {
            double tmp = newInput.get(i);
            double normalized = (tmp - min) / (max - min);
            input[i] = normalized;
        }
        for (int i = 0; i < 8; i++) {
            System.out.println(input[i]);
        }
//        Normalizer n = new MaxMinNormalizer();
//        DataSet dataSet = new DataSet(8);
//        double[] dod = new double[8];
//                dod[0] =test.getInput().get(0,0);
//        dod[1] = test.getInput().get(0,1);
//                dod[2] =test.getInput().get(0,2);
//                dod[3] =test.getInput().get(0,3);
//                dod[4] =test.getInput().get(0,4);
//                dod[5] =test.getInput().get(0,5);
//                dod[6] =test.getInput().get(0,6);
//                dod[7] =test.getInput().get(0,7);
//
//        System.out.println("Asdsadsadsadsada");
//        for (int i = 0; i < 8; i++) {
//            System.out.println(dod[i]);
//        }
//        DataSetRow dataSetRow = new DataSetRow();
//        dataSet.addRow(dod);
//        System.out.println("ADD ROW");
//        for (int i = 0; i < 8; i++) {
//            System.out.println(dataSet.getRows());
//        }
//        n.normalize(dataSet);
//        System.out.println("AFTER NormaliZED");
//        for (int i = 0; i < 8; i++) {
//            System.out.println(dataSet.getRows());
//        }
        //neural.s
//                0.4211, 0.1784, 0.6712, 0.6124, 0.6278, 0.2329, 0.6929, 0.5676
//        );
//        dataSetRow= dataSet.getRowAt(0);
        neural.setInput(input[0],input[1],input[2],input[3],input[4],input[5],input[6],input[7]);
//                dataSetRow.getInput()[0],dataSetRow.getInput()[1],dataSetRow.getInput()[2],dataSetRow.getInput()[3]
//                ,dataSetRow.getInput()[4],dataSetRow.getInput()[5],dataSetRow.getInput()[6],dataSetRow.getInput()[7]);
//        neural.setInput(  0.4953, 0.2485, 0.6878, 0.3539, 0.3117, 0.6102, 0.3193,0.9501);

        neural.calculate();
        Person_Identifier person_identifier = new Person_Identifier();
        double[] output = neural.getOutput();
        person_identifier.convert(output);
        ArrayList<String> tmp = person_identifier.getNames();
        for (int i = 0; i < tmp.size() ; i++) {
            //System.out.println(tmp.get(i));
        }

        BufferedImage image_1 = null;
        BufferedImage image_2 = null;
        BufferedImage image_3 = null;

//        image_1 = ImageIO.read(tmp.get(0).toString());

        try {
            image_1 = ImageIO.read(getClass().getClassLoader().getResource(tmp.get(0) +".png"));
            image_2 = ImageIO.read(getClass().getClassLoader().getResource(tmp.get(1) +".png"));
            image_3 = ImageIO.read(getClass().getClassLoader().getResource(tmp.get(2) +".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image dimg = image_1.getScaledInstance(one.getWidth(), one.getHeight(),
                Image.SCALE_SMOOTH);

        Image dimg2 = image_2.getScaledInstance(two.getWidth(), two.getHeight(),
                Image.SCALE_SMOOTH);

        Image dimg3 = image_3.getScaledInstance(three.getWidth(), three.getHeight(),
                Image.SCALE_SMOOTH);

        one.setIcon(new ImageIcon(dimg));
        two.setIcon(new ImageIcon(dimg2));
        three.setIcon(new ImageIcon(dimg3));

        tag_1.setText(tmp.get(0));
        tag_2.setText(tmp.get(1));
        tag_3.setText(tmp.get(2));

//        double max = 0;
//        int index = 0;
//        for (int i = 0; i < output.length ; i++) {
//            System.out.println(output[i]);
//            if(max<output[i]){
//                max = output[i];
//                index = i;
//
//            }
//        }
//
//
//        System.out.println(max);
//        System.out.println(index);



////        // create training set
//        DataSet trainingSet = null;
//        DataSet cv_ = null;
//        try {
//            trainingSet = TrainingSetImport.importFromFile("C:\\Users\\Radley Rosal\\IdeaProjects\\MLL\\src\\main\\resources\\Training.junshean", 8, 15, " ");
//            cv_ = TrainingSetImport.importFromFile("C:\\Users\\Radley Rosal\\IdeaProjects\\MLL\\src\\main\\resources\\cv.junshean", 8, 15, " ");
//
//        } catch (FileNotFoundException ex) {
//            System.out.println("File not found!");
//            System.out.println("Error reading file or bad number format!");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        // create multi layer perceptron
//        System.out.println("Creating neural network");
//        MultiLayerPerceptron neuralNet = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 8, 30, 15);
//
////         set learning parametars
//        MomentumBackpropagation learningRule = (MomentumBackpropagation) neuralNet.getLearningRule();
//        learningRule.setLearningRate(0.32);
//        learningRule.setMomentum(0.7);
//        learningRule.setMinErrorChange(0.1);
//
//        // learn the training set
//        System.out.println("Training neural network...");
//        neuralNet.learn(trainingSet);
//        System.out.println("Done!");
//
////         test perceptron
//        System.out.println("Testing trained neural network");
//        testFaceRecognition(neuralNet, cv_);
//
//        neuralNet.save("achitecture01_Test1.nnet");
    }

//    public static void testFaceRecognition(NeuralNetwork nnet, DataSet dset) {
//
//        for (DataSetRow trainingElement : dset.getRows()) {
//
//            nnet.setInput(trainingElement.getInput());
//            nnet.calculate();
//            double[] networkOutput = nnet.getOutput();
//            System.out.println("Input: " + Arrays.toString(trainingElement.getInput()));
//            System.out.println(" Output: " + Arrays.toString(networkOutput));
//        }
//
//    }

}
