import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Password_Generator_App extends JFrame{

    private JCheckBox lowerCaseCheckBox;
    private JCheckBox upperCaseCheckBox;
    private JCheckBox numbersCheckBox;
    private JCheckBox specialCharCheckBox;
    private JSpinner lengthSpinner;
    private JTextField passwordText;
    private JButton generateBtn;

    // set up  JFrame properties
    public Password_Generator_App() {
        setTitle("p4ssw0rd g3n3r4t0r");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        initialize();


    }
    // checkboxes for character type
    private void initialize(){
        lowerCaseCheckBox = new JCheckBox("Include lower case");
        upperCaseCheckBox = new JCheckBox("Include upper case");
        numbersCheckBox = new JCheckBox("Include numbers");
        specialCharCheckBox = new JCheckBox("Include special characters");

        lowerCaseCheckBox.setFocusPainted(false);
        lowerCaseCheckBox.setBorderPainted(false);
        lowerCaseCheckBox.setCursor(new Cursor(Cursor.HAND_CURSOR));

        upperCaseCheckBox.setFocusPainted(false);
        upperCaseCheckBox.setBorderPainted(false);
        upperCaseCheckBox.setCursor(new Cursor(Cursor.HAND_CURSOR));

        numbersCheckBox.setFocusPainted(false);
        numbersCheckBox.setBorderPainted(false);
        numbersCheckBox.setCursor(new Cursor(Cursor.HAND_CURSOR));

        specialCharCheckBox.setFocusPainted(false);
        specialCharCheckBox.setBorderPainted(false);
        specialCharCheckBox.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // spinner for length
        lengthSpinner = new JSpinner(new SpinnerNumberModel(8,4,30,1));

        // text to display generated password
        passwordText = new JTextField(20);
        passwordText.setFont(new Font("Arial", Font.PLAIN, 12));
        passwordText.setEditable(false);

        // button to generate
        generateBtn = new JButton("Generate password");
        generateBtn.setBackground(new Color(60, 90, 180));
        generateBtn.setForeground(Color.lightGray);
        generateBtn.setFocusPainted(false);
        generateBtn.setBorderPainted(false);
        generateBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        generateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generatePassword();
            }

        });
        // UI
        JPanel mainPanel = new JPanel(new GridLayout(8,1,10,10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        mainPanel.setBackground(Color.lightGray);

        mainPanel.add(lowerCaseCheckBox);
        mainPanel.add(upperCaseCheckBox);
        mainPanel.add(numbersCheckBox);
        mainPanel.add(specialCharCheckBox);
   
        JPanel lengthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lengthPanel.setBackground(Color.lightGray);
        lengthPanel.add(new JLabel("Password length"));
        lengthPanel.add(lengthSpinner);
        mainPanel.add(lengthPanel);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.setBackground(Color.lightGray);
        btnPanel.add(generateBtn);
        mainPanel.add(btnPanel);
        mainPanel.add(passwordText);

        getContentPane().setBackground(Color.lightGray);
        add(mainPanel);
    }

    private String generatePassword() {
     //get password length from spinner above
        int passwordLength = (int) lengthSpinner.getValue();
        // characters for generated password
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String specialChars = "!@#$%^&*()_+-=[]{}|;:,.<>?";
        String characters = "";

        // based on selection, initialize character string
        if(lowerCaseCheckBox.isSelected()) characters += lowerCase;
        if(upperCaseCheckBox.isSelected()) characters += upperCase;
        if(numbersCheckBox.isSelected()) characters += numbers;
        if(specialCharCheckBox.isSelected()) characters += specialChars;
        
        // error message if nothing is selected
        if(characters.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error, no selection has been made.");
            return "";
        }
    
       
    // selecting random characters to select password
    Random random = new Random();
    StringBuilder password = new StringBuilder();
    
    for(int i = 0; i < passwordLength; i++)
    {
       int randomIndex = random.nextInt(characters.length());
       char randomChar = characters.charAt(randomIndex);
       password.append(randomChar);
    }

    // display password that has been generated
    passwordText.setText(password.toString());
    return password.toString();
}
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
        
            try
            {
                UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
            Password_Generator_App app = new Password_Generator_App();
            app.setVisible(true);
        
        });
        
        
    }

}