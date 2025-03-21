package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Chart;

//This is a panel that is shown when a patients NEWS score is greater than 5 and the user chose "no" 
//when determining if the entered vitals were within the patients baseline. 
public class AlertPanel extends JPanel implements ActionListener {
    private VitalsMainMenuUI mainFrame;
    private Chart chart;
    private JPanel mainPanel;

    // EFFECTS: Creates an alert panel when patients NEWS score is above 5 and its
    // not their baseline.
    public AlertPanel(VitalsMainMenuUI mainFrame, Chart chart, JPanel mainPanel) {
        this.mainFrame = mainFrame;
        this.chart = chart;
        this.mainPanel = mainPanel;

        JButton back = new JButton("Back to Main Menu");

        back.setActionCommand("BACKTOMAIN");
        back.addActionListener(this);
        add(back);

        ImageIcon alert = new ImageIcon("data/alertImage.png");
        JLabel alertLabel = new JLabel(alert);
        add(alertLabel);
    }

    // MODIFIES: mainFrame
    // EFFECTS: adds the mainPanel (main menu) back to the mainFrame.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("BACKTOMAIN")) {
            mainFrame.remove(this);
            mainFrame.add(mainPanel);
            mainFrame.revalidate();
            mainFrame.repaint();
        }
    }

}
