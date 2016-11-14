package ovh.pacordonnier.telecommandevolet;

import android.content.Context;

import java.util.List;

import ovh.pacordonnier.telecommandevolet.model.Room;

/**
 * Created by pcordonnier up 13/11/16.
 */

public interface MainView {
    void resultGet(List<Room> rooms);

    void resultOK();

    void resultNotOk();

    Context getContext();
}
