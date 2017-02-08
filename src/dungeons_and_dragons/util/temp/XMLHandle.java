package dungeons_and_dragons.util.temp;

import dungeons_and_dragons.entity.Spells;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 * @author Sean Peel
 */
public class XMLHandle {

    ArrayList<Spells> spellList;

    public void handle() throws ParserConfigurationException, IOException {

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            XMLHandlerSpells handler = new XMLHandlerSpells();

            saxParser.parse("C:\\Users\\Sean Peel\\OneDrive\\NetBeansProjects\\DndMaster\\src\\dungeons_and_dragons\\resources\\Spells Compendium 1.2.1.xml", handler);
            
        } catch (SAXException ex) {
            Logger.getLogger(XMLHandle.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
