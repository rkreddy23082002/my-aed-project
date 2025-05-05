package ui.Hospitality;

import hospitality.HospitalityManager;
import service.BookingService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HospitalityManagerManagementPanel extends JPanel {

    private BookingService bookingService;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtName, txtUsername, txtPassword, txtType;
    private JCheckBox chkActive;

    public HospitalityManagerManagementPanel() {
        bookingService = BookingService.getInstance();
        initComponents();
        loadData();
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(161, 197, 153));

        JLabel lblTitle = new JLabel("Hospitality Manager Management");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblTitle, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Username", "Active"}, 0);
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBackground(new Color(161, 197, 153));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        txtName = new JTextField();
        txtUsername = new JTextField();
        txtPassword = new JTextField();
        txtType = new JTextField(); // 🎯 kept just as input field — not saved or used
        chkActive = new JCheckBox("Active");
        chkActive.setBackground(new Color(161, 197, 153));
        chkActive.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        formPanel.add(new JLabel("Name:"));
        formPanel.add(txtName);
        formPanel.add(new JLabel("Username:"));
        formPanel.add(txtUsername);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(txtPassword);
        formPanel.add(new JLabel("Type (Just for reference):"));
        formPanel.add(txtType); // ✅ present in UI but not used
        formPanel.add(new JLabel("Status:"));
        formPanel.add(chkActive);

        JButton btnAdd = new JButton("Add Manager");
        JButton btnDelete = new JButton("Delete Selected");

        styleButton(btnAdd, new Color(70, 130, 180));         // Primary
        styleButton(btnDelete, new Color(161, 182, 132));     // Secondary

        btnAdd.addActionListener(e -> addManager());
        btnDelete.addActionListener(e -> deleteManager());

        formPanel.add(btnAdd);
        formPanel.add(btnDelete);

        add(formPanel, BorderLayout.SOUTH);
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<HospitalityManager> managers = bookingService.getHospitalityManagers();
        for (HospitalityManager manager : managers) {
            tableModel.addRow(new Object[]{
                    manager.getManagerId(),
                    manager.getName(),
                    manager.getUsername(),
                    manager.isActive()
            });
        }
    }

    private void addManager() {
        String name = txtName.getText().trim();
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        boolean isActive = chkActive.isSelected();

        if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.");
            return;
        }

        int newId = bookingService.getHospitalityManagers().size() + 1;
        HospitalityManager manager = new HospitalityManager(newId, name, username, password, isActive);
        bookingService.addHospitalityManager(manager);
        loadData();
        clearFields();
    }

    private void deleteManager() {
        int row = table.getSelectedRow();
        if (row < 0) return;

        int managerId = (int) tableModel.getValueAt(row, 0);
        bookingService.deleteHospitalityManager(managerId);
        loadData();
    }

    private void clearFields() {
        txtName.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtType.setText("");  // ✅ still clears even though not used
        chkActive.setSelected(false);
    }
}
