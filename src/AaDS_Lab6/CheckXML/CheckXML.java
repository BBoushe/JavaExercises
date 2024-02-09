package AaDS_Lab6.CheckXML;

//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.LinkedList;
//import java.util.NoSuchElementException;
//
///*
////            outerLoop:
////            while (!XMLTags.isEmpty()) {
////                conditionMeet = false;
////                innerLoop:
////                for (int x = 0, y = XMLTags.size()-1; !XMLTags.isEmpty() ; x = 0, y = XMLTags.size() - 1) {
////                    if (isPair(XMLTags.get(x), XMLTags.get(y))) {
////                        XMLTags.remove(x);
////                        XMLTags.remove(--y);
////                        conditionMeet = true;
////                        break;
////                    } else if (isPair(XMLTags.get(x), XMLTags.get(x + 1))) {
////                        XMLTags.remove(x);
////                        XMLTags.remove(x);
////                        conditionMeet = true;
////                        break;
////                    } else if (isPair(XMLTags.get(y - 1), XMLTags.get(y))) {
////                        XMLTags.remove(y);
////                        XMLTags.remove(--y);
////                        conditionMeet = true;
////                        break;
////                    } else {
////                        valid = 0;
////                        break outerLoop;
////                    }
////                }
////                if (!conditionMeet) {
////                    valid = 0;
////                    break;
////                }
////            }
////        }
// */
//
//public class CheckXML {
//
//    static boolean isPair(String a, String b) {
//        if (!a.startsWith("/") && b.startsWith("/")) {
//            if (a.equals(b.substring(1))) return true;
//            else return false;
//        } else return false;
//    }
//
//    public static void main(String[] args) throws Exception {
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String s = br.readLine();
//        int n = Integer.parseInt(s);
//        String[] redovi = new String[n];
//
//        for (int i = 0; i < n; i++)
//            redovi[i] = br.readLine();
//
//        int valid = 1;
//
//        LinkedList<String> XMLTags = new LinkedList<>();
//
//        for (String line : redovi) {
//            if (line.startsWith("[") && line.endsWith("]")) {
//                XMLTags.add(line.substring(1, line.length() - 1));
//            }
//        }
//
//        boolean conditionMeet;
//
//        if (XMLTags.size() % 2 != 0)
//            valid = 0;
//        else {
//            do{
//                conditionMeet = false;
//                for(int i = 0; !XMLTags.isEmpty() && ( i < XMLTags.size()-1); i++){
//                    if(isPair(XMLTags.get(i), XMLTags.get(i+1))){
//                        XMLTags.remove(i);
//                        XMLTags.remove(i);
//                        conditionMeet = true;
//                    }
//                }
//                if(!conditionMeet) {
//                    valid = 0;
//                    break;
//                }
//            }while(true);
//        }
//
//        System.out.println(valid);
//
//        br.close();
//    }
//}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;

public class CheckXML {

    static String getOpeningTag(String line){
        return line.replace("/","");
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(s);
        String[] redovi = new String[n];

        for (int i = 0; i < n; i++)
            redovi[i] = br.readLine();

        int valid = 1;

        Stack<String> openTags = new Stack<>();

        for(String line : redovi){
            if(line.startsWith("[/") && line.endsWith("]")){
                if(openTags.isEmpty() || !openTags.pop().equals(getOpeningTag(line))){
                    valid = 0;
                    break;
                }
            } else if(line.startsWith("[") && line.endsWith("]")){
                openTags.push(line);
            }
        }


        System.out.println(valid);

        br.close();
    }
}