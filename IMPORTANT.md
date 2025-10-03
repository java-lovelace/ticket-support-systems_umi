# Sistema de Soporte (Tickets) - Especificaciones del Equipo

## 📋 Convenciones de Trabajo

### Commits
Todos los commits deben seguir el formato de **Conventional Commits** para mantener un historial claro y trazable:

#### Formato
```
<tipo>: <descripción breve en inglés>
```

#### Tipos permitidos

**`feat:`** - Para agregar nueva funcionalidad
```bash
git commit -m "feat: add ticket assignment validation"
git commit -m "feat: implement top categories report"
git commit -m "feat: add comment history to tickets"
```

**`fix:`** - Para corregir errores o bugs
```bash
git commit -m "fix: resolve null pointer in TicketDao"
git commit -m "fix: correct JOIN query in listByAssignee"
git commit -m "fix: handle empty category list"
```

### Ejemplos de buenos commits

✅ **Correcto:**
- `feat: add ticket status transition validation`
- `feat: implement filter by status and category`
- `fix: correct database connection leak in TicketDao`
- `fix: resolve duplicate entries in top categories query`

❌ **Incorrecto:**
- `agregué validación` (no sigue formato)
- `arreglé un bug` (muy vago, no especifica qué)
- `feat: arreglé la búsqueda` (usa 'feat' para un fix)
- `cambios varios` (no es descriptivo)

---

## 🔀 Git Flow

### Ramas principales
- **`main`**: Código estable y listo para producción
- **`develop`**: Rama de integración para desarrollo

### Ramas de trabajo
- **`feature/*`**: Para nuevas funcionalidades
  ```bash
  git checkout -b feature/ticket-assignment
  git checkout -b feature/top-categories-report
  ```

### Flujo de trabajo

1. **Crear rama desde develop:**
   ```bash
   git checkout develop
   git pull origin develop
   git checkout -b feature/nombre-funcionalidad
   ```

2. **Hacer commits siguiendo las convenciones:**
   ```bash
   git add .
   git commit -m "feat: add ticket creation validation"
   ```

3. **Push y Pull Request:**
   ```bash
   git push origin feature/nombre-funcionalidad
   ```
   - Crear PR hacia `develop` en Azure DevOps
   - Vincular con Work Item correspondiente

4. **Merge a develop:**
   - Después de code review aprobado
   - Eliminar rama feature después del merge

---

## 📊 Azure Boards

### Trazabilidad
- **Cada feature branch debe estar vinculada a un Work Item**
- Incluir el ID del Work Item en los commits cuando sea posible:
  ```bash
  git commit -m "feat: implement ticket assignment (#123)"
  ```

### Estados de Work Items
- **To Do**: Tarea asignada pero no iniciada
- **In Progress**: En desarrollo activo
- **In Review**: En Pull Request
- **Done**: Completado y mergeado

---

## 🚫 Prácticas a Evitar

- ❌ Commits directos a `main` o `develop`
- ❌ Commits sin mensaje descriptivo
- ❌ Mezclar múltiples funcionalidades en un solo commit
- ❌ Commits en español o sin formato convencional
- ❌ Push forzado (`git push -f`) sin coordinación
- ❌ Branches sin vincular a Work Items

---

## ✅ Checklist antes de hacer Push

- [ ] El código compila sin errores
- [ ] Los cambios están probados localmente
- [ ] El commit sigue el formato `tipo: descripción en inglés`
- [ ] La rama está actualizada con `develop`
- [ ] El Work Item está vinculado
- [ ] El mensaje describe claramente QUÉ se hizo

---

## 👥 Responsabilidades del Equipo

- **Todos**: Seguir convenciones de commits y Git Flow
- **Todos**: Mantener Work Items actualizados
- **Reviewers**: Verificar que PRs cumplan convenciones antes de aprobar
- **Líder técnico**: Supervisar que `main` permanezca estable

---

## 📞 Dudas o Conflictos

Si tienes dudas sobre:
- Qué tipo de commit usar → Pregunta antes de commitear
- Conflictos en merge → Coordina con el equipo antes de resolverlos
- Modificaciones a `main` → **Nunca hacerlo directamente**

---

**Recuerda:** La consistencia en nuestras prácticas de desarrollo facilita la colaboración y mantiene el proyecto organizado. 🚀
