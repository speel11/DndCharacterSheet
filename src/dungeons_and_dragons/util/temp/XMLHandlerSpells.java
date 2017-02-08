package dungeons_and_dragons.util.temp;

import dungeons_and_dragons.entity.Spells;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Sean Peel
 */
public class XMLHandlerSpells extends DefaultHandler {

    ArrayList<Spells> spellList = new ArrayList<>();
    DatabaseConnection dbConn = new DatabaseConnection();
    Spells spell;
    String text = "";
    String description = "";
    ArrayList<String> classes;
    boolean bname = false;
    boolean blevel = false;
    boolean bschool = false;
    boolean btime = false;
    boolean bcomponents = false;
    boolean bduration = false;
    boolean bclass_ = false;
    boolean bdescription = false;
    int count = 0;

    @Override
    public void startElement(String uri, String localName,
            String qName, Attributes atts)
            throws SAXException {
        
        if(qName.equalsIgnoreCase("spell")) {
            spell = new Spells();
        }

        if (qName.equalsIgnoreCase("name")) {
            bname = true;
        }
        if (qName.equalsIgnoreCase("level")) {
            blevel = true;
        }
        if (qName.equalsIgnoreCase("school")) {
            bschool = true;
        }
        if (qName.equalsIgnoreCase("time")) {
            btime = true;
        }
        if (qName.equalsIgnoreCase("components")) {
            bcomponents = true;
        }
        if (qName.equalsIgnoreCase("duration")) {
            bduration = true;
        }
        if (qName.equalsIgnoreCase("classes")) {
            bclass_ = true;
        }
        if (qName.equalsIgnoreCase("text")) {
            bdescription = true;
        }

    }

    @Override

    public void endElement(String uri, String localName,
            String qName) throws SAXException {

        if (qName.equalsIgnoreCase("text")) {
            description += text;
        }

        if (qName.equalsIgnoreCase("spell")) {
//            System.out.println(spell.getName() + " - " + spell.getClass_());
            spell.setDescription(description);
            for (String s : classes) {
                //System.out.println(count++);
                spell.setClass_(s);
                try {
                    //System.out.println(spell.getClass_());
                    dbConn.commit(spell);
                } catch (InstantiationException | IllegalAccessException | SQLException ex) {
                    Logger.getLogger(XMLHandlerSpells.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            classes = new ArrayList<>();
            count = 0;
//            spellList.add(spell);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (bname) {
            //System.out.println("new spell");
            spell.setName(new String(ch, start, length));
            bname = false;
        }

        if (blevel) {
            spell.setLevel(new String(ch, start, length));
            blevel = false;
        }

        if (bschool) {
            spell.setSchool(new String(ch, start, length));
            bschool = false;
        }

        if (btime) {
            spell.setTime(new String(ch, start, length));
            btime = false;
        }
        if (bcomponents) {
            spell.setComponents(new String(ch, start, length));
            bcomponents = false;
        }
        if (bduration) {
            spell.setDuration(new String(ch, start, length));
            bduration = false;
        }
        if (bclass_) {

            String classesString = new String(ch, start, length);
            //spell.setClass_(classesString.replace(",", ""));

            classes = new ArrayList<>();

            if (classesString.contains(", ")) {
                String[] c = classesString.split(", ");
//                for(int i = 0; i < c.length; i++) {
//                    System.out.println(c[i]);
//                }
                classes.addAll(Arrays.asList(c));
            } else {
                //System.out.println(classesString);
                classes.add(classesString);
            }

            bclass_ = false;
        }
        if (bdescription) {
            text = new String(ch, start, length);
            bdescription = false;
        }

    }

}
