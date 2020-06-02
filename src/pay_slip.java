import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class pay_slip extends JFrame implements ActionListener{
    Choice c1;
    JTextArea t1;
    JButton b1;
    
    pay_slip(){
        setSize(800,600);
        setLocation(400,100);
        c1 = new Choice();
        try{
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("select * from salary");
            while(rs.next()){
                c1.add(rs.getString("id"));
            }
        }catch(Exception e){}
        
        setLayout(new BorderLayout());
        JPanel p1 = new JPanel();
        p1.add(new JLabel("Select ID"));
        p1.add(c1);
        add(p1,"North");
        
        t1 = new JTextArea(30,80);
        JScrollPane jsp = new JScrollPane(t1);
        Font f1 = new Font("arial",Font.BOLD,20);
        t1.setFont(f1);
        b1 = new JButton("Generate Pay Slip");
        
        add(b1,"South");
        add(jsp,"Center");
        b1.addActionListener(this);               
    }
    
    public void actionPerformed(ActionEvent ae){
        try{
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("select * from employee where id="+c1.getSelectedItem());
            rs.next();
            String name = rs.getString("name");
            rs.close();
            
            rs = c.s.executeQuery("select * from salary where id="+c1.getSelectedItem());
            double gross=0;
            double net=0;
            
            java.util.Date d1 = new java.util.Date();
            int month = d1.getMonth();
            t1.setText("\n------------------ PAY SLIP FOR MONTH OF "+month+" ,2020 -------------------\n");
            
            if(rs.next()){
                t1.append("\n     Employee ID     : "+rs.getString("id"));
                t1.append("\n     Employee Name : "+name);
                t1.append("\n------------------------------------------------------");
                t1.append("\n");
                
                double hra = rs.getDouble("hra");
                t1.append("\n          HRA            : "+hra);
                
                double da = rs.getDouble("da");
                t1.append("\n          DA              : "+da);
                
                double med = rs.getDouble("med");
                t1.append("\n          MED            : "+med);
                
                double pf = rs.getDouble("pf");
                t1.append("\n          PF              : "+pf);
                
                double basic= rs.getDouble("basic_salary");                
                t1.append("\n          Basic Salary  : "+basic);
                
                gross = hra+da+med+pf+basic;
                net = gross-pf;
                t1.append("\n------------------------------------------------------");
                t1.append("\n");
                
                t1.append("\n       Gross Salary    : "+gross);
                t1.append("\n       Net Salary        : "+net);
                t1.append("\n       Tax  : 2.1% of gross  "+ (gross*0.021));
                t1.append("\n------------------------------------------------------");
                t1.append("\n\n\n\n");
                t1.append("     ( Signature )    ");
                
            }
        }catch(Exception ee){
            ee.printStackTrace();
        }
    }
    public static void main(String[] args){
        new pay_slip().setVisible(true);
    }
    
}
