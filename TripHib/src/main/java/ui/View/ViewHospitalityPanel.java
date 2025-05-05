package ui.View;

import Booking.Booking;
import hospitality.Hotel;
import hospitality.Restaurant;
import service.BookingService;
import tourist.Tourist;
import Functional_Dependencies.QRCodeGenerator;
import ui.View.ViewAttractionAndEventPanel.EmailService;
import User.UserSession;
import ui.Tourist.TouristJPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ViewHospitalityPanel extends JPanel {

    private BookingService bookingService;
    private JTable hotelTable;
    private JTable restaurantTable;

    public ViewHospitalityPanel() {
        bookingService = BookingService.getInstance();
        setLayout(new BorderLayout());
        setBackground(new Color(161,197,153)); // Light background

        // ----- Header with Back Button -----
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(new Color(33, 37, 41));
        JButton btnBack = new JButton("← Back");
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBack.setBackground(new Color(108, 117, 125));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        btnBack.addActionListener(e -> {
            Container parent = this.getParent();
            parent.removeAll();
            parent.add(new TouristJPanel());
            parent.revalidate();
            parent.repaint();
        });
        headerPanel.add(btnBack);
        add(headerPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 20, 10));
        mainPanel.setBackground(getBackground());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel, BorderLayout.CENTER);

        // ---------- Hotels Panel ----------
        JPanel hotelPanel = new JPanel(new BorderLayout());
        hotelPanel.setBackground(Color.WHITE);
        hotelPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 255)),
                "Available Hotels", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16), new Color(51, 51, 102)
        ));
        hotelTable = new JTable(new DefaultTableModel(new Object[]{"Name", "Location"}, 0));
        hotelTable.setRowHeight(30);
        hotelTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JScrollPane hotelScroll = new JScrollPane(hotelTable);
        hotelPanel.add(hotelScroll, BorderLayout.CENTER);

        JButton btnBookHotel = new JButton("Book Selected Hotel");
        styleBootstrapButton(btnBookHotel, "success");
        hotelPanel.add(btnBookHotel, BorderLayout.SOUTH);
        btnBookHotel.addActionListener(e -> bookHotel());

        // ---------- Restaurants Panel ----------
        JPanel restaurantPanel = new JPanel(new BorderLayout());
        restaurantPanel.setBackground(Color.WHITE);
        restaurantPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 255)),
                "Available Restaurants", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16), new Color(51, 51, 102)
        ));
        restaurantTable = new JTable(new DefaultTableModel(new Object[]{"Name", "Location"}, 0));
        restaurantTable.setRowHeight(30);
        restaurantTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JScrollPane restaurantScroll = new JScrollPane(restaurantTable);
        restaurantPanel.add(restaurantScroll, BorderLayout.CENTER);

        JButton btnBookRestaurant = new JButton("Book Selected Restaurant");
        styleBootstrapButton(btnBookRestaurant, "info");
        restaurantPanel.add(btnBookRestaurant, BorderLayout.SOUTH);
        btnBookRestaurant.addActionListener(e -> bookRestaurant());

        mainPanel.add(hotelPanel);
        mainPanel.add(restaurantPanel);

        populateTables();
    }

    private void populateTables() {
        DefaultTableModel hotelModel = (DefaultTableModel) hotelTable.getModel();
        DefaultTableModel restaurantModel = (DefaultTableModel) restaurantTable.getModel();

        hotelModel.setRowCount(0);
        for (Hotel h : bookingService.getHotels()) {
            hotelModel.addRow(new Object[]{h.getName(), h.getLocation()});
        }

        restaurantModel.setRowCount(0);
        for (Restaurant r : bookingService.getRestaurants()) {
            restaurantModel.addRow(new Object[]{r.getName(), r.getLocation()});
        }
    }

    private void bookHotel() {
    int row = hotelTable.getSelectedRow();
    if (row < 0) {
        JOptionPane.showMessageDialog(this, "Please select a hotel to book.");
        return;
    }

    Hotel hotel = bookingService.getHotels().get(row);
    Tourist tourist = bookingService.getTouristByUsername(UserSession.getInstance().getUsername());

    if (tourist == null) {
        JOptionPane.showMessageDialog(this, "Tourist not found. Please log in.");
        return;
    }

    if (!hotel.hasVacancy()) {
        JOptionPane.showMessageDialog(this, "No rooms available in this hotel.");
        return;
    }

    boolean booked = hotel.bookRoom(tourist.getName());
    if (!booked) {
        JOptionPane.showMessageDialog(this, "Booking failed. Please try again.");
        return;
    }

    String bookingId = "HOTEL-" + System.currentTimeMillis();
    String today = java.time.LocalDate.now().toString();

    // ✅ Store in BookingService
    Booking newBooking = new Booking(
        bookingService.getAllHotelBookings().size() + 1,
        tourist.getUsername(),
        hotel.getHotelId(),
        "Hotel",
        hotel.getName(),
        tourist.getName(),
        tourist.getEmail(),
        today,
        1,
        true,
        today
    );
    bookingService.addBooking(newBooking);

    String details = "Dear " + tourist.getName() + ",\n\n" +
            "Your hotel booking is confirmed!\n" +
            "Hotel: " + hotel.getName() + "\n" +
            "Booking ID: " + bookingId + "\n" +
            "Date: " + today + "\n\n" +
            "Enjoy your stay!";

    EmailService.sendBookingConfirmation(tourist.getEmail(), details);
    BufferedImage qr = QRCodeGenerator.generateQRCodeImage(hotel.getName(), bookingId);
    showBookingDialog(details, qr, "Hotel Booking Confirmation");
}


    private void bookRestaurant() {
    int row = restaurantTable.getSelectedRow();
    if (row < 0) {
        JOptionPane.showMessageDialog(this, "Please select a restaurant to book.");
        return;
    }

    Restaurant restaurant = bookingService.getRestaurants().get(row);
    Tourist tourist = bookingService.getTouristByUsername(UserSession.getInstance().getUsername());

    if (tourist == null) {
        JOptionPane.showMessageDialog(this, "Tourist not found. Please log in.");
        return;
    }

    if (restaurant.getCapacity() <= 0) {
        JOptionPane.showMessageDialog(this, "Sorry, this restaurant is fully booked!");
        return;
    }

    boolean booked = restaurant.bookTable(tourist.getName(), tourist.getEmail());
    if (!booked) {
        JOptionPane.showMessageDialog(this, "Booking failed. Try again.");
        return;
    }

    String bookingId = "REST-" + System.currentTimeMillis();
    String today = java.time.LocalDate.now().toString();

    // ✅ Store in BookingService
    Booking newBooking = new Booking(
        bookingService.getAllRestaurantBookings().size() + 1,
        tourist.getUsername(),
        restaurant.getRestaurantId(),
        "Restaurant",
        restaurant.getName(),
        tourist.getName(),
        tourist.getEmail(),
        today,
        1,
        true,
        today
    );
    bookingService.addBooking(newBooking);

    String details = "Dear " + tourist.getName() + ",\n\n" +
            "Your restaurant booking is confirmed!\n" +
            "Restaurant: " + restaurant.getName() + "\n" +
            "Booking ID: " + bookingId + "\n" +
            "Date: " + today + "\n\n" +
            "Bon appétit!";

    EmailService.sendBookingConfirmation(tourist.getEmail(), details);
    BufferedImage qr = QRCodeGenerator.generateQRCodeImage(restaurant.getName(), bookingId);
    showBookingDialog(details, qr, "Restaurant Booking Confirmation");
}




    private void showBookingDialog(String details, BufferedImage qrImage, String title) {
        JDialog dialog = new JDialog();
        dialog.setTitle(title);
        dialog.setLayout(new BorderLayout());

        dialog.add(new JLabel(new ImageIcon(qrImage)), BorderLayout.CENTER);

        JLabel detailsLabel = new JLabel("<html>" + details.replace("\n", "<br/>") + "</html>");
        detailsLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        dialog.add(detailsLabel, BorderLayout.SOUTH);

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private void styleBootstrapButton(JButton button, String style) {
        Color bgColor;
        switch (style) {
            case "success":
                bgColor = new Color(25, 135, 84); break;
            case "info":
                bgColor = new Color(13, 202, 240); break;
            default:
                bgColor = new Color(0, 123, 255);
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
