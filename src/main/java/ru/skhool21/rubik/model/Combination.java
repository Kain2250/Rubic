package ru.skhool21.rubik.model;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class Combination {
    private final Map<Formula, List<Action>> combo = new HashMap<>();

    public Combination() {
        Properties properties = new Properties();
        try (FileInputStream propertyFile = new FileInputStream("src/main/resources/formulas.properties")) {
            properties.load(propertyFile);
            combo.put(Formula.LEVEL2_UP_R, Action.actionPars(properties.getProperty("level2.up.r")));
            combo.put(Formula.LEVEL2_UP_L, Action.actionPars(properties.getProperty("level2.up.l")));
            combo.put(Formula.LEVEL2_DOWN_R, Action.actionPars(properties.getProperty("level2.down.r")));
            combo.put(Formula.LEVEL2_DOWN_L, Action.actionPars(properties.getProperty("level2.down.l")));
            combo.put(Formula.LEVEL2_LEFT_R, Action.actionPars(properties.getProperty("level2.left.r")));
            combo.put(Formula.LEVEL2_LEFT_L, Action.actionPars(properties.getProperty("level2.left.l")));
            combo.put(Formula.LEVEL2_RIGHT_R, Action.actionPars(properties.getProperty("level2.right.r")));
            combo.put(Formula.LEVEL2_RIGHT_L, Action.actionPars(properties.getProperty("level2.right.l")));
            combo.put(Formula.LEVEL3_CROSS1, Action.actionPars(properties.getProperty("level3.cross1")));
            combo.put(Formula.LEVEL3_CROSS2, Action.actionPars(properties.getProperty("level3.cross2")));
            combo.put(Formula.LEVEL3_CROSS3, Action.actionPars(properties.getProperty("level3.cross3")));
            combo.put(Formula.LEVEL3_CROSS4, Action.actionPars(properties.getProperty("level3.cross4")));
            combo.put(Formula.LEVEL3_CROSS_SORT1, Action.actionPars(properties.getProperty("level3.cross.sort1")));
            combo.put(Formula.LEVEL3_CROSS_SORT2, Action.actionPars(properties.getProperty("level3.cross.sort2")));
            combo.put(Formula.LEVEL3_CROSS_SORT3, Action.actionPars(properties.getProperty("level3.cross.sort3")));
            combo.put(Formula.LEVEL3_CROSS_SORT4, Action.actionPars(properties.getProperty("level3.cross.sort4")));
            combo.put(Formula.LEVEL3_ANGLE1, Action.actionPars(properties.getProperty("level3.angle1")));
            combo.put(Formula.LEVEL3_ANGLE2, Action.actionPars(properties.getProperty("level3.angle2")));
            combo.put(Formula.LEVEL3_ANGLE3, Action.actionPars(properties.getProperty("level3.angle3")));
            combo.put(Formula.LEVEL3_ANGLE4, Action.actionPars(properties.getProperty("level3.angle4")));
            combo.put(Formula.LEVEL3_ANGLE_SORT1, Action.actionPars(properties.getProperty("level3.angle.sort1")));
            combo.put(Formula.LEVEL3_ANGLE_SORT2, Action.actionPars(properties.getProperty("level3.angle.sort2")));
            combo.put(Formula.LEVEL3_ANGLE_SORT3, Action.actionPars(properties.getProperty("level3.angle.sort3")));
            combo.put(Formula.LEVEL3_ANGLE_SORT4, Action.actionPars(properties.getProperty("level3.angle.sort4")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Action> getCombo(Formula index) {
        return this.combo.get(index);
    }
}
