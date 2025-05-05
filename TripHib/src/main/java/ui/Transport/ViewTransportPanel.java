package ui.Transport;

import Transport.TransportationRoute;
import User.UserSession;
import service.BookingService;
import tourist.Tourist;
import Functional_Dependencies.QRCodeGenerator;
import ui.View.ViewAttractionAndEventPanel.EmailService;
import ui.Tourist.TouristJPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ViewTransportPanel extends JPanel {

    private JTable routeTable;
    private DefaultTableModel routeTableModel;
    private BookingService bookingService;

    public ViewTransportPanel() {
        bookingService = BookingService.getInstance();
        initComponents();
        populateTable();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(161,197,153)); // Light pastel background

        // --- Header with title and Back button ---
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(33, 37, 41));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("🚌 Available Transport Routes", JLabel.LEFT);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.WEST);

        JButton btnBack = new JButton("← Back");
        styleBootstrapButton(btnBack, "secondary");
        btnBack.addActionListener(e -> {
            Container parent = this.getParent();
            parent.removeAll();
            parent.add(new TouristJPanel());
            parent.revalidate();
            parent.repaint();
        });
        headerPanel.add(btnBack, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        // --- Table for transport routes ---
        routeTableModel = new DefaultTableModel(new Object[]{"Route Name", "Start Location", "End Location", "Frequency", "Price"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        routeTable = new JTable(routeTableModel);
        routeTable.setRowHeight(30);
        routeTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(routeTable);
        add(scrollPane, BorderLayout.CENTER);

        // --- Book button ---
        JButton bookBtn = new JButton("Book Selected Route");
        styleBootstrapButton(bookBtn, "primary");
        bookBtn.addActionListener(e -> bookRoute());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(getBackground());
        buttonPanel.add(bookBtn);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void populateTable() {
        routeTableModel.setRowCount(0);
        for (TransportationRoute route : bookingService.getRoutes()) {
            routeTableModel.addRow(new Object[]{
                    route.getRouteName(),
                    route.getStartLocation(),
                    route.getEndLocation(),
                    route.getFrequency() + " mins",
                    "$" + route.getPrice()
            });
        }
    }

    private void bookRoute() {
        int selectedRow = routeTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this,
                    "Please select a route to book.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String routeName = routeTableModel.getValueAt(selectedRow, 0).toString();
        TransportationRoute route = bookingService.getRoutes().get(selectedRow);
        String username = UserSession.getInstance().getUsername();
        Tourist tourist = bookingService.getTouristByUsername(username);

        if (tourist == null) {
            JOptionPane.showMessageDialog(this, "Tourist not found. Please log in.");
            return;
        }

        String bookingId = "ROUTE-" + System.currentTimeMillis();
        String bookingDetails = "Dear " + tourist.getName() + ",\n\n" +
                "Your transport booking is confirmed!\n" +
                "Route: " + route.getRouteName() + "\n" +
                "Booking ID: " + bookingId + "\n" +
                "Date: " + java.time.LocalDate.now() + "\n\n" +
                "Thank you for choosing our service!";

        // Send Email
        EmailService.sendBookingConfirmation(tourist.getEmail(), bookingDetails);

        // Generate QR Code
        BufferedImage qrImage = QRCodeGenerator.generateQRCodeImage(route.getRouteName(), bookingId);

        // Display confirmation with QR code
        JDialog dialog = new JDialog();
        dialog.setTitle("Transport Booking Confirmation");
        dialog.setLayout(new BorderLayout());

        dialog.add(new JLabel(new ImageIcon(qrImage)), BorderLayout.CENTER);

        JLabel detailsLabel = new JLabel("<html>" + bookingDetails.replace("\n", "<br/>") + "</html>");
        detailsLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        dialog.add(detailsLabel, BorderLayout.SOUTH);

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private void styleBootstrapButton(JButton button, String style) {
        Color bgColor;
        switch (style) {
            case "primary":
                bgColor = new Color( 70,130,180); break;
            case "secondary":
                bgColor = new Color(70,130,180); break;
            case "success":
                bgColor = new Color(70,130,180); break;
            case "info":
                bgColor = new Color(70,130,180); break;
            default:
                bgColor = new Color(70,130,180);
        }

        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }
}
