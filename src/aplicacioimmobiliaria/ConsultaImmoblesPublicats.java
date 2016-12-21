package aplicacioimmobiliaria;

public class ConsultaImmoblesPublicats {

    Interficie interficie = new Interficie();
    EntradaDades demana = new EntradaDades();
    Menu menu = new Menu();

    //--------------------------------
    //Menú i llistat d'array de FILTRE
    //--------------------------------
    int questonari(int opcio_consulta, Filtre filtre, Immoble immoble) {

        int opcio = 0;
        boolean opcio_correcta = false;

        do {

            //Tornar
            switch (opcio_consulta) {

                case 1:

                    opcio_correcta = true;

                    //Creem el filte fent les preguntes necessàries
                    crearFiltre(filtre, menu);

                    //Mostrem el filtre obtingut
                    interficie.imprimeix("Ha escollit: ");
                    interficie.imprimeix(interficie.stringFiltre(filtre));
                    interficie.saltDeLinea();

                    //Apliquem el filtre al total dels immobles
                    aplicarFiltre(filtre, immoble);
                    interficie.saltDeLinea();

                    //Mostrem els número de resultats obtinguts
                    switch (filtre.num_resultats) {
                        case 0:
                            interficie.imprimeix("No s'han trobat resultats");
                            break;
                        case 1:
                            interficie.imprimeix("S'ha trobat 1 resultat");
                            break;
                        default:
                            interficie.imprimeix("S'han trobat " + filtre.num_resultats + " resultats");
                            break;
                    }

                    interficie.saltDeLinea();

                    //Si hi han resultats, els imprimim
                    if (filtre.num_resultats > 0) {

                        interficie.llistaAnuncisFiltre(filtre);
                        interficie.saltDeLinea();
                        opcio = 1;
                        break;

                    } else {

                        interficie.saltDeLinea();
                        opcio = 0;
                        break;
                    }

                case 2:

                    opcio_correcta = true;
                    //Tornar al menu principal
                    opcio = 2;
                    break;

                default:

                    interficie.imprimeix(interficie.ERROR_MENU);
                    interficie.saltDeLinea();
            }

        } while (opcio_correcta = false);

        return opcio;
    }

    //------------------------------------------
    //Funció que recull les variables del FILTRE
    //------------------------------------------
    Filtre crearFiltre(Filtre filtre, Menu menu) {

        //Pregunta el tipus
        interficie.imprimeix(menu.menuCreaFiltre[0]);
        interficie.mostrarMenu(menu.menuTipus);
        int temporal = demana.entrarTipus();

        switch (temporal) {

            case 1:
                filtre.filtreTipus = "Local";
                break;

            case 2:
                filtre.filtreTipus = "Oficina";
                break;

            case 3:
                filtre.filtreTipus = "Pis";
                break;

            case 4:
                filtre.filtreTipus = "Casa";
                break;

            case 5:
                filtre.filtreTipus = "Pàrquing";
                break;

            case 6:
                filtre.filtreTipus = "Traster";
                break;
        }
        
        interficie.saltDeLinea();

        //Pregunta la població
        interficie.imprimeix(menu.menuCreaFiltre[1]);
        filtre.filtrePoblacio = demana.entrarTextNoBuit();
        interficie.saltDeLinea();

        //Pregunta la superfície mínima
        interficie.imprimeix(menu.menuCreaFiltre[2]);
        filtre.filtreSuperficieMin = demana.entrarUnEnter();
        interficie.saltDeLinea();

        //Pregunta la superfície màxima
        interficie.imprimeix(menu.menuCreaFiltre[3]);
        filtre.filtreSuperficieMax = demana.entrarUnEnter();
        interficie.saltDeLinea();

        //Pregunta el preu mínim
        interficie.imprimeix(menu.menuCreaFiltre[4]);
        filtre.filtrePreuMin = demana.entrarUnEnter();
        interficie.saltDeLinea();

        //Pregunta el preu màxim
        interficie.imprimeix(menu.menuCreaFiltre[5]);
        filtre.filtrePreuMax = demana.entrarUnEnter();
        interficie.saltDeLinea();

        return filtre;
    }

