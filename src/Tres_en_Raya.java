import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Tres_en_Raya extends JFrame {
    private JButton[][] buttons = new JButton[3][3]; // Botones para el tablero
    private boolean playerTurn = true;              // Turno del jugador
    private Random random = new Random();           // Generador de movimientos aleatorios para la máquina

    public Tres_en_Raya() {
        // Configuración de la ventana principal
        setTitle("Tres en Raya");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3)); // GridLayout 3x3 para el tablero

        // Inicialización del tablero de botones
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 40));
                buttons[i][j].setFocusPainted(false);
                add(buttons[i][j]);
                int row = i, col = j; // Captura las posiciones del botón
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (playerTurn && buttons[row][col].getText().equals("")) {
                            buttons[row][col].setText("X");
                            playerTurn = false;
                            if (!checkWinner("X")) {
                                computerMove();
                            }
                        }
                    }
                });
            }
        }
    }

    // Movimiento de la máquina
    private void computerMove() {
        boolean moved = false;
        while (!moved) {
            int row = random.nextInt(3);
            int col = random.nextInt(3);
            if (buttons[row][col].getText().equals("")) {
                buttons[row][col].setText("O");
                moved = true;
            }
        }
        playerTurn = true;
        checkWinner("O");
    }

    // Verifica si hay un ganador
    private boolean checkWinner(String player) {
        // Verificar filas, columnas y diagonales
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(player) &&
                buttons[i][1].getText().equals(player) &&
                buttons[i][2].getText().equals(player)) {
                showWinner(player);
                return true;
            }
            if (buttons[0][i].getText().equals(player) &&
                buttons[1][i].getText().equals(player) &&
                buttons[2][i].getText().equals(player)) {
                showWinner(player);
                return true;
            }
        }
        if (buttons[0][0].getText().equals(player) &&
            buttons[1][1].getText().equals(player) &&
            buttons[2][2].getText().equals(player)) {
            showWinner(player);
            return true;
        }
        if (buttons[0][2].getText().equals(player) &&
            buttons[1][1].getText().equals(player) &&
            buttons[2][0].getText().equals(player)) {
            showWinner(player);
            return true;
        }
        // Verificar empate
        if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "¡Empate!");
            resetBoard();
            return true;
        }
        return false;
    }

    // Verifica si el tablero está lleno
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    // Muestra al ganador y reinicia el tablero
    private void showWinner(String player) {
        JOptionPane.showMessageDialog(this, "¡El jugador " + player + " gana!");
        resetBoard();
    }

    // Reinicia el tablero
    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        playerTurn = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Tres_en_Raya game = new Tres_en_Raya();
            game.setVisible(true);
        });
    }
}

