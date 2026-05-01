# Pract2Estruct
---
## PL2a — Árbol Binario de Búsqueda

Implementación del TAD **`ArbolBinarioDeBusqueda<T>`** parametrizable con tipo genérico (`Comparable`).

### Funcionalidades

| Método | Descripción |
|--------|-------------|
| `add(T dato)` | Inserta un elemento manteniendo el orden |
| `getGrado()` | Grado máximo del árbol |
| `getAltura()` | Altura del árbol |
| `getCamino(T dato)` | Camino hasta un nodo |
| `getListaDatosNivel(int n)` | Nodos de un nivel concreto |
| `isArbolCompleto()` | ¿Todas las hojas al mismo nivel? |
| `isArbolCasiCompleto()` | ¿Hojas en dos niveles contiguos desde la izquierda? |
| `isArbolHomogeneo()` | ¿Todos los subárboles con el mismo número de hijos? |
| `getListaPreOrden()` | Recorrido pre-orden |
| `getListaPostOrden()` | Recorrido post-orden |
| `getListaOrdenCentral()` | Recorrido en orden central |
| `getSubArbolIzquierda()` | Subárbol izquierdo |
| `getSubArbolDerecha()` | Subárbol derecho |

### Subclase de ejemplo

**`ArbolBinarioDeBusquedaEnteros`** — añade `getSuma():int`.

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

| Nombres |  
|-------------|
| **Rosa** |
| **Rudy** |