    //-------------------------------------------------------------------
    //Funció que aplica les variables del FILTRE i crea els arrays FILTRE
    //-------------------------------------------------------------------
    Filtre aplicarFiltre(Filtre filtre, Immoble immoble) {

        //Creem un array provisional amb la mida del total dels immobles registrats
        //On senyalarem les linees que compleixen el filtre
        int[] posicions_temp_String = new int[immoble.arrayImmoblesString.length];
        int[] posicions_temp_int = new int[immoble.arrayImmoblesString.length];

        //Aquí guardarem el número de coincidencies
        filtre.num_resultats = 0;

        //Primer fem un array on els immobles trobats són del mateix tipus i guardem la posició
        //Anirem guardant el valor -1 a posicions_temp_String excepte si troba una concidència
        for (int i = 0; i < immoble.arrayImmoblesString.length; i++) {

            posicions_temp_String[i] = -1;

            if (immoble.arrayImmoblesString[i][0].equals(filtre.filtreTipus) && immoble.arrayImmoblesString[i][3].equals(filtre.filtrePoblacio)) {
                //Guardem la posició a l'array
                posicions_temp_String[i] = i;
            }
        }

        //Ara trobem a l'array Int les coincidències
        for (int i = 0; i < immoble.arrayImmoblesInt.length; i++) {
            posicions_temp_int[i] = -1;

            //Si posicions_temp_String != -1 és perque compleix les condicions de l'array String en aquella posició
            if (posicions_temp_String[i] != -1 && (immoble.arrayImmoblesInt[i][1] > filtre.filtreSuperficieMin && immoble.arrayImmoblesInt[i][1] < filtre.filtreSuperficieMax) && (immoble.arrayImmoblesInt[i][2] > filtre.filtrePreuMin && immoble.arrayImmoblesInt[i][2] < filtre.filtrePreuMax)) {
                posicions_temp_int[i] = i;
                filtre.num_resultats++;
            }
        }

        //Ja sabem les mides dels nostres arrays de Filtre
        filtre.arrayFiltreString = new String[filtre.num_resultats][immoble.NUM_CAMPS_IMMOBLE_STRING];
        filtre.arrayFiltreInt = new int[filtre.num_resultats][immoble.NUM_CAMPS_IMMOBLE_INT];

        //Ara podem procedir a guardar els arrays resultants de la cerca
        //String
        int num = 0;//Contador pel nou array

        for (int i = 0; i < posicions_temp_int.length; i++) {
            if (posicions_temp_int[i] != -1) {
                //Un cop trobada la posició passem a les columnes
                for (int j = 0; j < immoble.NUM_CAMPS_IMMOBLE_STRING; j++) {
                    filtre.arrayFiltreString[num][j] = immoble.arrayImmoblesString[i][j];
                }
                num++;
            }
        }

        //Int
        num = 0;//Reinicialitzem num
        for (int i = 0; i < posicions_temp_int.length; i++) {
            if (posicions_temp_int[i] != -1) {
                for (int j = 0; j < immoble.NUM_CAMPS_IMMOBLE_INT; j++) {
                    filtre.arrayFiltreInt[num][j] = immoble.arrayImmoblesInt[i][j];
                }
                num++;
            }
        }

        //Guardarem també les posicions dels usuaris
        filtre.posicioUsuari = new int[filtre.num_resultats];
        num = 0;
        for (int i = 0; i < posicions_temp_int.length; i++) {
            if (posicions_temp_int[i] != -1) {
                filtre.posicioUsuari[num] = posicions_temp_int[i];
                num++;
            }
        }

        //Guardarem les posicions de l'immoble a l'array total
        filtre.posicio = new int[filtre.num_resultats];
        num = 0;
        for (int i = 0; i < posicions_temp_int.length; i++) {
            if (posicions_temp_int[i] != -1) {
                filtre.posicio[num] = i;
                num++;
            }
        }

        return filtre;
    }

}
