package es.albarregas.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

public class ListaPaisesBean implements Serializable {

    private List<String> lista;
    private List<String> listaTraducida;

    public enum PaisesTraducidos {
        JAPÓN, HUNGRÍA, BULGARIA, AZERBAIYÁN, PORTUGAL, TURQUÍA, VIETNAM, ALBANIA, UZBEKISTÁN
    }

    // Constructor
    public ListaPaisesBean() {

        // Inicializa el atributo lista como una lista ordenada y única
        Set<String> listaOrdenada = new TreeSet<>();
        // Paso a la lista traducida los valores de los enums para poder poner los nombres de los paises con traducción en negrita
        listaTraducida = obtenerPaisesTraducidos();

        // Obtengo los locales disponibles
        Locale[] locales = Locale.getAvailableLocales();

        for (Locale locale : locales) {
            String pais = locale.getDisplayCountry();
            String idioma = locale.getLanguage();
            // Compruebo que no sean campos vacíos
            if (!pais.isEmpty()) {
                listaOrdenada.add(pais+"("+idioma+")");              
            }
        }

        // Paso la lista ya ordenada al atributo lista
        lista = new ArrayList<>(listaOrdenada);       
    }

    public List<String> getLista() {
        return lista;
    }

    public void setLista(List<String> lista) {
        this.lista = lista;
    }

    public List<String> getListaTraducida() {
        return listaTraducida;
    }

    public void setListaTraducida(List<String> listaTraducida) {
        this.listaTraducida = listaTraducida;
    }
   
    // Método para obtener el nombre del pais de la lista que se pasa por el parametro
    public String obtenerNombrePais(String nombre) {
        for (String pais : lista) {
            if (pais.equalsIgnoreCase(nombre)) {
                return pais;
            }
        }
        return null;
    }
    // Método estático que retorna los valores del Enum PaisesTraducidos
    public static List<String> obtenerPaisesTraducidos() {
        List<String> listaTraducidos = new ArrayList<>();

        for (PaisesTraducidos pais : PaisesTraducidos.values()) {
            listaTraducidos.add(pais.name());
        }
        return listaTraducidos;
    }

}
