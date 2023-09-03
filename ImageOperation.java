import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
public class ImageOperation
{

    public static void operate(int key)
    {
        JFileChooser fileChooser=new JFileChooser();
        fileChooser.showOpenDialog(null);//taki dialog box open ho center me
        File file=fileChooser.getSelectedFile();//jo selected file hogi woh mujhe file me milegi 
        ///file se data read karne ke liye
        try
        {
            FileInputStream fis=new FileInputStream(file);

            byte data[]=new byte[fis.available()];//byte ki array banegi jiski lfength 
            //image ka data array me aayega jo number ki form me
            fis.read(data);

            //bytes ko encryt karne ke liye 
            int i=0;
            for(byte b:data)
            {
                System.out.println(b);
                data[i]=(byte)(b^key);//image ke har ek number ko key ke saath XOR kiya phir number ke same location pe new data ko store kiya   
                i++;
            }

            //is encryted data ko write karenge
            FileOutputStream fos=new FileOutputStream(file);
            fos.write(data);////encrypted data ko humne wapas file me write kiya purana dat hat gaya
            fos.close();
            fis.close();
            JOptionPane.showMessageDialog(null,"Done");//encrytion  khatam ho gaya karke
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) 
    {
        // System.out.println("hi piyush");

        JFrame f=new JFrame();
        f.setTitle("Image Operation");
        f.setSize(500, 500);
        f.setLocationRelativeTo(null);//center pe window open ho
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//cross pe click karo toh close ho jaye

        //setting font
        Font font=new Font("Times New Roman",Font.BOLD,25);
        //creating button
        JButton button=new JButton();
        button.setText("Select Image");
        button.setFont(font);

        //creating textfield

        JTextField textfield=new JTextField(10);
        textfield.setFont(font);

        button.addActionListener(e->
        {
            String text=textfield.getText();
            int num=Integer.parseInt(text);//text ko number me convert karta hai
            operate(num);
        });//button ko click karne par jo action ko perform karne ke liye

        f.setLayout(new FlowLayout());//This assigns the FlowLayout as the layout manager for the container f. As a result, any components added to f will be arranged horizontally in a row, and when the available space is filled, they will wrap to the next row.

        f.add(button);
        f.add(textfield);

        f.setVisible(true);//window dikhne ke liye har time
    }
}