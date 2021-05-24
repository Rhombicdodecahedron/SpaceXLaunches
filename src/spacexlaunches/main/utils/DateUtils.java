/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacexlaunches.main.utils;

import spacexlaunches.main.Constantes;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Cette classe renferme la classe worker Date de l'application.
 *
 * @author Stella Alexis
 * @version 1.0
 * @since 28.04.2020
 */
public class DateUtils implements Constantes {

    /**
     * Cette méthode permet de créer une chaine de caractère contenant la date
     * et l'heure actuelle.
     *
     * @return une chaine de caractère contenant la date et l'heure actuelle.
     */
    public static String getCurrentDate() {
        Date now = new Date();
        SimpleDateFormat date = new SimpleDateFormat(FORMAT_DATE);
        String dateFormattee = date.format(now);
        return dateFormattee;
    }

    public static String formatDate(Date date) {
        String result = "";
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat(FORMAT_DATE);
            result = format.format(date);
        }

        return result;
    }

}
