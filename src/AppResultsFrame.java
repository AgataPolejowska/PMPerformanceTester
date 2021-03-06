import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AppResultsFrame extends JFrame{

    private final ArrayList<JPanel> panels = new ArrayList<>();
    private final JTextArea jTextArea = new JTextArea();

    AppResultsFrame() {
        super.setTitle("Psychomotor Performance Tester | " + Main.getName() + " " + Main.getSurname() + " results ");

        initUI();
    }

    private void initUI() {
        this.getContentPane().setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(800, 700);

        createPanels();
        createInformationHeader();
        loadText();
        addButtons(panels);
    }

    private void createPanels() {
        LayoutManager flowLayout = new FlowLayout();
        Border borderLine = BorderFactory.createLineBorder(Color.lightGray);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        panel2.setPreferredSize(new Dimension(800, 120));
        panel3.setPreferredSize(new Dimension(800, 100));

        panel2.setBorder(borderLine);
        panel3.setBorder(borderLine);

        panels.add(panel1);
        panels.add(panel2);
        panels.add(panel3);

        for (JPanel panel : panels) {
            panel.setBackground(Color.WHITE);
            panel.setLayout(flowLayout);
        }

        this.add(panel1, BorderLayout.CENTER);
        this.add(panel2, BorderLayout.NORTH);
        this.add(panel3, BorderLayout.SOUTH);
    }

    public void createInformationHeader() {
        JLabel headerLabel = new JLabel("Results", JLabel.LEFT);
        headerLabel.setFont(new Font("Verdana", Font.BOLD, 30));
        headerLabel.setBorder(new EmptyBorder(40,0,0,0));

        panels.get(1).add(headerLabel);
    }

    public void loadText() {
        jTextArea.setBorder(new EmptyBorder(40,0,0,0));
        jTextArea.setFont(new Font("Calibri", Font.PLAIN, 20));
        jTextArea.setHighlighter(null);
        jTextArea.setEditable(false);
        try {
            FileReader fr = new FileReader("src/Resources/Text/results.txt");
            BufferedReader reader = new BufferedReader(fr);

            String line;
            while ((line = reader.readLine()) != null)
            {
                this.jTextArea.append(line + "\n");
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }

        this.jTextArea.append("\n");
        this.jTextArea.append("Total score from first and second test: " + Main.getTotal());
        this.jTextArea.append("\nThe score from the third test is presented in the chart.");

        panels.get(0).add(jTextArea);
    }


    public void addButtons(ArrayList<JPanel> panels) {

        StyledButtonUI.setDesign(1);
        JButton nextButton = new JButton(" EXIT ");
        JButton showButton = new JButton(" SHOW CHART ");
        JButton returnButton = new JButton("RETURN");
        nextButton.setUI(new StyledButtonUI());
        showButton.setUI(new StyledButtonUI());
        returnButton.setUI(new StyledButtonUI());

        nextButton.addActionListener(e -> {
            AppResultsFrame.this.dispose();
            System.exit(0);
        });

        showButton.addActionListener(e -> {
            BarChart example = new BarChart("Results");
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);
            example.setVisible(true);
        });

        returnButton.addActionListener(e -> {
            AppResultsFrame.this.dispose();
            Main.setVisibileStartFrame();
        });

        panels.get(2).add(returnButton);
        panels.get(2).add(Box.createHorizontalStrut(100));
        panels.get(2).add(showButton);
        panels.get(2).add(Box.createHorizontalStrut(100));
        panels.get(2).add(nextButton);
    }

}
