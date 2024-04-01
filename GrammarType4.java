package Grammatical;
import java.util.*;

public class GrammarType4 {

	public static void reconsitutionGrammar(Grammar grammar) {          
		//如果生成式中有"|",将其文法重构
		ArrayList<String> newgrammar=new ArrayList<>();
		for(String production:grammar.getNewGrammar()) {
        	if(production.indexOf("|")!=-1) {                  
        //若没用"|",直接添加,若有,对其进行分解添加.
        		 String[] parts = production.split("->");
        		 String left = parts[0].trim();
                 String right = parts[1].trim();
                 String[] rightparts = right.split("\\|");
                 
              
                 for(int i=0;i<rightparts.length;i++) {        
        //若有‘|’，将左边依次'->'右边组合
                 newgrammar.add(left+"->"+rightparts[i]); 
                 }    
        	}else {
        		newgrammar.add(production); 
        	}	
        }
		grammar.setNewGrammar(newgrammar);
	}
	
	
	
	public static Boolean checkType_0(String s) {           //检查符不符合0型

		for(int i=0;i<s.length();i++) {
			if(Character.isUpperCase(s.charAt(i))) {
			return true;	
			}
		}
		return false;	
	}
	
	public static Boolean checkType_1(String left,String right) {           //检查符不符合1型

		if(left.length()<=right.length())
		{return true;}
		else {
			if(right.length()==1 &&right.equals("ε")) {               //空情况
				return true;
			}
		}
		return false;
	}
	
	public static Boolean checkType_2(String s) {                 //检查符不符合2型
		if(s.length()==1&&Character.isUpperCase(s.charAt(0))) {       //左边只有1个大写
			return true;
		}
		return false;
		
	}
	
	public static Boolean checkType_3(String s) {           //检查符不符合3型
		if(s.length()==2) {
			if(Character.isUpperCase(s.charAt(0))&&Character.isLowerCase(s.charAt(1))) {    //形如Aa
				return true;
			}
			if(Character.isUpperCase(s.charAt(1))&&Character.isLowerCase(s.charAt(0))) {    //形如aA
				return true;
			}
			if(Character.isUpperCase(s.charAt(0))&&Character.isDigit(s.charAt(1))) {        //形如O0
				return true;
			}
			if(Character.isUpperCase(s.charAt(1))&&Character.isDigit(s.charAt(0))) {        //形如0O
				return true;
			}
		}else if(s.length()==1){
			if(Character.isLowerCase(s.charAt(0))||Character.isDigit(s.charAt(0))) return true;   //形如0  或 a
		}
	
		return false;	
	}
	
	
	
	static String linear="";                   //左右线性
    public static int checkGrammarType(Grammar grammar) {          //判断文法类型
        // 初始化类型为3型
        int type = 3;
        // 检查每个产生式
        for (String production:grammar.getNewGrammar()) {
        	String[] parts = production.split("->");
            String left = parts[0].trim();
            String right = parts[1].trim();                  
            if(!checkType_0(left)){  //不符合0型就不是文法
            	type=-1;
            }else if (!checkType_1(left,right) ) {    //不符合1型就是0型
            	if(type>0)type = 0;  
            }else if(!checkType_2(left)){    //不符合2型就是1型     
            	if(type>1)type=1;
            }else if(!checkType_3(right)){          //不符合3型就是2型
            	if(type>2)type=2;
            }else {
            	if(type>3)type=3;                  //判断3型左右线性
            	if(right.length()!=1&&(Character.isUpperCase(right.charAt(0)))) {
            		linear="左线性";
            	}else if(right.length()!=1||Character.isDigit(right.charAt(0))){
            		linear="右线性";
            	}
            }
        }  
        return type;
    }
    
    public static void input(Grammar grammar) {                //输入
    	Scanner sc= new Scanner(System.in);
    	System.out.println("请输入生成式的条数:");
    	int n=sc.nextInt();
    	for(int i=0;i<n;i++) {
    		grammar.addtoNewGrammar(sc.next());
    	}
    }
    
    public static void output(Grammar grammar) {              //按照格式输出
    	if(checkGrammarType(grammar)!=-1) {
    		System.out.println("文法是"+checkGrammarType(grammar)+"类型");	
    		if(!linear.equals("")&&checkGrammarType(grammar)==3) {
    			System.out.println(linear);
    		}
    		
    		System.out.println("四元组是G={Vn，Vt，P，S}");
    		System.out.println("P由以下产生式组成:");
    		for(String s:grammar.getNewGrammar()) {
    			System.out.println(s);
    		}
    			System.out.print("其中非终结符：Vn={");
    		for(String s:grammar.getVn()) {
    			System.out.print(s+",");
    		}System.out.print("}");
    	
    		System.out.println();
    	
    		System.out.print("其中终结符：Vt={");
    		for(String s:grammar.getVt()) {
    			System.out.print(s+",");
    		}System.out.print("}");
    
    	}else {
    		System.out.println("输入文法有误!!!");	
    	}	
    }
    
    public static void elementJudgment(Grammar grammar) {        //提取终结符非终结符,设置开始符
    	grammar.setS(grammar.getNewGrammar().get(0).charAt(0));      
    	 for (String production:grammar.getNewGrammar()) {
    		 	String str=production;
    		 	for(int i=0;i<str.length();i++) {
    		 		if(Character.isUpperCase(str.charAt(i))) {    //大写放一个集合
    		 			grammar.addtoVn(Character.toString(str.charAt(i)));
    		 		}else if(Character.isLowerCase(str.charAt(i))
    		 				||Character.isDigit(str.charAt(i))) {   //小写或数字放一个集合
    		 			grammar.addtoVt(Character.toString(str.charAt(i)));
    		 		}
    		 	}
    		 	
    	 }
    }
    
    public static void main(String[] args) {
    	while(true) {
    		System.out.println("");	
    		Grammar grammar=new Grammar();     
            input(grammar);                    //输入文法
            elementJudgment(grammar);          //提取文法的非终结符和终结符,设置开始符
            reconsitutionGrammar(grammar);     //清除|符号,重构文法
            output(grammar);  
    	}
    }
}

/*
测试用例

-1型
S->a|b
b->Ab


0型
S->ACaB
Ca->aaC
CB->DB
CB->E
aD->Da
AD->AC
aE->Ea
AE->e

1型
S->e|A
A->aABC
A->abC
CB->BC
bB->bb
bC->bc
cC->cc

2型
S->aSb
S->ab 

3型
S->1A|1
A->0A|0

3型
 S->Bc|Sc
 B->Ab|Bb
 A->Aa|a
 
S->AB
A->BS|0
B->SA|1
S->ε


 
 */




