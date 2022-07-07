package br.ic.ufal.apps.comunidade;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.ic.ufal.bdOP.BD_OP;
import br.ic.ufal.interfacesgraficas.TLA_avisos;
import br.ic.ufal.interfacesgraficas.TLA_login;
import br.ic.ufal.usuario.Usuario;

public class TLA_criarcomunidade {

	private JPanel ctppainel;

	private JFrame frmjanela;

	private Usuario usuario;
	private Comunidade comunidade;

	private JLabel lblnome;
	private JLabel lbldescricao;

	private JTextField txtnome;
	private JTextField txtdescricao;

	private JButton btncriar;
	private JButton btncancelar;

	public TLA_criarcomunidade(TLA_login tla_login, Usuario user) {
		usuario = user;
		ctppainel = new JPanel();
		tla_login.getContentPane().setVisible(false);
		tla_login.setContentPane(ctppainel);
		ctppainel.setBorder(new EmptyBorder(5, 5, 5, 5));
		ctppainel.setLayout(null);
		ctppainel.setVisible(true);
		frmjanela = tla_login;

		lblnome = new JLabel("Nome:");
		lblnome.setBounds(15, 20, 50, 20);
		frmjanela.getContentPane().add(lblnome);

		txtnome = new JTextField();
		txtnome.setBounds(80, 20, 200, 20);
		frmjanela.getContentPane().add(txtnome);

		lbldescricao = new JLabel("Descrição:");
		lbldescricao.setBounds(15, 50, 100, 20);
		frmjanela.getContentPane().add(lbldescricao);

		txtdescricao = new JTextField();
		txtdescricao.setBounds(80, 50, 200, 200);
		txtdescricao.setAutoscrolls(true);
		frmjanela.getContentPane().add(txtdescricao);

		btncriar = new JButton();
		btncriar.setFont(new Font("Arial", 1, 15));
		btncriar.setBounds(145, 255, 70, 35);
		btncriar.setLayout(null);
		btncriar.setText("Criar");
		frmjanela.getContentPane().add(btncriar);
		btncriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					comunidade = new BD_OP().BD_CADASTRARCOMUNIDADE(txtnome.getText(), usuario.getLogin(),
							txtdescricao.getText());
					
					Integer i = comunidade.getId();
					String s = i.toString();
					
					usuario.getComunidadesIds().add(s);
					comunidade.getUsuariosparticipantes().add(usuario.getLogin());
					new BD_OP().BD_UPDATEINFOS(usuario);
					new BD_OP().BD_UPDATEINFOS(comunidade);
					new TLA_avisos().sucesso_comunidadecriada();
					new TLA_comunidade((TLA_login) frmjanela, usuario);
				} catch (Exception e2) {
					new TLA_avisos().erro_comunidanaocriada();
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
				new TLA_comunidade((TLA_login) frmjanela, usuario);
			}
		});

	}
}
