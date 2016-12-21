package aplicacioimmobiliaria;

import java.util.Arrays;

public class GestioImmoblesPropis {

    Utilitats executa = new Utilitats();
    Interficie interficie = new Interficie();
    EntradaDades demana = new EntradaDades();

    //Averiguar número d'anuncis d'usuari
    Usuari numeroAnuncis(Usuari usuari, Immoble immoble) {
        
        //Reinicialitzem usuari.numeroAnuncisUsuari per evitar sobreescriure
        usuari.numeroAnuncisUsuari = 0;
        for (int i = 0; i < immoble.arrayImmoblesInt.length; i++) {
            if (immoble.arrayImmoblesInt[i][0] == usuari.posiciousuariActiu) {
                usuari.numeroAnuncisUsuari++;
            }
        }
        return usuari;
    }

    //*********************
    // LLISTAR ANUNCIS
    //*********************
    Filtre consultarPropis(Usuari usuari, Immoble immoble) {
        
        //Crearem un filtre pels anuncis de l'usuari loguejat
        Filtre filtre = new Filtre();
        
        //Ja sabem les mides dels nostres filtres d'usuari gràcies a usuari.numeroAnuncisUsuari
        filtre.arrayFiltreString = new String[usuari.numeroAnuncisUsuari][immoble.NUM_CAMPS_IMMOBLE_STRING];
        filtre.arrayFiltreInt = new int[usuari.numeroAnuncisUsuari][immoble.NUM_CAMPS_IMMOBLE_INT];

        //***********
        //STRING
        //***********
        int num = 0;//Contador pel nou array
        
        //Recorrem tot el array int
        for (int i = 0; i < immoble.arrayImmoblesInt.length; i++) {
            //Si el número de la columna 0 de l'array és igual a la posició de l'usuari actiu...
            if (immoble.arrayImmoblesInt[i][0] == usuari.posiciousuariActiu) {
                //...recorrerem les columnes
                for (int j = 0; j < immoble.NUM_CAMPS_IMMOBLE_STRING; j++) {
                    //I anirem guardant els valors
                    filtre.arrayFiltreString[num][j] = immoble.arrayImmoblesString[i][j];
                }
                num++;
            }
        }

        //***********
        //INT
        //***********
        num = 0;//Reinicialitzem num
        
        //Recorrem tot el array int
        for (int i = 0; i < immoble.arrayImmoblesInt.length; i++) {
            //Si el número de la columna 0 de l'array és igual a la posició de l'usuari actiu...
            if (immoble.arrayImmoblesInt[i][0] == usuari.posiciousuariActiu) {
                //...recorrerem les columnes
                for (int j = 0; j < immoble.NUM_CAMPS_IMMOBLE_INT; j++) {
                    //I anirem guardant els valors
                    filtre.arrayFiltreInt[num][j] = immoble.arrayImmoblesInt[i][j];
                }
                num++;
            }
        }

        //Guardarem també les posicions de l'usuari
        //Inicialitzem l'array on guardar-les
        filtre.posicioUsuari = new int[usuari.numeroAnuncisUsuari];
        num = 0;
        for (int i = 0; i < immoble.arrayImmoblesInt.length; i++) {
            if (immoble.arrayImmoblesInt[i][0] == usuari.posiciousuariActiu) {
                filtre.posicioUsuari[num] = immoble.arrayImmoblesInt[i][0];
                num++;
            }
        }

        //Guardarem també les posicions a l'array total
        filtre.posicio = new int[usuari.numeroAnuncisUsuari];
        num = 0;
        for (int i = 0; i < immoble.arrayImmoblesInt.length; i++) {
            if (immoble.arrayImmoblesInt[i][0] == usuari.posiciousuariActiu) {
                filtre.posicio[num] = i;
                num++;
            }
        }

        return filtre;
    }

    //*********************
    // AFEGIR ANUNCI
    //*********************
    Immoble afegirAnunci(Immoble immoble, Usuari usuari) {
        
        //Cridem la classe menu pel menú que necessitem
        Menu menu = new Menu();
        
        //Fem les preguntes per guardar el nou immoble        
        questionariAnunci(immoble, menu);
        
        //Passem el nou immoble a l'array total
        guardarImmoble(immoble, usuari);
        return immoble;
    }

