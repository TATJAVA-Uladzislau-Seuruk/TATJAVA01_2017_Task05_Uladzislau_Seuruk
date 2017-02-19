package com.epam.tat.menu.util.parser.xml;

import com.epam.tat.menu.bean.MenuItem;
import com.epam.tat.menu.util.parser.exception.ParserException;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Uladzislau Seuruk.
 */
public class SaxMenuParser {
    public List<MenuItem> parse(String pathToFile) throws ParserException {
        List<MenuItem> itemList;
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            SaxMenuHandler handler = new SaxMenuHandler();
            reader.setContentHandler(handler);
            reader.parse(new InputSource(pathToFile));
            itemList = handler.getItemList();
        } catch (IOException | SAXException e) {
            throw new ParserException(ParsingConstants.ERROR_MESSAGE, e);
        }
        return itemList;
    }

    private class SaxMenuHandler extends DefaultHandler {
        private List<MenuItem> itemList = new LinkedList<>();
        private MenuItem item;
        private StringBuilder text;

        @Override
        public void characters(char[] ch, int start, int length) {
            text.append(ch, start, length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            switch (qName) {
                case ParsingConstants.PHOTO_URI_TAG:
                    item.setPhotoUri(text.toString());
                    break;
                case ParsingConstants.NAME_TAG:
                    item.setName(text.toString());
                    break;
                case ParsingConstants.DESCRIPTION_TAG:
                    item.setDescription(text.toString());
                    break;
                case ParsingConstants.PORTION_TAG:
                    item.setPortion(text.toString());
                    break;
                case ParsingConstants.PRICE_TAG:
                    item.setPrice(text.toString());
                    break;
                case ParsingConstants.ITEM_TAG:
                    itemList.add(item);
                    break;
            }
        }

        public List<MenuItem> getItemList() {
            return new ArrayList<>(itemList);
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes atts) {
            text = new StringBuilder();
            if (qName.equals(ParsingConstants.ITEM_TAG)) {
                item = new MenuItem();
                item.setId(atts.getValue(ParsingConstants.ID_ATTR));
            }
        }
    }
}