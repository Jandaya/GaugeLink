package gl;


import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;
import java.awt.geom.AffineTransform;





//class for the circle
public class Circle extends JPanel implements ActionListener {

	double angle;
        int makeone;
        gaugeForm g;
	Timer timer;
        //Image im = new Image();
        
	Circle() {
		super(null);
                gl.gaugeForm gauge = new gl.gaugeForm();
                gauge.setVisible(true);
                g = gauge;
		timer = new Timer(100, this);
		timer.start();
	}
        @Override
	public void actionPerformed(ActionEvent e) {
                angle += g.speedNum/100;
		if(angle > (2* Math.PI))
			angle = 0.0;
		repaint();
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		int x = (int) (Math.cos(angle) * (width / 3) + (width / 2));
		int y = (int) (Math.sin(angle) * (height / 3) + (height / 2));
		//g.fillOval(x, y, 10, 10);
                BufferedImage img = null;
                    try {
                        img = ImageIO.read(new File("C:\\Users\\My\\Desktop\\comp135\\carsprite.png"));
                    } catch (IOException e) {
                    }
                double rotationRequired = Math.toRadians(x);
                if (x < 300){
                    rotationRequired = Math.toRadians(y);
                }
                
                double locationX = img.getWidth() / 2;
                double locationY = img.getHeight() / 2;
                AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

                // Drawing the rotated image at the required drawing locations
                g.drawImage(op.filter(img, null), x, y, null);
                //g.drawImage(img, x, y, this);
	}
	
	
}
