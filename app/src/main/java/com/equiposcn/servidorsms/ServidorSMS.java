package com.equiposcn.servidorsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ServidorSMS extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        Bundle bundle = intent.getExtras();
        SmsMessage[] smsMessages = null;
        String telefono = "";
        String cuerpoMensaje = "";

        if(null != bundle){
            String informacion = "SMS recibido desde: ";
            Object [] pdus = (Object[]) bundle.get("pdus");
            smsMessages = new SmsMessage[pdus.length];
            for(int i=0; i<smsMessages.length; i++){
                smsMessages[i]= SmsMessage.createFromPdu((byte[])pdus[i]);

                telefono = smsMessages[i].getOriginatingAddress();
                cuerpoMensaje= smsMessages[i].getMessageBody().toString();
                informacion = informacion + telefono +"\n";
                informacion = informacion + "************* MENSAJE *****************\n";
                informacion = informacion + cuerpoMensaje;
            }
            Toast.makeText(context, informacion, Toast.LENGTH_SHORT).show();

            System.out.println("NUMERO: "+telefono);
            System.out.println("MENSAJE: "+cuerpoMensaje);

            analizarMensaje(cuerpoMensaje, telefono, context);
        }
    }

    /*
     * 04/01/1999, Horoscopo
     * */
    public void analizarMensaje(String mensaje, String telefono, Context context){
        String[] datos= null;
        String fecha= null;
        String servicio= null;

        if(mensaje!=null && mensaje!=""){
            datos  = mensaje.split("[,]",0);
            if(datos.length==2){
                fecha = datos[0].substring(0,5);
                servicio = datos[1].trim().toLowerCase();
                obtenerDatos(servicio, telefono, fecha,context);
            }else if(datos.length==1){
                // Solo se proporciono el tipo de servicio, por lo que se muestra el Horoscopo/Calendario la fecha actual
                Date c = Calendar.getInstance().getTime();
                System.out.println("Current time => " + c);

                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy");
                String formattedDate = df.format(c);

                fecha = formattedDate.substring(0,5).replace('-','/');
                System.out.println("FECHA;"+fecha);

                servicio = datos[0].trim().toLowerCase();
                obtenerDatos(servicio, telefono, fecha,context);
            }
        }

    }

    public void obtenerDatos(String servicio, String telefono,String fecha, Context context){
        DataBase databaseHandler = new DataBase(context);
        databaseHandler.getReadableDatabase();
        String respuestaSMS = null;
        String[] result = null;
        String[] result2 = null;

        System.out.println(servicio+" ES EL SERVICIOOO");
        if(servicio.equalsIgnoreCase("horóscopo") || servicio.equalsIgnoreCase("horoscopo")){
            result = databaseHandler.getZodiaco(fecha);

        }else if(servicio.equalsIgnoreCase("calendario")){
            result = databaseHandler.getCalendario(fecha);
        }else if(servicio.equalsIgnoreCase("ambos")){
            result = databaseHandler.getCalendario(fecha);
            result2 = databaseHandler.getZodiaco(fecha);
        }
        if(result!=null && result2==null) {
            respuestaSMS = armarMensajeRespuesta(servicio, result);
            reenviaSMS(telefono,respuestaSMS);
        }else if(result!=null && result2!=null){
            respuestaSMS = armarMensajeRespuesta("calendario", result);
            reenviaSMS(telefono,respuestaSMS);

            respuestaSMS = armarMensajeRespuesta("horoscopo", result2);
            reenviaSMS(telefono,respuestaSMS);
        }
}

    private String armarMensajeRespuesta(String servicio, String[] result){
        String infoSimbolo = "Simbolo: "+result[0]+"\n";
        String diosProtector;
        String infAdicional;
        String respuestaSMS = null;

        if(servicio.equalsIgnoreCase("calendario")){
            diosProtector = "Dios protector: "+result[1]+"\n";
            infAdicional = "Extra: "+result[2]+"\n";
        }else{
            diosProtector = "Divinidad: "+result[2]+"\n";
            infAdicional = "Extra (Planeta): "+result[1]+"\n";
        }

        respuestaSMS = servicio.toUpperCase()+"\n"+infoSimbolo+diosProtector+infAdicional;
        return respuestaSMS;
    }

    private void reenviaSMS(String telefono, String mensaje){
        SmsManager smsManager= SmsManager.getDefault();
        ArrayList<String> msgArray = smsManager.divideMessage(mensaje);
        smsManager.sendMultipartTextMessage(telefono, null, msgArray, null, null);
        //smsManager.sendTextMessage(telefono, null, mensaje, null, null);
    }

}
