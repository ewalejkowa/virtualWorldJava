/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt2;
import java.util.Random;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author Lenovo
 */

public class antylopa extends zwierze{
public antylopa(){
    sila =4;
    inicjatywa =4;
    znak = 'A';
}
public void akcja(swiat swiat,KeyEvent g,JLabel h){
        Random number = new Random();
	int f = 0;
	do {
		f = number.nextInt(4);
	} while (!this.sprawdzRuchAnty(f, pol_x, pol_y,swiat));
	int nowyx = pol_x;
	int nowyy = pol_y;
	switch (f)
	{
	case 0:
		nowyy = pol_y - 2;

		break;
	case 1:
		nowyy = pol_y + 2;
		break;
	case 2:
		nowyx = pol_x + 2;
		break;
	case 3:
		nowyx = pol_x - 2;
		break;
	}
	organizm kolizja1 = swiat.zwrocOrganizm(nowyx, nowyy);
	if (kolizja1 == null ){
		swiat.przesun(pol_x, pol_y, nowyx, nowyy);
		pol_x = nowyx;
		pol_y = nowyy;
                h.setText("<html>"+this.getznak()+"-Wykonuje ruch na poz-"+pol_x+","+pol_y+"<br>"+h.getText());
	}
	else if (kolizja1.getznak() == 'Z' && this.getsila() <5){
		kolizja1.kolizja(pol_x, pol_y, nowyx, nowyy,swiat,g,h);
                h.setText("<html>"+this.getznak()+"-Zostaje odgoniona przez żółwia na pozycji-"+pol_x+","+pol_y+"<br>"+h.getText());
	}
	else if (kolizja1.getznak() == 'g'){
		kolizja1.kolizja(pol_x, pol_y, nowyx, nowyy,swiat,g,h);
		pol_x = nowyx;
		pol_y = nowyy;
	}
	else if (kolizja1.getznak() == 'j'){
		kolizja1.kolizja(pol_x, pol_y, nowyx, nowyy,swiat,g,h);
	}
	else if (kolizja1.getznak() == this.getznak())
	{
		rozmnazanie(pol_x, pol_y, nowyx, nowyy, swiat,g,h);
	
	}
	else  {
		this.kolizja(pol_x, pol_y, nowyx, nowyy,swiat,g,h);
		pol_x = nowyx;
		pol_y = nowyy;
	}
	wiek++;
}
public boolean sprawdzRuchAnty(int kierunek, int x,int y,swiat swiat){
switch (kierunek){
		case 0:
			if (y - 2 >= 0) return true;//g
			break;
		case 1:
			if (y + 2< swiat.getsize()) return true;//d
			break;
		case 2:
			if (x + 2 < swiat.getsize()) return true;//p
			break;
		case 3:
			if (x - 2 >= 0) return true;//l
			break;
		}
		return false;
}
public void kolizja(int x, int y, int x2, int y2,swiat swiat,KeyEvent g,JLabel h){
	organizm f = swiat.zwrocOrganizm(x, y);
	organizm l = swiat.zwrocOrganizm(x2, y2);
	Random number = new Random();
	int b = 0;
	b = number.nextInt(2);
	switch (b){
	case 0:
		if (f.getsila() >= l.getsila()){
			swiat.usunto(x, y, x2, y2);
			if (l.getsila() == 0){ 
                        h.setText("<html>"+f.getznak()+"-Zjada na poz-"+pol_x+","+pol_y+":"+l.getznak()+"<br>"+h.getText());
			}
			else{
                        h.setText("<html>"+f.getznak()+"-Wygrywa walkę na poz-"+pol_x+","+pol_y+"z:"+l.getznak()+"<br>"+h.getText());
			}
		}

		else {
                        h.setText("<html>"+f.getznak()+"-Przegrywa walkę na poz-"+pol_x+","+pol_y+"z:"+l.getznak()+"<br>"+h.getText());
                        swiat.ilosc_organizmow--;
                        swiat.organizmy[y][x]= null;
		}		
		break;
	case 1:
            h.setText("<html>"+f.getznak()+"-Ucieka-"+pol_x+","+pol_y+"przed:"+l.getznak()+"<br>"+h.getText());
		this.akcja(swiat,g,h);
		break;
	}
}
}
