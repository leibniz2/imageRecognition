package model;

import org.openimaj.feature.DoubleFV;
import org.openimaj.feature.FeatureVector;
import org.openimaj.feature.FloatFV;
import org.openimaj.math.geometry.point.Point2dImpl;

import java.util.ArrayList;

/**
 * Created by RRosall on 12/17/2015.
 */
public class Features {
    private String picture_name;
    
    private Point2dImpl LEFTEYE_LEFTPOINT, LEFTEYE_RIGHTPOINT, LEFTEYE_CENTERPOINT;
    private Point2dImpl RIGHTEYE_LEFTPOINT, RIGHTEYE_RIGHTPOINT, RIGHTEYE_CENTERPOINT;
    private Point2dImpl MOUTH_LEFTPOINT, MOUTH_RIGHTPOINT, MOUTH_CENTERPOINT;
    private Point2dImpl NOSE_LEFTPOINT, NOSE_RIGHTPOINT, NOSE_CENTERPOINT;
    private Point2dImpl LEFTEYE_RIGHTEYE_CENTER;

    /*
    J1 - distance between middles of the eyes
    J2 - distance between middle of the left eyes and middle point of mouth
    J3 - distance between middle of the right eyes and middle point of mouth
    J4 - distance between middle of the left eyes and middle point of nose
    J5 - distance between middle of the right eyes and middle point of nose
    J6 - distance between middle point of mouth and middle point of nose
    J7 - distance of middle point of J1 and middle of nose
    J8 - width of nose
     */

//    private float MID_L_EYE_MID_R_EYE, MID_L_EYE_MID_MOUTH, MID_R_EYE_MID_MOUTH, L_EYE_MID_NOSE,
//                    R_EYE_MID_NOSE, MID_MOUTH_MID_NOSE,  MID_L_EYE_MID_R_EYE_MID_NOSE, LEFT_NOSE_RIGHT_NOSE ;

    private float J1, J2, J3, J4, J5, J6, J7, J8;
    
    private ArrayList<Float> feature_vector = new ArrayList<Float>();
    
 
    
    public void setFeatureVector(){
        feature_vector = new ArrayList<Float>();
            feature_vector.add(J1);
        feature_vector.add(J2);
        feature_vector.add(J3);
        feature_vector.add(J4);
        feature_vector.add(J5);
        feature_vector.add(J6);
        feature_vector.add(J7);
        feature_vector.add(J8);
        for (int i = 0; i < 8; i++) {
            System.out.println(feature_vector.get(i));
        }


    }

    public String getPicture_name() {
        return picture_name;
    }

    public void setPicture_name(String picture_name) {
        this.picture_name = picture_name;
    }

    public ArrayList<Float> getFeature_vector() {
        return feature_vector;
    }

    public void setFeature_vector(ArrayList feature_vector) {
        this.feature_vector = feature_vector;
    }
    
    
    
    public Features(String picture_name) {
        this.picture_name = picture_name;
    }
    
    

    public float getJ1() {
        return J1;
    }

    public void setJ1(float j1) {
        J1 = j1;
    }

    public float getJ2() {
        return J2;
    }

    public void setJ2(float j2) {
        J2 = j2;
    }

    public float getJ3() {
        return J3;
    }

    public void setJ3(float j3) {
        J3 = j3;
    }

    public float getJ4() {
        return J4;
    }

    public void setJ4(float j4) {
        J4 = j4;
    }

    public float getJ5() {
        return J5;
    }

    public void setJ5(float j5) {
        J5 = j5;
    }

    public float getJ6() {
        return J6;
    }

    public void setJ6(float j6) {
        J6 = j6;
    }

    public float getJ7() {
        return J7;
    }

    public void setJ7(float j7) {
        J7 = j7;
    }

    public float getJ8() {
        return J8;
    }

    public void setJ8(float j8) {
        J8 = j8;
    }

    public Point2dImpl getLEFTEYE_CENTERPOINT() {
        return LEFTEYE_CENTERPOINT;
    }

    public void setLEFTEYE_CENTERPOINT(Point2dImpl LEFTEYE_CENTERPOINT) {
        
        this.LEFTEYE_CENTERPOINT = LEFTEYE_CENTERPOINT;
    }

