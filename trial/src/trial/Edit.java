package trial;
import javax.swing.*;
import java.awt.*;

public class Edit extends JPanel {
    public Edit(CardLayout cl, JPanel cardPanel) {
        super(new BorderLayout());
        setBackground(Color.decode("#E1D0B3"));

        // Cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("SansSerif", Font.BOLD, 30));
        cancelButton.addActionListener(e -> cl.show(cardPanel, "AdminView"));
        add(cancelButton, BorderLayout.NORTH);

        // Options
        JPanel options = new JPanel(new GridLayout(3, 1, 20, 20));
        options.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));

        JButton removeFruit = new JButton("REMOVE FRUIT");
        JButton editPrice = new JButton("EDIT PRICE");
        JButton addFruit = new JButton("ADD FRUIT");

        removeFruit.setFont(new Font("SansSerif", Font.BOLD, 30));
        editPrice.setFont(new Font("SansSerif", Font.BOLD, 30));
        addFruit.setFont(new Font("SansSerif", Font.BOLD, 30));

        options.add(removeFruit);
        options.add(editPrice);
        options.add(addFruit);

        add(options, BorderLayout.CENTER);
    }
}
