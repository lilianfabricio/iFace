package br.ic.ufal.interfacesgraficas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.ic.ufal.bdOP.BD_OP;
import br.ic.ufal.usuario.Usuario;

public class TLA_login extends JFrame {
	private static final long serialVersionUID = 1L;

	private JFrame frmjanela;
	private TLA_login tla_login;
	private JPanel ctppainel;
	private final JPanel ctppainelinicial;

	private JTextField txtUsuario;
	private JTextField txtSenha;
	private JLabel lblUsuario;
	private JLabel lblSenha;
	private JButton btnLogar;
	private JButton btnCadastrar;

	private Usuario usuario;

	public TLA_login() {
		frmjanela = this;
		frmjanela.setResizable(false);
		frmjanela.setVisible(true);
		frmjanela.setTitle("IFACE");
		frmjanela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmjanela.setSize(300, 380);
		frmjanela.setLocationRelativeTo(null);

		ctppainel = new JPanel();
		ctppainel.setBorder(new EmptyBorder(5, 5, 5, 5));
		ctppainel.setLayout(null);
		frmjanela.setContentPane(ctppainel);
		
		lblUsuario = new JLabel("Usuário:");
		lblUsuario.setBounds(32, 62, 55, 16);
		ctppainel.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(85, 60, 150, 16);
		txtUsuario.setColumns(10);
		ctppainel.add(txtUsuario);

		lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(32, 82, 55, 16);
		ctppainel.add(lblSenha);

		txtSenha = new JTextField();
		txtSenha.setBounds(85, 82, 150, 16);
		txtSenha.setColumns(10);
		ctppainel.add(txtSenha);

		btnLogar = new JButton();
		btnLogar.setBounds(45, 300, 80, 30);
		btnLogar.setLayout(null);
		btnLogar.setText("Logar!");
		ctppainel.add(btnLogar);
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					usuario = new BD_OP().BD_LOGAR(getTxtUsuario().getText(), getTxtSenha().getText());
					if(usuario != null){
						new TLA_avisos().sucesso_login();
						new TLA_inicial(tla_login, usuario);
					}else{
						new TLA_avisos().erro_login();
					}
				} catch (Exception e2) {
					// e2.printStackTrace();
					new TLA_avisos().erro_servidor();
				}
			}
		});

		btnCadastrar = new JButton();
		btnCadastrar.setBounds(150, 300, 95, 30);
		btnCadastrar.setLayout(null);
		btnCadastrar.setText("Cadastrar");
		ctppainel.add(btnCadastrar);
		tla_login = this;
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TLA_cadastro(tla_login);
			}
		});
		
		ctppainelinicial= ctppainel;

	}

	// GETS
	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public JTextField getTxtSenha() {
		return txtSenha;
	}

	public JLabel getLblUsuario() {
		return lblUsuario;
	}

	public JLabel getLblSenha() {
		return lblSenha;
	}
	
	public JPanel getCtppainelinicial(){
		return ctppainelinicial;
	}

	// EndGETS
	// SETS
	public void setTxtUsuario(JTextField txtUsuario) {
		this.txtUsuario = txtUsuario;
	}

	public void setTxtSenha(JTextField txtSenha) {
		this.txtSenha = txtSenha;
	}

	public void setLblUsuario(JLabel lblUsuario) {
		this.lblUsuario = lblUsuario;
	}

	public void setLblSenha(JLabel lblSenha) {
		this.lblSenha = lblSenha;
	}
	// ENDSETS
}
