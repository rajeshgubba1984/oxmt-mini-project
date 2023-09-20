package com.oxmt.mini.project.utils;

/**
 * Please add your description here.
 *
 */
public class AgeUtil
{
    public static int getDays(String ageStr) {
        // Example:  "1 year, 2 months, 10 days"
        int result = 0;
        String[] parts = ageStr.split( "," );
        for(String s: parts) {
            String[] sub = s.split( " " );
            switch ( sub[1].toLowerCase() ) {
                case "year":
                case "years" :
                    result = result + Integer.parseInt( sub[0] ) * 365;
                    break;
                case "month":
                case "months" :
                    result = result + Integer.parseInt( sub[0] ) * 30;
                    break;
                case "day":
                case "days" :
                    result = result + Integer.parseInt( sub[0] );
                    break;
            }
        }

        return result;
    }
}