    public Point2dImpl getLEFTEYE_LEFTPOINT() {
        return LEFTEYE_LEFTPOINT;
    }

    public void setLEFTEYE_LEFTPOINT(Point2dImpl LEFTEYE_LEFTPOINT) {
        this.LEFTEYE_LEFTPOINT = LEFTEYE_LEFTPOINT;
    }

    public Point2dImpl getLEFTEYE_RIGHTPOINT() {
        return LEFTEYE_RIGHTPOINT;
    }

    public void setLEFTEYE_RIGHTPOINT(Point2dImpl LEFTEYE_RIGHTPOINT) {
        this.LEFTEYE_RIGHTPOINT = LEFTEYE_RIGHTPOINT;
    }

    public Point2dImpl getMOUTH_CENTERPOINT() {
        return MOUTH_CENTERPOINT;
    }

    public void setMOUTH_CENTERPOINT(Point2dImpl MOUTH_CENTERPOINT) {
        this.MOUTH_CENTERPOINT = MOUTH_CENTERPOINT;
    }

    public Point2dImpl getMOUTH_LEFTPOINT() {
        return MOUTH_LEFTPOINT;
    }

    public void setMOUTH_LEFTPOINT(Point2dImpl MOUTH_LEFTPOINT) {
        this.MOUTH_LEFTPOINT = MOUTH_LEFTPOINT;
    }

    public Point2dImpl getMOUTH_RIGHTPOINT() {
        return MOUTH_RIGHTPOINT;
    }

    public void setMOUTH_RIGHTPOINT(Point2dImpl MOUTH_RIGHTPOINT) {
        this.MOUTH_RIGHTPOINT = MOUTH_RIGHTPOINT;
    }

    public Point2dImpl getNOSE_CENTERPOINT() {
        return NOSE_CENTERPOINT;
    }

    public void setNOSE_CENTERPOINT(Point2dImpl NOSE_CENTERPOINT) {
        this.NOSE_CENTERPOINT = NOSE_CENTERPOINT;
    }

    public Point2dImpl getNOSE_LEFTPOINT() {
        return NOSE_LEFTPOINT;
    }

    public void setNOSE_LEFTPOINT(Point2dImpl NOSE_LEFTPOINT) {
        this.NOSE_LEFTPOINT = NOSE_LEFTPOINT;
    }

    public Point2dImpl getNOSE_RIGHTPOINT() {
        return NOSE_RIGHTPOINT;
    }

    public void setNOSE_RIGHTPOINT(Point2dImpl NOSE_RIGHTPOINT) {
        this.NOSE_RIGHTPOINT = NOSE_RIGHTPOINT;
    }

    public Point2dImpl getRIGHTEYE_LEFTPOINT() {
        return RIGHTEYE_LEFTPOINT;
    }

    public void setRIGHTEYE_LEFTPOINT(Point2dImpl RIGHTEYE_LEFTPOINT) {
        this.RIGHTEYE_LEFTPOINT = RIGHTEYE_LEFTPOINT;
    }

    public Point2dImpl getRIGHTEYE_CENTERPOINT() {
        return RIGHTEYE_CENTERPOINT;
    }

    public void setRIGHTEYE_CENTERPOINT(Point2dImpl RIGHTEYE_CENTERPOINT) {
        this.RIGHTEYE_CENTERPOINT = RIGHTEYE_CENTERPOINT;
    }

    public Point2dImpl getRIGHTEYE_RIGHTPOINT() {
        return RIGHTEYE_RIGHTPOINT;
    }

    public void setRIGHTEYE_RIGHTPOINT(Point2dImpl RIGHTEYE_RIGHTPOINT) {
        this.RIGHTEYE_RIGHTPOINT = RIGHTEYE_RIGHTPOINT;
    }

    public Point2dImpl getLEFTEYE_RIGHTEYE_CENTER() {
        return LEFTEYE_RIGHTEYE_CENTER;
    }

    public void setLEFTEYE_RIGHTEYE_CENTER(Point2dImpl LEFTEYE_RIGHTEYE_CENTER) {
        this.LEFTEYE_RIGHTEYE_CENTER = LEFTEYE_RIGHTEYE_CENTER;
    }
}
