## REST API de Gestão de Usuários, Autenticação e Permissão baseada funções (Roles)

**Spring Boot 3 & Spring Security 6**

### Suporte

- No momento, registro direto de usuários, sem validação via email
- Autenticação via token JWT com tempo de expiração. Default para teste e de 5min
- Protenção de rotas baseado ROLES
- Somente __Administradores__ ou perfil __SET_ROLE__ podem criar novas ROLES

Endpoint | Nível de Acesso
--------- | ------
error | Rota liberada
/admin | Somente administrador
/roles/** | Somente administrador
/users | Rota liberada
/users/{id}/role | Somente ADMIN e SET_ROLE
/auth/** | Rota liberada
/h2/** | Rota liberada
