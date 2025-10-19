package com.example.comando.lsgrep;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ProcesoTest {

    
    @Test
    public void testExtraer() throws IOException, InterruptedException {
        List<String> resultado = Proceso.extraer("ls");

        assertNotNull(resultado, "La lista devuelta no debe ser nula");
        assertTrue(resultado.size() > 0, "Debe devolver al menos un archivo o carpeta");
    }

    
    @Test
    public void testFiltrar() throws IOException, InterruptedException {
        
        List<String> entrada = Arrays.asList("archivo.txt", "imagen.png", "documento.pdf", "video.mp4");

        List<String> salida = Proceso.filtrar(entrada, "grep", "a");

        assertNotNull(salida, "La salida no debe ser nula");
        assertFalse(salida.isEmpty(), "Debe devolver al menos una coincidencia");
        assertTrue(salida.stream().allMatch(l -> l.contains("a")),
                "Todas las líneas devueltas deben contener la letra 'a'");
    }

    
    @Test
    public void testFiltrarSinCoincidencias() throws IOException, InterruptedException {
        List<String> entrada = Arrays.asList("uno", "dos", "tres");

        List<String> salida = Proceso.filtrar(entrada, "grep", "xyz123");

        assertNotNull(salida, "La salida no debe ser nula");
        assertTrue(salida.isEmpty(), "No debe devolver resultados si no hay coincidencias");
    }

    
    
    }

