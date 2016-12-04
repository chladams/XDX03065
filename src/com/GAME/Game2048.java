package com.GAME;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lewis on 2016/11/30.
 *
 */
public class Game2048 extends JPanel{
   private static final Color BG_COLOR = new Color(0xbbada0);
   private static final String  FONT_NAME = "lewis";
   private static int TILE_SIZE = 64;
   private static int TILES_MARGIN = 16;

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

   @Override
   public void print(Graphics g) {
      super.print(g);
   }


}
