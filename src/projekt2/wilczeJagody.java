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
public class wilczeJagody extends roslina{
    public wilczeJagody(){
        sila = 99;
        znak ='j';
    }
    public void kolizja(int x, int y, int x2, int y2, swiat t,KeyEvent g, JLabel h){
   
	organizm f = t.zwrocOrganizm(x, y);
	organizm l = t.zwrocOrganizm(x2, y2);
        h.setText("<html>"+f.getznak()+"-Zjada na poz-"+x2+","+y2+":"+l.getznak()+" i umiera<br>"+h.getText());
        t.ilosc_organizmow = t.ilosc_organizmow-2;
        t.organizmy[y][x] = null;
        t.organizmy[y2][x2] = null;			
    }
}
