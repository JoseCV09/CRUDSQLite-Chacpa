package xyz.yoandroid.crudsqlite_chacpa.BD;

public class SqliteStringHelpers {

    public static final String CREATE_TABLE_ALUMNO="CREATE TABLE " +
            "alumno(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,nombre TEXT,correo TEXT,codigo TEXT)";

    public static final String CAMPO_ID="id";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_CORREO="correo";
    public static final String CAMPO_CODIGO="codigo";
    public static final String TABLA_ALUMNO="alumno";


    public static final String SELECT_ALL_ALUMNO="SELECT * FROM alumno";

}
