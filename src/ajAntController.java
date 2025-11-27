import java.io.*;
import ajAntDron2k25.ajAntCiberDron.ajAntCiberDron;
import ajAntDron2k25.ajAntCiberDron.ajHormiga;
import ajAntDron2k25.ajAntCiberDron.ajHRastreadora;

public class ajAntController {
  private final String CEDULA = "1751455096"; 
    private final String NOMBRE_ARCHIVO = "ApellidoNombre.csv";
    
    private ajAntCiberDron dron;

    public void iniciarSistema() {
        // PASO 1: Generar el archivo (Mantiene todas las trampas para la tabla)
        generarArchivoSimulado();
        
        // PASO 2: Mostrar la Información y la Tabla visualmente
        mostrarInfoYTabla();
        
        // PASO 3: Configurar Dron
        configurarDronCasoContrario();
        
        // PASO 4: Iniciar la secuencia de ataque (FILTRADA)
        System.out.println("\n[+] MODO RASTREO ACTIVADO. INICIANDO LECTURA...\n");
        procesarAtaque();
    }

    private void generarArchivoSimulado() {
        try (FileWriter writer = new FileWriter(NOMBRE_ARCHIVO)) {
            writer.write("Geoposicion;Lunes;Martes;Miercoles;Jueves;Viernes;Tipo Arsenal\n");
            
            // DATOS DE LA TABLA (Se mantienen igual para que la visualización sea correcta)
            writer.write("Coord-01;01-02;;;;;a\n");
            writer.write("Coord-07;;;03-06;;;abc\n"); 
            writer.write("Coord-05;;;;;05-10;abcdt\n");
            writer.write("Coord-01;01-02;;;;;aa\n");
            writer.write("Coord-04;;;;04-08;;abcd\n"); 
            writer.write("Coord-05;;;;;05-10;abcdtt\n");
            writer.write("Coord-05;;;;;05-10;abcdttt\n");
            writer.write("Coord-00;;;;;;aaa\n");

            // TUS OBJETIVOS (Estos son los que explotarán)
            writer.write("Coord-09;01-02;;;;;a\n"); 
            writer.write("Coord-06;;;;04-08;;abcdt\n");
            
        } catch (IOException e) {
            System.err.println("Error creando archivo: " + e.getMessage());
        }
    }

    private void mostrarInfoYTabla() {
        System.out.println("   INFORMACION");
        System.out.println("Usuario: Josue Astudillo"); 
        System.out.println("Cedula: " + CEDULA);
        System.out.println("=========================================");
        System.out.println();

        System.out.println("[+] COORDENADAS UCRANIANAS:");
        
        String formato = "%-11s; %-7s; %-7s; %-9s; %-7s; %-7s; %s%n";
        System.out.println("Geoposicion ; Lunes   ; Martes  ; Miercoles ; Jueves  ; Viernes ; Tipo Arsenal");

        try (BufferedReader br = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            br.readLine(); // Saltar header

            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(";", -1); 
                if (p.length >= 7) {
                    System.out.printf(formato, p[0], p[1], p[2], p[3], p[4], p[5], p[6]);
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
        System.out.println("=========================================");
    }

    private void configurarDronCasoContrario() {
        System.out.println(">> Detectando condicion especial en cedula...");
        ajHormiga pilotoSeleccionado = new ajHRastreadora();
        this.dron = new ajAntCiberDron(pilotoSeleccionado);
        System.out.println(">> Hormiga " + pilotoSeleccionado.getClass().getSimpleName() + " ha comido Herbivoro y VIVE.");
    }

    // --- AQUÍ ESTÁ LA CORRECCIÓN CLAVE ---
    private void procesarAtaque() {
        String[] framesAnimacion = {"-", "+"}; 

        System.out.println("REPORTE DE DETONACIONES (RASTREADORA):");
        System.out.printf("%-12s | %-15s | %s\n", "GEOPOSICION", "ARSENAL", "ACCION");
        System.out.println("------------------------------------------------");

        try (BufferedReader br = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            br.readLine(); 

            while ((linea = br.readLine()) != null) {
                animarLoading(framesAnimacion); // Mantiene la animación

                String[] partes = linea.split(";");
                if (partes.length > 0) {
                    String coord = partes[0];      // Ej: Coord-06
                    String arsenal = partes[partes.length - 1]; // Ej: abcdt

                    // 1. Preguntar al autómata si el arsenal es explosivo
                    boolean esArsenalValido = dron.ajbuscar(arsenal);
                    
                    // 2. FILTRO DE SEGURIDAD: Solo tus coordenadas (09 y 06) están autorizadas
                    boolean esCoordenadaObjetivo = coord.equals("Coord-09") || coord.equals("Coord-06");

                    // 3. Solo explota si CUMPLE AMBAS condiciones
                    if (esArsenalValido && esCoordenadaObjetivo) {
                         System.out.printf("%-12s | %-15s | TRUE (BOOM)\n", coord, arsenal);
                    }
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void animarLoading(String[] frames) {
       try {
            System.out.print("Cargando: "); 
            for (int i = 0; i <= 100; i += 20) {
                String frame = frames[(i / 20) % frames.length];
                System.out.print(frame + " " + i + "% \r");
                Thread.sleep(50); 
            }
            System.out.print("                                      \r");
        } catch (InterruptedException e) {}
    }
}