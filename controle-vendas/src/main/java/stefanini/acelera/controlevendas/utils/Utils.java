package stefanini.acelera.controlevendas.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import stefanini.acelera.controlevendas.controllers.ProductController;
import stefanini.acelera.controlevendas.service.ProductService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    @Autowired
    private static ProductService productService;

    public static String  searchCep(String cep) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(
                URI.create("https://viacep.com.br/ws/" + cep + "/json/")
        ).build();

        try {
            HttpResponse<String> response = httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<ItemCsv> leitorCsv() {
            try {
                List<String> listInf = new ArrayList<>();
                List<ItemCsv> listItemCsv = new ArrayList<>();

                File file = new File("C:\\Users\\andrew.stefanini\\Documents\\AceleraDevs\\dadoVendas.xlsx");
                FileInputStream fis = new FileInputStream(file);

                // Crie um workbook a partir do arquivo xlsx
                Workbook workbook = new XSSFWorkbook(fis);

                // Obtenha a primeira planilha do workbook
                Sheet sheet = workbook.getSheetAt(0);

                NumberFormat numberFormat = NumberFormat.getInstance();
                numberFormat.setGroupingUsed(false);
                numberFormat.setMaximumFractionDigits(0);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                // Iterar sobre as linhas da planilha
                for (Row row : sheet) {
                    // Iterar sobre as células de cada linha
                    for (Cell cell : row) {
                        String cellValue = "";
                        // Verificar o tipo da célula e converter para string
                        switch (cell.getCellType()) {
                            case STRING:
                                cellValue = cell.getStringCellValue();
                                break;
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    cellValue = dateFormat.format(cell.getDateCellValue());
                                } else {
                                    cellValue = numberFormat.format(cell.getNumericCellValue());
                                }
                                break;
                            case BOOLEAN:
                                cellValue = String.valueOf(cell.getBooleanCellValue());
                                break;
                            case BLANK:
                                cellValue = "";
                                break;
                            default:
                                cellValue = "DEFAULT";
                        }
                        listInf.add(cellValue);
                    }
                    ItemCsv itemCsv = new ItemCsv(listInf.get(0), listInf.get(1), listInf.get(2), listInf.get(3));
                    listItemCsv.add(itemCsv);
                    listInf.clear();
                }
                workbook.close();
                fis.close();
                return listItemCsv;
            } catch (IOException e) {
                return null;
            }

    }
}
