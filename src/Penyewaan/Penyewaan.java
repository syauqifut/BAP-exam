package Penyewaan;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Penyewaan extends JFrame implements ActionListener {
    JPanel tambahanPanel = new JPanel();
    JPanel inputPanel = new JPanel();
    JPanel panelBorder = new JPanel();
    JPanel btnPanel = new JPanel();

    JLabel title = new JLabel("Form Penyewaan");

    JLabel mobilLbl = new JLabel("Pilih Mobil");
    JLabel tambahanLbl = new JLabel("Tambahan");
    JLabel hargaLbl = new JLabel("Harga per hari");
    JLabel hariLbl = new JLabel("Total Hari Sewa");

    String[] mobils = {"Avanza", "Innova", "Agya", "Calya"};
    JComboBox mobilInput = new JComboBox<>(mobils);
    JCheckBox supirInput = new JCheckBox("Supir");
    JCheckBox asuransiInput = new JCheckBox("Asuransi");
    JLabel hargaInput = new JLabel("300000");
    SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 100,1);
    JSpinner hariInput = new JSpinner(spinnerModel);

    JButton submitBtn = new JButton("Submit");
    JButton clearBtn = new JButton("Clear");


    public Penyewaan() {
        setVisible(true);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        title.setFont(new Font(null, Font.BOLD, 32));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        tambahanPanel.setLayout(new FlowLayout());
        tambahanPanel.add(supirInput);
        tambahanPanel.add(asuransiInput);

        inputPanel.setLayout(new GridLayout(4, 2));
        inputPanel.setBorder(new EmptyBorder(12, 12, 12, 12));
        inputPanel.add(mobilLbl);
        inputPanel.add(mobilInput);
        inputPanel.add(tambahanLbl);
        inputPanel.add(tambahanPanel);
        inputPanel.add(hargaLbl);
        hargaInput.setHorizontalAlignment(SwingConstants.CENTER);
        inputPanel.add(hargaInput);
        inputPanel.add(hariLbl);
        inputPanel.add(hariInput);

        btnPanel.setLayout(new GridLayout(1, 2));
        btnPanel.add(submitBtn);
        btnPanel.add(clearBtn);

        panelBorder.setLayout(new BorderLayout());
        panelBorder.add(title, BorderLayout.NORTH);
        panelBorder.add(inputPanel, BorderLayout.CENTER);
        panelBorder.add(btnPanel, BorderLayout.SOUTH);

        add(panelBorder);
        mobilInput.addActionListener(this);
        clearBtn.addActionListener(this);
        submitBtn.addActionListener(this);

    }

    public static void main(String[] args) {
        new Penyewaan();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mobilInput) {
            if (mobilInput.getSelectedItem().equals("Avanza")) {
                hargaInput.setText(Integer.toString(300000));
            } else if (mobilInput.getSelectedItem().equals("Innova")) {
                hargaInput.setText(Integer.toString(450000));
            } else if (mobilInput.getSelectedItem().equals("Agya")) {
                hargaInput.setText(Integer.toString(200000));
            } else if (mobilInput.getSelectedItem().equals("Calya")) {
                hargaInput.setText(Integer.toString(250000));
            }
            System.out.println("halo gays" + mobilInput.getSelectedItem());
        } else if (e.getSource() == clearBtn) {
            mobilInput.setSelectedItem("Avanza");
            supirInput.setSelected(false);
            asuransiInput.setSelected(false);
            hariInput.setValue(0);
        } else if (e.getSource() == submitBtn) {
            int harga = 0;
            int hargaMobil = 0;
            String tambahan = "";

            if (mobilInput.getSelectedItem().equals("Avanza")) {
                hargaMobil = 300000;
            } else if (mobilInput.getSelectedItem().equals("Innova")) {
                hargaMobil = 450000;
            } else if (mobilInput.getSelectedItem().equals("Agya")) {
                hargaMobil = 200000;
            } else if (mobilInput.getSelectedItem().equals("Calya")) {
                hargaMobil = 250000;
            }


            if (supirInput.isSelected()){
                harga += 60000;
                tambahan = " dengan Supir";
            }
            if (asuransiInput.isSelected()){
                harga += 30000;
                tambahan = " dengan Asuransi";
            }

            if (supirInput.isSelected() && asuransiInput.isSelected()){
                tambahan = " dengan Supir dan Asuransi";
            }

            harga += hargaMobil;
            int totalHarga = harga * (Integer) hariInput.getValue();
            String hasil = "Total harga dari penyewaan mobil ber-merk " + mobilInput.getSelectedItem() + " selama " + hariInput.getValue() + " hari" + tambahan + " adalah Rp " + totalHarga;
            JOptionPane.showMessageDialog(null, hasil);
        }
    }
}
