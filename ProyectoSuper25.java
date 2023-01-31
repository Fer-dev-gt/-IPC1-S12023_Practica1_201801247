package proyectosuper25;
import java.util.Scanner;
import static java.lang.Integer.parseInt;
/**
 * @author fernandoorozco
 */
public class ProyectoSuper25 {
        static Scanner entrada = new Scanner(System.in);                  // Creamos objeto global "entrada" tipo Scanner 
        static String[] product_list = new String[20];                           // Declaro lista de productos con 3 espacios, esta vacia por ahora
        static int[] price_list = new int[20];                                   // Declaro lista de precios de productos con 3 espacios, esta vacia por ahora
        static int contador_producto = 0;
        static boolean agregar_nuevo_producto = true;
        
        
        static String[][] lista_tienda_final = new String[3][2];
        
        
    
    public static void main(String[] args) {                                    // Entry Point    
        while (true){                                                           // Para que se repita el programa si credenciales son incorrectas
            System.out.println("Ingrese Usuario: ");
            String user = entrada.nextLine();                                   // Ingresamos "user"
            
            System.out.println("Ingrese Contraseña: ");
            String password = entrada.nextLine();                               // Ingresamos "password"
            
            if(user.equals("a") && password.equals("a")){
                menu_principal();                                               // Abrimos menu principal
            }else{
                System.out.println("Datos Incorrectos...");
            }      
        }          
    }                                                                           // Fin metodo main
    

    
    public static void menu_principal(){
        System.out.println("""
                           *****************************
                           *** BIENVENIDO A SUPER 25 ***
                           *****************************
                           """);
        System.out.println("Ingrese número de opción a realizar:\n"
                            + "1. Agregar nuevos productos.\n"
                            + "2. Agregar cupones de descuento.\n"
                            + "3. Realizar ventas.\n"
                            + "4. Realizar reporte.\n");
        String option = entrada.nextLine();
        
        switch(option){                                                         // Verificamos opcion usando operador de control switch
            case "1":
                addNewProduct();
                break;
            case "2":
                add_discount_coupon();
                break;
            case "3":
                make_sale();
                break;
            case "4":
                make_report();
                break;
            default:
                System.out.println("Opcion invalida, intente de nuevo.\n");
                menu_principal();                                               // Use recursividad
                break;
        } 
        
        
    }                                                                           // Fin metodo menu_principal()
        
    
    
