package ui.Admin;

import User.UserCredentialsManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UserCredentialsManagerPanel extends JPanel {

    private JTable userTable;
    private DefaultTableModel tableModel;

    public UserCredentialsManagerPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(161, 197, 153)); // Background color

        JLabel title = new JLabel("User Credentials Overview", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"Username", "Password", "Role"}, 0);
        userTable = new JTable(tableModel);
        userTable.setRowHeight(30);
        userTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(userTable);
        add(scrollPane, BorderLayout.CENTER);

        loadData();
    }

    private void loadData() {
        tableModel.setRowCount(0);
        UserCredentialsManager manager = UserCredentialsManager.getInstance();
        for (UserCredentialsManager.UserCredentials uc : manager.getAllUserCredentials()) {
            tableModel.addRow(new Object[]{uc.getUsername(), uc.getPassword(), uc.getRole()});
        }
    }
}
