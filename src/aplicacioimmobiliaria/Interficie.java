package aplicacioimmobiliaria;

import java.text.DecimalFormat;
import java.util.Scanner;

//En aquesta classe posarem totes les funcions que tingui a veure amb treure dades per pantalla
public class Interficie {

    Scanner scanner = new Scanner(System.in);
    EntradaDades demana = new EntradaDades();

    DecimalFormat formateja = new DecimalFormat("###,###");

    int LLARGADA_BARRA_TITOL = 60;
    String CARACTER_TITOL = "-";
    String CARACTER_PRESENTACIO = "'";
    String ERROR_MENU = "Error. Siusplau, introdueix una de les opcions del menu.";
    String ERROR_DADES = "Error. Siusplau, torna a introduïr les dades.";
    String RESPOSTA = "RESPOSTA: ";

    //************************
    // PRESENTACIÓ
    //************************
    //Presentem
    String presentacio(String titol) {
        //Definim la llargada de la barra pels titols
        String barra = imprimeixBarra(CARACTER_PRESENTACIO, LLARGADA_BARRA_TITOL);
        //Definim el espai en blanc per centrar titols
        String centrar = centrarRespecteBarra(LLARGADA_BARRA_TITOL, titol);

        imprimeix(barra);
        imprimeix(barra);
        imprimeixSenseSalt(centrar);
        imprimeix(titol);
        imprimeix(barra);
        imprimeix(barra);
        imprimeix("");
        return titol;
    }

    //************************
    // LOGIN
    //************************
    //Procés per loguejar-se
    void login(Usuari usuari, Login login) {
        boolean log = false;
        int voltes = 0;

        //Bucle d'autentificació
        do {
            //Només mostrem el títol si és la primera vegada
            if (voltes == 0) {
                //Titol
                mostrarTitol("Autentificació d'usuaris");
                saltDeLinea();
                imprimeix("Siusplau introdueix les teves dades");
                saltDeLinea();
            }

            //Demanem DNI i ja guardem el seu valor per saber quin usuari està actuant
            imprimeix("Número de DNI: ");
            String dni = demana.entrarTextNoBuit();
            saltDeLinea();

            //Demana contrassenya
            imprimeix("Contrassenya: ");
            String contrassenya = demana.entrarTextNoBuit();
            saltDeLinea();

            //Executem el login
            log = login.login(usuari, dni, contrassenya);

            if (log != true) {
                imprimeix(ERROR_MENU);
            }
            saltDeLinea();
            voltes++;

        } while (log != true);
    }

    //************************
    // MENUS
    //************************
    //Mostra menu i retorna la opcio triada
    int mostrarMenu(String titol, String[] menu) {

        int opcio;
        Interficie executa = new Interficie();

        //Mostrem titol. Si està buit, que no faci salt de linea
        if (!"".equals(titol)) {
            executa.mostrarTitol(titol);
        }

        //Mostrem menu en si
        executa.mostrarMenu(menu);

        //Donem a seleccionar
        imprimeix(RESPOSTA);
        opcio = executa.seleccionarOpcio(menu);

        return opcio;
    }

    //Mostrar Menu - Demanarà un array amb les opcions del menú
    void mostrarMenu(String[] menu) {
        for (int i = 0; i < menu.length; i++) {
            imprimeix((i + 1) + "- " + menu[i] + " ");
        }
        imprimeix("");
    }

    //Métode per mostrar els títols
    String mostrarTitol(String titol) {

        //Definim la llargada de la barra pels titols
        String barraStandard = imprimeixBarra(CARACTER_TITOL, LLARGADA_BARRA_TITOL);

        //Definim el espai en blanc per centrar titols
        String centrar = centrarRespecteBarra(LLARGADA_BARRA_TITOL, titol);

        imprimeix(barraStandard);
        imprimeixSenseSalt(centrar);
        imprimeix(titol);
        imprimeix(barraStandard);
        return titol;
    }

    //Funció per centrar títol respecte una barra de títol
    String centrarRespecteBarra(int llargadaBarra, String titol) {
        String espai = "";
        String espaiEnBlanc = " ";

        //Guardem a una variable la llargada del titol
        int llargadaFrase = titol.length();

        //Calculem la llargada que tindra la cadena d'espai
        int longitudEspai = (llargadaBarra - llargadaFrase) / 2;

        //Creem la cadena
        for (int i = 0; i < longitudEspai; i++) {
            espai = espai + espaiEnBlanc;
        }
        return espai;
    }

