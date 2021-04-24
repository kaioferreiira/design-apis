package com.example.design.api.geradorpdf;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.List;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class GeradorPDF {

    public static void main(String[] args) {

//        https://www.botecodigital.dev.br/java/gerando-pdf-em-java-com-a-biblioteca-openpdf/

        try {

            criaPDFBasico();

            criaPDFComParagrafo();

            criaPDFcomListaEnumerada();

            criaPDFcomTabelas();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void criaPDFcomTabelas() throws FileNotFoundException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("openpdf4.pdf"));
        document.open();

        PdfPTable table = new PdfPTable(4);

        PdfPCell cellHeader = new PdfPCell(new Paragraph("Linha de cabeçaçho"));
        cellHeader.setColspan(4);
        table.addCell(cellHeader);

        table.addCell("1 - 1");
        table.addCell("1 - 2");
        table.addCell("1 - 3");
        table.addCell("1 - 4");

        table.addCell("2 - 1");
        table.addCell("2 - 2");
        table.addCell("2 - 3");
        table.addCell("2 - 4");

        PdfPCell cell = new PdfPCell(new Paragraph("Celula 1"));
        cell.setBorderColor(new Color(255, 0, 0));
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Celula 2"));
        cell.setColspan(3);
        cell.setBackgroundColor(new Color(192, 192, 192));
        cell.setHorizontalAlignment( PdfPCell.ALIGN_CENTER);
        table.addCell(cell);

        document.add(table);

        document.close();
    }

    private static void criaPDFcomListaEnumerada() throws FileNotFoundException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("openpdf3.pdf"));
        document.open();

        //marcador normal
        List list = new List();
        list.add("Item 1");
        list.add("Item 2");
        list.add("Item 3");
        list.add("Item 4");
        document.add(list);

        document.add( Chunk.NEWLINE ); // nova linha

        //marcador numérico ordenado numero
        List listOrderedNumero = new List(List.ORDERED,List.NUMERICAL);
        listOrderedNumero.add("Item 1");
        listOrderedNumero.add("Item 2");
        listOrderedNumero.add("Item 3");
        listOrderedNumero.add("Item 4");
        document.add( listOrderedNumero );

        document.add( Chunk.NEWLINE ); // nova linha

        //marcador numérico ordenado letra
        List listOrderedLetra = new List(List.ORDERED,List.ALPHABETICAL);
        listOrderedLetra.add("Item 1");
        listOrderedLetra.add("Item 2");
        listOrderedLetra.add("Item 3");
        listOrderedLetra.add("Item 4");
        document.add( listOrderedLetra );

        document.add( Chunk.NEWLINE ); // nova linha

        //marcador com simbolo
        List listItem = new List();
        listItem.setListSymbol("\u001A");

        listItem.add("Item 1");
        listItem.add("Item 2");
        listItem.add("Item 3");
        listItem.add("Item 4");
        document.add(listItem);

        document.add( Chunk.NEWLINE ); // nova linha

        document.close();
    }

    private static void criaPDFBasico() throws IOException {
        //Criando documento basico
//        Document document = new Document( PageSize.A4, 10,10,10,10 ); // tamanho da página A4 e 10 de margem
        Document document = new Document();
        //gera instacia, neste caso vai criar um pdf na raiz
        // Usando  o file outputstream
        //ideia é criar o objeto e enviar para uma saida externa. outputstream
        //criar uma pasta temporaria para armazenar o pdf e gerar o base64
        FileOutputStream fileOutputStream = new FileOutputStream("openpdf.pdf");
        PdfWriter.getInstance(document, fileOutputStream);
        document.open();

        Chunk c = new Chunk("Uma String" );
        Font fontChunk = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLDITALIC);
        c.setFont(fontChunk);
        document.add(c);
        document.close();

        //encode
        byte[] inFileBytes = Files.readAllBytes(Paths.get("openpdf.pdf"));
//        byte[] encoded = java.util.Base64.getEncoder().encode(inFileBytes);
        String encodeToString = Base64.getEncoder().encodeToString(inFileBytes);


        //decode
        byte[] decoded = java.util.Base64.getDecoder().decode(encodeToString);
        FileOutputStream fos = new FileOutputStream("testeAquivoEncode.pdf");
        fos.write(decoded);
        fos.flush();
        fos.close();


        }




    private static void criaPDFComParagrafo() throws FileNotFoundException {
        Document documentComParagrafo = new Document();
        PdfWriter.getInstance(documentComParagrafo, new FileOutputStream("openpdf2.pdf"));
        documentComParagrafo.open();

        Font font = FontFactory.getFont(FontFactory.TIMES, 16, Font.NORMAL);

        Paragraph p1 = new Paragraph("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris cursus velit ligula, eu pretium odio sodales nec. Nulla aliquam posuere leo ac sollicitudin. Vestibulum neque magna, auctor a ex et, gravida vestibulum sapien." , font   );
        p1.setSpacingAfter(10);
        p1.setFirstLineIndent(30);
        documentComParagrafo.add( p1 );

        Paragraph p2 = new Paragraph("Proin sed justo aliquam, ornare justo at, ultricies sapien. Vestibulum sit amet lorem vel velit bibendum elementum. Integer euismod in sapien a rutrum. Mauris viverra odio in efficitur semper. Nullam eu leo rhoncus, vehicula tellus nec, placerat enim." , font   );
        p2.setSpacingAfter(10);
        p2.setAlignment(ElementTags.ALIGN_CENTER);
        documentComParagrafo.add( p2 );

        Paragraph p3 = new Paragraph("Duis mattis fermentum nulla, sed egestas ante iaculis vel. Vestibulum ac felis ac ipsum euismod consectetur. Vivamus sit amet turpis eget magna tempus malesuada in at ex." , font   );
        p3.setSpacingAfter(10);
        documentComParagrafo.add( p3 );

        documentComParagrafo.close();
    }
}
