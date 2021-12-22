
package erictekelljavamysqlnov8;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import static java.awt.SystemColor.text;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class AddForm extends JFrame {
    
    JFrame jframe = new JFrame("Add");
    JLabel name = new JLabel();
    JLabel birthday = new JLabel();
    JLabel file = new JLabel();
    JLabel weight = new JLabel();
    JLabel race = new JLabel();
    JLabel gender = new JLabel();
    JLabel className = new JLabel();
    JTextField nameText = new JTextField();
    JTextField birthdayText = new JTextField();
    JTextField weightText = new JTextField();
    JSeparator s = new JSeparator(JSeparator.VERTICAL);
    JSeparator s1 = new JSeparator(JSeparator.HORIZONTAL);
    JCheckBox cbxHuman = new JCheckBox("Human");  
    JCheckBox cbxElf = new JCheckBox("Elf");
    JCheckBox cbxDwarf = new JCheckBox("Dwarf");
    JCheckBox cbxOrc = new JCheckBox("Orc");
    JRadioButton genderMale = new JRadioButton();
    JRadioButton genderFemale = new JRadioButton();
    ButtonGroup genderButtons = new ButtonGroup();
    JRadioButton classWarrior = new JRadioButton();
    JRadioButton classRogue = new JRadioButton();
    JRadioButton classRanger = new JRadioButton();
    JRadioButton classCleric = new JRadioButton();
    ButtonGroup classButtons = new ButtonGroup();
    JButton buttonViewData = new JButton();
    JButton buttonAddCharacter = new JButton();
    JButton buttonChooseImage = new JButton();
    ImageIcon img;
    JLabel image = new JLabel();
    Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
    public static String mySQLURL = "jdbc:mysql://cstnt.tstc.edu:3306/tekellefa21";
    public static String userName = "tekellefa21";
    public static String passw = "1517940";
    public static Connection con = null;
    JFileChooser jFileChooser = new JFileChooser("C:");
    File imageFile;
   
   
    
    
    
    public AddForm(){
        jframe.setSize(450,500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dim.getWidth() - jframe.getWidth()) / 2);
        int y = (int) ((dim.getHeight() - jframe.getHeight()) / 2);
        jframe.setLocation(x, y);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLayout(null);
        name.setText("Name: ");
        name.setBounds(25, 35, name.getPreferredSize().width, name.getPreferredSize().height);
        jframe.add(name);
        file.setText("File: ");
        file.setBounds(200, 365, 200, file.getPreferredSize().height);
        jframe.add(file);
        gender.setText("Gender: ");
        gender.setBounds(25, 135, gender.getPreferredSize().width, gender.getPreferredSize().height);
        jframe.add(gender);
         className.setText("Class: ");
        className.setBounds(25, 200, className.getPreferredSize().width, className.getPreferredSize().height);
        jframe.add(className);
        birthday.setText("Birthday: ");
        birthday.setBounds(15, 60, birthday.getPreferredSize().width, birthday.getPreferredSize().height);
        jframe.add(birthday);
        weight.setText("Weight: ");
        weight.setBounds(22, 85, weight.getPreferredSize().width, weight.getPreferredSize().height);
        jframe.add(weight);
        nameText.setBounds(75,35,100, 20);
        jframe.add(nameText);
        birthdayText.setBounds(75,60,100, 20);
        jframe.add(birthdayText);
        weightText.setBounds(75,85,100, 20);
        jframe.add(weightText);
        race.setText("Race: ");
        race.setBounds(225, 15, race.getPreferredSize().width, race.getPreferredSize().height);
        jframe.add(race);
        s.setBounds(200,0,125,125);
        s1.setBounds(0, 125, 450, 450);
        jframe.add(s);
        jframe.add(s1);
        cbxHuman.setBounds(275,15, 75,15);  
        cbxElf.setBounds(275,40, 75,15); 
        cbxDwarf.setBounds(275,65, 75,15); 
        cbxOrc.setBounds(275,90, 75,15); 
        jframe.add(cbxHuman);
        jframe.add(cbxElf);
        jframe.add(cbxDwarf);
        jframe.add(cbxOrc);
        genderMale.setText("Male");
        genderMale.setBounds(73, 133, 75, 20);
        genderMale.setActionCommand("Male");
        genderButtons.add(genderMale);
        genderFemale.setText("Female");
        genderFemale.setBounds(73, 153, 75, 20);
        genderFemale.setActionCommand("Female");
        genderButtons.add(genderFemale);
        classWarrior.setText("Warrior");
        classWarrior.setBounds(73, 198, 75, 20);
        classButtons.add(classWarrior);
        classRogue.setText("Rogue");
        classRogue.setBounds(73, 218, 75, 20);
        classButtons.add(classRogue);
        classRanger.setText("Ranger");
        classRanger.setBounds(73, 238, 75, 20);
        classButtons.add(classRanger);
        classCleric.setText("Cleric");
        classCleric.setBounds(73, 258, 75, 20);
        classButtons.add(classCleric);
        classWarrior.setActionCommand("Warrior");
        classRogue.setActionCommand("Rogue");
        classRanger.setActionCommand("Ranger");
        classCleric.setActionCommand("Cleric");
        jframe.add(genderMale);
        jframe.add(genderFemale);
        jframe.add(classWarrior);
        jframe.add(classRogue);
        jframe.add(classRanger);
        jframe.add(classCleric);
        buttonViewData.setText("View Data");
        buttonViewData.setBounds(20, 400, 100,25);
        buttonViewData.addActionListener((ActionEvent e)->{
            jframe.setVisible(false);
            AddViewForm viewForm = new AddViewForm();});
        jframe.add(buttonViewData);
        buttonAddCharacter.setText("Add Character");
        buttonAddCharacter.setBounds(140, 400, 120,25);
        buttonAddCharacter.addActionListener((ActionEvent e)->{makeDBConnection();});
        jframe.add(buttonAddCharacter);
         buttonChooseImage.setText("Choose Image");
        buttonChooseImage.setBounds(275, 400, 120,25);
        buttonChooseImage.addActionListener((ActionEvent e)->{int r = jFileChooser.showSaveDialog(null);
          if (r == JFileChooser.APPROVE_OPTION)
 
            {
                // set the label to the path of the selected file
                file.setText("File: " + jFileChooser.getSelectedFile().getName());
                imageFile = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                String filePath = jFileChooser.getSelectedFile().getAbsolutePath();
                img = new ImageIcon(new ImageIcon(filePath).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
                image.setIcon(img);
                image.setText("");
                image.setBorder(border);
                image.setBounds(175, 150, 200, 200);
                jframe.add(image);
            }
            // if the user cancelled the operation
          else{
                file.setText("No image selected");
        }
        });
        jframe.add(buttonChooseImage);
        //image.setIcon("");
        image.setText("");
        image.setBounds(175, 150, 200, 200);
         image.setBorder(border);
        jframe.add(image);
        jframe.setVisible(true);
    }
    public void makeDBConnection()
{
    try
    {
        if(con == null)
        {
        con = DriverManager.getConnection(mySQLURL, userName, passw);
        }
        String queryMaxID = "SELECT MAX(ProfileID) FROM tekellefa21.profile;";
        PreparedStatement ps1 = con.prepareStatement(queryMaxID);
        ResultSet maxProfileID = ps1.executeQuery();
        String maxProfileID1 ="";
        if (maxProfileID.next())
        {
        maxProfileID1 = maxProfileID.getString(1);
        }
        byte [] b = Files.readAllBytes(imageFile.toPath());
        String query = "INSERT INTO tekellefa21.profile VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, Integer.parseInt(maxProfileID1)+1);
        ps.setString(2, nameText.getText());
        if(!validBirthday())
        {
            JOptionPane.showMessageDialog(null, "Birthday Not Correct Format. MM/DD/YYYY", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        java.util.Date date = format.parse(birthdayText.getText());
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        ps.setDate(3, sqlDate);
        if(!validWeight())
        {
            JOptionPane.showMessageDialog(null, "Weight not correct format. Must be number as in X or X.XX.", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        BigDecimal weightInput = new BigDecimal(weightText.getText());
        ps.setBigDecimal(4, weightInput);
        String genderInput = genderButtons.getSelection().getActionCommand();
        boolean gender = false;
        if (genderInput == "Male")
        {
            gender = true;
        }
        ps.setBoolean(5, gender);
        String classInput = classButtons.getSelection().getActionCommand();
        ps.setString(6, classInput);
        String raceInput = getRace();
        ps.setString(7, raceInput);
        ps.setBinaryStream(8, new ByteArrayInputStream(b, 0, b.length));
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Record Added To Database", "Update Success", JOptionPane.INFORMATION_MESSAGE);
      
    }
    catch(Exception ex)
    {
        //JOptionPane.showMessageDialog(null, "Connection Failed! Invalid Credentials.", "Connection Failed", JOptionPane.INFORMATION_MESSAGE);
        
        ex.printStackTrace();
    }
}
    public boolean validBirthday()
{
    boolean validBirthday = false;
    String birthday = new String(birthdayText.getText());
    String regex = "^\\d{2}/\\d{2}/\\d{4}$";
    Pattern p = Pattern.compile(regex);
    if (birthday == null)
    {
        validBirthday = false;
        return false;
    }
    Matcher m = p.matcher(birthday);
    validBirthday = m.matches();
    return validBirthday;
}
public boolean validWeight()
{
    boolean validWeight = false;
    String weight = new String(weightText.getText());
    String regex = "^[0-9]*\\.?[0-9]+$";
    Pattern p = Pattern.compile(regex);
    if (weight == null)
    {
        validWeight = false;
        return false;
    }
    Matcher m = p.matcher(weight);
    validWeight = m.matches();
    return validWeight;
}
public String getRace()
{
    String raceInput = "";
    if (cbxHuman.isSelected())
    {
        raceInput += "Human ";
    }
    if (cbxElf.isSelected())
    {
        raceInput += "Elf ";
    }
    if(cbxDwarf.isSelected())
    {
        raceInput += "Dwarf ";
    }
    if (cbxOrc.isSelected())
    {
        raceInput += "Orc ";
    }
    return raceInput;
}
}
