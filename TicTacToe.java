import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TicTacToe implements ActionListener {
    Random random = new Random();   // Choose a random value

    JFrame frame = new JFrame("TicTacToe");   // Create a frame

    JPanel titlePanel = new JPanel();   // Add panel hold the title
    JPanel buttonPanel = new JPanel();   // Add panel hold the buttons

    JLabel label = new JLabel();   // Add label display the text

    JButton[] buttons = new JButton[9];   // Creates a 9 size array of buttons

    int buttonCount = 0;
    boolean player1_turn;

    TicTacToe() {   // constructor of TicTacToe class
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // Add design to frame
        frame.setSize(500, 500);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        label.setBackground(new Color(25, 25, 25));   // Add design to label
        label.setForeground(new Color(25, 225, 0));
        label.setFont(new Font("Ink Free", Font.BOLD, 75));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setText("Tic-Tac-Toe");
        label.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());   // Add design to titlePanel
        titlePanel.setBounds(0, 0, 800, 100);

        buttonPanel.setLayout(new GridLayout(3, 3));   // Add design to buttonPanel
        buttonPanel.setBackground(new Color(150, 150, 150));

        for(int i=0; i<9; i++) {   // Add design to buttons using a loop
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        titlePanel.add(label);   // Add label to titlePanel

        frame.add(titlePanel, BorderLayout.NORTH);   // Add all objects to frame
        frame.add(buttonPanel);

        firstTurn();   // Call firstTurn() method
    }

    @Override
    public void actionPerformed(ActionEvent e) {   // Button will action performed when clicked
        for(int i=0; i<9; i++) {
            if(e.getSource() == buttons[i]) {   // Match the current button clicked
                if(player1_turn) {
                    if(buttons[i].getText() == "") {   // Add design to button if current button match from 1-9
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText("X");
                        player1_turn = false;
                        label.setText("O turn");
                        buttonCount++;   // Increment buttonCount
                        check();
                    }
                }
                else {
                    if(buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(0, 0, 255));
                        buttons[i].setText("O");
                        player1_turn = true;
                        label.setText("X turn");
                        buttonCount++;
                        check();
                    }
                }
            }
        }
    }

    public void firstTurn() {
        try {
            Thread.sleep(2000);   // Before choosing a random turn program sleep for 2ms
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
        
        if(random.nextInt(2) == 0) {   // X turn if random value == 0
            player1_turn = true;
            label.setText("X turn");
        }
        else {   // O turn if random value == 1
            player1_turn = false;
            label.setText("O turn");
        }
    }

    public void gameOver() {
        buttonCount = 0;   // ButtonCount again initialize to 0 after gameOver
        Object[] option = {"Restart", "Exit"};   // Choose an option from a pop-up window
        int n = JOptionPane.showOptionDialog(frame, "Game Over\n", "GameOver", JOptionPane.YES_NO_CANCEL_OPTION,   // Create a pop-up window
                JOptionPane.QUESTION_MESSAGE, null, option, option[0]);

        if(n == 0) {   // Restart game
            frame.dispose();
            new TicTacToe();
        }
        else {   // Exit game
            frame.dispose();
        }
    }

    public void check() {   // Check for winning conditions or if any player has currently won 
        if((buttons[0].getText() == "X") &&   // X Wins
        (buttons[1].getText() == "X") &&
        (buttons[2].getText() == "X")) {
            xWins(0, 1, 2);
        }

        if((buttons[3].getText() == "X") &&
        (buttons[4].getText() == "X") &&
        (buttons[5].getText() == "X")) {
            xWins(3, 4, 5);
        }

        if((buttons[6].getText() == "X") &&
        (buttons[7].getText() == "X") &&
        (buttons[8].getText() == "X")) {
            xWins(6, 7, 8);
        }

        if((buttons[0].getText() == "X") &&
        (buttons[3].getText() == "X") &&
        (buttons[6].getText() == "X")) {
            xWins(0, 3, 6);
        }

        if((buttons[1].getText() == "X") &&
        (buttons[4].getText() == "X") &&
        (buttons[7].getText() == "X")) {
            xWins(1, 4, 7);
        }

        if((buttons[2].getText() == "X") &&
        (buttons[5].getText() == "X") &&
        (buttons[8].getText() == "X")) {
            xWins(2, 5, 8);
        }

        if((buttons[0].getText() == "X") &&
        (buttons[4].getText() == "X") &&
        (buttons[8].getText() == "X")) {
            xWins(0, 4, 8);
        }

        if((buttons[2].getText() == "X") &&
        (buttons[4].getText() == "X") &&
        (buttons[6].getText() == "X")) {
            xWins(2, 4, 6 );
        }

        if((buttons[0].getText() == "O") &&   // O Wins
        (buttons[1].getText() == "O") &&
        (buttons[2].getText() == "O")) {
            oWins(0, 1, 2);
        }

        if((buttons[3].getText() == "O") &&
        (buttons[4].getText() == "O") &&
        (buttons[5].getText() == "O")) {
            oWins(3, 4, 5);
        }

        if((buttons[6].getText() == "O") &&
        (buttons[7].getText() == "O") &&
        (buttons[8].getText() == "O")) {
            oWins(6, 7, 8);
        }

        if((buttons[0].getText() == "O") &&
        (buttons[3].getText() == "O") &&
        (buttons[6].getText() == "O")) {
            oWins(0, 3, 6);
        }

        if((buttons[1].getText() == "O") &&
        (buttons[4].getText() == "O") &&
        (buttons[7].getText() == "O")) {
            oWins(1, 4, 7);
        }

        if((buttons[2].getText() == "O") &&
        (buttons[5].getText() == "O") &&
        (buttons[8].getText() == "O")) {
            oWins(2, 5, 8);
        }

        if((buttons[0].getText() == "O") &&
        (buttons[4].getText() == "O") &&
        (buttons[8].getText() == "O")) {
            oWins(0, 4, 8);
        }

        if((buttons[2].getText() == "O") &&
        (buttons[4].getText() == "O") &&
        (buttons[6].getText() == "O")) {
            oWins(2, 4, 6 );
        }

        if(buttonCount == 9) {   // All buttons visited & not any 3 buttons matched then draw 
            xoDraw();   // Call xoDraw() method
        }
    }

    public void xWins(int a, int b, int c) {   // method for X Wins
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for(int i=0; i<9; i++) {   // Disable all buttons if X Wins so Noplayer play further 
            buttons[i].setEnabled(false);
        }
        label.setText("X Wins");
        gameOver();   //Call gameOver() method
    }


    public void oWins(int a, int b, int c) {   // method for O Wins
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for(int i=0; i<9; i++) {   // Disable all buttons if O Wins so Noplayer play further 
            buttons[i].setEnabled(false);
        }
        label.setText("O Wins");
        gameOver();   //Call gameOver() method
    }

    public void xoDraw() {   // method for XO Draw
        for(int i=0; i<9; i++) {   // Disable all buttons if XO Draw so Noone play further 
            buttons[i].setEnabled(false);
        }
        label.setText("XO Draw");
        gameOver();   //Call gameOver() method
    }

    public static void main(String[] args) {
        new TicTacToe();   // Instance of TicTacToe
    }
}