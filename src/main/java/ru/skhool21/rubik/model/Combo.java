package ru.skhool21.rubik.model;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class Combo {
    private Cub cub;
    private List<List<Action>> combo = new ArrayList<>();


    public Combo(Cub cub) {
        FileInputStream file;
        Properties properties = new Properties();

        try {
            file = new FileInputStream("src/main/resources/formulas.properties");
            properties.load(file);
            combo.add(Action.actionPars(properties.getProperty("second.right")));
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

    public List<Action> getCombo(int index) {
        return this.combo.get(index);
    }

}
