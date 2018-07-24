package pl.MarekJast.Week1.Browser05;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.commons.lang3.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Browser {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String url = getUserInput(scanner);
        String html = getUserInput(scanner);

        //collecting data from given url and save it into String
        String results = scanWebpage(url, html);

        //save search results into file
        saveToFile(results,"popular_words.txt");

        //ranking
        System.out.println("Page "+ url + " has following number of words between <" + "> html elemnts");
        System.out.println("Search result has been saved to 'filtered_popular_words.txt'");
        String firstFiler = filterShortWords(results);
        ArrayList<String> secondFilter = filterForbiddenWords(firstFiler);
        ArrayList<String > popularity= calculatePopularity(secondFilter);
        exportResults(popularity,url,html);



    }

    //allows to input url and html from scanner:
    static String getUserInput(Scanner scanner) {

        String output = "";

        System.out.println("Choose option:");
        System.out.println("1 - to insert webpage link ");
        System.out.println("2 - to insert html elements");
        Integer choice = 0;


        while (choice != 1 || choice != 2) {
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                System.out.println("Enter a full address of webpage as in example -> http://www.onet.pl/");
                output = scanner.nextLine();
                //System.out.println(output);
                break;
            } else if (choice == 2) {
                System.out.println("Enter html element without '<' '>' :");
                output = scanner.nextLine();
                //System.out.println(output);
                break;

            } else {
                System.out.println("Option not available");
            }
        }
        return output;
    }

    //returns string with text between html selectors
    static String scanWebpage(String url, String html) {
        Document doc;
        StringBuilder sb = new StringBuilder();
        String result = "";
        {
            try {

                doc = Jsoup.connect(url).get();
                Elements scannedItems = doc.select(html);

                for (Element item : scannedItems) {
                    String tmpStr = item.text().toString();
                    //System.out.println(tmpStr);
                    sb.append(tmpStr + "\n");
                }
                result = sb.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    //save string to file
    public static void saveToFile(String str, String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName, false);
            fw.append(str);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //export data from an array and save it to file
    public static void exportResults(ArrayList<String> array, String url, String html){
        StringBuilder sb=new StringBuilder();
        String fileName="filtered_popular_words.txt";
        sb.append("Search result are based on the data saved in file 'popular_words.txt' "+"\n");

        for(int i=0; i<array.size(); i++){
            sb.append(array.get(i)+"\n");
        }
        String toFile=sb.toString();
        saveToFile(toFile,fileName);
    }


    //filters given string from words shorter than 3 characters
    static String filterShortWords(String str) {
        StringTokenizer stok = new StringTokenizer(str);
        String result = "";
        String result2 = "";

        while (stok.hasMoreTokens()) {
            String tmp = stok.nextToken();
            if (tmp.length() > 3) {
                result += tmp + "\n";
            }
        }
        //System.out.println(result);
        return result;
    }


    //filer string from forbidden and unwanted fords
    static ArrayList filterForbiddenWords(String str) {
        String[] forbiddenWords = {"oraz", "ponieważ", "także", "ponad", "wraz", "nich", "niech", "temu", "tego", "samego", "który", "których",
        "gdzie", "którą", "które", "którego"};
        int forbWordsLen = forbiddenWords.length;

        //Vairable splitting input string into list
        String[] strSplit = str.split("\n");

        //Variable storing filtered words (without excluded)
        ArrayList<String> filteredWords = new ArrayList<>();


        //loop filtering excluded words and adding the results to ArrayList 'filteredWords'
        for (int i = 0; i < strSplit.length; i++) {
            for (int j = 0; j < forbWordsLen; j++) {
                if (strSplit[i].trim().equals(forbiddenWords[j])) {
                    break;
                } else {
                    j++;
                }
            }
            filteredWords.add(strSplit[i]);
        }

        //printout  filtered words (for validation purposes)
//        for(int i=0; i<filtered.size(); i++){
//            System.out.println(filtered.get(i));
//        }
        return filteredWords;
    }


    //method evaluating the most popular words
    static ArrayList<String> calculatePopularity(ArrayList<String> arr) {

        //1st step find uniqe words and stroe them in 'uniqueWords'
        ArrayList<String> uniqeWords = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            if (!uniqeWords.contains(arr.get(i))) {
                uniqeWords.add(arr.get(i));
            }
        }

        System.out.println("Filtered words " + arr.size());
        System.out.println("Uniqe words " + uniqeWords.size());

        //pringitn 'uniqueWords' for validation purposes
        //        for(int i=0; i<uniqeWords.size(); i++){
        //            System.out.println(uniqeWirds.get(i));
        //        }


        //2nd step - calculate number of uniqe wors in whole population
        //results are store in "ranking"
        ArrayList<String> ranking = new ArrayList<>();


        for (int i = 0; i < uniqeWords.size(); i++) {
            int counter = 0;
            for (int j = 0; j < arr.size(); j++) {
                String tmpUnique = uniqeWords.get(i);
                String tmpArr = arr.get(j);
                if (tmpUnique.equals(tmpArr)) {
                    counter++;
                }
            }

            //block adding the numerical suffix to the words -> "words counter"
            String prefix = "";
            if (counter < 10) {
                prefix = "00" + counter + "_";
            } else {
                prefix = "0" + counter + "_";
            }
            String toAdd = prefix + uniqeWords.get(i);
            ranking.add(toAdd);
        }
        //data sorting
        Collections.sort(ranking, Collections.reverseOrder());

        //printing for validation purposes
        System.out.println("Ranking");
        for (int i = 0; i < ranking.size(); i++) {
            System.out.println(ranking.get(i));
        }
        return ranking;
    }
}










