# Grammar judgment program
编译原理文法类型判断/java/编译原理课程设计



一、实验目的 
	输入：一个文法G
	输出：文法G的类型及各部分




 
二、实验原理、思路  
1.	实验原理：
(1)	编译原理相关知识：
1)	文法的四元组G(Vn,Vt,P,S)
Vn为非终结符，在本次代码中用大写表示；Vt为终结符，本次代码用小写表示；P为产生式。 S为开始符。
2)	四种文法的关系
L0 包含L1包含L2 包含L3。
(2)	Java相关知识：
1)	Java面向对象思想
客观世界中的一个事物就是一个对象，每个客观事物都有自己的特征和行为。从程序设计的角度来看，事物的特性就是属性，行为就是方法。事物之间的特征和行为可以相互传递，互相作用，有机协调。
本次实验就是利用现实事物---文法，文法有四元组，就设置文法的属于为四元组，其中需要设置四元组和获取四元组，将这些设置为行为。将文法抽象成为“类”。

2)	ArrayList集合
本次实验的文法的属性---产生式就用到了ArrayList集合，其特点为集合中允许有重复元素，所有元素以一种线性的方式进行存储，可以通过索引访问集合中的指定元素。
本次实验所用的ArrayList的方法有添加元素add（）。

3)	HashSet集合
本次实验的文法的属性---终结符和非终结符就用到了HashSet，其特点为集合中的元素不能重复，且无序存放。
本次实验所用的HashSet方法有添加元素add（）。
4)	String字符串的方法split（）：以指定符号切割字符串，将切割后的每个小字符串放入一个字符串数组。

5)	Character的方法isUpperCase（）：判断给定的字符是否是大写字母。
isLowerCase （）：判断给定的字符是否是小写字母。
isDigit（）：判断给定的字符是否是数字。

6)	Foreach语句：用于遍历ArrayList、HashSet集合中的元素。

2.	实验思路：
	   步骤如下：
1)	文法输入；
输入产生式的条数，新建一个文法类对象，根据条数循环将每条产生式加入到对象的产生式ArrayList集合中。

2)	四元组获取；
用Foreach语句依次遍历对象的产生式ArrayList集合的元素，再对每条集合中的元素（产生式）进行依次遍历字符，并判断大小写，若为大写加入对象的非终结符HashSet集合中，若为小写加入对象的终结符HashSet集合中。

3)	重构文法（去除“|”符号）：
因为产生式有“|”，不好不断，将有“|”的每一条产生式，先以“->”进行分割，分为左右两部分；再将右边部分以“|”分割。最后将以“左部分“ “->“右部分分割的每个字符串“重新添加新的ArrayList集合，最后将其覆盖对象的产生式ArrayList集合。
例如S->a|Ba; 将其变为 S->a 和S->Ba加入对象的产生式。

4)	文法判断；
根据各文法的关系：L0 包含L1包含L2 包含L3。单个产生式判断为文法不是3型就是2型，不是2型就是1型，不是1型就是0型，不是0型是不是文法了。多条产生式判断写个循环，然后再类型赋值时，判断一下上一个产生式的类型是否是大于现在的产生式类型就行。
各个类型判断见第三部分程序代码的第4点判断文法类型的第（2）小点。
5)	输出格式；
		调用判断文法类型的方法和调用文法对象的get方法获取相应四元组，并按一定格式输出。

![image](https://github.com/HarveyJI/Grammar-judgment-program/assets/78439035/5d72e906-caa1-47bc-87e8-899139014207)

![image](https://github.com/HarveyJI/Grammar-judgment-program/assets/78439035/b3193b97-6cec-4b85-8101-fd2a19089101)

![image](https://github.com/HarveyJI/Grammar-judgment-program/assets/78439035/8e357c2d-2be3-42b2-be9c-53e9cafdb936)

![image](https://github.com/HarveyJI/Grammar-judgment-program/assets/78439035/d7ef190f-bbe9-469d-971d-70531a53ed12)

![image](https://github.com/HarveyJI/Grammar-judgment-program/assets/78439035/ad945d73-e7bd-4676-bb1b-31ef7316b2e3)

![image](https://github.com/HarveyJI/Grammar-judgment-program/assets/78439035/dcb85f98-cda7-42f4-b0e5-13ecceb002f6)

![image](https://github.com/HarveyJI/Grammar-judgment-program/assets/78439035/e5145ec0-3d7e-4d27-bf38-570a61130228)


