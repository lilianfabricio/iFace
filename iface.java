import java.util.Scanner; 

public class Menu {
	static Scanner input = new Scanner(System.in);
	static String[][] usuario = new String[256][3];

    static int[] count = new int[256];
    static int opcao = 0;
    
    public static void main(String[] args){
        menuPrincipal();
        int opcaoMenu = input.nextInt();

        while(true){
            if (opcao == 1){
                criarConta();    
            }
            else if (opcao == 2){
                editarPerfil();
            }            
        }
    }

}

public static menuPrincipal(){
    System.out.println("iFace");
    System.out.println("Digite (1) para criar uma nova conta.");
    System.out.println("Digite (2) para editar perfil.");
}


public static void criarConta() {
	System.out.println("Insira seu nome de usuario:");
	usuario[i][0] = input.nextLine();
    System.out.println("Insira o login:");
	usuario[i][1] = input.nextLine();
	System.out.println("Insira a sua senha:");
	usuario[i][2] = input.nextLine();
	for (int j = 0; j < i; j++) {
        if (usuario[j][0].equals(usuario[i][0])) {
            System.out.println("Esse nome de usuario já existe, tente novamente");
			criarConta();
		}
	}

	if (usuario[i][0] != null && usuario[i][1] != null && usuario[i][2] != null) {
		System.out.println("Sua conta foi criada!");
	}	
}

public static editarPerfil(){
	System.out.println("Para mudar o nome de usuário, digite (1)");
	System.out.println("Para mudar o login, digite (2)");
    System.out.println("Para mudar a senha, digite (3)");
	int opcaoEdicao = input.nextInt();
	input.nextLine();

    if (opcaoEdicao == 1){
        System.out.println("Insira seu novo nome de usuario");
	    usuario[i][0] = input.nextLine();

        for (int j = 0; j < i; j++) {
            if (usuario[j][0].equals(usuario[i][0])) {
                System.out.println("Esse nome de usuario já existe, tente novamente");
			    editarPerfil();
		    }
	    }
    }

    else if (opcaoEdicao == 2){
        System.out.println("Insira o novo login:");
	    usuario[i][1] = input.nextLine();
    }

    else if (opcaoEdicao == 3){
        System.out.println("Confirme sua senha atual:");
        usuario[i][3] = input.nextLine(); //usuario[i][3] só para comparar com a senha previamente escolhida
        
        for(int k = 0; m < n; m++){
            if (usuario[k][3].equals(usuario[n][2])){
                System.out.println("Senha antiga confirmada!");

                System.out.println("Nova senha:");
                usuario[i][2] = input.nextLine();
            }
        }
    }

}


