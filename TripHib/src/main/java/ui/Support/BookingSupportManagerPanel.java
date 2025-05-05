package ui.Support;

import Booking.Booking;
import service.BookingService;
import tourist.Tourist;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BookingSupportManagerPanel extends JPanel {
    private JTable bookingTable;
    private DefaultTableModel tableModel;
    private BookingService bookingService;

    public BookingSupportManagerPanel() {
        bookingService = BookingService.getInstance();
        setLayout(new BorderLayout());
        setBackground(new Color(248, 249, 250));

        JLabel title = new JLabel("📋 All Customer Bookings", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{
                "Booking ID", "Category", "Item Name", "Customer Name", "Customer Email"
        }, 0);

        bookingTable = new JTable(tableModel);
        bookingTable.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(bookingTable);
        add(scrollPane, BorderLayout.CENTER);

        populateTable();
    }

    private void populateTable() {
        tableModel.setRowCount(0); // clear previous data

        List<Booking> allBookings = bookingService.getAllAttractionBookings();
        allBookings.addAll(bookingService.getAllEventBookings());
        allBookings.addAll(bookingService.getAllHotelBookings());
        allBookings.addAll(bookingService.getAllRestaurantBookings());
        allBookings.addAll(bookingService.getAllTransportBookings());

        for (Booking booking : allBookings) {
            String category = booking.getItemType();
            String bookingId = String.valueOf(booking.getBookingId());
            String itemName = booking.getItemType() + " #" + booking.getItemId();
            Tourist tourist = bookingService.getTouristByUsername(booking.getTouristId());
            String name = tourist != null ? tourist.getName() : "Unknown";
            String email = tourist != null ? tourist.getEmail() : "N/A";

            tableModel.addRow(new Object[]{bookingId, category, itemName, name, email});
        }
    }
}
