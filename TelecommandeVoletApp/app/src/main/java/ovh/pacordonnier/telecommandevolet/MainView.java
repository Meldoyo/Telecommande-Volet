package ovh.pacordonnier.telecommandevolet;

import android.content.Context;

/**
 * Created by pcordonnier on 13/11/16.
 */

public interface MainView {
    void resultOK();

    void resultNotOk();

    Context getContext();
}
