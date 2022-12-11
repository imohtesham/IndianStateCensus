import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.Test;

public class StateCensusAnalyser_test {

    private static final String csvPath ="C:\\Users\\Ibrahim\\IdeaProjects\\State\\target\\IndiaStateCensusData.csv";

    @Test
    public void givenCensusCsvFile_returnCorrectRecords() throws IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        int recordsNumb = stateCensusAnalyser.LoadIndiaCensusData(csvPath);
        assertEquals(29,recordsNumb);
    }

}