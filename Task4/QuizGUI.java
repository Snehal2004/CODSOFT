import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String question;
    String[] options;
    int correctAnswer;

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class QuizGUI extends JFrame {
    private static final Question[] questions = {
            new Question("What is the capital of France?", new String[] { "Berlin", "Madrid", "Paris", "Rome" }, 2),
            new Question("Which planet is known as the Red Planet?",
                    new String[] { "Earth", "Mars", "Jupiter", "Venus" }, 1),
            new Question("Who wrote 'To Kill a Mockingbird'?",
                    new String[] { "Harper Lee", "Mark Twain", "J.K. Rowling", "Ernest Hemingway" }, 0)
    };

    private int score = 0;
    private int currentQuestionIndex = 0;
    private Timer timer;
    private int timeLeft = 10;

    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private JButton submitButton;
    private JLabel timerLabel;

    public QuizGUI() {
        setTitle("Quiz Application");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        questionLabel = new JLabel("Question");
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        ButtonGroup buttonGroup = new ButtonGroup();
        optionButtons = new JRadioButton[4];
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            buttonGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }
        add(optionsPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new BorderLayout());
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitAnswer();
            }
        });
        controlPanel.add(submitButton, BorderLayout.CENTER);

        timerLabel = new JLabel("Time left: 10s");
        controlPanel.add(timerLabel, BorderLayout.EAST);

        add(controlPanel, BorderLayout.SOUTH);

        displayNextQuestion();
    }

    private void displayNextQuestion() {
        if (currentQuestionIndex < questions.length) {
            Question q = questions[currentQuestionIndex];
            questionLabel.setText(q.question);
            for (int i = 0; i < q.options.length; i++) {
                optionButtons[i].setText(q.options[i]);
                optionButtons[i].setSelected(false);
            }

            timeLeft = 10;
            timerLabel.setText("Time left: " + timeLeft + "s");

            if (timer != null) {
                timer.cancel();
            }
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    SwingUtilities.invokeLater(() -> {
                        timeLeft--;
                        timerLabel.setText("Time left: " + timeLeft + "s");
                        if (timeLeft <= 0) {
                            timer.cancel();
                            submitAnswer();
                        }
                    });
                }
            }, 1000, 1000);
        } else {
            displayResults();
        }
    }

    private void submitAnswer() {
        if (timer != null) {
            timer.cancel();
        }

        int selectedOption = -1;
        for (int i = 0; i < optionButtons.length; i++) {
            if (optionButtons[i].isSelected()) {
                selectedOption = i;
                break;
            }
        }

        if (selectedOption == questions[currentQuestionIndex].correctAnswer) {
            score++;
        }

        currentQuestionIndex++;
        displayNextQuestion();
    }

    private void displayResults() {
        JOptionPane.showMessageDialog(this,
                "Quiz finished!\nYour final score: " + score + " out of " + questions.length,
                "Quiz Results", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new QuizGUI().setVisible(true);
        });
    }
}
