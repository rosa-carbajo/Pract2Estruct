## 1. ¿Cuál es el camino mínimo entre dos entidades A y B del grafo?
Para calcular el camino mínimo en este grafo de conocimiento, se utiliza el algoritmo de **Búsqueda en Anchura (BFS - Breadth-First Search)**. 

*   **Fundamento**: Dado que nuestro grafo es no dirigido y todas las relaciones (aristas) tienen el mismo peso, el BFS garantiza encontrar la ruta con el menor número de saltos entre dos nodos.
*   **Funcionamiento**: El algoritmo parte del nodo A, explora todos sus vecinos inmediatos, luego los vecinos de sus vecinos, y así sucesivamente hasta encontrar el nodo B.
*   **En nuestro grafo**: 
    *   Entre `persona:Albert Einstein` y `lugar:Ulm`, el camino mínimo es **1**.
    *   Si existiera otro físico nacido en Ulm, el camino entre Einstein y ese físico sería **2** (Einstein → Ulm → Otro Físico).

## 2. Grafo Disjunto
Un grafo es **disjunto** cuando está compuesto por dos o más grupos de nodos que no tienen ninguna conexión entre sí.

*   **Estado actual**: El grafo generado es **disjunto**. 
*   **Evidencia**: Tenemos conocimiento formado por persona: Albert Einstein, el año 1921 y lugar:Ulm. Por otro lado, tenemos a persona: Antonio y lugar: Villarrubia de los Caballeros. No existe ninguna arista (relación) que conecte ambos grupos.
*   **Prueba de código**: Si se realiza un recorrido desde Einstein,  nunca llegaremos a Antonio, confirmando que son componentes conexos separados.

## 3. ¿Qué físico famoso nació en la misma ciudad que Einstein?
Para responder a esta pregunta mediante el grafo, se debe realizar una **consulta de vecindad**:

1.  Se localiza el nodo `persona:Albert Einstein`.
2.  Se sigue la arista con el predicado `nace_en` hasta llegar al nodo `lugar:Ulm`.
3.  Desde `lugar:Ulm`, se buscan todas las aristas de entrada (`aristas_antes`) que tengan el predicado `nace_en`.
4.  Cualquier otro nodo de tipo `persona` conectado a ese lugar será la respuesta (por ejemplo, si añadiéramos a **Berlichingen** al JSON con `nace_en` hacia `lugar:Ulm`).

El código para verificarlo se llama en el Main: responderConsultaFisico()

## 4. Antonio y los Premios Nobel
Hemos añadido la tripleta `<"persona:Antonio", "nace_en", "lugar:Villarrubia de los Caballeros">`.

*   **Lugares de nacimiento de los Premios Nobel**: En el grafo actual, el único lugar de nacimiento de un Nobel es `lugar:Ulm` (asociado a Einstein).
*   **Caminos necesarios**: Para que la respuesta fuese correcta y completa, el sistema debe:
    1.  Identificar todos los nodos que tienen una relación `premio:Nobel`.
    2.  Desde esos nodos, seguir la relación `nace_en` para obtener el lugar.
    3.  Si un nodo (como Antonio) no tiene una relación con un premio, no debe aparecer en la lista de lugares de nacimiento de Nobeles.

## 5. ¿Qué tipos de nodos tiene el grafo?
Basándonos en la estructura semántica y el JSON de carga, el grafo distingue los siguientes tipos de nodos:

*   **Entidades Etiquetadas (URIs)**: Nodos que representan conceptos únicos definidos en la ontología:
    *   `persona`: Individuos (Einstein, Antonio).
    *   `lugar`: Ubicaciones geográficas (Ulm, Villarrubia).
    *   `premio`: Categorías de galardones.
*   **Literales**: Datos finales sin prefijo que representan valores específicos, como el año `1921`.

## 6. Ontología y Grafos
*   **¿Qué es una ontología?**: Es un modelo formal que define las categorías (clases), propiedades y relaciones que pueden existir en un dominio de conocimiento.
*   **Relación con los grafos**: El grafo es la **instancia** (los datos reales), mientras que la ontología es el **esquema** (las reglas). La ontología dice "Las personas nacen en lugares"; el grafo dice "Einstein nació en Ulm".
*   **Aplicación en nuestro problema**: Podríamos crear una ontología para definir que el predicado `nace_en` solo es válido si el Sujeto es de tipo `persona` y el Objeto es de tipo `lugar`. Esto permitiría validar automáticamente que los datos del JSON son coherentes y evitar errores de carga.



