package chesu.modelo;

import chesu.util.Sonido;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Cesar Acosta
 */
public class Tablero2 {
    private static final int TAMAÑO_CASILLA = 75; 
    private static final int MARGENX = 120;
    private static final int MARGENY = 50;
    private EscribirArchivo escribirArchivo;
    private Casilla[][] casillas;
    private Piezas[][] posiciones;
    private Map<String, Point> coordenadas;
    private Casilla casillaAnterior;
    private Casilla casillaActual;
    private Casilla casillaDestino;
    private boolean esPrimerClick;
    private boolean esTurnoBlancas;
    private boolean esCaptura;
    private String mensajeError;
    private String[] jugadores;
    private String ganador;
    private boolean esJaqueMate;
    private Piezas esPromocion;
    private String tipoNuevaPieza;
    private Movimiento movimiento;
    private Sonido sonido;
    private boolean esEnroqueCorto;
    private boolean esEnroqueLargo;
    
    
    public Tablero2(EscribirArchivo escribirArchivo){
        this.escribirArchivo = escribirArchivo;
        this.casillas = new Casilla[8][8];
        this.posiciones = new Piezas[8][8];
        this.coordenadas = new HashMap<>();
        this.esPrimerClick = true;
        this.esTurnoBlancas = true;
        this.esCaptura = false;
        this.jugadores = new String[2];  
        this.esJaqueMate = false;
        this.esPromocion = null;
        this.tipoNuevaPieza = "";
        this.sonido = new Sonido();
        this.esEnroqueCorto = false;
        this.esEnroqueLargo = false;
        
        inicializarTablero();
        inicializarMapaPosiciones();
    }
    
    public Casilla[][] crearCasillas(){
        for(int fila = 0; fila < 8; fila++){
            for(int columna = 0; columna < 8; columna++){
                int x = MARGENX + columna * TAMAÑO_CASILLA;
                int y = MARGENY + fila * TAMAÑO_CASILLA;
                String ruta;
                boolean ocupado;
                
                if ((fila + columna) % 2 == 0){
                    ruta = "/recursos/imagenes/piezas/casillaBlanca.png";
                } else {
                    ruta = "/recursos/imagenes/piezas/casillaMarron.png";
                }
                
                if(fila == 0 || fila == 1 || fila == 6 || fila == 7){
                    ocupado = true;
                }else{
                    ocupado = false;
                }
                
                casillas[fila][columna] = new Casilla(x, y, fila, columna, ruta, ocupado);
            }
        }
        
        return casillas;
    }
    
    private void inicializarTablero() {
        posiciones[0][0] = new Torre('R', "negra", "/recursos/imagenes/piezas/torreNegro.png", 0, 0, this);
        posiciones[0][1] = new Caballo('N', "negra", "/recursos/imagenes/piezas/caballoNegro.png", 0, 1);
        posiciones[0][2] = new Alfil('B', "negra", "/recursos/imagenes/piezas/alfilNegro.png", 0, 2);
        posiciones[0][3] = new Reina('Q', "negra", "/recursos/imagenes/piezas/reinaNegro.png", 0, 3);
        posiciones[0][4] = new Rey('K', "negra", "/recursos/imagenes/piezas/reyNegro.png", 0, 4);
        posiciones[0][5] = new Alfil('B', "negra", "/recursos/imagenes/piezas/alfilNegro.png", 0, 5);
        posiciones[0][6] = new Caballo('N', "negra", "/recursos/imagenes/piezas/caballoNegro.png", 0, 6);
        posiciones[0][7] = new Torre('R', "negra", "/recursos/imagenes/piezas/torreNegro.png", 0, 7, this);

        for (int i = 0; i < 8; i++) {
            posiciones[1][i] = new Peon('P', "negra", "/recursos/imagenes/piezas/peonNegro.png", 1, i);
        }

        posiciones[7][0] = new Torre('R', "blanca", "/recursos/imagenes/piezas/torreBlanco.png", 7, 0, this);
        posiciones[7][1] = new Caballo('N', "blanca", "/recursos/imagenes/piezas/caballoBlanco.png", 7, 1);
        posiciones[7][2] = new Alfil('B', "blanca", "/recursos/imagenes/piezas/alfilBlanco.png", 7, 2);
        posiciones[7][3] = new Reina('Q', "blanca", "/recursos/imagenes/piezas/reinaBlanco.png", 7, 3);
        posiciones[7][4] = new Rey('K', "blanca", "/recursos/imagenes/piezas/reyBlanco.png", 7, 4);
        posiciones[7][5] = new Alfil('B', "blanca", "/recursos/imagenes/piezas/alfilBlanco.png", 7, 5);
        posiciones[7][6] = new Caballo('N', "blanca", "/recursos/imagenes/piezas/caballoBlanco.png", 7, 6);
        posiciones[7][7] = new Torre('R', "blanca", "/recursos/imagenes/piezas/torreBlanco.png", 7, 7, this);

        for (int i = 0; i < 8; i++) {
            posiciones[6][i] = new Peon('P', "blanca", "/recursos/imagenes/piezas/peonBlanco.png", 6, i);
        }
    }
    
