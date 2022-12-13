import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCodeAnalyser {

    public static  int LoadIndianCodeData(String csvPath) throws IOException {
        Reader reader;
        reader = Files.newBufferedReader(Paths.get(csvPath));

        CsvToBean <IndianCodeCSV> csvToBean = new CsvToBeanBuilder(reader)
                .withType(IndianCodeCSV.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        Iterator<IndianCodeCSV> codeCSVIterator = csvToBean.iterator();;
        int entries = 0;

        while(codeCSVIterator.hasNext()) {
            entries++;
            IndianCodeCSV codeData = codeCSVIterator.next();
        }
        System.out.println(entries);
        return entries;
    }

    public static void main(String[] args) throws IOException {
        String csvPath = "C:\\Users\\Ibrahim\\IdeaProjects\\State\\target\\IndiaStateCode.csv";
        LoadIndianCodeData(csvPath);
    }
}
