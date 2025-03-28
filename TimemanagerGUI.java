import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimemanagerGUI {
    private static final int DAY_HOURS = 24;
    private static String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private static int[] freeTimePerDay = new int[daysOfWeek.length];
    private static int currentDay = 0;
    private static int dailyHours = DAY_HOURS;
    private static boolean sleepLogged = false;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Weekly Time Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Hello! This program will help you calculate your free time and manage your week!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 20, 20, 20);
        panel.add(titleLabel, gbc);

        JLabel dayLabel = new JLabel("Starting with: " + daysOfWeek[currentDay]);
        JLabel freeTimeLabel = new JLabel("You have " + dailyHours + " hours available today.");
        dayLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        freeTimeLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(dayLabel, gbc);
        gbc.gridy = 2;
        panel.add(freeTimeLabel, gbc);

        JLabel sleepLabel = new JLabel("Enter the number of hours for sleep:");
        JTextField inputField = new JTextField(10);
        sleepLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        inputField.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(sleepLabel, gbc);
        gbc.gridy = 4;
        panel.add(inputField, gbc);

        JLabel activityLabel = new JLabel("Enter your activity name:");
        JTextField activityField = new JTextField(30);
        JLabel activityHoursLabel = new JLabel("Enter the number of hours for this activity:");
        JTextField activityHoursField = new JTextField(10);
        activityLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        activityHoursLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        activityField.setFont(new Font("Arial", Font.PLAIN, 20));
        activityHoursField.setFont(new Font("Arial", Font.PLAIN, 20));

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(activityLabel, gbc);
        gbc.gridy = 6;
        panel.add(activityField, gbc);
        gbc.gridy = 7;
        panel.add(activityHoursLabel, gbc);
        gbc.gridy = 8;
        panel.add(activityHoursField, gbc);

        JButton sleepButton = new JButton("Log Sleep");
        JButton activityButton = new JButton("Log Activity");
        JButton nextDayButton = new JButton("Start Next Day");
        sleepButton.setFont(new Font("Arial", Font.BOLD, 24));
        activityButton.setFont(new Font("Arial", Font.BOLD, 24));
        nextDayButton.setFont(new Font("Arial", Font.BOLD, 24));

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        panel.add(sleepButton, gbc);
        gbc.gridx = 1;
        panel.add(activityButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 10;
        panel.add(nextDayButton, gbc);

        JTextArea outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 20));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        panel.add(scrollPane, gbc);

        frame.add(panel);
        frame.setVisible(true);

        activityButton.setEnabled(false);
        nextDayButton.setEnabled(false);

        sleepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int sleepHours = Integer.parseInt(inputField.getText().trim());
                    inputField.setText("");

                    if (sleepHours <= 0 || sleepHours > 14) {
                        outputArea.append("Please enter a realistic value for sleep hours (1-14).\n");
                        return;
                    }

                    if (dailyHours - sleepHours < 0) {
                        outputArea.append("Your time exceeds the available hours. Please enter a smaller value.\n");
                        return;
                    }

                    dailyHours -= sleepHours;
                    freeTimeLabel.setText("You have " + dailyHours + " hours available today.");
                    outputArea.append("Sleep logged: " + sleepHours + " hours. " +
                            "You have " + dailyHours + " hours left today.\n");
                    sleepLogged = true;
                    activityButton.setEnabled(true);
                    nextDayButton.setEnabled(true);
                    sleepButton.setEnabled(false);
                } catch (NumberFormatException ex) {
                    outputArea.append("Invalid input. Please enter a valid number for sleep hours.\n");
                }
            }
        });

        activityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!sleepLogged) {
                    outputArea.append("Please log your sleep hours first.\n");
                    return;
                }

                String activityName = activityField.getText().trim();
                if (activityName.isEmpty()) {
                    outputArea.append("Please enter an activity name.\n");
                    return;
                }

                try {
                    int activityHours = Integer.parseInt(activityHoursField.getText().trim());
                    activityField.setText("");
                    activityHoursField.setText("");

                    if (activityHours <= 0 || activityHours > dailyHours) {
                        outputArea.append("Please enter a valid number of hours (greater than 0 and within your remaining hours).\n");
                        return;
                    }

                    dailyHours -= activityHours;
                    freeTimeLabel.setText("You have " + dailyHours + " hours available today.");
                    outputArea.append("Activity logged: " + activityName + " for " + activityHours + " hours. " +
                            "You have " + dailyHours + " hours left today.\n");
                } catch (NumberFormatException ex) {
                    outputArea.append("Invalid input. Please enter a valid number for activity hours.\n");
                }
            }
        });

        nextDayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                freeTimePerDay[currentDay] = dailyHours;
                currentDay++;

                if (currentDay >= daysOfWeek.length) {
                    displaySummary(outputArea);
                    return;
                }

                dailyHours = DAY_HOURS;
                dayLabel.setText("Let's see what you have on " + daysOfWeek[currentDay] + ":");
                freeTimeLabel.setText("You have 24 hours available today.");
                sleepButton.setEnabled(true);
                activityButton.setEnabled(false);
                nextDayButton.setEnabled(false);
                sleepLogged = false;

                outputArea.append("\nStarting a new day: " + daysOfWeek[currentDay] + "\n");
            }
        });
    }

    private static void displaySummary(JTextArea outputArea) {
        outputArea.append("\nSummary of your free time for the week:\n");
        for (int i = 0; i < daysOfWeek.length; i++) {
            outputArea.append(daysOfWeek[i] + ": " + freeTimePerDay[i] + " hours of free time.\n");
        }
        outputArea.append("\nThank you for using the Weekly Time Manager!");
    }
}