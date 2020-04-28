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
abstract class organizm {
    protected int sila,pol_x, pol_y, wiek, inicjatywa;
    protected char znak;
    protected boolean flaga;
    public organizm(){
        sila=0;
        wiek=0;
        inicjatywa=0;
        flaga = false;
        znak='o';
    }
    public int getwiek(){
        return wiek;
    }
    public int getsila(){
        return sila;
    }
    public  int getinicjatywa(){
        return inicjatywa;
    }
    public char getznak(){
        return znak;
    }
    public int getpol_x(){
        return pol_x;
    }
    public int getpol_y(){
        return pol_y;
    }
    public void akcja(swiat t, KeyEvent g, JLabel h){
    
    }
    public boolean oflaguj(){
    return flaga = true;
    }

    
   public  void kolizja(int x, int y,int x2, int y2,swiat t,KeyEvent g, JLabel h){
	organizm f = t.zwrocOrganizm(x, y);
	organizm l = t.zwrocOrganizm(x2, y2);
	if (f.getsila() >= l.getsila()){
		
		if (l.getsila() == 0){
                    t.usunto(x, y, x2, y2);	
                        h.setText("<html>"+f.getznak()+"-Zjada na poz-"+pol_x+","+pol_y+":"+l.getznak()+"<br>"+h.getText());
                        t.organizmy[y2][x2].pol_x=x2;
                        t.organizmy[y2][x2].pol_y=y2;
		}
              
                else{
                     t.usunto(x, y, x2, y2);
                        h.setText("<html>"+f.getznak()+"-Wygrywa walkę na poz-"+pol_x+","+pol_y+"z:"+l.getznak()+"<br>"+h.getText());
                      t.organizmy[y2][x2].pol_x=x2;
                       t.organizmy[y2][x2].pol_y=y2;                  
		}
	}
	else {
         
                t.organizmy[y][x]=null;
                h.setText("<html>"+f.getznak()+"-Przegrywa walkę na poz-"+pol_x+","+pol_y+"z:"+l.getznak()+"<br>"+h.getText());
                t.ilosc_organizmow--;
	}
}
  int zwiekszSile(){
	return sila = sila + 3;
}
  public void rozmnazanie(int x, int y, int x2, int y2,swiat swiat,KeyEvent g, JLabel p){
	organizm h = swiat.zwrocOrganizm(x, y);
	organizm l = swiat.zwrocOrganizm(x2, y2);
	int potx, poty, potx1, poty1;
	potx = x ;
	poty = y ;
        Random number= new Random();
	int f = 0;
	do {
		f = number.nextInt(4);
	} while (!swiat.sprawdzRuch(f,potx , poty));
	 potx1 = potx;
	 poty1 = poty;
	switch (f)
	{
	case 0:
		poty1 = poty-1;

		break;
	case 1:
		poty1 = poty+1;
		break;
	case 2:
		potx1 = potx+1;
		break;
	case 3:
		potx1 = potx-1;
		break;
	}
        if(y+1>=swiat.getsize()||x+1>=swiat.getsize()||y-1<0||x-1<0){
            
        }
        else{
	char t = l.getznak();
	organizm kolizja1 = swiat.zwrocOrganizm(potx1, poty1);
	if (kolizja1 == null){
		swiat.dodajOrganizm( t, potx1,poty1,0);
                 p.setText("<html>"+this.getznak()+"-Rozmnozyl sie -"+potx+","+poty+"<br>"+p.getText());
	}
	
	else if (swiat.zwrocOrganizm(potx + 1, poty) != null&& swiat.zwrocOrganizm(potx, poty + 1) != null&&swiat.zwrocOrganizm(potx - 1, poty) != null&&swiat.zwrocOrganizm(potx, poty - 1) != null&&
               potx + 1<swiat.getsize()||  poty + 1<swiat.getsize() ||potx - 1>0||poty - 1>0 ){/// gdy jest za mało miejsca

	}
	else  rozmnazanie(x, y, x2, y2,swiat,g,p);
	
        }	
}
}
