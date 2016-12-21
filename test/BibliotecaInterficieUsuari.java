package aplicacioimmobiliaria;

import java.util.Scanner;

//En aquesta classe posarem totes les funcions que tingui a veure amb treure dades per pantalla
public class Interficie {

    Scanner scanner = new Scanner(System.in);
    EntradaDades demana = new EntradaDades();

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
    // MENUS
    //************************
    //Mostra menu i retorna la opcio triada
    int mostrarMenu(String titol, String[] menu) {
        int opcio;
        Interficie executa = new Interficie();
        //Mostrem titol
        executa.mostrarTitol(titol);
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

    //************************
    // FILTRE
    //************************
    Filtre crearFiltre(Menu menu) {

        Filtre filtre = new Filtre();

        imprimeix(menu.menuCreaFiltre[0]);
        mostrarMenu(menu.menuTipus);
        filtre.filtreTipus = demana.entrarUnEnter();
        imprimeix(menu.menuCreaFiltre[1]);
        filtre.filtrePoblacio = demana.entrarTextNoBuit();
        imprimeix(menu.menuCreaFiltre[2]);
        filtre.filtreSuperficieMin = demana.entrarNumeroDouble();
        imprimeix(menu.menuCreaFiltre[3]);
        filtre.filtreSuperficieMax = demana.entrarNumeroDouble();
        imprimeix(menu.menuCreaFiltre[4]);
        filtre.filtrePreuMin = demana.entrarNumeroDouble();
        imprimeix(menu.menuCreaFiltre[5]);
        filtre.filtrePreuMax = demana.entrarNumeroDouble();

        return filtre;
    }

    //Funció que converteix Filtre en String (per imprimir)
    String stringFiltre(Filtre filtre
    ) {
        String cadena = "Vivenda tipus: " + filtre.filtreTipus + ", a " + filtre.filtrePoblacio + ", de " + filtre.filtreSuperficieMin + " a " + filtre.filtreSuperficieMax + " metres quadrats i preu entre " + filtre.filtrePreuMin + " i " + filtre.filtrePreuMax + " euros";
        return cadena;
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

    String imprimeixSenseSalt(String cadena) {
        System.out.print(cadena);
        return cadena;
    }

    boolean imprimeixSenseSalt(boolean cadena) {
        System.out.print(cadena);
        return cadena;
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

    //Fem salt neteja-pantalles
    void salt() {
        imprimeix(imprimeixBarra("\n", 20));
    }
}
