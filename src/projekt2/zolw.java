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
public class zolw extends zwierze{
   public zolw(){
       sila= 2;
       inicjatywa =1;
       znak = 'Z';
   } 

   public void akcja(swiat swiat,KeyEvent g,JLabel h){
       Random number = new Random();
	int f = 0;
	do {
		f = number.nextInt(16);
	} while (!swiat.sprawdzRuch(f, pol_x, pol_y));
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
	case 4:
		nowyx = pol_x;
		break;
	case 5:
		nowyx = pol_x;
		break;
	case 6:
		nowyy = pol_y;
		break;
	case 7:
		nowyy = pol_y;
		break;
	case 8:
		nowyx = pol_x;
		break;
	case 9:
		nowyx = pol_x;
		break;
	case 10:
		nowyy = pol_y;
		break;
	case 11:
		nowyy = pol_y;
		break;
	case 12:
		nowyx = pol_x;
		break;
	case 13:
		nowyx = pol_x;
		break;
	case 14:
		nowyy = pol_y;
		break;
	case 15:
		nowyy = pol_y;
		break;
	}

	organizm kolizja1 = swiat.zwrocOrganizm(nowyx, nowyy);
	if (kolizja1 == null ){
		swiat.przesun(pol_x, pol_y, nowyx, nowyy);
		pol_x = nowyx;
		pol_y = nowyy;
	}
	else if (nowyy == pol_y&& nowyx == pol_x){
	
	}
	else if (kolizja1.getznak() == 'g'){
		kolizja1.kolizja(pol_x, pol_y, nowyx, nowyy, swiat,g,h);
		pol_x = nowyx;
		pol_y = nowyy;
	}
	else if (kolizja1.getznak() == 'j'){
		kolizja1.kolizja(pol_x, pol_y, nowyx, nowyy,swiat,g,h);
	}
	else if (kolizja1.getznak() == this.getznak())
	{
		rozmnazanie(pol_x, pol_y, nowyx, nowyy,swiat,g,h);
		
	}
	else {
		this.kolizja(pol_x, pol_y, nowyx, nowyy,swiat,g,h);
	}
wiek++;
}
   public void kolizja(int x, int y, int x2, int y2, swiat swiat,KeyEvent g, JLabel h){
       	organizm f = swiat.zwrocOrganizm(x, y);
	organizm l = swiat.zwrocOrganizm(x2, y2);
	if (l.getsila() <= f.getsila() && f.getsila()<5 && l.getznak() =='Z'){
            h.setText("<html>"+f.getznak()+"-Odgania przeciwnika na poz-"+pol_x+","+pol_y+":"+l.getznak()+"<br>"+h.getText());
	}
	else if (f.getsila()>= l.getsila() && f.getznak() == 'Z'){
		swiat.usunto(x2, y2, x, y);
		if (l.getsila() == 0){
                       h.setText("<html>"+f.getznak()+"-Zjada na poz-"+pol_x+","+pol_y+":"+l.getznak()+"<br>"+h.getText());
		}
		else{
                       h.setText("<html>"+f.getznak()+"-Wygrywa walkę na poz-"+pol_x+","+pol_y+"z:"+l.getznak()+"<br>"+h.getText());
		}
	}
	else {
		swiat.organizmy[y][x]= null;
                h.setText("<html>"+f.getznak()+"-Przegrywa walkę na poz-"+pol_x+","+pol_y+"z:"+l.getznak()+"<br>"+h.getText());
                swiat.ilosc_organizmow--;

	}
   }
}
