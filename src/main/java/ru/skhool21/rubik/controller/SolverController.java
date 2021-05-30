package ru.skhool21.rubik.controller;

import ru.skhool21.rubik.model.Action;
import ru.skhool21.rubik.model.Color;
import ru.skhool21.rubik.model.Cub;
import ru.skhool21.rubik.model.Side;
import ru.skhool21.rubik.service.impl.RotatorImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolverController {
    private Cub cub;
    private List<Action> confuse;
    private List<Action> solve;
    private RotatorImpl rotator;

    public SolverController() {
        this.cub = new Cub();
        this.rotator = new RotatorImpl();
    }

    public void pars(String[] arg) {
        //TODO: Заебошить парсинг
    }

    public void confuse() {
        //TODO: Заебошить запутыватель
    }

    public void solve() {
        //TODO: Заебошить сольвер
        this.solve = new ArrayList<>();

     }

     public void print() {
         System.out.println(Action.B2);
//         System.out.println(Color.GREEN);
         System.out.println(cub.getBack().toString());
         System.out.println(solve);

     }

}
