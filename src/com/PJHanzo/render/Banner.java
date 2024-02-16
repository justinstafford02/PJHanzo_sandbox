package com.PJHanzo.render;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Banner {
    // class to render art such as welcome or win screen
    private static List<String> renderList = null;


    //    public void printBanner(String name) {
//        try (InputStream inputStream = getClass().getResourceAsStream("PC4-ProjectHanzo/resources/"+ name + ".txt")) {
//            if (inputStream != null) {
//                try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
//                    String line;
//                    while ((line = reader.readLine()) != null) {
//                        System.out.println(line);
//                        try {
//                            Thread.sleep(200);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            } else {
//                System.out.println("File not found: " + name + ".txt");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    // Working print banner
    public void printBanner(String name) {
        try {
            renderList = Files.readAllLines(Path.of("./resources/" + name + ".txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String line : renderList) {
            System.out.println(line);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception E) {
            System.out.println(E);
        }
    }

}