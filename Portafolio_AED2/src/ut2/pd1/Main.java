/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut2.pd1;

/**
 *
 * @author Francisco
 */
public class Main {
    
    public static void main(String[] args) {
        TArbolGenerico arbol = new TArbolGenerico();
        arbol.insertar("Rectoria", "");
        arbol.insertar("Vicerrectoria del Medio Universitario", "Rectoria");
        arbol.insertar("Vicerrectoria Academica", "Rectoria");
        arbol.insertar("Vicerrectoria Administrativa", "Rectoria");
        arbol.insertar("Facultad de Ciencias Empresariales", "Vicerrectoria Academica");
        arbol.insertar("Facultad de Ciencias Humanas", "Vicerrectoria Academica");
        arbol.insertar("Facultad de Ciencias Ingenieria y Tecnologias", "Vicerrectoria Academica");
        arbol.insertar("Facultad de Psicologia", "Vicerrectoria Academica");
        arbol.insertar("Departamento de Informatica", "Facultad de Ciencias Ingenieria y Tecnologias");
        arbol.insertar("Departamento de Ingenieria Electrica", "Facultad de Ciencias Ingenieria y Tecnologias");
        arbol.insertar("Departamento de Matematica", "Facultad de Ciencias Ingenieria y Tecnologias");
        
        System.out.println(arbol.listarIndentado());
    }         
}
