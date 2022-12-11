import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {

    public String csvPath;
    public StateCensusAnalyser() {}

    public static int LoadIndiaCensusData(String csvPath) throws IOException, CensusAnalyserException   {

        Reader reader;
        try {
            reader = Files.newBufferedReader(Paths.get(csvPath));

            CsvToBean <IndianCensusCSV> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(IndianCensusCSV.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<IndianCensusCSV> censusCSVIterator = csvToBean.iterator();;
            int entries = 0;
            while(censusCSVIterator.hasNext()) {
                entries++;
                IndianCensusCSV censusData = censusCSVIterator.next();
            }
            return entries;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.Csv_File_Problem);
        }catch (IllegalStateException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType. Unable_To_Parse);
        }
    }

}