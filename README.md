# Pract2Estruct
---
## PL2a — Árbol Binario de Búsqueda

Implementación del TAD **[`ArbolBinarioDeBusqueda<T>`](src/Rosa/el2/Arbol/Arbol/ArbolBinarioDeBusqueda.java)** parametrizable con tipo genérico (`Comparable`).

### Funcionalidades

| Método | Descripción |
|--------|-------------|
|[`add(T dato)`](src/Rosa/el2/Arbol/Arbol/ArbolBinarioDeBusqueda.java#L78) | Inserta un elemento manteniendo el orden |
|[`getGrado()`](src/Rosa/el2/Arbol/Arbol/Nodo_Arbol_Binario.java.java#L44)  | Grado máximo del árbol |
|[`getAltura()`](src/Rosa/el2/Arbol/Arbol/ArbolBinarioDeBusqueda.java#L143) | Altura del árbol |
|[`getCamino(T dato)`](src/Rosa/el2/Arbol/Arbol/ArbolBinarioDeBusqueda.java#L254) | Camino hasta un nodo |
|[`getListaDatosNivel(int n)`](src/Rosa/el2/Arbol/Arbol/ArbolBinarioDeBusqueda.java#L157) | Nodos de un nivel concreto |
|[`isArbolCompleto()`](src/Rosa/el2/Arbol/Arbol/ArbolBinarioDeBusqueda.java#L442) | ¿Todas las hojas al mismo nivel? |
|[`isArbolCasiCompleto()`](src/Rosa/el2/Arbol/Arbol/ArbolBinarioDeBusqueda.java#L489) | ¿Hojas en dos niveles contiguos desde la izquierda? |
|[`isArbolHomogeneo()`](src/Rosa/el2/Arbol/Arbol/ArbolBinarioDeBusqueda.java#L408) | ¿Todos los subárboles con el mismo número de hijos? |
|[`getListaPreOrden()`](src/Rosa/el2/Arbol/Arbol/ArbolBinarioDeBusqueda.java#L371) | Recorrido pre-orden |
|[`getListaPostOrden()`](src/Rosa/el2/Arbol/Arbol/ArbolBinarioDeBusqueda.java#L339) | Recorrido post-orden |
|[`getListaOrdenCentral()`](src/Rosa/el2/Arbol/Arbol/ArbolBinarioDeBusqueda.java#L307)| Recorrido en orden central |
|[`getSubArbolIzquierda()`](src/Rosa/el2/Arbol/Arbol/ArbolBinarioDeBusqueda.java#L33) | Subárbol izquierdo |
|[`getSubArbolDerecha()`](src/Rosa/el2/Arbol/Arbol/ArbolBinarioDeBusqueda.java#L54) | Subárbol derecho |

### Subclase de ejemplo

**[`ArbolBinarioDeBusquedaEnteros`](src/Rosa/el2/Arbol/AVL/Ejercicio/ArbolBinarioDeBusquedaEnteros.java)** — añade [`getSuma():int`](src/Rosa/el2/Arbol/AVL/Ejercicio/ArbolBinarioDeBusquedaEnteros.java#L39).

#### Programa de prueba 1 — Inserción ordenada (0 a 128)
- Calcula la suma por los 3 recorridos
- Compara suma total vs suma de subárboles
- Altura del árbol e camino al valor `110`

#### Programa de prueba 2 — Inserción aleatoria (0 a 128, sin repetir)
- Mismas verificaciones que el programa 1
- Compara diferencias de altura y caminos respecto al orden fijo

---

## PL2b — Grafos de Conocimiento (RDF N-Triples)

Construcción de un **grafo de conocimiento** basado en RDF con formato N-Triples simplificado en JSON.

### ¿Qué hace?

- Carga un grafo desde un archivo JSON con tripletas RDF (`<sujeto, predicado, objeto>`)
- Calcula el **camino mínimo** entre dos entidades
- Detecta si el grafo es **disjunto** (dos archivos de prueba incluidos)
- Responde a consultas sobre **Premios Nobel** (ej: *¿Qué físico famoso nació en la misma ciudad que Einstein?*)
- Permite añadir nuevas tripletas dinámicamente
- Identifica los **tipos de nodos** del grafo


Las respuestas teóricas (ontologías, tipos de nodos, etc.) están en [`RESPUESTAS.md`](./RESPUESTAS.md).

---

## Autores

### Colaboradores

| Nombres | Carpeta |
| :--- | :--- |
| **Rosa Carbajo Rodríguez** | [`link`](src/Rosa) |
| **Rudy Benjamín Coarna Navarro** | [`link`](src/Rudy) |
