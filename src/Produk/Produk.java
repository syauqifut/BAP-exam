package Produk;

import Penyewaan.Penyewaan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;

public class Produk extends JFrame {
    JPanel panel = new JPanel();
    JPanel formPanel = new JPanel();

    JLabel produkLbl = new JLabel("Tabel Produk");

    DefaultTableModel produkTableModel = new DefaultTableModel(new Object[]{"Id Produk", "Nama Produk", "Harga", "Jumlah"},0);
    JTable produkTbl = new JTable(produkTableModel);

    JLabel idLbl = new JLabel("Id Produk");
    JLabel namaLbl = new JLabel("Nama Produk");
    JLabel hargaLbl = new JLabel("Harga Produk");
    JLabel qtyLable = new JLabel("Jumlah Produk");

    JTextField idInput = new JTextField();
    JTextField namaInput = new JTextField();
    JTextField hargaInput = new JTextField();
    JTextField qtyInput = new JTextField();

    JButton saveBtn = new JButton("Save");

    private Connection connection;

    private void UI() {
        setVisible(true);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        produkLbl.setHorizontalAlignment(SwingConstants.CENTER);
        add(produkLbl, BorderLayout.NORTH);
        add(new JScrollPane(produkTbl), BorderLayout.CENTER);

        formPanel.setLayout(new GridLayout(2, 4));
        formPanel.add(idLbl);
        formPanel.add(namaLbl);
        formPanel.add(hargaLbl);
        formPanel.add(qtyLable);
        formPanel.add(idInput);
        formPanel.add(namaInput);
        formPanel.add(hargaInput);
        formPanel.add(qtyInput);

        panel.setLayout(new GridLayout(2, 1));
        panel.add(formPanel);
        panel.add(saveBtn);
        add(panel, BorderLayout.SOUTH);

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges();
            }
        });
    }

    private void connectDB() {
        String url = "jdbc:mysql://localhost:3306/produk";
        String user = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadProdukData() {
        produkTableModel.setRowCount(0);

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM produk");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                int price = resultSet.getInt("price");
                int quantity = resultSet.getInt("quantity");

                produkTableModel.addRow(new Object[]{productId, productName, price, quantity});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveChanges() {
        int idProduk = Integer.parseInt(idInput.getText());
        int namaProduk = Integer.parseInt(namaInput.getText());
        int hargaProduk = Integer.parseInt(hargaInput.getText());
        int jumlahProduk = Integer.parseInt(qtyInput.getText());

        if (hargaProduk <= 0 || jumlahProduk < 0) {
            JOptionPane.showMessageDialog(this, "Harga dan Jumlah harus lebih dari 0");
            return;
        }

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Produk SET nama = ?, price = ?, quantity = ? WHERE product_id = ?");
            statement.setInt(1, namaProduk);
            statement.setInt(2, hargaProduk);
            statement.setInt(3, jumlahProduk);
            statement.setInt(4, idProduk);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Sukses");
                loadProdukData();
            } else {
                JOptionPane.showMessageDialog(this, "Id Produk tidak ditemukan");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Produk() {
        UI();
    }

    public static void main(String[] args) {
        new Produk();
    }
}
