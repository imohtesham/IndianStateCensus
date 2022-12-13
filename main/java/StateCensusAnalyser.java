import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;

public class StateCensusAnalyser {

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
                IndianCensusCSV cd = censusCSVIterator.next();
            }
            return entries;
            //TC 2
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.Csv_File_Problem);
            //TC 3-5
        }catch (IllegalStateException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType. Unable_To_Parse);
        }
    }
    public boolean LoadIndiaCensusCSVData(String csvPath) throws CensusAnalyserException, IOException {

        CSVReader reader;
        try {
            reader = new CSVReader(new FileReader(csvPath));

            while(reader.readNext() != null) {
                return true;
            }
            if(reader != null) {
                reader.close();
            }
        }catch(IllegalStateException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType. Unable_To_Parse);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}