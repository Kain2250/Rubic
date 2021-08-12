package ru.skhool21.rubik.model;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class Combination {
    private Cub cub;
    private Map<Formula, List<Action>> combo = new HashMap<>();

    public Combination(Cub cub) {
        Properties properties = new Properties();

        try (FileInputStream propertyFile = new FileInputStream("src/main/resources/formulas.properties")) {
            properties.load(propertyFile);
            //combo.put(Formula.SECOND_RIGHT, Action.actionPars(properties.getProperty("second.right")));
            combo.put(Formula.SECOND_LEFT, Action.actionPars(properties.getProperty("level2.up.right")));

            //TODO: Добавить в пропертя остальные формулы
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.cub = cub;
    }

    @SneakyThrows
    public void comboInvoke(List<Action> combo) {
        for (Action elem : combo) {
            Cub.class.getMethod(elem.getMethodName()).invoke(this.cub);
        }
    }

    public List<Action> getCombo(Formula index) {
        return this.combo.get(index);
    }

}