    //Selecciona opció de menú
    int seleccionarOpcio(String[] menu) {
        int opcio = 0;

        //Booleana pel bucle
        boolean bucle = true;

        //Primer calculem numero de opcions
        int numeroOpcions = menu.length;

        //Demanem opcions del menu
        while (bucle) {
            if (scanner.hasNextInt()) {
                opcio = scanner.nextInt();

                if (opcio >= 1 && opcio <= numeroOpcions) {
                    bucle = false;
                } else {
                    imprimeix(ERROR_DADES);
                }
            } else {
                imprimeix(ERROR_DADES);
                scanner.nextLine();
            }
        }
        return opcio;
    }

    //**************************
    // IMPRIMEIX LLISTA IMMOBLES
    //**************************
    // Funció per imprimir l'array provisional amb totes les dades en String
    void llistaAnuncisFiltre(Filtre filtre) {

        int mida_barra_separadora = 78;

        //Títols
        imprimeixSenseSalt("| ");
        imprimeixSenseSalt(Utilitats.midaExacta("ID", 5));
        imprimeixSenseSalt("| ");
        imprimeixSenseSalt(Utilitats.midaExacta("TIPUS", 10));
        imprimeixSenseSalt(" | ");
        imprimeixSenseSalt(Utilitats.midaExacta("DIRECCIÓ", 10));
        imprimeixSenseSalt(" | ");
        imprimeixSenseSalt(Utilitats.midaExacta("LOCALITAT", 15));
        imprimeixSenseSalt(" | ");
        imprimeixSenseSalt(Utilitats.midaExacta("SUPERFICIE", 10));
        imprimeixSenseSalt(" | ");
        imprimeixSenseSalt(Utilitats.midaExacta("PREU", 10));
        imprimeix(" |");

        imprimeix(imprimeixBarra("=", mida_barra_separadora));

        for (int i = 0; i < filtre.arrayFiltreInt.length; i++) {

            imprimeixSenseSalt("| ");

            //Id -- El passem a String amb valueOf doncs la funció midaExacta() només agafa Strings
            //Li sumem 1 a la id perque el 0 queda "raro"
            imprimeixSenseSalt(Utilitats.midaExacta(String.valueOf(filtre.posicio[i]), 5));
            imprimeixSenseSalt("| ");

            //Tipus
            imprimeixSenseSalt(Utilitats.midaExacta(filtre.arrayFiltreString[i][0], 10));
            imprimeixSenseSalt(" | ");

            //Direcció
            imprimeixSenseSalt(Utilitats.midaExacta(filtre.arrayFiltreString[i][2], 10));
            imprimeixSenseSalt(" | ");

            //Localitat
            imprimeixSenseSalt(Utilitats.midaExacta(filtre.arrayFiltreString[i][3], 15));
            imprimeixSenseSalt(" | ");

            //Superficie
            imprimeixSenseSalt(Utilitats.midaExacta((filtre.arrayFiltreInt[i][1] + " m2"), 10));
            imprimeixSenseSalt(" | ");

            //Preu
            imprimeixSenseSalt(Utilitats.midaExacta((formateja.format(filtre.arrayFiltreInt[i][2]) + " €"), 10));
            imprimeix(" |");
        }

        imprimeix(imprimeixBarra("=", mida_barra_separadora));
    }

