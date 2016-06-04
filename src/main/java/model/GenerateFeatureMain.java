/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import org.openimaj.image.FImage;
import org.openimaj.image.processing.face.detection.keypoints.FacialKeypoint;
import org.openimaj.image.processing.face.detection.keypoints.KEDetectedFace;

/**
 *
 * @author Admin
 */
public class GenerateFeatureMain {

    private CompleteDataSet CV_SET = new CompleteDataSet();
    private CompleteDataSet TRAINING_SET = new CompleteDataSet();

    public CompleteDataSet getCV_SET() {
        return CV_SET;
    }

    public void setCV_SET(CompleteDataSet CV_SET) {
        this.CV_SET = CV_SET;
    }

    public CompleteDataSet getTRAINING_SET() {
        return TRAINING_SET;
    }

    public void setTRAINING_SET(CompleteDataSet TRAINING_SET) {
        this.TRAINING_SET = TRAINING_SET;
    }

    public boolean fileExist() {
        return CV_SET.getFile().filePresent();
    }

    public void genFeatures() {
        Face_Detector face_detector = new Face_Detector();
        FeatureCalculator featureCalculator = new FeatureCalculator();
        Features features;
        String[] folder_name = {"CV"
//                , "Training"

        };

        int chck = 0;

        for (String folder : folder_name) {
            char name = 'a';
            String path = folder;
            chck = 0;
            for (int j = 0; j < 15; j++) {
                Person p = new Person("");
                if (name == 'a') {
                    p.setID("Andrew Garfield");
                } else if (name == 'b') {
                    p.setID("Candice");
                } else if (name == 'c') {
                    p.setID("Cara");
                } else if (name == 'd') {
                    p.setID("Chrissy");
                } else if (name == 'e') {
                    p.setID("Joseph");
                } else if (name == 'f') {
                    p.setID("Kurt Junshean");
                } else if (name == 'g') {
                    p.setID("Keira Knightley");
                } else if (name == 'h') {
                    p.setID("Kendall Jenner");
                } else if (name == 'i') {
                    p.setID("Lee Jung Suk");
                } else if (name == 'j') {
                    p.setID("Legolas");
                } else if (name == 'k') {
                    p.setID("Lenka");
                } else if (name == 'l') {
                    p.setID("Mario Maurer");
                } else if (name == 'm') {
                    p.setID("Michelle Monaghan");
                } else if (name == 'n') {
                    p.setID("Robert");
                } else if (name == 'o') {
                    p.setID("Roger");
                }

                String id = "";
                for (int i = 0; i < 15; i++) {
                    if (i == chck) {
                        id = id + "1";
                    } else {
                        id = id + "0";
                    }
                    id = id + " ";

                }

                int a = 0;
                int b = 0;
                if (folder.equals("CV")) {
                    CV_SET.getFile().setFromCV(true);
                    a = 1;
                    b = 2;
                } else {
                    TRAINING_SET.getFile().setFromCV(false);
                    a = 3;
                    b = 8;
                }
                for (; a <= b; a++) {
                    System.out.println(path + "/" + name + a + ".png");
                    /*
                     PART 1: LOADING THE IMAGE
        
                     */
                    FImage image = face_detector.loadImage(path + "/" + name + a + ".png");

                    /*
                     STEP 2: DETECT AND CROP
                     */
                    KEDetectedFace one = face_detector.detect_face(image);
                    KEDetectedFace two = face_detector.detect_face(one.getFacePatch());

                    /*
                     STEP 3: EXTRACT FEATURES USING KEY POINTS
                     */
                    FacialKeypoint[] facialKeypoints = face_detector.extract_features(two, one.getFacePatch().toRGB());

                    /*
                     STEP 4: SET AND CALCULATE FEATURES
                     */
                    features = face_detector.setFeatures(facialKeypoints);

                    features.setJ1(
                            featureCalculator.calculateDistance(features.getLEFTEYE_CENTERPOINT(), features.getRIGHTEYE_CENTERPOINT())
                    );

                    features.setJ2(
                            featureCalculator.calculateDistance(features.getLEFTEYE_CENTERPOINT(), features.getMOUTH_CENTERPOINT())
                    );

                    features.setJ3(
                            featureCalculator.calculateDistance(features.getRIGHTEYE_CENTERPOINT(), features.getMOUTH_CENTERPOINT())
                    );

                    features.setJ4(
                            featureCalculator.calculateDistance(features.getLEFTEYE_CENTERPOINT(), features.getNOSE_CENTERPOINT())
                    );

                    features.setJ5(
                            featureCalculator.calculateDistance(features.getRIGHTEYE_CENTERPOINT(), features.getNOSE_CENTERPOINT())
                    );

                    features.setJ6(
                            featureCalculator.calculateDistance(features.getMOUTH_CENTERPOINT(), features.getNOSE_CENTERPOINT())
                    );

                    features.setJ7(
                            featureCalculator.calculateDistance(features.getLEFTEYE_RIGHTEYE_CENTER(), features.getNOSE_CENTERPOINT())
                    );

                    features.setJ8(
                            featureCalculator.calculateDistance(features.getNOSE_LEFTPOINT(), features.getNOSE_RIGHTPOINT())
                    );
                    features.setFeatureVector();
                    p.getList_of_features().add(features);
                    p.setID(id);

                }
                name++;
                chck++;
                if (folder.equals("CV")) {
                    CV_SET.getDataSet().add(p);
                } else {
                    TRAINING_SET.getDataSet().add(p);
                }
            }
            if (folder.equals("CV")) {
                CV_SET.convertToFile(folder);
                CV_SET.convertFromFile(folder);
//                System.out.println("INPUT");
//                CV_SET.printMatrix(CV_SET.getInput());
//                System.out.println("OUTPUT");
//                CV_SET.printMatrix(CV_SET.getOutput());
            }
            else{
                TRAINING_SET.convertToFile(folder);
                TRAINING_SET.convertFromFile(folder);
//                System.out.println("INPUT");
//                TRAINING_SET.printMatrix(TRAINING_SET.getInput());
//                System.out.println("OUTPUT");
//                TRAINING_SET.printMatrix(TRAINING_SET.getOutput());
            }
        }

    }
}
