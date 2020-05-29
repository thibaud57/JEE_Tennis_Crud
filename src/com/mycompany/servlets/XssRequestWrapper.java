package com.mycompany.servlets;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.regex.Pattern;

public class XssRequestWrapper extends HttpServletRequestWrapper{

    private static final Pattern [] XSS_PATTERNS = {
            Pattern.compile( "<.*?>" ),
            Pattern.compile( "&.*?;" ),
            Pattern.compile( "%[0-9a-fA-F]*" )
    };

    public XssRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    @Override
    public String getParameter( String parameter ) {
        return removeTags( super.getParameter(parameter) );
    }

    private String removeTags( String value ) {
        if ( value != null ) {
            // On retire le code ASCII 0, au cas ou
            value = value.replaceAll( "\0", "" );

            // Supprime l'ensemble de tags et des entités existants
            for ( Pattern pattern : XSS_PATTERNS ) {
                value = pattern.matcher( value ).replaceAll( "" );
            }

            // Au cas ou les caractères < et > ne seraient pas en nombres pairs
            value = value.replaceAll( "<", "" );
            value = value.replaceAll( ">", "" );
        }
        return value;
    }








}
