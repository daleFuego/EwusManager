package com.manager.gui.panel.queue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.manager.logic.QueueManager;

@SuppressWarnings("serial")
public class DateFrame extends JFrame {

	@SuppressWarnings("unused")

	private QueueManager queueManager;
	private JPanel contentPane;
	public JTextField textField;

	public DateFrame(final QueueManager queueManager) {
		this.queueManager = queueManager;
		final Calendar calendar = new Calendar(this);
		setTitle("Piewszy wolny termin");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 127);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDate = new JLabel("Wybierz datę pierwszego wolnego terminu");
		lblDate.setBounds(14, 21, 222, 14);
		contentPane.add(lblDate);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(250, 18, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnDate = new JButton("Data");
		btnDate.setBounds(350, 17, 68, 23);
		btnDate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				calendar.setVisible(true);
			}
		});
		contentPane.add(btnDate);

		JButton btnOk = new JButton("OK");
		btnOk.setBounds(350, 51, 68, 23);
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				calendar.setVisible(false);
				queueManager.deliverData("====================================\nPierwszy wolny termin na "
						+ textField.getText() + "\n====================================" + System.lineSeparator());
			}
		});
		contentPane.add(btnOk);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}