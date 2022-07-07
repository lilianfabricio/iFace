package br.ic.ufal.apps.chat;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import br.ic.ufal.bdOP.BD_OP;
import br.ic.ufal.interfacesgraficas.TLA_inicial;
import br.ic.ufal.interfacesgraficas.TLA_login;
import br.ic.ufal.usuario.Usuario;

public class TLA_chataberta {

	private JPanel ctppainel;

	private JFrame frmjanela;

	private JTextArea txtachat;
	private JTextArea txtamensagem;

	private JButton btnenviar;
	private JButton btncancelar;

	private Usuario usuario;
	private Chat chat;

	public TLA_chataberta(TLA_login tla_login, Usuario user, Chat cht) {
		frmjanela = tla_login;
		usuario = user;
		chat = cht;
		ctppainel = new JPanel();
		tla_login.getContentPane().setVisible(false);
		tla_login.setContentPane(ctppainel);
		ctppainel.setBorder(new EmptyBorder(5, 5, 5, 5));
		ctppainel.setLayout(null);
		ctppainel.setVisible(true);
		frmjanela = tla_login;
		
		// txtachat = new JTextArea();
		// txtachat.setText(chat.getChat());
		// txtachat.setBounds(50, 50, 100, 100);
		// txtachat.setLineWrap(true);

		txtachat = new JTextArea(chat.getChat());
		txtachat.setEditable(false);
		txtachat.setVisible(true);
		txtachat.setBounds(10, 10, 200, 200);
		frmjanela.getContentPane().add(txtachat);

		txtamensagem = new JTextArea("");
		txtamensagem.setEditable(true);
		txtamensagem.setVisible(true);
		txtamensagem.setBounds(10, 220, 200, 100);
		txtamensagem.setLineWrap(true);
		frmjanela.getContentPane().add(txtamensagem);

		btnenviar = new JButton("Enviar!");
		btnenviar.setBounds(215, 225, 75, 90);
		frmjanela.getContentPane().add(btnenviar);
		btnenviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chat.setChat(chat.getChat()+usuario.getNome()+": "+txtamensagem.getText().trim()+"\n");
				new BD_OP().BD_UPDATEINFOS(chat);
				new TLA_chataberta((TLA_login) frmjanela, usuario, chat);
			}
		});
		
		btncancelar = new JButton();
		btncancelar.setFont(new Font("Arial", 0, 8));
		btncancelar.setBounds(215, 180, 70, 30);
		btncancelar.setLayout(null);
		btncancelar.setText("Cancelar");
		frmjanela.getContentPane().add(btncancelar);
		btncancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TLA_inicial((TLA_login) frmjanela, usuario);
			}
		});
		
	}

	public JTextArea getTxtachat() {
		return txtachat;
	}

	public void setTxtachat(JTextArea txtachat) {
		this.txtachat = txtachat;
	}

	public JTextArea getTxtamensagem() {
		return txtamensagem;
	}

	public void setTxtamensagem(JTextArea txtamensagem) {
		this.txtamensagem = txtamensagem;
	}
}
