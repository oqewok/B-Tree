import Structures.BTree;
import Structures.ITree;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ConsoleWindow extends JFrame {

    //region GUIConstants
    private final int sizeX = 1000;
    private final int sizeY = 550;
    private final int textAreaSizeX = 800;
    private final int textAreaSizeY = 485;
    private final int buttonWidth = 95;
    private final int buttonHeight = 25;
    private final int defaultShiftX = 15;
    private final int defaultShiftY = 10 + buttonHeight;

    private final Color TextColor = new Color(200,200,200);
    //endregion

    //region GUI

    private final JTextArea textAreaConsole;
    private JTextField txtFieldElementNum;
    private JTextField txtCurrentParameter;
    private JTextField txtNewParameter;
    private JTextField txtValueToAdd;
    private JLabel lblCurentParameter;
    private JLabel lblElementNum;
    private JLabel lblNewOrder;

    //region JButtons
    private JButton btnNewBTree;
    private JButton btnLoadBTree;
    private JButton btnSaveBTree;
    private JButton btnApplyParameter;
    private JButton btnShowBTree;
    private JButton btnClearTextArea;
    private JButton btnAddValue;
    private JButton btnRemoveValue;
    //endregion

    private JComboBox<String> comboBoxValues;

    //endregion

    //region Data
    private ITree<String> _BTree;
    private DirectoryDescriptor dirDescriptor;
    //endregion

    //region Methods
    private void PrintBTree(){
        textAreaConsole.append("\n" + "B-Tree из " + _BTree.size() + " элементов с параметром " + (_BTree.order()+1) + ".\n" + _BTree.toString());
    }

    private void RefreshComboBox(){
        try {
            Collection<String> tmpCol = _BTree.toCollection();
            comboBoxValues.removeAllItems();
            Iterator<String> iter = tmpCol.iterator();
            while (iter.hasNext()) {
                comboBoxValues.addItem(iter.next());
            }
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Ошибка обновления списка всех элементов");
        }
    }


    private void SaveBTree(){
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
        JFileChooser fc = new JFileChooser("./");
        fc.setFileFilter(filter);
        fc.setMultiSelectionEnabled(false);
        if ( fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
            try ( FileWriter fw = new FileWriter(fc.getSelectedFile())) {
                StringBuilder sb = new StringBuilder();
                sb.append(_BTree.order()+1);
                sb.append("\n");

                Collection<String> tmpCol = _BTree.toCollection();
                Iterator<String> iter = tmpCol.iterator();

                while(iter.hasNext()){
                    sb.append(iter.next() + "\n");
                }

                fw.write(sb.toString());
                fw.close();
            }
            catch ( IOException ex ) {
                JOptionPane.showMessageDialog(null, "Ошибка записи");
            }
        }

    }
    //endregion


    public ConsoleWindow(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("B-Tree");
        setBounds(0, 0, sizeX, sizeY);
        getContentPane().setLayout(null);


        btnNewBTree = new JButton("Новое дерево");
        btnNewBTree.setBounds(          textAreaSizeX + defaultShiftX, defaultShiftY - 30,
                                        buttonWidth + 70, buttonHeight);

        btnLoadBTree = new JButton("Загрузить");
        btnLoadBTree.setBounds(         textAreaSizeX + defaultShiftX, defaultShiftY,
                                        buttonWidth + 70, buttonHeight);


        btnSaveBTree = new JButton("Сохранить");
        btnSaveBTree.setBounds(         textAreaSizeX + defaultShiftX, buttonHeight + 40,
                                        buttonWidth + 70, buttonHeight);

        txtCurrentParameter = new JTextField();
        txtCurrentParameter.setEditable(false);
        txtCurrentParameter.setBounds(      textAreaSizeX + defaultShiftX, 12 * defaultShiftY,
                                        buttonWidth - 25, buttonHeight);

        txtFieldElementNum = new JTextField();
        txtFieldElementNum.setEditable(false);
        txtFieldElementNum.setBounds(   textAreaSizeX + 2 * defaultShiftX + 80, 12 * defaultShiftY,
                                        buttonWidth - 25, buttonHeight);

        lblCurentParameter = new JLabel("Параметр");
        lblCurentParameter.setBounds(       textAreaSizeX + defaultShiftX, 12 * defaultShiftY + 18,
                                        buttonWidth - 25, buttonHeight);
        getContentPane().add(lblCurentParameter);

        lblElementNum = new JLabel("Элементов");
        lblElementNum.setBounds(        textAreaSizeX + 2 * defaultShiftX + 80, 12 * defaultShiftY + 18,
                                        buttonWidth - 25, buttonHeight);
        getContentPane().add(lblElementNum);


        txtNewParameter = new JTextField();
        txtNewParameter.setBounds(          textAreaSizeX + defaultShiftX, 10 * defaultShiftY + 18,
                                        buttonWidth - 35, buttonHeight);

        lblNewOrder = new JLabel("Параметр");
        getContentPane().add(lblNewOrder);
        lblNewOrder.setBounds(          textAreaSizeX + defaultShiftX, 10 * defaultShiftY + 18 * 2,
                                        buttonWidth - 25, buttonHeight);


        btnApplyParameter = new JButton("Изменить");
        btnApplyParameter.setBounds(        textAreaSizeX + 2 * defaultShiftX + 55, 10 * defaultShiftY + 18,
                                        buttonWidth, buttonHeight);


        btnShowBTree = new JButton("Отобразить");
        btnShowBTree.setBounds(         textAreaSizeX + defaultShiftX, 6 * defaultShiftY,
                                        buttonWidth + 70, buttonHeight);


        btnClearTextArea = new JButton("Очистить");
        btnClearTextArea.setBounds(     textAreaSizeX + defaultShiftX, 7 * defaultShiftY - 5,
                                        buttonWidth + 70, buttonHeight);

        btnAddValue = new JButton("Добавить");
        btnAddValue.setBounds(          textAreaSizeX + defaultShiftX, 12 * defaultShiftY + 41,
                                        buttonWidth + 70, buttonHeight);


        btnRemoveValue = new JButton("Удалить");
        btnRemoveValue.setBounds(       textAreaSizeX + defaultShiftX, 14 * defaultShiftY + 1,
                                        buttonWidth + 70, buttonHeight);

        txtValueToAdd = new JTextField();
        txtValueToAdd.setBounds(       defaultShiftX/2, 12 * defaultShiftY + 44,
                                        buttonWidth * 8 + 22, buttonHeight - 3);


        comboBoxValues = new JComboBox<>();
        comboBoxValues.setBounds(       defaultShiftX/2, 14 * defaultShiftY + 3,
                                        buttonWidth * 8 + 22, buttonHeight - 3);

        getContentPane().add(comboBoxValues);

        //JButton btnNewBTree;
        getContentPane().add(btnNewBTree);
        btnNewBTree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Формируем дерево
                    _BTree = new BTree<>();

                    dirDescriptor = new DirectoryDescriptor();

                    //Выводим информацию о нем
                    txtCurrentParameter.setText(String.valueOf(_BTree.order() + 1));
                    txtFieldElementNum.setText(String.valueOf(_BTree.size()));
                    //Заносим в отображаемый список все файлы и папки
                    RefreshComboBox();
                    txtValueToAdd.setText(null);

                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ошибка создания");
                }
            }
        });



        //JButton btnLoadBTree;
        getContentPane().add(btnLoadBTree);
        btnLoadBTree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
                    JFileChooser fc = new JFileChooser("./");
                    fc.setFileFilter(filter);
                    fc.setMultiSelectionEnabled(false);
                    int ret = fc.showDialog(null, "Открыть файл");
                    if (ret == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        String absolutePath = file.getAbsolutePath();
                        dirDescriptor = new DirectoryDescriptor(absolutePath);

                        //Берем порядок и список всех директорий
                        int tmpDegree = dirDescriptor.getOrder();
                        tmpDegree--;
                        ArrayList<String> tmpList = dirDescriptor.getList();

                        //Формируем дерево
                        _BTree = new BTree<>(tmpDegree);

                        Iterator<String> iter = tmpList.iterator();
                        while (iter.hasNext()){
                            _BTree.add(iter.next());
                        }

                        //Выводим информацию о нем
                        txtCurrentParameter.setText(String.valueOf(_BTree.order() + 1));
                        txtFieldElementNum.setText(String.valueOf(_BTree.size()));

                        //Заносим в отображаемый список все файлы и папки
                        RefreshComboBox();
                        txtValueToAdd.setText(null);
                    }
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ошибка открытия");
                }
            }
        });


        //JButton btnSaveBTree;
        getContentPane().add(btnSaveBTree);
        btnSaveBTree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveBTree();
            }
        });


        //JButton btnApplyParameter;
        getContentPane().add(btnApplyParameter);
        btnApplyParameter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (Integer.parseInt(txtNewParameter.getText()) < 2){
                        JOptionPane.showMessageDialog(null, "Невозможно применить данное значение, параметр > 2.");
                        return;
                    }
                    int newDegree = Integer.parseInt(txtNewParameter.getText());
                    newDegree--;
                    ArrayList<String> tmpList = dirDescriptor.getList();
                    dirDescriptor.setOrder(newDegree);

                    Collection<String> tmpAllElements = _BTree.toCollection();

                    _BTree = new BTree<>(dirDescriptor.getOrder());

                    Iterator<String> iter = null;
                    if (!tmpAllElements.isEmpty()){
                        iter = tmpAllElements.iterator();
                    }

                    if (iter!=null)
                    while (iter.hasNext()){
                        _BTree.add(iter.next());
                    }

                    txtCurrentParameter.setText(String.valueOf(_BTree.order() + 1));
                    txtFieldElementNum.setText(String.valueOf(_BTree.size()));

                    RefreshComboBox();
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Невозможно применить данное значение");
                }
            }
        });

        //JButton btnShowBTree;
        getContentPane().add(btnShowBTree);
        btnShowBTree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PrintBTree();
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ошибка отображения");
                }
            }


        });

        //JButton btnClearTextArea;
        getContentPane().add(btnClearTextArea);
        btnClearTextArea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    textAreaConsole.setText(null);
                    txtValueToAdd.setText(null);
                    txtNewParameter.setText(null);
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ошибка очистки");
                }
            }
        });

        //JButton btnAddValue
        getContentPane().add(btnAddValue);
        btnAddValue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String itemToAdd = String.valueOf(txtValueToAdd.getText());
                    if (!itemToAdd.isEmpty())
                        if (!_BTree.contains(itemToAdd)){
                            _BTree.add(itemToAdd);}
                        else{
                            JOptionPane.showMessageDialog(null, "Элемент уже присутствует");
                        }
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Ошибка добавления элемента");
                }
                try{
                    txtCurrentParameter.setText(String.valueOf(_BTree.order() + 1));
                    txtFieldElementNum.setText(String.valueOf(_BTree.size()));
                    RefreshComboBox();
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Ошибка обновления информации");
                }
            }
        });

        //JButton btnRemoveValue
        getContentPane().add(btnRemoveValue);
        btnRemoveValue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String itemToRemove = String.valueOf(comboBoxValues.getSelectedItem());
                    _BTree.remove(itemToRemove);
                    dirDescriptor.remove(itemToRemove);
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ошибка удаления элемента");
                }

                try{
                    txtCurrentParameter.setText(String.valueOf(_BTree.order()));
                    txtFieldElementNum.setText(String.valueOf(_BTree.size()));
                    RefreshComboBox();
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Ошибка обновления информации");
                }
            }
        });

        //txtCurrentParameter
        txtCurrentParameter.setHorizontalAlignment(txtCurrentParameter.CENTER);
        getContentPane().add(txtCurrentParameter);
        txtCurrentParameter.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                if (str == null)
                    return;
                if ((getLength() + str.length()) <= 5) {
                    super.insertString(offset, str, attr);
                }
            }
        });

        //txtFieldElementNum
        txtFieldElementNum.setHorizontalAlignment(txtFieldElementNum.CENTER);
        getContentPane().add(txtFieldElementNum);
        txtFieldElementNum.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                if (str == null)
                    return;
                if ((getLength() + str.length()) <= 5) {
                    super.insertString(offset, str, attr);
                }
            }
        });

        //txtNewParameter
        txtNewParameter.setHorizontalAlignment(txtNewParameter.CENTER);
        getContentPane().add(txtNewParameter);
        txtNewParameter.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                if (str == null)
                    return;
                if ((getLength() + str.length()) <= 5) {
                    super.insertString(offset, str, attr);
                }
            }
        });

        //txtValueToAdd
        txtValueToAdd.setHorizontalAlignment(txtValueToAdd.CENTER);
        getContentPane().add(txtValueToAdd);

        //textAreaConsole
        textAreaConsole = new JTextArea();
        textAreaConsole.setLineWrap(false);
        textAreaConsole.setFont(new Font("MyFont", Font.PLAIN, 12));
        textAreaConsole.setForeground(TextColor);
        textAreaConsole.setCaretColor(TextColor);
        textAreaConsole.setBackground(Color.black);
        textAreaConsole.setWrapStyleWord(true);
        textAreaConsole.setEditable(false);

        //scrollPane
        JScrollPane scrollPane = new JScrollPane(textAreaConsole);
        scrollPane.setBounds(0, 0, textAreaSizeX-5, textAreaSizeY-27);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setWheelScrollingEnabled(true);
        getContentPane().add(scrollPane);

        textAreaConsole.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                switch (e.getKeyChar()) {
                    case KeyEvent.VK_ENTER: {
                        int last = 0;
                        int start = 0;
                        int end = 0;
                        String command = null;
                        try {
                            last  = textAreaConsole.getLineCount() - 1;
                            start = textAreaConsole.getLineStartOffset(last);
                            end = textAreaConsole.getLineEndOffset(last);
                            command = textAreaConsole.getText().substring(start, end);

                        } catch (BadLocationException e1) {
                            e1.printStackTrace();
                            textAreaConsole.append("\n" + "Unable understand a command");
                        }

                        textAreaConsole.append("\n" + command);
                        break;
                    }
                }
            }
        });
    }

}