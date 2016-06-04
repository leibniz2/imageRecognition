package model;

import org.openimaj.math.geometry.point.Point2dImpl;

/**
 * Created by RRosall on 12/16/2015.
 */
public class FeatureCalculator {

    public FeatureCalculator(){
    }



    /*
        Formula:
        MIDDLE LEFT EYE = (leftpoint_lefteye + right_point_lefteye)/2
        MIDDLE RIGHT EYE = (leftpoint_righteye + rightpoint_righteye)/2
        MIDDLE MOUTH = (leftpoint_mouth + rightpoint_mouth)/2
        MIDDLE NOSE = (leftpoint_nose + rightpoint_nose)/2


        X = math.pow((x1,x2),2)
        Y = math.pow((y1,y2),2)

       Middle = Math.sqrt((X+Y))

     */

    public float calculateDistance(Point2dImpl left, Point2dImpl right){
        float X = (float) Math.pow(left.getX()-right.getX(),2);
        float Y = (float) Math.pow(left.getY()-right.getY(),2);
        return (float) Math.sqrt(X+Y);
    }

}
