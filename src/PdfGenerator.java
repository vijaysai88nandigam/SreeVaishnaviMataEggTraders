import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/**
 * Created by nandigam on 9/7/17.
 */
public class PdfGenerator {
    private PDDocument document = null;
    private String separator = File.separator;
    private String home = System.getProperty("user.home");
    Parties parties = new Parties();

    public void generatePdf(String billingDate,String billNO,String vender,String itemData[][],String lorryNumber,String lorryAdvance,
                            String bankData[][],String subTotal,String previousDue,String lessCreditAmount, String totalAmount, String billType) throws IOException {
        try {
            document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            PDFont font = PDType1Font.TIMES_ROMAN;
            PDFont boldFont = PDType1Font.TIMES_BOLD;
            PDPageContentStream contentStream = new PDPageContentStream(document,page);
//            PDImageXObject pdImage = PDImageXObject.createFromFile("images/invoice.png", document);
//            contentStream.saveGraphicsState();
//            contentStream.drawImage(pdImage, 0, 0,PDRectangle.A4.getWidth(),PDRectangle.A4.getHeight());
//            contentStream.restoreGraphicsState();
            contentStream.beginText();
            contentStream.setFont( font, 12 );

            contentStream.newLine();
            contentStream.moveTextPositionByAmount(327,820);
            contentStream.showText("code");

            contentStream.newLine();
            contentStream.moveTextPositionByAmount(60,0);
            contentStream.showText("mode");

            contentStream.newLine();
            contentStream.moveTextPositionByAmount(80,0);
            contentStream.showText("Date");

            contentStream.newLine();
            contentStream.moveTextPositionByAmount(-160,-20);
            contentStream.showText(billNO);

            contentStream.newLine();
            contentStream.moveTextPositionByAmount(80,0);
            contentStream.showText("Credit");

            contentStream.newLine();
            contentStream.moveTextPositionByAmount(40,0);
            contentStream.showText(billingDate);

            contentStream.newLine();
            contentStream.moveTextPositionByAmount(-170,-30);
            contentStream.setFont(boldFont,14);
            contentStream.setNonStrokingColor(new Color(255, 153,0));
            contentStream.showText((String) ((Map)parties.getPartiesData().get(vender)).get("nameOfTheParty"));

            contentStream.setFont(font,9);
            contentStream.setNonStrokingColor(new Color(0, 0,0));
            contentStream.showText("("+ ((Map)parties.getPartiesData().get(vender)).get("suffixOfTheParty") + ")");

            contentStream.newLine();
            contentStream.setFont(font,8);
            contentStream.moveTextPositionByAmount(10,-15);
            contentStream.showText(((String) ((Map)parties.getPartiesData().get(vender)).get("address")));

            contentStream.newLine();
            contentStream.setFont(font,10);
            contentStream.moveTextPositionByAmount(-10,-20);
            contentStream.showText("cell: " + ((Map)parties.getPartiesData().get(vender)).get("cell") + ",   PAN: " +
                    ((Map)parties.getPartiesData().get(vender)).get("pan"));

            contentStream.newLine();
            contentStream.setFont(font,10);
            contentStream.moveTextPositionByAmount(225,-20);
            contentStream.showText(billType);

            int tableInitialXPosition = -445;
            int tableInitialYPosition = -70;
            int tableHeight = 5;
            int tableWidth = 6;
            int tableXPosition = 0;
            int tableYPosition = 0;
            for(int i =itemData.length, item = 0; i>0 ;i--,item++){
                contentStream.newLine();
                contentStream.setFont(font,14);
                for (int j =itemData[item].length, itemRow = 0; j>0 ;j--, itemRow++){
                    if (j == 6) contentStream.moveTextPositionByAmount(tableInitialXPosition,tableInitialYPosition);
                    else contentStream.moveTextPositionByAmount(tableXPosition,tableYPosition);

                    contentStream.showText(String.valueOf(itemData[item][itemRow]));
                    if (j==6) tableXPosition += 50;
                    else if (j==5) tableXPosition += 90;
                    else if (j==4) tableXPosition -= 90;
                    else if (j==3) tableXPosition += 30;
                    else if (j==2) tableXPosition += 0;
                    else tableXPosition += 20;
                }
                tableInitialXPosition = -400;
                tableInitialYPosition = -20;
                tableYPosition =0;
                tableXPosition =0;
            }

            contentStream.newLine();
            contentStream.moveTextPositionByAmount(-360,-50);
            contentStream.setFont(font, 8);
            contentStream.showText("LORRY ADVANCE FOR "+ lorryNumber);

            contentStream.setFont(font,14);
            contentStream.moveTextPositionByAmount(350,0);
            contentStream.showText(String.valueOf(Float.parseFloat(lorryAdvance)));

            contentStream.endText();

            contentStream.beginText();
            tableInitialXPosition = 57;
            tableInitialYPosition = 420;
            tableHeight = 4;
            tableWidth = 4;
            tableXPosition = 0;
            tableYPosition = 0;

            for (int i = bankData.length,bankRows = 0;i >0;i--,bankRows++){
                contentStream.newLine();
                contentStream.setFont(font,8);
                for (int j = bankData[bankRows].length,bankColumns=0;j>0;j--,bankColumns++){
                    if (j == 4) contentStream.moveTextPositionByAmount(tableInitialXPosition,tableInitialYPosition);
                    else contentStream.moveTextPositionByAmount(tableXPosition,tableYPosition);

                    contentStream.showText(String.valueOf(bankData[bankRows][bankColumns]));
                    if (j==4) tableXPosition += 40;
                    else if (j==3) tableXPosition += 65;
                    else if (j==2) tableXPosition -= 65;
                }
                tableInitialXPosition = -185;
                tableInitialYPosition = -20;
                tableYPosition =0;
                tableXPosition =0;
            }
            contentStream.endText();
            NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
            format.setMinimumFractionDigits(2);
            format.setMaximumFractionDigits(2);

            contentStream.beginText();
            contentStream.setFont(font,12);
            contentStream.newLineAtOffset(450,455.5f);
            contentStream.showText(format.format(Float.parseFloat(subTotal)));

            contentStream.newLineAtOffset(0,-26);
            contentStream.showText(format.format(Float.parseFloat(previousDue)));

            contentStream.newLineAtOffset(0,-26);
            contentStream.showText(format.format(Float.parseFloat(!Objects.equals(lessCreditAmount, "") ? lessCreditAmount : "0")));

            contentStream.newLineAtOffset(0,-39);
            contentStream.showText(format.format(Float.parseFloat(totalAmount)));

            contentStream.newLineAtOffset(-345,-15);
            int index = totalAmount.indexOf(".");
            contentStream.showText(EnglishNumberToWords.convert(Long.parseLong(totalAmount.split("\\.")[0])));

            contentStream.newLineAtOffset(15,-125);
            contentStream.setFont(font,18);
            format = NumberFormat.getNumberInstance(new Locale("en","IN"));
            contentStream.showText(format.format(Float.parseFloat(totalAmount)));

            contentStream.endText();
            contentStream.close();
            document.save(home + separator +  "Downloads" + separator + "invoice-"+ billType +".pdf");
            System.out.println(home + separator + "Downloads");

        } finally {
            if (document != null){
                document.close();
            }
        }
    }
}
