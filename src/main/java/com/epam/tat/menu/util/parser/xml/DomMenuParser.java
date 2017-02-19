package com.epam.tat.menu.util.parser.xml;

import com.epam.tat.menu.bean.MenuItem;
import com.epam.tat.menu.util.parser.exception.ParserException;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Uladzislau Seuruk.
 */
public class DomMenuParser {
    public List<MenuItem> parse(String pathToFile) throws ParserException {
        List<MenuItem> menu = new ArrayList<>();
        try {
            DOMParser parser = new DOMParser();
            parser.parse(pathToFile);
            Document doc = parser.getDocument();
            Element root = doc.getDocumentElement();
            NodeList menuNodes = root.getElementsByTagName(ParsingConstants.ITEM_TAG);
            for (int i = 0; i < menuNodes.getLength(); ++i) {
                Element itemElement = (Element) menuNodes.item(i);
                MenuItem item = parseMenuItem(itemElement);
                menu.add(item);
            }
        } catch (IOException | SAXException e) {
            throw new ParserException(ParsingConstants.ERROR_MESSAGE, e);
        }
        return menu;
    }

    private MenuItem parseMenuItem(Element itemElement) {
        MenuItem item = new MenuItem();
        item.setId(itemElement.getAttribute(ParsingConstants.ID_ATTR));

        String name = getSingleChild(itemElement, ParsingConstants.NAME_TAG)
                .getTextContent().trim();
        item.setName(name);

        String portion = getSingleChild(itemElement, ParsingConstants.PORTION_TAG)
                .getTextContent().trim();
        item.setPortion(portion);

        Element photoUriElement = getSingleChild(itemElement, ParsingConstants.PHOTO_URI_TAG);
        if (photoUriElement != null) {
            item.setPhotoUri(photoUriElement.getTextContent().trim());
        }

        Element descriptionElement = getSingleChild(
                itemElement, ParsingConstants.DESCRIPTION_TAG);
        if (descriptionElement != null) {
            item.setDescription(descriptionElement.getTextContent().trim());
        }

        Element priceElement = getSingleChild(itemElement, ParsingConstants.PRICE_TAG);
        if (priceElement != null) {
            item.setPrice(priceElement.getTextContent().trim());
        }

        return item;
    }

    private Element getSingleChild(Element element, String childName) {
        NodeList nodeList = element.getElementsByTagName(childName);
        Element child = null;
        if (nodeList.getLength() > 0) {
            child = (Element) nodeList.item(0);
        }
        return child;
    }
}