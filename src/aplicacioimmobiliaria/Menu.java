package aplicacioimmobiliaria;

//En aquesta classe agruparem tots els menus
public class Menu {

    String[] menuPrincipal = {"Gestionar anuncis propis", "Consultar anuncis publicats"};
    String[] menuTipus = {"Local", "Oficina", "Pis", "Casa", "Pàrquing", "Traster"};

    //Gestió d'immobles
    String[] menuGestioAnuncisPropis = {"Consultar anuncis publicats", "Consultar detall anunci", "Afegir nou anunci", "Eliminar anunci", "Tornar al menu principal"};
    String[] detallOTornar = {"Consultar detall de l'immoble", "Tornar al menu principal"};
    String[] afegirImmoble = {"Tipus d'immoble (escollir opció): ","Població: ", "Adreça: ", "Descripció: ", "Superfície: ", "Preu: "};

    //Consulta d'immobles
    String[] consultaAnuncisPublicats = {"Començar qüestionari", "Tornar al menú principal"};
    String[] menuCreaFiltre = {"Tipus d'immoble (escollir opció): ", "Població: ", "Superficie minima: ", "Superficie màxima: ", "Preu mínim: ", "Preu màxim: "};
    String[] ConsultaDetall = {"Consultar detall de l'immoble", "Tornar al menu principal"};
    String[] ConsultaZeroResultats = {"Tornar a provar", "Tornar al menu principal"};
    String[] ConsultaAltre = {"Consultar un altre immoble", "Tornar a fer el qëstionari", "Tornar al menu principal"};
}
