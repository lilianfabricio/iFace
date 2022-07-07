package br.ic.ufal.apps.chat;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.ic.ufal.bdOP.BD_OP;
import br.ic.ufal.interfacesgraficas.TLA_avisos;
import br.ic.ufal.interfacesgraficas.TLA_inicial;
import br.ic.ufal.interfacesgraficas.TLA_login;
import br.ic.ufal.usuario.Usuario;

public class TLA_chat {

	private JPanel ctppainel;

	private JFrame frmjanela;

	private Usuario usuario;
	private Usuario amigo;
	private Chat chat;

	private JLabel lblsuasconversas;
	private JLabel lblprousuario;

	private JTextField txtprousuario;

	private JButton btnconversar;
	private JButton btnremoverconversa;
	private JButton btnbuscar;
	private JButton btncancelar;

	private JComboBox<String> cbconversas;

	public TLA_chat(TLA_login tla_login, Usuario user) {
		usuario = user;
		ctppainel = new JPanel();
		tla_login.getContentPane().setVisible(false);
		tla_login.setContentPane(ctppainel);
		ctppainel.setBorder(new EmptyBorder(5, 5, 5, 5));
		ctppainel.setLayout(null);
		ctppainel.setVisible(true);
		frmjanela = tla_login;

		lblsuasconversas = new JLabel("Seus chats:");
		lblsuasconversas.setBounds(15, 30, 100, 20);
		tla_login.getContentPane().add(lblsuasconversas);

		cbconversas = new JComboBox<String>();
		cbconversas.setBounds(15, 50, 120, 20);
		for (String n : usuario.getChatsIds()) {
			cbconversas.addItem(n);
		}

		btnconversar = new JButton();
		btnconversar.setFont(new Font("Arial", 1, 15));
		btnconversar.setBounds(15, 75, 120, 30);
		btnconversar.setLayout(null);
		btnconversar.setText("Conversar!");
		frmjanela.getContentPane().add(btnconversar);
		btnconversar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chat = new BD_OP().BD_BUSCARCHATPID((String)cbconversas.getSelectedItem());
				new TLA_chataberta((TLA_login) frmjanela, usuario, chat);
			}
		});

		frmjanela.getContentPane().add(cbconversas);

		btnremoverconversa = new JButton("Deletar conversa");
		btnremoverconversa.setBounds(140, 50, 150, 20);
		frmjanela.getContentPane().add(btnremoverconversa);
		btnremoverconversa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// chat = new BD_OP().BD_BUSCARCHATPID((String)
					// getCbamigos().getSelectedItem());
					/*
					 * for (String n : chat.getParticipantes()) { Usuario
					 * usuario = new BD_OP().BD_BUSCARPLOGIN(n);
					 * System.out.println("USUARIOOOOOOOOOOO>"+usuario.getLogin(
					 * )); if (usuario != null) { Integer i = chat.getId();
					 * String s = i.toString(); usuario.getChatsIds().remove(s);
					 * chat.getParticipantes().remove(usuario.getLogin()); new
					 * BD_OP().BD_UPDATEINFOS(usuario); new
					 * BD_OP().BD_UPDATEINFOS(chat); } }
					 */
					usuario.getChatsIds().remove((String) getCbamigos().getSelectedItem());
					new BD_OP().BD_UPDATEINFOS(usuario);
					new TLA_avisos().sucesso_chatdeletado();
				} catch (Exception e2) {
					// e2.printStackTrace();
				}
			}
		});

		lblprousuario = new JLabel("Conversar com:");
		lblprousuario.setBounds(15, 245, 120, 20);
		lblprousuario.setVisible(true);
		frmjanela.getContentPane().add(lblprousuario);

		txtprousuario = new JTextField("");
		txtprousuario.setBounds(15, 270, 200, 20);
		txtprousuario.setVisible(true);
		frmjanela.getContentPane().add(txtprousuario);

		btnbuscar = new JButton();
		btnbuscar.setFont(new Font("Arial", 1, 15));
		btnbuscar.setBounds(15, 295, 120, 30);
		btnbuscar.setLayout(null);
		btnbuscar.setText("Conversar!");
		frmjanela.getContentPane().add(btnbuscar);
		btnbuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					amigo = new BD_OP().BD_BUSCARPLOGIN(getTxtprousuario().getText());
					if (amigo != null) {

						chat = new BD_OP().BD_CADASTRARCHAT();

						chat.getParticipantes().add(usuario.getLogin());
						chat.getParticipantes().add(amigo.getLogin());

						Integer i = chat.getId();
						String s = i.toString();

						usuario.addChatsIds(s);
						amigo.addChatsIds(s);

						new BD_OP().BD_UPDATEINFOS(chat);
						new BD_OP().BD_UPDATEINFOS(usuario);
						new BD_OP().BD_UPDATEINFOS(amigo);

						new TLA_chataberta((TLA_login) frmjanela, usuario, chat);
					} else {
						new TLA_avisos().erro_usuarionaoexiste();
					}
				} catch (Exception e2) {
					// e2.printStackTrace();
					
					new TLA_avisos().erro_inesperado();
				}
			}
		});

		btncancelar = new JButton();
		btncancelar.setFont(new Font("Arial", 0, 8));
		btncancelar.setBounds(215, 295, 70, 30);
		btncancelar.setLayout(null);
		btncancelar.setText("Cancelar");
		frmjanela.getContentPane().add(btncancelar);
		btncancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TLA_inicial((TLA_login) frmjanela, usuario);
			}
		});

	}

	public JComboBox<String> getCbamigos() {
		return cbconversas;
	}

	public JTextField getTxtprousuario() {
		return txtprousuario;
	}

	public <E> void gerarcombobox(JComboBox<E> combobox, ArrayList<E> list) {
		for (E n : list) {
			combobox.addItem(n);
		}

	}

}
