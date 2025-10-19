package com.example.comando.lsgrep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Proceso {
    final static String[] COMANDOS = {"ls", "grep"};
    final static String[] FILTRO = {"a"};

    public static void main(String[] args) throws IOException {
        try {
            List<String> listado = extraer(COMANDOS[0]);
            List<String> listadoFiltrado = filtrar(listado, COMANDOS[1],FILTRO[0]);
            for(int i = 0; i<listadoFiltrado.size(); i++){
                System.out.println(listadoFiltrado.get(i));
            }

        } catch (InterruptedException IE) {
            System.out.println("Error: " + IE.getMessage());
        }
    }

    public static List<String> extraer(String comando) throws IOException, InterruptedException {
        List<String> lista = new ArrayList<>();

        Process procesoLS = Runtime.getRuntime().exec(comando);

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(procesoLS.getInputStream()))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                lista.add(linea);
            }
        }

        procesoLS.waitFor();
        return lista;
    }

    public static List<String> filtrar(List<String> texto, String cmd, String filtro) throws IOException, InterruptedException {
        List<String> resultado = new ArrayList<>();
        String[] comando = {cmd, filtro};
        
        Process p = Runtime.getRuntime().exec(comando);

        try (OutputStream os = p.getOutputStream()) {
            for (String linea : texto) {
                os.write((linea + "\n").getBytes());
            }
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(p.getInputStream()))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                resultado.add(linea);
            }
        }

        p.waitFor();
        return resultado;
    }
}
