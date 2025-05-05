package ui.Hospitality;

import hospitality.Hotel;
import hospitality.Restaurant;
import service.BookingService;
import User.UserSession;
import ui.WorkRequest.WorkRequestPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HospitalityManagementPanel extends JPanel {

    private DefaultTableModel hotelTableModel;
    private DefaultTableModel restaurantTableModel;
    private BookingService bookingService;

    private JTable tblHotels;
    private JTable tblRestaurants;
    private JTextField txtHotelName, txtHotelLocation;
    private JTextField txtRestaurantName, txtRestaurantLocation;

    public HospitalityManagementPanel() {
        bookingService = BookingService.getInstance();
        initComponents();
        populateTables();
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
    }

    private void initComponents() {
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        setBackground(new Color(161, 197, 153));

        JLabel lblTitle = new JLabel("Hospitality Management Panel");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 400, 30));

        JButton btnWorkRequest = new JButton("Work Request");
        add(btnWorkRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 130, 30));
        styleButton(btnWorkRequest, new Color(70, 130, 180));
        btnWorkRequest.addActionListener(this::btnWorkRequestActionPerformed);

        JLabel lblHotels = new JLabel("Hotels");
        lblHotels.setFont(new Font("Segoe UI", Font.BOLD, 16));
        add(lblHotels, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        hotelTableModel = new DefaultTableModel(new String[]{"Hotel Name", "Location", "Available Rooms"}, 0);
        tblHotels = new JTable(hotelTableModel);
        JScrollPane hotelScroll = new JScrollPane(tblHotels);
        add(hotelScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 600, 150));

        JButton btnViewHotelBookings = new JButton("View Hotel Bookings");
        add(btnViewHotelBookings, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 80, 180, 30));
        styleButton(btnViewHotelBookings, new Color(161, 182, 132));
        btnViewHotelBookings.addActionListener(e -> viewHotelBookings());

        JLabel lblHotelName = new JLabel("Name:");
        add(lblHotelName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));
        txtHotelName = new JTextField();
        add(txtHotelName, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 180, -1));

        JLabel lblHotelLocation = new JLabel("Location:");
        add(lblHotelLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, -1, -1));
        txtHotelLocation = new JTextField();
        add(txtHotelLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 240, 180, -1));

        JButton btnAddHotel = new JButton("Add");
        JButton btnUpdateHotel = new JButton("Update");
        JButton btnDeleteHotel = new JButton("Delete");

        add(btnAddHotel, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 240, 70, 25));
        add(btnUpdateHotel, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 270, 70, 25));
        add(btnDeleteHotel, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 300, 70, 25));

        styleButton(btnAddHotel, new Color(70, 130, 180));
        styleButton(btnUpdateHotel, new Color(161, 182, 132));
        styleButton(btnDeleteHotel, new Color(161, 182, 132));

        btnAddHotel.addActionListener(e -> addHotel());
        btnUpdateHotel.addActionListener(e -> updateHotel());
        btnDeleteHotel.addActionListener(e -> deleteHotel());

        tblHotels.getSelectionModel().addListSelectionListener(e -> {
            int row = tblHotels.getSelectedRow();
            if (row >= 0) {
                txtHotelName.setText(hotelTableModel.getValueAt(row, 0).toString());
                txtHotelLocation.setText(hotelTableModel.getValueAt(row, 1).toString());
            }
        });

        JLabel lblRestaurants = new JLabel("Restaurants");
        lblRestaurants.setFont(new Font("Segoe UI", Font.BOLD, 16));
        add(lblRestaurants, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

        restaurantTableModel = new DefaultTableModel(new String[]{"Restaurant Name", "Location", "Available Seats"}, 0);
        tblRestaurants = new JTable(restaurantTableModel);
        JScrollPane restaurantScroll = new JScrollPane(tblRestaurants);
        add(restaurantScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 600, 150));

        JButton btnViewRestaurantBookings = new JButton("View Restaurant Bookings");
        add(btnViewRestaurantBookings, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 380, 180, 30));
        styleButton(btnViewRestaurantBookings, new Color(161, 182, 132));
        btnViewRestaurantBookings.addActionListener(e -> viewRestaurantBookings());

        JLabel lblRestaurantName = new JLabel("Name:");
        add(lblRestaurantName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, -1, -1));
        txtRestaurantName = new JTextField();
        add(txtRestaurantName, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 540, 180, -1));

        JLabel lblRestaurantLocation = new JLabel("Location:");
        add(lblRestaurantLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 540, -1, -1));
        txtRestaurantLocation = new JTextField();
        add(txtRestaurantLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 540, 180, -1));

        JButton btnAddRestaurant = new JButton("Add");
        JButton btnUpdateRestaurant = new JButton("Update");
        JButton btnDeleteRestaurant = new JButton("Delete");

        add(btnAddRestaurant, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 540, 70, 25));
        add(btnUpdateRestaurant, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 570, 70, 25));
        add(btnDeleteRestaurant, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 600, 70, 25));

        styleButton(btnAddRestaurant, new Color(70, 130, 180));
        styleButton(btnUpdateRestaurant, new Color(161, 182, 132));
        styleButton(btnDeleteRestaurant, new Color(161, 182, 132));

        btnAddRestaurant.addActionListener(e -> addRestaurant());
        btnUpdateRestaurant.addActionListener(e -> updateRestaurant());
        btnDeleteRestaurant.addActionListener(e -> deleteRestaurant());

        tblRestaurants.getSelectionModel().addListSelectionListener(e -> {
            int row = tblRestaurants.getSelectedRow();
            if (row >= 0) {
                txtRestaurantName.setText(restaurantTableModel.getValueAt(row, 0).toString());
                txtRestaurantLocation.setText(restaurantTableModel.getValueAt(row, 1).toString());
            }
        });
    }

    private void populateTables() {
        hotelTableModel.setRowCount(0);
        restaurantTableModel.setRowCount(0);

        for (Hotel hotel : bookingService.getHotels()) {
            hotelTableModel.addRow(new Object[]{hotel.getName(), hotel.getLocation(), hotel.getAvailableRooms()});
        }

        for (Restaurant restaurant : bookingService.getRestaurants()) {
            restaurantTableModel.addRow(new Object[]{restaurant.getName(), restaurant.getLocation(), restaurant.getCapacity()});
        }
    }

    private void viewHotelBookings() {
        int row = tblHotels.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select a hotel to view bookings.");
            return;
        }

        Hotel hotel = bookingService.getHotels().get(row);
        List<String> bookings = hotel.getBookedTourists();

        if (bookings.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No bookings yet for this hotel.");
            return;
        }

        StringBuilder sb = new StringBuilder("Bookings for " + hotel.getName() + ":\n\n");
        for (String name : bookings) {
            sb.append("• ").append(name).append("\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(300, 200));

        JOptionPane.showMessageDialog(this, scrollPane, "Hotel Bookings", JOptionPane.INFORMATION_MESSAGE);
    }

    private void viewRestaurantBookings() {
        int row = tblRestaurants.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select a restaurant to view bookings.");
            return;
        }

        Restaurant restaurant = bookingService.getRestaurants().get(row);
        List<String[]> bookings = restaurant.getBookedCustomers();

        if (bookings.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No bookings yet for this restaurant.");
            return;
        }

        StringBuilder sb = new StringBuilder("Bookings for " + restaurant.getName() + ":\n\n");
        for (String[] entry : bookings) {
            sb.append("• ").append(entry[0]).append(" (").append(entry[1]).append(")").append("\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(300, 200));

        JOptionPane.showMessageDialog(this, scrollPane, "Restaurant Bookings", JOptionPane.INFORMATION_MESSAGE);
    }

    private void addHotel() {
        String name = txtHotelName.getText().trim();
        String location = txtHotelLocation.getText().trim();
        if (name.isEmpty() || location.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all hotel fields.");
            return;
        }
        Hotel hotel = new Hotel(
                bookingService.getHotels().size() + 1,
                name,
                location,
                100,
                200.0,
                "WiFi, Pool",
                4,
                true
        );
        bookingService.addHotel(hotel);
        hotelTableModel.addRow(new Object[]{name, location, hotel.getAvailableRooms()});
        txtHotelName.setText("");
        txtHotelLocation.setText("");
    }

    private void updateHotel() {
        int row = tblHotels.getSelectedRow();
        if (row < 0) return;

        String name = txtHotelName.getText().trim();
        String location = txtHotelLocation.getText().trim();
        hotelTableModel.setValueAt(name, row, 0);
        hotelTableModel.setValueAt(location, row, 1);
    }

    private void deleteHotel() {
        int row = tblHotels.getSelectedRow();
        if (row < 0) return;
        hotelTableModel.removeRow(row);
        bookingService.deleteHotel(row);
        txtHotelName.setText("");
        txtHotelLocation.setText("");
    }

    private void addRestaurant() {
        String name = txtRestaurantName.getText().trim();
        String location = txtRestaurantLocation.getText().trim();
        if (name.isEmpty() || location.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all restaurant fields.");
            return;
        }
        Restaurant restaurant = new Restaurant(
                bookingService.getRestaurants().size() + 1,
                name,
                "Fine Dining",
                location,
                50,
                "10AM-10PM",
                25.0,
                true
        );
        bookingService.addRestaurant(restaurant);
        restaurantTableModel.addRow(new Object[]{name, location, restaurant.getCapacity()});
        txtRestaurantName.setText("");
        txtRestaurantLocation.setText("");
    }

    private void updateRestaurant() {
        int row = tblRestaurants.getSelectedRow();
        if (row < 0) return;

        String name = txtRestaurantName.getText().trim();
        String location = txtRestaurantLocation.getText().trim();
        restaurantTableModel.setValueAt(name, row, 0);
        restaurantTableModel.setValueAt(location, row, 1);
    }

    private void deleteRestaurant() {
        int row = tblRestaurants.getSelectedRow();
        if (row < 0) return;
        restaurantTableModel.removeRow(row);
        bookingService.deleteRestaurant(row);
        txtRestaurantName.setText("");
        txtRestaurantLocation.setText("");
    }

    private void btnWorkRequestActionPerformed(java.awt.event.ActionEvent evt) {
        String username = UserSession.getInstance().getUsername();
        String organization = UserSession.getInstance().getOrganization();
        String enterprise = UserSession.getInstance().getEnterprise();

        Container container = this.getParent();
        container.removeAll();
        container.add(new WorkRequestPanel(username, organization, enterprise, "Admin"));
        container.revalidate();
        container.repaint();
    }
}
