package onlineLibraryProject.online_Library_V4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

public class UserInterface extends JFrame {
    onlineLibrary_V4 lib = new onlineLibrary_V4();
    JFrame frame = new JFrame();
    
    JPanel issuedBooksPanel = new JPanel();
    JPanel availableBooksPanel = new JPanel();
    JPanel aboutPanel = new JPanel();
    JPanel returnBooksPanel = new JPanel();

    JPanel cards;
    Color lightGreyBG = new Color(58, 58, 58, 255);

    JButton issuedBooksButton;
    JButton availableBooksButton;
    JButton aboutButton;
    JButton issueButton;
    JButton returnBooksButton;

    JLabel searchLabel;
    JTextField searchField;

    public void displayMenu(){
        String filePath = "logo.png";
        ImageIcon logo = new ImageIcon(new ImageIcon(filePath).getImage().getScaledInstance(200, 120, Image.SCALE_SMOOTH));

        setupLookAndFeel();

        frame.setSize(1000, 600);
        frame.setResizable(false);
        frame.setTitle("The Online Library Project");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
            @Override
            public void windowClosed(WindowEvent e) {
                lib.sendSaveRequest();
            }
        });

        frame.getContentPane().setBackground(lightGreyBG);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(new Color(102, 204, 251, 255));
        sidePanel.setOpaque(true);
        sidePanel.setBounds(0, 0, 200, 600);

        JPanel columnPanel = new JPanel();
        columnPanel.setBackground(new Color(102, 204, 251, 255));
        columnPanel.setOpaque(true);
        columnPanel.setBounds(30, 140, 140, 600);
        columnPanel.setLayout(new GridLayout(12, 1, 0, 20));

        issuedBooksButton = new JButton("Issued Books");
        issuedBooksButton.addActionListener(e -> {
            if(e.getSource() == issuedBooksButton){
                CardLayout c1 = (CardLayout)(cards.getLayout());
                c1.show(cards, "issuedBooksPanel");
                searchLabel.setVisible(true);
                searchField.setVisible(true);
            }
        });
        issuedBooksButton.setFocusable(false);
        issuedBooksButton.setOpaque(false);
        issuedBooksButton.setFont(new Font(null, Font.PLAIN, 15));
        columnPanel.add(issuedBooksButton);

        availableBooksButton = new JButton("Available Books");
        availableBooksButton.setFocusable(false);
        availableBooksButton.addActionListener(e -> {
            if (e.getSource() == availableBooksButton) {
                CardLayout c2 = (CardLayout)(cards.getLayout());
                c2.show(cards, "availableBooksPanel");
                searchLabel.setVisible(true);
                searchField.setVisible(true);
            }
        });
        availableBooksButton.setOpaque(false);
        availableBooksButton.setFont(new Font(null, Font.PLAIN, 15));
        columnPanel.add(availableBooksButton);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(37, 37, 37));
        topPanel.setOpaque(true);
        topPanel.setBounds(200, 0, 800, 50);
        topPanel.setLayout(null);

        JLayeredPane layer = new JLayeredPane();
        layer.setBounds(0, 0, 1000, 600);
        layer.add(sidePanel, Integer.valueOf(0));
        layer.add(columnPanel, Integer.valueOf(1));

        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(logo);
        imageLabel.setBounds(0, 0, 200, 120);

        searchLabel = new JLabel("Search : ");
        searchLabel.setBounds(750, 70, 150, 15);
        searchLabel.setForeground(new Color(163, 163, 167));
        searchLabel.setFocusable(false);
        searchLabel.setVisible(false);
        searchLabel.setFont(new Font("JetBrains Mono font", Font.PLAIN, 15));

        searchField = new JTextField();
        searchField.setCaretColor(Color.black);
        searchField.setVisible(false);
        searchField.setBounds(820, 68, 120, 20);

        aboutButton = new JButton("About");
        aboutButton.setFocusable(false);
        aboutButton.addActionListener(e -> {
            if (e.getSource() == aboutButton) {
                CardLayout c3 = (CardLayout)(cards.getLayout());
                c3.show(cards, "aboutPanel");
                searchLabel.setVisible(false);
                searchField.setVisible(false);
            }
        });
        aboutButton.setBackground(new Color(30, 31, 34, 255));
        aboutButton.setBounds(15, 12, 70, 26);
        aboutButton.setFont(new Font(null, Font.PLAIN, 12));

        issueButton = new JButton("Issue Books");
        issueButton.setFocusable(false);
        issueButton.addActionListener(e -> {
            if (e.getSource() == issueButton) {
                issueBooksTabSetup();
            }
        });
        issueButton.setBackground(new Color(30, 31, 34, 255));
        issueButton.setBounds(95, 12, 100, 26);
        issueButton.setFont(new Font(null, Font.PLAIN, 12));

        returnBooksButton = new JButton("Return Books");
        returnBooksButton.setFocusable(false);
        returnBooksButton.addActionListener(e -> {
            if (e.getSource() == returnBooksButton) {
                CardLayout c5 = (CardLayout)(cards.getLayout());
                c5.show(cards, "returnBooksPanel");
                searchLabel.setVisible(false);
                searchField.setVisible(false);
            }
        });
        returnBooksButton.setBackground(new Color(30, 31, 34, 255));
        returnBooksButton.setBounds(205, 12, 110, 26);
        returnBooksButton.setFont(new Font(null, Font.PLAIN, 12));

        tableSetup(issuedBooksPanel, "I");
        tableSetup(availableBooksPanel, "A");

        aboutSectionSetup();

        cardsSetup();

        topPanel.add(aboutButton);
        topPanel.add(issueButton);
        topPanel.add(returnBooksButton);

        frame.add(searchLabel);
        frame.add(searchField);
        frame.add(topPanel);
        frame.add(imageLabel);
        frame.add(layer);
        frame.add(cards, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
    private void tableSetup(JPanel panel, String db){
        panel.setBounds(230, 110, 730, 430);
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);

        JTable table = lib.dbSetup(db);

        table.setBackground(lightGreyBG);
        table.setForeground(Color.white);
        table.getTableHeader().setReorderingAllowed(false);
        table.setEnabled(false);
        table.setGridColor(new Color(168, 168, 168));

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(70);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setPreferredWidth(110);
        table.getColumnModel().getColumn(4).setPreferredWidth(70);
        table.getColumnModel().getColumn(5).setPreferredWidth(120);
        table.setPreferredScrollableViewportSize(new Dimension(930, 1000));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setForeground(Color.BLACK);
        scrollPane.setOpaque(false);

        panel.add(scrollPane);
    }

    private void aboutSectionSetup(){
        int availableBooks = 0, issuedBooks = 0;
        String message1, message2 = "";

        aboutPanel.setBackground(lightGreyBG);
        aboutPanel.setOpaque(true);

        if(lib.database != null) {
            availableBooks = lib.database.size();
            message1 = "The Total books in our online Library  are : ";
        }
        else message1 = "Our Library is currently empty";
        if(lib.issuedDatabase != null){
            issuedBooks = lib.issuedDatabase.size();
            message2 = "The Total books issued in our Library  are : ";
        }

        JTextArea textArea = new JTextArea(
                """
               Welcome to The onlineLibraryProject, launched on December 15, 2023. Here, a universe of
               knowledge awaits at your fingertips. Dive into a digital realm where literature,
               information, and imagination converge to create an accessible and dynamic space for
               learning and exploration. Join us in this literary journey as we celebrate the joy of
               reading in the digital age.
               """ + "\n"
                        + message1 + availableBooks + "\n"
                        + message2 + issuedBooks + "\n"
        );
        textArea.setBackground(lightGreyBG);
        textArea.setForeground(new Color(190, 189, 189));
        textArea.setEditable(false);

        aboutPanel.add(textArea);
    }

    private void cardsSetup(){
        cards = new JPanel(new CardLayout());

        returnBooksPanel.setBackground(Color.red);
        returnBooksPanel.setOpaque(true);

        cards.add(aboutPanel, "aboutPanel");
        cards.add(availableBooksPanel, "availableBooksPanel");
        cards.add(issuedBooksPanel, "issuedBooksPanel");
        cards.add(returnBooksPanel, "returnBooksPanel");

        cards.setBounds(230, 110, 730, 430);
    }

    private int searchIndex(String ID){
        for(int i = 0; i < lib.database.size(); i++) {
            if (lib.database.get(i).get(0).equals(ID)) return i;
        }
        return  -1;
    }
    private void issueBooksTabSetup(){
        int ans;
        String name;
        String ID;
        int index;
        Object[] row = new Object[6];

        while(true){
            ID = JOptionPane.showInputDialog("Enter the ID of the book to be issued ");
            index = searchIndex(ID);
            if (Objects.equals(ID, "")) continue;

            try {
                name = lib.database.get(searchIndex(ID)).get(1);
                ans = JOptionPane.showConfirmDialog(null, "Do you want to issue this book? \n" + name, "Confirmation", JOptionPane.YES_NO_OPTION);

                if(ans == JOptionPane.OK_OPTION) {
                    JOptionPane.showMessageDialog(null, "you have issued the book \"" + name + "\"");

                    lib.issuedDatabase.add(lib.database.get(index));
                    lib.database.remove(index);

                    for (int i = 0; i < 6; i++) {
                        row[i] = lib.availableBooksModel.getValueAt(index, i);
                    }

                    lib.issuedBooksModel.addRow(row);
                    lib.availableBooksModel.removeRow(index);

                    break;
                } else if (ans == JOptionPane.CANCEL_OPTION || ans == JOptionPane.CLOSED_OPTION) break;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "No such book could be found", "", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
    }
    private void setupLookAndFeel(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            UIManager.put("InternalFrame.activeTitleBackground", Color.black);
//            UIManager.put("InternalFrame.titleFont", new Font(null, Font.PLAIN, fontSize));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new UserInterface().displayMenu();
    }
}
