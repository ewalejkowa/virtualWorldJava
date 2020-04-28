/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt2;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author Lenovo
 */
public class guarana extends roslina{
    public guarana(){
        znak = 'g';
    }
    public void kolizja(int x, int y, int x2, int y2, swiat t,KeyEvent g,JLabel h){
	organizm f = t.zwrocOrganizm(x, y);
	organizm l = t.zwrocOrganizm(x2, y2);
		f.zwiekszSile();
                h.setText("<html>-Guarana zwiększa sile na poz-"+pol_x+","+pol_y+":"+f.getznak()+"<br>"+h.getText());
 		t.usunto(x, y, x2, y2);
}
    
}
