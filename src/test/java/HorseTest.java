import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;


import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;


@ExtendWith(MockitoExtension.class)
class HorseTest {
    @Test
    public void testConstructorWithNullFirst(){
        assertThrows(IllegalArgumentException.class,()-> new Horse(null,11));
    }

   @Test
    public void nullNameMassage(){
     try {
         new Horse(null,1,1);
     }
        catch (IllegalArgumentException e){
            assertEquals("Name cannot be null.",e.getMessage());
        }
   }

    @ParameterizedTest
    @ValueSource(strings = {"","  ","\t\t","\n\n"})
    public void blankNameException (String name){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,() -> new Horse(name,1,1));

            assertEquals("Name cannot be blank.",e.getMessage());
    }

    @Test
    public void secondParametrIsMinus (){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,() ->new  Horse ("test",-1,1));

        assertEquals("Speed cannot be negative.",e.getMessage());
    }

    @Test
    public void thirdParametrIsMinus (){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,() ->new  Horse ("test",11,-1));

        assertEquals("Distance cannot be negative.",e.getMessage());
    }

    @Test
    public void getName() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("qwerty",1,1);
        Field name = Horse.class.getDeclaredField("name");
        name.setAccessible(true);
        String nameValue=(String) name.get(horse);
        assertEquals("qwerty",nameValue);
    }

    @Test
    public void getSpeed (){
        double expectedSpeed =443;
        Horse horse = new Horse("qwerty", expectedSpeed,1);
        assertEquals(expectedSpeed,horse.getSpeed());
    }

    @Test
    public void getDistance (){
        double expectedDistance =443;
        Horse horse = new Horse("qwerty", 1,expectedDistance);
        assertEquals(expectedDistance,horse.getDistance());
    }
    @Test
    public void zeroDistanceByDefault(){
        Horse horse = new Horse("qwerty",1);
        assertEquals(0,horse.getDistance());
    }
    @Test
    public void  moveUsesGetRandom (){

       try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            new Horse("qwe",21,23).move();

            mockedStatic.verify(()->Horse.getRandomDouble(0.2,0.9));
        }

    }
   @ParameterizedTest
    @ValueSource(doubles = {0.1,0.2,0.5,0.9,1.0,999.999,0.0})
    void move(double random ){
        try (MockedStatic<Horse> mockedStatic =mockStatic(Horse.class)){
            Horse horse = new Horse("qwerty",31,206.1);
            mockedStatic.when(()->Horse.getRandomDouble(0.2,0.9)).thenReturn(random);

            horse.move();

            assertEquals(206.1+31*random,horse.getDistance());
            }
        }

}