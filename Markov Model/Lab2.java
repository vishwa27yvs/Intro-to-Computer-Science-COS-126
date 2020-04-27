

public class Lab2 {
    public static void main(String[] args) {
        String temp = "Helilio World";
        String temp1 = new String("Hello World");
        String temp3 = new String("Hello World");
        String temp4 = new String(temp1);
        String temp2 = temp1.intern();
        Character c = 45;
        // System.out.println(c.isUpperCase(c));
        // only in case of numbers they get converted into ascii characters
        // all ascii charcters are the representation
        String s = " geeks     for geeks       ";
        // System.out.println(temp.replace('i', 'o'));
        StringBuffer t1 = new StringBuffer("Hello World");
        t1.insert(1, "hell", 1, 3);
        System.out.println(t1);
        /* ystem.out.println(s.trim());
        String t = String.valueOf(5.4);
        System.out.println(t);
        System.out.println(temp == temp1);
        System.out.println(temp1 == temp2);
        System.out.println(temp == temp2);
        System.out.println(temp3 == temp1);
        System.out.println(temp4 == temp1); */
        
    }
}
