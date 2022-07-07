package br.ic.ufal.interfacesgraficas;

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

@SuppressWarnings("unused")
public class TLA_cadastro {

	private TLA_login frmjanela;

	private JPanel ctpjanelaant;
	private JPanel ctppainel;

	public JTextField txtNome;
	public JTextField txtUsuario;
	public JTextField txtSenha;
	public JLabel lblNome;
	public JLabel lblUsuario;
	public JLabel lblSenha;
	public JButton btnCadastrar;
	public JButton btnCancelar;

	public TLA_cadastro(TLA_login tla_login) {
		ctpjanelaant = (JPanel) tla_login.getContentPane();
		tla_login.getContentPane().setVisible(false);
		ctppainel = new JPanel();
		ctppainel.setBorder(new EmptyBorder(5, 5, 5, 5));
		ctppainel.setLayout(null);
		ctppainel.setVisible(true);
		tla_login.setContentPane(ctppainel);
		frmjanela = tla_login;

		lblNome = new JLabel("Nome:");
		lblNome.setBounds(32, 62, 55, 16);
		ctppainel.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(85, 62, 150, 16);
		txtNome.setColumns(10);
		ctppainel.add(txtNome);

		lblUsuario = new JLabel("Usuário:");
		lblUsuario.setBounds(32, 82, 55, 16);
		ctppainel.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(85, 82, 150, 16);
		txtUsuario.setColumns(10);
		ctppainel.add(txtUsuario);

		lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(32, 102, 55, 16);
		ctppainel.add(lblSenha);

		txtSenha = new JTextField();
		txtSenha.setBounds(85, 102, 150, 16);
		txtSenha.setColumns(10);
		ctppainel.add(txtSenha);

		btnCadastrar = new JButton();
		btnCadastrar.setBounds(100, 300, 100, 30);
		btnCadastrar.setLayout(null);
		btnCadastrar.setText("Cadastrar");
		ctppainel.add(btnCadastrar);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (new BD_OP().BD_CADASTRAR(getTxtUsuario().getText(), getTxtSenha().getText(),
						getTxtNome().getText())) {
					new TLA_avisos().sucesso_cadastro();
					frmjanela.getContentPane().setVisible(false);
					frmjanela.setContentPane(ctpjanelaant);
					frmjanela.getContentPane().setVisible(true);
				} else {
					new TLA_avisos().erro_cadastro();
				}

			}
		});

		btnCancelar = new JButton();
		Font font = new Font("Arial", 0, 8);
		btnCancelar.setFont(font);
		btnCancelar.setBounds(220, 320, 70, 30);
		btnCancelar.setLayout(null);
		btnCancelar.setText("Cancelar");
		ctppainel.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new TLA_login();
				} catch (Exception e2) {
					new TLA_avisos().erro_cadastro();
				}
			}
		});
	}

	public JTextField getTxtNome() {
		return txtNome;
	}

	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public JTextField getTxtSenha() {
		return txtSenha;
	}
}
