package inf011.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.lang.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.org.apache.xpath.internal.axes.IteratorPool;

import inf011.services.PluginService;
import interfaces.PizzaComponent;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JMenu;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.Console;
import java.awt.event.ActionEvent;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.JLabel;

public class MainFrame extends JFrame {

	private JButton buttonSelect;
	private JButton buttonRemove;
	private JButton btnFazerPizza; 
	private JList list1;
	private JList list2;
	private JPanel contentPane;
	private DefaultListModel listModel1 = new DefaultListModel();
	private DefaultListModel listModel2 = new DefaultListModel();
	private PluginService pluginService;
	private JPanel panel_2;
	private JTextPane textPane;
	
	public MainFrame(PluginService pluginService) {
		this.pluginService = pluginService;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		
		
		Iterator<String> sabores = this.pluginService.getSabores();
		 
		while (sabores.hasNext()) {
			this.listModel1.addElement(sabores.next());
        }		
		
		list1 = new JList(listModel1);
		panel.add(list1);
		
		this.buttonSelect = new JButton("->");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 1;
		gbc_button.gridy = 0;
		contentPane.add(buttonSelect, gbc_button);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 3;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 0;
		contentPane.add(panel_1, gbc_panel_1);
		
		this.list2 = new JList(this.listModel2);
		panel_1.add(list2);			
		
		this.buttonRemove = new JButton("<-");
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(0, 0, 5, 5);
		gbc_button_1.gridx = 1;
		gbc_button_1.gridy = 1;
		contentPane.add(buttonRemove, gbc_button_1);
		
		this.btnFazerPizza = new JButton("Fazer Pizza");
		GridBagConstraints gbc_btnFazerPizza = new GridBagConstraints();
		gbc_btnFazerPizza.insets = new Insets(0, 0, 5, 5);
		gbc_btnFazerPizza.gridx = 1;
		gbc_btnFazerPizza.gridy = 2;
		contentPane.add(btnFazerPizza, gbc_btnFazerPizza);
		
		panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridheight = 2;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 3;
		contentPane.add(panel_2, gbc_panel_2);
		
		textPane = new JTextPane();
		panel_2.add(textPane);
		loadButtonActions();		
	}
	
	private void loadButtonActions() {
		this.buttonRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDeleteClick();
			}
		});
		this.buttonSelect.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		btnSelectClick();
	      	}
		});	
		this.btnFazerPizza.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		btnFazerPizzaClick();
	      	}
		});			
	}

	private void btnSelectClick() {
		this.listModel2.addElement(this.listModel1.getElementAt(this.list1.getSelectedIndex()));
		System.out.println("Added " + this.listModel1.getElementAt(this.list1.getSelectedIndex()));
	}
	private void btnDeleteClick() {
		System.out.println("Removed " + this.listModel2.getElementAt(this.list1.getSelectedIndex()));
		this.listModel2.remove(this.list2.getSelectedIndex());		
	}
	private void btnFazerPizzaClick() {
		PizzaComponent pizza = this.pluginService.fazerPizza(this.listModel2.toArray());
		this.listModel2.removeAllElements();
		this.textPane.setText(pizza.preparar());
	}
	
}
