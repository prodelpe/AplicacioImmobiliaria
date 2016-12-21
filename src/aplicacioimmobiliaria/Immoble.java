package aplicacioimmobiliaria;

public class Immoble {

    //Inicialitzem només per pillar usuari.NUM_USUARIS
    Usuari usuari = new Usuari();
    Utilitats executa = new Utilitats();

    int MAXIM_IMMOBLES = 3;//Número màxim d'immobles que pot tenir un usuari

    int NUM_IMMOBLES = usuari.NUM_USUARIS * MAXIM_IMMOBLES;
    int NUM_CAMPS_IMMOBLE = 7;
    
    int NUM_CAMPS_IMMOBLE_STRING = 4;
    int NUM_CAMPS_IMMOBLE_INT = 3;

    //Camps: [0]posicio_usuari, [1]tipus, [2]descripcio, [3]adreca, [4]poblacio, [5]superficie, [6]preu
    //(Només per a us provisional abans de partir-lo en String i Int)
    String[][] arrayImmobles;
    
    //Camps: [0]tipus, [1]descripcio, [2]adreca, [3]poblacio
    String[][] arrayImmoblesString;
    
    //Camps: [0]posicio_usuari, [1]superficie, [2]preu
    int[][] arrayImmoblesInt;    
    
    //Comptador immobles
    int comptadorImmobles;
    
    //NOUS IMMOBLES   
    //Camps: [0]tipus, [1]descripcio, [2]adreca, [3]poblacio
    String[] nouArrayImmobleString;
    
    //Camps: [0]posicio_usuari, [1]superficie, [2]preu
    int[] nouArrayImmobleInt;

}
