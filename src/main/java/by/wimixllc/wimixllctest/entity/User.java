/*
РЕСТ сервисы для
1 регистрация пользователя (после регистрации появляется возможность получить токен)
2 создание профиля пользователя(инфа на твое усмотрение) (авторизованный)
3 получение профиля (автоизованный)
4 обновление профиля (авторизованный)
5 получение списка пользователей (с пагинацией) по параметру(на усмотрение)
6 подключение в вебсокет - (авторизованный)
7 натификация клиента о добавлении нового юзераб изменение данных о других юзерах- через вебсокет
8 возможность общения в чате между двумя пользователями
9 формирование статистического отчета по новым юзерам,
по наиболее часто общающимся, с последующей отправкой на емейл за период, конфигурируемый
 */

package by.wimixllc.wimixllctest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Entity(name = "Users")
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "users_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_role_id", referencedColumnName = "id")})
    private Set<Role> userRoles;

}
