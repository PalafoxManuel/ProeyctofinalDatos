import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.util.HashSet;
import java.util.Set;

public class Adivinador extends JFrame {

    private JPanel contentPane;
    private JLabel lblNewLabel_1;
    private int previousNumber;
    private Set<Integer> generatedNumbers;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Adivinador frame = new Adivinador();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Adivinador() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 682, 549);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBackground(new Color(128, 255, 128));
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Advinador");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel.setBounds(265, 11, 152, 68);
        panel.add(lblNewLabel);

        generatedNumbers = new HashSet<>();
        previousNumber = generateRandomNumber();
        lblNewLabel_1 = new JLabel("Tu numero es: " + previousNumber);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblNewLabel_1.setBounds(211, 166, 291, 68);
        panel.add(lblNewLabel_1);

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(new GridLayout(1, 3, 0, 0));

        JButton btnNewButton_1 = new JButton("Es menor que mi numero");
        panel_1.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Ese es mi numero");
        panel_1.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("Es mayor que mi numero");
        panel_1.add(btnNewButton_3);

        btnNewButton_1.addActionListener(e -> {
            int newNumber = generateRandomNumberGreaterThan(previousNumber);
            previousNumber = newNumber;
            generatedNumbers.add(newNumber);
            lblNewLabel_1.setText("Tu numero es: " + previousNumber);
        });

        btnNewButton_3.addActionListener(e -> {
            int newNumber = generateRandomNumberSmallerThan(previousNumber);
            previousNumber = newNumber;
            generatedNumbers.add(newNumber);
            lblNewLabel_1.setText("Tu numero es: " + previousNumber);
        });
    }

    private int generateRandomNumber() {
        int randomNumber;
        do {
            randomNumber = (int) (Math.random() * 1000000);
        } while (generatedNumbers.contains(randomNumber));
        return randomNumber;
    }

    private int generateRandomNumberGreaterThan(int minValue) {
        int randomNumber;
        do {
            randomNumber = (int) (Math.random() * 1000000);
        } while (randomNumber <= minValue || generatedNumbers.contains(randomNumber));
        return randomNumber;
    }

    private int generateRandomNumberSmallerThan(int maxValue) {
        int randomNumber;
        do {
            randomNumber = (int) (Math.random() * 1000000);
        } while (randomNumber >= maxValue || generatedNumbers.contains(randomNumber));
        return randomNumber;
    }
}
