标题
3、通过window.location.open
  如果是希望打开一个新页面，而不是改变当前的页面，那么window.location.href就不适用了，此时，、
要借助于window.location.open()来实现 
简单介绍有一下window.open()函数，window.open()有三个参数，第一个参数是要打开的页面的url,第二个参数是窗口目标，第三个参数是一个特定字符串以及一个表示新页面是否取代浏览器历史集中当前加载页面的布尔值，通过只需要传递第一个参数。第二个参数还可以是”_blank”,”_self”,”_parent”,”_top”这样的特殊窗口名称，”_blank”打开新窗口,”_self”实现的效果同window.location.href. 
  的撒大撒大撒
继续上面的例子：

	var index = "lemon";
	var url = "receive.html?index="+index;
	$("#more").click(function(){
	window.open(url)
	});

这样在点击的时候，就会打开一个新页面，页面的url地址与上面相同。


	package com.csidez.myblog;
    import java.io.BufferedReader;
    import java.io.File;
    import java.io.FileReader;
    /**
     *
     select * from (SELECT `idcard`,`name`,COUNT(`name`) num  FROM tb_staff_info WHERE `name` IN ('﻿资奖根')
     UNION
     SELECT `idcard`,`name`,COUNT(`name`) num FROM tb_staff_info WHERE `name` IN ('朱正治')
     )c
     where num !=1
     */
    public class test {
    	public static void main(String args[])throws Exception{
		    File file = new File("D:\\idcard.txt");//Text文件
		    BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
		    String s = null;
		    String[] idcard=new String[400];
		    String[] flag = new String[400];
		    int i=0;
		    while((s = br.readLine())!=null){//使用readLine方法，一次读一行s
		    //idcard[i]=s;
		    //i++;
		    //s.indexOf("/",4);
		    //s.indexOf("/",6);
		    if (s.length()>2){
		    StringBuffer stringBuffer = new StringBuffer(s);
		    //增加
		    stringBuffer.insert(4,"/");
		    stringBuffer.insert(7,"/");
		    //截取
		    //String k = s.substring(1,4);
		    System.out.println(stringBuffer);
		    }else {
		    System.out.println(s);
		    }
		    //System.out.println("SELECT `idcard`,`name`,COUNT(`name`) FROM tb_staff_info WHERE `name` IN ('"+s+"')\n" +
		    //"UNION");
		    //System.out.print(s+",");
		    }
		    //for(int j=0;j<idcard.length;j++){
		    //int y=1;
		    //for(int k=j+1;k<idcard.length;k++){
		    //if (idcard[j].equals(idcard[k])){
		    //y++;
		    //}
		    //}
		    //if (y==2){
		    //System.out.println("是");
		    //}else {
		    //System.out.println("否");
		    //}
		    //}
		    //
		    //for (String o:flag){
		    //System.out.println(o);
		    //}
		    br.close();;
	    }
    }
    
![avatar](http://img.zcool.cn/community/019c2958a2b760a801219c77a9d27f.jpg)