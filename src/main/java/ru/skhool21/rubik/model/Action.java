package ru.skhool21.rubik.model;

import org.jetbrains.annotations.NotNull;

public enum Action {
	U("upClockwise"), U_("upCounterClockwise"),
	D("downClockwise"), D_("downCounterClockwise"),
	B("backClockwise"), B_("backCounterClockwise"),
	F("frontClockwise"), F_("frontCounterClockwise"),
	L("leftClockwise"), L_("leftCounterClockwise"),
	R("rightClockwise"), R_("rightCounterClockwise"),
	U2("upDoubleClockwise"), U2_("upDoubleCounterClockwise"),
	D2("downDoubleClockwise"), D2_("downDoubleCounterClockwise"),
	B2("backDoubleClockwise"), B2_("backDoubleCounterClockwise"),
	F2("frontDoubleClockwise"), F2_("frontDoubleCounterClockwise"),
	L2("leftDoubleClockwise"), L2_("leftDoubleCounterClockwise"),
	R2("rightDoubleClockwise"), R2_("rightDoubleCounterClockwise");

	private final String name;

	Action(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@NotNull
	public static Action fromId(int id) {
		for(Action action : Action.values()){
			if(action.ordinal() == id){
				return action;
			}
		}
		return null;
	}
}
