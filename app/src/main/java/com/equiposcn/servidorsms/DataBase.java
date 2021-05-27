package com.equiposcn.servidorsms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Zodiaco_Calendario_Azteca";
    private static final String TABLE_NAME = "Zodiaco";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_SIGNO = "signo";
    private static final String COLUMN_PLANETA = "planeta";
    private static final String COLUMN_DIVINIDAD = "divinidad";

    private static final String TABLE_CAL = "Calendario";
    private static final String COLUMN_SIMBOLO = "simbolo";
    private static final String COLUMN_DIOS = "dios";
    private static final String COLUMN_DESC = "descripción";

    private static final String TABLE_FECHAS = "Fechas";
    private static final String COLUMN_FECHA = "fecha";
    private static final String COLUMN_FKZ = "id_zodiaco";
    private static final String COLUMN_FKC = "id_calendario";

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //CREACIÓN DE LAS TABLAS Y LOS INSERTS
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_ZODIACO ="CREATE TABLE " + TABLE_NAME + "( " + COLUMN_ID +" CHAR(3) PRIMARY KEY, "+
                COLUMN_SIGNO +" TEXT, "+COLUMN_PLANETA+" TEXT, "+COLUMN_DIVINIDAD+" TEXT)";
        String CREATE_TABLE_CALEN= "CREATE TABLE " + TABLE_CAL + "( " + COLUMN_ID +" CHAR(3) PRIMARY KEY, "+
                COLUMN_SIMBOLO + " TEXT, "+COLUMN_DIOS +" TEXT, "+COLUMN_DESC+" TEXT)";
        String CREATE_TABLE_FECHAS= "CREATE TABLE " + TABLE_FECHAS + "( " + COLUMN_FECHA +" CHAR(5) PRIMARY KEY, "+
                COLUMN_FKZ + " CHAR(3), "+COLUMN_FKC +" CHAR(3), " +
                " FOREIGN KEY("+COLUMN_FKZ+") REFERENCES "+TABLE_NAME +" ("+COLUMN_ID+")," +
                "FOREIGN KEY("+COLUMN_FKC+") REFERENCES "+TABLE_CAL +" ("+COLUMN_ID+"))";
        db.execSQL(CREATE_TABLE_ZODIACO);
        db.execSQL(CREATE_TABLE_CALEN);
        db.execSQL(CREATE_TABLE_FECHAS);


        //TABLAS DE SIGNOS DEL ZODIACO
        db.execSQL("insert into "+TABLE_NAME+" values ('Z1', 'CAIMÁN O COCODRILO (CIPACTLI)', 'Venus','Tonacatecuhtli')");
        db.execSQL("insert into "+TABLE_NAME+" values ('Z2', 'CASA (CALLI)', 'Venus','Tepeyolohti')");
        db.execSQL("insert into "+TABLE_NAME+" values ('Z3', 'FLOR (XOCHITI)', 'Venus','Xochiquetzal')");
        db.execSQL("insert into "+TABLE_NAME+" values ('Z4', 'SERPIENTE (CÓATL)', 'Saturno','Chalchiúhtlicue')");
        db.execSQL("insert into "+TABLE_NAME+" values ('Z5', 'JAGUAR (OCÉLOTL)', 'Júpiter','Tlazoltéotl')");
        db.execSQL("insert into "+TABLE_NAME+" values ('Z6', 'CAÑA (ACATL)', 'Júpiter','Tezcatlipoca')");
        db.execSQL("insert into "+TABLE_NAME+" values ('Z7', 'CONEJO (TOCHTLI)', 'La Luna','Mayáhuel')");
        db.execSQL("insert into "+TABLE_NAME+" values ('Z8', 'ÁGUILA (CUAYHTLI) ', 'El Sol','Xipe Totec')");
        db.execSQL("insert into "+TABLE_NAME+" values ('Z9', 'MONO (OZOMATLI)', 'Marte','Xochipilli')");
        db.execSQL("insert into "+TABLE_NAME+" values ('Z10', 'SÍLEX (TÉCPATL)', 'Mercurio','Tezcatlipoca')");
        db.execSQL("insert into "+TABLE_NAME+" values ('Z11', 'PERRO (ITZCUINTLI)', 'Marte','Mictlantecuhtli')");
        db.execSQL("insert into "+TABLE_NAME+" values ('Z12', 'CIERVO (MÁZATL)', ' La Luna','Mayáhuel ')");


        //TABLAS CALENDARIO
        db.execSQL("insert into "+TABLE_CAL+" values ('C1', 'Cocodrilo', 'Tonacatecuhtli, “Señor que nos da el sustento”','Representa al dios anciano, que es la deificación de la tierra')");
        db.execSQL("insert into "+TABLE_CAL+" values ('C2', 'Viento', ' Ehécatl, “Viento”','Representa a Dios Viento Creador, que es la deificación de la vida')");
        db.execSQL("insert into "+TABLE_CAL+" values ('C3', 'Casa', 'Tepeyólotl “Corazón del Monte”','Representa a Dios Jaguar, que es la deificación del Inframundo')");
        db.execSQL("insert into "+TABLE_CAL+" values ('C4', 'Lagartija', 'Huehuecóyotl, “Coyote Viejo”','epresenta a Dios Venado, que es la deificación del Cielo')");
        db.execSQL("insert into "+TABLE_CAL+" values ('C5', 'Serpiente', 'Chalchihutlicue, “La de la falda de jades”','Representa a Diosa Serpiente, que es la deificación del mar')");
        db.execSQL("insert into "+TABLE_CAL+" values ('C6', 'Cráneo', 'Texitécatl-Mextli, “La Luna Oscura”','Representa a Diosa Oscuridad, que es la Luna antes de la creación del mundo')");
        db.execSQL("insert into "+TABLE_CAL+" values ('C7', 'Venado', 'Tláloc, “Néctar de la Tierra”','Representa a Dios Lluvia, que es la deificación de la lluvia')");
        db.execSQL("insert into "+TABLE_CAL+" values ('C8', 'Conejo', 'Mayahuel, “El maguey que rodea (a la tierra)”','Representa a Diosa Maguey, que es la deificación del Supramundo')");
        db.execSQL("insert into "+TABLE_CAL+" values ('C9', 'Agua', 'Xiuhtecuhtli, “Señor Fuego””','RRepresenta a Dios Fuego, que es la deificación del fuego')");
        db.execSQL("insert into "+TABLE_CAL+" values ('C10', 'Perro', 'Mictlantecuhtli, “El señor de los descarnados”','Representa a Dios Muerte, que es la deificación de la muerte')");
        db.execSQL("insert into "+TABLE_CAL+" values ('C11', 'Mono', ' Xochipilli, “Flor-Niño”','Representa a Dios Florecimiento, que es la deificación de la Estación Florida')");
        db.execSQL("insert into "+TABLE_CAL+" values ('C12', 'Hierba', 'Patécatl, “El que es medicinal”','Representa a Dios Luna en su tránsito por el Mundo de los Vivos')");
        db.execSQL("insert into "+TABLE_CAL+" values ('C13', 'Caña', 'Tezcatlipoca, “Reflejo Negro”','Representa a Dios Reflejo Negro, que es la deificación del Cielo Diurno')");
        db.execSQL("insert into "+TABLE_CAL+" values ('C14', 'Jaguar', 'Tlazoltéotl, “La devoradora de la oscuridad”','Representa a Diosa Luna, que es la deificación de la Luna en su tránsito por el Mundo de los Muertos')");
        db.execSQL("insert into "+TABLE_CAL+" values ('C15', 'Águila Arpía', 'Xipetótec, “Dios Desollador”','Representa a Dios Desollador, que es la deificación del Cielo Nocturno')");
        db.execSQL("insert into "+TABLE_CAL+" values ('C16', 'Zopilote', 'Itzpápalotl, “Mariposa de Obsidiana”','Representa a Dios Mariposa de Obsidiana, que es la deificación de la Estación de Sacrificio')");
        db.execSQL("insert into "+TABLE_CAL+" values ('C17', 'Movimiento', 'Nanahuatzin, “El que es cuidado por su madre”','Representa a Dios Enfermo, que es la deificación del Sol antes de la creación del mundo')");
        db.execSQL("insert into "+TABLE_CAL+" values ('C18', 'Pedernal', ' Chalchiuhtotolin, “Guajolote Precioso”','Representa a Dios Guajolote, que es la deificación del Sol en su tránsito por el Mundo de los Muertos')");
        db.execSQL("insert into "+TABLE_CAL+" values ('C19', 'Lluvia', 'Tonatiuh, “El que nos alumbra”','Representa a Dios Sol, que es la deificación del Sol')");
        db.execSQL("insert into "+TABLE_CAL+" values ('C20', 'Flor', 'Xochiquetzal, “Flor Preciosa o Flor-Quetzal”','Representa a Diosa Quetzal que es la deificación de la madre del Sol')");

        //TABLAS DE FECHAS
        //********************************* E N E R O **********************************************
        db.execSQL("insert into "+TABLE_FECHAS+" values ('01/01', 'Z9', 'C1')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('02/01', 'Z10', 'C2')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('03/01', 'Z11', 'C3')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('04/01', 'Z1', 'C4')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('05/01', 'Z2', 'C5')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('06/01', 'Z3', 'C6')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('07/01', 'Z4', 'C7')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('08/01', 'Z12', 'C8')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('09/01', 'Z5', 'C9')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('10/01', 'Z6', 'C10')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('11/01', 'Z7', 'C11')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('12/01', 'Z8', 'C12')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('13/01', 'Z9', 'C13')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('14/01', 'Z10', 'C14')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('15/01', 'Z11', 'C15')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('16/01', 'Z1', 'C16')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('17/01', 'Z2', 'C17')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('18/01', 'Z3', 'C18')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('19/01', 'Z4', 'C19')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('20/01', 'Z12', 'C20')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('21/01', 'Z5', 'C1')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('22/01', 'Z6', 'C2')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('23/01', 'Z7', 'C3')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('24/01', 'Z8', 'C4')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('25/01', 'Z9', 'C5')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('26/01', 'Z10', 'C6')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('27/01', 'Z11', 'C7')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('28/01', 'Z1', 'C8')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('29/01', 'Z2', 'C9')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('30/01', 'Z3', 'C10')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('31/01', 'Z4', 'C11')");

        //********************************* F E B R E R O ******************************************
        db.execSQL("insert into "+TABLE_FECHAS+" values ('01/02', 'Z12', 'C1')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('02/02', 'Z1', 'C2')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('03/02', 'Z2', 'C3')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('04/02', 'Z3', 'C4')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('05/02', 'Z4', 'C5')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('06/02', 'Z12', 'C6')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('07/02', 'Z5', 'C7')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('08/02', 'Z6', 'C8')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('09/02', 'Z7', 'C9')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('10/02', 'Z8', 'C10')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('11/02', 'Z9', 'C11')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('12/02', 'Z10', 'C12')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('13/02', 'Z11', 'C13')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('14/02', null, 'C14')"); //NO HAY ZODIACO
        db.execSQL("insert into "+TABLE_FECHAS+" values ('15/02', 'Z2', 'C15')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('16/02', 'Z3', 'C16')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('17/02', 'Z4', 'C17')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('18/02', 'Z12', 'C18')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('19/02', 'Z5', 'C19')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('20/02', 'Z6', 'C20')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('21/02', 'Z7', 'C1')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('22/02', 'Z8', 'C2')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('23/02', 'Z9', 'C3')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('24/02', 'Z10', 'C4')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('25/02', 'Z11', 'C5')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('26/02', null, 'C6')");//NO HAY ZODIACO
        db.execSQL("insert into "+TABLE_FECHAS+" values ('27/02', 'Z2', 'C7')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('28/02', 'Z3', 'C8')");

        //********************************* M A R Z O **********************************************
        db.execSQL("insert into "+TABLE_FECHAS+" values ('01/03', 'Z4', 'C1')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('02/03', 'Z12', 'C2')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('03/03', 'Z5', 'C3')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('04/03', 'Z6', 'C4')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('05/03', 'Z7', 'C5')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('06/03', 'Z8', 'C6')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('07/03', 'Z9', 'C7')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('08/03', 'Z10', 'C8')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('09/03', 'Z11', 'C9')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('10/03', 'Z1', 'C10')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('11/03', 'Z2', 'C11')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('12/03', 'Z3', 'C12')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('13/03', 'Z4', 'C13')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('14/03', 'Z12', 'C14')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('15/03', 'Z5', 'C15')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('16/03', 'Z6', 'C16')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('17/03', 'Z7', 'C17')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('18/03', 'Z8', 'C18')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('19/03', 'Z9', 'C19')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('20/03', 'Z10', 'C20')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('21/03', 'Z11', 'C1')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('22/03', 'Z1', 'C2')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('23/03', 'Z2', 'C3')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('24/03', 'Z3', 'C4')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('25/03', 'Z4', 'C5')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('26/03', 'Z12', 'C6')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('27/03', 'Z5', 'C7')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('28/03', 'Z6', 'C8')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('29/03', 'Z7', 'C9')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('30/03', 'Z8', 'C10')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('31/03', 'Z9', 'C11')");

        //********************************* A B R I L **********************************************
        db.execSQL("insert into "+TABLE_FECHAS+" values ('01/04', 'Z10', 'C1')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('02/04', 'Z11', 'C2')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('03/04', 'Z1', 'C3')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('04/04', 'Z2', 'C4')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('05/04', 'Z3', 'C5')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('06/04', 'Z4', 'C6')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('07/04', 'Z12', 'C7')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('08/04', 'Z5', 'C8')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('09/04', 'Z6', 'C9')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('10/04', 'Z7', 'C10')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('11/04', 'Z8', 'C11')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('12/04', 'Z9', 'C12')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('13/04', 'Z10', 'C13')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('14/04', 'Z11', 'C14')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('15/04', 'Z1', 'C15')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('16/04', 'Z2', 'C16')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('17/04', 'Z3', 'C17')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('18/04', 'Z4', 'C18')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('19/04', 'Z12', 'C19')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('20/04', 'Z5', 'C20')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('21/04', 'Z6', 'C1')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('22/04', 'Z7', 'C2')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('23/04', 'Z8', 'C3')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('24/04', 'Z9', 'C4')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('25/04', 'Z10', 'C5')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('26/04', 'Z11', 'C6')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('27/04', 'Z1', 'C7')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('28/04', 'Z2', 'C8')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('29/04', 'Z3', 'C9')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('30/04', 'Z4', 'C10')");

        //********************************* M A Y O ************************************************
        db.execSQL("insert into "+TABLE_FECHAS+" values ('01/05', 'Z12', 'C1')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('02/05', 'Z5', 'C2')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('03/05', 'Z6', 'C3')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('04/05', 'Z7', 'C4')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('05/05', 'Z8', 'C5')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('06/05', 'Z9', 'C6')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('07/05', 'Z10', 'C7')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('08/05', 'Z11', 'C8')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('09/05', 'Z1', 'C9')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('10/05', 'Z2', 'C10')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('11/05', 'Z3', 'C11')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('12/05', 'Z4', 'C12')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('13/05', 'Z12', 'C13')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('14/05', 'Z5', 'C14')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('15/05', 'Z6', 'C15')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('16/05', 'Z7', 'C16')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('17/05', 'Z8', 'C17')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('18/05', 'Z9', 'C18')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('19/05', 'Z10', 'C19')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('20/05', 'Z11', 'C20')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('21/05', 'Z1', 'C1')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('22/05', 'Z2', 'C2')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('23/05', 'Z3', 'C3')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('24/05', 'Z4', 'C4')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('25/05', 'Z12', 'C5')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('26/05', 'Z5', 'C6')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('27/05', 'Z6', 'C7')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('28/05', 'Z7', 'C8')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('29/05', 'Z8', 'C9')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('30/05', 'Z9', 'C10')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('31/05', 'Z10', 'C11')");

        //********************************* J U N I O **********************************************
        db.execSQL("insert into "+TABLE_FECHAS+" values ('01/06', 'Z11', 'C1')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('02/06', 'Z1', 'C2')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('03/06', 'Z2', 'C3')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('04/06', 'Z3', 'C4')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('05/06', 'Z4', 'C5')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('06/06', 'Z12', 'C6')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('07/06', 'Z5', 'C7')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('08/06', 'Z6', 'C8')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('09/06', 'Z7', 'C9')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('10/06', 'Z8', 'C10')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('11/06', 'Z9', 'C11')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('12/06', 'Z10', 'C12')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('13/06', 'Z11', 'C13')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('14/06', 'Z1', 'C14')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('15/06', 'Z2', 'C15')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('16/06', 'Z3', 'C16')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('17/06', 'Z4', 'C17')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('18/06', 'Z12', 'C18')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('19/06', 'Z5', 'C19')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('20/06', 'Z6', 'C20')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('21/06', 'Z7', 'C1')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('22/06', 'Z8', 'C2')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('23/06', 'Z9', 'C3')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('24/06', 'Z10', 'C4')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('25/06', 'Z11', 'C5')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('26/06', 'Z1', 'C6')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('27/06', 'Z2', 'C7')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('28/06', 'Z3', 'C8')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('29/06', 'Z4', 'C9')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('30/06', 'Z12', 'C10')");

        //********************************* J U L I O **********************************************
        db.execSQL("insert into "+TABLE_FECHAS+" values ('01/07', 'Z5', 'C1')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('02/07', 'Z6', 'C2')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('03/07', 'Z7', 'C3')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('04/07', 'Z8', 'C4')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('05/07', 'Z9', 'C5')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('06/07', 'Z10', 'C6')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('07/07', 'Z11', 'C7')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('08/07', 'Z1', 'C8')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('09/07', 'Z2', 'C9')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('10/07', 'Z3', 'C10')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('11/07', 'Z4', 'C11')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('12/07', 'Z12', 'C12')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('13/07', 'Z5', 'C13')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('14/07', 'Z6', 'C14')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('15/07', 'Z7', 'C15')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('16/07', 'Z8', 'C16')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('17/07', 'Z9', 'C17')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('18/07', 'Z10', 'C18')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('19/07', 'Z11', 'C19')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('20/07', 'Z1', 'C20')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('21/07', 'Z2', 'C1')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('22/07', 'Z3', 'C2')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('23/07', 'Z4', 'C3')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('24/07', 'Z12', 'C4')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('25/07', 'Z5', 'C5')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('26/07', 'Z6', 'C6')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('27/07', 'Z7', 'C7')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('28/07', 'Z8', 'C8')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('29/07', 'Z9', 'C9')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('30/07', 'Z10', 'C10')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('31/07', 'Z11', 'C11')");

        //********************************* A G O S T O ********************************************
        db.execSQL("insert into "+TABLE_FECHAS+" values ('01/08', 'Z1', 'C1')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('02/08', 'Z2', 'C2')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('03/08', 'Z3', 'C3')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('04/08', 'Z4', 'C4')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('05/08', 'Z12', 'C5')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('06/08', 'Z5', 'C6')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('07/08', 'Z6', 'C7')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('08/08', 'Z7', 'C8')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('09/08', 'Z8', 'C9')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('10/08', 'Z9', 'C10')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('11/08', 'Z10', 'C11')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('12/08', 'Z11', 'C12')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('13/08', 'Z1', 'C13')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('14/08', 'Z2', 'C14')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('15/08', 'Z3', 'C15')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('16/08', 'Z4', 'C16')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('17/08', 'Z12', 'C17')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('18/08', 'Z5', 'C18')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('19/08', 'Z6', 'C19')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('20/08', 'Z7', 'C20')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('21/08', 'Z8', 'C1')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('22/08', 'Z9', 'C2')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('23/08', 'Z10', 'C3')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('24/08', 'Z11', 'C4')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('25/08', 'Z1', 'C5')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('26/08', 'Z2', 'C6')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('27/08', 'Z3', 'C7')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('28/08', 'Z4', 'C8')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('29/08', 'Z12', 'C9')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('30/08', 'Z5', 'C10')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('31/08', 'Z6', 'C11')");

        //******************************* S E P T I E M B R E **************************************
        db.execSQL("insert into "+TABLE_FECHAS+" values ('01/09', 'Z7', 'C1')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('02/09', 'Z8', 'C2')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('03/09', 'Z9', 'C3')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('04/09', 'Z10', 'C4')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('05/09', 'Z11', 'C5')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('06/09', 'Z1', 'C6')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('07/09', 'Z2', 'C7')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('08/09', 'Z3', 'C8')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('09/09', 'Z4', 'C9')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('10/09', 'Z12', 'C10')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('11/09', 'Z5', 'C11')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('12/09', 'Z6', 'C12')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('13/09', 'Z7', 'C13')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('14/09', 'Z8', 'C14')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('15/09', 'Z9', 'C15')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('16/09', 'Z10', 'C16')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('17/09', 'Z11', 'C17')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('18/09', 'Z1', 'C18')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('19/09', 'Z2', 'C19')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('20/09', 'Z3', 'C20')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('21/09', 'Z4', 'C1')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('22/09', 'Z12', 'C2')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('23/09', 'Z5', 'C3')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('24/09', 'Z6', 'C4')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('25/09', 'Z7', 'C5')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('26/09', 'Z8', 'C6')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('27/09', 'Z9', 'C7')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('28/09', 'Z10', 'C8')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('29/09', 'Z11', 'C9')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('30/09', 'Z1', 'C10')");

        //********************************** O C T U B R E *****************************************
        db.execSQL("insert into "+TABLE_FECHAS+" values ('01/10', 'Z2', 'C1')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('02/10', 'Z3', 'C2')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('03/10', 'Z4', 'C3')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('04/10', 'Z12', 'C4')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('05/10', 'Z5', 'C5')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('06/10', 'Z6', 'C6')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('07/10', 'Z7', 'C7')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('08/10', 'Z8', 'C8')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('09/10', 'Z9', 'C9')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('10/10', 'Z10', 'C10')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('11/10', 'Z11', 'C11')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('12/10', 'Z1', 'C12')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('13/10', 'Z2', 'C13')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('14/10', 'Z3', 'C14')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('15/10', 'Z4', 'C15')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('16/10', 'Z12', 'C16')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('17/10', 'Z5', 'C17')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('18/10', 'Z6', 'C18')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('19/10', 'Z7', 'C19')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('20/10', 'Z8', 'C20')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('21/10', 'Z9', 'C1')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('22/10', 'Z10', 'C2')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('23/10', 'Z11', 'C3')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('24/10', 'Z1', 'C4')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('25/10', 'Z2', 'C5')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('26/10', 'Z3', 'C6')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('27/10', 'Z4', 'C7')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('28/10', 'Z12', 'C8')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('29/10', 'Z5', 'C9')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('30/10', 'Z6', 'C10')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('31/10', 'Z7', 'C11')");

        //******************************* N O V I E M B R E ****************************************
        db.execSQL("insert into "+TABLE_FECHAS+" values ('01/11', 'Z8', 'C1')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('02/11', 'Z9', 'C2')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('03/11', 'Z10', 'C3')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('04/11', 'Z11', 'C4')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('05/11', 'Z1', 'C5')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('06/11', 'Z2', 'C6')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('07/11', 'Z3', 'C7')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('08/11', 'Z4', 'C8')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('09/11', 'Z12', 'C9')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('10/11', 'Z5', 'C10')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('11/11', 'Z6', 'C11')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('12/11', 'Z7', 'C12')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('13/11', 'Z8', 'C13')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('14/11', 'Z9', 'C14')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('15/11', 'Z10', 'C15')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('16/11', 'Z11', 'C16')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('17/11', 'Z1', 'C17')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('18/11', 'Z2', 'C18')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('19/11', 'Z3', 'C19')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('20/11', 'Z4', 'C20')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('21/11', 'Z12', 'C1')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('22/11', 'Z5', 'C2')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('23/11', 'Z6', 'C3')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('24/11', 'Z7', 'C4')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('25/11', 'Z8', 'C5')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('26/11', 'Z9', 'C6')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('27/11', 'Z10', 'C7')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('28/11', 'Z11', 'C8')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('29/11', 'Z1', 'C9')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('30/11', 'Z2', 'C10')");

        //******************************* D I C I E M B R E ****************************************
        db.execSQL("insert into "+TABLE_FECHAS+" values ('01/12', 'Z3', 'C1')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('02/12', 'Z4', 'C2')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('03/12', 'Z12', 'C3')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('04/12', 'Z5', 'C4')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('05/12', 'Z6', 'C5')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('06/12', 'Z7', 'C6')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('07/12', 'Z8', 'C7')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('08/12', 'Z9', 'C8')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('09/12', 'Z10', 'C9')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('10/12', 'Z11', 'C10')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('11/12', 'Z1', 'C11')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('12/12', 'Z2', 'C12')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('13/12', 'Z3', 'C13')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('14/12', 'Z4', 'C14')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('15/12', 'Z12', 'C15')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('16/12', 'Z5', 'C16')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('17/12', 'Z6', 'C17')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('18/12', 'Z7', 'C18')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('19/12', 'Z8', 'C19')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('20/12', 'Z9', 'C20')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('21/12', 'Z10', 'C1')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('22/12', 'Z11', 'C2')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('23/12', 'Z1', 'C3')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('24/12', 'Z2', 'C4')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('25/12', 'Z3', 'C5')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('26/12', 'Z4', 'C6')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('27/12', 'Z12', 'C7')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('28/12', 'Z5', 'C8')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('29/12', 'Z6', 'C9')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('30/12', 'Z7', 'C10')");
        db.execSQL("insert into "+TABLE_FECHAS+" values ('31/12', 'Z8', 'C11')");


    }

    //Actualiza la tabla
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Borrar la tabla anterior
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FECHAS);
        //Creamos de nuevo la tabla
        onCreate(db);

    }

    //BUSCA EN ZODDIACO
    public String[] getZodiaco(String fecha){
        String[] list = new String [3];
        String query = "Select * from "+ TABLE_FECHAS + " inner join "+TABLE_NAME + " on "+TABLE_FECHAS+"."+COLUMN_FKZ+"="+TABLE_NAME+"."+COLUMN_ID+
                " where "+COLUMN_FECHA+"= '"+fecha+"';";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();
        list[0]=cursor.getString(4);
        list[1]=cursor.getString(5);
        list[2]=cursor.getString(6);
        cursor.close();
        sqLiteDatabase.close();
        return list;
    }

    //BUSCA EN CALENDARIO
    public String[] getCalendario(String fecha){
        String[] list = new String [3];
        String query = "Select * from "+ TABLE_FECHAS + " inner join "+TABLE_CAL+ " on "+TABLE_FECHAS+"."+COLUMN_FKC+"="+TABLE_CAL+"."+COLUMN_ID+
                " where "+COLUMN_FECHA+"= '"+fecha+"';";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();
        list[0]=cursor.getString(4);
        list[1]=cursor.getString(5);
        list[2]=cursor.getString(6);
        cursor.close();
        sqLiteDatabase.close();
        return list;
    }
}
