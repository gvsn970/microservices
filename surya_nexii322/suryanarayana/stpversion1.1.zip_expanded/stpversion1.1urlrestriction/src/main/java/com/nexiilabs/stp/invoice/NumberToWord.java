package com.nexiilabs.stp.invoice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NumberToWord  



{
 /*  private static final String[] specialNames = {
       "",
       " thousand",
       " million",
       " billion",
       " trillion",
       " quadrillion",
       " quintillion"
   };
   
   private static final String[] tensNames = {
       "",
       " ten",
       " twenty",
       " thirty",
       " forty",
       " fifty",
       " sixty",
       " seventy",
       " eighty",
       " ninety"
   };
   
   private static final String[] numNames = {
       "",
       " one",
       " two",
       " three",
       " four",
       " five",
       " six",
       " seven",
       " eight",
       " nine",
       " ten",
       " eleven",
       " twelve",
       " thirteen",
       " fourteen",
       " fifteen",
       " sixteen",
       " seventeen",
       " eighteen",
       " nineteen"
   };
   
   private static String  convertLessThanOneThousand(int number) {
       String current;
       
       if (number % 100 < 20){
           current = numNames[number % 100];
           number /= 100;
       }
       else {
           current = numNames[number % 10];
           number /= 10;
           
           current = tensNames[number % 10] + current;
           number /= 10;
       }
       if (number == 0) return current;
       return numNames[number] + " hundred" + current;
   }
   
   public static String convert(String num) {

	   int number = Integer.parseInt(num);
	   
       if (number == 0) { return "zero"; }
       
       String prefix = "";
       
       if (number < 0) {
           number = -number;
           prefix = "negative";
       }
       
       String current = "";
       int place = 0;
       
       do {
           int n = number % 1000;
           if (n != 0){
               String s = convertLessThanOneThousand(n);
               current = s + specialNames[place] + current;
           }
           place++;
           number /= 1000;
       } while (number > 0);
       
       return (prefix + current).trim();
   }
   
   public static void main(String[] args) {
       NumberToWord obj = new NumberToWord();
       System.out.println("*** " + obj.convert(123456789));
       System.out.println("*** " + obj.convert(-55));
   }
   public static String convertToIndianCurrency(String num) {
       BigDecimal bd = new BigDecimal(num);
       long number = bd.longValue();
       long no = bd.longValue();
       int decimal = (int) (bd.remainder(BigDecimal.ONE).doubleValue() * 100);
       System.out.println(decimal+"..........decimal");
       int digits_length = String.valueOf(no).length();
       System.out.println(digits_length+"........digits_length");
       int i = 0;
       ArrayList<String> str = new ArrayList<>();
       HashMap<Integer, String> words = new HashMap<>();
       words.put(0, "");
       words.put(1, "One");
       words.put(2, "Two");
       words.put(3, "Three");
       words.put(4, "Four");
       words.put(5, "Five");
       words.put(6, "Six");
       words.put(7, "Seven");
       words.put(8, "Eight");
       words.put(9, "Nine");
       words.put(10, "Ten");
       words.put(11, "Eleven");
       words.put(12, "Twelve");
       words.put(13, "Thirteen");
       words.put(14, "Fourteen");
       words.put(15, "Fifteen");
       words.put(16, "Sixteen");
       words.put(17, "Seventeen");
       words.put(18, "Eighteen");
       words.put(19, "Nineteen");
       words.put(20, "Twenty");
       words.put(30, "Thirty");
       words.put(40, "Forty");
       words.put(50, "Fifty");
       words.put(60, "Sixty");
       words.put(70, "Seventy");
       words.put(80, "Eighty");
       words.put(90, "Ninety");
       String digits[] = {"", "Hundred", "Thousand", "Lakh", "Crore"};
       while (i < digits_length) {
           int divider = (i == 2) ? 10 : 100;
           number = no % divider;
           no = no / divider;
           i += divider == 10 ? 1 : 2;
           if (number > 0) {
               int counter = str.size();
               String plural = (counter > 0 && number > 9) ? "s" : "";
               String tmp = (number < 21) ? words.get(Integer.valueOf((int) number)) + " " + digits[counter] + plural : words.get(Integer.valueOf((int) Math.floor(number / 10) * 10)) + " " + words.get(Integer.valueOf((int) (number % 10))) + " " + digits[counter] + plural;                
               str.add(tmp);
           }
       }

       Collections.reverse(str);
       String Rupees = String.join(" ", str).trim();

       String paise = (decimal) > 0 ? " And " + words.get(Integer.valueOf((int) (decimal - decimal % 10))) + " " + words.get(Integer.valueOf((int) (decimal % 10))) : "";
       if(!paise.equals("")){
    	   paise=paise+" Paise";
       }
      
       return  Rupees +" Rupees " + paise + " Only";
   }*/
   
   public static String numberToWords (String number)
   {
   String twodigitword="";
   String word="";
   String[] HTLC = {"", "Hundred", "Thousand", "Lakh", "Crore"}; //H-hundread , T-Thousand, ..
   int split[]={0,2, 3, 5, 7,9};
   String[] temp=new String[split.length];
   boolean addzero=true;
   int len1=number.length();
   if (len1>split[split.length-1]) { System.out.println("Error. Maximum Allowed digits "+ split[split.length-1]);
   System.exit(0);
   }
   for (int l=1 ; l<split.length; l++ )
   if (number.length()==split[l] ) addzero=false;
   if (addzero==true) number="0"+number;
   int len=number.length();
   int j=0;
   //spliting & putting numbers in temp array.
   while (split[j]<len)
   {
       int beg=len-split[j+1];
       int end=beg+split[j+1]-split[j];
       temp[j]=number.substring(beg , end);
       j=j+1;
   }
    
   for (int k=0;k<j;k++)
   {
       twodigitword=ConvertOnesTwos(temp[k]);
       if (k>=1){
       if (twodigitword.trim().length()!=0) word=twodigitword+" " +HTLC[k] +" "+word;
       }
       else word=twodigitword ;
       }
      return (word);
   }

public static String ConvertOnesTwos(String t)
{
final String[] ones ={"", "One", "Two", "Three", "Four", "Five","Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve","Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
final String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty","Ninety"};

String word="";
int num=Integer.parseInt(t);
if (num%10==0) word=tens[num/10]+" "+word ;
else if (num<20) word=ones[num]+" "+word ;
else
{
word=tens[(num-(num%10))/10]+word ;
word=word+" "+ones[num%10] ;
}
return word;
}
public  String dateConverter(String date) {
    
	//String date = "2016-01-06";
	System.out.println(date);
	String date1[]=date.split(" ");
	String formatted =null;
	try{
		LocalDate localDate = LocalDate.parse(date1[0]);
		formatted= DateTimeFormatter.ofPattern("dd-MMM-yyyy").format(localDate);
		System.out.println(formatted);
	}catch(Exception e){
    	e.printStackTrace();
    }
	return formatted;
			
}
public  String stringToSqlDate(String date) {
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
    LocalDate localDate = LocalDate.parse(date, formatter);
    System.out.println(localDate);  
	
    return localDate.toString();
			
}
   
}
