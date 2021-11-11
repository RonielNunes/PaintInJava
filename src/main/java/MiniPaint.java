import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MiniPaint extends JFrame {
    private ArrayList<Coordenada> desenho = new ArrayList<>();
    private boolean precionado = false;
    private int color = 0;

    //Criação da janela
    MiniPaint(){
        //Pegando ações via mouse
        addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {}
                    @Override
                    public void mousePressed(MouseEvent mouseEvent) {
                        precionado = true;
                        desenho.clear();
                    }
                    @Override
                    public void mouseReleased(MouseEvent mouseEvent) {
                        precionado = false;
                    }

                    @Override
                    public void mouseEntered(MouseEvent mouseEvent) {}
                    @Override
                    public void mouseExited(MouseEvent mouseEvent) {}
                }
        );
        //Pegando ações via teclado
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //super.keyPressed(e);
//                System.out.println(e.getKeyChar());
                if(e.getKeyChar() == 'r'){
                    color = 1;
                }else if(e.getKeyChar() == 'g'){
                    color = 2;
                }else if(e.getKeyChar() == 'b'){
                    color = 3;
                }else if (e.getKeyChar() == 'd'){
                    color = 0;
                }
            }
        });

        //Tread de desenho inicializada
        Time time = new Time();
        time.start();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(new Dimension(1200,800));
        this.setLocationRelativeTo(null);
        this.setTitle("Paint");
        this.setVisible(true);
    }
    //Método para desenha
    public void paint(Graphics g){
        for (int i = 1; i < desenho.size(); i++) {
            int xAtual, yAtual, xPassado,yPassado;
            xAtual = desenho.get(i).getX();
            yAtual = desenho.get(i).getY();
            xPassado = desenho.get(i-1).getX();
            yPassado = desenho.get(i-1).getY();

            if (color == 0){
                g.setColor(Color.black);
            }else if (color == 1){
                g.setColor(Color.red);
            }else if (color == 2){
                g.setColor(Color.green);
            }else if (color == 3){
                g.setColor(Color.blue);
            }
            g.drawLine(xPassado,yPassado,xAtual,yAtual);
        }
        g.setFont(new Font("Ink Free", Font.BOLD,50));
        g.drawString("r = Red, b = Blue, g = Green, d = Dark", 100,120);
    }
    public class Time extends Thread{
        public void run(){
            while(true){
                if (precionado){
                    try {
                        Point ponto = getMousePosition(); //Classe para pegar coordenadas dos pontos
                        desenho.add(new Coordenada(ponto.x,ponto.y));
                    }catch (Exception error){

                    }
                }
                repaint();//repitar a janela
            }
        }
    }
}
