/*
APLICACIÓ IMMOBILIARIA
Author: Pau Rodellino Pérez
 */
package aplicacioimmobiliaria;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AplicacioImmobiliaria {

    //CLASSES DE LA APLICACIO
    //
    //Estructura de dades
    Immoble immoble = new Immoble();
    Filtre filtre = new Filtre();
    Usuari usuari = new Usuari();
    Menu menu = new Menu();

    //
    //Funcions
    ConsultaImmoblesPublicats consulta = new ConsultaImmoblesPublicats(); //Apartat 2
    GestioImmoblesPropis gestio = new GestioImmoblesPropis(); //Apartat 1
    Importar importa = new Importar();
    Login login = new Login();

    //
    //Utilitats
    EntradaDades demana = new EntradaDades(); //Funcions relacionades amb la entrada de dades per part de l'usuari
    Interficie interficie = new Interficie(); //Funcions relacionades amb extreure dades per pantalla
    Utilitats executa = new Utilitats(); //Cajón de sastre
    //
    //

    //------------------------------
    // MAIN
    //------------------------------
    public static void main(String[] args) {
        AplicacioImmobiliaria main = new AplicacioImmobiliaria();
        main.menuPrincipal();
        //main.proves();
    }

    private void proves() {
//        importa.importarDades(immoble, usuari);
//        login.login(usuari, "31898139Z", "1111");
//        gestioAnuncisPropis();
    }

    //------------------------------
    // MENU PRINCIPAL
    //------------------------------
    private void menuPrincipal() {

        // Importació de dades
        importa.importarDades(immoble, usuari);

        //Salt perque no es noti el canvi de pantalla
        interficie.salt();

        //Presentació
        interficie.presentacio("AGÈNCIA IMMOBILIÀRIA");

        //Titol Menu Principal
        int opcio = interficie.mostrarMenu("Menu Principal", menu.menuPrincipal);

        //Anem al login
        login(opcio);
    }

    //------------------------------
    // LOGIN
    //------------------------------
    private void login(int opcio) {

        interficie.salt();

        //Ens loguejem
        interficie.login(usuari, login);

        //Donem la benvinguda a l'usuari
        interficie.salt();
        interficie.imprimeix("Benvingut/da " + usuari.usuariActiu[1] + " !!!");

        //Salt de temps abns de començar el programa
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AplicacioImmobiliaria.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Dirigim segons la opció triada
        if (opcio == 1) {
            gestioAnuncisPropis();
        } else if (opcio == 2) {
            questionariAnuncisPublicats();
        }
    }

    //------------------------------
    // GESTIÓ D'ANUNCIS PROPIS
    //------------------------------
    private void gestioAnuncisPropis() {

        interficie.salt();

        //Variable de control
        boolean opcio_correcta = false;

        //Primer averigüem si l'usuari té anuncis
        gestio.numeroAnuncis(usuari, immoble);

        //Obtenim els anuncis de l'usuari i els guardem al Filtre
        filtre = gestio.consultarPropis(usuari, immoble);

        //Menu
        int opcio_gestio = interficie.mostrarMenu("Gestió d'anuncis", menu.menuGestioAnuncisPropis);

        //Comença el bucle del menu
        do {
            switch (opcio_gestio) {

                //CONSULTAR ANUNCIS PUBLICATS
                case 1:

                    if (usuari.numeroAnuncisUsuari == 0) {
                        demana.premUnaTeclaMissatge("No tens cap anunci. Prem una tecla per tornar al menú");
                        gestioAnuncisPropis();

                    } else {

                        //Pantalla nova
                        interficie.salt();
                        //Un cop obtingut el filtre els podem mostrar
                        interficie.llistaAnuncisFiltre(filtre);
                        //I ja estem llestos per tornar al menú
                        demana.premUnaTeclaMissatge("Prem un tecla per tornar al menu");
                        gestioAnuncisPropis();
                    }

                    break;

                //CONSULTAR DETALL ANUNCI
                case 2:

                    //Preguntem l'id a consultar
                    interficie.imprimeix("Introdueix l'ID de l'immoble");

                    //Comencem bucle establint una variable de control
                    boolean control;

                    //El guardem a la variable id
                    int id = demana.entrarUnEnter();

                    //Esbrinem si aquest id està al filtre
                    control = interficie.preguntaIdFiltre(filtre, id);

                    //Si ho es el fem tornar al menú
                    if (!control) {
                        interficie.imprimeix("No tens cap anunci amb aquest id");
                        demana.premUnaTeclaMissatge("Prem una tecla per tornar al menu");
                        gestioAnuncisPropis();
                    }

                    //...pantalla nova i mostrem detall
                    interficie.salt();
                    interficie.detallAnunci(immoble, id);

                    //I ja estem llestos per tornar al menú
                    demana.premUnaTeclaMissatge("Prem un tecla per tornar al menu");
                    gestioAnuncisPropis();

                    break;

                //AFEGIR NOU ANUNCI
                case 3:

                    interficie.salt();

                    //Si es tenen menys de 3 anuncis registrats podrem afegir
                    if (usuari.numeroAnuncisUsuari < usuari.MAXIM_ANUNCIS_PER_USUARI) {

                        //Funció qëstionari
                        gestio.afegirAnunci(immoble, usuari);

                        //Mostrem el detall de l'immoble introduït (serà l'últim a l'array)
                        id = immoble.arrayImmoblesString.length - 1;
                        interficie.salt();
                        interficie.detallAnunci(immoble, id);

                    } else {

                        interficie.imprimeix("No es poden tenir més de " + usuari.MAXIM_ANUNCIS_PER_USUARI + " anuncis. Esborra un per poder-ne afegir");
                    }

                    //I ja estem llestos per tornar al menú
                    demana.premUnaTeclaMissatge("Prem un tecla per tornar al menu");
                    gestioAnuncisPropis();

                    break;

                //ELIMINAR ANUNCI
                case 4:

                    interficie.salt();

                    //Demanem posició a esborrar
                    interficie.imprimeix("Entra l'id de l'anunci que vols borrar: ");
                    id = demana.entrarUnEnter();

                    //Comprovem si aquest id està al filtre
                    control = interficie.preguntaIdFiltre(filtre, id);

                    if (!control) {
                        interficie.imprimeix("No tens cap anunci amb aquest id");
                        demana.premUnaTeclaMissatge("Prem una tecla per tornar al menu");
                        gestioAnuncisPropis();
                    }

                    //Confirmació per esborrar
                    interficie.imprimeix("L'anunci " + id + " s'eliminarà de manera permanent!!!");
                    boolean confirmar = demana.confirmar("Vols continuar?(S/N)", -1);

                    //Si és decideix esborrar
                    if (confirmar) {

                        gestio.eliminarAnunci(immoble, id);
                        interficie.imprimeix("L'anunci s'ha esborrat. Prem una tecla per continuar");
                        demana.PremUnaTecla();

                        //Si no, tornem al menu
                    } else {

                        gestioAnuncisPropis();
                    }

                    gestioAnuncisPropis();

                    break;

                //TORNAR AL MENU PRINICIPAL    
                case 5:

                    menuPrincipal();

                    break;

                default:
                    interficie.imprimeix(interficie.ERROR_MENU);
                    interficie.saltDeLinea();
            }

            //Fi del do-while
        } while (opcio_correcta = false);
    }

    //------------------------------
    // CONSULTA D'ANUNCIS PUBLICATS
    //------------------------------
    //
    //******************************
    //PART 1 - QUESTIONARI PER OBTENIR EL FILTRE
    //******************************
    void questionariAnuncisPublicats() {

        interficie.salt();

        //Mostrem títol
        interficie.mostrarTitol("Consulta d'anuncis publicats");

        //Menu
        int opcio_menu = interficie.mostrarMenu("Consulta d'anuncis", menu.consultaAnuncisPublicats);

        //Segons la opcio de menu del titol
        int opcio_consulta = consulta.questonari(opcio_menu, filtre, immoble);

        boolean opcio_correcta = false;

        //Comença el menu
        do {

            switch (opcio_consulta) {

                //No s'ha trobat cap resultat
                case 0:

                    zeroResultats();

                //S'ha trobat 1 o més resultats
                case 1:
                    opcio_correcta = true;
                    detallAnuncisPublicats();
                    break;

                //Tornar al menú principal
                case 2:
                    opcio_correcta = true;
                    menuPrincipal();
                    break;

                default:

                    interficie.imprimeix(interficie.ERROR_MENU);
                    interficie.saltDeLinea();

            }

        } while (opcio_correcta = false);
    }

    //PART 1-B - TORNAR A PROVAR SI OBTENIM 0 RESULTATS
    void zeroResultats() {

        int opcio_zero_resultats = interficie.mostrarMenu("", menu.ConsultaZeroResultats);

        boolean opcio_correcta = false;

        //Comença el menu
        do {

            switch (opcio_zero_resultats) {

                //Tornar a provar
                case 1:
                    opcio_correcta = true;
                    questionariAnuncisPublicats();
                    break;

                //Tornar al menú principal
                case 2:

                    opcio_correcta = true;
                    menuPrincipal();
                    break;

                default:

                    interficie.imprimeix(interficie.ERROR_MENU);
                    interficie.saltDeLinea();
            }

        } while (opcio_correcta = false);
    }

    //******************************
    //PART 2 - MOSTRAR DETALL D'UN ANUNCI
    //******************************
    void detallAnuncisPublicats() {

        //Un cop mostrats els resultats consultem si es vol observar detall
        int opcio_mostrar_detall = interficie.mostrarMenu("", menu.ConsultaDetall);

        //Tornem a posar en false opcio_correcta per un altre bucle
        boolean opcio_correcta = false;

        //Comença el menu
        do {

            switch (opcio_mostrar_detall) {

                //Mostrar detall
                case 1:

                    opcio_correcta = true;

                    //Demana l'id del filtre a consultar
                    interficie.imprimeix("Introdueix l'ID de l'immoble");
                    int id = demana.entrarUnEnter();

                    //Mostra el detall sefons el filtre entrat
                    interficie.detallAnunci(immoble, id);
                    interficie.saltDeLinea();
                    consultaAltresAnuncisPublicats();

                    break;

                //Tornar al menu prinicipal
                case 2:

                    opcio_correcta = true;
                    menuPrincipal();
                    break;

                default:

                    interficie.imprimeix(interficie.ERROR_MENU);
                    interficie.saltDeLinea();
            }

        } while (opcio_correcta = false);
    }

    //******************************
    //PART 3 - TORNAR A COMENÇAR SI ES VOLEN CONSULTAR ALTRES IMMOBLES
    //******************************
    void consultaAltresAnuncisPublicats() {

        //Un cop mostrat el detall preguntem si vol mostrar un altra detall o tornem al menu principal
        int opcio_mostrar_altre = interficie.mostrarMenu("", menu.ConsultaAltre);

        //Tornem a posar en false opcio_correcta per un altre bucle
        boolean opcio_correcta = false;

        do {

            switch (opcio_mostrar_altre) {

                //VEURE DETALL DE L'ANUNCI TROBAT
                case 1:

                    opcio_correcta = true;
                    detallAnuncisPublicats();
                    break;

                //TORNAR A PROVAR
                case 2:

                    opcio_correcta = true;
                    questionariAnuncisPublicats();
                    break;

                //ANAR AL MENU PRINCIPAL
                case 3:

                    opcio_correcta = true;
                    menuPrincipal();
                    break;

                default:

                    interficie.imprimeix(interficie.ERROR_MENU);
                    interficie.saltDeLinea();
            }

        } while (opcio_correcta = false);
    }
}
