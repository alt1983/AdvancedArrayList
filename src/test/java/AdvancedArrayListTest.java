
import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Comparator;


class AdvancedArrayListTest {

    AdvancedArrayList<Integer> testArray = new AdvancedArrayList<>();

    @Test
    void addTest(){
        Integer testValue = 1;

        Boolean result = testArray.add(testValue);
        Assertions.assertTrue(result);
    }

    @Test
    void addWithIndexTest(){
        Integer testValue = 1;

        testArray.add(0,testValue);
        Assertions.assertEquals(testArray.get(0),testValue);
    }

    @Test
    void addWithIndexIndexOutOfBoundsExceptionTest(){
        Boolean errorIndexOutOfBoundsException = false;
        Integer testValue = 1;

        try {
            testArray.add(100,testValue);
        } catch (IndexOutOfBoundsException e) {
            errorIndexOutOfBoundsException = true;
        }
        Assertions.assertTrue(errorIndexOutOfBoundsException);
    }


    @Test
    void getTest(){
        Integer testValue = 1;

        testArray.add(testValue);
        Assertions.assertEquals(testArray.get(0),testValue);
    }

    @Test
    void getTestIndexOutOfBoundsException(){
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> testArray.get(0));
    }

    @Test
    void setTest(){
        Integer oldTestValue = 1;
        Integer newTestValur = 2;

        testArray.add(oldTestValue);
        Assertions.assertEquals(testArray.set(0,newTestValur),oldTestValue);
    }

    @Test
    void setTestIndexOutOfBoundsException(){
        Boolean errorIndexOutOfBoundsException = false;

        Integer testValue = 1;
        try {
            Integer res = testArray.set(-1,testValue);
        } catch (IndexOutOfBoundsException e) {
            errorIndexOutOfBoundsException = true; // assertThrows
        }
        Assertions.assertTrue(errorIndexOutOfBoundsException);
    }

    @Test
    void removeByIndexTest(){
        Integer testValue = 1;

        testArray.add(testValue);
        Assertions.assertEquals(testArray.remove(0),testValue);
    }

    @Test
    void removeByIndexTestIndexOutOfBoundsException(){
        Boolean errorIndexOutOfBoundsException = false;

        try {
            Integer res = testArray.remove(0);
        } catch (IndexOutOfBoundsException e) {
            errorIndexOutOfBoundsException = true;// assertThrows
        }
        Assertions.assertTrue(errorIndexOutOfBoundsException);
    }

    @Test
    void removeByObjectTest(){
        Integer testValue = 1;

        testArray.add(testValue);
        Assertions.assertTrue(testArray.remove(testValue));
    }

    @Test
    void removeByObjectTestNullPointerException(){
        Boolean errorNullPointerException = false;

        try {
            testArray.remove(null);
        } catch (NullPointerException e) {
            errorNullPointerException = true;
        }// assertThrows
        Assertions.assertTrue(errorNullPointerException);
    }

    @Test
    void clearTest(){
        Integer testValue = 1;

        testArray.add(testValue);
        testArray.clear();
        Assertions.assertEquals(testArray.size(),0);
    }

    @Test
    void sizeTest(){
        Integer testValue = 1;

        testArray.add(testValue);
        Assertions.assertEquals(testArray.size(),1);
    }

    @Test
    void sortTest(){
        Integer smallValue = -50;
        Integer midValue = 10;
        Integer bigValue = 100;

        testArray.add(midValue);
        testArray.add(bigValue);
        testArray.add(smallValue);
        testArray.sort(Comparator.naturalOrder());
        AdvancedArrayList<Integer> sortedArray = new AdvancedArrayList<>();
        sortedArray.add(smallValue);
        sortedArray.add(midValue);
        sortedArray.add(bigValue);
        Assertions.assertEquals(testArray,sortedArray);
    }




}

