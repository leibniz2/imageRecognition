/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface;

import model.DetectMain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author RRosall
 */
public class ml176 extends javax.swing.JFrame {

    
    JFileChooser jf = new JFileChooser();
    JLabel image_view = new JLabel();

    File file;

    int returnValue;

    String path = "";
    BufferedImage image;
    BufferedImage[] default_pics = new BufferedImage[2];
    BufferedImage default_man;
    BufferedImage default_woman;

    //test purposes
    String[] accuracy = {"93.23%", "87.23%", "63.12%"};
    String[] tag = { "best match" , "second", "third"};
    boolean ready = false;
    /**
     * Creates new form ml176
     */
    public ml176() {
        initComponents();
        initResources();
//        randomDefaultPics();
        createAndShowGUI();
        setLocationRelativeTo(null);
        jf.setCurrentDirectory(new File(System.getProperty("user.dir") + "/src/main/resources/TEST/"));
        label_image_1_acc.setText("");
        label_image_2_acc.setText("");
        label_image_3_acc.setText("");
    }

    public void randomDefaultPics(){

        int index = new Random().nextInt( (1-0) + 1);

        ImageIcon image_scaled = new ImageIcon(
                default_pics[index].getScaledInstance( imageView.getWidth(),
                        imageView.getHeight(), Image.SCALE_SMOOTH));

        ImageIcon best_scaled = new ImageIcon(
                default_pics[index].getScaledInstance(image_best.getWidth(),
                        imageView.getHeight(), Image.SCALE_SMOOTH));

        ImageIcon scaled_2 = new ImageIcon(
                default_pics[index].getScaledInstance(image_2.getWidth(),
                        image_2.getHeight(), Image.SCALE_SMOOTH));

        ImageIcon scaled_3 = new ImageIcon(
                default_pics[index].getScaledInstance(image_3.getWidth(),
                        image_3.getHeight(), Image.SCALE_SMOOTH));

        imageView.setIcon(image_scaled);
        imageView.setText("");
        image_best.setIcon(best_scaled);
        image_best.setText("");
        image_2.setIcon(scaled_2);
        image_2.setText("");
        image_3.setIcon(scaled_3);
        image_3.setText("");
    }

    public ImageIcon scaleIcon(JLabel component, BufferedImage image){
        return new ImageIcon(
                image.getScaledInstance(component.getWidth(), component.getHeight(),
                Image.SCALE_SMOOTH));
    }

