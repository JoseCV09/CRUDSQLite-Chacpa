package xyz.yoandroid.crudsqlite_chacpa.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import xyz.yoandroid.crudsqlite_chacpa.BD.DatabaseSQLite;
import xyz.yoandroid.crudsqlite_chacpa.BD.SqliteStringHelpers;
import xyz.yoandroid.crudsqlite_chacpa.Presenter.IPresenterDetail;
import xyz.yoandroid.crudsqlite_chacpa.Presenter.IPresenterMain;
import xyz.yoandroid.crudsqlite_chacpa.View.IDetailView;
import xyz.yoandroid.crudsqlite_chacpa.View.IMainView;
import xyz.yoandroid.crudsqlite_chacpa.View.MainActivity;

public class Alumno implements IAlumno {

    int ID;
    String nombre;
    String correo;
    String codigo;

    IPresenterDetail presenterDetail;
    IDetailView detailView;
    IPresenterMain presenterMain;
    IMainView viewMain;


    public Alumno() {
    }

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public Alumno(IPresenterMain presenterMain, IMainView interfaceMainView) {
        this.presenterMain=presenterMain;
        this.viewMain=interfaceMainView;
    }

    public Alumno(IPresenterDetail presenterDetail, IDetailView detailView) {
        this.presenterDetail=presenterDetail;
        this.detailView=detailView;
    }

    public Alumno(int id, String nombre, String correo, String codigo) {
        this.ID=id;
        this.nombre = nombre;
        this.correo = correo;
        this.codigo = codigo;
    }

    public Alumno(String nombre, String correo, String codigo) {
        this.nombre = nombre;
        this.correo = correo;
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public void insertAlumno(String nombre, String correo, String codigo) {

        try {
            DatabaseSQLite conn=new DatabaseSQLite((Context)detailView,"bd_empresa",null,1);
            SQLiteDatabase db=conn.getWritableDatabase();

            ContentValues values=new ContentValues();
            values.put(SqliteStringHelpers.CAMPO_NOMBRE,nombre);
            values.put(SqliteStringHelpers.CAMPO_CORREO,correo);
            values.put(SqliteStringHelpers.CAMPO_CODIGO,codigo);

            long result= db.insert(SqliteStringHelpers.TABLA_ALUMNO,null,values);

            String message;

            if(result==-1) {
                message="Error al Insertar!";
            }
            else {
                message="Alumno registrado correctamente!";
            }
            db.close();
            conn.close();
            presenterDetail.showMessage(message);
            presenterDetail.goActivityAdd(MainActivity.class);

        }catch (Exception ex) {
            presenterDetail.showMessage(ex.getMessage());
            presenterDetail.goActivityAdd(MainActivity.class);
        }
    }

    @Override
    public ArrayList<Alumno> getAllAlumno() {
        try {
            DatabaseSQLite conn=new DatabaseSQLite((Context)viewMain,"bd_empresa",null,1);
            SQLiteDatabase db=conn.getReadableDatabase();
            Cursor cursor=db.rawQuery(SqliteStringHelpers.SELECT_ALL_ALUMNO,null);
            if(cursor.getCount()==0) {
                return null;
            }
            else {
                ArrayList<Alumno> alumnos=new ArrayList<>();
                while(cursor.moveToNext()) {
                    Alumno alumno=new Alumno();
                    alumno.setId(cursor.getInt(0));
                    alumno.setNombre(cursor.getString(1));
                    alumno.setCorreo(cursor.getString(2));
                    alumno.setCodigo(cursor.getString(3));
                    alumnos.add(alumno);
                }
                return alumnos;
            }
        }catch (Exception ex){
            return  null;
        }
    }

    @Override
    public void updateAlumno(String id, String nombre, String correo, String codigo) {
       try {
           DatabaseSQLite conn=new DatabaseSQLite((Context)detailView,"bd_empresa",null,1);
           SQLiteDatabase db=conn.getWritableDatabase();

           String[] myId={id};
           ContentValues values=new ContentValues();
           values.put(SqliteStringHelpers.CAMPO_NOMBRE,nombre);
           values.put(SqliteStringHelpers.CAMPO_CORREO,correo);
           values.put(SqliteStringHelpers.CAMPO_CODIGO,codigo);
           db.update(SqliteStringHelpers.TABLA_ALUMNO,values,"id=?",myId);
           db.close();
           conn.close();
           presenterDetail.showMessage("Alumno Modificado Correctamente!");
           presenterDetail.goActivityAdd(MainActivity.class);
       }catch (Exception ex){
           presenterDetail.showMessage(ex.getMessage());
       }
    }

    @Override
    public void deleteAlumno(String id, String nombre) {

        DatabaseSQLite conn=new DatabaseSQLite((Context)detailView,"bd_empresa",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();

        String[] myId={id};

        db.delete(SqliteStringHelpers.TABLA_ALUMNO,"id=?",myId);

        db.close();
        conn.close();

        presenterDetail.showMessage("Eliminaste a: "+ nombre);

        presenterDetail.goActivityAdd(MainActivity.class);


    }
}
