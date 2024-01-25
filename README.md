# REST API de Gestão de Usuários, Autenticação e Permissionamento via tokens JWT

**Spring Boot 3 | Spring Security 6 | Docker**

<br>

### Suporte

- No momento, registro simples e direto de usuários
- Autenticação via token JWT com tempo de expiração. 
    - Default para teste: 5min
- Protenção de rotas baseado ROLES de permissonamento
- Administradores podem criar novas ROLES

<br>

Endpoint | Nível de Acesso
--------- | ------
error | Rota liberada
/admin | Somente administrador
/roles/** | Somente administrador
/users | Rota liberada
/users/{id}/role | Somente ADMIN e SET_USER_ROLE
/auth/** | Rota liberada
/h2/** | Rota liberada

<br>

### TODO

- [ ] Validar cadastro do usuário via email
- [ ] Completar operações do CRUD de ROLES
- [ ] Containerizar API
- [ ] Helm Chart