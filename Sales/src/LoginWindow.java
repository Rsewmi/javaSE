import javax.swing.*;
import java.awt.*;
//import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow {
    private JFrame loginFrame;
    private JTextField userNameTextField, fullNameTextField, passwordfield, ageTextField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginWindow window = new LoginWindow();
                    window.loginFrame.setVisible(true);
                    window.loginFrame.setSize (600,300);
                    window.loginFrame.setTitle("Login Window");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LoginWindow() {
        initialize();
    }

    private void initialize() {
        loginFrame = new JFrame();
        loginFrame.setBounds(100, 100, 730, 489);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.getContentPane().setLayout(null);
        loginFrame.setLocationRelativeTo(null);

        JLabel userNameLabel = new JLabel("User Name");
        userNameLabel.setBounds(65, 31, 80, 14);
        loginFrame.getContentPane().add(userNameLabel);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(65, 71, 80, 14);
        loginFrame.getContentPane().add(passwordLabel);

        userNameTextField = new JTextField();
        userNameTextField.setBounds(185, 31, 146, 21);
        loginFrame.getContentPane().add(userNameTextField);
        userNameTextField.setColumns(10);

        passwordfield = new JPasswordField();
        passwordfield.setBounds (185, 70, 146, 21);//(185, 86, 146, 21);
        loginFrame.getContentPane().add(passwordfield);
        passwordfield.setColumns(10);

        //submit button
        JButton btnSubmit = new JButton("submit");

        btnSubmit.setBackground(Color.BLUE);
        btnSubmit.setForeground(Color.MAGENTA);
        btnSubmit.setBounds(185, 140, 146, 21);
        loginFrame.getContentPane().add(btnSubmit);

        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                String userName = userNameTextField.getText();
                String password = passwordfield.getText();

                if(userNameTextField.getText().isEmpty()||(passwordfield.getText().isEmpty()))
                    JOptionPane.showMessageDialog(null, "Username or password empty");
                else {
                    if (BasicUser.login(userName,password)){
                      //check the userType and load the relative window
                        BasicUser.loadWindow(userName);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Please try again later");
                    }
                }
            }
        });

        //signup button
        JButton btnSignup = new JButton("Sign Up");

        btnSignup.setBackground(Color.BLUE);
        btnSignup.setForeground(Color.MAGENTA);
        btnSignup.setBounds(385, 140, 146, 21);
        loginFrame.getContentPane().add(btnSignup);

        btnSignup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                try {
                    SignUpWindow.openSignUpWindow();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
