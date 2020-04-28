/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt2;

/**
 *
 * @author Lenovo
 */
import java.util.Comparator;

public class newComparator implements Comparator<organizm>
{
    @Override
    public int compare(organizm x, organizm y)
    {

        if (x.inicjatywa < y.inicjatywa)
        {
            return -1;
        }
        if (x.inicjatywa>  y.inicjatywa)
        {
            return 1;
        }
         if(x.inicjatywa ==y.inicjatywa && x.wiek > y.wiek){
             return 1;
         }
        return 0;
    }
}
