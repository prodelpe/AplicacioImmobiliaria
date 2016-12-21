package aplicacioimmobiliaria;

public class Login {

    Usuari usuari = new Usuari();

    boolean login(Usuari usuari, String dni, String contrassenya) {
        boolean login = false;

        //Recorrerem l'array usuaris
        for (int i = 0; i < usuari.arrayUsuaris.length; i++) {
            if (dni.equals(usuari.arrayUsuaris[i][0]) && contrassenya.equals(usuari.arrayUsuaris[i][3])) {
                //Primer guardem ja la posició de l'usuari
                usuari.posiciousuariActiu = i;
                //Guardem l'usuari actiu
                usuari.usuariActiu = new String[usuari.NUM_CAMPS_USUARI];
                for (int j = 0; j < usuari.NUM_CAMPS_USUARI; j++) {
                    usuari.usuariActiu[j] = usuari.arrayUsuaris[i][j];

                    login = true;
                }
            }
        }
        return login;
    }
}
