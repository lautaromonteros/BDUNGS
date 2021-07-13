## Introducción
El presente informe tiene como objetivo explicar el desarrollo del código
Biblioteca Digital UNGS, usando el lenguaje de programación y las herramientas de
tecnología JAVA. De forma que la aplicación de computadora permita organizar y
administrar los libros de las nuevas estanterías de la biblioteca.

## Desarrollo
### Explicación IREP
La clase **Libro** modela un libro, y posee las siguientes propiedades:
- `String nombre`, representa el nombre del libro, no debe estar vacío.
- `Sting categoría`, representa la categoría del libro, no debe estar vacío.
- `String isbn`, representa al código del libro, no debe estar vacío.
- `Integer ancho`, el cual va a ser el ancho del libro. Debe ser mayor 0.

La clase **Estante** modela un estante el cual es donde se van a poder ingresar
cada libro o eliminarlo. Esta clase posee las siguientes propiedades:
- `String categoría`, que representa la categoría del estante. Para todo Libro l que se
va a ingresar a un estante, debe cumplirse que l.getCategoria()==categoría.
- `Integer ancho`, indica el ancho del estante. Debe ser mayor a 0.
- `Integer espacioLibre`, representa el espacio libre que hay en un estante, este es
igual a ancho – suma de los anchos de los libros. El espacio libre debe ser menor
al ancho del estante y mayor a 0.
- `Integer numeroDeOrden`, representa la posición del estante. Debe ser mayor a 0.
- `ArrayList<Libro> libros`, representa a los libros ingresados en el estante. Por cada
Libro l ingresado a un Estante e, libros.add(l).



La clase **BDUNGS** modela una biblioteca, y posee las siguientes propiedades:
- `Integer cantidadEstantes`, representa la cantidad de estantes que va a haber en
nuestra biblioteca, debe ser mayor a 0.
- `Integer ancho`, representa el ancho de todos los estantes de la biblioteca. Debe ser
mayor a 0.
- `HashMap<Integer, Estante> estantes`, en su key se representará el número de
orden, y para cada key se representará un Estante con el ancho mencionado
anteriormente. Debe cumplirse que 0 <= key < cantidadEstantes.
- `HashMap <String, Integer> libros`, representa la cantidad de libros que hay en stock
ingresados en nuestra biblioteca. El String representa el ISBN del libro, y el Integer
representa la cantidad de ejemplares que hay. Para todo Libro l en libros, hay un
estante e tal que e.getLibros().contains(l). El value debe ser mayor o igual a 0.
- `HashSet <String> categorias`, que para toda categoría c en categorías hay un
estante tal que e.getCategoria() == c.

### Explicación de métodos de la clase BDUNGS:
Para poder ingresar un libro a nuestra biblioteca, se verifica que la categoría de
nuestro libro a ingresar esté en nuestro HashSet categorías. En caso de estarlo, se crea
un HashSet de estantes de la misma categoría, para posteriormente recorrerla y verificar
que el ancho del libro sea menor al espacio libre que tiene el estante. De esta manera
el libro se ingresará al estante.

Por otra parte, para rotular un estante, se pide ingresar una posición, y el método
`rotularEstante()` verifica que dicha posición no sea mayor a la cantidad de estantes ya
ingresados anteriormente, sino, lanza una excepción. Además, si el usuario ingresa una
posición correcta pero el estante ya había sido rotulado y este no está vacío, marcará
un error. Posteriormente, si no se verifica lo anterior, el estante se rotula correctamente.

Para el desarrollo de `espacioLibre()` verificamos que la posición pasada en este
método este incluido en nuestro HashMap de estantes, y mediante eso retornar el
espacio libre de dicho estante.

Cuando se elimina un libro, se verifica que este ya esté ingresado al HashMap
de libros. Para el desarrollo de este método decidimos crear un ArrayList dentro, del
mismo de tipo Estante, para guardar todos los estantes, y posteriormente verificar por
cada estante que contenga el ISBN que se pidió. En caso de encontrar un libro por su
ISBN, el mismo se eliminará tanto del estante, como del HashMap de libros.

Para `verLibrosCategoria()`, se pide una categoría la cual verifica si está contenida
en nuestro HashSet de categorías. Este método debe retornar otro HashMap de tipo
String e Integer el cual a medida que se van recorriendo los estantes, verifica que la
categoría de dicho estante sea la misma que la que se pide antes, para posteriormente
crear un ArrayList de libros, recorrerlo, y agregarlos a nuestro HashMap y retornarlo.

Al igual que el método mencionado anteriormente, para `reacomodarCategoria()` se
verifica que la categoría pedida este ingresada en nuestro HashSet de categorías. En
caso de estarlo, este método retornará un entero el cual nos dirá cuál sería la cantidad
de estantes liberados. Para el desarrollo de este método decidimos crear una ArrayList
de tipo Integer que guarde el número de orden de cada estante con la categoría pedida. Posteriormente creamos dos variables de tipo enteras que una guarda el largo del
arreglo de órdenes menos uno (llamada ultimoEstante) y la otra queda instanciada en
cero (llamada primerEstantedeOrden).

Luego, el código verifica que ultimoEstante sea mayor a primerEstantedeOrden.
Mientras esto se cumpla se verificará que el estante que obtiene el valor del ArrayList
de orden del ultimo estante no este vacío, sino su valor decrementa. Asimismo, si el
estante en posición con la orden obtenida por primerEstantedeOrden está lleno, su valor
incrementa en uno.

Si no verifica lo mencionado anteriormente, se creará un ArrayList de tipo Libro,
el cual contendrá todos los libros del estante la posición del orden de
primerEstantedeOrden, para recorrerla. Para ello decidimos crear una variable auxiliar
de tipo libro que, si ultimoEstante es mayor primerEstantedeOrden y el ancho del libro
a ingresar es menor al espacio libre del estante a ingresarlo, se ingresará el libro auxiliar
a dicho estante y se lo eliminará de donde se lo trajo, sino el valor de ultimoEstante
decrementa en uno su valor, verifica si el estante con la posición del
orden de ultimoEstante está vacío y en caso de estarlo se liberará un espacio el cual se
guardará en una variable, que finalmente, es lo que retornará este método.
