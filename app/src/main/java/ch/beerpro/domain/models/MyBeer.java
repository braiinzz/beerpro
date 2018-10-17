package ch.beerpro.domain.models;

import ch.beerpro.domain.models.Beer;

import java.util.Date;

public interface MyBeer {
    FridgeItem getFridgeItem();
    void setFridgeItem(FridgeItem fridgeItem);
    String getBeerId();

    Beer getBeer();

    Date getDate();
}
