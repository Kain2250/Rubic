package ru.skhool21.rubik.service;

import ru.skhool21.rubik.model.Cub;

public interface Rotator {
    void frontClockwise(Cub cub);
    void frontCounterClockwise(Cub cub);

    void backClockwise(Cub cub);
    void backCounterClockwise(Cub cub);

    void leftClockwise(Cub cub);
    void leftCounterClockwise(Cub cub);

    void rightClockwise(Cub cub);
    void rightCounterClockwise(Cub cub);

    void upClockwise(Cub cub);
    void upCounterClockwise(Cub cub);

    void downClockwise(Cub cub);
    void downCounterClockwise(Cub cub);

}
