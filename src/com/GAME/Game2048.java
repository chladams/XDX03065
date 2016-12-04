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
