-- ============================================================
-- V2: Rol de usuario (ADMIN / USER)
-- El usuario administrador por defecto lo crea la aplicacion
-- al arrancar (DataInitializer), con la contrasena ya hasheada.
-- ============================================================

ALTER TABLE usuario ADD COLUMN rol VARCHAR(20) NOT NULL DEFAULT 'USER';
