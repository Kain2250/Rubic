package ru.skhool21.rubik.controller;

import lombok.SneakyThrows;
import ru.skhool21.rubik.exception.ParsingException;
import ru.skhool21.rubik.model.Action;
import ru.skhool21.rubik.model.Combination;
import ru.skhool21.rubik.model.Cub;
import ru.skhool21.rubik.model.Formula;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SolverController {
    public Cub cub;
    private static List<Action> confuse = new ArrayList<>();
    private List<Action> solve;
    private int randomCount;
    private boolean isGraphicMode;

    public SolverController() {
        this.cub = new Cub();
        this.solve = new ArrayList<>();
    }

    public static List<Action> getConfuse() {
        return confuse;
    }

    public void pars(String[] args) throws ParsingException {
        if (args.length > 0 && !args[0].isEmpty()) {
            for (String arg : args) {
                if ("-help".equals(arg)) {
                    System.out.println("HELP"); //TODO: Инфу
                } else if (arg.startsWith("-random=")) {
                    parseCount(arg);
                } else if ("-graphic".equals(arg)) {
                    isGraphicMode = true;
                } else {
                    parsAction(arg);
                }
            }
            if (randomCount != 0 && !confuse.isEmpty()) {
                throw new ParsingException("Запутать можно только одним типом");
            }
        }

    }

    @SneakyThrows
    public List<Action> confuse() {
        final int GOD_NUMBER_CONFUSE = 21;
        Random random = new Random();

        if (!confuse.isEmpty()) {
            for (Action elem : confuse) {
                Cub.class.getMethod(Action.fromId(elem.ordinal()).getMethodName()).invoke(this.cub);
            }
        } else {
            int currentConfuseSteps = randomCount > 0 ? randomCount : GOD_NUMBER_CONFUSE;
            for (int i = 0; i < currentConfuseSteps; i++) {
                int nextInt = random.nextInt(Action.values().length);
                Cub.class.getMethod(Action.fromId(nextInt).getMethodName()).invoke(this.cub);
                confuse.add(Action.fromId(nextInt));
            }
        }
        return confuse;
    }

    public void solve() {
        Combination combination = new Combination(this.cub);

//        combination.comboInvoke(combination.getCombo(Formula.SECOND_RIGHT));
//        solve.addAll(combination.getCombo(Formula.SECOND_RIGHT));
        //TODO: Заебошить сольвер
    }

    public void printSolve() {
        System.out.println(
                String.valueOf(this.solve)
                        .replace("_", "\'"));
    }

    public void printCub() {
        System.out.println(this.cub);
    }

    public void printConfuse() {
        System.out.println(
                String.valueOf(this.confuse)
                        .replace("_", "\'"));
    }

    private void parsAction(String arg) throws ParsingException {
        String normalizeStr = arg.replace('\'', '_');
        boolean itsValidAction;

        String[] actions = normalizeStr.split("\\s+");
        for (String actionStr : actions) {
            itsValidAction = false;
            for (Action action : Action.values()) {
                if (actionStr.equals(action.name())) {
                    itsValidAction = true;
                    confuse.add(action);
                }
            }
            if (!itsValidAction) {
                throw new ParsingException("Неизвестный ключ поворота - " + actionStr);
            }
        }
    }

    private void parseCount(String arg) throws ParsingException {
        try {
            randomCount = Integer.parseInt(arg.substring(arg.lastIndexOf("=") + 1));
            if (randomCount < 1) {
                throw new RuntimeException();
            }
        } catch (RuntimeException exception) {
            throw new ParsingException("Не верное значение '" + arg + "'");
        }
    }

    public boolean isGraphicMode() {
        return isGraphicMode;
    }
}
