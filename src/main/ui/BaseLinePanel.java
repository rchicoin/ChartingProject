package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Chart;

// Creates a panel asking if the vitals that were just entered are within the patients baseline. 
public class BaseLinePanel extends JPanel implements ActionListener {

    private VitalsMainMenuUI mainFrame;
    private Chart chart;
    private JPanel mainPanel;

    // EFFECTS: Creates a panel asking if the vitals are within the patients
    // baseline or not, yes or no buttons included.
    public BaseLinePanel(VitalsMainMenuUI mainFrame, Chart chart, JPanel mainPanel) {
        this.mainFrame = mainFrame;
        this.chart = chart;
        this.mainPanel = mainPanel;

        JButton yes = new JButton("Yes");
        JButton no = new JButton("No");

        yes.setActionCommand("RETURNTOMAIN");
        yes.addActionListener(this);
        add(yes);

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
