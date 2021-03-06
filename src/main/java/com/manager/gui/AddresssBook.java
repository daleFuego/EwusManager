package com.manager.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.manager.dao.DBData;
import com.manager.utils.DefineUtils;

@SuppressWarnings("serial")
public class AddresssBook extends JFrame {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AddresssBook(final JTextField textFieldAddress) {
		setResizable(false);
		setSize(416, 168);
		getContentPane().setLayout(null);
		setVisible(true);

		JLabel lblWybierzOdbiorc = new JLabel("Odbiorcy");
		lblWybierzOdbiorc.setFont(DefineUtils.FONT);
		lblWybierzOdbiorc.setBounds(8, 8, 66, 14);
		getContentPane().add(lblWybierzOdbiorc);

		JScrollPane scrollPaneReceivers = new JScrollPane();
		scrollPaneReceivers.setBounds(8, 30, 260, 87);
		getContentPane().add(scrollPaneReceivers);

		final JList listAddresses = new JList(DBData.getInstance().getReceivers());
		listAddresses.setFont(DefineUtils.FONT);
		scrollPaneReceivers.setViewportView(listAddresses);

		JButton btnSelect = new JButton("Wybierz");
		btnSelect.setFont(DefineUtils.FONT);
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldAddress.setText(listAddresses.getSelectedValue().toString());
				dispose();
			}
		});
		btnSelect.setBounds(276, 28, 109, 20);
		getContentPane().add(btnSelect);

		JButton btnDelete = new JButton("Usuń");
		btnDelete.setFont(DefineUtils.FONT);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DBData.getInstance().removeFromContacts(listAddresses.getSelectedValue().toString());
					DefaultListModel model = (DefaultListModel) listAddresses.getModel();
					model.remove(listAddresses.getSelectedIndex());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(new JFrame(), "Wystąpił błąd", DefineUtils.APP_TITLE,
							JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});

		listAddresses.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					textFieldAddress.setText(listAddresses.getSelectedValue().toString());
					dispose();
				}
			}
		});

		btnDelete.setBounds(276, 97, 109, 20);
		getContentPane().add(btnDelete);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
