/*
 * Program: Sudoku
 * Name: Angela Mao
 * Description: This is my GUI program. I coded 3 sudoku puzzles for this game
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Sudoku implements ActionListener {

    private JFrame frame;
    private JPanel p_card;
    private JPanel main, instructions, play, easy, medium, hard;
    // This is for the progress bar that is shown on the main screen
    private JProgressBar progressBar;
    private JLabel highScore;
    // This will be updated with the current score every time the player completes the puzzle
    private int score = 0;
    // This will be updated and will increase up to 3.This is used to ensure that the player can only have up to 3 chances.
    private int easyWrong = 0;
    private int mediumWrong = 0;
    private int hardWrong = 0;
    // This is used to update the progress bar. The variable num will be updated every time we finish a puzzle.
    private int num = 0;
    private JLabel easyChance1, easyChance2, easyChance3, mediumChance1, mediumChance2, mediumChance3, hardChance1, hardChance2, hardChance3;
    // This is declaring the array for the board of the easy puzzle.
    private JTextField[][] easyPuzzle = new JTextField[9][9];
    // This is the array that has the starting values of the easy puzzle. A 0 is an empty space.
    private int[][] boardEasy = {{0, 0, 0, 0, 0, 7, 1, 9, 0},
            {9, 2, 0, 6, 0, 4, 7, 8, 0},
            {0, 4, 0, 0, 0, 0, 0, 5, 0},
            {4, 0, 7, 0, 6, 9, 3, 0, 0},
            {0, 0, 9, 0, 1, 0, 8, 0, 0},
            {0, 0, 5, 7, 8, 0, 9, 0, 6},
            {0, 9, 0, 0, 0, 0, 0, 6, 0},
            {0, 7, 2, 1, 0, 6, 0, 3, 9},
            {0, 5, 6, 3, 0, 0, 0, 0, 0}};
    // This is declaring the array for the board of the medium puzzle.
    private JTextField[][] mediumPuzzle = new JTextField[9][9];
    // This is the array that has the starting values of the medium puzzle. A 0 is an empty space.
    private int[][] boardMedium = {{0, 4, 0, 0, 0, 0, 7, 0, 0},
            {0, 0, 0, 9, 0, 0, 0, 3, 0},
            {3, 1, 2, 7, 4, 5, 0, 0, 0},
            {0, 0, 0, 0, 0, 7, 3, 0, 6},
            {7, 6, 3, 0, 0, 0, 2, 9, 8},
            {1, 0, 9, 6, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 8, 4, 6, 2, 3},
            {0, 3, 0, 0, 0, 6, 0, 0, 0},
            {0, 0, 8, 0, 0, 0, 0, 4, 0}};
    // This is declaring the array for the board of the hard puzzle.
    private JTextField[][] hardPuzzle = new JTextField[9][9];
    // This is the array that has the starting values of the hard puzzle. A 0 is an empty space.
    private int[][] boardHard = {{0, 0, 0, 0, 9, 3, 0, 4, 0},
            {7, 0, 0, 0, 0, 0, 8, 0, 0},
            {0, 0, 0, 0, 5, 4, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 9, 5, 0},
            {4, 1, 0, 0, 0, 0, 0, 2, 3},
            {0, 5, 9, 6, 0, 0, 0, 0, 0},
            {5, 0, 0, 4, 3, 0, 0, 0, 0},
            {0, 0, 6, 0, 0, 0, 0, 0, 5},
            {0, 8, 0, 2, 6, 0, 0, 0, 0}};
    CardLayout cdLayout = new CardLayout();

    public static void main(String[] args) {
        new Sudoku();
    }

    public Sudoku() {
        frame = new JFrame();
        frame.setSize(800, 600);
        frame.setBackground(Color.black);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Sudoku");
        p_card = new JPanel();
        p_card.setSize(800, 600);
        p_card.setLayout(cdLayout);
        main();
        instructions();
        play();
        easy();
        medium();
        hard();
        frame.add(p_card);
        frame.setVisible(true);
    }

    // This is a method for the main screen. This will be the first screen players see when they run this program. It has a progress bar and displays the high score.
    public void main() {
        main = new JPanel();
        main.setBackground(new Color(112, 168, 178));

        JLabel title = new JLabel("Sudoku Tournament");
        title.setFont(new Font("Algerian", Font.PLAIN, 45));

    	/* Hester, Blake. ��Follow-up to 'Pokemon Go': 'Harry Potter' Augmented Reality Game.�� Rolling Stone, Rolling Stone, 8 Nov. 2017, 
      www.rollingstone.com/glixel/news/the-follow-up-to-pokemon-go-is-a-harry-potter-ar-game-w511334*/
        JLabel picture = new JLabel(createImageIcon("harryPotter.jpg"));
        picture.setPreferredSize(new Dimension(500, 330));

        // This is here to format the buttons and the progress bar and the high score so that they are in a grid format; 2 buttons on top of the 2 other buttons.
        JPanel buttons = new JPanel(new GridLayout(2, 2, 10, 10));
        buttons.setBackground(new Color(112, 168, 178));

        JButton play = new JButton("Play");
        play.addActionListener(this);
        play.setActionCommand("play");
        play.setBackground(new Color(233, 187, 88));
        play.setFont(new Font("Calibri", Font.BOLD, 20));

        JButton instructions = new JButton("Instructions");
        instructions.addActionListener(this);
        instructions.setActionCommand("instructions");
        instructions.setBackground(new Color(233, 187, 88));
        instructions.setFont(new Font("Calibri", Font.BOLD, 20));

        // This will display the high score on the main screen
        highScore = new JLabel("High Score: 000");
        highScore.setFont(new Font("Calibri", Font.BOLD, 20));

    	/* This will be the progress bar that shows the player how far along they are in the game 
    and how close they are to completing all levels of the sudoku puzzles */
        progressBar = new JProgressBar();
        progressBar.setValue(0);

        buttons.add(play);
        buttons.add(instructions);
        buttons.add(highScore);
        buttons.add(progressBar);
        main.add(title);
        main.add(picture);
        main.add(buttons);
        p_card.add("main", main);
    }

    // This is here to format the instructions screen.
    public void instructions() {
        instructions = new JPanel();
        instructions.setBackground(new Color(112, 168, 178));

        JLabel title = new JLabel("Instructions");
        title.setFont(new Font("Algerian", Font.PLAIN, 45));

        // This is here to format the pictures and the instructions so that the instructions are in between the 2 pictures
        JPanel middle = new JPanel();
        middle.setLayout(new FlowLayout(FlowLayout.LEFT));
        middle.setBackground(new Color(112, 168, 178));

        //"Triwizard Tournament (Scopatore).�� Harry Potter Fanon Wiki, harrypotterfanon.wikia.com/wiki/Triwizard_Tournament_(Scopatore)
        JLabel picture1 = new JLabel(createImageIcon("triwizardCup.jpg"));
        picture1.setPreferredSize(new Dimension(120, 280));

        // This JTextArea displays all the instructions
        JTextArea rules = new JTextArea(7, 20);
        rules.setBackground(new Color(112, 168, 178));
        rules.setText(" Fill the grid with your keyboard so that every row, column and 3x3 box\n");
        rules.append(" contains the digits 1 to 9, without repeating them.Navigate the grid with\n");
        rules.append(" your mouse; clear entries with Delete or Backspace. You will not be able to\n");
        rules.append(" edit the squares with values already given to you. When the entire puzzle is\n");
        rules.append(" completed, press the enter button if you want to check if the puzzle is\n");
        rules.append(" completed correctly. Beware! You only have 3 chances to check and complete\n");
        rules.append(" your puzzle correctly. You can also press the restart button to replay\n");
        rules.append(" your puzzle.");
        rules.setFont(new Font("Calibri", Font.BOLD, 16));

        //Same picture as JLabel picture1 as I wanted the same picture to be on either side of the instructions
        //"Triwizard Tournament (Scopatore).�� Harry Potter Fanon Wiki, harrypotterfanon.wikia.com/wiki/Triwizard_Tournament_(Scopatore).
        JLabel picture2 = new JLabel(createImageIcon("triwizardCup.jpg"));
        picture2.setPreferredSize(new Dimension(120, 280));

        // This is to format the buttons so that they are beside each other near the bottom of the screen
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttons.setBackground(new Color(112, 168, 178));

        JButton main = new JButton("Main");
        main.addActionListener(this);
        main.setActionCommand("main");
        main.setBackground(new Color(233, 187, 88));
        main.setFont(new Font("Calibri", Font.BOLD, 20));

        JButton play = new JButton("Play");
        play.addActionListener(this);
        play.setActionCommand("play");
        play.setBackground(new Color(233, 187, 88));
        play.setFont(new Font("Calibri", Font.BOLD, 20));

        buttons.add(main);
        buttons.add(play);
        middle.add(picture1);
        middle.add(rules);
        middle.add(picture2);
        instructions.add(title);
        instructions.add(middle);
        instructions.add(buttons);
        p_card.add("instructions", instructions);
    }

    // This method is to set up the "play" screen that displays all the different levels
    public void play() {
        play = new JPanel();
        play.setBackground(new Color(112, 168, 178));

        JLabel title = new JLabel("Levels");
        title.setFont(new Font("Algerian", Font.PLAIN, 45));

        // This menu bar is declared to create a menu on my screen.
        JMenuBar menuBar = new JMenuBar();

        // This is to declare the menu and to give it a name
        JMenu menu = new JMenu("           Back          ");
        menu.setFont(new Font("Calibri", Font.BOLD, 20));
        menu.setBackground(new Color(233, 187, 88));

        // This is an option on my menu
        JMenuItem main = new JMenuItem("Main");
        main.addActionListener(this);
        main.setActionCommand("main");

        // This is an option on my menu
        JMenuItem instructions = new JMenuItem("Instructions");
        instructions.addActionListener(this);
        instructions.setActionCommand("instructions");

        // This is to format all of the buttons, the menu and the title so that it is in a straight line in the middle of the screen.
        JPanel levels = new JPanel();
        levels.setLayout(new GridLayout(5, 1, 10, 10));
        levels.setBackground(new Color(112, 168, 178));

        JButton hard = new JButton("Hard");
        hard.addActionListener(this);
        hard.setActionCommand("hard");
        hard.setBackground(new Color(233, 187, 88));
        hard.setFont(new Font("Calibri", Font.BOLD, 20));

        JButton medium = new JButton("Medium");
        medium.addActionListener(this);
        medium.setActionCommand("medium");
        medium.setBackground(new Color(233, 187, 88));
        medium.setFont(new Font("Calibri", Font.BOLD, 20));

        JButton easy = new JButton("Easy");
        easy.addActionListener(this);
        easy.setActionCommand("easy");
        easy.setBackground(new Color(233, 187, 88));
        easy.setFont(new Font("Calibri", Font.BOLD, 20));

        //"Triwizard Tournament (Scopatore).�� Harry Potter Fanon Wiki, harrypotterfanon.wikia.com/wiki/Triwizard_Tournament_(Scopatore).
        JLabel picture1 = new JLabel(createImageIcon("triwizardCup.jpg"));
        picture1.setPreferredSize(new Dimension(120, 280));

        //Same picture as JLabel picture1 as I wanted the same picture to be on either side of the instructions
        //"Triwizard Tournament (Scopatore).�� Harry Potter Fanon Wiki, harrypotterfanon.wikia.com/wiki/Triwizard_Tournament_(Scopatore).
        JLabel picture2 = new JLabel(createImageIcon("triwizardCup.jpg"));
        picture2.setPreferredSize(new Dimension(120, 280));

        // These are used to create spaces between the pictures and the buttons
        JLabel space1 = new JLabel("    ");
        JLabel space2 = new JLabel("    ");

        menuBar.add(menu);
        menu.add(main);
        menu.add(instructions);
        levels.add(title);
        levels.add(easy);
        levels.add(medium);
        levels.add(hard);
        levels.add(menuBar);
        play.add(picture1);
        play.add(space1);
        play.add(levels);
        play.add(space2);
        play.add(picture2);
        p_card.add("play", play);
    }

    // This is a method that assigns values to the easyPuzzle array and it also sets up the screen for the easy puzzle
    public void easy() {
        easy = new JPanel();
        easy.setBackground(new Color(112, 168, 178));

        JPanel puzzle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        puzzle.setBackground(new Color(112, 168, 178));

        JLabel title = new JLabel("        Sudoku        ");
        title.setFont(new Font("Algerian", Font.PLAIN, 45));

        // This is here to assign all the JTextFields their values
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //If the value in the array is 0, it will be set as an empty space in the puzzle array
                if (boardEasy[i][j] == 0)
                    easyPuzzle[i][j] = new JTextField(" ", 2);
                else {
                    easyPuzzle[i][j] = new JTextField("" + boardEasy[i][j], 2);
                    // This is here to make sure that the players can not change these values as they are set values.
                    easyPuzzle[i][j].setEditable(false);
                }
            }
        }

        // This is to make all of the JTextFields the same colour, but a different colour from the background so that it is more visible
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                easyPuzzle[i][j].setBackground(new Color(187, 236, 207));
            }
        }

        JPanel sudoku1 = new JPanel();
        sudoku1.setLayout(new GridLayout(9, 9));

        // This is here to add my sudoku puzzle in a JPanel in order to format it and make it the 9x9 grid game
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                sudoku1.add(easyPuzzle[i][j]);
        }
        //"Triwizard Tournament (Scopatore).�� Harry Potter Fanon Wiki, harrypotterfanon.wikia.com/wiki/Triwizard_Tournament_(Scopatore).
        JLabel picture1 = new JLabel(createImageIcon("triwizardCup.jpg"));
        picture1.setPreferredSize(new Dimension(120, 280));

        //Same picture as JLabel picture1 as I wanted the same picture to be on either side of the instructions
        //"Triwizard Tournament (Scopatore).�� Harry Potter Fanon Wiki, harrypotterfanon.wikia.com/wiki/Triwizard_Tournament_(Scopatore).
        JLabel picture2 = new JLabel(createImageIcon("triwizardCup.jpg"));
        picture2.setPreferredSize(new Dimension(120, 280));

        // This JPanel is here to format the buttons
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttons.setBackground(new Color(112, 168, 178));

        // This menu bar is declared to create a menu on my screen.
        JMenuBar menuBar = new JMenuBar();

        // This is to declare the menu and to give it a name
        JMenu menu = new JMenu(" Back ");
        menu.setFont(new Font("Calibri", Font.BOLD, 20));
        menu.setBackground(new Color(233, 187, 88));

        // This is an option on my menu
        JMenuItem main = new JMenuItem("Main");
        main.addActionListener(this);
        main.setActionCommand("main");

        // This is an option on my menu
        JMenuItem instructions = new JMenuItem("Instructions");
        instructions.addActionListener(this);
        instructions.setActionCommand("instructions");

        // The button will lead back to the "play" screen that has all 3 levels of difficulties
        JMenuItem levels = new JMenuItem("Levels");
        levels.addActionListener(this);
        levels.setActionCommand("play");

        JButton restart = new JButton("Restart");
        restart.addActionListener(this);
        restart.setActionCommand("restartEasy");
        restart.setBackground(new Color(233, 187, 88));
        restart.setFont(new Font("Calibri", Font.BOLD, 20));

        JButton enter = new JButton("Enter");
        enter.addActionListener(this);
        enter.setActionCommand("enterEasy");
        enter.setBackground(new Color(233, 187, 88));
        enter.setFont(new Font("Calibri", Font.BOLD, 20));

        // These three JLabels are here as I am allowing the player 3 chances and so I need to show them on the screen
        easyChance1 = new JLabel("X");
        easyChance1.setFont(new Font("Calibri", Font.BOLD, 20));

        easyChance2 = new JLabel("X");
        easyChance2.setFont(new Font("Calibri", Font.BOLD, 20));

        easyChance3 = new JLabel("X");
        easyChance3.setFont(new Font("Calibri", Font.BOLD, 20));

        // This is here to place a space between the X's and the buttons
        JLabel space = new JLabel("      ");

        menuBar.add(menu);
        menu.add(main);
        menu.add(instructions);
        menu.add(levels);
        buttons.add(easyChance1);
        buttons.add(easyChance2);
        buttons.add(easyChance3);
        buttons.add(space);
        buttons.add(menuBar);
        buttons.add(restart);
        buttons.add(enter);
        puzzle.add(picture1);
        puzzle.add(sudoku1);
        puzzle.add(picture2);
        easy.add(title);
        easy.add(puzzle);
        easy.add(buttons);
        p_card.add("easy", easy);
    }

    // This is a method that assigns values to the mediumPuzzle array and it also sets up the screen for the medium puzzle
    public void medium() {
        medium = new JPanel();
        medium.setBackground(new Color(112, 168, 178));

        JPanel puzzle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        puzzle.setBackground(new Color(112, 168, 178));

        JLabel title = new JLabel("        Sudoku        ");
        title.setFont(new Font("Algerian", Font.PLAIN, 45));

        // This is here to assign every JTextField their values
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //If the value in the array is 0, it will be set as an empty space in the puzzle array
                if (boardMedium[i][j] == 0)
                    mediumPuzzle[i][j] = new JTextField(" ", 2);
                else {
                    mediumPuzzle[i][j] = new JTextField("" + boardMedium[i][j], 2);
                    // This is here to make sure that the players can not changes these values as they are set values.
                    mediumPuzzle[i][j].setEditable(false);
                }
            }
        }

        // This is to make all of the JTextFields the same colour, but a different colour from the background so that it is more visible
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                mediumPuzzle[i][j].setBackground(new Color(187, 236, 207));
            }
        }

        JPanel sudoku2 = new JPanel();
        sudoku2.setLayout(new GridLayout(9, 9));

        // This is here to add my sudoku puzzle in a Jpanel in order to format it and make it the 9x9 grid game
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                sudoku2.add(mediumPuzzle[i][j]);
        }

        //"Triwizard Tournament (Scopatore).�� Harry Potter Fanon Wiki, harrypotterfanon.wikia.com/wiki/Triwizard_Tournament_(Scopatore).
        JLabel picture1 = new JLabel(createImageIcon("triwizardCup.jpg"));
        picture1.setPreferredSize(new Dimension(120, 280));

        //Same picture as JLabel picture1 as I wanted the same picture to be on either side of the instructions
        //"Triwizard Tournament (Scopatore).�� Harry Potter Fanon Wiki, harrypotterfanon.wikia.com/wiki/Triwizard_Tournament_(Scopatore).
        JLabel picture2 = new JLabel(createImageIcon("triwizardCup.jpg"));
        picture2.setPreferredSize(new Dimension(120, 280));

        // This JPanel is here to format the buttons
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttons.setBackground(new Color(112, 168, 178));

        // This menu bar is declared to create a menu on my screen.
        JMenuBar menuBar = new JMenuBar();

        // This is to declare the menu and to give it a name
        JMenu menu = new JMenu(" Back ");
        menu.setFont(new Font("Calibri", Font.BOLD, 20));
        menu.setBackground(new Color(233, 187, 88));

        // This is an option on my menu
        JMenuItem main = new JMenuItem("Main");
        main.addActionListener(this);
        main.setActionCommand("main");

        // This is an option on my menu
        JMenuItem instructions = new JMenuItem("Instructions");
        instructions.addActionListener(this);
        instructions.setActionCommand("instructions");

        // The button will lead back to the "play" screen that has all 3 levels of difficulties
        JMenuItem levels = new JMenuItem("Levels");
        levels.addActionListener(this);
        levels.setActionCommand("play");

        JButton restart = new JButton("Restart");
        restart.addActionListener(this);
        restart.setActionCommand("restartMedium");
        restart.setBackground(new Color(233, 187, 88));
        restart.setFont(new Font("Calibri", Font.BOLD, 20));

        JButton enter = new JButton("Enter");
        enter.addActionListener(this);
        enter.setActionCommand("enterMedium");
        enter.setBackground(new Color(233, 187, 88));
        enter.setFont(new Font("Calibri", Font.BOLD, 20));

        // These three JLabels are here as I am allowing the player 3 chances and so I need to show them on the screen
        mediumChance1 = new JLabel("X");
        mediumChance1.setFont(new Font("Calibri", Font.BOLD, 20));

        mediumChance2 = new JLabel("X");
        mediumChance2.setFont(new Font("Calibri", Font.BOLD, 20));

        mediumChance3 = new JLabel("X");
        mediumChance3.setFont(new Font("Calibri", Font.BOLD, 20));

        // This is here to place a space between the X's and the buttons
        JLabel space = new JLabel("      ");

        menuBar.add(menu);
        menu.add(main);
        menu.add(instructions);
        menu.add(levels);
        buttons.add(mediumChance1);
        buttons.add(mediumChance2);
        buttons.add(mediumChance3);
        buttons.add(space);
        buttons.add(menuBar);
        buttons.add(restart);
        buttons.add(enter);
        puzzle.add(picture1);
        puzzle.add(sudoku2);
        puzzle.add(picture2);
        medium.add(title);
        medium.add(puzzle);
        medium.add(buttons);
        p_card.add("medium", medium);
    }

    public void hard() {
        hard = new JPanel();
        hard.setBackground(new Color(112, 168, 178));

        JPanel puzzle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        puzzle.setBackground(new Color(112, 168, 178));

        JLabel title = new JLabel("        Sudoku        ");
        title.setFont(new Font("Algerian", Font.PLAIN, 45));

        // This is here to assign all the JTextFields their values
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //If the value in the array is 0, it will be set as an empty space in the puzzle array
                if (boardHard[i][j] == 0)
                    hardPuzzle[i][j] = new JTextField(" ", 2);
                else {
                    hardPuzzle[i][j] = new JTextField("" + boardHard[i][j], 2);
                    // This is here to make sure that the players can not change these values as they are set values.
                    hardPuzzle[i][j].setEditable(false);
                }
            }
        }

        // This is to make all of the JTextFields the same colour, but a different colour from the background so that it is more visible
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                hardPuzzle[i][j].setBackground(new Color(187, 236, 207));
            }
        }

        JPanel sudoku3 = new JPanel();
        sudoku3.setLayout(new GridLayout(9, 9));

        // This is here to add my sudoku puzzle in a JPanel in order to format it and make it the 9x9 grid game
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                sudoku3.add(hardPuzzle[i][j]);
        }

        //"Triwizard Tournament (Scopatore).�� Harry Potter Fanon Wiki, harrypotterfanon.wikia.com/wiki/Triwizard_Tournament_(Scopatore).
        JLabel picture1 = new JLabel(createImageIcon("triwizardCup.jpg"));
        picture1.setPreferredSize(new Dimension(120, 280));

        //Same picture as JLabel picture1 as I wanted the same picture to be on either side of the instructions
        //"Triwizard Tournament (Scopatore).�� Harry Potter Fanon Wiki, harrypotterfanon.wikia.com/wiki/Triwizard_Tournament_(Scopatore).
        JLabel picture2 = new JLabel(createImageIcon("triwizardCup.jpg"));
        picture2.setPreferredSize(new Dimension(120, 280));

        // This JPanel is here to format the buttons
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttons.setBackground(new Color(112, 168, 178));

        // This menu bar is declared to create a menu on my screen.
        JMenuBar menuBar = new JMenuBar();

        // This is to declare the menu and to give it a name
        JMenu menu = new JMenu(" Back ");
        menu.setFont(new Font("Calibri", Font.BOLD, 20));
        menu.setBackground(new Color(233, 187, 88));

        // This is an option on my menu
        JMenuItem main = new JMenuItem("Main");
        main.addActionListener(this);
        main.setActionCommand("main");

        // This is an option on my menu
        JMenuItem instructions = new JMenuItem("Instructions");
        instructions.addActionListener(this);
        instructions.setActionCommand("instructions");

        // The button will lead back to the "play" screen that has all 3 levels of difficulties
        JMenuItem levels = new JMenuItem("Levels");
        levels.addActionListener(this);
        levels.setActionCommand("play");

        JButton restart = new JButton("Restart");
        restart.addActionListener(this);
        restart.setActionCommand("restartHard");
        restart.setBackground(new Color(233, 187, 88));
        restart.setFont(new Font("Calibri", Font.BOLD, 20));

        JButton enter = new JButton("Enter");
        enter.addActionListener(this);
        enter.setActionCommand("enterHard");
        enter.setBackground(new Color(233, 187, 88));
        enter.setFont(new Font("Calibri", Font.BOLD, 20));

        // These three JLabels are here as I am allowing the player 3 chances and so I need to show them on the screen
        hardChance1 = new JLabel("X");
        hardChance1.setFont(new Font("Calibri", Font.BOLD, 20));

        hardChance2 = new JLabel("X");
        hardChance2.setFont(new Font("Calibri", Font.BOLD, 20));

        hardChance3 = new JLabel("X");
        hardChance3.setFont(new Font("Calibri", Font.BOLD, 20));

        // This is here to place a space between the X's and the buttons
        JLabel space = new JLabel("      ");

        menuBar.add(menu);
        menu.add(main);
        menu.add(instructions);
        menu.add(levels);
        buttons.add(hardChance1);
        buttons.add(hardChance2);
        buttons.add(hardChance3);
        buttons.add(space);
        buttons.add(menuBar);
        buttons.add(restart);
        buttons.add(enter);
        puzzle.add(picture1);
        puzzle.add(sudoku3);
        puzzle.add(picture2);
        hard.add(title);
        hard.add(puzzle);
        hard.add(buttons);
        p_card.add("hard", hard);
    }

    /* This is a method that return true or false when checking the user input with the answers for the easy puzzle
    The parameter int answer is the user input, int i is the row number and int j is the column number*/
    public boolean easyAnswer(int answer, int i, int j) {
        // This is the array that has all of the correct answers for the easy puzzle
        int[][] easyAnswer = {{5, 6, 3, 8, 2, 7, 1, 9, 4},
                {9, 2, 1, 6, 5, 4, 7, 8, 3},
                {7, 4, 8, 9, 3, 1, 6, 5, 2},
                {4, 8, 7, 2, 6, 9, 3, 1, 5},
                {6, 3, 9, 4, 1, 5, 8, 2, 7},
                {2, 1, 5, 7, 8, 3, 9, 4, 6},
                {3, 9, 4, 5, 7, 8, 2, 6, 1},
                {8, 7, 2, 1, 4, 6, 5, 3, 9},
                {1, 5, 6, 3, 9, 2, 4, 7, 8}};
        // If the user input equals the value in the answer array, true will be returned from the method; if not, false will be returned
        return easyAnswer[i][j] == answer;
    }

    /* This is a method that return true or false when checking the user input with the answers for the medium puzzle
    The parameter int answer is the user input, int i is the row number and int j is the column number*/
    public boolean mediumAnswer(int answer, int i, int j) {
        // This is the array that has all of the correct answers for the medium puzzle
        int[][] mediumAnswer = {{9, 4, 6, 3, 1, 8, 7, 5, 2},
                {8, 7, 5, 9, 6, 2, 4, 3, 1},
                {3, 1, 2, 7, 4, 5, 8, 6, 9},
                {2, 5, 4, 8, 9, 7, 3, 1, 6},
                {7, 6, 3, 4, 5, 1, 2, 9, 8},
                {1, 8, 9, 6, 2, 3, 5, 7, 4},
                {5, 9, 7, 1, 8, 4, 6, 2, 3},
                {4, 3, 1, 2, 7, 6, 9, 8, 5},
                {6, 2, 8, 5, 3, 9, 1, 4, 7}};
        //If the user input equals the value in the answer array, true will be returned from the method; if not, false will be returned
        return mediumAnswer[i][j] == answer;
    }

    /*  This is a method that return true or false when checking the user input with the answers for the hard puzzle
    The parameter int answer is the user input, int i is the row number and int j is the column number*/
    public boolean hardAnswer(int answer, int i, int j) {
        // This is the array that has all of the correct answers for the hard puzzle
        int[][] hardAnswer = {{8, 6, 1, 7, 9, 3, 5, 4, 2},
                {7, 4, 5, 1, 2, 6, 8, 3, 9},
                {9, 2, 3, 8, 5, 4, 7, 6, 1},
                {6, 7, 2, 3, 4, 1, 9, 5, 8},
                {4, 1, 8, 5, 7, 9, 6, 2, 3},
                {3, 5, 9, 6, 8, 2, 1, 7, 4},
                {5, 9, 7, 4, 3, 8, 2, 1, 6},
                {2, 3, 6, 9, 1, 7, 4, 8, 5},
                {1, 8, 4, 2, 6, 5, 3, 9, 7}};
        //If the user input equals the value in the answer array, true will be returned from the method; if not, false will be returned
        return hardAnswer[i][j] == answer;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("instructions"))
            cdLayout.show(p_card, "instructions");
        else if (e.getActionCommand().equals("main"))
            cdLayout.show(p_card, "main");
        else if (e.getActionCommand().equals("play"))
            cdLayout.show(p_card, "play");
        else if (e.getActionCommand().equals("easy"))
            cdLayout.show(p_card, "easy");
        else if (e.getActionCommand().equals("medium"))
            cdLayout.show(p_card, "medium");
        else if (e.getActionCommand().equals("hard"))
            cdLayout.show(p_card, "hard");
            // This code runs if the restart button on the easy screen is pressed
        else if (e.getActionCommand().equals("restartEasy")) {
            // This is here to assign every JTextField their values
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    //If the value in the array is 0, it will be set as an empty space in the puzzle array
                    if (boardEasy[i][j] == 0)
                        easyPuzzle[i][j].setText(" ");
                    else {
                        easyPuzzle[i][j].setText("" + boardEasy[i][j]);
                        // This is here to make sure that the players can not changes these values as they are set values.
                        easyPuzzle[i][j].setEditable(false);
                    }
                }
            }
            cdLayout.show(p_card, "easy");
        }
        // This code runs if the restart button on the medium screen is pressed
        else if (e.getActionCommand().equals("restartMedium")) {
            // This is here to assign every JTextField their values
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    //If the value in the array is 0, it will be set as an empty space in the puzzle array
                    if (boardMedium[i][j] == 0)
                        mediumPuzzle[i][j].setText(" ");
                    else {
                        mediumPuzzle[i][j].setText("" + boardMedium[i][j]);
                        // This is here to make sure that the players can not changes these values as they are set values.
                        mediumPuzzle[i][j].setEditable(false);
                    }
                }
            }
            cdLayout.show(p_card, "medium");
        }
        // This code runs if the restart button on the hard screen is pressed
        else if (e.getActionCommand().equals("restartHard")) {
            // This is here to assign every JTextField their values
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    //If the value in the array is 0, it will be set as an empty space in the puzzle array
                    if (boardHard[i][j] == 0)
                        hardPuzzle[i][j].setText(" ");
                    else {
                        hardPuzzle[i][j].setText("" + boardHard[i][j]);
                        // This is here to make sure that the players can not changes these values as they are set values.
                        hardPuzzle[i][j].setEditable(false);
                    }
                }
            }
            cdLayout.show(p_card, "hard");
        }
        // This code runs if the enter button on the easy screen is pressed
        else if (e.getActionCommand().equals("enterEasy")) {
            // This code goes through all of the squares and sends it to the easyAnswer method to check and see if the user input was correct
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    // This is here as any square with a space will be counted as a 0
                    int a = 0;

    				/* This takes the user input in the square and converts it to an integer. The .trim() makes sure that only the number in the 
            square is taken and converted into an integer. It gets rid of any extra spaces in the squares*/
                    if (!easyPuzzle[i][j].getText().equals(" "))
                        a = Integer.parseInt(easyPuzzle[i][j].getText().trim());
                    easyAnswer(a, i, j);

                    // This is here to change the the first X red if the user is wrong for the first time
                    if (!easyAnswer(a, i, j) && easyWrong == 0) {
                        i = 9;
                        j = 9;
                        easyWrong++;
                        easyChance1.setForeground(Color.red);
                    }
                    // This is here to change the the second X red if the user is wrong for the second time
                    else if (!easyAnswer(a, i, j) && easyWrong == 1) {
                        i = 9;
                        j = 9;
                        easyWrong++;
                        easyChance2.setForeground(Color.red);
                    }
                    // This is here to change the the third X red if the user is wrong for the third time
                    else if (!easyAnswer(a, i, j) && easyWrong == 2) {
                        i = 9;
                        j = 9;
                        easyWrong++;
                        easyChance3.setForeground(Color.red);
                    }
                    // This code runs if all the chances were used by the user. It closes down the program if they used up all of their chances
                    else if (!easyAnswer(a, i, j) && easyWrong == 3) {
                        JOptionPane.showMessageDialog(null, "YOU ARE OUT OF CHOICES AND HAVE FAILED THE GAME. Good-bye!", "WRONG", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                }
            }
    		/* This is here to check if the last square is correct. The code will only get to this step if all of the squares were checked above and not all the chances were used up. 
            It can also only run if the last square isn't empty */
            if (!easyPuzzle[8][8].getText().equals(" ")) {
                int b = Integer.parseInt(easyPuzzle[8][8].getText().trim());
                //If the last square is correct, the entire puzzle is correct and the score increases and so does the variable num
                if (easyAnswer(b, 8, 8) && score < 600) {
                    JOptionPane.showMessageDialog(null, "YOU HAVE COMPLETED THIS PUZZLE. Now try the other levels!", "CORRECT", JOptionPane.ERROR_MESSAGE);
                    score += 300;
                    highScore.setText("Highscore: " + score);
                    num++;
                }
                //If the player completes all 3 puzzles successfully, they will get the highest score possible, 900.
                else if (easyAnswer(b, 8, 8) && score >= 600) {
                    JOptionPane.showMessageDialog(null, "YOU HAVE COMPLETED THIS GAME. Congratulations! You won!", "CORRECT", JOptionPane.ERROR_MESSAGE);
                    score = 900;
                    highScore.setText("Highscore: " + score);
                    num++;
                }
            }
        }
        // This code runs if the restart button on the medium screen is pressed
        else if (e.getActionCommand().equals("enterMedium")) {
            // This code goes through all of the squares and sends it to the mediumAnswer method to check and see if the user input was correct
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    // This is here as any square with a space will be counted as a 0
                    int a = 0;

    				/* This takes the user input in the square and converts it to an integer. The .trim() makes sure that only the number in the 
            square is taken and converted into an integer. It gets rid of any extra spaces in the squares*/
                    if (!mediumPuzzle[i][j].getText().equals(" "))
                        a = Integer.parseInt(mediumPuzzle[i][j].getText().trim());
                    mediumAnswer(a, i, j);

                    // This is here to change the the first X red if the user is wrong for the first time
                    if (!mediumAnswer(a, i, j) && mediumWrong == 0) {
                        i = 9;
                        j = 9;
                        mediumWrong++;
                        mediumChance1.setForeground(Color.red);
                    }
                    // This is here to change the the second X red if the user is wrong for the second time
                    else if (!mediumAnswer(a, i, j) && mediumWrong == 1) {
                        i = 9;
                        j = 9;
                        mediumWrong++;
                        mediumChance2.setForeground(Color.red);
                    }
                    // This is here to change the the third X red if the user is wrong for the third time
                    else if (!mediumAnswer(a, i, j) && mediumWrong == 2) {
                        i = 9;
                        j = 9;
                        mediumWrong++;
                        mediumChance3.setForeground(Color.red);
                    }
                    // This code runs if all the chances were used by the user. It closes down the program if they used up all of their chances
                    else if (!mediumAnswer(a, i, j) && mediumWrong == 3) {
                        JOptionPane.showMessageDialog(null, "YOU ARE OUT OF CHOICES AND HAVE FAILED THE GAME. Good-bye!", "WRONG", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                }
            }
    		/* This is here to check if the last square is correct. The code will only get to this step if all of the squares were checked above and not all the chances were used up. 
        It can also only run if the last square isn't empty */
            if (!mediumPuzzle[8][8].getText().equals(" ")) {
                int b = Integer.parseInt(mediumPuzzle[8][8].getText().trim());
                //If the last square is correct, the entire puzzle is correct and the score increases and so does the variable num
                if (mediumAnswer(b, 8, 8) && score < 600) {
                    JOptionPane.showMessageDialog(null, "YOU HAVE COMPLETED THIS PUZZLE. Now try the other levels!", "CORRECT", JOptionPane.ERROR_MESSAGE);
                    score += 300;
                    highScore.setText("Highscore: " + score);
                    num++;
                }
                //If the player completes all 3 puzzles successfully, they will get the highest score possible, 900.
                else if (mediumAnswer(b, 8, 8) && score >= 600) {
                    JOptionPane.showMessageDialog(null, "YOU HAVE COMPLETED THIS GAME. Congratulations! You won!", "CORRECT", JOptionPane.ERROR_MESSAGE);
                    score = 900;
                    highScore.setText("Highscore: " + score);
                    num++;
                }
            }
        }
        // This code runs if the restart button on the hard screen is pressed
        else if (e.getActionCommand().equals("enterHard")) {
            // This code goes through all of the squares and sends it to the mediumAnswer method to check and see if the user input was correct
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    // This is here as any square with a space will be counted as a 0
                    int a = 0;

    				/* This takes the user input in the square and converts it to an integer. The .trim() makes sure that only the number in the 
            square is taken and converted into an integer. It gets rid of any extra spaces in the squares*/
                    if (!hardPuzzle[i][j].getText().equals(" "))
                        a = Integer.parseInt(hardPuzzle[i][j].getText().trim());
                    hardAnswer(a, i, j);

                    // This is here to change the the first X red if the user is wrong for the first time
                    if (!hardAnswer(a, i, j) && hardWrong == 0) {
                        i = 9;
                        j = 9;
                        hardWrong++;
                        hardChance1.setForeground(Color.red);
                    }
                    // This is here to change the the second X red if the user is wrong for the second time
                    else if (!hardAnswer(a, i, j) && hardWrong == 1) {
                        i = 9;
                        j = 9;
                        hardWrong++;
                        hardChance2.setForeground(Color.red);
                    }
                    // This is here to change the the third X red if the user is wrong for the third time
                    else if (!hardAnswer(a, i, j) && hardWrong == 2) {
                        i = 9;
                        j = 9;
                        hardWrong++;
                        hardChance3.setForeground(Color.red);
                    }
                    // This code runs if all the chances were used by the user. It closes down the program if they used up all of their chances
                    else if (!hardAnswer(a, i, j) && hardWrong == 3) {
                        JOptionPane.showMessageDialog(null, "YOU ARE OUT OF CHOICES AND HAVE FAILED THE GAME. Good-bye!", "WRONG", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                }
            }
    		/* This is here to check if the last square is correct. The code will only get to this step if all of the squares were checked above and not all the chances were used up. 
        It can also only run if the last square isn't empty */
            if (!hardPuzzle[8][8].getText().equals(" ")) {
                int b = Integer.parseInt(hardPuzzle[8][8].getText().trim());
                //If the last square is correct, the entire puzzle is correct and the score increases and so does the variable num
                if (hardAnswer(b, 8, 8) && score < 600) {
                    JOptionPane.showMessageDialog(null, "YOU HAVE COMPLETED THIS PUZZLE. Now try the other levels!", "CORRECT", JOptionPane.ERROR_MESSAGE);
                    score += 300;
                    highScore.setText("Highscore: " + score);
                    num++;
                }
                //If the player completes all 3 puzzles successfully, they will get the highest score possible:900.
                else if (hardAnswer(b, 8, 8) && score >= 600) {
                    JOptionPane.showMessageDialog(null, "YOU HAVE COMPLETED THIS GAME. Congratulations! You won!", "CORRECT", JOptionPane.ERROR_MESSAGE);
                    score = 900;
                    highScore.setText("Highscore: " + score);
                    num++;
                }
            }
        }
        //Depending on what the num variable was, the progress bar will be filled up. The num variable was increased every time any puzzle was completed
        if (num == 1)
            progressBar.setValue(33);
        else if (num == 2)
            progressBar.setValue(66);
        else
            progressBar.setValue(100);
    }

    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Sudoku.class.getResource(path);
        if (imgURL != null)
            return new ImageIcon(imgURL);
        else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}