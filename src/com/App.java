package com;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

class App {

    private JButton buttonmsg;
    private JPanel panelMain;
    private JTextArea pleaseEnterYourNameTextArea;
    private JTextField textField1;
    private JTextArea pleaseEnterYourFavoriteTextArea;
    private JPasswordField passwordField1;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private String filePathWelcome = "resources/Welcome.txt";
    private String filePathTutorial = "resources/Tutorial.txt";
    private static JFrame frame = new JFrame("App");

    public App() throws IOException {
        buttonmsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // just a little demonstration to show that when the event listener is called text is sout'ed
                System.out.println("Button pressed");
                // after clicking the button the visibility is set to true
                textArea1.setVisible(true);
                textArea2.setVisible(true);
                
                JOptionPane.showMessageDialog(null, textField1.getText());
            }
        });
        // this is the pre-generated KeyListener for the button
        buttonmsg.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                    System.out.println("hello");
                }
                textArea2.setVisible(false);
                // I do not know the specific use case of this
                // currently but this can easily be edited to fit as necessary
                super.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
            }
        });
        // These two textAreas are referencing JTextArea fields that are instantiated in the App.form
        // I am unsure if there is a way to prevent having to instantiate two FileReaders, but it might have to be
        // necessary because you are having to call the reader and the target in the .read()
        FileReader reader = new FileReader(filePathWelcome);
        textArea1.read(reader, filePathWelcome);
        textArea1.setVisible(false);
        // this logic is to set the visibility of the textArea to invisible and reliant on the actionListener above
        FileReader reader2 = new FileReader(filePathTutorial);
        textArea2.read(reader2, filePathTutorial);
        textArea2.setVisible(false);
    }

    public static void main(String[] args) throws IOException {
        // This main method instantiates the frame, though I am curious as to why it's necessary since there is a frame
        // in the App.form as well. perhaps this is just a necessary step? I imagine that the code with EXIT_ON_CLOSE
        // is also necessary

        frame.setPreferredSize(new Dimension(600, 450));
        frame.setContentPane(new App().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // I know that setVisible is an option that can be toggled in the settings of App.form, so why not the rest?
        frame.setVisible(true);


        // these are just test labels and experiments to change Bounds and how to add it to the label
//        JLabel label = new JLabel("User 44");
//        label.setBounds(10, 20, 80, 25);
//        frame.add(label);

        // this was my first attempt at adding text into a field manually but found a way to do so through the App.form
//        JTextField userText = new JTextField(20);
//        userText.setBounds(100, 20, 165, 25);
//        frame.add(userText);
//        JLabel success = new JLabel("");
//        success.setBounds(10, 110, 300, 25);
//        frame.add(success);
        
    }
}