    /**
     * Calcula las posiciones en pixeles donde se ubicaran las piezas y las guarda en un hashMap.
     */
    private void inicializarMapaPosiciones() {
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                
                int x = MARGENX + columna * TAMAÑO_CASILLA + 5;
                int y = MARGENY + fila * TAMAÑO_CASILLA + 10;

                
                String id = fila +""+ columna;
                coordenadas.put(id, new Point(x, y));
            }
        }
    }
    
    public Piezas[][] posiblesCasillasDestino(int fila, int columna){
        Piezas pieza = posiciones[fila][columna];
        
        System.out.println(pieza.getTipo());
        if(moverPieza(3, 0, pieza)){
            return posiciones;
        }
        return null;
    }
    
    public boolean casillaSeleccionada(int x, int y) {
        if (x < 120 || x > 750 || y < 50 || y > 650) {
            return false;
        }

        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
            Casilla casilla = casillas[fila][columna];

                if(x > casilla.getPosX() && x < casilla.getPosX() + 75 &&
                    y > casilla.getPosY() && y < casilla.getPosY() + 75) {

                    if(esPrimerClick) {
                        if (posiciones[fila][columna] != null && 
                            ((esTurnoBlancas && posiciones[fila][columna].getColor().equals("blanca")) ||
                             (!esTurnoBlancas && posiciones[fila][columna].getColor().equals("negra")))) {

                            this.casillaActual = casilla;
                            casilla.setImagen("/recursos/imagenes/piezas/casillaSeleccionada.png");
                            esPrimerClick = false;
                        } else {
                            // Si en el primer click no se selecciona una pieza sale no realiza ninguna accion.
                            return false;
                        }
                    } 
                    // Segundo clic, intentar mover la pieza
                    else {
                        this.casillaDestino = casilla;

                        // Verificar si la casilla destino es válida
                        if (casillaActual.getOcupado()) {
                            Piezas pieza = posiciones[casillaActual.getFila()][casillaActual.getColumna()];
                            if(moverPieza(casillaDestino.getFila(), casillaDestino.getColumna(), pieza)){
                                sonido.reproducir();
                                int filaDestino = casillaDestino.getFila();
                                int columnaDestino = casillaDestino.getColumna();
                                int[] destino = {filaDestino, columnaDestino};
                                int[] origen = {casillaActual.getFila(), casillaActual.getColumna()};
                            
                                esTurnoBlancas = !esTurnoBlancas;
                                this.movimiento = new Movimiento(pieza.getTipo(), origen, destino, esCaptura, esJaqueMate, 
                                        verificarGanador(), verificarPromocion(pieza), esEnroqueCorto, esEnroqueLargo);
                                esEnroqueCorto = false; 
                                esEnroqueLargo = false;
                                mensajeError = null;
                                
                                // Restaurar la imagen de la casilla anterior
                                if (casillaAnterior != null && casillaAnterior != casilla) {
                                    if ((casillaAnterior.getFila() + casillaAnterior.getColumna()) % 2 == 0) {
                                        casillaAnterior.setImagen("/recursos/imagenes/piezas/casillaBlanca.png");
                                    } else {
                                        casillaAnterior.setImagen("/recursos/imagenes/piezas/casillaMarron.png");
                                    }
                                }

                                // Reiniciar el estado para el próximo turno
                                esPrimerClick = true;
                                return true;
                            }
                        }

                        // Restaurar la imagen de la casilla anterior
                        if (casillaAnterior != null && casillaAnterior != casilla) {
                            if ((casillaAnterior.getFila() + casillaAnterior.getColumna()) % 2 == 0) {
                                casillaAnterior.setImagen("/recursos/imagenes/piezas/casillaBlanca.png");
                            } else {
                                casillaAnterior.setImagen("/recursos/imagenes/piezas/casillaMarron.png");
                            }
                        }

                        // Reiniciar el estado para el próximo turno
                        esPrimerClick = true;
                    }

                    this.casillaAnterior = casilla;
                    return false;
                }
            }
        }
        return false;
    }

    
    
    private boolean moverPieza(int filaDestino, int columnaDestino, Piezas pieza) {
        esCaptura = false;
        
        if(verificarEnroqueCorto(filaDestino, columnaDestino, pieza)){
            esEnroqueCorto = true;
            return true;
        }else if(verificarEnroqueLargo(filaDestino, columnaDestino, pieza)){
            esEnroqueLargo = true;
            return true;
        }
        
        
        // Verificar si el movimiento es válido para la pieza
        if (pieza.esMovimientoValido(filaDestino, columnaDestino, this)) {
            int filaActual = pieza.getFila();
            int columnaActual = pieza.getColumna();

            // Detectar si hay una pieza en la posición de destino.
            if (posiciones[filaDestino][columnaDestino] != null) {
                Piezas piezaDestino = posiciones[filaDestino][columnaDestino];

                // Comprobar si la pieza en destino es del color opuesto.
                if (!pieza.getColor().equals(piezaDestino.getColor())) {
                    esCaptura = true;
                    // Capturar la pieza.
                    posiciones[filaDestino][columnaDestino] = pieza; 
                    pieza.setPosicion(filaDestino, columnaDestino); 
                    posiciones[filaActual][columnaActual] = null;
                    casillas[filaActual][columnaActual].setOcupado(false);
                    casillas[filaDestino][columnaDestino].setOcupado(true);
                    verificarFinJuego();
                    return true;
                } else {
                    // Mismo color, no se puede capturar.
                    mensajeError = "Invalid Move, you cannot capture your own piece.";
                    return false;
                }
            } else {
                // Movimiento normal sin captura.
                posiciones[filaDestino][columnaDestino] = pieza;
                pieza.setPosicion(filaDestino, columnaDestino);
                posiciones[filaActual][columnaActual] = null;
                casillas[filaActual][columnaActual].setOcupado(false);
                casillas[filaDestino][columnaDestino].setOcupado(true);
                verificarFinJuego();
                return true;
            }
        }
        // Movimiento inválido.
        mensajeError = "Invalid move, try again.";
        return false;
    }
    
    private boolean verificarEnroqueCorto(int filaDestino, int columnaDestion, Piezas pieza){
        if(pieza.getTipo() != 'K'){
            return false;
        }
        
        if(pieza.getColor().equals("blanca")){
            Piezas torre = posiciones[7][7];
            if(pieza.getFila() == 7 && pieza.getColumna() == 4 && torre.getTipo() == 'R' && 
                    torre.getFila() == 7 && torre.getColumna() == 7 && posiciones[7][5] == null && 
                    posiciones[7][6] == null){
                        posiciones[7][6] = pieza;
                        posiciones[7][5] = torre;
                        posiciones[7][4] = null;
                        posiciones[7][7] = null;
                        return true;
            }
        }
        
        if(pieza.getColor().equals("negra")){
            Piezas torre = posiciones[0][7];
            if(pieza.getFila() == 0 && pieza.getColumna() == 4 && torre.getTipo() == 'R' && 
                    torre.getFila() == 0 && torre.getColumna() == 7 && posiciones[0][5] == null && 
                    posiciones[0][6] == null){
                        posiciones[0][6] = pieza;
                        posiciones[0][5] = torre;
                        posiciones[0][4] = null;
                        posiciones[0][7] = null;
                        return true;
            }
        }
        
        return false;
    }
    
    private boolean verificarEnroqueLargo(int filaDestino, int columnaDestino, Piezas pieza){
        if(pieza.getTipo() != 'K'){
            return false;
        }
        
        if(pieza.getColor().equals("blanca")){
            Piezas torre = posiciones[7][0];
            if(pieza.getFila() == 7 && pieza.getColumna() == 4 && torre.getTipo() == 'R' && 
                    torre.getFila() == 7 && torre.getColumna() == 0 && posiciones[7][1] == null && 
                    posiciones[7][2] == null && posiciones[7][3] == null){
                        posiciones[7][2] = pieza;
                        posiciones[7][3] = torre;
                        posiciones[7][4] = null;
                        posiciones[7][0] = null;
                        return true;
            }
        }
        
        if(pieza.getColor().equals("negra")){
            Piezas torre = posiciones[0][0];
            if(pieza.getFila() == 0 && pieza.getColumna() == 4 && torre.getTipo() == 'R' && 
                    torre.getFila() == 0 && torre.getColumna() == 0 && posiciones[0][1] == null && 
                    posiciones[0][2] == null && posiciones[0][3] == null){
                        posiciones[0][2] = pieza;
                        posiciones[0][3] = torre;
                        posiciones[0][4] = null;
                        posiciones[0][0] = null;
                        return true;
            }
        }
        
        return false;
    }
    
    public boolean verificarFinJuego(){
        boolean reyBlanco = false;
        boolean reyNegro = false;
        
        for(int fila = 0; fila < 8; fila++){
            for(int columna = 0; columna < 8; columna++){
                Piezas pieza = posiciones[fila][columna];
                if(pieza != null && pieza.getTipo() == 'K' ){
                    if(pieza.getColor().equals("blanca")){
                        reyBlanco = true;
                    }else if(pieza.getColor().equals("negra")){
                        reyNegro = true;
                    }
                }
                
            }
        }
        if(reyBlanco && !reyNegro){
            this.ganador = jugadores[0];
            this.esJaqueMate = true;
            return true;
        }else if(!reyBlanco && reyNegro){
            this.ganador = jugadores[1];
            this.esJaqueMate = true;
            return true;
        }else{
            return false;
        }
    }
    
    private String verificarGanador(){
        if(ganador != null && ganador.equals(jugadores[0])){
            return "1-0";
        }else if(ganador != null && ganador.equals(jugadores[1])){
            return "0-1";
        }else{
            return "";
        }
    }
    
    private String verificarPromocion(Piezas pieza){
        if(pieza.getTipo() != 'P'){
            return "";
        }
        
        int j = pieza.getColor().equals("blanca") ? 0 : 7;
        
        for(int i = 0; i < 8; i++){
            if(posiciones[j][i] == pieza){
                esPromocion = pieza;
                return "=" + tipoNuevaPieza;
            }
        }
        return "";
    }
    
    public void escribirMovimiento(){
        if(!tipoNuevaPieza.equals("")){
            movimiento.setPromocion("=" + tipoNuevaPieza);
        }
        
        escribirArchivo.escribirMovimiento(movimiento);
        tipoNuevaPieza = "";
    }
    
    public void cambiarPieza(char tipoPieza){
        if(esPromocion != null){
            int fila = esPromocion.getFila();
            int columna = esPromocion.getColumna();
            String color = esPromocion.getColor();

            String rutaPiezaBlanca = switch(tipoPieza){
                case 'Q' -> "/recursos/imagenes/piezas/reinaBlanco.png";
                case 'R' -> "/recursos/imagenes/piezas/torreBlanco.png";
                case 'N' -> "/recursos/imagenes/piezas/caballoBlanco.png";
                case 'B' -> "/recursos/imagenes/piezas/alfilBlanco.png";
                default -> "";
            };

            String rutaPiezaNegra = switch(tipoPieza){
                case 'Q' -> "/recursos/imagenes/piezas/reinaNegro.png";
                case 'R' -> "/recursos/imagenes/piezas/torreNegro.png";
                case 'N' -> "/recursos/imagenes/piezas/caballoNegro.png";
                case 'B' -> "/recursos/imagenes/piezas/alfilNegro.png";
                default -> "";
            };

            String ruta = color.equals("blanca") ? rutaPiezaBlanca : rutaPiezaNegra;

            Piezas nuevaPieza = switch(tipoPieza){
                case 'Q' -> new Reina(tipoPieza, color, ruta, fila, columna);
                case 'R' -> new Torre(tipoPieza, color, ruta, fila, columna, this);
                case 'N' -> new Caballo(tipoPieza, color, ruta, fila, columna);
                case 'B' -> new Alfil(tipoPieza, color, ruta, fila, columna);
                default -> null;
            };

            posiciones[fila][columna] = nuevaPieza;
            tipoNuevaPieza = Character.toString(tipoPieza);
            esPromocion = null;
        }
        
    }
    
    public void setNombreJugadores(String[] jugadores){
        this.jugadores = jugadores;
    }
    
    public String[] getNombreJugadores(){
        return jugadores;
    }
    
    
    
    /**
     * verifica si una posicion en la matriz <code>posiciones</code> esta ocupada.
     * @param fila la fila de la posicion a buscar.
     * @param columna la columna de la posicion a buscar.
     * @return true si esta ocupada y false si no esta ocupada.
     */
    public boolean estaOcupada(int fila, int columna){
        return posiciones[fila][columna] != null;
    } 
    
    public boolean getEsTurnoBlancas(){
        return esTurnoBlancas;
    }
    
    public String getGanador(){
        return ganador;
    }
    
    public String getMensajeError(){
        return mensajeError;
    }
    
    public void restaurarMensajeError(){
        this.mensajeError = null;
    }
    
    public Casilla[][] getCasillas(){
        return casillas;
    }
    
    public Piezas[][] getPosiciones(){
        return posiciones;
    }
    
    public Map<String, Point> getCoordenadas(){
        return coordenadas;
    }
    
    public Piezas getEsPromocion(){
        return esPromocion;
    }
    
    public void setTipoNuevaPieza(char tipoPieza){
        this.tipoNuevaPieza = Character.toString(tipoPieza);
    }
}
