package ovh.pacordonnier.telecommandevolet;

/**
 * Created by pcordonnier on 13/11/16.
 */

public interface Presenter<V> {
    void attachView(V view);

    void detachView();
}
