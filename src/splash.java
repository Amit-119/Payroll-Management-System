/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
        
public class splash {
    public static void main(String s[]){
        sframe f1 = new sframe("Payroll system free Licence version 1.0.1");
        f1.setVisible(true);
        int i;
        int x=1;
        for(i=2;i<=400;i+=8,x+=4){
            f1.setLocation((600-((i+x)/2)),400-(i/2));
            f1.setSize(i+x,i);
            try{
                Thread.sleep(10);
            }catch(Exception e){ }
        }
                
    }
}

class sframe extends JFrame implements Runnable{
    Thread t1;
    sframe(String s){
        super(s);
        setLayout(new FlowLayout());
        ImageIcon c1 = new ImageIcon(ClassLoader.getSystemResource("icon/payroll_system.jpg"));
        Image i1 = c1.getImage().getScaledInstance(800,600,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i1);
        
        JLabel m1 = new JLabel(i2);
        add(m1);
        t1 = new Thread(this);
        t1.start();
    }
    public void run(){
        try{
            Thread.sleep(5000);
            //this.setVisible(false);
            login f1 = new login();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
