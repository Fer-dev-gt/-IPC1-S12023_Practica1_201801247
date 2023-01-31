package proyectosuper25;
import java.util.Scanner;
import static java.lang.Integer.parseInt;
/**
 * @author fernandoorozco
 */
public class ProyectoSuper25 {
        static Scanner entrada = new Scanner(System.in);                  // Creamos objeto global "entrada" tipo Scanner 
        static String[][] lista_tienda_final = new String[20][2];               // Matriz de 2 dimensiones
        static String[] product_list = new String[20];                          // Declaro lista de productos con 3 espacios, esta vacia por ahora
        static int[] price_list = new int[20];                                  // Declaro lista de precios de productos con 3 espacios, esta vacia por ahora
        
        static String[] descuento_list = new String[20];                        // Creamos lista donde se guardaran los diferentes codigos 
        static int[] porcentaje_descuento = new int[20];                        // Creamos lista tipo "int" para los porcentajes de descuento
        static int contador_producto = 0;                                       // Lleva el conteo de cuantos productos has sido ingresados exitosamente
        static int contador_descuento = 0;                                      // Lleva el conteo de cuantos codigos de descuento has sido ingresados exitosamente
        static boolean agregar_nuevo_producto = true;                           // Condicial para mantener ciclo while activo, se encuenta en "addNewProduct()"
        static boolean agregar_nuevo_descuento = true;                          // Condicial para mantener ciclo while activo, se encuenta en "add_discount_coupon()"
        
    
    public static void main(String[] args) {                                    // Entry Point    
        while (true){                                                           // Para que se repita el programa si credenciales son incorrectas
            System.out.println("Ingrese Usuario: ");
            String user = entrada.nextLine();                                   // Ingresamos "user"
            
            System.out.println("Ingrese Contraseña: ");
            String password = entrada.nextLine();                               // Ingresamos "password"
            
            if(user.equals("a") && password.equals("a")){        // Verificamos que los datos sean correctos
                menu_principal();                                               // Abrimos menu principal
            }else{
                System.out.println("Datos Incorrectos...");
            }      
        }          
    }                                                                       // Fin metodo main
    
    
    public static void menu_principal(){
        System.out.println("""
                           *****************************
                           *** BIENVENIDO A SUPER 25 ***
                           *****************************
                           """);
        System.out.println("Ingrese número de opción a realizar:\n"
                            + "1. Agregar nuevos productos.\n"
                            + "2. Agregar cupones de descuento.\n"
                            + "3. Realizar venta.\n"
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
        int contador = 1;                                                       // Creo contador interno inicializado en 1, indica por cual registro vamos
        
        do {                                                                    // Creamos ciclo principal con un "do-while"
            boolean ya_ingresado = false;                                       // Creamos variable tipo "boolean", la inicializamos como "false"
            System.out.println("[ Iteracion #" + (contador) + " ]");            // Mostramos por cual iteracion vamos
            System.out.println("Contador_producto: " + contador_producto);
            System.out.println("Ingrese nombre del producto:");  
            String product_name = entrada.nextLine();                           // Guardamos producto en variable "product_name"
            
            
            for (int i = 0; i < lista_tienda_final.length; i++) {               // Creamos ciclo secundario que itera dentro de lista "product_list", "lista_tienda_final.length" es igual a 20
                if(product_list[i] != null){                                    // Verificamos que los valores existenentes no sean "null"
                    if(product_list[i].equals(product_name)){            // Revisamos que "product_name" no se encuentre en lista "product_list"
                        ya_ingresado = true;                                    // Si ya existe en lista, cambiamos "ya_ingresado" a "true"
                        System.out.println("Ya EXISTE");                         
                        break;                                                  // Salimos de este ciclo
                    }
                }
            }
            
            
            if (ya_ingresado) {                                                                                 // Verificamos si ya existe el producto en la lista
                System.out.println("Este articulo ya se encuentra en la lista");                          
                continue;                                                                                       // Ignoramos las demas instrucciones y volvemos al inicio del ciclo principal
            }else{                                                                                              // Si el producto no existe en la lista entonces:
                for (int i = contador_producto; i < lista_tienda_final.length; i++) {                           // Llevamos seguimiento de los productos ingresado con "contador_producto"
                    product_list[i] = product_name;                                                             // Agregamos "product_name" a lista "product_list"
                    System.out.println("Producto '" + product_list[i] + "' ingresado correctamente");           
                    System.out.println("Ingrese precio de '" + product_list[i] + "'");                          // Pedimos que ingrese el precio del producto que acaba de ingresar
                    String precio_string = entrada.nextLine();                                                  // Guardamos dato tipo String a "precio_string"
                    int precio = parseInt(precio_string);                                                     // Casteamos el dato String a uno tipo int y lo guardamos en "precio"
                    
                    if(precio > 0){                                                                             // Revisamos que el producto no sea negativo
                        price_list[i] = precio;                                                                 // Lo agregamos a la lista "price_list"
                        System.out.println("Precio es: " + price_list[i]);                                  
                        break;                                                                                  // Salimos de este ciclo
                    }else{                                                                                      // Si el precio es negativo, entonces:
                        System.out.println("Precio no puede ser negativo, ingrese producto de nuevo");   
                        continue;                                                                               // Regresamos al inicio de este ciclo
                    }
                }
            }
            
          
            contador_producto++;                                                                                // Al ingresar correctamente un nuevo producto aumentamos "contador_producto" en 1
            
            if(contador_producto > 19){                                                                         // Vericamos que no hayan mas de 20 productos registrados
                System.out.println("LIMITE DE LISTA ALCANZADO");
                break;                                                                                          // Salimos del ciclo principal "do-while"
            }
            
            System.out.println("¿Desea agregar otro producto?\n");                                                // Le preguntamos al cajero si quiere ingresar otro producto
            boolean seguir = preguntar_continuar();                                                                         // Invocamos funcion que pregunta al usuario si quiere ingresar otro producto
            contador++;                                                                                         // Si decide ingresar otro producto aumentamos el "contador" de iteraciones en 1
            
            if(seguir){
                System.out.println("INGRESANDO NUEVO PRODUCTO\n");
            }else{
                agregar_nuevo_producto = false;
            }
            
        } while (agregar_nuevo_producto);                                                                       // Fin del ciclo "do-while", se revisa condicion
        
        
        System.out.println("-----Articulos disponibles en la tienda----- ");
        for (int i = 0; i < lista_tienda_final.length; i++) {                                                   // Creamos ciclo que muestra los productos y sus precios
            System.out.print(product_list[i] + ", " + price_list[i]);                                           // Mostramos los productos y precios
            System.out.println("");
        }
      
        menu_principal();                                                                                       // Regresamos al menu principal
    }                                                                                                           // Fin metodo addNewProduct()
    
    
    public static boolean preguntar_continuar(){
        boolean seguir = true;
        System.out.println("1. Si\n"                                                // Le preguntamos al cajero si quiere ingresar otro producto"1. Si\n"
                         + "2. No\n");
        String continuar_string = entrada.nextLine();                                                 // Guardamos respuesta
        int continuar = parseInt(continuar_string);                                                   // La convertimos en tipo "int"
        
        
        if (continuar == 1){                                                                                  // Validamos opcion "1"
            seguir = true;
        }else if(continuar == 2){
            System.out.println("\nREGRESANDO A MENU PRINCIPAL");                                            // Validamos opcion "2"      
            seguir = false;                                                                 // Cambiamos "agregar_nuevo_producto" a false, lo que terminara el ciclo principal
        }else{
            System.out.println("ERROR ***** Opcion invalida, ingrese datos nuevamente");                  // Si cajero no ingreso una opcion valida:
            preguntar_continuar();                                                                     // Volvemos a preguntar usando recursividad
        }
        return seguir;
    }
    
    
    public static void add_discount_coupon(){
        System.out.println("-----Agregar cupones de descuento------");
        System.out.println("LIMITE TOTAL DE DESCUENTOS: 20");
        int contador2 = 1;                                                                              // Creo contador interno inicializado en 1, indica por cual registro vamos
        
        do {
            boolean existe_descuento = false, longitud_erronea = false;                                 // Declaramos variable "existe_descuento" y "longitud_erronea" como "false"
            System.out.println("[ Iteracion #" + (contador2) + " ]");                                   // Mostramos por cual iteracion vamos
            System.out.println("Descuentos ya registrados: " + contador_producto);                      // Le mostramos al cajero cuantos codigos ya estan registrados
            System.out.println("--> Ingrese código de descuento: (4 caracteres)");  
            String codigo = entrada.nextLine();                                                         // Lo guardamos en variable "codigo"
            int longitud_codigo = codigo.length();                                                      // Guardamos la longitud de la palabra adentro de "codigo"
            System.out.println("Codigo '" + codigo + "' es de " + longitud_codigo + " caracteres");     // Mostramos la longitud del codigo
            
            
            if(longitud_codigo != 4){                                                                   // Verificamos que el codigo sea exactamente de 4 digitos
                System.out.println("ERROR ********* El codigo tiene que ser de 4 caracteres");
                longitud_erronea = true;                                                                // Si no es de 4 digitos "longitud_erronea" se vuelve "true"
                continue;                                                                               // Regresamos al inicio del ciclo principal                               
            }
            
            
            for (int i = 0; i < lista_tienda_final.length; i++) {                                       // Creamos ciclo secundario que itera dentro de lista "descuento_list", "lista_tienda_final.length" es igual a 20
                if(descuento_list[i] != null){                                                          // Verificamos que los valores existenentes no sean "null"
                    if(descuento_list[i].equals(codigo)){                                        // Revisamos que "product_name" no se encuentre en lista "product_list"
                        existe_descuento = true;                                                        // Si ya existe en lista, cambiamos "ya_ingresado" a "true"
                        System.out.println("Ya EXISTE DESCUENTO ");                         
                        break;                                                                          // Salimos de este ciclo
                    }
                }
            }
            
            
            if (existe_descuento || longitud_erronea) {                                                                 // Si alguna de las dos condiciones es "true" le pedimos que ingrese otro codigo        
                System.out.println("Ingrese otro codigo");                                                            
                continue;                                                                                               // Regresamos al inicio del ciclo principal
            }else{
                for (int i = contador_descuento; i < lista_tienda_final.length; i++) {                                  // Creamos ciclo secundarios para ingresar "codigo" y "porcentage" de descuento
                    descuento_list[i] = codigo;                                                                         // Guardamos "codigo" en la lista "descuento_list"
                    System.out.println("Código '" + descuento_list[i] + "' ingresado correctamente");  
                    System.out.println("Ingrese Porcentaje de Descuento del codigo '" + descuento_list[i] + "'");
                    String porcentaje_string = entrada.nextLine();                                                      // Solicitamos que ingrese la cantidad del descuento del codigo
                    int porcentaje = parseInt(porcentaje_string);                                                     // Convertimos el dato a tipo "int"
                    
                    if(porcentaje > 0 && porcentaje < 100){                                                             // Verificamos que porcentaje este en el rango de [1,99]
                        porcentaje_descuento[i] = porcentaje;                                                           // Guardamos "porcentaje" en lista "porcentaje_descuento"
                        System.out.println(porcentaje_descuento[i]+ "% de Descuento");
                        break;                                                                                          // Salimos de ciclo secundario
                    }else{
                        System.out.println("ERROR ****** NO SE ACEPTAN NUMEROS NEGATIVOS O MAYORES DE 100%, INGRESE CODIGO DE NUEVO");
                        continue;                                                                                       // Regresamos al inicio del ciclo principal
                    }
                }
            }
            
            contador_descuento++;                                                                       // Al ingresar correctamente un nuevo codigo aumentamos "contador_descuento" en 1
            
            if(contador_descuento > 19){                                                                // Vericamos que no hayan mas de 20 productos registrados
                System.out.println("LIMITE DE LISTA ALCANZADO");
                break;                                                                                  // Salimos del ciclo principal "do-while"
            }
            
            System.out.println("¿Desea agregar otro cupon?\n");                                       // Le preguntamos al cajero si quiere ingresar otro producto
            boolean seguir = preguntar_continuar();                                                     // Invocamos funcion que pregunta al usuario si quiere ingresar otro cupon
            contador2++;                                                                                // Si decide ingresar otro codigo aumentamos el "contador" de iteraciones en 1
            
            
            if(seguir){
                System.out.println("INGRESANDO NUEVO CUPON\n");
            }else{
                agregar_nuevo_descuento = false;
            }
            
        } while (agregar_nuevo_descuento);                                                              // Fin del ciclo "do-while", se revisa condicion
        
         
        System.out.println("-----CODIGOS DE DESCUENTO REGISTRADOS----- ");                            // Mostramos los codigos y porcentajes de descuento 
        for (int i = 0; i < lista_tienda_final.length; i++) {                                           // Creamos ciclo que para mostrar los codigos y sus porcentajes
            System.out.println(descuento_list[i] + ", " + porcentaje_descuento[i] + "% De Descuento");
            System.out.println("");
        }
        
        menu_principal();                                                                               // Regresamos al menu principal
    }                                                                                                   // Fin metodo add_discoutn_coupon()
    
    
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
            hacer_pedido(nombre_cliente);                                                                                         // Invoco a funcion 
        }else if(tiene_nit == 2){
            nit_string = "C/F";
            System.out.println("NIT del cliente: " + nombre_cliente + " quedo como: " + nit_string);
            hacer_pedido(nombre_cliente);                                                                                         // Invoco a funcion
        }else{
            System.out.println("ERROR ***** Opcion invalida, ingrese datos nuevamente");
            make_sale();                                                                                            // Use recursividad
        }
    }
    
    
    public static void make_report(){
         System.out.println("opcion 4");
    }
    
    
    public static void hacer_pedido(String nombre_cliente){
        System.out.println("-----LISTADO DE PRODUCTOS DISPONIBLES------");
        System.out.println("-----Articulos disponibles en la tienda----- ");
        System.out.println("\nCODIGO DE PRODUCTO    PRODUCTO    PRECIO");
        int[] lista_sub_totales = new int[20]; 
        int[] lista_cantidad_compradas = new int [20];
        String[] productos_factura = new String [20];
        int final_de_lista = 0;
        int total_compra_sin_descuento = 0;
        int contador_compra = 0;
        boolean seguir_comprando = true;
        
        
        for (int i = 0; i < lista_tienda_final.length; i++) {                                                   // Creamos ciclo que muestra los productos y sus precios
            if(product_list[i] == null){
                System.out.println("\n-----FIN DE PRODUCTOS DISPONIBLES (los demas espacios en lista son 'null')\n");
                final_de_lista = (i);
                break;
            } 
            
            System.out.print("#"+ (i+1) + "                    " + product_list[i] + "        Q" + price_list[i]);                                           // Mostramos los productos y precios
            System.out.println("");
        }
        
        do{
            System.out.println("--> INGRESE COGIDO DEL PRODUCTO (DEL 1 AL " + final_de_lista + ")"); 
            String codigo_string = entrada.nextLine();
            int codigo_producto = parseInt(codigo_string);
            
            
            if(codigo_producto < 1 || codigo_producto > final_de_lista){
                System.out.println("ERROR ******* Ingrese codigo de producto valido");
                continue;
            }else{
                System.out.println("¿Cuantos articulos de '" + product_list[(codigo_producto - 1)] + "' desea comprar?");
                String cantidad_producto_string = entrada.nextLine();
                int cantidad_producto = parseInt(cantidad_producto_string);
                System.out.println("\n**** Se compraron " + cantidad_producto + " articulos de '" + product_list[(codigo_producto - 1)] + "' ****\n");
                
                lista_cantidad_compradas[contador_compra] = cantidad_producto;
                lista_sub_totales[contador_compra] = cantidad_producto * price_list[codigo_producto - 1];
                productos_factura[contador_compra] = product_list[(codigo_producto - 1)];
                contador_compra++;
                
                
                System.out.println("¿Desea comprar otro producto?\n");
                boolean seguir = preguntar_continuar(); 
                
                if(seguir){
                    System.out.println("INGRESANDO NUEVO PRODUCTO\n");
                }else{
                    seguir_comprando = false;
                }
            }
           
        }while(seguir_comprando);
        
        System.out.println("\n-----COMPRA FINALIZADA-----\n");
        System.out.println("Lista de subtotales\n");
        
        
        for (int i = 0; i < lista_tienda_final.length; i++) {                                           // Creamos ciclo que para mostrar los codigos y sus porcentajes
            if(product_list[i] != null){
            System.out.println(productos_factura[i] + " x " + lista_cantidad_compradas[i] + " = Q" + lista_sub_totales[i]);
            }
        }
        
        
        for(int subtotal : lista_sub_totales){
            total_compra_sin_descuento += subtotal;
        }
        
        
        System.out.println("\nEl total de la compra es de: \n" + total_compra_sin_descuento + "\n");
        
    }
}
                                                                                                                   // Fin "class ProyectSuper25"