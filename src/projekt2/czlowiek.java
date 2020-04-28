/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt2;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

/**
 *
 * @author Lenovo
 */
public class czlowiek extends zwierze{
    private int licznik =0;
    public czlowiek(){
       inicjatywa = 4;
       sila = 5;
       znak = 'C';
    }

    public void zmniejsz(){
      licznik--;
    }
public void akcja(swiat swiat,KeyEvent g,JLabel h){

    if (g.getKeyChar()=='t'|| g.getKeyCode()==KeyEvent.VK_RIGHT||g.getKeyCode()==KeyEvent.VK_UP||g.getKeyCode()==KeyEvent.VK_LEFT||g.getKeyCode()==KeyEvent.VK_DOWN){
	Random number = new Random();	
                if (licznik == 0){                     
			if (g.getKeyChar() == 't'){
				licznik = 10;
			}
		}
    
		if (licznik > 5 && licznik >7){
			sp(g,swiat,h);
		}
		else if (licznik > 5 && licznik <= 7){
			
			int nowy = number.nextInt(2);
			switch (nowy){
			case 0:
				sp(g,swiat,h);
				break;

			case 1:
				zmniejsz();				
				break;
			}
		}
		 if (licznik <= 5 ){
			int nowyx = pol_x;
			int nowyy = pol_y;
			int kierunek = 0;
                        kierunek = g.getKeyCode();
			if(swiat.CzlowiekRuch(kierunek, pol_x, pol_y)==true){
			switch (kierunek){

			case KeyEvent.VK_DOWN: // gora
				nowyy = pol_y + 1;
				break;
			case KeyEvent.VK_UP: // dol
				nowyy = pol_y - 1;
				break;
			case KeyEvent.VK_RIGHT: // prawo
				nowyx = pol_x + 1;
				break;
			case KeyEvent.VK_LEFT: // lewo
				nowyx = pol_x - 1;
				break;
			}
			organizm kolizja1 = swiat.zwrocOrganizm(nowyx, nowyy);


			if (kolizja1 == null){
				swiat.przesun(pol_x, pol_y, nowyx, nowyy);
				pol_x = nowyx;
				pol_y = nowyy;
                                h.setText("<html>"+this.getznak()+"-Wykonuje ruch na poz-"+pol_x+","+pol_y+"<br>"+h.getText());

			}
			else if (kolizja1.getznak() == 'j'){
				kolizja1.kolizja(pol_x, pol_y, nowyx, nowyy,swiat,g,h);
			}
			else if (kolizja1.getznak() == 'g'){
				kolizja1.kolizja(pol_x, pol_y, nowyx, nowyy,swiat , g,h);
				pol_x = nowyx;
				pol_y = nowyy;
			}
			else {
				this.kolizja(pol_x, pol_y, nowyx, nowyy,swiat,g,h);

			}
                 }
			if (licznik > 0){
				zmniejsz();
			}
		}
                 else{}
    }
    
    
    else{}
		 wiek++;
}
public void kolizja(int x, int y, int x2, int y2,swiat swiat,KeyEvent g, JLabel p){
	organizm f = swiat.zwrocOrganizm(x, y);
	organizm l = swiat.zwrocOrganizm(x2, y2);
	if (f.getsila() >= l.getsila()){
		swiat.usunto(x, y, x2, y2);
		if (l.getsila() == 0){
                        p.setText("<html>"+f.getznak()+"-Zjada na poz-"+pol_x+","+pol_y+":"+l.getznak()+"<br>"+p.getText());
                        swiat.organizmy[y2][x2].pol_x=x2;
                        swiat.organizmy[y2][x2].pol_y=y2;

		}
		else{
                        p.setText("<html>"+f.getznak()+"-Wygrywa walkę na poz-"+pol_x+","+pol_y+"z:"+l.getznak()+"<br>"+p.getText());
                        swiat.organizmy[y2][x2].pol_x=x2;
                        swiat.organizmy[y2][x2].pol_y=y2;
		}
	}
	else {
                p.setText("<html>"+f.getznak()+"-Przegrywa walkę na poz-"+pol_x+","+pol_y+"z:"+l.getznak()+"<br>"+p.getText());
                swiat.organizmy[y][x]= null;
                swiat.ilosc_organizmow--;

	}
}
void sp(KeyEvent g, swiat swiat, JLabel h){
    if ( g.getKeyCode()==KeyEvent.VK_RIGHT||g.getKeyCode()==KeyEvent.VK_UP||g.getKeyCode()==KeyEvent.VK_LEFT||g.getKeyCode()==KeyEvent.VK_DOWN){
	int nowyx = pol_x;
	int nowyy = pol_y;
	int kierunek = 0;
		kierunek = g.getKeyCode();
	if(sprawdzRuchcz(kierunek, pol_x, pol_y,swiat)==true){
		switch (kierunek){

			case KeyEvent.VK_DOWN: // gora
				nowyy = pol_y + 2;
				break;
			case KeyEvent.VK_UP: // dol
				nowyy = pol_y - 2;
				break;
			case KeyEvent.VK_RIGHT: // prawo
				nowyx = pol_x + 2;
				break;
			case KeyEvent.VK_LEFT: // lewo
				nowyx = pol_x - 2;
				break;
			}
	organizm kolizja1 = swiat.zwrocOrganizm(nowyx, nowyy);
			if (kolizja1 == null){
				swiat.przesun(pol_x, pol_y, nowyx, nowyy);
				pol_x = nowyx;
				pol_y = nowyy;
                                h.setText("<html>"+this.getznak()+"-Wykonuje ruch na poz-"+pol_x+","+pol_y+"<br>"+h.getText());

			}
			else if (kolizja1.getznak() == 'j'){
				kolizja1.kolizja(pol_x, pol_y, nowyx, nowyy,swiat,g,h);
			}
			else if (kolizja1.getznak() == 'g'){
				kolizja1.kolizja(pol_x, pol_y, nowyx, nowyy,swiat , g,h);
				pol_x = nowyx;
				pol_y = nowyy;
			}
			else {
				this.kolizja(pol_x, pol_y, nowyx, nowyy,swiat,g,h);

			}
	zmniejsz();
        }
        else{}
    }
    else{}
	wiek++;
}
boolean sprawdzRuchcz(int kierunek, int x, int y, swiat swiat){
	if (kierunek == KeyEvent.VK_DOWN){// dol
		if (y + 2 < swiat.getsize()){
			return true;
		}

	}
	else if (kierunek == KeyEvent.VK_UP){
		// gora
		if (y - 2>= 0){
			return true;
		}

	}
	else if (kierunek == KeyEvent.VK_LEFT){
		// lewo
		if (x - 2>= 0){
			return true;
		}

	}
	else if (kierunek == KeyEvent.VK_RIGHT){
		// prawo
		if (x + 2 < swiat.getsize()){
			return true;
		}

	}

	return false;
}
}

