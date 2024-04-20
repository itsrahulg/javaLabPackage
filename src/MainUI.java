import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class MainUI extends JFrame {
    public MainUI() {
        setTitle("Java Lab Package");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 300);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        getContentPane().add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        panel.add(inputPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel optionLabel = new JLabel("Choose an option:");
        optionLabel.setFont(optionLabel.getFont().deriveFont(Font.BOLD, 25)); // Make the label larger
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(optionLabel, gbc);

        String[] options = {"Palindrome", "Isogram", "Anagram"};
        JComboBox<String> optionComboBox = new JComboBox<>(options);
        optionComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setFont(label.getFont().deriveFont(Font.BOLD, 16)); // Set font size to 14 (adjust as needed)
                return label;
            }
        });
        optionComboBox.setPreferredSize(new Dimension(200, 30)); // Set preferred size (width: 200, height: 40)
        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(optionComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1; // Move to the next row
        gbc.gridwidth = 2; // Span across two columns
        inputPanel.add(new JPanel(), gbc); // Empty panel for spacing

        JLabel string1Label = new JLabel("Enter string to check :");
        string1Label.setFont(string1Label.getFont().deriveFont(Font.BOLD, 16)); // Increase font size
        gbc.gridx = 0;
        gbc.gridy = 2; // Move to the next row
        gbc.gridwidth = 1; // Reset grid width
        inputPanel.add(string1Label, gbc);

        JTextField string1Field = new JTextField(30);
        Dimension fieldDimension = string1Field.getPreferredSize(); // Get the current preferred size
        fieldDimension.height = 30; // Set the desired height
        string1Field.setPreferredSize(fieldDimension); // Set the new preferred size
        gbc.gridx = 1;
        gbc.gridy = 2; // Move to the next row
        inputPanel.add(string1Field, gbc);

        JLabel string2Label = new JLabel("Enter Second String :");
        string2Label.setFont(string2Label.getFont().deriveFont(Font.BOLD, 16)); // Increase font size
        gbc.gridx = 0;
        gbc.gridy = 3; // Move to the next row
        gbc.gridwidth = 1; // Reset grid width
        inputPanel.add(string2Label, gbc);
        string2Label.setVisible(false); // Initially hide the label

        JTextField string2Field = new JTextField(30);
        Dimension fieldDimension2 = string2Field.getPreferredSize(); // Get the current preferred size
        fieldDimension2.height = 30; // Set the desired height
        string2Field.setPreferredSize(fieldDimension2); // Set the new preferred size
        gbc.gridx = 1;
        gbc.gridy = 3; // Move to the next row
        gbc.gridwidth = 1; // Reset grid width
        inputPanel.add(string2Field, gbc);
        string2Field.setVisible(false);
        // Initially hide the text field

        // Adding ActionListener for optionComboBox to show/hide string2Field based on selected option
        optionComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) optionComboBox.getSelectedItem();
                if (selectedOption.equals("Anagram")) {
                    string2Label.setVisible(true);
                    string2Field.setVisible(true);
                } else {
                    string2Label.setVisible(false);
                    string2Field.setVisible(false);
                }
            }
        });

        // Existing code for string1Label and string1Field remains the same

        JPanel analyzePanel = new JPanel(new BorderLayout());
        panel.add(analyzePanel, BorderLayout.SOUTH);

        // Create a panel to contain the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center the buttons horizontally
        analyzePanel.add(buttonPanel, BorderLayout.NORTH);

        // Create and customize the "GET RESULT" button
        JButton analyzeButton = new JButton("GET RESULT");
        analyzeButton.setPreferredSize(new Dimension(150, 40)); // Set width and height
        analyzeButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Add margin (10 pixels) above and below the button
        buttonPanel.add(analyzeButton);

        // Create and customize the "RESET" button
        JButton resetButton = new JButton("RESET");
        resetButton.setPreferredSize(new Dimension(150, 40)); // Set width and height
        resetButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Add margin (10 pixels) above and below the button
        buttonPanel.add(resetButton);

        // Add padding below the button
        buttonPanel.add(Box.createVerticalStrut(10)); // Adjust vertical spacing as needed

        // Create and customize the result label
        JLabel resultLabel = new JLabel("");
        resultLabel.setFont(resultLabel.getFont().deriveFont(Font.BOLD, 16)); // Set font to bold and size to 16
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text horizontally
        analyzePanel.add(resultLabel, BorderLayout.CENTER);

        // Adding action listeners for analyzeButton and resetButton remain the same

        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string1 = string1Field.getText().trim();
                String string2 = string2Field.isVisible() ? string2Field.getText().trim() : ""; // Get string2 only if it's visible
                int option = optionComboBox.getSelectedIndex() + 1; // Since JComboBox is 0-based index

                switch (option) {
                    case 1:
                        if (isPalindrome(string1)) {
                            resultLabel.setText(string1 + " is a palindrome.");
                        } else {
                            resultLabel.setText(string1 + " is not a palindrome.");
                        }
                        break;
                    case 2:
                        if (isIsogram(string1)) {
                            resultLabel.setText(string1 + " is an isogram.");
                        } else {
                            resultLabel.setText(string1 + " is not an isogram.");
                        }
                        break;
                    case 3:
                        if (areAnagrams(string1, string2)) {
                            resultLabel.setText(string1 + " and " + string2 + " are anagrams.");
                        } else {
                            resultLabel.setText(string1 + " and " + string2 + " are not anagrams.");
                        }
                        break;
                    default:
                        resultLabel.setText("Invalid option selected.");
                        break;
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear text fields
                string1Field.setText("");
                string2Field.setText("");
                // Set text to empty in the result label
                resultLabel.setText("");
            }
        });
    }

    // Palindrome check logic
    private boolean isPalindrome(String str) {
        String string1 = str.replaceAll("\\s", "").toLowerCase();
        int leftPointer = 0;
        int rightPointer = string1.length() - 1;
        while (leftPointer < rightPointer) {
            if (string1.charAt(leftPointer) != string1.charAt(rightPointer)) {
                return false;
            }
            leftPointer++;
            rightPointer--;
        }
        return true;
    }

    // Isogram check logic
    private boolean isIsogram(String str) {
        String stringNew = str.replaceAll("\\s", "");
        int stringLength = stringNew.length();
        for (int i = 0; i < stringLength; i++) {
            int count = 1;
            for (int j = 0; j < stringLength; j++) {
                if (i == j) {
                    continue;
                }
                if (stringNew.charAt(i) == stringNew.charAt(j)) {
                    count += 1;
                    if (count > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Anagram check logic
    private boolean areAnagrams(String str1, String str2) {
        String stringOne = str1.replaceAll("\\s", "").toLowerCase();
        String stringTwo = str2.replaceAll("\\s", "").toLowerCase();
        char[] array1 = stringOne.toCharArray();
        char[] array2 = stringTwo.toCharArray();
        Arrays.sort(array1);
        Arrays.sort(array2);
        return Arrays.equals(array1, array2);
    }

    public static void main(String[] args) {
        new MainUI();
    }
}
