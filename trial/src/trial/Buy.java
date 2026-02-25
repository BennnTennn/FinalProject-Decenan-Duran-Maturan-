package trial;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Buy extends JPanel {
    private Map<String, Integer> productAmounts = new HashMap<>();
    private Map<String, JLabel> amountLabels = new HashMap<>();
    private Map<String, Double> productPrices = new HashMap<>();

    public Buy(CardLayout cl, JPanel cardPanel) {
        super(new BorderLayout());
        setBackground(Color.decode("#D6C0E4"));

        // Cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("SansSerif", Font.BOLD, 30));
        cancelButton.addActionListener(e -> cl.show(cardPanel, "Main"));
        add(cancelButton, BorderLayout.NORTH);

        // Read fruits from file
        List<String[]> fruits = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("fruit.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    fruits.add(parts);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Dynamic grid (2 columns, enough rows)
        int fruitCount = fruits.size();
        int rows = (int) Math.ceil(fruitCount / 2.0);
        JPanel grid = new JPanel(new GridLayout(rows, 2, 20, 20));

        // Build fruit panels
        for (String[] parts : fruits) {
            String name = parts[0].trim();
            double price = Double.parseDouble(parts[1].trim());
            int stock = Integer.parseInt(parts[2].trim());

            productAmounts.put(name, 0);
            productPrices.put(name, price);

            JPanel item = new JPanel(new BorderLayout());
            item.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

            // Image (optional, if file exists)
            ImageIcon icon = new ImageIcon("images/" + name.toLowerCase() + ".png");
            JLabel productLabel = new JLabel(name + " ($" + price + ")", icon, SwingConstants.CENTER);

            // Amount label
            JLabel amountLabel = new JLabel("Amount: 0 / Stock: " + stock, SwingConstants.CENTER);
            amountLabels.put(name, amountLabel);

            // Buttons
            JPanel buttonPanel = new JPanel(new FlowLayout());
            JButton addButton = new JButton("+");
            JButton removeButton = new JButton("−");

            addButton.addActionListener(e -> {
                int current = productAmounts.get(name);
                if (current < stock) {
                    productAmounts.put(name, current + 1);
                    amountLabel.setText("Amount: " + (current + 1) + " / Stock: " + stock);
                }
            });

            removeButton.addActionListener(e -> {
                int current = productAmounts.get(name);
                if (current > 0) {
                    productAmounts.put(name, current - 1);
                    amountLabel.setText("Amount: " + (current - 1) + " / Stock: " + stock);
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
            StringBuilder summary = new StringBuilder("Your order:\n");
            int totalItems = 0;
            double totalCost = 0;

            for (String fruit : productAmounts.keySet()) {
                int amt = productAmounts.get(fruit);
                if (amt > 0) {
                    double price = productPrices.get(fruit);
                    summary.append(fruit)
                           .append(": ")
                           .append(amt)
                           .append(" x $")
                           .append(price)
                           .append(" = $")
                           .append(amt * price)
                           .append("\n");
                    totalItems += amt;
                    totalCost += amt * price;
                }
            }

            summary.append("\nTotal items: ").append(totalItems);
            summary.append("\nTotal cost: $").append(totalCost);

            JOptionPane.showMessageDialog(this, summary.toString());
        });
        add(continueButton, BorderLayout.SOUTH);
    }
}