    public static void addNewProduct(){
        System.out.println("-----Agregar nuevos productos-----");
        System.out.println("LIMITE TOTAL DE PRODUCTOS: 20");
        int contador = 1;
        
                                    do {
                                        boolean ya_ingresado = false;                                       // Creamos variable tipo "boolean", la inicializamos como "false"
                                        System.out.println("[ Iteracion #" + (contador) + " ]");               // Mostramos por cual iteracion vamos
                                        System.out.println("Ingrese nombre del producto:");  
                                        System.out.println("Contador_producto: " + contador_producto);
                                        String product_name = entrada.nextLine();                           // Guardamos producto en variable "product_name"
                                        
                                        
                                        for (int i = 0; i < lista_tienda_final.length; i++) {                   // lista_tienda_final.length es igual a 20
                                            if(product_list[i] != null){
                                                if(product_list[i].equals(product_name)){                // Revisamos que "product_name" no se encuentre en lista "product_list"
                                                    ya_ingresado = true;                                        // Si ya existe en lista, cambiamos "ya_ingresado" a "true"
                                                    System.out.println("Ya EXISTE");                         
                                                    break;                                                      // Salimos de este ciclo
                                                }
                                            }
                                        }
                                        
                                        
                                        if (ya_ingresado) {                                                                         // Verificamos si ya existe el producto en la lista
                                            System.out.println("Este articulo ya se encuentra en la lista");                          
                                            continue;                                                                                    // Regresamos un paso en el "for" principal usando "i--"
                                        }else{                                                                                      // Si el producto no existe en la lista entonces:
                                            for (int i = contador_producto; i < lista_tienda_final.length; i++) {     
               /**/                             product_list[i] = product_name;                                                         // Agregamos "product_name" a lista "product_list"
                                                System.out.println("Producto '" + product_list[i] + "' ingresado correctamente");       
                                                System.out.println("Ingrese precio de '" + product_list[i] + "'");                      // Pedimos que ingrese el precio del producto que acaba de ingresar
                                                String precio_string = entrada.nextLine();                                              // Guardamos dato tipo String a "precio_string"
                                                int precio = parseInt(precio_string);                                                 // Casteamos el dato String a uno tipo int y lo guardamos en "precio"
                                                
                                                if(precio > 0){                                                                         // Revisamos que el producto no sea negativo
                  /**/                              price_list[i] = precio;                                                             // Lo agregamos a la lista "price_list"
                                                    System.out.println("Precio es: " + price_list[i]);                                  
                                                    break;
                                                }else{
                                                    System.out.println("Precio no puede ser negativo, ingrese producto de nuevo");   
                                                    continue;
                                                }
                                            }
                                        }
                                      
                                        contador_producto++;  
                                        if(contador_producto > 2){
                                            System.out.println("LIMITE DE LISTA ALCANZADO");
                                            break;
                                        }
                                      
                                        preguntar_nuevo_producto();
                                        contador++;
                                    } while (agregar_nuevo_producto);
                                    
                                    System.out.println("-----Articulos de la compra----- ");
                                    for (int i = 0; i < 3; i++) {                                           // Creamos ciclo que para mostrar los productos y sus precios
                                        System.out.print(product_list[i] + ", " + price_list[i]);           // Mostramos los productos y precios
                                        System.out.println("");
                                    }
                                    
                                    menu_principal();
    }                                                                                                   // Fin metodo addNewProduct()
    
    public static void preguntar_nuevo_producto(){
        
        System.out.println("¿Desea agregar otro producto?\n"
                           + "1. Si\n"
                           + "2. No\n");
        String agregar_product_string = entrada.nextLine();
        int agregar = parseInt(agregar_product_string);
        
        
        if (agregar == 1){
            System.out.println("INGRESANDO NUEVO PRODUCTO");
        }else if(agregar == 2){
            System.out.println("REGRESANDO A MENU PRINCIPAL");
            agregar_nuevo_producto = false;
        }else{
            System.out.println("ERROR ***** Opcion invalida, ingrese datos nuevamente");
            preguntar_nuevo_producto();                                                                                            // Use recursividad
        }
        
    }
    
    
    
    
    
    
    
