
package erictekelljavamysqlnov8;

import static erictekelljavamysqlnov8.AddForm.con;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.InputStream;
import static java.nio.file.Files.list;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AddViewForm extends JFrame{
    
    JFrame jframe = new JFrame("Add");
    JList jList = new JList();
    JScrollPane sp;
    JLabel name = new JLabel();
    JLabel birthday = new JLabel();
    JLabel weight = new JLabel();
    JLabel gender = new JLabel();
    JLabel classType = new JLabel();
    JLabel race = new JLabel();
    ImageIcon img;
    JLabel image = new JLabel();  
    Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
    JTextField nameText = new JTextField();
    JTextField birthdayText = new JTextField();
    JTextField weightText = new JTextField();
    JTextField genderText = new JTextField();
    JTextField classText = new JTextField();
    JTextField raceText = new JTextField();
    JButton buttonAddForm = new JButton();
    
    public AddViewForm()
    {
        jframe.setSize(450,500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dim.getWidth() - jframe.getWidth()) / 2);
        int y = (int) ((dim.getHeight() - jframe.getHeight()) / 2);
        jframe.setLocation(x, y);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLayout(null);
        DefaultListModel model = new DefaultListModel(); 
        String query = "SELECT Name, Birthday, Weight, Gender, Class, Race, Img FROM tekellefa21.profile;";
        try
        {
             if(AddForm.con == null)
        {
        AddForm.con = DriverManager.getConnection(AddForm.mySQLURL, AddForm.userName, AddForm.passw);
        }
        Statement statement = AddForm.con.createStatement();
        ResultSet resultSet = statement.executeQuery(query); 
        
        ArrayList <Profile> dbProfile = new ArrayList<Profile>();
         
        while (resultSet.next())
        {
            
         Profile profile = new Profile();
         profile.name = (resultSet.getString("Name")); 
         profile.birthday = (resultSet.getDate("Birthday"));
         profile.weight = (resultSet.getBigDecimal("Weight"));
         profile.gender = (resultSet.getBoolean("Gender"));
         profile.classType = (resultSet.getString("Class"));
         profile.race = (resultSet.getString("Race"));
         profile.img = (resultSet.getBlob("Img"));
         dbProfile.add(profile);
         model.addElement(profile.name);
        }
       
        jList.setModel(model);
        jList.addListSelectionListener((ListSelectionEvent e)->{
           
          
            if(e.getValueIsAdjusting() == true)
            {
            int index = jList.getSelectedIndex();
            nameText.setText(dbProfile.get(index).name);
            Date date = dbProfile.get(index).birthday;  
            DateFormat dateFormat = new SimpleDateFormat("MM\\dd\\yyyy"); 
            String strDate = dateFormat.format(date);  
            birthdayText.setText(strDate);
            weightText.setText(dbProfile.get(index).weight.toString());
            String gender = "unknown";
            if (dbProfile.get(index).gender == true)
            {
                gender = "Male";
            }
            else
            {
                gender = "Female";
            }
            genderText.setText(gender);
            classText.setText(dbProfile.get(index).classType);
            raceText.setText(dbProfile.get(index).race);
            // Extract the stream from the blob object
            try
            {
            InputStream inStream = dbProfile.get(index).img.getBinaryStream(1, dbProfile.get(index).img.length());
             // convert it to a byte array so Java can work with it
            byte[] b = inStream.readAllBytes();  
            img = new ImageIcon(new ImageIcon(b).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
                image.setIcon(img);
                image.setText("");
                image.setBorder(border);
                image.setBounds(125, 275, 150, 150);
            }
            catch(Exception ex)
             {
                        JOptionPane.showMessageDialog(null, "Image Reading Error!", "Error", JOptionPane.INFORMATION_MESSAGE);
             }

           
            }
        
            });  
        resultSet.close();
        statement.close();
        }
        catch(Exception ex)
        {
        ex.printStackTrace();
        }
        jList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jList.setLayoutOrientation(JList.VERTICAL);
        jList.setVisibleRowCount(-1);

        sp = new JScrollPane(jList);
        sp.setBounds(125, 25, 200, 50);
        jframe.add(sp);
        name.setText("Name: ");
        name.setBounds(40, 100, name.getPreferredSize().width, name.getPreferredSize().height);
        jframe.add(name);
        birthday.setText("Birthday: ");
        birthday.setBounds(28, 130, birthday.getPreferredSize().width, birthday.getPreferredSize().height);
        jframe.add(birthday);
        weight.setText("Weight: ");
        weight.setBounds(33, 160, weight.getPreferredSize().width, weight.getPreferredSize().height);
        jframe.add(weight);
        gender.setText("Gender: ");
        gender.setBounds(31, 190, gender.getPreferredSize().width, gender.getPreferredSize().height);
        jframe.add(gender);
        classType.setText("Class: ");
        classType.setBounds(40, 220, classType.getPreferredSize().width, classType.getPreferredSize().height);
        jframe.add(classType);
        race.setText("Race: ");
        race.setBounds(41, 250, race.getPreferredSize().width, race.getPreferredSize().height);
        jframe.add(race);
         image.setText("");
        image.setBounds(125, 275, 150, 150);
         image.setBorder(border);
        jframe.add(image);
        nameText.setBounds(90,100,175, 20);
        jframe.add(nameText);
        birthdayText.setBounds(90,130,175, 20);
        jframe.add(birthdayText);
        weightText.setBounds(90,160,175, 20);
        jframe.add(weightText);
        genderText.setBounds(90,190,175, 20);
        jframe.add(genderText);
        classText.setBounds(90,220,175, 20);
        jframe.add(classText);
        raceText.setBounds(90,250,175, 20);
        jframe.add(raceText);
         buttonAddForm.setText("Add Form");
        buttonAddForm.setBounds(300, 400, 120,25);
        buttonAddForm.addActionListener((ActionEvent e)->{
            jframe.setVisible(false);
            AddForm addForm = new AddForm();
        });
        jframe.add(buttonAddForm);
        jframe.setVisible(true);
    }
    
   
}
