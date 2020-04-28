/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt2;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
/**
 *
 * @author Lenovo
 */
public class lis extends zwierze{
   public  lis(){
    sila = 3;
    inicjatywa = 7;
    znak = 'L';
}   
    
    
    public void akcja(swiat t,KeyEvent g, JLabel h){
        Random number= new Random();
	int f = 0;
	do {
		f = number.nextInt(4);
	} while (!t.sprawdzRuch(f, pol_x, pol_y));
	int nowyx = pol_x;
	int nowyy = pol_y;
	switch (f)
	{
	case 0:
		nowyy = pol_y - 1;

		break;
	case 1:
		nowyy = pol_y + 1;
		break;
	case 2:
		nowyx = pol_x + 1;
		break;
	case 3:
		nowyx = pol_x - 1;
		break;
	}
	organizm kolizja1 = t.zwrocOrganizm(nowyx, nowyy);
          if(pol_y+1>=t.getsize()|| pol_x+1>=t.getsize()||pol_y-1<0||pol_x-1<0){
            
        }
          else{
	if (kolizja1 == null ){
		t.przesun(pol_x, pol_y, nowyx, nowyy);
		pol_x = nowyx;
		pol_y = nowyy;
                h.setText("<html>"+this.getznak()+"-Wykonał ruch  na poz-"+pol_x+","+pol_y+"<br>"+h.getText());
	}
	else if (kolizja1.getznak() == 'g'){
		kolizja1.kolizja(pol_x, pol_y, nowyx, nowyy, t,g,h);
		pol_x = nowyx;
		pol_y = nowyy;
	}
	else if (kolizja1.getznak() == 'j'){
		kolizja1.kolizja(pol_x, pol_y, nowyx, nowyy,t,g,h);
	}
	else if (kolizja1.getznak() == this.getznak())
	{
		rozmnazanie(pol_x, pol_y, nowyx, nowyy, t,g,h);

	}
	else {
		if (kolizja1.getsila() > this.getsila()){
			this.akcja(t,g,h);
		}
		else if (kolizja1.getsila() <= this.getsila()){
			this.kolizja(pol_x, pol_y, nowyx, nowyy,t,g,h);
		}
        }
	wiek++;
} 
    }
}
