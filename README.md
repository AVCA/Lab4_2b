# Lab4_2b

## 2.b):
Desarrolle un programa Java (buildarff.java) que le permita extender o formar un archivo de fotos en formato arff (rgbfotos.arff) a partir de un archivo de cuadrantes (cuadrantek.txt) generado por ejecutark.java.

## Algoritmo
### 1) Diseñar interfaz que nos permita cargar todas las fotos.
### 2) Almacenar la direccion de las imagenes cargadas.
### 3) Tomar cada imagen cargada y leer sus pixeles: 
### 4) Dividir sus pixeles en 4 cuadrantes
### 5) Calculamos la media y desviacion estandar de cada uno de los colores
### 6) Almacenamos los resultados en cuadante"k".txt donde k es el numero del cuadrante.
### 7) Generamos o agregamos los valores de los cuadrantes.txt al archivo rgbfotos.arff

## Cosas que faltan:
- [ ] Validar que la carpeta que seleccionamos tenga BMPs
- [ ] Mostrar el archivo ARFF generado en la interfaz
- [ ] Validar que la cantidad de archivos bmps sea la correcta segun el valor de bmps por persona que recibimos
  Ej: 20 fotos total - 5 definidas por persona
      Si definimos 6 por persona no deberia de funcionar o faltarian fotos
