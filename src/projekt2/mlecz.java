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
public class mlecz extends roslina{
    public mlecz(){
        znak = 'm';
    }
public void akcja(swiat swiat,KeyEvent g,JLabel h){
    for (int i=0; i<3;i++){
        Random number= new Random();
	int c = number.nextInt(60);
	switch (c){
	case 0:
		int f = 0;
		do {
			f = number.nextInt(4);;
		} while (!swiat.sprawdzRuch(f, pol_x, pol_y));
		int nowyx = pol_x;
		int nowyy = pol_y;
		switch (f)
		{
		case 0:
			nowyy = pol_y-1;

			break;
		case 1:
			nowyy = pol_y+1;
			break;
		case 2:
			nowyx = pol_x+1;
			break;
		case 3:
			nowyx = pol_x-1;
			break;
		}
		char t = this.getznak();
		organizm kolizja1 = swiat.zwrocOrganizm(nowyx, nowyy);
                  if(pol_y+1>=swiat.getsize()||pol_x+1>=swiat.getsize()||pol_y-1<0||pol_x-1<0){
            
        }
                  else{
		if (kolizja1 == null){
			swiat.dodajOrganizm( t, nowyy, nowyx,0);
                       h.setText("<html>"+this.getznak()+"-Zasial nową roslinę na poz-"+nowyx+","+nowyy+"<br>"+h.getText());
		}
                	else if (swiat.zwrocOrganizm(pol_x + 1, pol_y) != null&& swiat.zwrocOrganizm(pol_x, pol_y + 1) != null&&swiat.zwrocOrganizm(pol_x - 1, pol_y) != null&&swiat.zwrocOrganizm(pol_x, pol_y - 1) != null&&
               pol_x + 1<swiat.getsize()||  pol_y + 1<swiat.getsize() ||pol_x - 1>0||pol_y - 1>0 ){// gdy nie ma mieejsca
                        }
	}
		break;
	}
}
}
}
