import javax.swing.*;
import java.awt.*;

/**
 * Created by nandigam on 4/6/17.
 */
public class SreeVaishnaviMataEggTraders extends JFrame{

    public Container container = new Container();

    public SreeVaishnaviMataEggTraders(JPanel panel) throws HeadlessException {
        container.add(panel);
        container.setSize(new Dimension(panel.getWidth(),panel.getHeight()));
        add(container);
        setTitle("Sree Vaishnavi Egg Traders");
        setSize(panel.getWidth(),panel.getHeight());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        DataEntry dataEntry = new DataEntry();
        new SreeVaishnaviMataEggTraders(dataEntry);

    }
}
