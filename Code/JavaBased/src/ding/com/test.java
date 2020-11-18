package ding.com;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;

/**
 * Created by ai on 17/8/18.
 */
public class test extends JFrame {

    private JPanel topPanel;
    private JTextPane tPane;

    public test() {
        topPanel = new JPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tPane = new JTextPane();
        tPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        tPane.setMargin(new Insets(5, 5, 5, 5));

        topPanel.add(tPane);

        appendToPane(tPane, "这里是红色\n", Color.RED);
        appendToPane(tPane, "蓝色的字 ", Color.BLUE);
        appendToPane(tPane, "我猜", Color.DARK_GRAY);
        appendToPane(tPane, "我再猜", Color.MAGENTA);
        appendToPane(tPane, "我猜猜猜", Color.ORANGE);

        add(topPanel);
        pack();
        setVisible(true);
    }

    private void appendToPane(JTextPane tp, String msg, Color c) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
                StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "宋体");
        aset = sc.addAttribute(aset, StyleConstants.Alignment,
                StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
    }

    public static void main(String[] args) {
        new test();
    }

    /**
     * 将 money 字符串(仅包含数字和小数点)例如 xxxxxxx.xxx 装换成 x,xxx,xxx.xxx 类型
     * @param money
     * @return
     */
    private String moneyFormat(String money){
        String str="";
        int index=money.indexOf(".");
        if(index==-1){ // true,沒有小數點, money 的長度即爲位數; false 有小數點, index 即爲  money 的位數
            index=money.length();
        }

        if(index<=3){
            str = money;
        }else{
            StringBuffer string= new StringBuffer(money);
            for(index-=3;index>0;index-=3){
                string.insert(index, ",");
            }
            str = string.toString();
        }
        return str;
    }
}
