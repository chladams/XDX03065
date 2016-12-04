package com.GAME;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by lewis on 2016/11/30.
 *
 */
public class Game2048 extends JPanel{
   private static final Color BG_COLOR = new Color(0xbbada0);
   private static final String  FONT_NAME = "lewis";
   private static int TILE_SIZE = 64;
   private static int TILES_MARGIN = 16;

   private Tile[] MyTiles;
   boolean myWin = false;
   boolean myLose = false;
   int myScore = 0;


   static class Tile{                                                 //小块类
      int value;                                                       //设置当前的小块的分数

      public Tile(){
         this(0);
      }

      public Tile(int num){                                          //设置分数
         value = num;
      }

      public boolean isEmpty(){
         return value == 0;
      }

      public Color getForeground(){                                     //判断是否满了，返回对应的颜色？
         return value<16?new Color(0x776e65) : new Color(0xf9f6f2);
      }

      public Color getBackground(){                                     //设置背景色
         switch(value){
            case 2: return new Color(0xeee4da);
            case 4: return new Color(0xede0c8);
            case 8: return new Color(0xf2b179);
            case 16: return new Color(0xf59563);
            case 32: return new Color(0xf67c5f);
            case 64: return new Color(0xf65e3b);
            case 128: return new Color(0xedcf72);
            case 256: return new Color(0xedcc61);
            case 512: return new Color(0xedc850);
            case 1024: return new Color(0xedc53f);
            case 2048: return new Color(0xedc22e);
         }
         return new Color(0xcdc1b4);
      }
   }

   public static void start(){
      JFrame game = new JFrame("2048小游戏，Powered By Lewis");
      game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//EXIT_ON_CLOSE（在 JFrame 中定义）：使用 System exit 方法退出应用程序。仅在应用程序中使用。
      game.setSize(340,400);
      game.setResizable(false);                                      //是否可以改变窗体大小
      game.add(new Game2048());                                      //添加游戏Panel
      game.setLocationRelativeTo(null);                              //设置窗口相对于指定组件的位置。如果组件当前未显示，或者 c 为 null，则此窗口将置于屏幕的中央。中点可以使用
      game.setVisible(true);                                         //窗体是否可见

   }

   public Game2048(){
      setFocusable(true);                                //将此 Component 的焦点状态设置为指定值。此值覆盖 Component 的默认焦点状态。
      addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent e) {            //添加按键监听器
            if(e.getKeyCode()==KeyEvent.VK_ESCAPE){      //若按ESC键，则重置游戏
               resetGame();
            }

            if(!canMove()){                              //若无法移动，则判断为输
               myLose = true;
            }

            if(!myWin&&!myLose){                         //若在中间状态，则继续移动游戏
               switch(e.getKeyCode()){
                  case KeyEvent.VK_LEFT:
                     left();break;
                  case KeyEvent.VK_RIGHT:
                     right();break;
                  case KeyEvent.VK_UP:
                     up();break;
                  case KeyEvent.VK_DOWN:
                     down();break;
               }
            }

            if(!myWin&&!canMove()){                      //若没到达赢的局面而且无法移动则判断为输
               myLose = true;
            }

            repaint();
         }
      });
   }

   public void resetGame(){                              //重置游戏

   }

   public boolean canMove(){                             //判断当前局面是否能否移动
      return true;
   }

   public void left(){                                    //向左移动

   }

   public void right(){                                  //向右移动

   }

   public void up(){                                     //向上移动

   }

   public void down(){                                   //向下移动

   }

   @Override
   public void print(Graphics g) {

   }

}
