import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;

public class MainForm {
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel textPanel;
    private JButton collapse;
    private JButton expand;
    private JTextArea surname;
    private JTextArea patronymic;
    private JTextArea name;
    private JTextArea fullName;


    public MainForm() {
        mainPanel.setBackground(new Color(0xA2E5E5));
        buttonPanel.setBackground(new Color(0xA2E5E5));
        textPanel.setBackground(new Color(0xA2E5E5));
        expand.setVisible(false);
        fullName.setVisible(false);
        collapse.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {

                if (surname.getText().isEmpty() || name.getText().isEmpty()){
                    JOptionPane.showMessageDialog(
                            mainPanel,
                            "Фамилия и имя не указанный, пожалуйста заполните соответсвующие поля",
                            "Заполните имя и фамилию",
                            JOptionPane.PLAIN_MESSAGE
                    );


                }else {
                    String text = surname.getText()  + " " + name.getText()+ " " + patronymic.getText();
                    fullName.setText(text);
                    surname.setVisible(false);
                    name.setVisible(false);
                    patronymic.setVisible(false);
                    fullName.setVisible(true);
                    collapse.setVisible(false);
                    expand.setVisible(true);
                }
            }
        });

        expand.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                String[] arrayString = fullName.getText().split("\\s+");
                if (arrayString.length < 2 || arrayString.length > 3) {
                    JOptionPane.showMessageDialog(
                            mainPanel,
                            "Фамилия и имя не указанный, пожалуйста заполните соответсвующие поле",
                            "Заполните имя и фамилию",
                            JOptionPane.PLAIN_MESSAGE
                    );
                }
                else {
                    if (arrayString.length == 3) {
                        surname.setText(arrayString[0]);
                        name.setText(arrayString[1]);
                        patronymic.setText(arrayString[2]);
                    }
                    else {
                        surname.setText(arrayString[0]);
                        name.setText(arrayString[1]);
                        patronymic.setText("");
                    }
                    expand.setVisible(false);
                    fullName.setVisible(false);
                    surname.setVisible(true);
                    name.setVisible(true);
                    patronymic.setVisible(true);
                    collapse.setVisible(true);

                }

            }
        });

        surname.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                surname.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        name.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                name.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        patronymic.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                patronymic.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
