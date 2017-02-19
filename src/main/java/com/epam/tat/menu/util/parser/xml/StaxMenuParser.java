package com.epam.tat.menu.util.parser.xml;

import com.epam.tat.menu.bean.MenuItem;
import com.epam.tat.menu.util.parser.exception.ParserException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Uladzislau Seuruk.
 */
public class StaxMenuParser {
    public List<MenuItem> parse(String pathToFile) throws ParserException {
        List<MenuItem> itemList = new LinkedList<>();
        try {
            InputStream input = new FileInputStream(pathToFile);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(input);
            MenuItem item = null;
            String tagName = null;
            while (reader.hasNext()) {
                int type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        tagName = reader.getLocalName().trim();
                        if (ParsingConstants.ITEM_TAG.equals(tagName)) {
                            item = new MenuItem();
                            String id = reader.getAttributeValue(null, ParsingConstants.ID_ATTR);
                            item.setId(id);
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        String text = reader.getText().trim();
                        if (text.isEmpty()) {
                            break;
                        }
                        switch (tagName) {
                            case ParsingConstants.PHOTO_URI_TAG:
                                item.setPhotoUri(text);
                                break;
                            case ParsingConstants.NAME_TAG:
                                item.setName(text);
                                break;
                            case ParsingConstants.DESCRIPTION_TAG:
                                item.setDescription(text);
                                break;
                            case ParsingConstants.PORTION_TAG:
                                item.setPortion(text);
                                break;
                            case ParsingConstants.PRICE_TAG:
                                item.setPrice(text);
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        tagName = reader.getLocalName();
                        if (ParsingConstants.ITEM_TAG.equals(tagName)) {
                            itemList.add(item);
                        }
                        break;
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new ParserException(ParsingConstants.ERROR_MESSAGE, e);
        }
        return itemList;
    }
}