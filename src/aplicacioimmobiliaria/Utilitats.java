package aplicacioimmobiliaria;

public class Utilitats {

    Interficie interficie = new Interficie(); //Podria fer un extends, però esperarem a fer-ho al curs...

    //*********************
    // MANIPULACIÓ CADENES
    //*********************
    //Retallar un String fins la mida que volem
    public static String retallarFinsMida(String entraString, int entraMida) {
        entraString = entraString.substring(0, entraMida-4) + " ...";
        return entraString;
    }

    //Farcir un String amb espais buits fins la mida que volem
    public static String farcirFinsMida(String entraString, int entraMida) {

        int midaEntraString = entraString.length();
        String espaiEnBlanc = " ";
        int caractersQueFalten = entraMida - entraString.length();

        if (midaEntraString < entraMida) {
            //Solució trobada a http://stackoverflow.com/questions/1235179/simple-way-to-repeat-a-string-in-java
            String farciment = new String(new char[caractersQueFalten]).replace("\0", espaiEnBlanc);
            entraString = entraString + farciment;
        }
        return entraString;
    }

    //Deixar un String fins la mida exacta (fem servir les anteriors dues funcions)
    public static String midaExacta(String entraString, int entraMida) {
        int midaEntraString = entraString.length();
        if (midaEntraString > entraMida) {
            entraString = retallarFinsMida(entraString, entraMida);
        } else if (midaEntraString < entraMida) {
            entraString = farcirFinsMida(entraString, entraMida);
        }
        return entraString;
    }
}
