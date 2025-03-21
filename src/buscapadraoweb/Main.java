/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package buscapadraoweb;

import buscaweb.CapturaRecursosWeb;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author Santiago
 */
public class Main {

    // busca char em vetor e retorna indice
    public static int get_char_ref (char[] vet, char ref ){
        for (int i=0; i<vet.length; i++ ){
            if (vet[i] == ref){
                return i;
            }
        }
        return -1;
    }

    // busca string em vetor e retorna indice
    public static int get_string_ref (String[] vet, String ref ){
        for (int i=0; i<vet.length; i++ ){
            if (vet[i].equals(ref)){
                return i;
            }
        }
        return -1;
    }

    

    //retorna o próximo estado, dado o estado atual e o símbolo lido
    public static int proximo_estado(char[] alfabeto, int[][] matriz,int estado_atual,char simbolo){
        int simbol_indice = get_char_ref(alfabeto, simbolo);
        if (simbol_indice != -1){
            return matriz[estado_atual][simbol_indice];
        }else{
            return -1;
        }
    }

    /*
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // instancia e usa objeto que captura código-fonte de páginas Web
        CapturaRecursosWeb crw = new CapturaRecursosWeb();
        crw.getListaRecursos().add("https://www.uuidgenerator.net/");
        ArrayList<String> listaCodigos = crw.carregarRecursos();

        String codigoHTML = listaCodigos.get(0);

        // mapa do alfabeto
        char[] alfabeto = {
                '0','1','2','3',
                '4','5','6','7',
                '8','9','a','b',
                'c','d','e','f',
                '-'
        };


        // mapa de estados
        String[] estados = new String[37];
        for (int i = 0; i < estados.length; i++) {
            estados[i] = "q" + i;
        }

        String startState = "q0";
        String[] endState = new String[1];
        endState[0] = "q36";

        int q0 = get_string_ref(estados, "q0");
        int q8 = get_string_ref(estados, "q8");
        int q9 = get_string_ref(estados, "q9");
        int q13 = get_string_ref(estados, "q13");
        int q14 = get_string_ref(estados, "q14");
        int q15 = get_string_ref(estados, "q15");
        int q17 = get_string_ref(estados, "q17");
        int q18 = get_string_ref(estados, "q18");
        int q19 = get_string_ref(estados, "q19");
        int q20 = get_string_ref(estados, "q20");
        int q23 = get_string_ref(estados, "q23");
        int q24 = get_string_ref(estados, "q24");
        int hyfenIndex = get_char_ref(alfabeto, '-');
        int versionIndex = get_char_ref(alfabeto, '4');

        int[][] matriz = new int[37][alfabeto.length];

        for (int i = 0; i < 37; i++) {
            for (int j = 0; j < alfabeto.length; j++) {
                matriz[i][j] = -1;
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < alfabeto.length; j++) {
                char c = alfabeto[j];
                if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f')) {
                    matriz[i][j] = i + 1;
                }
            }
        }

        matriz[q8][hyfenIndex] = q9;

        for (int i = q9; i <= get_string_ref(estados, "q12"); i++) {
            for (int j = 0; j < alfabeto.length; j++) {
                char c = alfabeto[j];
                if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f')) {
                    matriz[i][j] = i + 1;
                }
            }
            matriz[i][hyfenIndex] = -1;
        }

        for (int j = 0; j < alfabeto.length; j++) {
            matriz[q13][j] = -1;
        }
        matriz[q13][hyfenIndex] = q14;

        for (int j = 0; j < alfabeto.length; j++) {
            matriz[q14][j] = -1;
        }
        matriz[q14][versionIndex] = q15;

        for (int i = q15; i <= q17; i++) {
            for (int j = 0; j < alfabeto.length; j++) {
                char c = alfabeto[j];
                if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f')) {
                    matriz[i][j] = i + 1;
                }
            }
            matriz[i][hyfenIndex] = -1;
        }

        for (int j = 0; j < alfabeto.length; j++) {
            matriz[q18][j] = -1;
        }
        matriz[q18][hyfenIndex] = q19;

        for (int j = 0; j < alfabeto.length; j++) {
            matriz[q19][j] = -1;
        }
        matriz[q19][get_char_ref(alfabeto, '8')] = q20;
        matriz[q19][get_char_ref(alfabeto, '9')] = q20;
        matriz[q19][get_char_ref(alfabeto, 'a')] = q20;
        matriz[q19][get_char_ref(alfabeto, 'b')] = q20;

        for (int i = q20; i <= get_string_ref(estados, "q22"); i++) {
            for (int j = 0; j < alfabeto.length; j++) {
                char c = alfabeto[j];
                if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f')) {
                    matriz[i][j] = i + 1;
                }
            }
            matriz[i][hyfenIndex] = -1;
        }

        for (int j = 0; j < alfabeto.length; j++) {
            matriz[q23][j] = -1;
        }
        matriz[q23][hyfenIndex] = q24;

        for (int i = q24; i <= get_string_ref(estados, "q35"); i++) {
            for (int j = 0; j < alfabeto.length; j++) {
                char c = alfabeto[j];
                if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f')) {
                    matriz[i][j] = i + 1;
                }
            }
            matriz[i][hyfenIndex] = -1;
        }

        for (int j = 0; j < alfabeto.length; j++) {
            matriz[get_string_ref(estados, "q36")][j] = -1;
        }

        int estado = get_string_ref(estados, startState);
        int estado_anterior = -1;
        ArrayList<String> palavras_reconhecidas = new ArrayList<>();
        String palavra = "";

        for (int i = 0; i < codigoHTML.length(); i++) {
            estado_anterior = estado;
            estado = proximo_estado(alfabeto, matriz, estado, codigoHTML.charAt(i));

            if (estado == -1) {
                estado = get_string_ref(estados, startState);
                if (get_string_ref(endState, estados[estado_anterior]) != -1) {
                    if (!palavra.equals("")) {
                        palavras_reconhecidas.add(palavra);
                    }
                    i--;
                }
                palavra = "";
            } else {
                palavra += codigoHTML.charAt(i);
            }
        }

        for (String p : palavras_reconhecidas) {
            System.out.println(p);
        }

        // System.out.println(Arrays.toString(estados));

    }
}