package trial;
import javax.swing.*;
import java.awt.*;

public class Admin extends JPanel {
    public Admin(CardLayout cl, JPanel cardPanel) {
        super(new BorderLayout());
        setBackground(Color.decode("#C0D6E4"));

        // Title
        JLabel title = new JLabel("ADMIN LOGIN", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 50));
        add(title, BorderLayout.NORTH);

        // Login form
        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));
        form.add(new JLabel("Username:"));
        JTextField username = new JTextField();
        form.add(username);
        form.add(new JLabel("Password:"));
        JPasswordField password = new JPasswordField();
        form.add(password);

        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");
        form.add(loginButton);
        form.add(cancelButton);

        add(form, BorderLayout.CENTER);

        // Actions
        loginButton.addActionListener(e -> {
            // For now, skip validation and go straight to AdminView
            cl.show(cardPanel, "AdminView");
        });
        cancelButton.addActionListener(e -> cl.show(cardPanel, "Main"));
    }
}
