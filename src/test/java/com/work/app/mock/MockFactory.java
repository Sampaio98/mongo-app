package com.work.app.mock;

import com.work.app.domain.City;
import com.work.app.domain.State;

public class MockFactory {

    public static City mockCity() {
        return new City("Londrina", new State("PR"));
    }
    public static City mockCity2() {
        return new City("Florianópolis", new State("SC"));
    }

}
