package proyectosuper25;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

/**
 * @author fernandoorozco
 */
public class ProyectoSuper25 {
        static Scanner entrada = new Scanner(System.in);                  // Creamos objeto global "entrada" tipo Scanner 
        static Scanner entrada2 = new Scanner(System.in); 
        
        static String[]product_list = new String[20];                           // Declaro lista de productos con 3 espacios, esta vacia por ahora
        static int[] price_list = new int[20];                                  // Declaro lista de precios de productos con 3 espacios, esta vacia por ahora
        static String[] descuento_list = new String[20];                        // Creamos lista donde se guardaran los diferentes codigos 
        static int[] porcentaje_descuento = new int[20];                        // Creamos lista tipo "int" para los porcentajes de descuento
        
        static int contador_producto = 0;                                       // Lleva el conteo de cuantos productos has sido ingresados exitosamente
        static int contador_descuento = 0;                                      // Lleva el conteo de cuantos codigos de descuento has sido ingresados exitosamente
        static boolean agregar_nuevo_producto = true;                           // Condicial para mantener ciclo while activo, se encuenta en "addNewProduct()"
        static boolean agregar_nuevo_descuento = true;                          // Condicial para mantener ciclo while activo, se encuenta en "add_discount_coupon()"
        
    
    public static void main(String[] args) {                                    // Entry Point    
        while (true){                                                           // Para que se repita el programa si credenciales son incorrectas
            System.out.println("--> Ingrese Usuario: ");
            String user = entrada2.nextLine();                                   // Ingresamos "user"
            
            System.out.println("--> Ingrese Contraseña: ");
            String password = entrada2.nextLine();                               // Ingresamos "password"
            
            if(user.equals("a") && password.equals("a")){        // Verificamos que los datos sean correctos
                menu_principal();                                               // Abrimos menu principal
            }else{
                System.out.println("\nCREDENCIALES INCORRECTAS, INTENTE OTRA VEZ\n");
            }      
        }          
    }                                                                       // Fin metodo main
    
    
    public static void menu_principal(){
        System.out.println("""
                           
                           ***************************************
                           ********                       ********
                           ******** BIENVENIDO A SUPER 25 ********
                           ********         🛍 🏬 🛍       ********
                           ***************************************
                           """);
        System.out.println("--> Ingrese número de opción a realizar:\n"
                            + "\n1. Agregar nuevos productos.\n"
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
        System.out.println("-------AGREGAR NUEVOS PRODUCTOS-------");
        System.out.println("\nLimite de productos que se pueden registrar: 20\n");
        int contador = 1;                                                       // Creo contador interno inicializado en 1, indica por cual registro vamos
        
        do {                                                                    // Creamos ciclo principal con un "do-while"
            boolean ya_ingresado = false;                                       // Creamos variable tipo "boolean", la inicializamos como "false"
            System.out.println("[ Registrando producto #" + (contador) + " ]");            // Mostramos por cual iteracion vamos
            System.out.println("\nProductos ya registrados: " + contador_producto + "\n");
            System.out.println("--> Ingrese nombre del producto:");  
            String product_name = entrada.nextLine();                           // Guardamos producto en variable "product_name"
            
            
            for (int i = 0; i < product_list.length; i++) {               // Creamos ciclo secundario que itera dentro de lista "product_list", "product_list.length" es igual a 20
                if(product_list[i] != null){                                    // Verificamos que los valores existenentes no sean "null"
                    if(product_list[i].equals(product_name)){            // Revisamos que "product_name" no se encuentre en lista "product_list"
                        ya_ingresado = true;                                    // Si ya existe en lista, cambiamos "ya_ingresado" a "true"
                        System.out.println("Producto '" + product_name + "'  ya se registro en la lista");                          
                        break;                                                  // Salimos de este ciclo
                    }
                }
            }
            
            
            if (ya_ingresado) {                                                                                 // Verificamos si ya existe el producto en la lista
                continue;                                                                                       // Ignoramos las demas instrucciones y volvemos al inicio del ciclo principal
            }else{                                                                                              // Si el producto no existe en la lista entonces:
                for (int i = contador_producto; i < product_list.length; i++) {                           // Llevamos seguimiento de los productos ingresado con "contador_producto"
                    product_list[i] = product_name;                                                             // Agregamos "product_name" a lista "product_list"
                    System.out.println("\nProducto '" + product_list[i] + "' ingresado correctamente ✅\n");           
                    System.out.println("--> Ingrese precio de '" + product_list[i] + "'");                          // Pedimos que ingrese el precio del producto que acaba de ingresar
                    String precio_string = entrada.nextLine();                                                  // Guardamos dato tipo String a "precio_string"
                    int precio = parseInt(precio_string);                                                     // Casteamos el dato String a uno tipo int y lo guardamos en "precio"
                    
                    if(precio > 0){                                                                             // Revisamos que el producto no sea negativo
                        price_list[i] = precio;                                                                 // Lo agregamos a la lista "price_list"
                        System.out.println("\nPrecio de '" + product_list[i] + "' registrado a: Q" + price_list[i]);                                  
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
            
            System.out.println("\n¿Desea agregar otro producto?");                                                // Le preguntamos al cajero si quiere ingresar otro producto
            boolean seguir = preguntar_continuar();                                                                         // Invocamos funcion que pregunta al usuario si quiere ingresar otro producto
            contador++;                                                                                         // Si decide ingresar otro producto aumentamos el "contador" de iteraciones en 1
            
            if(seguir){
                System.out.println("\n\n+++ REGISTRANDO NUEVO PRODUCTO +++\n");
            }else{
                agregar_nuevo_producto = false;
            }
            
        } while (agregar_nuevo_producto);                                                                       // Fin del ciclo "do-while", se revisa condicion
        
        
        System.out.println("-----Articulos disponibles en la tienda-----\n");
        for (int i = 0; i < product_list.length; i++) {                                                   // Creamos ciclo que muestra los productos y sus precios
            System.out.print(product_list[i] + ", Q" + price_list[i]);                                           // Mostramos los productos y precios
            System.out.println("");
        }
      
        menu_principal();                                                                                       // Regresamos al menu principal
    }                                                                                                           // Fin metodo addNewProduct()
    
    
    public static boolean preguntar_continuar(){
        boolean seguir = true;
        System.out.println("1. Si\n"                                                // Le preguntamos al cajero si quiere ingresar otro producto"1. Si\n"
                         + "2. No");
        String continuar = entrada.nextLine();                                                 // Guardamos respuesta
            switch (continuar) {
                case "1":
                    // Validamos opcion "1"
                    seguir = true;
                    break;
                case "2":
                    System.out.println("\nREGRESANDO A MENU PRINCIPAL\n");                                            // Validamos opcion "2"      
                    seguir = false;                                                                 // Cambiamos "agregar_nuevo_producto" a false, lo que terminara el ciclo principal
                    break;
                default:
                    System.out.println("\nERROR ***** Opcion invalida, ingrese datos nuevamente\n");                  // Si cajero no ingreso una opcion valida:
                    preguntar_continuar();                                                                     // Volvemos a preguntar usando recursividad
                    break;
            }
        return seguir;
    }
    
    
    public static void add_discount_coupon(){
        System.out.println("\n-------REGRISTAR CÓDIGOS DE DESCUENTO--------\n");
        System.out.println("Limite de codigos que se pueden registrar: 20");
        int contador2 = 1;                                                                              // Creo contador interno inicializado en 1, indica por cual registro vamos
        
        do {
            boolean existe_descuento = false, longitud_erronea = false;                                 // Declaramos variable "existe_descuento" y "longitud_erronea" como "false"
            System.out.println("\n[ Registrando código #" + (contador2) + " ]\n");                                   // Mostramos por cual iteracion vamos
            System.out.println("Descuentos ya registrados: " + contador_descuento);                      // Le mostramos al cajero cuantos codigos ya estan registrados
            System.out.println("\n--> Ingrese código de descuento: (4 caracteres)");  
            String codigo = entrada.nextLine();                                                         // Lo guardamos en variable "codigo"
            int longitud_codigo = codigo.length();                                                      // Guardamos la longitud de la palabra adentro de "codigo"
            System.out.print("\nCodigo '" + codigo + "' es de " + longitud_codigo + " caracteres. ");     // Mostramos la longitud del codigo
            
            
            if(longitud_codigo != 4){                                                                   // Verificamos que el codigo sea exactamente de 4 digitos
                System.out.println("ERROR ********* El codigo tiene que ser de 4 caracteres");
                longitud_erronea = true;                                                                // Si no es de 4 digitos "longitud_erronea" se vuelve "true"
                continue;                                                                               // Regresamos al inicio del ciclo principal                               
            }
            
            
            for (int i = 0; i < product_list.length; i++) {                                       // Creamos ciclo secundario que itera dentro de lista "descuento_list", "product_list.length" es igual a 20
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
                for (int i = contador_descuento; i < product_list.length; i++) {                                  // Creamos ciclo secundarios para ingresar "codigo" y "porcentage" de descuento
                    descuento_list[i] = codigo;                                                                         // Guardamos "codigo" en la lista "descuento_list"
                    System.out.println("Código '" + descuento_list[i] + "' ingresado correctamente ✅\n");  
                    System.out.println("--> Ingrese Porcentaje de Descuento (Tiene que ser mayor a 0 y menor a 100)");
                    String porcentaje_string = entrada.nextLine();                                                      // Solicitamos que ingrese la cantidad del descuento del codigo
                    int porcentaje = parseInt(porcentaje_string);                                                     // Convertimos el dato a tipo "int"
                    
                    if(porcentaje > 0 && porcentaje < 100){                                                             // Verificamos que porcentaje este en el rango de [1,99]
                        porcentaje_descuento[i] = porcentaje;                                                           // Guardamos "porcentaje" en lista "porcentaje_descuento"
                        System.out.println("\nCódigo '" + descuento_list[i] + "' representa un "+ porcentaje_descuento[i]+ "% de Descuento\n");
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
            
            System.out.println("¿Desea agregar otro cupon?");                                       // Le preguntamos al cajero si quiere ingresar otro producto
            boolean seguir = preguntar_continuar();                                                     // Invocamos funcion que pregunta al usuario si quiere ingresar otro cupon
            contador2++;                                                                                // Si decide ingresar otro codigo aumentamos el "contador" de iteraciones en 1
            
            
            if(seguir){
                System.out.println("\n\n+++ INGRESANDO NUEVO CUPON +++");
            }else{
                agregar_nuevo_descuento = false;
            }
            
        } while (agregar_nuevo_descuento);                                                              // Fin del ciclo "do-while", se revisa condicion
        
         
        System.out.println("-----CODIGOS DE DESCUENTO REGISTRADOS-----\n");                            // Mostramos los codigos y porcentajes de descuento 
        for (int i = 0; i < product_list.length; i++) {                                           // Creamos ciclo que para mostrar los codigos y sus porcentajes
            System.out.println(descuento_list[i] + ", " + porcentaje_descuento[i] + "% De Descuento");
            System.out.println("");
        }
        
        menu_principal();                                                                               // Regresamos al menu principal
    }                                                                                                   // Fin metodo add_discoutn_coupon()
    
    
    public static void make_sale(){
        System.out.println("\n--------REALIZAR VENTA--------\n");
        System.out.println("--> Ingrese nombre del cliente: ");
        String nombre_cliente = entrada.nextLine();
        String nit_del_cliente = "";
        System.out.println("\n¿" + nombre_cliente + " tiene NIT?\n"
                            + "1. Si\n"
                            + "2. No");
        String tiene_nit = entrada.nextLine();
        
            switch (tiene_nit) {
                case "1":
                    System.out.println("\n--> Ingrese NIT de '" + nombre_cliente + "'");
                    nit_del_cliente = entrada.nextLine();
                    System.out.println("\nNIT de '" + nombre_cliente + "' registrado como: " + nit_del_cliente);
                    hacer_pedido(nombre_cliente, nit_del_cliente);                                                                                         // Invoco a funcion 
                    break;
                case "2":
                    nit_del_cliente = "C/F";
                    System.out.println("\nNIT del cliente: " + nombre_cliente + " quedo como: " + nit_del_cliente);
                    hacer_pedido(nombre_cliente, nit_del_cliente);                                                                                         // Invoco a funcion
                    break;
                default:
                    System.out.println("ERROR ***** Opcion invalida, ingrese datos nuevamente");
                    make_sale();                                                                                            // Use recursividad
                    break;
            }
    }
    
    
    public static void make_report(){
         System.out.println("opcion 4");
    }
    
    
    public static void hacer_pedido(String nombre_cliente, String nit_del_cliente){
        System.out.println("\n-----PRODUCTOS DISPONIBLES PARA VENDER------\n");
        System.out.println("CODIGO DE PRODUCTO    PRODUCTO    PRECIO");
        int[] lista_sub_totales = new int[20]; 
        int[] lista_cantidad_compradas = new int [20];
        int[] precio_por_producto = new int[20];
        String[] productos_factura = new String [20];
        int final_de_lista = 0;
        int total_compra_sin_descuento = 0;
        int contador_compra = 0;
        int index_descuento = -1;
        int descuento_aplicado = 0;
        boolean seguir_comprando = true;
        
        
        for (int i = 0; i < product_list.length; i++) {                                                   // Creamos ciclo que muestra los productos y sus precios
            if(product_list[i] == null){
                System.out.println("\n------FIN DE PRODUCTOS DISPONIBLES------\n(Los demas espacios en lista son 'null')\n");
                final_de_lista = (i);
                break;
            } 
            System.out.print("#"+ (i+1) + "                    " + product_list[i] + "        Q" + price_list[i]);                                           // Mostramos los productos y precios
            System.out.println("");
        }
        
        
        do{
            System.out.println("--> INGRESE CODIGO DEL PRODUCTO (DEL 1 AL " + final_de_lista + ")"); 
            String codigo_string = entrada.nextLine();
            int codigo_producto = parseInt(codigo_string);
            
            if(codigo_producto < 1 || codigo_producto > final_de_lista){
                System.out.println("ERROR ******* Ingrese codigo de producto valido");
                continue;
            }else{
                System.out.println("\n¿Cuantos unidades de '" + product_list[(codigo_producto - 1)] + "' desea comprar?");
                String cantidad_producto_string = entrada.nextLine();
                int cantidad_producto = parseInt(cantidad_producto_string);
                System.out.println("\n****** SE COMPRARON " + cantidad_producto + " UNIDADES DE '" + product_list[(codigo_producto - 1)] + "' *****\n");
                
                lista_cantidad_compradas[contador_compra] = cantidad_producto;
                lista_sub_totales[contador_compra] = cantidad_producto * price_list[codigo_producto - 1];
                productos_factura[contador_compra] = product_list[(codigo_producto - 1)];
                precio_por_producto[contador_compra] = price_list[codigo_producto - 1];
                
                contador_compra++;
                
                System.out.println("¿Desea comprar otro producto?\n");
                boolean seguir = preguntar_continuar(); 
                
                if(seguir){
                    System.out.println("\nCOMPRANDO NUEVO PRODUCTO\n");
                }else{
                    seguir_comprando = false;
                }
            }
        }while(seguir_comprando);
        
        
        System.out.println("\n-----COMPRA FINALIZADA-----\n");
        System.out.println("Lista de subtotales\n");
        
        for (int i = 0; i < product_list.length; i++) {                                           // Creamos ciclo que para mostrar mini version de factura
            if(productos_factura[i] != null){
            System.out.println(productos_factura[i] + " x " + lista_cantidad_compradas[i] + " = Q" + lista_sub_totales[i]);
            }
        }
        
        for(int subtotal : lista_sub_totales){                                                                  // Creamos ciclo "for" que itera la lista y suma los subtotales
            total_compra_sin_descuento += subtotal;
        }
        
        System.out.println("\nSubtotal de compra es: \n" + "\nQ" + total_compra_sin_descuento + "\n");
        
        
        boolean hay_cupon = existe_cupon();
        boolean seguir_pidiendo_cupon = true;
        if(hay_cupon){
            System.out.println("\nCliente tiene cupon\n");
            do{
                boolean codigo_encontrado = false;
                System.out.println("--> Ingrese codigo de descuento (Tiene que ser de 4 caracteres)");
                String buscar_codigo = entrada2.nextLine();

                
                for(String descuento : descuento_list){
                    index_descuento++;
                    if(descuento != null){
                        if(descuento.equals(buscar_codigo)){
                            codigo_encontrado = true;
                            descuento_aplicado = porcentaje_descuento[index_descuento];
                            break;
                        }
                    }
                }
                
                
                if(codigo_encontrado){
                    System.out.println("\n¡¡¡ 🎉 🎉 🎉 CODIGO VALIDADO EXITOSAMENTE 🎉 🎉 🎉!!!\n");
                    break;
                }else{
                    System.out.println("\nCODIGO INCORRECTO, INTENTE DE NUEVO\n");
                    continue;
                }
                
            }while(seguir_pidiendo_cupon);
            
            double total_compra_con_descuento = total_compra_sin_descuento - (total_compra_sin_descuento * ( (double) descuento_aplicado / 100));
            
            System.out.println("\nSE HACE DESCUENTO  DEl " + descuento_aplicado + "%\n");
            emitir_factura(total_compra_con_descuento, nombre_cliente, nit_del_cliente, lista_cantidad_compradas, lista_sub_totales, productos_factura, precio_por_producto, total_compra_sin_descuento, descuento_aplicado);
        }else{
            System.out.println("Cliente no tiene cupon de descuento");
            int no_tiene_descuento = 0;
            emitir_factura(total_compra_sin_descuento, nombre_cliente, nit_del_cliente, lista_cantidad_compradas, lista_sub_totales, productos_factura, precio_por_producto, total_compra_sin_descuento, no_tiene_descuento);
        }
        
        
        
        
        
    }
    
    public static boolean existe_cupon(){
        System.out.println("¿Tiene cupon de descuento?\n"
                         + "1. Si\n"                                                
                         + "2. No");
        String tiene_cupon_string = entrada.next();
        int tiene_cupon = parseInt(tiene_cupon_string);
        boolean hay_cupon = false;
        
        if(tiene_cupon == 1){
            hay_cupon =  true;
        }else if(tiene_cupon == 2){
            hay_cupon = false;
        }else{
            System.out.println("Opcion invalida, intente otra vez");
            existe_cupon();
        }
        return hay_cupon;
    }
    
    
    
    public static void emitir_factura(double total_a_pagar, String nombre_cliente, String nit_del_cliente, int[]lista_cantidad_compradas, int[]lista_sub_totales, String[]productos_factura, int[]precio_por_producto, int total_compra_sin_descuento, int descuento_aplicado){
        String total_a_pagar_2_decimales = String.format("%.2f", total_a_pagar);
        System.out.println("\n*************** FACTURA ***************\n");
        System.out.println("""
                           
                           *****************************************
                           *********                       *********
                           *********   SUPER 25  🛍 🏬 🛍   *********
                           *********                       *********
                           *****************************************
                           """);
        
        System.out.println("Nombre del cajero:  Fernando Orozco\n");
        System.out.println("Nombre del cliente: " + nombre_cliente);
        System.out.println("\nNit de '" + nombre_cliente + "' es: " + nit_del_cliente);
        System.out.println("\nFecha de compra: 10/2/2023   17:53 (GMT -6)\n\n\n");
        
        
        for (int i = 0; i < product_list.length; i++) {                                           // Creamos ciclo que para mostrar mini version de factura
            if(productos_factura[i] != null){
            System.out.println("(" + productos_factura[i] + ", Precio x Unidad: Q"+ precio_por_producto[i] + ") x " + lista_cantidad_compradas[i] + " = Q" + lista_sub_totales[i]);
            }
        }
        
        System.out.println("------------------------------------------");
        System.out.println("SUBTOTAL:                            Q" + total_compra_sin_descuento);
        
        if(descuento_aplicado > 0){
            System.out.println("\nDescuento aplicado de:           " + descuento_aplicado + "%");
        }else{
            System.out.println("\nCliente no tiene descuento");
        }
        
        System.out.println("\nTotal a pagar: Q" + total_a_pagar_2_decimales);
                                
        
        System.out.println("\n\n\n\n\n\n\n\n\n\n\nREGRESANDO A MENU PRINCIPAL\n"); 
        menu_principal();  
        
        
        
        
    }
}
                                                                                                                   // Fin "class ProyectSuper25"