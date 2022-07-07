package br.ic.ufal.apps.comunidade;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class TLA_comunidade {

	private JPanel ctppainel;

	private JFrame frmjanela;

	private Usuario usuario;
	private Comunidade comunidade;

	private JLabel lblsuascomunidades;
	private JLabel lblprocomunidade;
	private JLabel lblcriarcomunidade;

	private JTextField txtprocomunidade;

	private JButton btnacessar;
	private JButton btnbuscar;
	private JButton btncriar;
	private JButton btncancelar;

	private JComboBox<String> cbcomunidades;

	public TLA_comunidade(TLA_login tla_login, Usuario user) {
		usuario = user;
		ctppainel = new JPanel();
		tla_login.getContentPane().setVisible(false);
		tla_login.setContentPane(ctppainel);
		ctppainel.setBorder(new EmptyBorder(5, 5, 5, 5));
		ctppainel.setLayout(null);
		ctppainel.setVisible(true);
		frmjanela = tla_login;

		lblsuascomunidades = new JLabel("Suas comunidades");
		lblsuascomunidades.setBounds(15, 30, 100, 20);
		tla_login.getContentPane().add(lblsuascomunidades);

		cbcomunidades = new JComboBox<String>();
		cbcomunidades.setBounds(15, 50, 120, 20);
		for (String n : usuario.getComunidadesIds()) {
			cbcomunidades.addItem(n);
		}
		frmjanela.getContentPane().add(cbcomunidades);

		btnacessar = new JButton();
		btnacessar.setFont(new Font("Arial", 1, 15));
		btnacessar.setBounds(15, 75, 120, 30);
		btnacessar.setLayout(null);
		btnacessar.setText("Acessar");
		frmjanela.getContentPane().add(btnacessar);
		btnacessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					comunidade = new BD_OP().BD_BUSCARCOMUNIDADEPID((String) cbcomunidades.getSelectedItem());
					new TLA_perfildacomunidade((TLA_login) frmjanela, usuario, comunidade);
				} catch (Exception e2) {
					new TLA_avisos().erro_inesperado();
				}

			}
		});

		lblprocomunidade = new JLabel("Procurar comunidade:");
		lblprocomunidade.setBounds(15, 245, 140, 20);
		lblprocomunidade.setVisible(true);
		frmjanela.getContentPane().add(lblprocomunidade);

		txtprocomunidade = new JTextField("");
		txtprocomunidade.setBounds(15, 270, 200, 20);
		txtprocomunidade.setVisible(true);
		frmjanela.getContentPane().add(txtprocomunidade);

		btnbuscar = new JButton();
		btnbuscar.setFont(new Font("Arial", 1, 15));
		btnbuscar.setBounds(15, 295, 120, 30);
		btnbuscar.setLayout(null);
		btnbuscar.setText("Buscar!");
		frmjanela.getContentPane().add(btnbuscar);
		btnbuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					comunidade = new BD_OP().BD_BUSCARCOMUNIDADEPID((String) cbcomunidades.getSelectedItem());
					new TLA_perfildacomunidade((TLA_login) frmjanela, usuario, comunidade);
				} catch (Exception e2) {
					new TLA_avisos().erro_comunidadenaoexiste();
				}
			}
		});

		lblcriarcomunidade = new JLabel("Criar comunidade:");
		lblcriarcomunidade.setBounds(175, 30, 120, 20);
		lblcriarcomunidade.setVisible(true);
		frmjanela.getContentPane().add(lblcriarcomunidade);

		btncriar = new JButton();
		btncriar.setFont(new Font("Arial", 1, 15));
		btncriar.setBounds(175, 60, 100, 30);
		btncriar.setLayout(null);
		btncriar.setText("Criar!");
		frmjanela.getContentPane().add(btncriar);
		btncriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new TLA_criarcomunidade((TLA_login) frmjanela, usuario);
				} catch (Exception e2) {
					//
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
}