    public static void add_discount_coupon(){
        System.out.println("-----Agregar cupones de descuento------");
        System.out.println("¿Cuantos códigos de descuento ingresará?");                     
        String descuento_string = entrada.nextLine();                                                   // Guardamos la cantidad de codigos que se van a ingresar
        int cantidad_de_codigos = parseInt(descuento_string);                                         // Lo convertimos a tipo "int"
        String[] descuento_list = new String[cantidad_de_codigos];                                      // Creamos lista donde se guardaran los diferentes codigos 
        int[] porcentaje_descuento = new int[cantidad_de_codigos];                                      // Creamos lista tipo "int" para los porcentajes de descuento
        
        
         for (int i = 0; i < cantidad_de_codigos; i++) {                                                // Ciclo principal
            boolean existe_descuento = false, longitud_erronea = false;                                 // Declaramos variable "existe_descuento" y "longitud_erronea" como "false"
            System.out.println("[ Iteracion #" + (i + 1) + " ]");                                       // Mostramos numero de iteracion
            System.out.println("--> Ingrese código de descuento: (4 caracteres)");                    // Solicitamos un codigo de 4 caracteres
            String codigo = entrada.nextLine();                                                         // Lo guardamos en variable "codigo"
            int longitud_codigo = codigo.length();                                                      // Guardamos la longitud de la palabra adentro de "codigo"
            System.out.println("Codigo '" + codigo + "' es de " + longitud_codigo + " caracteres");     // Mostramos la longitud del codigo
            
            
            if(longitud_codigo != 4){                                                                   // Verificamos que el codigo sea exactamente de 4 digitos
                    System.out.println("ERROR ********* El codigo tiene que ser de 4 caracteres");
                    longitud_erronea = true;                                                            // Si no es de 4 digitos "longitud_erronea" se vuelve "true"
            }
 
            
            for (int j = 0; j < i; j++) {                                                               // Ciclo secundario, donde iteramos la lista "descuento_list"          
                if(descuento_list[j].equals(codigo)){                                            // Verificamos que no exista ya el descuento
                    System.out.println("Este codigo YA ESTA registrado");
                    existe_descuento = true;                                                            // Si ya existe el codigo, marcamos "existe_descuento" como "true"
                    break;                                                                              // Salimos del ciclo secundario
                }
            }
            
            
            if (existe_descuento || longitud_erronea) {                                                 // Si alguna de las dos condiciones es "true" le pedimos que ingrese otro codigo        
                System.out.println("Ingrese otro codigo");                                            // Damos un paso atras en el ciclo principal
                i--;                                                                                       
            }else{                                                                                     
                descuento_list[i] = codigo;                                                                         // Guardamos "codigo" en la lista "descuento_list"
                System.out.println("Código '" + descuento_list[i] + "' ingresado correctamente");  
                System.out.println("Ingrese Porcentaje de Descuento del codigo '" + descuento_list[i] + "'");
                String porcentaje_string = entrada.nextLine();                                                      // Solicitamos que ingrese la cantidad del descuento del codigo
                int porcentaje = parseInt(porcentaje_string);                                                     // Convertimos el dato a tipo "int"
                
                if(porcentaje > 0 && porcentaje < 100){                                                             // Verificamos que porcentaje este en el rango de [1,99]
                    porcentaje_descuento[i] = porcentaje;
                    System.out.println(porcentaje_descuento[i]+ "% de Descuento");
                }else{
                    System.out.println("ERROR ****** NO SE ACEPTAN NUMEROS NEGATIVOS O MAYORES DE 100%, INGRESE CODIGO DE NUEVO");
                    i--; 
                }
            }
        }
         
         
        System.out.println("-----Codigos de descuento utilizados----- ");                                         // Mostramos los productos y precios 
        for (int i = 0; i < cantidad_de_codigos; i++) {                                                             // Creamos ciclo que para mostrar los productos y sus precios
            System.out.println(descuento_list[i] + ", " + porcentaje_descuento[i] + "% De Descuento");
            System.out.println("");
        }
    }                                                                                                               // Fin metodo add_discoutn_coupon()
    
    public static void make_sale(){
        System.out.println("-----REALIZAR VENTAS------");
        System.out.println("--> Ingrese nombre del cliente: ");
        String nombre_cliente = entrada.nextLine();
        System.out.println("¿" + nombre_cliente + " tiene NIT?\n"
                            + "1. Si\n"
                            + "2. No\n");
        String nit_string = entrada.nextLine();
        int tiene_nit = parseInt(nit_string);
        
        if (tiene_nit == 1){
            System.out.println("Ingrese número de NIT de '" + nombre_cliente + "'");
            String nit_del_cliente = entrada.nextLine();
            System.out.println("NIT de '" + nombre_cliente + "' es: " + nit_del_cliente);
            hacer_pedido();                                                                                         // Invoco a funcion 
        }else if(tiene_nit == 2){
            nit_string = "C/F";
            System.out.println("NIT del cliente: " + nombre_cliente + " quedo como: " + nit_string);
            hacer_pedido();                                                                                         // Invoco a funcion
        }else{
            System.out.println("ERROR ***** Opcion invalida, ingrese datos nuevamente");
            make_sale();                                                                                            // Use recursividad
        }
        
        
        
        
        
    }
    
    public static void make_report(){
         System.out.println("opcion 4");
    }
    
    
    public static void hacer_pedido(){
        System.out.println("-----LISTADO DE PRODUCTOS DISPONIBLES------");
        
    }
}
                                                                                                                   // Fin "class ProyectSuper25"