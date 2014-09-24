import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * My very simply password generator.
 */

/**
 * Created with IntelliJ IDEA.
 * User: Vadym Kryshyna
 * Date: 18.09.14
 * Time: 10:56
 * To change this template use File | Settings | File Templates.
 */
public class Main extends JFrame {
    private JCheckBox checkNumber;
    private JCheckBox checkBig;
    private JCheckBox checkLittle;
    private JCheckBox checkSpecial;
    private JButton generateBatton;
    private JLabel text;
    private JTextField generatePassword;
    private JTextField passwordLength;

    public static void main(String []args){
        Main main = new Main();
        main.setVisible(true);
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public Main(){
        super("Password generator");
        this.setSize(200, 400);
        checkNumber = new JCheckBox();
        checkBig = new JCheckBox();
        checkLittle = new JCheckBox();
        checkSpecial = new JCheckBox();
        generateBatton = new JButton("Generate password");
        text = new JLabel("Please select params:");
        passwordLength = new JTextField("5");
        generatePassword = new JTextField();

        checkNumber.setText("Include number");
        checkBig.setText("Include big letter");
        checkLittle.setText("Include little letter");
        checkSpecial.setText("Include special chars");
        checkNumber.setSelected(true);

        this.setLayout(new GridLayout(9, 1));
        this.add(text);
        this.add(checkNumber);
        this.add(checkBig);
        this.add(checkLittle);
        this.add(checkSpecial);
        this.add(passwordLength);
        this.add(generateBatton);
        this.add(generatePassword);

        generateBatton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((checkNumber.isSelected() | checkBig.isSelected() | checkLittle.isSelected() | checkSpecial.isSelected()) == true) {
                    char[][] tableChars = getTable(checkNumber.isSelected(), checkBig.isSelected(), checkLittle.isSelected(), checkSpecial.isSelected());
                    int length = Integer.parseInt(passwordLength.getText());
                    String password = generate(tableChars, length);
                    generatePassword.setText(password);
                } else {
                    JOptionPane.showMessageDialog(null, "Please check same chars for your password.");
                }

            }
        });
    }

    // create table with selected chars
    private char[][] getTable(boolean num, boolean big, boolean little, boolean special){
        int size = 0;
        if (num == true){   size++; }
        if (big == true){   size++; }
        if (little == true){   size++; }
        if (special == true){   size++; }
        char [][] chars = new char[size][];
        if (num == true){
            size--;
            char []temp = {'1','2','3','4','5','6','7','8','9','0'};
            chars[size] = temp;
        }
        if (big == true){
            size--;
            char []temp = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
            chars[size] = temp;
        }
        if (little == true){
            size--;
            char []temp = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
            chars[size] = temp;
        }
        if (special == true){
            size--;
            char []temp = {'!','@','#','$','%','^','&','*','(',')','-','_','+','=','[',']','{','}','?','/','.',','};
            chars[size] = temp;
        }
        System.out.println(chars.toString());
        return chars;
    }

    //random select chars for password
    private String generate(char chars[][], int length){
        char []password = new char[length];
        for (int i = 0; i < password.length; i++){
            Random rand = new Random();
            int j = rand.nextInt(chars.length);
            int k = rand.nextInt(chars[j].length);
            password[i] = chars[j][k];
        }

        return String.valueOf(password);
    }
}
