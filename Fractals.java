import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Fractals extends Canvas {
    private static int width = 1920;
    private static int height = 1080;
    private double R = 255;
    private double G = 0;
    private double B = 0;
    private double temp = 4;
    private int branches;

    private int fractal = 0; // 0-Tree, 1-Circle, 2-Cross, 3-Triforce //
    private int iterations = 16;
    private double length = 200;
    private int angle1 = 40;
    private int angle2 = 40;
    private double shorten = 0.7;

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.white);
        branches = 0;

//        R = 255;
//        G = 0;
//        B = 0;


        iterations = 10;
        length = 250;
        angle1 = 70;
        angle2 = 70;
        shorten = 0.7;

        branches(iterations, length);
        g2d.translate(width / 2, height);
        branch(g2d, length, iterations);


//        if (fractal == 0) {
//
//            iterations = 10;
//            length = 250;
//            angle1 = 70;
//            angle2 = 70;
//            shorten = 0.7;
//
//            branches(iterations, length);
//            g2d.translate(width / 2, height);
//            branch(g2d, length, iterations);
//        } else if (fractal == 1) {
//
//            iterations = 20;
//            length = 400;
//            angle1 = 100;
//            angle2 = 100;
//            shorten = 0.6;
//
//            branches(iterations, length);
//            g2d.translate(width / 2, height / 2);
//            circle(g2d, length, iterations);
//        } else if (fractal == 2) {
//
//            iterations = 10;
//            length = 300;
//            shorten = 0.5;
//            temp = 1.5;
//
//            branches(iterations, length);
//            g2d.translate(width / 2, height / 2);
//            cross(g2d, length, iterations);
//        }
    }

    public void branch(Graphics2D g, double len, int iteration) {
//        changeColors();
//        g.setColor(new Color(0,0,0));
//        g.setColor(new Color((int) R, (int) G, (int) B));
        g.drawLine(0, 0, 0, -(int) len);
        g.translate(0, -(int) len);
        if (len >= 1) {
            if (iteration > 0) {
                iteration--;
                AffineTransform old = g.getTransform();
                g.translate(0,len*0.4);
                g.rotate(Math.toRadians(-angle1));
                branch(g, len * shorten, iteration);
                g.setTransform(old);
                g.rotate(Math.toRadians(-0));
                branch(g, len * shorten, iteration);
                g.setTransform(old);
                g.translate(0,len*0.2);
                g.rotate(Math.toRadians(angle2));
                branch(g, len * shorten, iteration);
                g.setTransform(old);
            }
        }
    }

//    public void circle(Graphics2D g, double len, int iteration) {
//        changeColors();
//        g.setColor(new Color((int) R, (int) G, (int) B));
//        g.drawOval((int) (0 - len / 2), (int) (0 - len / 2), (int) len, (int) len);
//        if (len >= 1) {
//            if (iteration > 0) {
//                iteration--;
//                AffineTransform old = g.getTransform();
//                g.translate((int) (-len / 2) * (angle1 / 100.0), 0);
//                circle(g, len * shorten, iteration);
//                g.setTransform(old);
//                g.translate((int) (len / 2) * (angle2 / 100.0), 0);
//                circle(g, len * shorten, iteration);
//                g.setTransform(old);
//            }
//        }
//    }
//
//    public void cross(Graphics2D g, double len, int iteration) {
//        if (iteration != iterations) {
//            changeColors();
//            g.setColor(new Color((int) R, (int) G, (int) B));
//            g.drawLine(0, 0, 0, -(int) len);
//            g.translate(0, -(int) len);
//        }
//        if (len >= 1) {
//            if (iteration > 0) {
//                iteration--;
//                AffineTransform old = g.getTransform();
//                g.rotate(Math.toRadians(-90));
//                cross(g, len * shorten, iteration);
//                g.setTransform(old);
//                g.rotate(Math.toRadians(0));
//                cross(g, len * shorten, iteration);
//                g.setTransform(old);
//                g.rotate(Math.toRadians(90));
//                cross(g, len * shorten, iteration);
//                g.setTransform(old);
//                if (iteration == iterations - 1) {
//                    g.rotate(Math.toRadians(180));
//                    cross(g, len * shorten, iteration);
//                    g.setTransform(old);
//                }
//            }
//        }
//    }

    public void branches(int iteration, double len) {
        if (len >= 1) {
            if (iteration > 0) {
                iteration--;
                branches(iteration, len * shorten);
                branches(iteration, len * shorten);
                branches(iteration, len * shorten);
                branches++;
            }
        }
    }

//    public void changeColors() {
//        if (R == 255 && G < 255 && B == 0) {
//            G += (255.0 / branches) * temp;
//        } else if (R > 0 && G == 255 && B == 0) {
//            R -= (255.0 / branches) * temp;
//        } else if (R == 0 && G == 255 && B < 255) {
//            B += (255.0 / branches) * temp;
//        } else if (R == 0 && G > 0 && B == 255) {
//            G -= (255.0 / branches) * temp;
//        } else if (R < 255 && G == 0 && B == 255) {
//            R += (255.0 / branches) * temp;
//        } else if (R == 255 && G == 0 && B > 0) {
//            B -= (255.0 / branches) * temp;
//        }
//
//        if (R > 255) {
//            R = 255;
//        } else if (G > 255) {
//            G = 255;
//        } else if (B > 255) {
//            B = 255;
//        } else if (R < 0) {
//            R = 0;
//        } else if (G < 0) {
//            G = 0;
//        } else if (B < 0) {
//            B = 0;
//        }
//    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Fractal");
        frame.setSize(width, height);
        Canvas canvas = new Fractals();
        canvas.setSize(width, height);
        frame.add(canvas);
        frame.pack();
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
