package aplicacioimmobiliaria;

public class Filtre {
        
    //Array Filtre String
    //Camps: [0]tipus, [1]descripcio, [2]adreca, [3]poblacio
    String[][] arrayFiltreString;
    
    //Array Filtre Int
    //Camps: [0]posicio_usuari, [1]superficie, [2]preu
    int[][] arrayFiltreInt;
    
    //Resultats que s'obtenen aplicat el filtre
    int num_resultats;
    
    //Posició de l'usuari a l'array usuari
    int[] posicioUsuari;
    
    //Posició de l'immoble a l'array total
    int[] posicio;
    
    //tipus
    String filtreTipus;
       
    //població
    String filtrePoblacio;

    //superficie
    int filtreSuperficieMin;
    int filtreSuperficieMax;
    
    //preu
    int filtrePreuMin;
    int filtrePreuMax;

}
