package aplicacioimmobiliaria;

import java.util.Scanner;

public class EntradaDades {

    Menu menu = new Menu();
    //NOTA: Instanciar BibliotecaInterficieUsuari provoca error Stack Overflow
    //Per això no instancio la classe Interficie des d'aquí

    String ERROR_DADES = "Error. Siusplau, torna a introduïr les dades.";

    Scanner scanner = new Scanner(System.in);

    //****************************
    //Demana un enter
    int entrarUnEnter() {
        int enter = 0;
        boolean correcte = false;
        do {
            correcte = scanner.hasNextInt();
            if (correcte) {
                enter = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
                System.out.println(ERROR_DADES);
            }
        } while (!correcte);
        return enter;
    }

    //****************************
    //Demana un tipus de vivenda
    int entrarTipus() {
        int enter = 0;
        boolean correcte = false;
        do {
            correcte = scanner.hasNextInt();
            enter = scanner.nextInt();
            if (correcte && enter < 7) {
                scanner.nextLine();
            } else {
                correcte = false;
                scanner.nextLine();
                System.out.println(ERROR_DADES);
            }
        } while (!correcte);
        return enter;
    }

    //****************************
    //Demana número double
    double entrarNumeroDouble() {
        double numero = 0;

        //Si el valor entrat és númeric
        if (scanner.hasNextDouble()) {
            //El guardem a la variable número
            numero = scanner.nextDouble();
            //Si no ho és
        } else {
            //Tornem a cridar la funció i torna a començar fins que s'entri un número
            entrarNumeroDouble();
        }
        //Si em entrat un número ja podem sortir de la funció i retornem el valor
        return numero;
    }

    //****************************
    //Demana cadena i la retorna
    String entrarText(String cadena) {
        //Inicialitzem
        String text = "";
        //Mostrem missatge enunciat
        System.out.println(cadena);
        //Recoillim text
        if (scanner.hasNextLine()) {
            text = scanner.nextLine();
        }
        return text;
    }

    //****************************
    //Demana valor a l'usuari i si no el rep torna valor per defecte
    String entrarTextAmbValorDef(String cadena, String defaultValue) {
        String text = "";
        //Mostrem missatge
        System.out.println(cadena);
        //Recollim text i si no ens el donen els hi tornem la defaultValue
        if (scanner.hasNextLine()) {
            text = scanner.nextLine();
            if ("".equals(text)) {
                text = defaultValue;
            }
        }
        return text;
    }

    //****************************
    //Demana una cadena i si no la rep torna a demanar
    String entrarTextNoBuit() {
        String text = "";
        //Mostrem missatge
        //Si ens trobem la mateixa cadena buida (text="")...
        if (scanner.hasNextLine()) {
            text = scanner.nextLine();
            if ("".equals(text)) {//...demana més. Torna a executar la funció
                System.out.println(ERROR_DADES);
                entrarTextNoBuit();
            }
        }
        return text;
    }

    //****************************
    //Prem una tecla per continuar
    void PremUnaTecla() {
        //Extret de http://stackoverflow.com/questions/19870467/how-do-i-get-press-any-key-to-continue-to-work-in-my-java-code
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

    void premUnaTeclaMissatge(String cadena) {
        System.out.println(cadena);
        PremUnaTecla();
    }

    //****************************
    //DEMANA SI O NO
    boolean confirmar(String missatge, int defaultValue) {
        
        //Ha de tornar Si = 1, o No 0 False
        //Pot venir amb variable predefinida: defaultValue 1 || 0
        //O no

        //Per defecte diem que retorn es no
        boolean retorn = false;
        //Variable de control de si ver per defecte
        boolean bucle = true;
        //Resposta de l'usuari
        String resposta;

        if (defaultValue == 0) {
            retorn = false;
        } else if (defaultValue == 1) {
            retorn = true;
        }

        //Mentre aquest while tingui la variable de control bucle en true anirà repetint
        while (bucle = true) {
            System.out.println(missatge);
            //Si ens han donat variable per defecte, sigui no(0) o si(1)...
            if (defaultValue == 0 || defaultValue == 1) {
                //Preguntem
                if (scanner.hasNextLine()) {
                    resposta = scanner.nextLine();
                    //Si ens diuen que si...
                    if ("s".equals(resposta) || "S".equals(resposta)) {
                        retorn = true;//El valor a retornar serà SI (true)
                        bucle = false;//Acabem el bucle
                        break;
                        //Si ens diuen que no...
                    } else if ("n".equals(resposta) || "N".equals(resposta)) {
                        retorn = false;//El valor a retornar serà NO (false)
                        bucle = false;//Acabem el bucle
                        break;
                        //Si no entren s, S, n o N... 
                    } else {
                        bucle = false;//Acabem el bucle
                        break;//Sortim amb el valor per defecte, doncs no li donem nou valor a retorn
                    }
                }
            } else//No ve valor predeterminat
            //Preguntem
             if (scanner.hasNextLine()) {
                    resposta = scanner.nextLine();
                    //Si ens diuen que si
                    if ("s".equals(resposta) || "S".equals(resposta)) {
                        retorn = true;//Retornem el si
                        bucle = false;//Sortim del bucle
                        break;
                        //Si ens diuen que no
                    } else if ("n".equals(resposta) || "N".equals(resposta)) {
                        retorn = false;//Retornem el no
                        bucle = false;//Sortim del bucle
                        break;
                        //Si no entren s, S, n o N... S'haura de repetir
                    } else {
                        System.out.println("Torna a provar");
                        bucle = true;//Mantenim el bucle
                    }
                }
        }//fi while
        return retorn;
    }//fi confirmar
}
