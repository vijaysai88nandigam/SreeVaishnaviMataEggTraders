import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Properties;

/**
 * Created by nandigam on 4/6/17.
 */
class DataEntry extends Panel {

    String data[][] = {};
    String column[] = {"S.NO.","DESCRIPTION","PER BOX","QUANTITY", "RATE", "TOTAL"};

    String bankData[][] = {};
    String bankColumn[] = {"<html><br>&nbsp;<br>&nbsp;DATE<br>&nbsp;<br>&nbsp;</html>","<html>LESS AMOUNTS<br>RECEIVED FROM YOU<br>ON <b>NAME OF THE<b></html>","BANK", "AMOUNT"};

    DataEntry() {

        int itemRowHeight = 550;
        int lessCreditItemHeight = 600;

        setLayout(null);
        setSize(800,700);

        JLabel dataLabel = new JLabel();
        dataLabel.setText("Date:");
        dataLabel.setBounds(10,10,50,50);
        add(dataLabel);

        JLabel testLabel = new JLabel();
        testLabel.setText("");
        testLabel.setBounds(250,10,300,50);
        add(testLabel);

        UtilDateModel billingDateModel = new UtilDateModel();
        Properties billingDateProperties = new Properties();
        billingDateProperties.put("text.today","Today");
        billingDateProperties.put("text.month", "Month");
        billingDateProperties.put("text.year", "Year");
        JDatePanelImpl billingDateDatePanel = new JDatePanelImpl(billingDateModel,billingDateProperties);
        JDatePickerImpl billingDateDatePicker = new JDatePickerImpl(billingDateDatePanel,new DateLabelFormatter("dd-MM-YYYY"));
        billingDateDatePicker.setBounds(70,22,130,50);
        billingDateDatePicker.addActionListener(e -> testLabel.setText(new SimpleDateFormat("EEEE, MMM,dd,yyyy")
                .format((billingDateDatePicker.getModel().getValue()))));
        add(billingDateDatePicker);

        Parties parties = new Parties();
        JComboBox<Object> partiesSelection = new JComboBox<>(parties.getPartiesData().keySet().toArray());
        partiesSelection.setBounds(340,91,170,30);
        add(partiesSelection);

        JLabel partiesLabel = new JLabel();
        partiesLabel.setText("Vendors:");
        partiesLabel.setBounds(270,95,100,20);
        add(partiesLabel);

        JLabel codeLabel = new JLabel();
        codeLabel.setText("Code:");
        codeLabel.setBounds(10,80,50,50);
        add(codeLabel);

        JTextPane codeTextPane = new JTextPane(new DefaultStyledDocument(){

            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if ((getLength() + str.length()) <= 9) {
                    super.insertString(offs, str, a);
                }
                else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        codeTextPane.setBounds(55,90,140,30);
        codeTextPane.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        add(codeTextPane);

        JTable eggsTable = new JTable(data, column);
        eggsTable.setBounds(10,150,750,110);

        JScrollPane scrollPane = new JScrollPane(eggsTable);
        scrollPane.setBounds(10,150,750,110);
        add(scrollPane);

        JLabel serialNoLabel = new JLabel();
        serialNoLabel.setText("Sl.No:");
        serialNoLabel.setBounds(10,itemRowHeight,45,20);
        add(serialNoLabel);

        JComboBox<String> serialNoBox = new JComboBox<>(new String[] {"1","2","3","4","5"});
        serialNoBox.setBounds(60,itemRowHeight,40,20);
        add(serialNoBox);

        JLabel descriptionLabel = new JLabel();
        descriptionLabel.setText("Description:");
        descriptionLabel.setBounds(110,itemRowHeight,90,20);
        add(descriptionLabel);

        JTextPane descriptionTextBox = new JTextPane(new DefaultStyledDocument(){
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if ((str.contains("1") || str.contains("2") || str.contains("3") || str.contains("4")
                        ||str.contains("5") || str.contains("6") || str.contains("7") || str.contains("8") ||
                        str.contains("9") || str.contains("0")) && (getLength() + str.length()) <= 4) {
                    super.insertString(offs, str, a);
                }
                else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        descriptionTextBox.setBounds(205,itemRowHeight,35,20);
        add(descriptionTextBox);

        JLabel perBoxLabel = new JLabel();
        perBoxLabel.setText("Per Box:");
        perBoxLabel.setBounds(260,itemRowHeight,100,20);
        add(perBoxLabel);

        JTextPane perBoxTextPane = new JTextPane(new DefaultStyledDocument(){
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if ((str.contains("1") || str.contains("2") || str.contains("3") || str.contains("4")
                        ||str.contains("5") || str.contains("6") || str.contains("7") || str.contains("8") ||
                        str.contains("9") || str.contains("0")) && (getLength() + str.length()) <= 3) {
                    super.insertString(offs, str, a);
                }
                else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        perBoxTextPane.setBounds(325,itemRowHeight,35,20);
        add(perBoxTextPane);

        JLabel rateLabel = new JLabel();
        rateLabel.setText("Rate:");
        rateLabel.setBounds(380,itemRowHeight,100,20);
        add(rateLabel);

        JTextPane rateLabelTextBox = new JTextPane(new DefaultStyledDocument(){
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if ((getLength() + str.length()) == 2 && str.contains(".")) {
                    super.insertString(offs, str, a);
                }else if (getLength() + str.length() >= 1 && getLength() + str.length() <= 4 && getLength() + str.length() !=2 &&
                        (str.contains("1") || str.contains("2") || str.contains("3") || str.contains("4")
                        ||str.contains("5") || str.contains("6") || str.contains("7") || str.contains("8") ||
                        str.contains("9") || str.contains("0"))){
                    super.insertString(offs, str, a);
                }
                else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        rateLabelTextBox.setBounds(425,itemRowHeight,35,20);
        add(rateLabelTextBox);

        JLabel lorryAdvanceFor = new JLabel();
        lorryAdvanceFor.setText("LORRY ADVANCE FOR:");
        lorryAdvanceFor.setBounds(400,310,150,20);
        add(lorryAdvanceFor);

        JTextPane lorryNumberTextBox = new JTextPane();
        lorryNumberTextBox.setBounds(552,310,100,20);
        add(lorryNumberTextBox);

        JTable bankTable = new JTable(bankData,bankColumn);
        bankTable.setBounds(10,350,550,1900);

        JScrollPane bankTableLayout = new JScrollPane(bankTable);
        bankTableLayout.setBounds(10,350,550,190);
        add(bankTableLayout);

        JLabel subTotalLabel = new JLabel("SUB TOTAL:");
        subTotalLabel.setBounds(570,350,100,20);
        add(subTotalLabel);

        JLabel subTotalText = new JLabel("");
        subTotalText.setBounds(660,350,100,20);
        add(subTotalText);

        JLabel previousDueLabel = new JLabel("PREVIOUS DUE:");
        previousDueLabel.setBounds(570,390,110,20);
        add(previousDueLabel);

        JLabel lessCreditLabel = new JLabel("LESS CREDIT:");
        lessCreditLabel.setBounds(570,420,100,20);
        add(lessCreditLabel);

        JLabel lessCreditAmount = new JLabel("");
        lessCreditAmount.setBounds(660,420,100,20);
        add(lessCreditAmount);

        UtilDateModel lessCreditDateModel = new UtilDateModel();
        Properties lessCreditDateProperties = new Properties();
        lessCreditDateProperties.put("text.today","Today");
        lessCreditDateProperties.put("text.month", "Month");
        lessCreditDateProperties.put("text.year", "Year");

        JDatePanelImpl lessCreditDatePanel = new JDatePanelImpl(lessCreditDateModel,lessCreditDateProperties);
        JDatePickerImpl lessCreditDatePicker = new JDatePickerImpl(lessCreditDatePanel,new DateLabelFormatter("dd-MMM"));
        lessCreditDatePicker.setBounds(10,lessCreditItemHeight,95,25);
        add(lessCreditDatePicker);

        JLabel lessCredNameLabel = new JLabel("In name of:");
        lessCredNameLabel.setBounds(110,lessCreditItemHeight,100,25);
        add(lessCredNameLabel);

        JTextPane lessCredNameTextPane = new JTextPane();
        lessCredNameTextPane.setBounds(200,lessCreditItemHeight,120,25);
        add(lessCredNameTextPane);

        JLabel lessCreditBankLabel = new JLabel("Bank:");
        lessCreditBankLabel.setBounds(330,lessCreditItemHeight,60,25);
        add(lessCreditBankLabel);

        JTextPane lessCreditBankTextPane = new JTextPane();
        lessCreditBankTextPane.setBounds(370,lessCreditItemHeight,120,25);
        add(lessCreditBankTextPane);

        JLabel lessCreditAmountLabel = new JLabel("Amount:");
        lessCreditAmountLabel.setBounds(500,lessCreditItemHeight,60,25);
        add(lessCreditAmountLabel);

        JTextPane lessCreditAmountTextPane = new JTextPane(new DefaultStyledDocument(){
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (str.contains(".") || str.contains("1") || str.contains("2") || str.contains("3") || str.contains("4")
                                ||str.contains("5") || str.contains("6") || str.contains("7") || str.contains("8") ||
                                str.contains("9") || str.contains("0")){
                    super.insertString(offs, str, a);
                }
                else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });

        JLabel totalLabel = new JLabel("TOTAL:");
        totalLabel.setBounds(570,450,100,25);
        add(totalLabel);

        JLabel totalAmount = new JLabel();
        totalAmount.setBounds(630,450,100,25);
        add(totalAmount);

        JTextPane previousTextBox = new JTextPane(new DefaultStyledDocument(){
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (str.contains(".") || str.contains("1") || str.contains("2") || str.contains("3") || str.contains("4")
                        ||str.contains("5") || str.contains("6") || str.contains("7") || str.contains("8") ||
                        str.contains("9") || str.contains("0")){
                    super.insertString(offs, str, a);
                }
                else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        previousTextBox.setBounds(680,390,100,20);
        previousTextBox.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                totalAmount.setText(String.valueOf(CalculateTotal(subTotalText.getText(),previousTextBox.getText(),lessCreditAmount.getText())));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                totalAmount.setText(String.valueOf(CalculateTotal(subTotalText.getText(),previousTextBox.getText(),lessCreditAmount.getText())));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                totalAmount.setText(String.valueOf(CalculateTotal(subTotalText.getText(),previousTextBox.getText(),lessCreditAmount.getText())));
            }
        });
        add(previousTextBox);

        JTextPane lorryAdvanceForTextBox = new JTextPane(new DefaultStyledDocument(){
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (str.contains("1") || str.contains("2") || str.contains("3") || str.contains("4")
                        ||str.contains("5") || str.contains("6") || str.contains("7") || str.contains("8") ||
                        str.contains("9") || str.contains("0")) {
                    super.insertString(offs, str, a);
                }
                else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        lorryAdvanceForTextBox.setBounds(660,310,100,20);
        lorryAdvanceForTextBox.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                subTotalText.setText(String.valueOf(CalculateSubTotal(data,lorryAdvanceForTextBox.getText())));
                totalAmount.setText(String.valueOf(CalculateTotal(subTotalText.getText(),previousTextBox.getText(),lessCreditAmount.getText())));

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                subTotalText.setText(String.valueOf(CalculateSubTotal(data,lorryAdvanceForTextBox.getText())));
                totalAmount.setText(String.valueOf(CalculateTotal(subTotalText.getText(),previousTextBox.getText(),lessCreditAmount.getText())));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                subTotalText.setText(String.valueOf(CalculateSubTotal(data,lorryAdvanceForTextBox.getText())));
                totalAmount.setText(String.valueOf(CalculateTotal(subTotalText.getText(),previousTextBox.getText(),lessCreditAmount.getText())));
            }
        });
        add(lorryAdvanceForTextBox);

        JButton addButton = new JButton();
        addButton.setText("Add Item");
        addButton.setBounds(465,itemRowHeight,100,20);
        addButton.addActionListener(e -> {
            int serialNo, description, perBox, quantity, length;
            String temp[][];
            float rate, total;
            if (serialNoBox.getSelectedItem() != null && !Objects.equals(descriptionTextBox.getText(), "") &&
                    !Objects.equals(perBoxTextPane.getText(), "") && !Objects.equals(rateLabelTextBox.getText(), ""))
            {
                serialNo = Integer.parseInt(String.valueOf(serialNoBox.getSelectedItem()));
                description = Integer.parseInt(descriptionTextBox.getText());
                perBox = Integer.parseInt(perBoxTextPane.getText());
                quantity = description * perBox;
                rate = Float.parseFloat(rateLabelTextBox.getText());
                total = quantity * rate;
                length = data.length;
                temp = new String[data.length + 1][6];

                for (int i =0; i<data.length;i++){
                    System.arraycopy(data[i], 0, temp[i], 0, data[i].length);
                }

                temp[length][0] = String.valueOf(serialNo);
                temp[length][1] = String.valueOf(description);
                temp[length][2] = String.valueOf(perBox);
                temp[length][3] = String.valueOf(quantity);
                temp[length][4] = String.valueOf(rate);
                if (total == 0){
                    temp[length][5] = "-";
                }else {
                    temp[length][5] = String.valueOf(total);
                }

                data = temp;

                JTable tempTable;
                tempTable = new JTable(data,column);
                tempTable.setBounds(10,150,550,110);
                eggsTable.setModel(tempTable.getModel());
                serialNoBox.removeItemAt(serialNoBox.getSelectedIndex());
                subTotalText.setText(String.valueOf(CalculateSubTotal(data,lorryAdvanceForTextBox.getText())));
                totalAmount.setText(String.valueOf(CalculateTotal(subTotalText.getText(),previousTextBox.getText(),lessCreditAmount.getText())));
            }

        });
        add(addButton);

        lessCreditAmountTextPane.setBounds(560,lessCreditItemHeight,120,25);
        add(lessCreditAmountTextPane);

        JButton lessCreditButton = new JButton("Add less Credit");
        lessCreditButton.setBounds(600,lessCreditItemHeight + 30,150,25);
        lessCreditButton.addActionListener(e -> {
            if (bankTable.getRowCount() < 5 && !(lessCreditDatePicker.getJFormattedTextField().getText().equals("")) && !lessCredNameTextPane.getText()
                    .equals("") && !lessCreditBankTextPane.getText().equals("") && !lessCreditAmountTextPane.getText()
                    .equals("")){
                String date,inNameOf,bankName;
                Float amount;
                int length = bankData.length;
                String[][] temp = new String[length+1][4];
                Float lessCreditTotal = Float.valueOf(0);

                date = lessCreditDatePicker.getJFormattedTextField().getText();
                inNameOf = lessCredNameTextPane.getText();
                bankName = lessCreditBankTextPane.getText();
                amount = Float.parseFloat(lessCreditAmountTextPane.getText());

                for (int i = 0; i < bankData.length; i++){
                    System.arraycopy(bankData[i],0,temp[i],0, bankData[i].length);
                }

                temp[length][0] = String.valueOf(date);
                temp[length][1] = String.valueOf(inNameOf);
                temp[length][2] = String.valueOf(bankName);
                temp[length][3] = String.valueOf(amount);

                bankData = temp;

                for (String[] bankDataRow : bankData) {
                    lessCreditTotal += Float.valueOf(bankDataRow[bankDataRow.length -1]);
                }
                lessCreditAmount.setText(String.valueOf(lessCreditTotal));
                totalAmount.setText(String.valueOf(CalculateTotal(subTotalText.getText(),previousTextBox.getText(),lessCreditAmount.getText())));
                JTable tempTable;
                tempTable = new JTable(bankData,bankColumn);
                tempTable.setBounds(110,350,550,110);
                bankTable.setModel(tempTable.getModel());
            }
        });
        add(lessCreditButton);

        JButton pdfGeneration = new JButton("Generate PDF");
        pdfGeneration.addActionListener(e->{
            if (!Objects.equals(testLabel.getText(), "") && !codeTextPane.getText().equals("") && data.length > 0
                    && !Objects.equals(lorryNumberTextBox.getText(), "")){
                PdfGenerator pdfGenerator = new PdfGenerator();
                try {
                    pdfGenerator.generatePdf(testLabel.getText(),codeTextPane.getText(),partiesSelection.getSelectedItem().toString()
                            ,data,lorryNumberTextBox.getText(), lorryAdvanceForTextBox.getText(),bankData,
                            subTotalText.getText(),previousTextBox.getText(),
                            lessCreditAmount.getText(),totalAmount.getText(),"original");
                    pdfGenerator.generatePdf(testLabel.getText(),codeTextPane.getText(),partiesSelection.getSelectedItem().toString()
                            ,data,lorryNumberTextBox.getText(), lorryAdvanceForTextBox.getText(),bankData,
                            subTotalText.getText(),previousTextBox.getText(),
                            lessCreditAmount.getText(),totalAmount.getText(),"duplicate");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        pdfGeneration.setBounds(600,500,150,25);
        add(pdfGeneration);
    }

    private Float CalculateSubTotal(String data[][], String lorryAdvance){
        Float total = 0f;
        if (data.length > 0){
            for (String[] dataItem : data) {
                if (!Objects.equals(dataItem[dataItem.length - 1], "-"))
                total += Float.parseFloat(dataItem[dataItem.length-1]);
            }
        }
        if (!lorryAdvance.equals("")){
            total += Float.parseFloat(lorryAdvance);
        }
        return total;
    }

    private Float CalculateTotal(String subTotal,String previousDue,String lessCredit){
        if (subTotal.equals("")){
            subTotal = "0";
        }
        if (previousDue.equals("")){
            previousDue = "0";
        }
        if (lessCredit.equals("")){
            lessCredit="0";
        }
        return Float.valueOf(subTotal)  + Float.valueOf(previousDue) - Float.valueOf(lessCredit);
    }
}