    //**************************
    // IMPRIMEIX DETALL IMMOBLE
    //**************************
    void detallAnunci(Immoble immoble, int posicio) {

        int llargada_anunci = 72;
        int llargada_amb_text = llargada_anunci - 4;

        imprimeix(imprimeixBarra("-", llargada_anunci));

        imprimeixSenseSalt("| ");
        imprimeixSenseSalt(Utilitats.midaExacta(immoble.arrayImmoblesString[posicio][2], 40));
        imprimeixSenseSalt("| ");
        imprimeixSenseSalt(Utilitats.midaExacta(immoble.arrayImmoblesString[posicio][3], 26));
        imprimeix(" |");

        imprimeix(imprimeixBarra("-", llargada_anunci));

        imprimeixSenseSalt("| ");
        imprimeixSenseSalt(Utilitats.midaExacta(immoble.arrayImmoblesString[posicio][0] + " de " + immoble.arrayImmoblesInt[posicio][1] + " m2.", 68));
        imprimeix(" |");

        imprimeixSenseSalt("| ");
        imprimeixSenseSalt(imprimeixBarra(" ", llargada_amb_text));
        imprimeix(" |");

        imprimeixSenseSalt("| ");
        imprimeixSenseSalt(Utilitats.midaExacta(immoble.arrayImmoblesString[posicio][1], llargada_amb_text));
        imprimeix(" |");

        imprimeixSenseSalt("| ");
        imprimeixSenseSalt(imprimeixBarra(" ", llargada_amb_text));
        imprimeix(" |");

        imprimeixSenseSalt("| ");
        imprimeixSenseSalt(imprimeixBarra(" ", 40));
        imprimeixSenseSalt("| ");
        imprimeixSenseSalt(Utilitats.midaExacta("PREU: " + formateja.format(immoble.arrayImmoblesInt[posicio][2]) + " €", 26));
        imprimeix(" |");

        imprimeix(imprimeixBarra("-", llargada_anunci));
    }

    //************************
    // FILTRE
    //************************
    //Funció que mostra els valors que em entrat al filtre
    String stringFiltre(Filtre filtre) {
        String cadena = "Vivenda tipus: " + filtre.filtreTipus + ", a " + filtre.filtrePoblacio + ", de " + filtre.filtreSuperficieMin + " a " + filtre.filtreSuperficieMax + " metres quadrats i preu entre " + filtre.filtrePreuMin + " i " + filtre.filtrePreuMax + " euros";
        return cadena;
    }

    //Si l'id és al filtre torna true. Si no, false
    boolean preguntaIdFiltre(Filtre filtre, int id) {
        boolean control = false;

        for (int i = 0; i < filtre.posicio.length; i++) {
            if (filtre.posicio[i] == id) {
                control = true;
            }
        }
        return control;
    }

    //===========================
    // FUNCIONS PER IMPRIMIR
    //===========================
    String imprimeix(String cadena) {
        System.out.println(cadena);
        return cadena;
    }

    int imprimeix(int enter) {
        System.out.println(enter);
        return enter;
    }

    double imprimeix(double doble) {
        System.out.println(doble);
        return doble;
    }

    boolean imprimeix(boolean booleana) {
        System.out.println(booleana);
        return booleana;
    }

    String imprimeixSenseSalt(String cadena) {
        System.out.print(cadena);
        return cadena;
    }

    int imprimeixSenseSalt(int cadena) {
        System.out.print(cadena);
        return cadena;
    }

    boolean imprimeixSenseSalt(boolean cadena) {
        System.out.print(cadena);
        return cadena;
    }

    //Imprimeix Array String
    void imprimirArray(String[] array) {
        for (int i = 0; i < array.length; i++) {
            imprimeixSenseSalt(array[i] + ", ");
        }
    }

    //Imprimeix Array Int
    void imprimirArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            imprimeixSenseSalt(array[i] + ", ");
        }
    }

    //Imprimeix Array Multiple String
    void imprimirArrayMultiple(String[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                imprimeixSenseSalt("|" + array[i][j]);
            }
            imprimeix("|");
        }
    }

    //Imprimeix Array Multiple Int
    void imprimirArrayMultiple(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                imprimeixSenseSalt("|" + array[i][j]);
            }
            imprimeix("|");
        }
    }

    //Imprimeix separador. Demana caracter a repetir i número de vegades
    String imprimeixBarra(String caracter, int numVegades) {
        String barra = "";
        for (int i = 0; i < numVegades; i++) {
            barra = barra + caracter;
        }
        return barra;
    }

    //Imprimeix en vermell
    void error (String txt) {
        System.out.println("\033[31m" + txt);
    }

    //Separem una linea
    void saltDeLinea() {
        imprimeix("");
    }

    //Fem salt neteja-pantalles
    void salt() {
        imprimeix(imprimeixBarra("\n", 100));
    }
}
