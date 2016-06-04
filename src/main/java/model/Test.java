package model;

import Jama.Matrix;
import org.openimaj.image.FImage;
import org.openimaj.image.processing.face.detection.keypoints.FacialKeypoint;
import org.openimaj.image.processing.face.detection.keypoints.KEDetectedFace;

import java.util.ArrayList;

/**
 * Created by RRosall on 12/20/2015.
 */
public class Test {
    private Person p;
    private Face_Detector face_detector = new Face_Detector();
    private FeatureCalculator featureCalculator = new FeatureCalculator();
    private Features features;
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

    public Person getP() {
        return p;
    }

    public void setP(Person p) {
        this.p = p;
    }

    public void extractFeatures(String name){
            p  = new Person(name);
                         /*
                         PART 1: LOADING THE IMAGE

                         */
            FImage image = face_detector.loadImage("TEST/" + name);

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
            p.setID(name);
    }

    public void featuresToString(){
        for(Features features: p.getList_of_features()){
            dataSetString.add(features.getFeature_vector().get(0)+" "+features.getFeature_vector().get(1)
                    +" "+features.getFeature_vector().get(2)+" "+features.getFeature_vector().get(3)
                    +" "+features.getFeature_vector().get(4)+" "+features.getFeature_vector().get(5)
                    +" "+features.getFeature_vector().get(6)+" "+features.getFeature_vector().get(7)) ;
        }

    }

    public void writeToFile(){
        featuresToString();
        for(String s:dataSetString){
            file.setData(file.getData() + s +"\r\n");
        }
        file.write(p.getName());
    }

    public void loadFromFile(){
        file.read(p.getName());
        output = file.getOutput();
        input = file.getInput();
    }
}
