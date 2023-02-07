package proyectosuper25;
import java.util.Scanner;                                                                      // Libreria para guardar inputs de tipo texto
import static java.lang.Integer.parseInt;                                                      // Libreria para castear datos de tipo String a tipo int
/**
 * @author fernandoorozco
 */
public class ProyectoSuper25 {
        static Scanner entrada = new Scanner(System.in);                                  // Creamos objeto global "entrada" tipo Scanner 
        static Scanner entrada2 = new Scanner(System.in);                                 // Cree un Scanner "entrada2" de respaldo
        
        static String[]product_list = new String[20];                                           // Declaro lista de productos con 3 espacios, esta vacia por ahora
        static String[] descuento_list = new String[20];                                        // Creamos lista donde se guardaran los diferentes codigos
        static String[] productos_factura = new String [20];                                    // Lista para de productos comprados e impresos en factura
        static int[] price_list = new int[20];                                                  // Declaro lista de precios de productos con 3 espacios, esta vacia por ahora
        static int[] porcentaje_descuento = new int[20];                                        // Creamos lista tipo "int" para los porcentajes de descuento
        static int[] lista_cantidad_compradas = new int [20];                                   // Lista que registra las veces que se compro un producto
        
        static int contador_producto = 0;                                                       // Lleva el conteo de cuantos productos has sido ingresados exitosamente
        static int contador_descuento = 0;                                                      // Lleva el conteo de cuantos codigos de descuento has sido ingresados exitosamente
        static boolean agregar_nuevo_producto = true;                                           // Condicial para mantener ciclo while activo, se encuenta en "addNewProduct()"
        static boolean agregar_nuevo_descuento = true;                                          // Condicial para mantener ciclo while activo, se encuenta en "add_discount_coupon()"
        
    
    public static void main(String[] args) {                                                    // Entry Point    
        while (true){                                                                           // Para que se repita el programa si credenciales son incorrectas
            System.out.println("--> Ingrese Usuario: ");
            String user = entrada2.nextLine();                                                  // Ingresamos "user"
            
            System.out.println("--> Ingrese Contraseña: ");
            String password = entrada2.nextLine();                                              // Ingresamos "password"
            
            if(user.equals("a") && password.equals("a")){                        // Verificamos que usuario y contraseña sean correctos
                menu_principal();                                                               // Abrimos menu principal
            }else{
                System.out.println("\n❌ CREDENCIALES INCORRECTAS, INTENTE OTRA VEZ ❌\n");
            }      
        }          
    }                                                                     
    
    
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
                            + "4. Realizar reporte.\n"
                            + "5. CERRAR PROGRAMA.\n");
        
        String option = entrada2.nextLine();
        switch(option){                                                                                         // Verificamos opcion usando "switch-case"
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
            case "5":
                System.out.println("CERRANDO PROGRAMA, FELIZ DIA");
                System.exit(0);                                                                           // Detenemos la ejecucion del programa
                break;
            default:
                System.out.println("\n❌ Opcion invalida, intente de nuevo. ❌\n");
                menu_principal();                                                                               // Use recursividad
                break;
        } 
    }                                                                         
        
    
    public static void addNewProduct(){
        System.out.println("\n\n\n-------AGREGAR NUEVOS PRODUCTOS-------");
        System.out.println("\nLimite de productos que se pueden registrar: 20\n");
        int contador = 1;                                                                                               // Creo contador interno inicializado en 1, indica por cual registro vamos
        
        do {                                                                                                            // Creamos ciclo principal con un "do-while"
            boolean ya_ingresado = false;                                                                               // Creamos variable tipo "boolean", la inicializamos como "false"
            System.out.println("[ Registrando Producto #" + (contador) + " ]");                                         // Mostramos por cual iteracion vamos
            System.out.println("\nProductos ya registrados: " + contador_producto + "\n");
            System.out.println("--> Ingrese nombre del producto:");  
            String product_name = entrada.nextLine();                                                                   // Guardamos producto en variable "product_name"
            
            for (int i = 0; i < product_list.length; i++) {                                                              // Creamos ciclo secundario que itera dentro de lista "product_list", "product_list.length" es igual a 20
                if(product_list[i] != null){                                                                             // Verificamos que los valores existenentes no sean "null"
                    if(product_list[i].equals(product_name)){                                                     // Revisamos que "product_name" no se encuentre en lista "product_list"
                        ya_ingresado = true;                                                                             // Si ya existe en lista, cambiamos "ya_ingresado" a "true"
                        System.out.println("\n❌ Producto '" + product_name + "'  ya se registro en la lista ❌\n");                          
                        break;                                                                                           // Salimos de este ciclo
                    }
                }
            }
            
            if (ya_ingresado) {                                                                                                 // Verificamos si ya existe el producto en la lista
                continue;                                                                                                       // Ignoramos las demas instrucciones y volvemos al inicio del ciclo principal
            }else{                                                                                                              // Si el producto no existe en la lista entonces:
                for (int i = contador_producto; i < product_list.length; i++) {                                                 // Llevamos seguimiento de los productos ingresado con "contador_producto"
                    product_list[i] = product_name;                                                                             // Agregamos "product_name" a lista "product_list"
                    System.out.println("\n✅ Producto '" + product_list[i] + "' ingresado correctamente ✅\n");           
                    System.out.println("--> Ingrese precio de '" + product_list[i] + "'");                                      // Pedimos que ingrese el precio del producto que acaba de ingresar
                    String precio_string = entrada.nextLine();                                                                  // Guardamos dato tipo String a "precio_string"
                    int precio = parseInt(precio_string);                                                                     // Casteamos el dato String a uno tipo int y lo guardamos en "precio"
                    
                    if(precio > 0){                                                                                             // Revisamos que el producto no sea negativo
                        price_list[i] = precio;                                                                                 // Lo agregamos a la lista "price_list"
                        System.out.println("\n✅ Precio de '" + product_list[i] + "' registrado a: Q" + price_list[i] + " ✅");                                  
                        break;                                                                                                  // Salimos de este ciclo
                    }else{                                                                                                      // Si el precio es negativo, entonces:
                        System.out.println("❌ Precio no puede ser negativo, ingrese producto de nuevo ❌");                   // Regresamos al inicio de este ciclo
                    }
                }
            }
            
            contador_producto++;                                                                                // Al ingresar correctamente un nuevo producto aumentamos "contador_producto" en 1
            if(contador_producto > 19){                                                                         // Vericamos que no hayan mas de 20 productos registrados
                System.out.println("LIMITE DE LISTA ALCANZADO");
                break;                                                                                          // Salimos del ciclo principal "do-while"
            }
            
            System.out.println("\n¿Desea agregar otro producto?");                                            // Le preguntamos al cajero si quiere ingresar otro producto
            boolean seguir = preguntar_continuar();                                                             // Invocamos funcion que pregunta al usuario si quiere ingresar otro producto
            contador++;                                                                                         // Si decide ingresar otro producto aumentamos el "contador" de iteraciones en 1
            

            if(seguir){
                System.out.println("\n\n+++ REGISTRANDO NUEVO PRODUCTO +++\n");
            }else{
                agregar_nuevo_producto = false;
            }
        } while (agregar_nuevo_producto);                                                                       // Fin del ciclo "do-while", se revisa condicion
        
        
        System.out.println("-----Articulos disponibles en la tienda-----\n");
        for (int i = 0; i < product_list.length; i++) {                                                         // Creamos ciclo que muestra los productos y sus precios
            System.out.print(product_list[i] + ", Q" + price_list[i]);                                          // Mostramos los productos y precios
            System.out.println("");
        }
      
        menu_principal();                                                                                       // Regresamos al menu principal
    }                                                                                                           
    
    
    public static void add_discount_coupon(){
        System.out.println("\n-------REGRISTAR CÓDIGOS DE DESCUENTO--------\n");
        System.out.println("Limite de codigos que se pueden registrar: 20");
        int contador2 = 1;                                                                                      // Creo contador interno inicializado en 1, indica por cual registro vamos
        
        do {
            boolean existe_descuento = false, longitud_erronea = false;                                         // Declaramos variable "existe_descuento" y "longitud_erronea" como "false"
            System.out.println("\n[ Registrando código #" + (contador2) + " ]\n");                              // Mostramos por cual iteracion vamos
            System.out.println("Descuentos ya registrados: " + contador_descuento);                             // Le mostramos al cajero cuantos codigos ya estan registrados
            System.out.println("\n--> Ingrese código de descuento: (4 caracteres)");  
            String codigo = entrada.nextLine();                                                                 // Lo guardamos en variable "codigo"
            int longitud_codigo = codigo.length();                                                              // Guardamos la longitud de la palabra adentro de "codigo"
            System.out.println("\nCodigo '" + codigo + "' es de " + longitud_codigo + " caracteres. ");         // Mostramos la longitud del codigo
            
            
            if(longitud_codigo != 4){                                                                           // Verificamos que el codigo sea exactamente de 4 digitos
                System.out.println("\n❌ ERROR: El codigo tiene que ser de 4 caracteres ❌");
                longitud_erronea = true;                                                                        // Si no es de 4 digitos "longitud_erronea" se vuelve "true"
                continue;                                                                                       // Regresamos al inicio del ciclo principal                               
            }
            
            
            for (int i = 0; i < product_list.length; i++) {                                                     // Creamos ciclo secundario que itera dentro de lista "descuento_list", "product_list.length" es igual a 20
                if(descuento_list[i] != null){                                                                  // Verificamos que los valores existenentes no sean "null"
                    if(descuento_list[i].equals(codigo)){                                                // Revisamos que "product_name" no se encuentre en lista "product_list"
                        existe_descuento = true;                                                                // Si ya existe en lista, cambiamos "ya_ingresado" a "true"
                        System.out.println("❌ Ya EXISTE DESCUENTO ❌");                         
                        break;                                                                                  // Salimos de este ciclo
                    }
                }
            }
            
            
            if (existe_descuento || longitud_erronea) {                                                                                             // Si alguna de las dos condiciones es "true" le pedimos que ingrese otro codigo        
                System.out.println("Ingrese otro codigo");                                                            
                continue;                                                                                                                           // Regresamos al inicio del ciclo principal
            }else{
                for (int i = contador_descuento; i < product_list.length; i++) {                                                                    // Creamos ciclo secundarios para ingresar "codigo" y "porcentage" de descuento
                    descuento_list[i] = codigo;                                                                                                     // Guardamos "codigo" en la lista "descuento_list"
                    System.out.println("\n--> Ingrese Porcentaje de Descuento (Tiene que ser mayor a 0 y menor a 100)");
                    String porcentaje_string = entrada.nextLine();                                                                                  // Solicitamos que ingrese la cantidad del descuento del codigo
                    int porcentaje = parseInt(porcentaje_string);                                                                                 // Convertimos el dato a tipo "int"
                    
                    if(porcentaje > 0 && porcentaje < 100){                                                                                         // Verificamos que porcentaje este en el rango de [1,99]
                        porcentaje_descuento[i] = porcentaje;                                                                                       // Guardamos "porcentaje" en lista "porcentaje_descuento"
                        System.out.println("\n✅ Código '" + descuento_list[i] + "' ingresado correctamente ✅\n");
                        System.out.println("Código '" + descuento_list[i] + "' representa un "+ porcentaje_descuento[i]+ "% de Descuento\n");
                        break;                                                                                                                      // Salimos de ciclo secundario
                    }else{
                        System.out.println("\n❌ ERROR: NO SE ACEPTAN NUMEROS NEGATIVOS O MAYORES DE 100%, INGRESE CODIGO DE NUEVO ❌");          // Regresamos al inicio del ciclo principal
                    }
                }
            }
            
            contador_descuento++;                                                                           // Al ingresar correctamente un nuevo codigo aumentamos "contador_descuento" en 1
            
            if(contador_descuento > 19){                                                                    // Vericamos que no hayan mas de 20 productos registrados
                System.out.println("LIMITE DE LISTA ALCANZADO");
                break;                                                                                      // Salimos del ciclo principal "do-while"
            }
            
            System.out.println("¿Desea agregar otro cupon?");                                             // Le preguntamos al cajero si quiere ingresar otro producto
            boolean seguir = preguntar_continuar();                                                         // Invocamos funcion que pregunta al usuario si quiere ingresar otro cupon
            contador2++;                                                                                    // Si decide ingresar otro codigo aumentamos el "contador" de iteraciones en 1
            
            if(seguir){
                System.out.println("\n\n+++ INGRESANDO NUEVO CUPON +++");
            }else{
                agregar_nuevo_descuento = false;
            }
            
        } while (agregar_nuevo_descuento);                                                                  // Fin del ciclo "do-while", se revisa condicion
        
         
        System.out.println("-----CODIGOS DE DESCUENTO REGISTRADOS-----\n");                               // Mostramos los codigos y porcentajes de descuento 
        for (int i = 0; i < product_list.length; i++) {                                                     // Creamos ciclo que para mostrar los codigos y sus porcentajes
            System.out.println(descuento_list[i] + ", " + porcentaje_descuento[i] + "% De Descuento");
            System.out.println("");
        }
        
        menu_principal();                                                                                   // Regresamos al menu principal
    }                                                                                                   
    
    
    public static void make_sale(){
        System.out.println("\n--------REALIZAR VENTA--------\n");
        System.out.println("--> Ingrese nombre del cliente: ");
        String nombre_cliente = entrada.nextLine();
        String nit_del_cliente = "";                                                                                        
        System.out.println("\n¿" + nombre_cliente + " tiene NIT?\n"                                                         // Le preguntamos al cliente si tiene NIT
                            + "1. Si\n"
                            + "2. No");
        String tiene_nit = entrada.nextLine();
        
            switch (tiene_nit) {
                case "1":                                                                                                   // Si el cliente tiene NIT, le pedimos que lo ingrese 
                    System.out.println("\n--> Ingrese NIT de '" + nombre_cliente + "'");
                    nit_del_cliente = entrada.nextLine();
                    System.out.println("\nNIT de '" + nombre_cliente + "' registrado como: " + nit_del_cliente);            // Le mostrasmos el NIT ingresado
                    hacer_pedido(nombre_cliente, nit_del_cliente);                                                          // Uso metodo hacer_pedido() para finalizar compra
                    break;
                case "2":                                                                                                   // Si no tiene NIT lo dejamos como "Consumidor Final C/F"
                    nit_del_cliente = "C/F";
                    System.out.println("\nNIT del cliente: " + nombre_cliente + " quedo como: " + nit_del_cliente);
                    hacer_pedido(nombre_cliente, nit_del_cliente);                                                          // Uso metodo hacer_pedido() para finalizar compra
                    break;
                default:
                    System.out.println("❌ ERROR: Opcion invalida, ingrese datos nuevamente ❌");
                    make_sale();                                                                                            // Use recursividad
                    break;
            }
    }
    
    
    public static void hacer_pedido(String nombre_cliente, String nit_del_cliente){
        int[] lista_sub_totales = new int[20];                                                                                              // Declaramo varias variables que serviran para realizar el pedido y generar reportes
        int[] precio_por_producto = new int[20];
        int final_de_lista = 0;
        int total_compra_sin_descuento = 0;
        int contador_compra = 0;
        int index_descuento = -1;
        int descuento_aplicado = 0;
        boolean seguir_comprando = true;
        boolean seguir_pidiendo_cupon = true;
        
        do{                                                                                                                                 // Creamos un ciclo "do-while" para que pida constantemente los productos que comprara el cliente
            System.out.println("\n\n-----PRODUCTOS DISPONIBLES PARA VENDER------\n");
            System.out.println("CODIGO DE PRODUCTO    PRODUCTO    PRECIO");
            for (int i = 0; i < product_list.length; i++) {                                                                                 // Creamos ciclo que muestra los productos y sus precios
                if(product_list[i] == null){                                                                                                // Si ya no hay producto disponible mostramos mensaje indicandolo
                    System.out.println("\n------FIN DE PRODUCTOS DISPONIBLES------\n(Los demas espacios en lista son 'null')\n");
                    final_de_lista = (i);                                                                                                   // Guardamos el valor "i" en "final_de_lista" para ayudar con el flujo de hacer un pedido
                    break;
                } 
                System.out.print("#"+ (i+1) + "                    " + product_list[i] + "        Q" + price_list[i]);                      // Mostramos los productos y precios
                System.out.println("");
            }
            
            System.out.println("--> INGRESE CODIGO DEL PRODUCTO (DEL 1 AL " + final_de_lista + ")");                                        // Le mostramos al cliente los codigos de los productos disponibles
            String codigo_string = entrada.nextLine();
            int codigo_producto = parseInt(codigo_string);
            
            if(codigo_producto < 1 || codigo_producto > final_de_lista){                                                                    // Evaluamos si el cliente ingreso un dato no válido
                System.out.println("\n❌ ERROR: Ingrese codigo de producto valido ❌\n");
            }else{
                System.out.println("\n¿Cuantos unidades de '" + product_list[(codigo_producto - 1)] + "' desea comprar?");                              // Le preguntamos cuantas unidades del producto seleccionado desea comprar
                String cantidad_producto_string = entrada.nextLine();
                int cantidad_producto = parseInt(cantidad_producto_string);
                System.out.println("\n✅ SE COMPRARON " + cantidad_producto + " UNIDADES DE '" + product_list[(codigo_producto - 1)] + "' ✅\n");        // Confirmamo el numero de productos comprados asi como el nombre del producto
                
                lista_cantidad_compradas[contador_compra] = cantidad_producto;                                                                          // Guardamos valor en lista que contiene las cantidad de los productos comprado
                lista_sub_totales[contador_compra] = cantidad_producto * price_list[codigo_producto - 1];                                               // Guardamos valor en lista para mostrar los subtotales de la compra, prevenimos error "Miss by One"
                productos_factura[contador_compra] = product_list[(codigo_producto - 1)];                                                               // Guardamos valor en lista para mostrar los producto comprados, prevenimos error "Miss by One"
                precio_por_producto[contador_compra] = price_list[codigo_producto - 1];                                                                 // Guardamos el precio por producto del producto que se acaba de compar, prevenimos error "Miss by One"
                
                contador_compra++;                                                                                                                      // Aumentamos contador que lleva el orden de las listas recien usadas
                
                System.out.println("¿Desea comprar otro producto?\n");                                                                                
                boolean seguir = preguntar_continuar();                                                                                                 // Llamaos funcion para preguntar si desea comprar mas productos
                
                if(seguir){
                    System.out.println("\nCOMPRANDO NUEVO PRODUCTO\n");
                }else{
                    seguir_comprando = false;
                }
            }
        }while(seguir_comprando);                                                                                                                       // Mientras "seguir_comprando" sea true, seguimos comprando productos y repetimos el codigo de arriba
        
        
        System.out.println("\n-----COMPRA FINALIZADA-----\n");                                                                // Le decimos al cajero que la compra ya termino                          
        System.out.println("Lista de subtotales\n");
        
        
        for (int i = 0; i < product_list.length; i++) {                                                                         // Creamos ciclo que para mostrar lista de subtotales
            if(productos_factura[i] != null){
            System.out.println(productos_factura[i] + " x " + lista_cantidad_compradas[i] + " = Q" + lista_sub_totales[i]);
            }
        }
        
        
        for(int subtotal : lista_sub_totales){                                                                                  // Creamos ciclo "for" que itera la lista y suma los subtotales
            total_compra_sin_descuento += subtotal;
        }
        
        System.out.println("\nSubtotal de compra: \n" + "\nQ" + total_compra_sin_descuento + "\n");                             // Mostramos el subtotal de la compra que aun no toma en cuenta si existen cupones de descuento
        
        boolean hay_cupon = existe_cupon();                                                                                     // Invocamos funcion para preguntar si el cliente tiene cupones de descuento
        
        if(hay_cupon){                                                                                                          // Evaluamos si el cliente tiene cupon
            System.out.println("\nCliente tiene cupon\n");
            do{                                                                                                                 // Si el cliente tiene cupon entramos a un ciclo "do-while" para pedirle que ingrese el codigo
                boolean codigo_encontrado = false;                                                                              // Creamos variable tipo "boolean" inicializada en false para saber si se encontro el cupon en nuestra lista
                System.out.println("--> Ingrese codigo de descuento (Tiene que ser de 4 caracteres)");                        // Le decimos que el cupon tiene que ser de 4 caracteres
                String buscar_codigo = entrada2.nextLine();

                
                for(String descuento : descuento_list){                                                                         // Entramos a un ciclo "for" para buscar el cupon ingresado en nuestra lista
                    index_descuento++;
                    if(descuento != null){                                                                                      // Verificamos que el espacio de lista no sea "null"
                        if(descuento.equals(buscar_codigo)){                                                             // Verificamos que el valor si existe en nuestra lista
                            codigo_encontrado = true;                                                                           // Cambiamos variable "codigo_encontrado" a true
                            descuento_aplicado = porcentaje_descuento[index_descuento];                                         // Fuardamos el porcentaje aplicado a esta lissta
                            break;
                        }   
                    }
                }
                
                
                if(codigo_encontrado){                                                                                          // Verificamos si el codigo ha sido encontrado
                    System.out.println("\n¡¡¡ 🎉 🎉 🎉 CODIGO VALIDADO EXITOSAMENTE 🎉 🎉 🎉!!!\n");                            // Felicitamos al cliente
                    break;
                }else{
                    System.out.println("\n❌ CODIGO INCORRECTO, INTENTE DE NUEVO ❌\n");                                      // Le decimos que su codigo no se encuentra en nuestra lista y regresamos al inicio del ciclo "do-while"
                }
                
            }while(seguir_pidiendo_cupon);                                                                                      // Verificamos si tenemos que seguir pidiendo el cupon
            
            double total_compra_con_descuento = total_compra_sin_descuento - (total_compra_sin_descuento * ( (double) descuento_aplicado / 100));                                           // Realizamos el calculo del total de la compra
            
            System.out.println("\nSE HACE DESCUENTO  DEl " + descuento_aplicado + "%\n");                                                                                                   // Le decimos al cliente de cuanto fue el descuento aplicado
            emitir_factura(total_compra_con_descuento, nombre_cliente, nit_del_cliente, lista_cantidad_compradas, lista_sub_totales, productos_factura, precio_por_producto, total_compra_sin_descuento, descuento_aplicado);  // Invocamos metodo para realizar factura
        }else{
            System.out.println("\nCliente no tiene cupon de descuento");
            int no_tiene_descuento = 0;                                                                             
            emitir_factura(total_compra_sin_descuento, nombre_cliente, nit_del_cliente, lista_cantidad_compradas, lista_sub_totales, productos_factura, precio_por_producto, total_compra_sin_descuento, no_tiene_descuento);   // Invocamos metodo para realizar factura
        }
    }
    
    
    public static void emitir_factura(double total_a_pagar, String nombre_cliente, String nit_del_cliente, int[]lista_cantidad_compradas, int[]lista_sub_totales, String[]productos_factura, int[]precio_por_producto, int total_compra_sin_descuento, int descuento_aplicado){
        String total_a_pagar_2_decimales = String.format("%.2f", total_a_pagar);                                                // Convertimos el resultado de "total_a_pagor" a una cifra con 2 decimales
        System.out.println("\n\n\n\n**************** FACTURA ****************\n");
        System.out.println("""
                           
                           *****************************************
                           *********                       *********
                           *********   SUPER 25  🛍 🏬 🛍   *********
                           *********                       *********
                           *****************************************
                           """);
        
        System.out.println("Nombre del cajero:  Fernando Orozco\n");                                                  // Mostramos el nombre del cajero que atendio la venta
        System.out.println("Nombre del cliente: " + nombre_cliente);                                                    // Mostramos nombre del cliente
        System.out.println("\nNit de '" + nombre_cliente + "' es: " + nit_del_cliente);                                 // Mostramos NIT del cliente, si este existe
        System.out.println("\nFecha de compra: 10/2/2023   17:53 (GMT -6)\n\n\n");                                    // Mostramos la fecha y hora de la venta
        System.out.println("-------------------------------------------");
        
        
        for (int i = 0; i < product_list.length; i++) {                                                                 // Creamos ciclo que para mostrar factura de subtotales
            if(productos_factura[i] != null){
            System.out.println("(" + productos_factura[i] + ", Precio x Unidad: Q"+ precio_por_producto[i] + ") x " + lista_cantidad_compradas[i] + " =    Q" + lista_sub_totales[i]);
            }
        }
        
        System.out.println("-------------------------------------------");
        System.out.println("SUBTOTAL:                               Q" + total_compra_sin_descuento);                   // Mostramos el total sin tomar en cuenta el descuento
        
        if(descuento_aplicado > 0){
            System.out.println("\nDescuento aplicado de:                  " + descuento_aplicado + "%");                // Si existe descuento se lo mostramos en la factura
        }else{
            System.out.println("\nCliente no tiene descuento");
        }
        System.out.println("\n\n-------------------------------------------");
        System.out.println("Total a pagar: Q" + total_a_pagar_2_decimales);                                             // Mostramos el total final de la compra en una cifra con 2 decimales
        System.out.println("-------------------------------------------\n\n");
        
        menu_principal();                                                                                               // Regresamos al menu prinicipal
    }
    
    
    public static void make_report(){
        int variable_temporal;                                                                      // Declaramos variables para ordenar las listas y mostrar los productos más vendidos
        String variable_temporal_producto;                                                          // Declaramos variables para ordenar las listas y mostrar los productos más vendidos
        
        System.out.println("\n\n-------------REPORTE DE VENTAS-------------\n\n");
        System.out.println("  PRODUCTOS MAS VENDIDOS HOY DE LA TIENDA ");
        System.out.println("\n\n-------------------------------------------");
                                                                                                                    // Para ordenar las listas usamos el "Método de ordenamiento de burbuja"
        for (int i = 0; i < lista_cantidad_compradas.length - 1; i++) {                                             // Ciclo "for" principal, itera para ordenar los valores de la lista
           for (int j = i + 1; j < lista_cantidad_compradas.length; j++) {                                          // Ciclo "for" secundario, se encarga de compara todos los elementos dentro de las listas
              if (lista_cantidad_compradas[i] < lista_cantidad_compradas[j]) {                                      // Si el valor de la izquierda es mayor al de la derechas, se necesita ordenar
                 variable_temporal = lista_cantidad_compradas[i];                                                   // Guardamos el valor de la izquierda en una variable temporal
                 variable_temporal_producto = productos_factura[i];                                                 // Guardamos el valor de la izquierda en una variable temporal
                 lista_cantidad_compradas[i] = lista_cantidad_compradas[j];                                         // Intercambiamos el valor de la derecha y los pasamos a la izquierda
                 productos_factura[i] = productos_factura[j];                                                       // Intercambiamos el valor de la derecha y los pasamos a la izquierda
                 lista_cantidad_compradas[j] = variable_temporal;                                                   // El valor de la izquierda lo pasamos a la derecha usando la variable temporal
                 productos_factura[j] = variable_temporal_producto;                                                 // El valor de la izquierda lo pasamos a la derecha usando la variable temporal
              }
           }
        }
                                                                                                                    // Mostramos la lista de productos mas vendidos
        System.out.println("Producto       Cantidad\n");                                            
        for (int i = 0; i < lista_cantidad_compradas.length; i++) {                                                 // Cremos ciclo "for" para imprimis todos los valores de nuestras nuevas listas ordenadas
            if(lista_cantidad_compradas[i] != 0){
                System.out.println(productos_factura[i] + "           " + lista_cantidad_compradas[i] + " ");       // Mostramos los valores de la lista
            }
        }
        
        System.exit(0);
    }
    
    
    public static boolean preguntar_continuar(){
        boolean seguir = true;
        System.out.println("1. Si\n"                                                                    // Le preguntamos al cajero si quiere ingresar otro producto"1. Si\n"
                         + "2. No");
        String continuar = entrada2.nextLine();                                                         // Guardamos respuesta
            switch (continuar) {
                case "1":
                    // Validamos opcion "1"
                    seguir = true;
                    break;
                case "2":
                    System.out.println("\nREGRESANDO A MENU PRINCIPAL\n");                            // Validamos opcion "2"      
                    seguir = false;                                                                     // Cambiamos "agregar_nuevo_producto" a false, lo que terminara el ciclo principal
                    break;
                default:
                    System.out.println("\n❌ ERROR: Opcion invalida, ingrese datos nuevamente ❌\n");  // Si cajero no ingreso una opcion valida:
                    preguntar_continuar();                                                              // Volvemos a preguntar usando recursividad
                    break;
            }
        return seguir;
    }
    
    
    public static boolean existe_cupon(){
        System.out.println("¿Tiene cupon de descuento?\n"
                         + "1. Si\n"                                                
                         + "2. No");
        String tiene_cupon = entrada.next();
        boolean hay_cupon = false;
        
            switch (tiene_cupon) {
                case "1":
                    hay_cupon =  true;
                    break;
                case "2":
                    hay_cupon = false;
                    break;
                default:
                    System.out.println("❌ Opcion invalida, intente otra vez ❌");
                    existe_cupon();
                    break;
            }
        return hay_cupon;
    }
}