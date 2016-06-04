package model;

import org.openimaj.feature.FeatureVector;
import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.FImage;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.colour.RGBColour;
import org.openimaj.image.processing.face.detection.keypoints.FKEFaceDetector;
import org.openimaj.image.processing.face.detection.keypoints.FacialKeypoint;
import org.openimaj.image.processing.face.detection.keypoints.KEDetectedFace;
import org.openimaj.math.geometry.point.Point2dImpl;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * Created by RRosall on 12/15/2015.
 */
public class Face_Detector {

    private String DEFAULT_PATH = "src/main/resources/";
    BufferedImage image ;
    private String image_name;

    public Face_Detector(){

    }


    /**
     * Accepts the image's filename and convert it to FImage
     * @param filename the name of the file
     * @return fimage
     */
    public FImage loadImage(String filename){
        image_name = filename;
        try {
            image = ImageIO.read(getClass().getClassLoader().getResource(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ImageUtilities.createFImage(image);
    }


    /**
     * @param image
     * @return
     */
    public KEDetectedFace detect_face(FImage image){



        FKEFaceDetector fd = new FKEFaceDetector(30);

        KEDetectedFace face = fd.detectFaces(image).get(0);
//        KEDetectedFace facee = fd.detectFaces(face.getFacePatch()).get(0);
//        return convert(face.getFacePatch());
//        frame = face.getFacePatch().toRGB();
        return face;



    }

//    public KEDetectedFace convert(FImage image){
//        return image.toRGB()
//    }

    /**
     * @param faces
     * @param image
     */
    public FacialKeypoint[] extract_features( KEDetectedFace faces, MBFImage image) {
        for(FacialKeypoint keypoint:faces.getKeypoints()){
            keypoint.position.translate((float)faces.getBounds().minX(),(float)faces.getBounds().minY());
            image.drawPoint(keypoint.position, RGBColour.BLUE,5);

        }

       //DisplayUtilities.display(image);
       // setFeautures(faces.getKeypoints());
        return faces.getKeypoints();

    }

    public Features setFeatures(FacialKeypoint[] keypoints){

        Features features = new Features(image_name);

        if(FacialKeypoint.getKeypoint(keypoints,FacialKeypoint.FacialKeypointType.EYE_LEFT_LEFT) != null )
            features.setLEFTEYE_LEFTPOINT(FacialKeypoint.getKeypoint(keypoints,FacialKeypoint.FacialKeypointType.EYE_LEFT_LEFT).position);
        if(FacialKeypoint.getKeypoint(keypoints,FacialKeypoint.FacialKeypointType.EYE_LEFT_RIGHT)!= null)
            features.setLEFTEYE_RIGHTPOINT(FacialKeypoint.getKeypoint(keypoints,FacialKeypoint.FacialKeypointType.EYE_LEFT_RIGHT).position);
        if(FacialKeypoint.getKeypoint(keypoints,FacialKeypoint.FacialKeypointType.EYE_LEFT_CENTER)!= null)
            features.setLEFTEYE_CENTERPOINT(FacialKeypoint.getKeypoint(keypoints,FacialKeypoint.FacialKeypointType.EYE_LEFT_CENTER).position);
        else{
            features.setLEFTEYE_CENTERPOINT(calculateCenter(features.getLEFTEYE_LEFTPOINT(),features.getLEFTEYE_RIGHTPOINT()));
        }

        if(FacialKeypoint.getKeypoint(keypoints,FacialKeypoint.FacialKeypointType.EYE_RIGHT_LEFT)!= null)
            features.setRIGHTEYE_LEFTPOINT(FacialKeypoint.getKeypoint(keypoints,FacialKeypoint.FacialKeypointType.EYE_RIGHT_LEFT).position);
        if(FacialKeypoint.getKeypoint(keypoints,FacialKeypoint.FacialKeypointType.EYE_RIGHT_RIGHT)!= null)
            features.setRIGHTEYE_RIGHTPOINT(FacialKeypoint.getKeypoint(keypoints,FacialKeypoint.FacialKeypointType.EYE_RIGHT_RIGHT).position);
        if(FacialKeypoint.getKeypoint(keypoints,FacialKeypoint.FacialKeypointType.EYE_RIGHT_CENTER)!= null)
            features.setRIGHTEYE_CENTERPOINT(FacialKeypoint.getKeypoint(keypoints,FacialKeypoint.FacialKeypointType.EYE_RIGHT_CENTER).position);
        else{
            features.setRIGHTEYE_CENTERPOINT(calculateCenter(features.getRIGHTEYE_LEFTPOINT(),features.getRIGHTEYE_RIGHTPOINT()));
        }

        if(FacialKeypoint.getKeypoint(keypoints,FacialKeypoint.FacialKeypointType.NOSE_LEFT)!= null)
            features.setNOSE_LEFTPOINT(FacialKeypoint.getKeypoint(keypoints,FacialKeypoint.FacialKeypointType.NOSE_LEFT).position);
        if(FacialKeypoint.getKeypoint(keypoints,FacialKeypoint.FacialKeypointType.NOSE_RIGHT)!= null)
            features.setNOSE_RIGHTPOINT(FacialKeypoint.getKeypoint(keypoints,FacialKeypoint.FacialKeypointType.NOSE_RIGHT).position);
        if(FacialKeypoint.getKeypoint(keypoints,FacialKeypoint.FacialKeypointType.NOSE_MIDDLE)!= null)
            features.setNOSE_CENTERPOINT(FacialKeypoint.getKeypoint(keypoints,FacialKeypoint.FacialKeypointType.NOSE_MIDDLE).position);
        else{
            features.setNOSE_CENTERPOINT(calculateCenter(features.getNOSE_LEFTPOINT(),features.getNOSE_RIGHTPOINT()));
        }

        if(FacialKeypoint.getKeypoint(keypoints,FacialKeypoint.FacialKeypointType.MOUTH_LEFT)!= null)
            features.setMOUTH_LEFTPOINT(FacialKeypoint.getKeypoint(keypoints,FacialKeypoint.FacialKeypointType.MOUTH_LEFT).position);
        if(FacialKeypoint.getKeypoint(keypoints,FacialKeypoint.FacialKeypointType.MOUTH_RIGHT)!= null)
            features.setMOUTH_RIGHTPOINT(FacialKeypoint.getKeypoint(keypoints,FacialKeypoint.FacialKeypointType.MOUTH_RIGHT).position);
        if( FacialKeypoint.getKeypoint(keypoints,FacialKeypoint.FacialKeypointType.MOUTH_CENTER)!= null)
            features.setMOUTH_CENTERPOINT(FacialKeypoint.getKeypoint(keypoints,FacialKeypoint.FacialKeypointType.MOUTH_CENTER).position);
        else{
            features.setMOUTH_CENTERPOINT(calculateCenter(features.getMOUTH_LEFTPOINT(),features.getMOUTH_RIGHTPOINT()));
        }


        features.setLEFTEYE_RIGHTEYE_CENTER(calculateCenter(features.getLEFTEYE_CENTERPOINT(),features.getRIGHTEYE_CENTERPOINT()));


        return features;
    }
    public boolean chker(Point2dImpl feature){
        return feature!=null;
    }

    public Point2dImpl calculateCenter(Point2dImpl left, Point2dImpl right){
        Point2dImpl center = new Point2dImpl();
        center.setX((left.getX()+right.getX())/2);
        center.setY((left.getY()+right.getY())/2);
        return center;
    }

    public String getDEFAULT_PATH() {
        return DEFAULT_PATH;
    }

    public void setDEFAULT_PATH(String DEFAULT_PATH) {
        this.DEFAULT_PATH = DEFAULT_PATH;
    }
}
