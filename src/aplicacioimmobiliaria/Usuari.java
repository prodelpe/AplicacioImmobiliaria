package aplicacioimmobiliaria;

public class Usuari {

    int NUM_USUARIS = 100;
    int NUM_CAMPS_USUARI = 4;
    int MAXIM_ANUNCIS_PER_USUARI = 3;

    //Camps: [0]DNI, [1]nom, [2]correu, [3]contrassenya
    String[][] arrayUsuaris;
    
    //Camps: [0]DNI, [1]nom, [2]correu, [3]contrassenya
    String[] usuariActiu;
    
    //Posició de l'usuari actiu per obtenir els seus anuncis
    int posiciousuariActiu;
    
    //Número d'anuncis que te un usuari
    int numeroAnuncisUsuari;
    
    //Número d'usuaris registrats
    int comptadorUsuaris;

}
