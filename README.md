# Lab4_2b

## 2.b):
Desarrolle un programa Java (buildarff.java) que le permita extender o formar un archivo de fotos en formato arff (rgbfotos.arff) a partir de un archivo de cuadrantes (cuadrantek.txt) generado por ejecutark.java.

## Algoritmo
### 1) Diseñar interfaz que nos permita cargar todas las fotos.

[Ana]: Tengo pensado cargar todas de una sola ves o 
cargar la direccion de la carpeta que las contiene
y almacenar sus direcciones en una lista
*bmp_direccion.add(C:/1.bmp)*

Lo malo es que tenemos que cargar 5 imagenes por persona,
asi que mi idea no sea tan util. Talves tengamos que agregar de 5
en 5 los datos al archivo arff?

### 2) Almacenar la direccion de las imagenes cargadas.

### 3) Tomar cada imagen cargada y leer sus pixeles: *Clase: ejecutark.java

Necesitamos:
  Ancho y alto de la imagen
  Lector binario
  (Esto debo de tenerlo yo - [Ana])

### 4) Dividir sus pixeles en 4 cuadrantes
Tomaremos un rango de pixeles dependiendo de su tamaño
Ejemplo:
Alto:
Ancho:
K1 = [ - ]


### 5) Calculamos la media y desviacion estandar de cada uno de los colores
media =
desviacion estandar =

### 6) Almacenamos los resultados en cuadante"k".txt donde k es el numero del cuadrante.
Ejemplo:

### 7) Generamos o agregamos los valores de los cuadrantes.txt al archivo rgbfotos.arff
