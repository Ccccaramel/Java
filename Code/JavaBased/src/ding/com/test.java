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

        appendToPane(tPane, "�����Ǻ�ɫ\n", Color.RED);
        appendToPane(tPane, "��ɫ���� ", Color.BLUE);
        appendToPane(tPane, "�Ҳ�", Color.DARK_GRAY);
        appendToPane(tPane, "���ٲ�", Color.MAGENTA);
        appendToPane(tPane, "�Ҳ²²�", Color.ORANGE);

        add(topPanel);
        pack();
        setVisible(true);
    }

    private void appendToPane(JTextPane tp, String msg, Color c) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
                StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "����");
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
     * �� money �ַ���(���������ֺ�С����)���� xxxxxxx.xxx װ���� x,xxx,xxx.xxx ����
     * @param money
     * @return
     */
    private String moneyFormat(String money){
        String str="";
        int index=money.indexOf(".");
        if(index==-1){ // true,�]��С���c, money ���L�ȼ���λ��; false ��С���c, index ����  money ��λ��
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