    Immoble questionariAnunci(Immoble immoble, Menu menu) {
        
        //Variable de control del do-while
        boolean control = false;
        
        //Declarem arrays
        immoble.nouArrayImmobleString = new String[immoble.NUM_CAMPS_IMMOBLE_STRING];
        immoble.nouArrayImmobleInt = new int[immoble.NUM_CAMPS_IMMOBLE_INT];

        do {
            //Pregunta el tipus
            interficie.imprimeix(menu.afegirImmoble[0]);
            interficie.mostrarMenu(menu.menuTipus);
            int temporal = demana.entrarTipus();
            
            switch (temporal) {
                case 1:
                    immoble.nouArrayImmobleString[0] = "Local";
                    break;
                case 2:
                    immoble.nouArrayImmobleString[0] = "Oficina";
                    break;
                case 3:
                    immoble.nouArrayImmobleString[0] = "Pis";
                    break;
                case 4:
                    immoble.nouArrayImmobleString[0] = "Casa";
                    break;
                case 5:
                    immoble.nouArrayImmobleString[0] = "Pàrquing";
                    break;
                case 6:
                    immoble.nouArrayImmobleString[0] = "Traster";
                    break;
            }
            interficie.saltDeLinea();

            //Pregunta la població
            interficie.imprimeix(menu.afegirImmoble[1]);
            immoble.nouArrayImmobleString[3] = demana.entrarTextNoBuit();
            interficie.saltDeLinea();

            //Pregunta l'adreça
            interficie.imprimeix(menu.afegirImmoble[2]);
            immoble.nouArrayImmobleString[2] = demana.entrarTextNoBuit();
            interficie.saltDeLinea();

            //Pregunta la descripció
            interficie.imprimeix(menu.afegirImmoble[3]);
            immoble.nouArrayImmobleString[1] = demana.entrarTextNoBuit();
            interficie.saltDeLinea();

            //Pregunta la superficie
            interficie.imprimeix(menu.afegirImmoble[4]);
            immoble.nouArrayImmobleInt[1] = demana.entrarUnEnter();
            interficie.saltDeLinea();

            //Pregunta el preu
            interficie.imprimeix(menu.afegirImmoble[5]);
            immoble.nouArrayImmobleInt[2] = demana.entrarUnEnter();
            interficie.saltDeLinea();

            //Preguntem si és correcte
            control = demana.confirmar("És correcte?(S/N)", -1);

        } while (control != true);

        return immoble;
    }

    //Funció que guarda un nou immoble als arrays Immoble
    Immoble guardarImmoble(Immoble immoble, Usuari usuari) {
        int novaMida = immoble.arrayImmoblesString.length + 1;

        //***********
        //STRING
        //***********
        //Afegim una linea a l'array
        immoble.arrayImmoblesString = Arrays.copyOf(immoble.arrayImmoblesString, novaMida);

        //Afegim valor null a la nova linea
        //Ni idea de perque sumem 1 a immoble.NUM_CAMPS_IMMOBLE_STRING
        for (int i = 0; i < immoble.NUM_CAMPS_IMMOBLE_STRING + 1; i++) {
            immoble.arrayImmoblesString[novaMida - 1] = new String[i];
        }

        //Adjudiquem valors
        for (int i = 0; i < immoble.NUM_CAMPS_IMMOBLE_STRING; i++) {
            immoble.arrayImmoblesString[novaMida - 1][i] = immoble.nouArrayImmobleString[i];
        }

        //***********
        //INT
        //***********
        //Afegim una linea a l'array
        immoble.arrayImmoblesInt = Arrays.copyOf(immoble.arrayImmoblesInt, novaMida);

        //Afegim valor null a la nova linea
        //Ni idea de perque sumem 1 a immoble.NUM_CAMPS_IMMOBLE_STRING
        for (int i = 0; i < immoble.NUM_CAMPS_IMMOBLE_INT + 1; i++) {
            immoble.arrayImmoblesInt[novaMida - 1] = new int[i];
        }

        //Adjudiquem valors
        for (int i = 0; i < immoble.NUM_CAMPS_IMMOBLE_INT; i++) {
            immoble.arrayImmoblesInt[novaMida - 1][i] = immoble.nouArrayImmobleInt[i];
        }
        
        //Reescribim la posició de l'usuari per completar l'array
        immoble.arrayImmoblesInt[novaMida - 1][0] = usuari.posiciousuariActiu;
        
        //Sumem el nou anunci al compte de l'usuari
        usuari.numeroAnuncisUsuari++;

        return immoble;
    }

    //*********************
    // ELIMINAR ANUNCI
    //*********************
    Immoble eliminarAnunci(Immoble immoble, int posicio) {      

        //Creem variable de la nova mida dels arrays
        int novaMida = immoble.arrayImmoblesString.length - 1;

        //***********
        //STRING
        //***********     
        
        //Fem un loop des de la fila que volem borrar fins al final. He de restar a la posicio. Els arrays comencen per zero!!
        for (int i = posicio; i < novaMida; i++) {
            //I sobrescribim aquesta fila amb els valors posteriors
            immoble.arrayImmoblesString[i] = immoble.arrayImmoblesString[i + 1];
        }
        //Ara treiem la fila null que hauria quedat
        immoble.arrayImmoblesString = Arrays.copyOf(immoble.arrayImmoblesString, novaMida);
        
        //***********
        //INT
        //***********
        
        //Fem un loop des de la fila que volem borrar fins al final. He de restar a la posicio. Els arrays comencen per zero!!
        for (int i = posicio; i < novaMida; i++) {
            //I sobrescribim aquesta fila amb els valors posteriors
            immoble.arrayImmoblesInt[i] = immoble.arrayImmoblesInt[i + 1];
        }
        //Ara treiem la fila null que hauria quedat
        immoble.arrayImmoblesInt = Arrays.copyOf(immoble.arrayImmoblesInt, novaMida);
        
        //Restem un immoble
        immoble.comptadorImmobles--;

        return immoble;
    }

}
