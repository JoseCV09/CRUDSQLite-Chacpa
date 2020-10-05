package xyz.yoandroid.crudsqlite_chacpa.Model;

import java.util.ArrayList;

public interface IAlumno {

    void insertAlumno(String nombre, String correo, String codigo);

    ArrayList<Alumno> getAllAlumno();

    void updateAlumno(String id, String nombre, String correo, String codigo);

    void deleteAlumno(String id, String nombre);

}
