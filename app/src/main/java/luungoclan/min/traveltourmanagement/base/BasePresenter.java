package luungoclan.min.traveltourmanagement.base;

public class BasePresenter<T extends IBaseView> {
    public T view;

    public BasePresenter(T view) {
        this.view = view;
    }
}
