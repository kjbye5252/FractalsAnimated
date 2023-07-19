import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class TreeBuffer extends Canvas {
    private static int width = 1700;
    private static int height = 960;
    private double R = 255;
    private double G = 0;
    private double B = 0;
    private double temp = 2;
    private int branches;

    private int iterations = 9;
    private double length = 270;
    private double angle1 = 8;
    private double angle2 = 8;
    private double angle3 = 1;
    private double shorten = 0.71;

    public void paint(Graphics g) {
        Image offscreen = null;
        offscreen = createImage(width, height);
        double time = System.currentTimeMillis();
        Graphics2D g2d = (Graphics2D) offscreen.getGraphics();

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, width, height);
        branches = 0;
        angle1 += 0.2;
        angle2 += 0.08;
        angle3 += 0.03;
//        length += 0.04;
        R = 255;
        G = 0;
        B = 0;
//        iterations = 17;
//        length = 270;
//        angle1 = 8;
//        angle2 = 8;
////        angle3 = 0;
//        shorten = 0.71;
//        temp = 3;

        branches(iterations, length);
        g2d.translate(width / 2, height);
        branch(g2d, length, iterations);
        g.drawImage(offscreen,0,0,this);
        System.out.println((System.currentTimeMillis()-time)/1000);
        if(System.currentTimeMillis()-time <= 16.6){
            try {
                Thread.sleep((long) (16.6-(System.currentTimeMillis()-time)));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        repaint();

    }

    public void branch(Graphics2D g, double len, int iteration) {
        changeColors();
        g.setColor(new Color((int) R, (int) G, (int) B));
        if(iteration/5F >= 0.5){
            g.setStroke(new BasicStroke(iteration/4F));
        } else g.setStroke(new BasicStroke(0.5F));
        if(len >= 1){
            g.drawLine(0, 0, 0, -(int) len);
        } else g.drawLine(0, 0, 0, -1);
        g.translate(0, -(int) len);
        if (len >= 0.1) {
            if (iteration > 0) {
                iteration--;
                AffineTransform old = g.getTransform();

//                g.translate(0,len*0.4);
                g.rotate(Math.toRadians(-angle1));
                branch(g, len * shorten, iteration);
                g.setTransform(old);

//                g.translate(0,0);
                g.rotate(Math.toRadians(-angle3));
                branch(g, len * shorten, iteration);
                g.setTransform(old);

//                g.translate(0,len*0.2);
                g.rotate(Math.toRadians(angle2));
                branch(g, len * shorten, iteration);
                g.setTransform(old);
            }
        }
    }

    public void branches(int iteration, double len) {
        if (len >= 0.1) {
            if (iteration > 0) {
                iteration--;
                branches(iteration, len * shorten);
                branches(iteration, len * shorten);
                branches(iteration, len * shorten);
                branches++;
            }
        }
    }

    public void changeColors() {
        if (R == 255 && G < 255 && B == 0) {
            G += (255.0 / branches) * temp;
        } else if (R > 0 && G == 255 && B == 0) {
            R -= (255.0 / branches) * temp;
        } else if (R == 0 && G == 255 && B < 255) {
            B += (255.0 / branches) * temp;
        } else if (R == 0 && G > 0 && B == 255) {
            G -= (255.0 / branches) * temp;
        } else if (R < 255 && G == 0 && B == 255) {
            R += (255.0 / branches) * temp;
        } else if (R == 255 && G == 0 && B > 0) {
            B -= (255.0 / branches) * temp;
        }

        if (R > 255) {
            R = 255;
        } else if (G > 255) {
            G = 255;
        } else if (B > 255) {
            B = 255;
        } else if (R < 0) {
            R = 0;
        } else if (G < 0) {
            G = 0;
        } else if (B < 0) {
            B = 0;
        }
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        JFrame frame = new JFrame();
        frame.setTitle("TreeBuffer");
        frame.setSize(width, height);
        Canvas canvas = new TreeBuffer();
        canvas.setSize(width, height);
        frame.add(canvas);
        frame.pack();
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
