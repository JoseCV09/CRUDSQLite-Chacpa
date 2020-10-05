package xyz.yoandroid.crudsqlite_chacpa.View;

public interface IDetailView {

    void setModeDetail(int id, String nombre, String correo, String codigo);

    void setModeEdit();

    void setAddAlumno();

   void showAction();

    void insertAlumno(String nombre, String correo, String codigo);

    void showMessage(String mensaje);

    void goActivityAdd(Class clase);

    void showErrorNombre(String message);
    void showErrorCorreo(String message);
    void showErrorCodigo(String message);
    void showErrorCorreoValid(String message);


}
