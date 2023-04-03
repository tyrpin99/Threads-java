package com.cwiczenie_java;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main extends JFrame {


    public Main() {
        this.setTitle("Watki-animacja ");
        this.setBounds(250, 300, 400, 350);
        this.getContentPane().add(panelButonow, BorderLayout.SOUTH);
        this.getContentPane().add(panelAnimacji);
        panelAnimacji.setBackground(Color.GRAY);
        JButton bStart = (JButton) panelButonow.add(new JButton("Start"));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAnimation();
            }
        });
        JButton bStop = (JButton) panelButonow.add(new JButton("Stop"));

        bStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                stopAnimation();
            }
        });

        JButton bDodaj = (JButton) panelButonow.add(new JButton("Dodaj"));
        bDodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dodajAnimation();
            }
        });
        JButton bPrzyspiesz = (JButton) panelButonow.add(new JButton("Przyspiesz"));
        JButton bZwolnij = (JButton) panelButonow.add(new JButton("Zwolnij"));
        bPrzyspiesz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                przyspieszAnimation();
            }
        });
        bZwolnij.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zwolnijAnimation();
            }
        });

    }

    private PanelAnimacji panelAnimacji = new PanelAnimacji();
    private JPanel panelButonow = new JPanel();

    public void startAnimation() {
        panelAnimacji.startAnimation();
    }

    public void stopAnimation() {
        panelAnimacji.stop();
    }

    public void dodajAnimation() {
        panelAnimacji.addKropelka();
    }

    public void przyspieszAnimation(){

        panelAnimacji.przyspiesz();
    }
    public void zwolnijAnimation(){
        panelAnimacji.zwolnij();

    }

    public static void main(String[] args) {
        //  Calculator calculator = new Calculator();
        // Conventer conventer = new Conventer();

        new Main().setVisible(true);
    }


    class PanelAnimacji extends JPanel {

        private volatile boolean zatrzymany = false;
        private Object lock = new Object();
        private int time = 1;

        public void addKropelka() {
            listaKropelek.add(new Kropelka());
            watek = new Thread(grupaWatkow,new KropelkaRunnable((Kropelka)listaKropelek.get(listaKropelek.size() - 1)));
            watek.start();
            grupaWatkow.list();
        }

        public void stop() {
            zatrzymany = true;
        }
        public void startAnimation(){
            if(zatrzymany){
                zatrzymany=false;
                synchronized (lock){
                    lock.notifyAll();
                }
            }
        }

        public void przyspiesz(){

            if(time > 1)
                time--;
        }
        public void zwolnij(){
            if(time < 1000)
                time++;

        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int i = 0; i < listaKropelek.size(); i++) {
                g.drawImage(Kropelka.getImg(), ((Kropelka) listaKropelek.get(i)).x, ((Kropelka) listaKropelek.get(i)).y, null);
            }
        }

        ArrayList listaKropelek = new ArrayList();

        JPanel ten = this;
        Thread watek;
        ThreadGroup grupaWatkow = new ThreadGroup("Grupa kropelek");

        public class KropelkaRunnable implements Runnable {
            Kropelka kropelka;

            public KropelkaRunnable(Kropelka kropelka) {
                this.kropelka = kropelka;
            }

            @Override
            public void run() {


                    while (true) {
                        synchronized (lock){
                            while(zatrzymany )
                            {
                                try {
                                    lock.wait();
                                }
                                catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        this.kropelka.ruszKropelka(ten);
                        repaint();
                        try {
                        Thread.sleep(time);
                    }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                }

            }
        }
    }
}

class Kropelka {
    public static Image getImg() {
        return Kropelka.kropelka;
    }

    public static Image kropelka = new ImageIcon("pobrane2.jpg").getImage();

    public void ruszKropelka(JPanel pojemnik) {
        Rectangle granicePanelu = pojemnik.getBounds();
        x += dx;
        y += dy;

        if (y + yKropelki >= granicePanelu.getMaxY()) {
            y = (int) (granicePanelu.getMaxY() - yKropelki);
            dy = -dy;
        }

        if (x + xKropelki >= granicePanelu.getMaxX()) {
            x = (int) (granicePanelu.getMaxX() - xKropelki);
            dx = -dx;
        }
        if (y <= granicePanelu.getMinY()) {
            y = (int) (granicePanelu.getMinY());
            dy = -dy;
        }
        if (x <= granicePanelu.getMinX()) {
            x = (int) (granicePanelu.getMinX());
            dx = -dx;
        }


    }

    int x = 0;
    int y = 0;
    int dx = 1;
    int dy = 1;
    int xKropelki = kropelka.getWidth(null);
    int yKropelki = kropelka.getHeight(null);
}

