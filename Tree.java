import javax.swing.*;
        import java.awt.*;
        import java.awt.geom.AffineTransform;

public class Tree extends Canvas {
    private static int width = 1700;
    private static int height = 960;
    private double R = 255;
    private double G = 0;
    private double B = 0;
    private double temp = 3;
    private int branches;

    private int iterations = 17;
    private double length = 270;
    private int angle1 = 8;
    private int angle2 = 8;
    private int angle3 = 24;
    private double shorten = 0.71;

    public void paint(Graphics g) {
        super.paint(g);
        double time = System.currentTimeMillis();
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, width, height);
        branches = 0;

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
        System.out.println((System.currentTimeMillis()-time)/1000);
        if(System.currentTimeMillis()-time <= 1000){
            try {
                Thread.sleep((long) (1000-(System.currentTimeMillis()-time)));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        repaint();
    }

    public void branch(Graphics2D g, double len, int iteration) {
        changeColors();
        g.setColor(new Color((int) R, (int) G, (int) B));
//        if(iteration/5F >= 0.5){
            g.setStroke(new BasicStroke(iteration/9F));
//        } else g.setStroke(new BasicStroke(0.5F));
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
//                g.rotate(Math.toRadians(-angle3));
//                branch(g, len * shorten, iteration);
//                g.setTransform(old);

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
//                branches(iteration, len * shorten);
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
        frame.setTitle("Tree");
        frame.setSize(width, height);
        Canvas canvas = new Tree();
        canvas.setSize(width, height);
        frame.add(canvas);
        frame.pack();
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
