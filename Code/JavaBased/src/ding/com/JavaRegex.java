package ding.com;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaRegex {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 *  Pattern 类
		 *    Pattern 对象是一个正则表达式的编译表示, Pattern 类没有公共构造方法
		 *   要创建一个 Pattern 对象,你必须首先调用其公共静态方法,它返回一个 Pattern 对象
		 *   该方法接受一个正则表达式作为他的第一个参数
		 *  
		 *  Matcher 类
		 *    Matcher 对象是对输入字符串进行解释和匹配操作的引擎
		 *   与 Pattern 类一样, Matcher 没有公共构造方法
		 *   你需要调用 Pattern 对象的 matcher 方法来获得一个 Matcher 对象
		 *   索引方法
		 *      public int start()
		 *       返回以前匹配的初始索引
		 *      public int start(int group)
		 *       返回在以前的匹配操作期间,由给定组所捕获的子序列的初始索引
		 *      public int end()
		 *       返回最后匹配字符之后的偏移量
		 *      public int end(int froup)
		 *       返回在以前的匹配操作期间,由给定组所捕获子序列的最后字符之后的偏移量
		 *   查找方法
		 *      public boolean lookingAt()
		 *       尝试将从区域开头开始的输入序列与该模式匹配
		 *      public boolean find()
		 *       尝试查找与模式匹配的输入序列的下一个子序列
		 *      public boolean find(int start)
		 *       重置此匹配器,然后尝试查找匹配该模式,从指定索引开始的输入序列的下一个子序列
		 *      public boolean matches()
		 *       尝试将整个区域与模式匹配
		 *   替换方法
		 *      public Matcher appendReplacement(StringBuffer sb,String replacement)
		 *       实现非终端添加和替换步骤
		 *      public StringBuffer appendTail(StringBuffer sb)
		 *       实现终端添加和替换步骤
		 *      public String replaceAll(String replacement)
		 *       替换模式与给定替换字符串相匹配的输入序列的每个子序列
		 *      public String replaceFirst(String replacement)
		 *       替换模式与给定替换字符串匹配的输入序列的第一个子序列
		 *      public Static String quoteReplacement(String e)
		 *       返回指定字符串的字面替换字符串
		 *   
		 *  PatternSyntaxException
		 *    PatternSyntaxException 是一个非强制异常类,它表示一个正则表达式模式中的语法错误
		 */
		String s="I am noob from runoob.com.";
		String p=".*runoob.*";
		boolean isMatch=Pattern.matches(p, s);
		System.out.println("s是否包含'runoob'字符串:"+isMatch);
		
		//  start 和 end 方法
		String s1="\\bcat\\b";
		String s2="  cat caat  cat cattt cat";
		Pattern p1=Pattern.compile(s1);
		Matcher m1=p1.matcher(s2);
		int i1=0;
		while(m1.find()) {
			i1++;
			System.out.println("Match number:"+i1);
			System.out.println("start():"+m1.start());
			System.out.println("end():"+m1.end());
		}
		
		//  matches 和 lookingAt 方法
		String s3="foo",s4="fooooooooooooo",s5="ooooooofoooooooo";
		Pattern p2=Pattern.compile(s3);
		Matcher m2=p2.matcher(s4),m3=p2.matcher(s5);
		System.out.println("lookingAt():"+m2.lookingAt());
		System.out.println("matches():"+m2.matches());
		System.out.println("lookingAt():"+m3.lookingAt());

		/**
		 * 捕获组
		 *   捕获组是把多个字符当一个单独单元进行处理的方法,它通过对括号内的字符进行分组来创建
		 *   捕获组是通过从左至右计算其开括号来编号
		 *   可通过调用 matcher 对象的 groupCount 方法来查看表达式有多少个分组
		 *    groupCount 方法返回一个 int 值,表示 matcher 对象当前有多少个捕获组
		 *   还有一个特殊的组(group(0)),它总是代表整个表达式,该组不包括在 groupCount 的返回值中
		 */
		String s6="THis order was placed for QT3000!OK?";
		String p3="(\\D*)(\\d+)(.*)";
		Pattern r2=Pattern.compile(p3);
		Matcher m4=r2.matcher(s6);
		if(m4.find()) {
			System.out.println("Found value:"+m4.group(0));
			System.out.println("Found value:"+m4.group(1));
			System.out.println("Found value:"+m4.group(2));
			System.out.println("Found value:"+m4.group(3));
		}else {
			System.out.println("NO MATCH");
		}
		
		/**
		 * 正则表达式语法
		 *   在其它语言中, '\\'表示:我想要在正则表达式中插入一个普通的反斜杠,请不要给它任何特殊意义
		 *   在 Java 中,'\\'表示:我要插入一个正则表达式的反斜线,所以其后的字符具有特殊的意义
		 *   所以在其它语言中,一个'\'就足以具有转义的作用,而在 Java 中需要两个才能被解析
		 *   \             将下一个字符标记为特殊字符
		 *   ^             匹配输入字符串开始的位置
		 *   $             匹配输入字符串结尾的位置
		 *   *             零次或多次匹配前的字符或子表达式
		 *   +             一次或多次匹配前面的字符或子表达式
		 *   ?             零次或一次匹配前面的字符或子表达式
		 *   {n}            n 是非负整数,正好匹配 n 次
		 *   {n,}           n 是非负整数,至少匹配 n 次
		 *   {n,m}          n m 是非负整数,至少匹配 n 次,之多匹配 m 次
		 *   ?             零次或一次匹配前面的字符或子表达式
		 *   .             匹配除"\r\n"之外的任何单一字符
		 *   (pattern)     匹配 pattern 并捕获该匹配的子表达式
		 *   (?:pattern)   匹配 pattern 但不捕获该匹配的子表达式
		 *   (?=pattern)   匹配 pattern 执行正向预测先行搜索的子表达式
		 *   (?!pattern)   匹配 pattern 执行反向预测先行搜索的子表达式
		 *   x|y           匹配 x 或 y
		 *   [xyz]         匹配包含的任一字符
		 *   [^xyz]        匹配未包含的任何字符
		 *   [a-z]         匹配指定范围内的任何字符
		 *   [^a-z]        匹配不在指定的范围内的任何字符
		 *   \b            匹配字符边界
		 *   \B            非字符边界匹配
		 *   \cx           匹配 x 指示的控制字符
		 *   \d            数字字符匹配
		 *   \D            非数字字符匹配
		 *   \f            换页符匹配
		 *   \n            换行符匹配
		 *   \r            回车符匹配
		 *   \s            匹配任何空白字符
		 *   \S            匹配任何非空白字符
		 *   \t            制表符匹配
		 *   \v            垂直制表符匹配
		 *   \w            匹配任何字类字符,包括下划线,与 [A-Za-z0-9_] 等效
		 *   \W            匹配任何非单词字符,与 [^A-Za-z0-9_] 等效
		 *   \xn           匹配 n(十六进制转移码)
		 *   \num          匹配 num(正整数)
		 *   \n            标识一个八进制转移码或反向引用
		 *   \nm           标识一个八进制转移码或反向引用...
		 *   \nml          当 n 是八进制数(0-3), m 和 l 是八进制(0-7)时,匹配八进制转义码 nml
		 *   \+un          (去掉+会报错)匹配 n ,其中 n 是以四位十六进制数表示的 Unicode 字符 
		 */
	}

}
