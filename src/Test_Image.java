import   java.awt.*; 
import   java.awt.BorderLayout; 
import   java.awt.Component; 
import   java.awt.Graphics; 
import   java.awt.Graphics2D; 
 
import   javax.swing.Icon; 
import   javax.swing.ImageIcon; 
import   javax.swing.JFrame; 
import   javax.swing.JLabel;
 
public   class   Test_Image   implements   Icon   { 
        private   Icon   icon   =   null; 
 
        public   Test_Image(Icon   icon)   { 
                this.icon   =   icon; 
//                icon=new ImageIcon(i);
        } 
 
        public   int   getIconHeight()   { 
                return   icon.getIconHeight(); 
        } 
 
        public   int   getIconWidth()   { 
                return   icon.getIconWidth(); 
        } 
 
        public   void   paintIcon(Component   c,   Graphics   g,   int   x,   int   y)   { 
                float   wid   =   c.getWidth(); 
                float   hei   =   c.getHeight(); 
                int   iconWid   =   icon.getIconWidth(); 
                int   iconHei   =   icon.getIconHeight(); 
 
                Graphics2D   g2d   =   (Graphics2D)   g; 
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,   
                        RenderingHints.VALUE_INTERPOLATION_BILINEAR); 
                g2d.scale(wid/iconWid,   hei/iconHei); 
                icon.paintIcon(c,   g2d,   0,   0); 
        }
 
        public   static   void   main(String[]   args)   { 
                Test_Image   icon   =   new   Test_Image(new   ImageIcon( "C:/Users/xiexy/Pictures/543.jpg")); 
                JLabel   label   =   new   JLabel(icon); 
                JFrame   frame   =   new   JFrame(); 
                frame.getContentPane().add(label,   BorderLayout.CENTER); 
//                frame.getContentPane().add(new JButton("click"),BorderLayout.NORTH);
                frame.setSize(1024,640); 
                frame.setLocationRelativeTo(null); 
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
                frame.setVisible(true); 
        } 
} 