import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class login extends JFrame implements ActionListener{
    JLabel l1,l2;
    JTextField t1;
    JPasswordField t2;
    JButton b1,b2;
    
    login(){
        super("Login Page");
        setLayout(new BorderLayout());
        t1 = new JTextField(10);
        t2 = new JPasswordField(10);
        JLabel l = new JLabel(new ImageIcon(ClassLoader.getSystemResource("icon\\defaultpic.png")));
        
        b1 = new JButton("Submit",new ImageIcon(ClassLoader.getSystemResource("icon\\login.png")));
        b2 = new JButton("Cancel",new ImageIcon(ClassLoader.getSystemResource("icon\\cancel.png")));
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        JPanel p1,p2,p3,p4;
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        
        //ImageIcon c1 = new ImageIcon("login.png");
        //JLabel m1 = new JLabel(c1);
        //p1.add(m1);
        
        add(l,BorderLayout.WEST);
        
        p2.add(new JLabel("User Name "));
        p2.add(t1);
        p2.add(new JLabel("Password "));
        p2.add(t2);
        add(p2,BorderLayout.CENTER);
        
        p4.add(b1);
        p4.add(b2);
        add(p4,BorderLayout.SOUTH);
        
        setSize(350,250);
        setLocation(400,200);
        setVisible(true);        
    }
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource()==b1){
            try{
             conn c1 = new conn();
             String u = t1.getText();
             String v = t2.getText();

             String q = "select * from login where username='"+u+"' and password='"+v+"'";
             ResultSet rs = c1.s.executeQuery(q);
             if(rs.next()){
                 new project().setVisible(true);
                 setVisible(false);
             }
             else{
                 JOptionPane.showMessageDialog(null,"Invalid Login");
                 //setVisible(false);

             }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if(ae.getSource()==b2){
            try{
                conn c1 = new conn();
                JOptionPane.showMessageDialog(null,"Cancelled");
                this.setVisible(false);
            }catch(Exception ee){
                ee.printStackTrace();
            }           
        }
    }
    
    public static void main(String[] args){
         new login();   
        }   
}