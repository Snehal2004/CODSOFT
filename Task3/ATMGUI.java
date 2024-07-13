import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMGUI extends JFrame {
    private BankAccount bankAccount;
    private JLabel balanceLabel;
    private JTextField amountField;
    private JTextArea messageArea;

    public ATMGUI(BankAccount account) {
        this.bankAccount = account;
        setTitle("ATM Machine");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the windojavac BankAccount.javaw

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        balanceLabel = new JLabel("Current Balance: $" + bankAccount.getBalance());
        panel.add(balanceLabel);

        amountField = new JTextField(10);
        panel.add(amountField);

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    bankAccount.deposit(amount);
                    updateBalance();
                    showMessage("Deposit successful. Current balance: $" + bankAccount.getBalance());
                } catch (NumberFormatException ex) {
                    showMessage("Invalid amount format. Please enter a valid number.");
                }
            }
        });
        panel.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    boolean success = bankAccount.withdraw(amount);
                    if (success) {
                        updateBalance();
                        showMessage("Withdrawal successful. Current balance: $" + bankAccount.getBalance());
                    } else {
                        showMessage("Insufficient funds or invalid amount. Withdrawal failed.");
                    }
                } catch (NumberFormatException ex) {
                    showMessage("Invalid amount format. Please enter a valid number.");
                }
            }
        });
        panel.add(withdrawButton);

        messageArea = new JTextArea(5, 20);
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        panel.add(scrollPane);

        add(panel);
        setVisible(true);
    }

    private void updateBalance() {
        balanceLabel.setText("Current Balance: $" + bankAccount.getBalance());
    }

    private void showMessage(String message) {
        messageArea.append(message + "\n");
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000); // Initial balance
        new ATMGUI(account);
    }
}
