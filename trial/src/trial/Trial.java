package trial;
import javax.swing.*;
import java.awt.*;

public class Trial extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    Trial() {
        super("Main");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Outer bars
        JPanel outerNorth = new JPanel();
        outerNorth.setPreferredSize(new Dimension(1000, 50));
        outerNorth.setBackground(Color.decode("#1B3C53"));
        add(outerNorth, BorderLayout.NORTH);

        JPanel outerSouth = new JPanel();
        outerSouth.setPreferredSize(new Dimension(1000, 50));
        outerSouth.setBackground(Color.decode("#1B3C53"));
        add(outerSouth, BorderLayout.SOUTH);

        JPanel outerEast = new JPanel();
        outerEast.setPreferredSize(new Dimension(50, 1000));
        outerEast.setBackground(Color.decode("#1B3C53"));
        add(outerEast, BorderLayout.EAST);

        JPanel outerWest = new JPanel();
        outerWest.setPreferredSize(new Dimension(50, 1000));
        outerWest.setBackground(Color.decode("#1B3C53"));
        add(outerWest, BorderLayout.WEST);

        // CardLayout in CENTER
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // === Main Screen (your design) ===
        JPanel mainScreen = new JPanel(new BorderLayout());

        // NORTH
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        northPanel.setBackground(Color.decode("#E1D0B3"));
        JButton adminButton = new JButton("Admin");
        adminButton.setBackground(Color.decode("#A18D6D"));
        adminButton.setFont(new Font("SansSerif", Font.BOLD, 30));
        northPanel.add(adminButton);
        northPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 200, 10));
        mainScreen.add(northPanel, BorderLayout.NORTH);

        // CENTER
        JLabel welcome = new JLabel("POINT OF SALE", SwingConstants.CENTER);
        welcome.setFont(new Font("SansSerif", Font.BOLD, 100));
        JPanel center = new JPanel(new GridBagLayout());
        center.add(welcome);
        center.setBackground(Color.decode("#A18D6D"));
        mainScreen.add(center, BorderLayout.CENTER);

        // SOUTH
        JButton buy = new JButton("BUY");
        buy.setFont(new Font("SansSerif", Font.BOLD, 100));
        buy.setBackground(Color.decode("#A18D6D"));
        JPanel bottom = new JPanel(new GridBagLayout());
        bottom.setBorder(BorderFactory.createEmptyBorder(30, 0, 220, 0));
        bottom.add(buy);
        bottom.setBackground(Color.decode("#E1D0B3"));
        mainScreen.add(bottom, BorderLayout.SOUTH);

        // EAST/WEST margins
        JPanel blank1 = new JPanel();
        blank1.setPreferredSize(new Dimension(80, 150));
        blank1.setBackground(Color.decode("#E1D0B3"));
        mainScreen.add(blank1, BorderLayout.EAST);

        JPanel blank2 = new JPanel();
        blank2.setPreferredSize(new Dimension(80, 150));
        blank2.setBackground(Color.decode("#E1D0B3"));
        mainScreen.add(blank2, BorderLayout.WEST);

        // Add screens to cardPanel
        cardPanel.add(mainScreen, "Main");
        cardPanel.add(new Buy(cardLayout, cardPanel), "Buy");
        cardPanel.add(new Admin(cardLayout, cardPanel), "Admin");
        cardPanel.add(new AdminView(cardLayout, cardPanel), "AdminView");
        cardPanel.add(new Edit(cardLayout, cardPanel), "Edit");

        adminButton.addActionListener(e -> cardLayout.show(cardPanel, "Admin"));


        // Put cardPanel in CENTER of outer frame
        add(cardPanel, BorderLayout.CENTER);

        // Action Listeners
        buy.addActionListener(e -> cardLayout.show(cardPanel, "Buy"));
        adminButton.addActionListener(e -> cardLayout.show(cardPanel, "Admin"));

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Trial::new);
    }
}
