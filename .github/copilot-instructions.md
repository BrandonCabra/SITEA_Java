# Project Guidelines

## Scope
These instructions apply to the whole SITEA_Java workspace.
Use this file as the single source of workspace-wide agent guidance.

## Architecture
- Main app is a Jakarta EE 10 WAR project built with Maven (groupId `com.sena`, artifactId `sitea`).
- Layer flow: JSF/Facelets views (`src/main/webapp`) -> managed beans/controllers (`src/main/java/com/sena/sitea/beans` and `src/main/java/com/sena/sitea/controller`) -> EJB facades (`src/main/java/com/sena/sitea/services`) -> JPA entities (`src/main/java/com/sena/sitea/entities`) -> MySQL.
- Python upload microservice lives in `python_upload_service` and integrates over HTTP.

## Build And Run
- Java build: `mvn clean install`
- Docker stack (Java + Python + MySQL): `docker-compose up --build`
- Python service local run: `python app.py` (from `python_upload_service`)
- Python service smoke test: `python test_service.py` (from `python_upload_service`)

## Environment Requirements
- JDK 25 is required (`pom.xml` uses `maven.compiler.release=25`).
- Maven is required to build the WAR.
- Docker is optional but recommended for full local stack (`webapp`, `python-service`, `db`).

## Conventions
- Keep feature code in existing package structure under `src/main/java/com/sena/sitea`:
  - `controller` for feature orchestration
  - `services` for EJB facades (`*Facade` + `*FacadeLocal`)
  - `entities` for JPA models
  - `converters` for JSF converters
- Keep UI pages modular under `src/main/webapp/views/<module>/` and shared layouts under `src/main/webapp/templates/`.
- Keep SQL migrations as root-level `.sql` files with descriptive names, following existing pattern (for example `MIGRACION_*.sql`, `database_updates_*.sql`).
- Do not modify generated or build-output directories (`target/`).

## Agent Working Rules
- Prefer minimal, targeted changes; do not reformat unrelated code.
- Validate with the smallest relevant command for changed area:
  - Java changes: `mvn clean install`
  - Python upload service changes: `python test_service.py`
- For infra/runtime changes, prefer validating with `docker-compose up --build` when feasible.

## Common Pitfalls
- Build failures often come from wrong Java version (must be 25).
- Deployment/runtime issues may come from missing datasource/JNDI configuration in app server.
- SendGrid features require environment setup; avoid hardcoding API keys.
- Python upload service requires writable `uploads/` directory.

## Documentation Index (Link, Do Not Duplicate)
- Main documentation index: `INDICE_DOCUMENTACION.md`
- Java module architecture: `ARQUITECTURA_MODULO.md`
- SendGrid architecture: `ARQUITECTURA_SENDGRID.md`
- Quick implementation steps: `GUIA_RAPIDA_IMPLEMENTACION.md`
- Known fixes and regressions: `CORRECCIONES_ERRORES.md`
- Python integration details: `INTEGRACION_PYTHON_UPLOAD.md`
- SendGrid setup details: `INSTRUCCIONES_SENDGRID_FINAL.md`
- Python service docs: `python_upload_service/README.md`
