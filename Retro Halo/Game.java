import java.awt.*;
import java.applet. *;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.*;
import sun.audio.*;
import java.io.*;

public class Game extends JFrame implements Runnable, MouseListener, ActionListener, KeyListener
{
    
    private static final long serialVersionUID = 1L;
    public int mapWidth = 30;
    public int mapHeight = 15;
    private Thread thread;
    private boolean running;
    private BufferedImage image;
    public BufferedImage gun;
    public BufferedImage startScreen;
    public BufferedImage startScreen2;
    public BufferedImage gunShot;
    public BufferedImage rifle;
    public BufferedImage rifleShot;
    public BufferedImage hud;
    public BufferedImage winscreen;
    public BufferedImage deathscreen;
    public boolean shot_gun;
    public boolean shot_rifle;
    boolean pistol=false;
    boolean ar=false;
    boolean start=false;
    double distx;
    double disty;
    double distx2;
    double disty2;
    double distx3;
    double disty3;
    double distx4;
    double disty4;
    double distx5;
    double disty5;
    boolean killed=false;
    boolean killed2=false;
    boolean killed3=false;
    boolean killed4=false;
    boolean killed5=false;
    boolean dead=false;
    boolean win=false;
    int choice;
    int soundcount=0;
    int wavecount=0;
    double x1=4.5;
    double y1=4.5;
    int score;
    int gruntDeath;
    int eliteDeath;
    int bulx;
    int buly;
    int bulxNeg;
    int bulyNeg;
    int xlevel;
    int ylevel;
    int en1y=8;
    int en1x=15;
    int en2y=4;
    int en2x=14;
    int en3y=5;
    int en3x=21;
    int en4y=12;
    int en4x=14;
    int en5y=12;
    int en5x=20;
    int FREQ=2000;
    Timer t=new Timer(FREQ,this);
    double pfx;
    double pfy;
    InputStream gunshot;
        InputStream fall;
        InputStream music;
        InputStream elite1;
        InputStream elite2;
        InputStream elite3;
        InputStream grunt1;
        InputStream grunt2;
        InputStream grunt3;
        InputStream waveCom;
        InputStream music2;
        AudioData elite1as;
        AudioData elite2as;
        AudioData elite3as;
        AudioStream waveas;
        AudioData grunt1as;
        AudioData grunt2as;
        AudioData grunt3as;
        AudioData as;
        AudioData as2;
        AudioStream as3;
        AudioStream as4;
        ContinuousAudioDataStream elite1cas;
        ContinuousAudioDataStream elite2cas;
        ContinuousAudioDataStream elite3cas;
        ContinuousAudioDataStream wavecas; 
       ContinuousAudioDataStream cas; 
       ContinuousAudioDataStream cas2; 
        ContinuousAudioDataStream cas3;
        ContinuousAudioDataStream grunt1cas; 
       ContinuousAudioDataStream grunt2cas; 
        ContinuousAudioDataStream grunt3cas; 
    public int[] pixels;
    public ArrayList<Texture> textures;
    public Camera camera;
    public Screen screen;
    int count=0;
    public static int[][] map = 
        {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,2,3,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,2,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,6,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
        
        
       
    
    public Game() {
       
        t.start();
        
        
        try{
            gun = ImageIO.read(getClass().getResourceAsStream("res/gun.png"));
            gunShot = ImageIO.read(getClass().getResourceAsStream("res/gun_shot.png"));
            rifle = ImageIO.read(getClass().getResourceAsStream("res/rifle.png"));
            rifleShot = ImageIO.read(getClass().getResourceAsStream("res/rifle_shot.png"));
            hud = ImageIO.read(getClass().getResourceAsStream("res/hud.png"));
            startScreen=ImageIO.read(getClass().getResourceAsStream("res/pxArt.png"));
            startScreen2=ImageIO.read(getClass().getResourceAsStream("res/pxArt2.png"));
            winscreen=ImageIO.read(getClass().getResourceAsStream("res/victory.png"));
            deathscreen=ImageIO.read(getClass().getResourceAsStream("res/game_over.png"));
        } catch(IOException e) 
            {
                e.printStackTrace();
            }
           
        
        try{
            gunshot = new FileInputStream(new File("rifle_sound.wav"));
            as = new AudioStream(gunshot).getData();
            cas=new ContinuousAudioDataStream(as);
            fall=new FileInputStream(new File("empty-bullet-shell-fall-01.wav"));
            as2=new AudioStream(fall).getData();
            cas2=new ContinuousAudioDataStream(as2);
            music=new FileInputStream(new File("HaloReachOst-Overture.wav"));
            as3=new AudioStream(music);
             music2=new FileInputStream(new File("gameplay_music.wav"));
            as4=new AudioStream(music2);
            grunt1=new FileInputStream(new File("grunt_death_1.wav"));
            grunt2=new FileInputStream(new File("grunt_death_2.wav"));
            grunt3=new FileInputStream(new File("grunt_death_3.wav"));
            grunt1as=new AudioStream(grunt1).getData();
            grunt2as=new AudioStream(grunt2).getData();
            grunt3as=new AudioStream(grunt3).getData();
            grunt1cas=new ContinuousAudioDataStream(grunt1as);
            grunt2cas=new ContinuousAudioDataStream(grunt2as);
            grunt3cas=new ContinuousAudioDataStream(grunt3as);
            waveCom=new FileInputStream(new File("game_over.wav"));
            waveas=new AudioStream(waveCom);
            elite1=new FileInputStream(new File("elite_death_1.wav"));
            elite2=new FileInputStream(new File("elite_death_2.wav"));
            elite3=new FileInputStream(new File("elite_death_3.wav"));
            elite1as=new AudioStream(elite1).getData();
            elite2as=new AudioStream(elite2).getData();
            elite3as=new AudioStream(elite3).getData();
            elite1cas=new ContinuousAudioDataStream(elite1as);
            elite2cas=new ContinuousAudioDataStream(elite2as);
            elite3cas=new ContinuousAudioDataStream(elite3as);
        }catch(FileNotFoundException e)
        {
            System.out.println(e);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        AudioPlayer.player.start(as3);
        thread = new Thread(this);
        image = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
        textures = new ArrayList<Texture>();
        textures.add(Texture.wood);
        textures.add(Texture.brick);
        textures.add(Texture.bluestone);
        textures.add(Texture.stone);
        textures.add(Texture.enemy);
        textures.add(Texture.elite);
        camera = new Camera(x1, y1, 1, 0, 0, -.66);
        screen = new Screen(map, mapWidth, mapHeight, textures, 640, 480);
        addKeyListener(camera);
        addKeyListener(this);
        addMouseListener(this);
        setSize(640, 480);
        setResizable(false);
        setTitle("Retro Halo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.black);
        setLocationRelativeTo(null);
        setVisible(true);
        shot_gun = false;
        shot_rifle = false;
        start();
    }
    private synchronized void start() {
        running = true;
        thread.start();
    }
    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        if(start==false)
        {
            
            g.drawImage(startScreen,0,0,700,600,null);
            g.drawImage(startScreen2,100,25,500,500,null);
        }
        if(win==true)
        {
            AudioPlayer.player.start(waveas);
            g.drawImage(winscreen,-100,50,750,600,null);
            g.setColor(Color.white);
            g.drawString("Score: "+score,200,350);
            AudioPlayer.player.stop(cas);
             AudioPlayer.player.stop(cas2);
          AudioPlayer.player.stop(as4);
       
           AudioPlayer.player.stop(grunt1cas);
          AudioPlayer.player.stop(grunt2cas);
           AudioPlayer.player.stop(grunt3cas);
          AudioPlayer.player.stop(elite1cas);
          AudioPlayer.player.stop(elite2cas);
          AudioPlayer.player.stop(elite3cas);
        }
        if(start==true&&win==false&&dead==false)
        {
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        g.drawImage(hud, 0, 30, hud.getWidth(), hud.getHeight(), null);
        
        if(shot_gun == true)
          {
              if(count%2==0)
              {
              g.drawImage(rifleShot,300,50, gun.getWidth() * 6, gun.getHeight() * 6, null); 
            }
            else
            {
            
            g.drawImage(rifleShot,300,100, gun.getWidth() * 6, gun.getHeight() * 6, null);
        }
          }
          else
          {
             
              g.drawImage(rifle,300,100, gun.getWidth() * 6, gun.getHeight() * 6, null); 
            
            
          }
         if(killed5==true)
         {
             
            
             t.stop();
             FREQ-=100;
             if(FREQ==0)
             {win=true;
                 
                }
             t=new Timer(FREQ,this);
             t.start();
             killed=false;
             killed2=false;
             killed4=false;
             killed5=false;
             map[en1y][en1x]=0;
             map[en2y][en2x]=0;
             map[en3y][en3x]=0;
             map[en4y][en4x]=0;
             map[en5y][en5x]=0;
             en1y=8;
             en1x=15;
             en2y=4;
             en2x=14;
            en3y=5;
            en3x=21;
           en4y=12;
           en4x=14;
          en5y=12;
          en5x=20;
          x1=4.5;
          y1=4.5;
        }
        if(dead==true)
        {
            AudioPlayer.player.start(waveas);
            g.setColor(Color.black);
            g.fillRect(0,0,1000,1000);
            g.setColor(Color.white);
            g.drawImage(deathscreen,-100,50,700,400,null);
            g.drawString(""+score,600,400);
            AudioPlayer.player.stop(cas);
             AudioPlayer.player.stop(cas2);
          AudioPlayer.player.stop(as4);
          AudioPlayer.player.stop(cas);
             AudioPlayer.player.stop(cas2);
          AudioPlayer.player.stop(as4);
          
           AudioPlayer.player.stop(grunt1cas);
          AudioPlayer.player.stop(grunt2cas);
           AudioPlayer.player.stop(grunt3cas);
          AudioPlayer.player.stop(elite1cas);
          AudioPlayer.player.stop(elite2cas);
          AudioPlayer.player.stop(elite3cas);
        }
    }
        bs.show();
        
    }
    public void run() {
        long lastTime = System.nanoTime();
        final double ns = 1000000000.0 / 60.0;//60 times per second
        double delta = 0;
        requestFocus();
        while(running) {
            long now = System.nanoTime();
            delta = delta + ((now-lastTime) / ns);
            lastTime = now;
            while (delta >= 1)//Make sure update is only happening 60 times a second
            {
                //handles all of the logic restricted time
               
                
                
                
                
                screen.update(camera, pixels);
                camera.update(map);
                delta--;
            
            
        }
        
        if(start==true&&win==false&&dead==false)
                {
                pfx=camera.getxPos();
                 pfy=camera.getyPos();
                 
                if(shot_gun==false)
                {
                 bulx=(int)Math. round (pfx);
                 buly=(int)Math. round (pfy);
                 bulxNeg=(int)Math. round (pfx);
                 bulyNeg=(int)Math. round (pfy);
                 xlevel=(int)Math. round (pfx);
                 ylevel=(int)Math. round (pfy);
                }
                 
                if(shot_gun==true)
                {
                    bulx+=1;
                    buly+=1;
                    bulxNeg-=1;
                    bulyNeg-=1;
                    if(bulx==en1x&&ylevel==en1y)
                    {map[en1y][en1x]=0;
                     killed=true;
                     score++;
                     en1y=8;
                     en1x=15;
                     gruntDeath=(int) (Math. random()*3);
                     switch(gruntDeath)
                     {   case 1: AudioPlayer.player.start(grunt1cas);
                         break;
                         case 2: AudioPlayer.player.start(grunt2cas);
                         break;
                         case 3: AudioPlayer.player.start(grunt3cas);
                        }
                } 
                if(bulxNeg==en1x&&ylevel==en1y)
                    {map[en1y][en1x]=0;
                     killed=true;
                     score++;
                     en1y=8;
                     en1x=15;
                     gruntDeath=(int) (Math. random()*3);
                     switch(gruntDeath)
                     {   case 1: AudioPlayer.player.start(grunt1cas);
                         break;
                         case 2: AudioPlayer.player.start(grunt2cas);
                         break;
                         case 3: AudioPlayer.player.start(grunt3cas);
                        }
                } 
                if(buly==en1y&&xlevel==en1x)
                    {map[en1y][en1x]=0;
                     killed=true;
                     score++;
                     en1y=8;
                     en1x=15;
                     gruntDeath=(int) (Math. random()*3);
                     switch(gruntDeath)
                     {   case 1: AudioPlayer.player.start(grunt1cas);
                         break;
                         case 2: AudioPlayer.player.start(grunt2cas);
                         break;
                         case 3: AudioPlayer.player.start(grunt3cas);
                        }
                } 
                if(bulyNeg==en1y&&xlevel==en1x)
                    {map[en1y][en1x]=0;
                     killed=true;
                     score++;
                     en1y=8;
                     en1x=15;
                     gruntDeath=(int) (Math. random()*3);
                     switch(gruntDeath)
                     {   case 1: AudioPlayer.player.start(grunt1cas);
                         break;
                         case 2: AudioPlayer.player.start(grunt2cas);
                         break;
                         case 3: AudioPlayer.player.start(grunt3cas);
                        }
                } 
                if(bulx==en2x&&ylevel==en2y)
                    {map[en2y][en2x]=0;
                     killed2=true;
                     score++;
                     en2y=4;
                      en2x=14;
                     gruntDeath=(int) (Math. random()*3);
                     switch(gruntDeath)
                     {   case 1: AudioPlayer.player.start(grunt1cas);
                         break;
                         case 2: AudioPlayer.player.start(grunt2cas);
                         break;
                         case 3: AudioPlayer.player.start(grunt3cas);
                        }
                } 
                if(bulxNeg==en2x&&ylevel==en2y)
                    {map[en2y][en2x]=0;
                     killed2=true;
                     score++;
                     en2y=4;
                      en2x=14;
                     gruntDeath=(int) (Math. random()*3);
                     switch(gruntDeath)
                     {   case 1: AudioPlayer.player.start(grunt1cas);
                         break;
                         case 2: AudioPlayer.player.start(grunt2cas);
                         break;
                         case 3: AudioPlayer.player.start(grunt3cas);
                        }
                } 
                if(buly==en2y&&xlevel==en2x)
                    {map[en2y][en2x]=0;
                     killed2=true;
                     score++;
                      en2y=4;
                      en2x=14;
                     gruntDeath=(int) (Math. random()*3);
                     switch(gruntDeath)
                     {   case 1: AudioPlayer.player.start(grunt1cas);
                         break;
                         case 2: AudioPlayer.player.start(grunt2cas);
                         break;
                         case 3: AudioPlayer.player.start(grunt3cas);
                        }
                } 
                if(bulyNeg==en2y&&xlevel==en2x)
                    {map[en2y][en2x]=0;
                     killed2=true;
                     score++;
                     en2y=4;
                      en2x=14;
                     gruntDeath=(int) (Math. random()*3);
                     switch(gruntDeath)
                     {   case 1: AudioPlayer.player.start(grunt1cas);
                         break;
                         case 2: AudioPlayer.player.start(grunt2cas);
                         break;
                         case 3: AudioPlayer.player.start(grunt3cas);
                        }
                } 
                if(bulx==en3x&&ylevel==en3y)
                    {map[en3y][en3x]=0;
                     killed3=true;
                     score++;
                     en3y=5;
                      en3x=21;
                     gruntDeath=(int) (Math. random()*3);
                     switch(gruntDeath)
                     {   case 1: AudioPlayer.player.start(grunt1cas);
                         break;
                         case 2: AudioPlayer.player.start(grunt2cas);
                         break;
                         case 3: AudioPlayer.player.start(grunt3cas);
                        }
                } 
                if(bulxNeg==en3x&&ylevel==en3y)
                    {map[en3y][en3x]=0;
                     killed3=true;
                     score++;
                     en3y=5;
                      en3x=21;
                     gruntDeath=(int) (Math. random()*3);
                     switch(gruntDeath)
                     {   case 1: AudioPlayer.player.start(grunt1cas);
                         break;
                         case 2: AudioPlayer.player.start(grunt2cas);
                         break;
                         case 3: AudioPlayer.player.start(grunt3cas);
                        }
                } 
                if(buly==en3y&&xlevel==en3x)
                    {map[en3y][en3x]=0;
                     killed3=true;
                     score++;
                     en3y=5;
                      en3x=21;
                     gruntDeath=(int) (Math. random()*3);
                     switch(gruntDeath)
                     {   case 1: AudioPlayer.player.start(grunt1cas);
                         break;
                         case 2: AudioPlayer.player.start(grunt2cas);
                         break;
                         case 3: AudioPlayer.player.start(grunt3cas);
                        }
                } 
                if(bulyNeg==en3y&&xlevel==en3x)
                    {map[en3y][en3x]=0;
                     killed3=true;
                     score++;
                     en3y=5;
                      en3x=21;
                     gruntDeath=(int) (Math. random()*3);
                     switch(gruntDeath)
                     {   case 1: AudioPlayer.player.start(grunt1cas);
                         break;
                         case 2: AudioPlayer.player.start(grunt2cas);
                         break;
                         case 3: AudioPlayer.player.start(grunt3cas);
                        }
                } 
                if(bulx==en4x&&ylevel==en4y)
                    {map[en4y][en4x]=0;
                     killed4=true;
                     score++;
                     en4y=12;
                     en4x=14;
                     gruntDeath=(int) (Math. random()*3);
                     switch(gruntDeath)
                     {   case 1: AudioPlayer.player.start(grunt1cas);
                         break;
                         case 2: AudioPlayer.player.start(grunt2cas);
                         break;
                         case 3: AudioPlayer.player.start(grunt3cas);
                        }
                } 
                if(bulxNeg==en4x&&ylevel==en4y)
                    {map[en4y][en4x]=0;
                     killed4=true;
                     score++;
                     en4y=12;
                     en4x=14;
                     gruntDeath=(int) (Math. random()*3);
                     switch(gruntDeath)
                     {   case 1: AudioPlayer.player.start(grunt1cas);
                         break;
                         case 2: AudioPlayer.player.start(grunt2cas);
                         break;
                         case 3: AudioPlayer.player.start(grunt3cas);
                        }
                } 
                if(buly==en4y&&xlevel==en4x)
                    {map[en4y][en4x]=0;
                     killed4=true;
                     score++;
                     en4y=12;
                     en4x=14;
                     gruntDeath=(int) (Math. random()*3);
                     switch(gruntDeath)
                     {   case 1: AudioPlayer.player.start(grunt1cas);
                         break;
                         case 2: AudioPlayer.player.start(grunt2cas);
                         break;
                         case 3: AudioPlayer.player.start(grunt3cas);
                        }
                } 
                if(bulyNeg==en4y&&xlevel==en4x)
                    {map[en4y][en4x]=0;
                     killed4=true;
                     score++;
                     en4y=12;
                     en4x=14;
                     gruntDeath=(int) (Math. random()*3);
                     switch(gruntDeath)
                     {   case 1: AudioPlayer.player.start(grunt1cas);
                         break;
                         case 2: AudioPlayer.player.start(grunt2cas);
                         break;
                         case 3: AudioPlayer.player.start(grunt3cas);
                        }
                } 
                if(bulx==en5x&&ylevel==en5y)
                    {map[en5y][en5x]=0;
                     killed5=true;
                     soundcount=0;
                     score++;
                     en5y=12;
                     en5x=20;
                     eliteDeath=(int) (Math. random()*3);
                     switch(eliteDeath)
                     {   case 1: AudioPlayer.player.start(elite1cas);
                         break;
                         case 2: AudioPlayer.player.start(elite2cas);
                         break;
                         case 3: AudioPlayer.player.start(elite3cas);
                        }
                } 
                if(bulxNeg==en5x&&ylevel==en5y)
                    {map[en5y][en5x]=0;
                     killed5=true;
                     soundcount=0;
                     score++;
                     en5y=12;
                     en5x=20;
                     eliteDeath=(int) (Math. random()*3);
                     switch(eliteDeath)
                     {   case 1: AudioPlayer.player.start(elite1cas);
                         break;
                         case 2: AudioPlayer.player.start(elite2cas);
                         break;
                         case 3: AudioPlayer.player.start(elite3cas);
                        }
                } 
                if(buly==en5y&&xlevel==en5x)
                    {map[en5y][en5x]=0;
                     killed5=true;
                     soundcount=0;
                     score++;
                     en5y=12;
                     en5x=20;
                     eliteDeath=(int) (Math. random()*3);
                     switch(eliteDeath)
                     {   case 1: AudioPlayer.player.start(elite1cas);
                         break;
                         case 2: AudioPlayer.player.start(elite2cas);
                         break;
                         case 3: AudioPlayer.player.start(elite3cas);
                        }
                } 
                if(bulyNeg==en5y&&xlevel==en5x)
                    {map[en5y][en5x]=0;
                     killed5=true;
                     soundcount=0;
                     score++;
                     en5y=12;
                     en5x=20;
                     eliteDeath=(int) (Math. random()*3);
                     switch(eliteDeath)
                     {   case 1: AudioPlayer.player.start(elite1cas);
                         break;
                         case 2: AudioPlayer.player.start(elite2cas);
                         break;
                         case 3: AudioPlayer.player.start(elite3cas);
                        }
                } 
            }
            }
        count++;
        render();//displays to the screen unrestricted time
    }
}
     public void mouseEntered(MouseEvent e){}
     public void mouseExited(MouseEvent e){}
     public void mousePressed(MouseEvent e)
     {
       if(start==true&&win==false&&dead==false)
       {
         shot_gun = true;
       AudioPlayer.player.start(cas);
       AudioPlayer.player.start(cas2);
       
    }
     }
     public void mouseClicked(MouseEvent e){
        if(start==false&&win==false&&dead==false)
        {start=true;
            AudioPlayer.player.stop(as3);
            AudioPlayer.player.start(as4);
        }
        if(dead==true||win==true)
        { Runtime. getRuntime(). halt(0);
            Game game=new Game();
        }
        }
     public void mouseReleased(MouseEvent e)
     {
       if(start==true&&win==false&&dead==false)
       {
         shot_gun = false;
         AudioPlayer.player.stop(cas);
       AudioPlayer.player.stop(cas2);
       AudioPlayer.player.stop(grunt1cas);
       AudioPlayer.player.stop(grunt2cas);
       AudioPlayer.player.stop(grunt3cas);
       AudioPlayer.player.stop(elite1cas);
       AudioPlayer.player.stop(elite2cas);
       AudioPlayer.player.stop(elite3cas);
       
    }
     }
                        
     public void actionPerformed(ActionEvent e)
        {
            
            if(start==true&&win==false&&dead==false)
            {
            pfx=camera.getxPos();
            pfy=camera.getyPos();
            
            
            distx=pfy-en1x;
            disty=pfx-en1y;
           distx2=pfy-en2x;
          disty2=pfx-en2y;
           distx3=pfy-en3x;
           disty3=pfx-en3y;
           distx4=pfy-en4x;
         disty4=pfx-en4y;
         distx5=pfy-en5x;
         disty5=pfx-en5y;
            if(killed==false)
            {
             if(distx>1)
             {  map[en1y][en1x]=0;
                
                en1x=en1x+1;
                if(map[en1y][en1x]!=5||map[en1y][en1x]!=6)
                {
                map[en1y][en1x]=5;
                }
                else
                {
                    en1x=en1x-1;
                    map[en1y][en1x]=5;
             }
            }
            if(distx<-1)
            {map[en1y][en1x]=0;
               
                en1x=en1x-1;
                if(map[en1y][en1x]!=5||map[en1y][en1x]!=6)
                {
                map[en1y][en1x]=5;
                }
                else
                {
                    en1x=en1x+1;
                    map[en1y][en1x]=5;
             }
            }
            if(disty>1)
            {map[en1y][en1x]=0;
               
                en1y=en1y+1;
                if(map[en1y][en1x]!=5||map[en1y][en1x]!=6)
                {
                map[en1y][en1x]=5;
                }
                else
                {
                    en1y=en1y-1;
                    map[en1y][en1x]=5;
             }
            }
            if(disty<-1)
            {   map[en1y][en1x]=0;
                
                en1y=en1y-1;
                if(map[en1y][en1x]!=5||map[en1y][en1x]!=6)
                {
                map[en1y][en1x]=5;
                }
                else
                {
                    en1y=en1y+1;
                    map[en1y][en1x]=5;
             }
            }
        }
        if(killed2==false)
        {
        if(distx2>1)
             {  map[en2y][en2x]=0;
                
                en2x=en2x+1;
                if(map[en2y][en2x]!=5||map[en2y][en2x]!=6)
                {
                map[en2y][en2x]=5;
                }
                else
                {
                    en2x=en2x-1;
                    map[en2y][en2x]=5;
               }
             }
            if(distx2<-1)
            {map[en2y][en2x]=0;
               
                en2x=en2x-1;
                if(map[en2y][en2x]!=5||map[en2y][en2x]!=6)
                {
                map[en2y][en2x]=5;
                }
                else
                {
                    en2x=en2x+1;
                    map[en2y][en2x]=5;
             }
            }
            if(disty2>1)
            {map[en2y][en2x]=0;
               
                en2y=en2y+1;
                if(map[en2y][en2x]!=5||map[en2y][en2x]!=6)
                {
                map[en2y][en2x]=5;
                }
                else
                {
                    en2x=en2x-1;
                    map[en2y][en2x]=5;
             }
            }
            if(disty2<-1)
            {   map[en2y][en2x]=0;
                
                en2y=en2y-1;
                if(map[en2y][en2x]!=5||map[en2y][en2x]!=6)
                {
                map[en2y][en2x]=5;
                }
                else
                {
                    en2y=en2y+1;
                    map[en2y][en2x]=5;
             }
            }
        }
           if(killed3==false)
           {
            if(distx3>1)
             {  map[en3y][en3x]=0;
                
                en3x=en3x+1;
                if(map[en3y][en3x]!=5||map[en3y][en3x]!=6)
                {
                map[en3y][en3x]=5;
                }
                else
                {
                    en3x=en3x-1;
                    map[en3y][en3x]=5;
             }
             }
            if(distx3<-1)
            {map[en3y][en3x]=0;
               
                en3x=en3x-1;
                if(map[en3y][en3x]!=5||map[en3y][en3x]!=6)
                {
                map[en3y][en3x]=5;
                }
                else
                {
                    en3x=en3x+1;
                    map[en3y][en3x]=5;
             }
            }
            if(disty3>1)
            {map[en3y][en3x]=0;
               
                en3y=en3y+1;
                if(map[en3y][en3x]!=5||map[en3y][en3x]!=6)
                {
                map[en3y][en3x]=5;
                }
                else
                {
                    en3y=en3y-1;
                    map[en3y][en3x]=5;
             }
            }
            if(disty3<-1)
            {   map[en3y][en3x]=0;
                
                en3y=en3y-1;
                if(map[en3y][en3x]!=5||map[en3y][en3x]!=6)
                {
                map[en3y][en3x]=5;
                }
                else
                {
                    en3y=en3y+1;
                    map[en1y][en1x]=5;
             }
            }
        }
        if(killed4==false)
        {
            if(distx4>1)
             {  map[en4y][en4x]=0;
                
                en4x=en4x+1;
                if(map[en4y][en4x]!=5||map[en4y][en4x]!=6)
                {
                map[en4y][en4x]=5;
                }
                else
                {
                    en4x=en4x-1;
                    map[en4y][en4x]=5;
             }
             }
            if(distx4<-1)
            {map[en4y][en4x]=0;
               
                en4x=en4x-1;
                if(map[en4y][en4x]!=5||map[en4y][en4x]!=6)
                {
                map[en4y][en4x]=5;
                }
                else
                {
                    en4x=en4x+1;
                    map[en4y][en4x]=5;
             }
            }
            if(disty4>1)
            {map[en4y][en4x]=0;
               
                en4y=en4y+1;
                if(map[en4y][en4x]!=5||map[en4y][en4x]!=6)
                {
                map[en4y][en4x]=5;
                }
                else
                {
                    en4y=en4y-1;
                    map[en4y][en4x]=5;
             }
            }
            if(disty4<-1)
            {   map[en4y][en4x]=0;
                
                en4y=en4y-1;
                if(map[en4y][en4x]!=5||map[en4y][en4x]!=6)
                {
                map[en4y][en4x]=5;
                }
                else
                {
                    en4y=en4y+1;
                    map[en1y][en1x]=5;
             }
            }
        }
        if(killed5==false)
        {
            if(distx5>1)
             {  map[en5y][en5x]=0;
                
                en5x=en5x+1;
                if(map[en5y][en5x]!=5)
                {
                map[en5y][en5x]=6;
                }
                else
                {
                    en5x=en5x-1;
                  map[en5y][en5x]=6;
             }
             }
            if(distx5<-1)
            {map[en5y][en5x]=0;
               
                en5x=en5x-1;
                if(map[en5y][en5x]!=5)
                {
                map[en5y][en5x]=6;
                }
                else
                {
                    en5x=en5x+1;
                    map[en5y][en5x]=6;
             }
            }
            if(disty5>1)
            {map[en5y][en5x]=0;
               
                en5y=en5y+1;
                if(map[en5y][en5x]!=5)
                {
                map[en5y][en5x]=6;
                }
                else
                {
                    en5y=en5y-1;
                    map[en5y][en5x]=6;
             }
            }
            if(disty5<-1)
            {   map[en5y][en5x]=0;
                
                en5y=en5y-1;
                if(map[en5y][en5x]!=5)
                {
                map[en5y][en5x]=6;
                }
                else
                {
                    en5y=en5y+1;
                    map[en5y][en5x]=6;
             }
            }
        }
        if(killed==false)
        {
        if(en1x==(int)Math. round (pfx+1)&&en1y==(int)Math. round (pfy)||en1x==(int)Math. round (pfx-1)&&en1y==(int)Math. round (pfy)||en1x==(int)Math. round (pfx)&&en1y==(int)Math. round (pfy+1)||en1x==(int)Math. round (pfx)&&en1y==(int)Math. round (pfy-1))
        {dead=true;
        }
        }
        if(killed2==false)
        {
        if(en2x==(int)Math. round (pfx+1)&&en2y==(int)Math. round (pfy)||en2x==(int)Math. round (pfx-1)&&en2y==(int)Math. round (pfy)||en2x==(int)Math. round (pfx)&&en2y==(int)Math. round (pfy+1)||en2x==(int)Math. round (pfx)&&en2y==(int)Math. round (pfy-1))
        {dead=true;
        }
        }
        if(killed3==false)
        {
        if(en3x==(int)Math. round (pfx+1)&&en3y==(int)Math. round (pfy)||en3x==(int)Math. round (pfx-1)&&en3y==(int)Math. round (pfy)||en3x==(int)Math. round (pfx)&&en3y==(int)Math. round (pfy+1)||en3x==(int)Math. round (pfx)&&en3y==(int)Math. round (pfy-1))
        {dead=true;
        }
        }
        if(killed4==false)
        {
        if(en4x==(int)Math. round (pfx+1)&&en4y==(int)Math. round (pfy)||en4x==(int)Math. round (pfx-1)&&en4y==(int)Math. round (pfy)||en4x==(int)Math. round (pfx)&&en4y==(int)Math. round (pfy+1)||en4x==(int)Math. round (pfx)&&en4y==(int)Math. round (pfy-1))
        {dead=true;
        }
        }
        if(killed5==false)
        {
        if(en5x==(int)Math. round (pfx+1)&&en5y==(int)Math. round (pfy)||en5x==(int)Math. round (pfx-1)&&en5y==(int)Math. round (pfy)||en5x==(int)Math. round (pfx)&&en5y==(int)Math. round (pfy+1)||en5x==(int)Math. round (pfx)&&en5y==(int)Math. round (pfy-1))
        {dead=true;
        }
        }
        if(killed==false)
        {
        if(en1x==(int)Math. round (pfx)&&en1y==(int)Math. round (pfy)||en1x==(int)Math. round (pfx)&&en1y==(int)Math. round (pfy)||en1x==(int)Math. round (pfx)&&en1y==(int)Math. round (pfy)||en1x==(int)Math. round (pfx)&&en1y==(int)Math. round (pfy))
        {dead=true;
        }
        }
        if(killed2==false)
        {
        if(en2x==(int)Math. round (pfx)&&en2y==(int)Math. round (pfy)||en2x==(int)Math. round (pfx)&&en2y==(int)Math. round (pfy)||en2x==(int)Math. round (pfx)&&en2y==(int)Math. round (pfy)||en1x==(int)Math. round (pfx)&&en2y==(int)Math. round (pfy))
        {dead=true;
        }
        }
        if(killed3==false)
        {
        if(en3x==(int)Math. round (pfx)&&en3y==(int)Math. round (pfy)||en3x==(int)Math. round (pfx)&&en3y==(int)Math. round (pfy)||en3x==(int)Math. round (pfx)&&en3y==(int)Math. round (pfy)||en3x==(int)Math. round (pfx)&&en3y==(int)Math. round (pfy))
        {dead=true;
        }
        }
        if(killed4==false)
        {
        if(en4x==(int)Math. round (pfx)&&en4y==(int)Math. round (pfy)||en4x==(int)Math. round (pfx)&&en4y==(int)Math. round (pfy)||en4x==(int)Math. round (pfx)&&en4y==(int)Math. round (pfy)||en4x==(int)Math. round (pfx)&&en4y==(int)Math. round (pfy))
        {dead=true;
        }
        }
        if(killed5==false)
        {
        if(en5x==(int)Math. round (pfx)&&en5y==(int)Math. round (pfy)||en5x==(int)Math. round (pfx)&&en5y==(int)Math. round (pfy)||en5x==(int)Math. round (pfx)&&en5y==(int)Math. round (pfy)||en5x==(int)Math. round (pfx)&&en5y==(int)Math. round (pfy))
        {dead=true;
        }
        }
     }
    }
     public void keyTyped(KeyEvent e) {
        
    }
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    public static void main(String [] args) {
        Game game = new Game();
    }
}