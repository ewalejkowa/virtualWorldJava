/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt2;
import java.awt.event.KeyListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JPanel;
import java.applet.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Projekt2 extends Applet  implements ActionListener , KeyListener, MouseListener {
  private okienko ramka;
  private panel rysunkowy ;
  private JButton wzorekButton;
  private static swiat t=new swiat();
  private JLabel rezultat;
  private JButton zapis;
  private JButton wczyt;
  private String nazwa = "plik1.txt";

    public static void main(String[] args) {
                           Projekt2 h = new Projekt2();
                           h.zbudujGUI();                       
    }
    public void init( ) 
    {
    ramka.addKeyListener(this); 
    rysunkowy.addMouseListener(this);
    ramka.requestFocus( ); 
    } 
    public void zbudujGUI(){
        
        ramka= new okienko();
        rysunkowy = new panel();  
        rysunkowy.setSize(500,500);
        init();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
         
        
        JPanel panel2 = new JPanel();
	panel2.setLayout(new GridLayout(1,2)); 
        
        JToolBar tb = new JToolBar();
        tb.setLayout(new GridLayout(1,200));
        wzorekButton = new JButton("Rozpocznij nową turę");
        wzorekButton.addActionListener(this);
        tb.add(wzorekButton);
        
        zapis = new JButton("Zapisz stan do pliku");
        zapis.addActionListener(this);
        zapis.setSize(10,10);
        tb.add(zapis);
        
        wczyt = new JButton("Wczytaj stan z pliku");
        wczyt.addActionListener(this);
        wczyt.setSize(10,10);
        tb.add(wczyt);
        
        panel2.add( "South", rysunkowy); 
        rezultat = new JLabel();
        rezultat.setSize(400,20);
            
        panel2.add(rezultat);
        panel2.add("North",panel);
        
        
        ramka.add("Center",panel2);
        ramka.add("North",panel);
        ramka.add("North",tb);
        ramka.setVisible(true);
        wzorekButton.setFocusable(false);
        zapis.setFocusable(false);
        wczyt.setFocusable(false);
    }        


public static void zapisPliku(String nazwa) throws java.io.IOException {
        PrintWriter plikWy = null;
        try {
            // tworzy nowy plik jeżeli nie istnieje, w przeciwnym przypadku
            // usuwa zawartość pliku i nadpisuje od początku
            plikWy = new PrintWriter(nazwa);
            // zapis łańcucha
            for (int i=0; i<t.getsize(); i++){
                for (int j=0; j<t.getsize();j++){
                    if (t.organizmy[i][j]!=null){
                            char tekst = t.organizmy[i][j].getznak(); 
                            plikWy.println(tekst);
                            int g = t.organizmy[i][j].getwiek(); 
                            plikWy.println(g);
                            g=t.organizmy[i][j].getpol_x(); 
                            plikWy.println(g);
                            g=t.organizmy[i][j].getpol_y(); 
                            plikWy.println(g);
                    }
                }
            }
        }  
        catch(IOException ioE) {
        System.out.println("nie udało się otworzyć pliku");          
        }
    finally {
            if (plikWy != null) {
                plikWy.close();
            }
        }       
}  

public  void odczytPlikuTekstowego(String nazwa) throws IOException {
        // odczyt wiersz po wierszu
       for (int i=0; i<t.getsize();i++){
        for (int j=0; j<t.getsize();j++){
            t.organizmy[i][j]=null;
        }
    }
       t.ilosc_organizmow=0;
        BufferedReader plik2 = null;
             int d=0; //wiek
             boolean flagad=false;
             int a=0;//pol+x
             boolean flagaa=false;
             int k=0;//pol y
             boolean flagaf=false;
             boolean flagak=false;
             int tmp=0;
             char f=0;
        try {            
            plik2 = new BufferedReader(new FileReader(nazwa));
            String l ;
            while ((l = plik2.readLine()) != null) {
                if (flagaa!=true&&flagaf!=true&&flagad!=true&&flagak!=true&&flagaf!=true){
                    char[] www = l.toCharArray();
                    f=(char)www[0];
                    flagaf=true;
                }
                else if (flagaa!=true&&flagad!=true&&flagak!=true){
                    d= Integer.parseInt(l.trim());
                    flagad=true;   
                }
                     else  if (flagaa!=true&&flagak!=true){
                    a=Integer.parseInt(l.trim());
                    flagaa=true;  
                }
                      else if (flagak!=true){
                    k=Integer.parseInt(l.trim());
                    flagak=true;    
                } 
                  if (flagad==true&&flagaa==true&&flagaf==true&&flagak==true){                  
                    t.dodajOrganizm( f, a, k,d);
                    flagad=false;
                    flagaa=false;
                    flagaf=false;
                    flagak=false;
                }          
            }
        } finally {
            if (plik2 != null) {
                plik2.close();
            }
        }
         rysowanie();
         rezultat.setText("");        
    }
    public void keyPressed(KeyEvent keyEvent) {
                 rezultat.setText("");
                 t.wykonaj_ture(keyEvent,rezultat);
                 rysowanie();
    }  
    public void actionPerformed(ActionEvent akcja) {
    try{
      if (akcja.getSource() == wzorekButton){
         rezultat.setText("");
         t= new swiat();
         rysowanie();      
    }
        if (akcja.getSource() == zapis){
             zapisPliku(nazwa); 
     }
        if (akcja.getSource() == wczyt){ 
            odczytPlikuTekstowego(nazwa);         
      }     
    }catch(IOException e){ 
        }      
     }   
 	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
                String tekst = JOptionPane.showInputDialog("Wpisz symbol organizmu, który chcesz dodać");
                if (tekst!=null){
                if (tekst.length()!=0){ 
                char[] www = tekst.toCharArray();
                char f=0;             
                f=(char)www[0];
                    if(f=='A'||f=='g'||f=='L'||f=='m'||f=='O'||f=='t'||f=='j'||f=='W'||f=='Z'){
                        t.dodajOrganizm( f, x/20, y/20,0);
                         rysowanie();
                }
                  else if (f=='C'){
                       JOptionPane.showMessageDialog(null, "Możesz dodawać tylko zwierzęta i rośliny!");
                      }   
                else {
                       JOptionPane.showMessageDialog(null, "Wpisałeś nieprawidłowy symbol!");
                      }
                }
             }
	}   
 public void rysowanie(){
     Graphics d= rysunkowy.getGraphics();
      d.clearRect(0, 0, t.getsize()*20, t.getsize()*20);
            for (int i=0; i< t.getsize();i++){
           for (int j=0;j< t.getsize(); j++ ){
               if (t.organizmy[i][j]==null){   
               }
               else{
                     if (t.organizmy[i][j].getznak()== 'L'){
                         d.setColor(new Color(255,128, 32)); 
                         d.fillOval(20*t.organizmy[i][j].getpol_x(),20*t.organizmy[i][j].getpol_y(),10,10);
            } 
                     if (t.organizmy[i][j].getznak()== 'W'){
                         d.setColor(new Color(0,0, 0)); 
                         d.fillOval(20*t.organizmy[i][j].getpol_x(),20*t.organizmy[i][j].getpol_y(),10,10);
            } 
                     if (t.organizmy[i][j].getznak()== 'O'){
                         d.setColor(new Color(255,255, 0)); 
                         d.fillOval(20*t.organizmy[i][j].getpol_x(),20*t.organizmy[i][j].getpol_y(),10,10);
            } 
                     if (t.organizmy[i][j].getznak()== 'A'){
                         d.setColor(new Color(192,64, 16)); 
                         d.fillOval(20*t.organizmy[i][j].getpol_x(),20*t.organizmy[i][j].getpol_y(),10,10);
            }       
                     if (t.organizmy[i][j].getznak()== 'C'){
                         d.setColor(new Color(192,64, 112)); 
                         d.fillOval(20*t.organizmy[i][j].getpol_x(),20*t.organizmy[i][j].getpol_y(),10,10);
            }
                     if (t.organizmy[i][j].getznak()== 'g'){
                         d.setColor(new Color(192,160, 255)); 
                         d.fillOval(20*t.organizmy[i][j].getpol_x(),20*t.organizmy[i][j].getpol_y(),10,10);
            }
                     if (t.organizmy[i][j].getznak()== 'm'){
                         d.setColor(new Color(255,0, 0)); 
                         d.fillOval(20*t.organizmy[i][j].getpol_x(),20*t.organizmy[i][j].getpol_y(),10,10);
            }  
                    if (t.organizmy[i][j].getznak()== 't'){
                         d.setColor(new Color(128,255, 16)); 
                         d.fillOval(20*t.organizmy[i][j].getpol_x(),20*t.organizmy[i][j].getpol_y(),10,10);
            }
                     if (t.organizmy[i][j].getznak()== 'j'){
                         d.setColor(new Color(128,0, 224)); 
                         d.fillOval(20*t.organizmy[i][j].getpol_x(),20*t.organizmy[i][j].getpol_y(),10,10);
            }
                     if (t.organizmy[i][j].getznak()== 'Z'){
                         d.setColor(new Color(160,160, 160)); 
                         d.fillOval(20*t.organizmy[i][j].getpol_x(),20*t.organizmy[i][j].getpol_y(),10,10);
            }                  
               }
         } 
         }
         }   
       @Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
       @Override
        public void keyReleased(KeyEvent e){} // ignore
       @Override
        public void keyTyped(KeyEvent e){} // ignore
 }   
    

