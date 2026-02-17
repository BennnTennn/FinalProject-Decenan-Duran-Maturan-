package trial;
import javax.swing.*;
import java.awt.*;

public class AdminView extends JPanel {
    public AdminView(CardLayout cl, JPanel cardPanel) {
        super(new BorderLayout());
        setBackground(Color.decode("#D6C0E4"));

        JLabel title = new JLabel("INVENTORY (ADMIN VIEW)", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 40));
        add(title, BorderLayout.NORTH);

        // Grid of placeholders
        JPanel grid = new JPanel(new GridLayout(2, 2, 20, 20));
        for (int i = 1; i <= 4; i++) {
            JPanel item = new JPanel(new BorderLayout());
            item.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
            item.add(new JLabel("PLACE HOLDER " + i, SwingConstants.CENTER), BorderLayout.CENTER);
            item.add(new JLabel("Amount: ___", SwingConstants.CENTER), BorderLayout.SOUTH);
            grid.add(item);
        }
        add(grid, BorderLayout.CENTER);

        // Edit button
        JButton editButton = new JButton("Edit");
        editButton.setFont(new Font("SansSerif", Font.BOLD, 30));
        editButton.addActionListener(e -> cl.show(cardPanel, "Edit"));
        add(editButton, BorderLayout.SOUTH);
    }
}
