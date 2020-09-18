题目一：
```java
    public String cleanNames(List<String> listOfNames) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < listOfNames.size(); i++) {
            if (listOfNames.get(i).length() > 1) {
                result.append(capitalizeString(listOfNames.get(i))).append(",");
            }
        }
        return result.substring(0, result.length() - 1).toString();
    }
 
    public String capitalizeString(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
    }
}

```

试着对这段代码进行改写，使它变得更简洁

题目二：
写一个求俩数和的add函数，写完后试着想想，能否写成每次只接收一个参数，类似从 add(3,5) 变成 add(3)(5)

题目三：
使用lambda表达式写一个递归的斐波那契函数

注：斐波那契数列，即兔子繁殖问题，1、1、2、3、5、8、13、21、34......