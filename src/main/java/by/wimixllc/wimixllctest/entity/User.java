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
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name", unique = true, nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @ManyToMany(fetch = EAGER)
    @JoinTable(
            name = "users_user_role",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "user_role_id",
                    referencedColumnName = "id"
            ))
    private Set<Role> userRoles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        getUserRoles().forEach(userRole -> {
            authorities.add(new SimpleGrantedAuthority(userRole.getRoleName()));
        });
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
