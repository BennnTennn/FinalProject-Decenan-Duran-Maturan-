package trial;
import javax.swing.*;
import java.awt.*;

public class Buy extends JPanel {
    private int[] productAmounts;
    private JLabel[] amountLabels;

    public Buy(CardLayout cl, JPanel cardPanel) {
        super(new BorderLayout());
        setBackground(Color.decode("#D6C0E4"));

        productAmounts = new int[4];
        amountLabels = new JLabel[4];

        // Cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("SansSerif", Font.BOLD, 30));
        cancelButton.addActionListener(e -> cl.show(cardPanel, "Main"));
        add(cancelButton, BorderLayout.NORTH);

        // Grid of products
        JPanel grid = new JPanel(new GridLayout(2, 2, 20, 20));
        for (int i = 0; i < 4; i++) {
            int index = i;
            JPanel item = new JPanel(new BorderLayout());
            item.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

            // Product name
            JLabel productLabel = new JLabel("PLACE HOLDER " + (i+1), SwingConstants.CENTER);
            productLabel.setFont(new Font("SansSerif", Font.BOLD, 20));

            // Amount label
            JLabel amountLabel = new JLabel("Amount: 0", SwingConstants.CENTER);
            amountLabels[i] = amountLabel;

            // Buttons panel (Add / Remove)
            JPanel buttonPanel = new JPanel(new FlowLayout());
            JButton addButton = new JButton("+");
            JButton removeButton = new JButton("−");

            addButton.addActionListener(e -> {
                productAmounts[index]++;
                amountLabels[index].setText("Amount: " + productAmounts[index]);
            });

            removeButton.addActionListener(e -> {
                if (productAmounts[index] > 0) { // prevent negative
                    productAmounts[index]--;
                    amountLabels[index].setText("Amount: " + productAmounts[index]);
                }
            });

            buttonPanel.add(addButton);
            buttonPanel.add(removeButton);

            // Assemble item panel
            item.add(productLabel, BorderLayout.NORTH);
            item.add(buttonPanel, BorderLayout.CENTER);
            item.add(amountLabel, BorderLayout.SOUTH);

            grid.add(item);
        }
        add(grid, BorderLayout.CENTER);

        // Continue button
        JButton continueButton = new JButton("Continue");
        continueButton.setFont(new Font("SansSerif", Font.BOLD, 30));
        continueButton.addActionListener(e -> {
            int total = 0;
            for (int amt : productAmounts) total += amt;
            JOptionPane.showMessageDialog(this, "Total items: " + total);
        });
        add(continueButton, BorderLayout.SOUTH);
    }
}
