package br.ic.ufal;

import java.awt.EventQueue;

import br.ic.ufal.interfacesgraficas.TLA_avisos;
import br.ic.ufal.interfacesgraficas.TLA_login;

@SuppressWarnings("unused")
public class Main {
	private static TLA_login tlalogin;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tlalogin = new TLA_login();
				} catch (Exception e) {
					new TLA_avisos().erro_inesperado();
					//e.printStackTrace();
				}
			}
		});
		
	}

}
