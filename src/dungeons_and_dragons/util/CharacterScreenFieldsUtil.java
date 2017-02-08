package dungeons_and_dragons.util;

import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.geometry.Insets;
import javafx.scene.text.Font;

/**
 * @author Sean Peel
 */
public interface CharacterScreenFieldsUtil {

    //Using -10 instead of -1
    //Stat modifiers can be go down to -5
    final int EMPTY_INT_FIELD = -10;

    //=============//
    //             //
    //    FONTS    //
    //             //
    //=============//
    //Character Name Font
    final Font NAME_FONT = new Font("Tahoma", 48);

    //Character Class Area Font
    final Font CLASS_AREA_FONT = new Font("Tahoma", 32);

    //Character Initiative Area Font
    final Font INITIATIVE_AREA_FONT = new Font("Tahoma", 48);

    //Character Stats Area Font
    final Font STATS_AREA_FONT = new Font("Tahoma", 48);

    //Character Stat Modifiers Area Font
    final Font STATS_MOD_AREA_FONT = new Font("Tahoma", 26);

    //Character Personality Area Font
    final Font PERSONALITY_AREA_FONT = new Font("Tahoma", 20);

    //=============//
    //             //
    //  LOCATIONS  //
    //             //
    //=============//
    final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    final double SCREEN_WIDTH = SCREEN_SIZE.getWidth();
    final double SCREEN_HEIGHT = SCREEN_SIZE.getHeight();

    //Character Name Field
    final int NAME_TEXT_FIELD_WIDTH = 650;
    final int NAME_TEXT_FIELD_HEIGHT = 85;
    final int NAME_TEXT_FIELD_TRANSLATE_X = -500;
    final int NAME_TEXT_FIELD_TRANSLATE_Y = -900;
    final double NAME_TEXT_FIELD_TRANSLATE_X_RATIO = SCREEN_WIDTH / NAME_TEXT_FIELD_TRANSLATE_X;
    final double NAME_TEXT_FIELD_WIDTH_RATIO = SCREEN_WIDTH / NAME_TEXT_FIELD_WIDTH;

    final double CLASS_AREA_TEXT_HEIGHT = 85;
    final double CLASS_AREA_TEXT_1_WIDTH = 375;
    final double CLASS_AREA_TEXT_2_WIDTH = 300;
    final double CLASS_AREA_TEXT_3_WIDTH = 300;
    final double CLASS_AREA_BOX_WIDTH = 990;
    final double CLASS_AREA_BOX_TRANSLATE_X = 350;
    final double CLASS_AREA_BOX_TRANSLATE_Y = -900;
    final double CLASS_AREA_BOX_SPACING = 10;
    final double CLASS_AREA_BOX_TRANSLATE_X_RATIO = SCREEN_WIDTH / CLASS_AREA_BOX_TRANSLATE_X;
    final double CLASS_AREA_BOX_WIDTH_RATIO = SCREEN_WIDTH / CLASS_AREA_BOX_WIDTH;
    final double CLASS_AREA_TEXT_1_WIDTH_RATIO = SCREEN_WIDTH / CLASS_AREA_TEXT_1_WIDTH;
    final double CLASS_AREA_TEXT_2_WIDTH_RATIO = SCREEN_WIDTH / CLASS_AREA_TEXT_2_WIDTH;
    final double CLASS_AREA_TEXT_3_WIDTH_RATIO = SCREEN_WIDTH / CLASS_AREA_TEXT_3_WIDTH;

    final double CHAR_WAY_OF_LIFE_BOX_WIDTH = 480;
    final double CHAR_WAY_OF_LIFE_BOX_HEIGHT = 625;
    final double CHAR_WAY_OF_LIFE_BOX_SPACING = 52;
    final double CHAR_WAY_OF_LIFE_BOX_TRANSLATE_X = 600;
    final double CHAR_WAY_OF_LIFE_BOX_TRANSLATE_Y = -400;
    final double CHAR_WAY_OF_LIFE_BOX_TRANSLATE_X_RATIO = SCREEN_WIDTH / CHAR_WAY_OF_LIFE_BOX_TRANSLATE_X;
    final double CHAR_WAY_OF_LIFE_BOX_WIDTH_RATIO = SCREEN_WIDTH / CHAR_WAY_OF_LIFE_BOX_WIDTH;

    final double INITIATIVE_AREA_BOX_HEIGHT = 160;
    final double INITIATIVE_AREA_BOX_WIDTH = 525;
    final double INITIATIVE_AREA_BOX_TRANSLATE_Y = -655;
    final Insets INITIATIVE_AREA_BOX_PADDING = new Insets(25, 0, 0, -15);
    final double INITIATIVE_AREA_BOX_WIDTH_RATIO = SCREEN_WIDTH / INITIATIVE_AREA_BOX_WIDTH;

    final double STATS_AREA_BOX_HEIGHT = 1250;
    final double STATS_AREA_BOX_WIDTH = 210;
    final double STATS_AREA_BOX_TRANSLATE_X = -795;
    final double STATS_AREA_BOX_TRANSLATE_Y = -125;
    final double STATS_AREA_BOX_SPACING = 52;
    final Insets STATS_AREA_BOX_PADDING = new Insets(65, 20, 20, 35);
    final double STATS_AREA_BOX_TRANSLATE_X_RATIO = SCREEN_WIDTH / STATS_AREA_BOX_TRANSLATE_X;
    final double STATS_AREA_BOX_WIDTH_RATIO = SCREEN_WIDTH / STATS_AREA_BOX_WIDTH;

    final double STAT_BOX_SPACING = 2.5;

    //Personality Field
    final double PERSONALITY_TEXT_FIELD_HEIGHT = 140;
    final double IDEALS_TEXT_FIELD_HEIGHT = 100;
    //Spell List Field
    final double SPELL_LIST_FIELD_HEIGHT = 500;
    final double SPELL_LIST_FIELD_WIDTH = 525;
    final double SPELL_LIST_FIELD_TRANSLATE_Y = 750;
    final double SPELL_LIST_FIELD_TRANSLATE_X = 600;
    final double SPELL_LIST_FIELD_TRANSLATE_X_RATIO = SCREEN_WIDTH / SPELL_LIST_FIELD_TRANSLATE_X;
    final double SPELL_LIST_FIELD_WIDTH_RATIO = SCREEN_WIDTH / SPELL_LIST_FIELD_WIDTH;

}