    public void initResources(){
//        try {
//            default_man = ImageIO.read(getClass().getResource(System.getProperty("user.dir") + "\\src\\main\\resources\\app\\men.jpg"));
//            default_woman = ImageIO.read(getClass().getResource(System.getProperty("user.dir") + "\\src\\main\\resources\\app\\woman.jpg"));
//            default_pics[0] = default_man;
//            default_pics[1] = default_woman;
//        } catch (IOException ex) {
//            Logger.getLogger(ml176.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }


    public void switchImageView(int toSwap){
        /*
            Store accuracy and tag in index based on the original order
            active = best_match view
            image2 = 2nd best match view
            image3 = 3rd best match view

        Cycle
        If user clicks active:
                do nothing
        else if clicks image2
                swap (active, image2)
        else if clicks image3
                swap (active,image3)

        */


//                Icon icon = imageView.getIcon();
//                imageView.setIcon(image_2.getIcon());
//                image_2.setIcon(icon); use swap method
        String act_acc = label_image_1_acc.getText();
        String act_tag = label_image_1_tag.getText();
        String label = label_image_1.getText();

        if(toSwap==2){

            label_image_1_acc.setText(label_image_2_acc.getText());
            label_image_1_tag.setText(label_image_2_tag.getText());

            label_image_2_acc.setText(act_acc);
            label_image_2_tag.setText(act_tag);

            label_image_1.setText(label_image_2.getText());
            label_image_2.setText(label);

        }
        else if(toSwap==3){

            label_image_1_acc.setText(label_image_3_acc.getText());
            label_image_1_tag.setText(label_image_3_tag.getText());

            label_image_3_acc.setText(act_acc);
            label_image_3_tag.setText(act_tag);

            label_image_1.setText(label_image_3.getText());
            label_image_3.setText(label);
        }


    }

    private void createAndShowGUI()
    {

        jf.setDialogTitle("Choose Picture");
        // Create label
        image_view.setVisible(true);
        // Let label come fatty!!
        image_view.setPreferredSize(new Dimension(175,175));

        // Set label as accessory
        jf.setAccessory(image_view);

        // Accept only image files
        jf.setAcceptAllFileFilterUsed(false);

        // Create filter for image files
        FileNameExtensionFilter filter=new FileNameExtensionFilter
                ("176 Image Files","png");

        // Set it as current filter
        jf.setFileFilter(filter);

        // Add property change listener
        jf.addPropertyChangeListener(new PropertyChangeListener(){

            // When any JFileChooser property changes, this handler
            // is executed
            public void propertyChange(final PropertyChangeEvent pe)
            {
                // Create SwingWorker for smooth experience
                SwingWorker<Image,Void> worker=new SwingWorker<Image,Void>(){

                    // The image processing method
                    protected Image doInBackground()
                    {
                        // If selected file changes..
                        if(pe.getPropertyName().equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY))
                        {
                        // Get selected file
                        File f=jf.getSelectedFile();

                            try
                            {
                            // Create FileInputStream for file
                            FileInputStream fin=new FileInputStream(f);

                            // Read image from fin
                            BufferedImage bim=ImageIO.read(fin);

                            // Return the scaled version of image
                            return bim.getScaledInstance(178,170,BufferedImage.SCALE_FAST);

                            }catch(Exception e){
                                // If there is a problem reading image,
                                // it might not be a valid image or unable
                                // to read
                                image_view.setText("       No Preview Available");
                            }
                        }

                    return null;
                    }

                    protected void done()
                    {
                        try
                        {
                        // Get the image
                        Image i=get(1L,TimeUnit.NANOSECONDS);

                        // If i is null, go back!
                        if(i==null) return;

                        // Set icon otherwise
                        image_view.setIcon(new ImageIcon(i));
                        }catch(Exception e){
                            // Print error occured
                            image_view.setText(" Error occured.");
                        }
                    }
                };

                // Start worker thread
                worker.execute();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        image_holder = new javax.swing.JPanel();
        imageView = new JLabel();
        jLabel1 = new JLabel();
        open = new javax.swing.JButton();
        match = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        image_best = new JLabel();
        label_image_1 = new JLabel();
        jPanel4 = new javax.swing.JPanel();
        image_2 = new JLabel();
        jPanel2 = new javax.swing.JPanel();
        image_3 = new JLabel();
        label_image_2 = new JLabel();
        label_image_3 = new JLabel();
        label_image_1_acc = new JLabel();
        label_image_1_tag = new JLabel();
        label_image_3_acc = new JLabel();
        label_image_2_acc = new JLabel();
        label_image_2_tag = new JLabel();
        label_image_3_tag = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Machine Learning - Image Recognition");
        setBackground(new Color(255, 255, 255));
        setResizable(false);

        image_holder.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, Color.darkGray));
        image_holder.setName(""); // NOI18N

        imageView.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        imageView.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageView.setText("NO PREVIEW AVAILABLE");
        imageView.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout image_holderLayout = new javax.swing.GroupLayout(image_holder);
        image_holder.setLayout(image_holderLayout);
        image_holderLayout.setHorizontalGroup(
            image_holderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(image_holderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageView, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        image_holderLayout.setVerticalGroup(
            image_holderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(image_holderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageView, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel1.setText("File:");

        open.setBackground(new Color(66, 165, 245));
        open.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        open.setForeground(new Color(51, 51, 51));
        open.setText("Open File");
        open.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openMouseClicked(evt);
            }
        });

        match.setBackground(new Color(66, 165, 245));
        match.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        match.setForeground(new Color(51, 51, 51));
        match.setText("MATCH");
        match.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                matchMouseClicked(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, null, Color.darkGray));

        image_best.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        image_best.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        image_best.setText("NO IMAGE SELECTED");
        image_best.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(image_best, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(image_best, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        label_image_1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        label_image_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_image_1.setText("BEST MATCH");

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, Color.darkGray));

        image_2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        image_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        image_2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        image_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                image_2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(image_2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(image_2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, Color.darkGray));

        image_3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        image_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        image_3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        image_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                image_3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(image_3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(image_3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        label_image_2.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        label_image_2.setText("2");

        label_image_3.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        label_image_3.setText("3");

        label_image_1_acc.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        label_image_1_acc.setText("0.00%");

        label_image_1_tag.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        label_image_1_tag.setText("Image Name");

        label_image_3_acc.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        label_image_3_acc.setText("0.00%");

        label_image_2_acc.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        label_image_2_acc.setText("0.00%");

        label_image_2_tag.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        label_image_2_tag.setText("Image Name");

        label_image_3_tag.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        label_image_3_tag.setText("Image Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 25, Short.MAX_VALUE)
                        .addComponent(image_holder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(open))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(match, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(label_image_1_acc, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(label_image_1_tag, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(label_image_1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label_image_2)
                    .addComponent(label_image_3)
                    .addComponent(label_image_2_acc, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_image_2_tag, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label_image_3_tag, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label_image_3_acc, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_image_1_acc, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_image_1_tag, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label_image_2)
                        .addGap(18, 18, 18)
                        .addComponent(label_image_2_acc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(label_image_2_tag)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                        .addComponent(label_image_3)
                        .addGap(18, 18, 18)
                        .addComponent(label_image_3_acc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(label_image_3_tag)
                        .addContainerGap(170, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(open)
                    .addComponent(jLabel1)
                    .addComponent(label_image_1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(image_holder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(match, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void openMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_openMouseClicked
        // TODO add your handling code here:
      returnValue = jf.showOpenDialog(null);
        System.out.println(returnValue);
      if(returnValue == JFileChooser.APPROVE_OPTION){
          System.out.println("APPROVE");
          file = jf.getSelectedFile();

         try {
              FileInputStream fin = new FileInputStream(file);
              image = ImageIO.read(fin);
          } catch (FileNotFoundException ex) {
              Logger.getLogger(ml176.class.getName()).log(Level.SEVERE, null, ex);
          } catch (IOException ex) {
              Logger.getLogger(ml176.class.getName()).log(Level.SEVERE, null, ex);
          }

          Image dimg = image.getScaledInstance(imageView.getWidth(), imageView.getHeight(),
                  Image.SCALE_SMOOTH);

          imageView.setIcon(new ImageIcon(dimg));
          imageView.setText("");

          ready = true;
          System.out.println(file.getName());

        }
      else{
         // randomDefaultPics();
      }
    }//GEN-LAST:event_openMouseClicked

    private void matchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_matchMouseClicked
       //TODO: SHOW THE IMAGEICONS HERE, 3 ICONS.
          // pass the variable "image" (BufferedImage) to the main code
        if(ready){
            new DetectMain(file.getName(), image_best, image_2, image_3, label_image_1_tag, label_image_2_tag, label_image_3_tag);
        }


    }//GEN-LAST:event_matchMouseClicked

    private void image_2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_image_2MouseClicked
        // TODO add your handling code here:
        System.out.println("Switched on ImageView 2: No image switch implemented!");
        switchImageView(2);
    }//GEN-LAST:event_image_2MouseClicked

    private void image_3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_image_3MouseClicked
        // TODO add your handling code here:
        System.out.println("Switched on ImageView 3: No image switch implemented!");
        switchImageView(3);
    }//GEN-LAST:event_image_3MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ml176.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ml176.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ml176.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ml176.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ml176().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel imageView;
    private JLabel image_2;
    private JLabel image_3;
    private JLabel image_best;
    private javax.swing.JPanel image_holder;
    private JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    public JLabel label_image_1;
    private JLabel label_image_1_acc;
    private JLabel label_image_1_tag;
    public JLabel label_image_2;
    private JLabel label_image_2_acc;
    private JLabel label_image_2_tag;
    public JLabel label_image_3;
    private JLabel label_image_3_acc;
    private JLabel label_image_3_tag;
    private javax.swing.JButton match;
    private javax.swing.JButton open;
    // End of variables declaration//GEN-END:variables
}
