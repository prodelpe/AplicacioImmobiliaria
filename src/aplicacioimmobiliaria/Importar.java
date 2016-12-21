package aplicacioimmobiliaria;

public class Importar {

    ImportacioDades dades = new ImportacioDades();

    //Funció principal
    void importarDades(Immoble immoble, Usuari usuari) {
        
        importarImmobles(immoble);
        importarUsuaris(usuari);
    }

    //***********************
    //IMPORTACIÓ IMMOBLES
    //***********************
    //Extret de: http://stackoverflow.com/questions/9673384/java-splitting-data-into-a-2d-array
    Immoble importarImmobles(Immoble immoble) {
        
        String dadesImportar = dades.obtenirDadesImmoblesDesDeCentral();

        //ARRAY TOTAL AMB TOTES LES DADES EN STRING
        //-----------------------------------------
        //
        //Primer posem a un array la fila
        String[] fila = dadesImportar.split("###");
        //I amb el seu length podem saber la mida de l'array on guardarem les dades
        immoble.arrayImmobles = new String[fila.length][];

        //I ara podem anar recorrent les files per crear l'array Total en format String
        for (int i = 0; i < fila.length; i++) {
            immoble.arrayImmobles[i] = fila[i].split("#");
        }

        //SEPARACIÓ EN DOS ARRAYS SEGONS DADES
        //------------------------------------
        //
        //Ara toca separar valors int dels String
        //Inicialitzem els arrays
        immoble.arrayImmoblesString = new String[fila.length][immoble.NUM_CAMPS_IMMOBLE_STRING];
        immoble.arrayImmoblesInt = new int[fila.length][immoble.NUM_CAMPS_IMMOBLE_INT];

        for (int i = 0; i < immoble.arrayImmobles.length; i++) {
            for (int j = 0; j < immoble.arrayImmobles[0].length; j++) {
                //[0]posicio_usuari, [1]tipus, [2]descripcio, [3]adreca, [4]poblacio, [5]superficie, [6]preu

                    
                    //Int: [0]posicio_usuari, [5]superficie, [6]preu

                        immoble.arrayImmoblesInt[i][0] = Integer.parseInt(immoble.arrayImmobles[i][0]);

                        immoble.arrayImmoblesInt[i][1] = Integer.parseInt(immoble.arrayImmobles[i][5]);

                        immoble.arrayImmoblesInt[i][2] = Integer.parseInt(immoble.arrayImmobles[i][6]);
                        
                    //String: [0]tipus, [1]descripcio, [2]adreca, [3]poblacio

                        immoble.arrayImmoblesString[i][0] = immoble.arrayImmobles[i][1];

                        immoble.arrayImmoblesString[i][1] = immoble.arrayImmobles[i][2];

                        immoble.arrayImmoblesString[i][2] = immoble.arrayImmobles[i][3];

                        immoble.arrayImmoblesString[i][3] = immoble.arrayImmobles[i][4];
                
            }
        }

        //Guardem també el número total d'immobles
        immoble.comptadorImmobles = fila.length;

        return immoble;
    }

    //***********************
    //IMPORTACIÓ USUARIS
    //***********************
    //Seguint els mateixos passos que pels immobles
    Usuari importarUsuaris(Usuari usuari) {
        
        String dadesImportar = dades.obtenirDadesUsuarisDesDeCentral();
        String[] fila = dadesImportar.split("###");
        
        usuari.arrayUsuaris = new String[fila.length][];
        
        for (int i = 0; i < fila.length; i++) {
            usuari.arrayUsuaris[i] = fila[i].split("#");
        }
        
        usuari.comptadorUsuaris = fila.length;
        
        return usuari;
    }
}
