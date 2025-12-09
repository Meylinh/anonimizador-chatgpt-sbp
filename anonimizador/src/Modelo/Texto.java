package Modelo; 

public class Texto { private String contenido; 

public Texto(String contenido) { 
    this.contenido = contenido; 
} 
 
public String getContenido() { 
    return contenido; 
} 
 
public String anonimizar() { 
    
    String[] sensibles = { "Banistmo", "B4nistmo", "Banistm0" }; 
    String resultado = contenido; 
 
    for (String palabra : sensibles) { 
        resultado = resultado.replaceAll("(?i)\\b" + palabra + "\\b", "***"); 
    } 
 
    return resultado; 
} 
 
@Override 
public String toString() { 
    return "Texto original: " + contenido; 
} 
  

} 