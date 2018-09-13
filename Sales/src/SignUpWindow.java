import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpWindow {

    private JFrame frame;
    private JTextField userNameTextField, fullNameTextField, passwordfield, ageTextField;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SignUpWindow window = new SignUpWindow();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public SignUpWindow() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 730, 489);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);

        JLabel userNameLabel = new JLabel("User Name");
        userNameLabel.setBounds(65, 31, 80, 14);
        frame.getContentPane().add(userNameLabel);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(65, 71, 80, 14);
        frame.getContentPane().add(passwordLabel);

        JLabel userTypeLabel = new JLabel("User Type");
        userTypeLabel.setBounds(65, 111, 80, 14);
        frame.getContentPane().add(userTypeLabel);

        userNameTextField = new JTextField();
        userNameTextField.setBounds(185, 31, 146, 21);
        frame.getContentPane().add(userNameTextField);
        userNameTextField.setColumns(10);

        passwordfield = new JPasswordField();
        passwordfield.setBounds (185, 70, 146, 21);//(185, 86, 146, 21);
        frame.getContentPane().add(passwordfield);
        passwordfield.setColumns(10);

        final JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.addItem("Select");
        comboBox.addItem("Customer");
        comboBox.addItem("Seller");
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        comboBox.setBounds(185, 110, 146, 21);
        frame.getContentPane().add(comboBox);


        JButton btnSubmit = new JButton("submit");

        btnSubmit.setBackground(Color.BLUE);
        btnSubmit.setForeground(Color.MAGENTA);
        btnSubmit.setBounds(185, 140, 146, 21);
        frame.getContentPane().add(btnSubmit);


        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                String userName = userNameTextField.getText().toString();
                String password = passwordfield.getText().toString();
                String userType = comboBox.getSelectedItem().toString();

                if(userNameTextField.getText().isEmpty()||(passwordfield.getText().isEmpty())||(comboBox.getSelectedItem().equals("Select")))
                    JOptionPane.showMessageDialog(null, "Data Missing");
                else {
                    if(BasicUser.checkUserNameDuplication(userName)) {
                        JOptionPane.showMessageDialog(null, "User name already exists.");
                    }
                    else if (!BasicUser.checkUserNameDuplication(userName)) {
                        JOptionPane.showMessageDialog(null, "Data Submitted");
                        if (userType.equals("Seller")) {
                            BasicUser newUser = new BasicUser(userName, password, 's');
                        }
                        else {
                            BasicUser newUser = new BasicUser(userName, password, 'c');
                        }
                    }
                }
            }
        });

    }
}

