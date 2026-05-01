# Arboles (Respuestas)
Como **__ejemplo práctico:__** 
1. Crea una subclase ArbolBinarioDeBusquedaEnteros.\
`Creada en el archivo ArbolBinariodeBusquedaDeEnteros.java`
2. Añade el método que calcule la suma de los números insertados en ese árbol (getSuma():int).\
`¡Implementado en el TAD específicado con anterioridad!`
3. Crea un programa de prueba:\
`Creado en el archivo MainABDBDE.java (preguntas i, ii, iii y iv)`
    1. Añadir los números de 0 a 128 en orden.
    2. Calcular la suma (getSuma())
    3. Verifica que la suma es la misma accediendo en los 3 tipos de recorridos posibles.
    4. Verifica que la suma es la misma cuando se suman los elementos de los subárboles izquierdo y derecho. ¿Por qué?\
   `Se visitan exactamente los mismos nodos, por eso la suma, siempre es igual, lo único que cambia es el orden de los sumandos. Para el segundo programa de prueba con aleatoriedad, es el mismo motivo.`
    5. ¿Cuál es la altura del árbol?\
   `La altura máxima es 128, ya que el árbol está completamente desbalanceado al haber introducido elemento  a elemento`
    6. ¿Cuál es el camino para llegar al valor 110? ¿Cuál es su longitud de camino?\
   `Consiste en una lista tal que así: 0 → 1 → 2 → ... → 110`\
   `Su longitud es: 110 (al tratarse de 111 nodos recorridos)`
4. Crea un segundo programa de prueba.\
`Creado en el archivo MainABDBDEConAleatoriedad.java (preguntas i, ii, iii, iv)`
    1. Añade los números de 0 a 128 PERO DE MANERA ALEATORIA y sin repetir.
    2. Calcula la suma (getSuma())
    3. Verifica que la suma es la misma accediendo en los 3 tipos de recorridos posibles.
    4. Verifica que la suma es la misma cuando se suman los elementos de los subárboles izquierdo y derecho. ¿Por qué?
    5. ¿Cuál es la altura del árbol? ¿por qué?\
   `La altura cambia con cada vez que se ejecuta el código, porque el árbol es distinto al no saber qué número del 0 al 128 se ha introducido primero en la raíz... Pero en comparación con al primer programa, en la mayoria de casos será significativamente menor, al tratarse de un árbol que está muchísimo más equilibrado`
    6. ¿Cuál es el camino para llegar al valor 110? ¿Cuál es su longitud de camino?\
   `Es más corto y depende de cada ejecución, al igual que la altura. Su longitud también varía según el camino del árbol generado... Hay muchas posibilidades`
* Explique las diferencias (si las ha habido) de los resultados obtenidos entre los dos programas de prueba.\
`Las diferencias son las siguientes: `
  * El árbol ordenado está completamente desbalanceado.
  * El árbol aleatorio tiende a estar más equilibrado.
  * La altura cambia significativamente. 
  * Los caminos son más largos en el árbol ordenado. 
  * La suma y los recorridos no cambian.
* ¿Qué sucede con los resultados si ejecuta los programas de prueba varias veces?\
`Que para el primer programa, el resultado será siempre mismo, mientras que para el segundo, como se ha específicado antes, el resultado obtenido en una segunda ejecución, es muy probable que sea DIFERENTE que al del primero...`

# Grafos (Respuestas)
* ¿Cuál es el camino mínimo entre dos entidades A y B del grafo?\
`Se específica en el método mínimoCamino() usado en la clase MainGrafo.java, que pertenece a la clase Grafo.java`
* Dado un archivo de datos que se carga en el grafo, ¿genera un grafo disjunto?  Cree dos archivos que generen cada opción posible y compruebe en el código.\
`Esto se ha comprobado con la función esConexo() en el mismo archivo específicado en la pregunta anterior...` 
* Suponiendo un grafo de conocimiento general con la información de los premios Nobel de todas las áreas, cómo harías para responder a la pregunta: ¿Qué físico famoso nació en la misma ciudad que Einstein?. Crea el fichero de datos para completar el grafo y poder extraer la respuesta a esta pregunta. Crea el código para verificarlo.\
`El código para poder verificarlo está en la clase ConsultaEinstein.java, y se ha testeado en el archivo MainGrafo.java usando varias tripletas con diferente tipo de información de varias personas.`\
\
`La forma para poder llevar esto a cabo sería primeramente obtener la ciudad (objeto) de la persona/sustantivo (Einstein) de la tripleta que se ha pasado como parámetro. Luego ver si existen relaciones/pares/predicados que contengan al objeto como lugar de nacimiento, copiando las personas de cada una de las relaciones, y finalmente, se filtra según si son físicos o no con la función esFísico()`
* Añada una tripleta <"persona:Antonio", "nace_en", "lugar:Villarrubia de los Caballeros"> al grafo. Liste cuáles son los lugares de nacimiento de los premios Nobel. ¿Qué caminos necesita recorrer para que su respuesta fuese correcta?\
`Se ha añadido la tripleta, y al mismo tiempo, se ha comprobado la lista de los lugares de nacimiento de los premios Nobel, ambos en el archivo MainGrafo().java`
* ¿Qué tipos de nodos tiene el grafo?\
`Esto básicamente se refiere a las tripletas. Tenemos los siguientes tipos:`
  * Personas -> Einstein, Curie
  * Lugares -> Ulm, Varsovia
  * Literales -> Nóbel, físico
  * Relaciones -> nace_en, premio (predicado)\
  `En otras palabras, todos los nodos que están guardado dentro del conjunto de los pares... las relaciones...`
* ¿Qué es una ontología? ¿Qué relación tiene con los grafos? ¿Podríamos crear una ontología para nuestro problema? ¿Qué haríamos con ella?\
\
`En el contexto de Ciencias de la Computación (y no Filosofía), consiste en un modelo formal que permite establecer y definir los conceptos de un dominio y sus relaciones. Su relación con los grafos es mediante la estructura RDF, que da lugar a la definición de nodos como sujetos/objetos, arcos como predicados, y estructura el conocimiento en base a los archivos JSON que se han pasado. Es posible crear una ontología, sí, solo se trata de definir correctamente los strings de cada uno de los conceptos del dominio. Con ella podemos crera estructuras de búsqueda más eficientes y permitir una mayor cohesión y relación entre diferentes tipos de datos, que el ordenador pueda comprender. Básicamente, estamos realizando conceptualmente un modelado complejo, que además, contiene su propia semántica en base a cómo hemos estructurado el grafo con la ontología`