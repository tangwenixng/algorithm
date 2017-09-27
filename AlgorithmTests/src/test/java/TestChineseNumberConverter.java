import com.sealyu.algorithm.ChineseNumberConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for ChineseNumberConverter
 * @author Haibo Yu on 09/27/2017.
 */
public class TestChineseNumberConverter {

    private static ChineseNumberConverter converter = new ChineseNumberConverter();

    @Test
    public void testSimpleUsingFromEndStrategy() throws Exception{
        String testString = "十";
        long expectedOutput = 10l;
        long realOutput = converter.convertToLongFromEnd(testString);
        assertEquals(realOutput,expectedOutput);

        testString = "十五";
        expectedOutput = 15l;
        realOutput = converter.convertToLongFromEnd(testString);
        assertEquals(realOutput,expectedOutput);

        testString = "廿五";
        expectedOutput = 25l;
        realOutput = converter.convertToLongFromEnd(testString);
        assertEquals(realOutput,expectedOutput);

        testString = "一百零五";
        expectedOutput = 25l;
        realOutput = converter.convertToLongFromEnd(testString);
        assertEquals(realOutput,expectedOutput);

        testString = "八万一千零卅五";
        expectedOutput = 81035l;
        realOutput = converter.convertToLongFromEnd(testString);
        assertEquals(realOutput,expectedOutput);
    }

    @Test
    public void testComplicatedUsingFromEndStrategy() throws Exception{
        String testString = "二十五万五百亿三千零八万一千零卅五";
        long expectedOutput = 25050030081035l;
        long realOutput = converter.convertToLongFromEnd(testString);
        assertEquals(realOutput,expectedOutput);
    }
}
