import java.awt.Button;  
import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;  
  
import javax.swing.*;  
public final class GridBagLayoutExample extends JFrame{  
    public static void main(String[] args) {  
            GridBagLayoutExample a = new GridBagLayoutExample();  
        }  
        public GridBagLayoutExample() {  
        	GridBagLayout grid = new GridBagLayout();  
            GridBagConstraints gbc = new GridBagConstraints();  
            setLayout(grid);  
            setTitle("GridBag Layout Example");  
            GridBagLayout layout = new GridBagLayout();  
    this.setLayout(layout);  
    gbc.fill = GridBagConstraints.HORIZONTAL;  
    gbc.gridx = 0;  
    gbc.gridy = 0;  
    this.add(new JLabel("User Name"), gbc); 
    gbc.gridx = 1;  
    gbc.gridy = 0;  
    this.add(new JTextField(), gbc); 
    
    gbc.fill = GridBagConstraints.HORIZONTAL;  
    gbc.ipady = 20;  
    gbc.gridx = 0;  
    gbc.gridy = 1;  
    this.add(new JLabel("Password"), gbc);  
    gbc.gridx = 1;  
    gbc.gridy = 1;  
    this.add(new JPasswordField(), gbc);  
    gbc.gridx = 0;  
    gbc.gridy = 56; 
    gbc.fill = GridBagConstraints.HORIZONTAL;  
    gbc.gridwidth = 2;  
    this.add(new Button("Button Five"), gbc);  
            setSize(300, 300);  
            setPreferredSize(getSize());  
            setVisible(true);  
            setDefaultCloseOperation(EXIT_ON_CLOSE);  
      
        }  
      
}  
