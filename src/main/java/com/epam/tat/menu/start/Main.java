package com.epam.tat.menu.start;

import com.epam.tat.menu.bean.MenuItem;
import com.epam.tat.menu.util.parser.exception.ParserException;
import com.epam.tat.menu.util.parser.xml.DomMenuParser;
import com.epam.tat.menu.util.parser.xml.SaxMenuParser;
import com.epam.tat.menu.util.parser.xml.StaxMenuParser;

import java.io.File;
import java.util.List;

/**
 * @author Uladzislau Seuruk.
 */
public class Main {
    public static void main(String[] args) {
        String pathToFile = System.getProperty("user.dir") + File.separator + "menu.xml";
        try {
            //SaxMenuParser parser = new SaxMenuParser();
            StaxMenuParser parser = new StaxMenuParser();
            //DomMenuParser parser = new DomMenuParser();
            List<MenuItem> itemList = parser.parse(pathToFile);
            for (int i = 0; i < itemList.size(); ++i) {
                printItemInfo(i + 1, itemList.get(i));
            }
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printItemInfo(int number, MenuItem item) {
        StringBuilder builder = new StringBuilder();
        builder.append(number)
                .append(") id: ")
                .append(item.getId());
        if (item.getPhotoUri() != null) {
            builder.append("\nphotoUri: ")
                    .append(item.getPhotoUri());
        }
        builder.append("\nname: ")
                .append(item.getName());
        if (item.getDescription() != null) {
            builder.append("\ndescription: ")
                    .append(item.getDescription());
        }
        builder.append("\nportion: ")
                .append(item.getPortion());
        if (item.getPrice() != null) {
            builder.append("\nprice: ")
                    .append(item.getPrice());
        }
        System.out.println(builder.toString() + "\n");
    }
}