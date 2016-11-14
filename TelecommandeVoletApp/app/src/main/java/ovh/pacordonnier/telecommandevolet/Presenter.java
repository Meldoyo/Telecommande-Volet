package ovh.pacordonnier.telecommandevolet;

/**
 * Created by pcordonnier up 13/11/16.
 */

public interface Presenter<V> {
    void attachView(V view);

    void detachView();
}
