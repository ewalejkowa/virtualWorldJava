/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt2;
import java.awt.event.*;
import java.util.Random;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;
import javax.swing.JPanel;
import javax.swing.*;

public class swiat extends JPanel{
    protected int ilosc_organizmow=30;
    private int size;
    public organizm [][]organizmy;
    private Comparator<organizm> comparator = new newComparator();
    private Queue<organizm> kolejka = new PriorityQueue<organizm>(comparator);
    public int getsize(){
    return size;
}
    public swiat(){
       size =20;
       organizmy= new organizm[size][size];
       dodajOrganizmy();

    }
   void dodajOrganizmy(){           //dodawanie organizmow na losowych miejscach
        Random number = new Random();
      for (int i = 0; i < ilosc_organizmow && i < size*size; i++){
		int f, g;     
		do {
                     f = number.nextInt(size);
                     g = number.nextInt(size);
		} while (organizmy[f][g]!=null);

		int numerOrganizmu = number.nextInt(9);
		switch (numerOrganizmu){
		case 0: organizmy[f][g] = new wilk(); kolejka.add(organizmy[f][g]);break;
		case 1: organizmy[f][g] = new owca(); kolejka.add(organizmy[f][g]); break;
		case 2: organizmy[f][g] = new antylopa(); kolejka.add(organizmy[f][g]); break;
		case 3: organizmy[f][g] = new lis();kolejka.add(organizmy[f][g]); break;
		case 4: organizmy[f][g] = new zolw();  kolejka.add(organizmy[f][g]); break;
		case 5: organizmy[f][g] = new guarana();   kolejka.add(organizmy[f][g]); break;
		case 6: organizmy[f][g] = new mlecz();   kolejka.add(organizmy[f][g]); break;
		case 7: organizmy[f][g] = new trawa();  kolejka.add(organizmy[f][g]); break;
		case 8: organizmy[f][g] = new wilczeJagody(); kolejka.add(organizmy[f][g]); break;
		}
		organizmy[f][g].pol_x = g;
                organizmy [f][g].pol_y = f;
	}
	int c, k;
	do {
		c = number.nextInt(size);
		k = number.nextInt(size);
	} while (organizmy[c][k]!=null);
	organizmy[c][k] = new czlowiek();
	organizmy[c][k].pol_x = k;
        organizmy[c][k].pol_y = c;
	kolejka.add(organizmy[c][k]) ;  
    }
void rysujSwiat1(){                              // rysowanie świata w konsoli
	for (int i = 0; i < size; ++i)
	{
		for (int j = 0; j < size; ++j){
			if (organizmy[i][j] == null){
				System.out.print("_") ;
                                System.out.print(" ") ;
			}
			else{
				System.out.print(organizmy[i][j].znak) ;
			}
		}
		System.out.println() ;
	}

}
void kolejka(){                                         // sprawdzenie poprawności kolejki
    for (int i=0; i<ilosc_organizmow; i++){
    System.out.println(kolejka.poll().getinicjatywa()) ;
    }
}
void wykonaj_ture(KeyEvent g,JLabel h){
    
       for (int i=0; i<kolejka.size(); i++){            
           organizm tmp =  kolejka.poll();
           if (tmp!=null){   
               if (tmp == organizmy[tmp.pol_y][tmp.pol_x]){
                            tmp.akcja(this,g,h); 
               }
           }
           else {
               
           }
       }
    	for (int k = 0; k < size; ++k)
	{
		for (int j = 0; j < size; ++j){
			if (organizmy[k][j] == null){
			}
			else{
				kolejka.add(organizmy[k][j]) ;
			}
		}
	
    } 
}
boolean sprawdzRuch(int kierunek, int pol_x, int pol_y){
  
	switch (kierunek){
	case 0:
		if (pol_y-1 >= 0) return true;//g
		break;
	case 1:
		if (pol_y +1< size) return true;//d
		break;
	case 2:
		if (pol_x +1 < size) return true;//p
		break;
	case 3:
		if (pol_x-1  >= 0) return true;//l
		break;
        default:
                return true;
	//break;
	}	
	return false;
}  
organizm zwrocOrganizm(int pol_x, int pol_y){
    return organizmy[pol_y][pol_x];
}
public void przesun(int x, int y, int nowyx, int nowyy){
	organizmy[nowyy][nowyx]= organizmy[y][x];
	organizmy[y][x] = null;	
}
public void usunto(int x, int y, int x2, int y2){
	 organizmy[y2][x2] = null;
	 przesun(x, y, x2, y2); 
         ilosc_organizmow--;
      
}
public void dodajOrganizm( char znak,int potx, int poty ,int wiek)
{
	switch (znak){
	case 'W': organizmy[poty][potx] = new wilk(); break;
	case 'A': organizmy[poty][potx] = new antylopa(); break;
	case 'O': organizmy[poty][potx] = new owca();  break;
	case 'L': organizmy[poty][potx] = new lis();  break;
	case 'Z': organizmy[poty][potx] = new zolw();  break;
	case 't': organizmy[poty][potx] = new trawa();  break;
	case 'm': organizmy[poty][potx] = new mlecz();  break;
	case 'g': organizmy[poty][potx] = new guarana();  break;
	case 'j': organizmy[poty][potx] = new wilczeJagody();  break;
        case 'C':organizmy[poty][potx] = new czlowiek(); break;
	}
        ilosc_organizmow++;
        organizmy[poty][potx].pol_x = potx;
        organizmy [poty][potx].pol_y = poty;
        organizmy [poty][potx].wiek = wiek;
}
boolean CzlowiekRuch(int kierunek, int x, int y){
	if (kierunek == KeyEvent.VK_DOWN){// dol
		if (y + 1 < size){
			return true;
		}

	}
	else if (kierunek == KeyEvent.VK_UP){
		// gora
		if (y - 1>= 0){
			return true;
		}

	}
	else if (kierunek == KeyEvent.VK_LEFT){
		// lewo
		if (x - 1>= 0){
			return true;
		}

	}
	else if (kierunek == KeyEvent.VK_RIGHT){
		// prawo
		if (x + 1 < size){
			return true;
		}

	}

	return false;
}
}

