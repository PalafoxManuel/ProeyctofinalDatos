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
import javax.swing.JOptionPane;

public class Adivinador extends JFrame {

    private JPanel contentPane;
    private JLabel lblNewLabel_1;
    private int previousNumber;
    private Set<Integer> generatedNumbers;
    private JPanel panel;
    private Color[] colors;
    private int limitemenor;
    private int limitemayor;

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

        panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Advinador");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel.setBounds(265, 11, 152, 68);
        panel.add(lblNewLabel);

        generatedNumbers = new HashSet<>();
        limitemenor = 0;
        limitemayor = 1000000;
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
            if (limitemenor > limitemayor) {
                JOptionPane.showMessageDialog(this, "Ya no hay mas numeros posibles.", "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                return;
            }

            limitemenor = previousNumber + 1;
            int newNumber = generateRandomNumber();
            previousNumber = newNumber;
            generatedNumbers.add(newNumber);
            lblNewLabel_1.setText("Tu numero es: " + previousNumber);

            int colorIndex = getColorIndex();
            panel.setBackground(colors[colorIndex]);



        });

        btnNewButton_3.addActionListener(e -> {

            if (limitemenor > limitemayor) {
                JOptionPane.showMessageDialog(this, "Ya no hay mas numeros posibles.", "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                return;
            }

            limitemayor = previousNumber - 1;
            int newNumber = generateRandomNumber();
            previousNumber = newNumber;
            generatedNumbers.add(newNumber);
            lblNewLabel_1.setText("Tu numero es: " + previousNumber);

            int colorIndex = getColorIndex();
            panel.setBackground(colors[colorIndex]);
        });

        initializeColors();
        panel.setBackground(colors[0]);
    }

    private void initializeColors() {
        colors = new Color[] {
                new Color(0, 0, 128),    
                new Color(135, 206, 250),
                new Color(255, 255, 0),
                new Color(255, 165, 0),
                new Color(255, 69, 0)
        };
    }

    private int getColorIndex() {
        int generatedNumbersSize = generatedNumbers.size();
        if (generatedNumbersSize <= 3) {
            return 0;
        } else if (generatedNumbersSize <= 20) {
            int colorIndex = (int) ((generatedNumbersSize - 4) / 4) + 1;
            return Math.min(colorIndex, colors.length - 1);
        } else {
            return colors.length - 1;
        }
    }

    private int generateRandomNumber() {
        if (limitemenor > limitemayor) {
            return limitemenor;
        }

        int randomNumber;
        do {
            randomNumber = (int) (limitemenor + Math.random() * (limitemayor - limitemenor + 1));
        } while (generatedNumbers.contains(randomNumber));
        return randomNumber;
    }
}
