import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StateCensusAnalyser_test {

    private static final String csvPath ="C:\\Users\\Ibrahim\\IdeaProjects\\State\\target\\IndiaStateCensusData.csv";
    private static final String csvWrongPath ="C:\\Users\\Ibrahim\\IdeaProjects\\State\\target2\\IndiaStateCensusData.csv";
    private static final String pdfPath = "C:\\Users\\Ibrahim\\IdeaProjects\\State\\target\\certificat.pdf";

  // TC 1
    @Test
    public void givenCensusCsvFile_returnCorrectRecords() throws IOException, CensusAnalyserException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        int recordsNumb = stateCensusAnalyser.LoadIndiaCensusData(csvPath);
        assertEquals(29,recordsNumb);
    }

//TC 2
    @Test
    public void given_IndiaCensusData_CsvFile_ShouldThrowException() throws IOException {
        try {
            StateCensusAnalyser censusAnalyZer = new StateCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyZer.LoadIndiaCensusData(csvWrongPath);
        } catch(CensusAnalyserException e) {
            assertEquals(CensusAnalyserException.ExceptionType.Csv_File_Problem, e.type);
        }
    }

   // TC 3
    @Test
    public void given_IndiaCensusData_WithWrongFile_ShouldThrewException() throws IOException {
        try {
            StateCensusAnalyser censusAnalyZer = new StateCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyZer.LoadIndiaCensusData(pdfPath);
        } catch(CensusAnalyserException e) {
            assertEquals(CensusAnalyserException.ExceptionType.Unable_To_Parse, e.type);
        }
    }
    //TC 4-5
    @Test
    public void given_IndiaCensusData_WithWrongDelimeter_ShouldThrowException() throws IOException {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.LoadIndiaCensusCSVData(csvPath);
        } catch(CensusAnalyserException e) {
            assertEquals(CensusAnalyserException.ExceptionType.Unable_To_Parse, e.type);
        }
    }



}