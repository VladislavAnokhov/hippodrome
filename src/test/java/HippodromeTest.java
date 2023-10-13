import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {
    @Test
    public void testConstructorWithNull(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()-> new Hippodrome(null));

        assertEquals("Horses cannot be null." , e.getMessage());
    }@Test
    public void testConstructorWithEmptyArray(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()-> new Hippodrome(new ArrayList<>()));

        assertEquals("Horses cannot be empty." , e.getMessage());
    }
    @Test
    public void getHorses (){
        List <Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse(""+i,i,i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses,hippodrome.getHorses());
    }
    @Test
public void testOfMove (){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }

        new Hippodrome(horses).move();

        for (Horse horse : horses){
            verify(horse,atLeastOnce()).move();
        }
    }
@Test
    public void getWinnerTest() {
       Horse horse=new Horse("qwee",1,1);
       Horse horse1=new Horse("qwe1",1,2);
       Horse horse2=new Horse("qwe2",1,3);
       Horse horse3=new Horse("qwe3",1,4);
       Hippodrome hippodrome = new Hippodrome(List.of(horse,horse1,horse2,horse3));

       assertSame(horse3,hippodrome.getWinner());
    }

}