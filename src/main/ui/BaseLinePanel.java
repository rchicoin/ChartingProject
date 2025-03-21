package ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.Chart;

// Creates a panel asking if the vitals that were just entered are within the patients baseline. 
public class BaseLinePanel extends JPanel implements ActionListener {

    private VitalsMainMenuUI mainFrame;
    private Chart chart;
    private JPanel mainPanel;
    private JLabel baselinePrompt;

    // EFFECTS: Creates a panel asking if the vitals are within the patients
    // baseline or not, yes or no buttons included.
    public BaseLinePanel(VitalsMainMenuUI mainFrame, Chart chart, JPanel mainPanel) {
        this.mainFrame = mainFrame;
        this.chart = chart;
        this.mainPanel = mainPanel;

        setLayout(new GridLayout(0, 1));

        Border border = BorderFactory.createLineBorder(Color.BLUE, 10);
        setBorder(border);
        baselinePrompt = new JLabel("Are these vitals within the patients baseline?");
        add(baselinePrompt);

        JButton yes = new JButton("Yes");
        yes.setActionCommand("RETURNTOMAIN");
        yes.addActionListener(this);
        add(yes);

        JButton no = new JButton("No");
        no.setActionCommand("SETOFFALERT");
        no.addActionListener(this);
        add(no);

    }

    // MODIFIES:mainFrame
    // EFFECTS: if the user chooses "yes" then they will be re-directed back to the
    // main menu, if the user chooses "no" they will be sent to the alert panel
    // reminding them to notify the provider about the patients status
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("SETOFFALERT")) {
            mainFrame.remove(this);
            mainFrame.add(new AlertPanel(mainFrame, chart, mainPanel));
            mainFrame.revalidate();
            mainFrame.repaint();

        } else if (e.getActionCommand().equals("RETURNTOMAIN")) {
            mainFrame.remove(this);
            mainFrame.add(mainPanel);
            mainFrame.revalidate();
            mainFrame.repaint();
        }
    }

}
